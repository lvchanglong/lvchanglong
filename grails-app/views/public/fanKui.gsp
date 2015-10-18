<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main"/>
		<title>反馈${ grailsApplication.config.html.title.suffix }</title>
	</head>
	<body>
		<g:render template="/layouts/plugins/faBuFanKui" />
		
		<hr/>

		<div id="fanKui-wrapper">
			<div id="fanKui-load">
				<ul>
					<g:each in="${fanKuiList}" status="i" var="fanKui">
						<li class="relative">
							
							<div class="pull-left" style="position:relative;margin:0 10px 0 0;">
								<g:img uri="${ createLink(controller:'public', action:'loadTouXiang') }" width="70px" height="70px" alt="头像" class="img-thumbnail"/>
								<p style="margin-top:5px;">
									${ fanKui.xingMing }
								</p>
							</div>
						
							<span class="neiRong" style="margin-bottom:25px;">
								${fanKui.neiRong}									
							</span>
							
							<div style="position:absolute;right:0;bottom:0;padding-bottom:5px;">
								<g:formatDate date="${fanKui.dateCreated}" format="yyyy-MM-dd HH:mm:ss"/>
							</div>
							
							<div style="clear:both;width:100%;border-bottom:1px dashed lightgray;margin:20px 0;"></div>
						</li>
					</g:each>
				</ul>
				<div class="pagination">
					<g:paginate total="${fanKuiCount}" />
				</div>
			</div>
		</div>
		<g:javascript>
			paginate("#fanKui-wrapper", "#fanKui-load");
		</g:javascript>
	</body>
</html>