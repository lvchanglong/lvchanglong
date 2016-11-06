<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main"/>
		<title>实体修改${ grailsApplication.config.html.title.suffix }</title>
	</head>
	<body>
		<g:if test="${ session.uid && dangQianYongHu?.isMyShiTi(shiTi.id) }">
			<g:form name="shiTi-update-form" url="[controller:'protected', action:'updateShiTi']" method="PUT" >
				<g:hiddenField name="id" value="${shiTi.id}" />
				<g:hiddenField name="version" value="${shiTi?.version}" />

				<g:textField name="biaoTi" required="" value="${shiTi?.biaoTi}" autofocus="" class="form-control mb15w"/>

				<g:textArea id="shiTiNeiRongUpdate" name="neiRong" required="" value="${shiTi?.neiRong}" class="ckeditor"/>

				<g:submitButton name="submit" class="btn btn-primary pull-right" value="${message(code: 'default.button.update.label', default: 'Update')}" style="margin:8px 15px 0 15px;"/>
			</g:form>
			<g:javascript>
				jQuery("#shiTi-update-form").ajaxForm({
					beforeSerialize: function($form, options) {
					    var neiRongHtml = CKEDITOR.instances.shiTiNeiRongUpdate.getData();
					    jQuery("#shiTiNeiRongUpdate").val(neiRongHtml);
					},
					success:function(data,textStatus){
						success(data,textStatus,'#shiTi-message-console');
					},
					error:function(XMLHttpRequest,textStatus,errorThrown){
						error(XMLHttpRequest,textStatus,errorThrown,'#shiTi-message-console');
					}
				});
			</g:javascript>

			<g:form name="shiTi-delete-form" url="[controller:'protected', action:'deleteShiTi']" method="DELETE">
				<g:hiddenField name="id" value="${shiTi.id}" />
				<g:submitButton name="submit" class="btn btn-danger pull-right" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" style="margin-top:8px;"/>
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
		<g:else>
			<g:render template="/layouts/plugins/errorInfo" model="[message:'没有权限']"/>
		</g:else>
	</body>
</html>