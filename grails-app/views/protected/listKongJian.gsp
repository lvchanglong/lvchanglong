<%@ page import="lvchanglong.BkColor" %>

<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main"/>
		<title>空间管理${ grailsApplication.config.html.title.suffix }</title>
	</head>
	<body>
		<g:if test="${ session.uid || dangQianYongHu?.shiFouGuanLiYuan() }">
			<g:form name="kongJian-save-form" url="[controller:'kongJian', action:'save']" class="clearfix">
				<g:textField name="biaoTi" value="" placeholder="标题" required="" autofocus="" class="form-control mb15w"/>
				<g:textArea name="miaoShu" required="" value="" class="form-control mb15w" placeholder="描述"/>
				<g:textArea id="kongJianFuJianSave" name="fuJian" value="附件图片【100%，370】" placeholder="附件" class="ckeditor"/>
				
				<g:textArea name="neiRong" value="" style="margin:15px 0;width:100%;" placeholder="1,2,3,7,9（实体代号格式）" class="form-control"/>
				
				<g:hiddenField name="yongHu.id" value="${ session.uid }"/>
				<g:submitButton name="faBu" value="发布" class="btn btn-primary pull-right" style="margin-top:8px;margin-right:10px;"/>
				<div id="kongJian-message-save" class="alert alert-info" role="alert">没吃药，萌萌哒...</div>
			</g:form>
			<g:javascript>
				jQuery("#kongJian-save-form").ajaxForm({
					beforeSerialize: function($form, options) { 
					    var dataHtml = CKEDITOR.instances.kongJianFuJianSave.getData();
					    jQuery("#kongJianFuJianSave").val(dataHtml);
					},
					success:function(data,textStatus){
						success(data,textStatus,'#kongJian-message-save');
						window.location.reload();
					}, 
					error:function(XMLHttpRequest,textStatus,errorThrown){
						error(XMLHttpRequest,textStatus,errorThrown,'#kongJian-message-save');
					}
				});
			</g:javascript>
			
			<hr/>
		</g:if>
		
		<g:if test="${kongJianList}">
			<div id="kongJian-wrapper">
				<div id="kongJian-load">
					<ul>
						<g:each in="${kongJianList}" status="i" var="kongJian">
							<li>
								<g:render template="/public/kongJian" model="[kongJian:kongJian]"/>
							</li>
						</g:each>
					</ul>
					<div class="pagination" style="clear:both;">
						<g:paginate total="${kongJianCount}"/>
					</div>
				</div>
			</div>
			<g:javascript>
				paginate("#kongJian-wrapper", "#kongJian-load");
			</g:javascript>
		</g:if>
	</body>
</html>