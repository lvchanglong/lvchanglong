<%@ page import="lvchanglong.YongHu" %>

<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main"/>
		<title>反馈${ grailsApplication.config.html.title.suffix }</title>
		<g:set var="dangQianYongHu" value="${ YongHu.get(session.uid) }" />
	</head>
	<body>
		<g:render template="/layouts/plugins/faBuFanKui" />
		
		<hr/>

		<div id="fanKui-wrapper">
			<div id="fanKui-load">
				<ul>
					<g:each in="${fanKuiList}" status="i" var="fanKui">
						<li class="relative">
							<span class="neiRong" style="display:block;margin-bottom:25px;">
									<span style="font-size:30px;font-weight:bold;">${ fanKui.xingMing }</span>：${fanKui.neiRong}									
							</span>
							<div style="position:absolute;left:0;bottom:0;margin-bottom:3px;color:lightgray;">
								<g:formatDate date="${fanKui.dateCreated}" format="yyyy-MM-dd HH:mm:ss"/>
							</div>

							<g:if test="${dangQianYongHu?.shiFouGuanLiYuan()}">
								<div style="position:absolute;right:0;bottom:0;margin-bottom:3px;">
									<g:form name="fanKui-delete-form" url="[controller:'private', action:'deleteFanKui']" method="DELETE" class="deleteForm">
										<g:hiddenField name="id" value="${fanKui.id}" />
										<g:submitButton name="submit" value="删除" class="btn btn-danger"/>
									</g:form>
								</div>
							</g:if>
							
							<div style="clear:both;width:100%;border-bottom:1px dashed lightgray;margin:20px 0;"></div>
						</li>
					</g:each>
				</ul>
				<div class="pagination">
					<g:paginate total="${fanKuiCount}" />
				</div>
			</div>
		</div>
	</body>
</html>