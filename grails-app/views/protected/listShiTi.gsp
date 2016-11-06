<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main"/>
		<title>实体管理${ grailsApplication.config.html.title.suffix }</title>
	</head>
	<body>
		<g:if test="${ session.uid }">
			<g:form name="shiTi-save-form" url="[controller:'protected', action:'saveShiTi']" class="clearfix">
				<g:hiddenField name="leiBie" value="${params.leiBie}"/>

				<g:textField name="biaoTi" value="" placeholder="标题" required="" autofocus="" class="form-control mb15w"/>

				<g:textArea id="shiTiNeiRongSave" name="neiRong" value="100%，500px" placeholder="内容" class="ckeditor"/>
				
				<g:hiddenField name="yongHu.id" value="${ session.uid }"/>
				<g:submitButton name="faBu" value="发布" class="btn btn-primary pull-right" style="margin-top:8px;margin-right:10px;"/>
				<div id="shiTi-message-save" class="alert alert-info" role="alert">没吃药，萌萌哒...</div>
			</g:form>
			<g:javascript>
				jQuery("#shiTi-save-form").ajaxForm({
					beforeSerialize: function($form, options) { 
					    var neiRongHtml = CKEDITOR.instances.shiTiNeiRongSave.getData();
					    jQuery("#shiTiNeiRongSave").val(neiRongHtml);
					},
					success:function(data,textStatus){
						success(data,textStatus,'#shiTi-message-save');
						window.location.reload();
					}, 
					error:function(XMLHttpRequest,textStatus,errorThrown){
						error(XMLHttpRequest,textStatus,errorThrown,'#shiTi-message-save');
					}
				});
			</g:javascript>
			
			<hr/>
		</g:if>
			
		<g:if test="${shiTiList}">
			<div id="shiTi-wrapper">
				<div id="shiTi-load">
					<ul>
						<g:each in="${shiTiList}" status="i" var="shiTi">
							<li>
								<g:render template="/public/shiTi" model="[shiTi:shiTi]"/>
							</li>
						</g:each>
					</ul>
					<div class="pagination">
						<g:paginate total="${shiTiCount}" params="[leiBie:params.leiBie]"/>
					</div>
				</div>
			</div>
			<g:javascript>
				paginate("#shiTi-wrapper", "#shiTi-load");
			</g:javascript>
		</g:if>
	</body>
</html>