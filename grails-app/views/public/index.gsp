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
			<g:if test="${params.content}">
				<div class="col-md-12">
					<hr/>
				</div>
				<g:if test="${instanceList}">
					<g:each in="${instanceList}" status="i" var="instance">
						<g:if test="${ instance }">
							<div class="col-md-3">
								<div class="relative" style="display:inline-block;min-height:90px;width:100%;">
									<g:link controller="public" action="element" id="${instance.id}" target="_blank">[${ instance.leiBie }]${ instance.biaoTi }</g:link>
								</div>
							</div>
						</g:if>
					</g:each>
					<div class="col-md-12">
						<div class="pagination" style="clear:both;margin-top:30px;">
							<g:paginate total="${instanceCount}" params="[content:params.content]"/>
						</div>
					</div>
				</g:if>
				<g:else>
					<div class="col-md-12">
						<g:render template="/layouts/plugins/errorInfo" model="[message:'没有数据呀（O _ O）']"/>
					</div>
				</g:else>

			</g:if>
			<g:else>

				<g:if test="${video}">
					<div class="col-md-12">
						<div class="listBox" style="background-color:${BkColor.getInst()};">
							<div class="content relative clearfix">
								${ video.neiRong }
							</div>
							<div class="relative">
								<g:link controller="public" action="element" id="${video.id}" target="_blank">${ video.biaoTi }</g:link>
								<span style="float:right;">
									<span class="dateCreated">
										<g:formatDate date="${video.dateCreated}" format="yyyy.MM.dd" />
									</span>
									<span class="separator">|</span>
									<span class="yongHu">
										${video.user.xingMing}
									</span>
									<span class="separator">|</span>
									<span>
										${video.leiBie}&nbsp;${video.id}
									</span>
								</span>
							</div>
						</div>
					</div>
				</g:if>
				<g:if test="${link}">
					<div class="col-md-12">
						<div class="listBox" style="background-color:${BkColor.getInst()};">
							<div class="content relative clearfix">
								<iframe width="100%" height="700" frameborder="0" scrolling="auto" src="${ link.neiRong }">
									<a href="${ link.neiRong }" target="_blank">${ link.biaoTi }</a>
								</iframe>
							</div>
							<div class="relative">
								<g:link controller="public" action="element" id="${link.id}" target="_blank">${ link.biaoTi }</g:link>
								<span style="float:right;">
									<span class="dateCreated">
										<g:formatDate date="${link.dateCreated}" format="yyyy.MM.dd" />
									</span>
									<span class="separator">|</span>
									<span class="yongHu">
										${link.user.xingMing}
									</span>
									<span class="separator">|</span>
									<span>
										${link.leiBie}&nbsp;${link.id}
									</span>
								</span>
							</div>
						</div>
					</div>
				</g:if>
				<g:if test="${texts}">
					<g:each in="${texts}" status="i" var="text">
						<g:if test="${ text }">
							<div class="col-md-${text.getColspan()}">

								<div class="listBox" style="background-color:${BkColor.getInst()};">
									<h1 style="margin-top:0;text-align: center;">
										<g:link controller="public" action="element" id="${text.id}" target="_blank">${ text.biaoTi }</g:link>
									</h1>
									<div class="content relative clearfix">
										${ text.neiRong }
									</div>
									<div class="relative">
										<span class="dateCreated">
											<g:formatDate date="${text.dateCreated}" format="yyyy.MM.dd" />
										</span>
										<span class="separator">|</span>
										<span class="yongHu">
											${text.user.xingMing}
										</span>
										<span class="separator">|</span>
										<span>
											${text.leiBie}&nbsp;${text.id}
										</span>
									</div>
								</div>

							</div>
						</g:if>
					</g:each>
				</g:if>
				<g:if test="${instanceList}">
					<div class="col-md-12">
						<hr/>
					</div>
					<g:each in="${instanceList}" status="i" var="instance">
						<g:if test="${ instance }">
							<div class="col-md-3">
								<div class="relative" style="display:inline-block;min-height:90px;width:100%;">
									<g:link controller="public" action="element" id="${instance.id}" target="_blank">[${ instance.leiBie }]${ instance.biaoTi }</g:link>
								</div>
							</div>
						</g:if>
					</g:each>
				</g:if>
				<g:else>
					<div class="col-md-12">
						<g:render template="/layouts/plugins/errorInfo" model="[message:'没有数据呀（O _ O）']"/>
					</div>
				</g:else>

			</g:else>
		</div>
	</body>
</html>