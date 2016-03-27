<%@ page import="lvchanglong.YongHu" %>

<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main"/>
		<title>功能测试${ grailsApplication.config.html.title.suffix }</title>
	</head>
	<body>
		<g:render template="/protected/fileUpload"/>

		%{--<g:render template="/layouts/plugins/3D" />

		<g:form name="ajaxForm" controller="private" action="ajaxTest">
			<span id="console">控制台</span>
			<g:submitButton name="submit" value="提交"/>
		</g:form>

		<g:javascript>
			jQuery("#ajaxForm").ajaxForm({
				success:function(data,textStatus){
					success(data,textStatus,'#console');
				},
				error:function(XMLHttpRequest,textStatus,errorThrown){
					error(XMLHttpRequest,textStatus,errorThrown,'#console');
				}
			});
		</g:javascript>--}%
		
		<content tag="header">
		</content>
		
		<content tag="footer">
		</content>
	</body>
</html>