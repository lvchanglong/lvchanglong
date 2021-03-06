<%@ page import="lvchanglong.ChineseName; lvchanglong.User; lvchanglong.Element; lvchanglong.BkColor" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main"/>
	</head>
	<body>
		<div class="row">
			<g:if test="${instanceList}">
				<g:each in="${instanceList}" status="i" var="instance">
					<g:if test="${ instance }">
						<div class="col-md-12">
							<div class="listBox" style="background-color:${BkColor.getInst()};">
								<div class="content relative clearfix">
									${ instance.neiRong }
								</div>
								<div class="relative">
									<g:link controller="public" action="element" id="${instance.id}" target="_blank">${ instance.biaoTi }</g:link>
									<span style="float:right;">
										<span class="dateCreated">
											<g:formatDate date="${instance.dateCreated}" format="yyyy.MM.dd" />
										</span>
										<span class="separator">|</span>
										<span class="yongHu">
											${instance.user.xingMing}
										</span>
										<span class="separator">|</span>
										<span>
											CODE-${instance.id}
										</span>
									</span>
								</div>
							</div>
						</div>
					</g:if>
					<div class="col-md-12">
						<g:render template="/layouts/plugins/changYanPingLun" model="[sid:'element'+ instance.id]"/>
					</div>
				</g:each>
				<div class="col-md-12">
					<div class="pagination" style="clear:both;margin-top:30px;">
						<g:paginate total="${instanceCount}" params="[q:params.q]"/>
					</div>
				</div>
			</g:if>
			<g:else>
				<div class="col-md-12">
					<g:render template="/layouts/plugins/errorInfo" model="[message:'没有数据呀（O _ O）']"/>
				</div>
			</g:else>
		</div>

		<content tag="header">
			<g:render template="/layouts/headers/subHeader"/>
		</content>
	</body>
</html>