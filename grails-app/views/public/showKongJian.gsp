<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main"/>
		<title>${ kongJian.biaoTi }${ grailsApplication.config.html.title.suffix }</title>
	</head>
	<body>
		<g:render template="/public/kongJian" model="[kongJian:kongJian]"/>
	</body>
</html>