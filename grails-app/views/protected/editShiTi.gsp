<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main"/>
		<title>实体修改${ grailsApplication.config.html.title.suffix }</title>
	</head>
	<body>
		<g:if test="${ session.uid && session.uid == shiTi.yongHu?.id || dangQianYongHu?.shiFouGuanLiYuan() }">
			<g:form name="shiTi-update-form" url="[resource:shiTi, action:'update']" method="PUT" >
				<g:hiddenField name="version" value="${shiTi?.version}" />

				<g:textField name="biaoTi" required="" value="${shiTi?.biaoTi}" autofocus="" class="form-control mb15w"/>
				<g:textArea name="miaoShu" required="" value="${shiTi?.miaoShu}" class="form-control mb15w" placeholder="描述"/>
				<g:textArea id="shiTiFuJianUpdate" name="fuJian" required="" value="${shiTi?.fuJian}" class="ckeditor"/>
				<br/>
				<g:textArea id="shiTiNeiRongUpdate" name="neiRong" required="" value="${shiTi?.neiRong}" class="ckeditor"/>
				
				<g:actionSubmit class="btn btn-primary pull-right" action="update" value="${message(code: 'default.button.update.label', default: 'Update')}" style="margin:8px 15px 0 15px;"/>
			</g:form>
			<g:javascript>
				jQuery("#shiTi-update-form").ajaxForm({
					beforeSerialize: function($form, options) { 
					    var neiRongHtml = CKEDITOR.instances.shiTiNeiRongUpdate.getData();
					    jQuery("#shiTiNeiRongUpdate").val(neiRongHtml);
					    
					    var fuJianHtml = CKEDITOR.instances.shiTiFuJianUpdate.getData();
					    jQuery("#shiTiFuJianUpdate").val(fuJianHtml);
					},
					success:function(data,textStatus){
						success(data,textStatus,'#shiTi-message-console');
					}, 
					error:function(XMLHttpRequest,textStatus,errorThrown){
						error(XMLHttpRequest,textStatus,errorThrown,'#shiTi-message-console');
					}
				});
			</g:javascript>
			
			<g:form name="shiTi-delete-form" url="[resource:shiTi, action:'delete']" method="DELETE">
				<g:actionSubmit  class="btn btn-danger pull-right" action="delete" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" style="margin-top:8px;"/>		
			</g:form>
			<g:javascript>
				jQuery("#shiTi-delete-form").ajaxForm({
					success:function(data,textStatus){
						success(data,textStatus,'#shiTi-message-console');
					}, 
					error:function(XMLHttpRequest,textStatus,errorThrown){
						error(XMLHttpRequest,textStatus,errorThrown,'#shiTi-message-console');
					}
				});
			</g:javascript>
			
			<div id="shiTi-message-console" class="alert alert-info" role="alert">没吃药，萌萌哒...</div>
		</g:if>
	</body>
</html>