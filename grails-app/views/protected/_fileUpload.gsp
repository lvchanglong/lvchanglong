<div class="row">
	<div class="col-md-2">
		<div class="btn btn-success fileinput-button" style="width:100%;height:34px;">
			<i class="glyphicon glyphicon-plus"></i>
			<span>选择文件...</span>
			<input id="fileupload" type="file" name="file" multiple accept="${grailsApplication.config.grails.mime.types.xls}, ${grailsApplication.config.grails.mime.types.xlsx}">
		</div>
	</div>
	<div class="col-md-10">
		<div id="progress" class="progress" style="width:100%;height:34px;margin-bottom:0;">
			<div class="progress-bar progress-bar-success"></div>
		</div>
	</div>
	<div class="col-md-12">
		<div id="files" class="files">
			%{--_fileList--}%
		</div>
	</div>
</div>

<script>
	$(function () {
		'use strict';
		$('#fileupload').fileupload({
			url: "${createLink(controller:"protected", action:"uploadFile")}",
			dataType: 'json',
			limitMultiFileUploadSize: 1000000,
			done: function (e, data) {
//				console.log(data.result.file);
//				$('<p/>').text(data.result.file).appendTo('#files');
				listFiles();
			},
			progressall: function (e, data) {
				var progress = parseInt(data.loaded / data.total * 100, 10);
				$('#progress .progress-bar').css(
						'width', progress + '%'
				);
			}
		}).prop('disabled', !$.support.fileInput).parent().addClass($.support.fileInput ? undefined : 'disabled');
	});

	/**
	 * 更新文件列表
	 */
	function listFiles() {
		jQuery("#files").load("${createLink(controller:'protected', action:'listFiles')}");
	}

	listFiles();
</script>