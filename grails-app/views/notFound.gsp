<!doctype html>
<html>
    <head>
        <title>404</title>
        <meta name="layout" content="main">
        <g:if env="development"><asset:stylesheet src="errors.css"/></g:if>
    </head>
    <body>
		<g:render template="/layouts/plugins/error" model="[message:'Page Not Found (404)']"/>
    </body>
</html>
