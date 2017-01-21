// This is a manifest file that'll be compiled into application.js.
//
// Any JavaScript file within this directory can be referenced here using a relative path.
//
// You're free to add application-wide JavaScript to this file, but it's generally better
// to create separate JavaScript files as needed.
//
//= encoding UTF-8
//= require HubSpot-messenger/build/js/messenger.min.js
//= require HubSpot-messenger/build/js/messenger-theme-future.js
//= require_tree .
//= require_self

if (typeof jQuery !== 'undefined') {
    (function($) {
        $(document).ajaxStart(function() {
            $('#spinner').fadeIn();
        }).ajaxStop(function() {
            $('#spinner').fadeOut();
        });
    })(jQuery);
}

/**
 * 图片查看
 * wrapperSelector:用于更新头像
 * tuPianXuanZe:用于更新选择(按钮)状态
 * shangChuanSelector:用于更新上传(按钮)状态
 * <input type="file" name="file" onchange="tuPianChaKan(this.files, '#touXiangWrapper', '#tuPianXuanZe', '#kaiShiShangChuan');" multiple="false" class="borderBox"/>
 */
function tuPianChaKan(files, wrapperSelector, xuanZeSelector, shangChuanSelector) {

	$xuanZe = jQuery(xuanZeSelector);//上传按钮
	
	if (files.length < 1) {
		$xuanZe.html("选择图片");
		return false;
	}
	
	for(var i = 0; i < files.length; i++) {
         var tuPian = files[i];//图片
         var reader = new FileReader();
         reader.readAsDataURL(tuPian);
         reader.onload=function(e){
             //console.log(e.target.result);
             jQuery(wrapperSelector).html("<img src='"+e.target.result+"' width='100%' height='100%' alt=''/>");
             $xuanZe.html("已选择");
             jQuery(shangChuanSelector).val("开始上传");
         };
    }
	
}

/**
 * 文件上传
 * files:jQuery(this).find([type=file])[0].files
 * url:${ createLink(controller:"x520", action:"touXiangShangChuan") }
 * shangChuanSelector:上传按钮
 * <g:uploadForm useToken="true" controller="x520" action="touXiangShangChuan" onsubmit="touXiangShangChuan(jQuery(this).find([type=file])[0].files, '${ createLink(controller:"x520", action:"touXiangShangChuan") }', '#kaiShiShangChuan', jQuery(this).serialize());return false">
 */
function wenJianShangChuan(files, url, shangChuanSelector, params) {

	$shangChuan = jQuery(shangChuanSelector);//上传按钮

	if (files.length < 1) {
		$shangChuan.val("( ↑ _ ↑ )");
		return false;
	}
	
	try {//修复谷歌浏览器sendAsBinary()异常
	  if (typeof XMLHttpRequest.prototype.sendAsBinary == 'undefined') {
	    XMLHttpRequest.prototype.sendAsBinary = function(text){
	      var data = new ArrayBuffer(text.length);
	      var ui8a = new Uint8Array(data, 0);
	      for (var i = 0; i < text.length; i++) ui8a[i] = (text.charCodeAt(i) & 0xff);
	      this.send(ui8a);
	    }
	  }
	} catch (e) {}
	
	for(var i = 0; i < files.length; i++) {
        var file = files[i];//当前文件
        var reader = new FileReader();

        reader.onloadstart = function() {
			console.log("读取开始");
			$shangChuan.val("读取开始");
		}
		
		reader.onprogress = function(p) {
			var loaded = p.loaded;
			var total = p.total;
			var baiFenBi = loaded / total * 100;
			console.log("读取进行中:" + baiFenBi + "%");
			$shangChuan.val("读取进行中:" + baiFenBi + "%");
		}
		
		reader.onload = function() {
			console.log("读取完成"); 
			$shangChuan.val("读取完成");
		}
		
		reader.onloadend = function() {
			if (reader.error) {//失败
				console.log(reader.error);
				$shangChuan.val("上传失败");
			} else {//成功
				var xhr = new XMLHttpRequest();
				xhr.open("POST", url + "?fileName=" + encodeURIComponent(file.name) + "&" + params);
          		xhr.overrideMimeType("application/octet-stream");
				xhr.sendAsBinary(this.result);
				
				xhr.onreadystatechange = function() {
					if (xhr.readyState == 4) {
						if (xhr.status == 200) {
							console.log("上传成功");
							//console.log("response: " + xhr.responseText);
							$shangChuan.val("上传成功");
							window.location.reload();
						}
					}
				}
			}
		}
		reader.readAsBinaryString(file);
    }
}

/**
 * 成功处理
 * @param data
 * @param textStatus
 * @param selector 用于更新成功信息
 */
function success(data,textStatus,selector) {
	switch(textStatus) {
		case "nocontent":
			textStatus = "删除成功";
			break;
		case "success":
			textStatus = "操作成功";
			break;
	}
	jQuery(selector).attr('class', 'alert alert-success').html(textStatus);
}

/**
 * 失败处理
 * @param XMLHttpRequest
 * @param textStatus
 * @param errorThrown
 * @param selector 用于更新失败信息
 */
function error(XMLHttpRequest,textStatus,errorThrown,selector) {
	var responseText = XMLHttpRequest.responseText;
	if(!responseText) {
		switch(errorThrown) {
			case "Not Found":
				responseText = "资源未找到";
				break;
			case "Not Acceptable":
				responseText = "验证未通过";
				break;
			case "Conflict":
				responseText = "已存在";
				break;
			case "Unauthorized":
				responseText = "未授权";
				break;
			case "Bad Request":
				responseText = "请求不合法";
				break;
			case "Unprocessable Entity":
				responseText = "请求未接受";
				break;
		}
	}
	jQuery(selector).attr('class', 'alert alert-warning').html(responseText);
}

function doSuccess(text) {
	Messenger().post(text);
	window.location.reload();
}

function doError(text) {
	Messenger().post({
		message: text,
		hideAfter: 3,
		showCloseButton: true
	});
}

/**
 * 删除表单
 */
jQuery(".deleteForm").ajaxForm({
	success:function(data,textStatus){
		doSuccess("删除成功");
	},
	error:function(XMLHttpRequest,textStatus,errorThrown){
		doError("删除失败");
	}
});

/**
 * 保存表单
 */
jQuery(".saveForm").ajaxForm({
	success:function(data,textStatus){
		doSuccess("保存成功");
	},
	error:function(XMLHttpRequest,textStatus,errorThrown){
		doError("保存失败");
	}
});

/**
 * 修改表单
 */
jQuery(".updateForm").ajaxForm({
	success:function(data,textStatus){
		doSuccess("修改成功");
	},
	error:function(XMLHttpRequest,textStatus,errorThrown){
		doError("修改失败");
	}
});

/**
 * 提交表单
 */
jQuery(".submitForm").ajaxForm({
	success:function(data,textStatus){
		doSuccess("操作成功");
	},
	error:function(XMLHttpRequest,textStatus,errorThrown){
		doError(XMLHttpRequest.responseText);
	}
});

jQuery(document).ready(function(){
	$('.input-group').on('focus', '.form-control', function () {
		$(this).closest('.input-group, .form-group').addClass('focus');
	}).on('blur', '.form-control', function () {
		$(this).closest('.input-group, .form-group').removeClass('focus');
	});
	$(document).on('click', 'a[href="#fakelink"]', function (e) {
		e.preventDefault();
	});

	jQuery(document).tooltip({
		content: function () {
			return jQuery(this).attr('title');
		}
	});

	NProgress.configure({
		showSpinner: false
	});
	NProgress.start();
});

jQuery(window).load(function(){
	NProgress.done();
});