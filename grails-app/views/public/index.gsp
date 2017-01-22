<%@ page import="lvchanglong.User; lvchanglong.Element; lvchanglong.BkColor" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main"/>
	</head>
	<body>
		<div class="row">
			<div class="col-md-12">
				<g:render template="/layouts/plugins/createFeedback"/>
			</div>

			<g:if test="${instanceList}">
				<g:each in="${instanceList}" status="i" var="instance">
					<g:if test="${ instance }">
						<div class="col-md-${instance.getColspan()}">

							<div class="listBox" style="background-color:${BkColor.getInst()};">
								<h1 style="margin-top:0;text-align: center;">
									${ instance.biaoTi }
								</h1>
								<div class="content relative clearfix">
									${ instance.neiRong }
								</div>
								<div class="relative">
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
								</div>
							</div>

						</div>
					</g:if>
				</g:each>
				<div class="col-md-12">
					<div class="pagination" style="clear:both;margin-top:30px;">
						<g:paginate total="${instanceCount}" params="[text:params.text]"/>
					</div>
				</div>
			</g:if>
			<g:else>
				<div class="col-md-12">
					<g:render template="/layouts/plugins/errorInfo" model="[message:'没有数据呀（O _ O）']"/>
				</div>
			</g:else>
		</div>
	</body>
</html>