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
							<span class="neiRong" style="display:block;margin-bottom:25px;">
									<span style="font-size:30px;font-weight:bold;">${ fanKui.xingMing }</span>：${fanKui.neiRong}									
							</span>
							<div style="position:absolute;left:0;bottom:0;margin-bottom:3px;color:lightgray;">
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