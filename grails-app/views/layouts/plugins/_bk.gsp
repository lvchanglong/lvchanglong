<g:form name="shiTi-save-form" url="[controller:'protected', action:'saveShiTi']" class="clearfix">
	<g:textField name="biaoTi" value="" placeholder="标题" required="" autofocus="" class="form-control"/>

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

<div class="col-md-4">
	<div class="listBox" style="background-color:#C0392B;text-align: center;">
		<g:link controller="public" action="videos" target="_black" style="font-size:70px;">
			<span class="glyphicon glyphicon-film"></span>
			视频
		</g:link>
	</div>
</div>
<div class="col-md-4">
	<div class="listBox" style="background-color:#F39C12;text-align: center;">
		<g:link controller="public" action="links" target="_black" style="font-size:70px;">
			<span class="glyphicon glyphicon-tag"></span>
			链接
		</g:link>
	</div>
</div>
<div class="col-md-4">
	<div class="listBox" style="background-color:#2980B9;text-align: center;">
		<g:link controller="public" action="texts" target="_black" style="font-size:70px;">
			<span class="glyphicon glyphicon-file"></span>
			文本
		</g:link>
	</div>
</div>

<asset:javascript src="stickUp-master/stickUp.min.js"/>
<script>
	jQuery(function($) {
		$(document).ready( function() {
			$('.stickup-wrapper').stickUp();
		});
	});
</script>