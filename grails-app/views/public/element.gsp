<%@ page import="lvchanglong.User; lvchanglong.Element; lvchanglong.BkColor" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main"/>
		<title>${instance?:"不毛之地"}${ grailsApplication.config.html.title.suffix }</title>
	</head>
	<body>
		<div class="row">
			<g:if test="${instance}">
				<div class="col-md-12">
					<div class="listBox" style="background-color:${BkColor.getInst()};">
						<div class="content relative clearfix">

							<g:if test="${instance.isVideo() || instance.isText()}">
								${ instance.neiRong }
							</g:if>

							<g:if test="${instance.isLink()}">
								<iframe width="100%" height="700" frameborder="0" scrolling="auto" src="${ instance.neiRong }">
									<a href="${ instance.neiRong }" target="_blank">${ instance.biaoTi }</a>
								</iframe>
							</g:if>

						</div>
						<div class="relative">
							<span>${ instance.biaoTi }</span>
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
				<div class="col-md-12">
					<g:render template="/layouts/plugins/changYanPingLun" model="[sid:'element'+ instance.id]"/>
				</div>
			</g:if>
			<g:else>
				<div class="col-md-12">
					<g:render template="/layouts/plugins/errorInfo" model="[message:'没有数据呀（O _ O）']"/>
				</div>
			</g:else>
		</div>

		<content tag="header">

		</content>
		<content tag="footer">

		</content>
	</body>
</html>