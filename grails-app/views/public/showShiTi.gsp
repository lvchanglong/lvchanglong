<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main"/>
		<title>${ shiTi.biaoTi }${ grailsApplication.config.html.title.suffix }</title>
	</head>
	<body>
		<g:render template="/public/shiTi" model="[shiTi:shiTi]"/>
		
		<g:render template="/layouts/plugins/baiDuFenXiang"/>
		
		<hr/>
		
		<g:render template="/layouts/plugins/changYanPingLun" model="[sid:'st' + shiTi.id]"/>
	</body>
</html>