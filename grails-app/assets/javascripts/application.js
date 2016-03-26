// This is a manifest file that'll be compiled into application.js.
//
// Any JavaScript file within this directory can be referenced here using a relative path.
//
// You're free to add application-wide JavaScript to this file, but it's generally better
// to create separate JavaScript files as needed.
//
//= encoding UTF-8
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
 * 连续动画
 * id:canvas id（更新画布）
 * imageSrc:image的src
 * w:单个图片宽度
 * h:单个图片高度
 * maxColumn:整个图片所占列数
 * formRow:起始行
 * toRow:终止行
 * ms:延迟执行时间
 * bkColor:背景色
 */
function Motion(id, imageSrc, w, h, maxColumn, fromRow, toRow, ms, callFun, callIdx, bkColor) {

	//载入图像
	this.loadImage = function() {
		var inst = this;
		inst.image.onload = function() {
			inst.go();
		}
	}

	//动作执行
	this.go = function() {
		var inst = this; //实例别名
		setTimeout(function(){
			
			var x = inst.x;
			var y = inst.y;
			var w = inst.w;
			var h = inst.h;
			
			var maxSize = inst.maxSize;
			var index = inst.index;
			var maxColumn = inst.maxColumn;
			
			var ctx = inst.ctx;
			var image = inst.image;
			
			if (inst.bkColor) {
				ctx.fillStyle=inst.bkColor;
			} else {
				ctx.fillStyle='transparent';
			}
			
			ctx.fillRect(0, 0, w, h);
			ctx.drawImage(image, x, y, w, h, 0, 0, w, h);

			//数据更新
			inst.nIndex++;
			
			if (callIdx == inst.nIndex % maxColumn) {
				if (callFun) {
					callFun();
				}
				//console.log(inst.nIndex);
			}
			
			if (inst.nIndex >= maxSize) {
				inst.nIndex = index;
			}

			inst.x = (inst.nIndex % maxColumn) * w;
			inst.y = Math.floor(inst.nIndex / maxColumn) * h;
			
			inst.go();//再次执行
			
		}, inst.ms);
	}
	
	var canvas = jQuery("#" + id)[0];//画布
	canvas.width = w;
    canvas.height = h;
    
    this.ctx = canvas.getContext("2d");//上下文
	
	this.image = new Image();//图片
	this.image.src = imageSrc;
	
	this.w = w;//图像宽度
	this.h = h;//图像高度
	this.maxColumn = maxColumn;//最大列数
	
	this.index = (fromRow - 1) * maxColumn;//初始索引
	this.nIndex = this.index;//自增索引(下限[index]->上限[maxSize])

	this.maxSize = this.index + maxColumn * (toRow - fromRow + 1);//最大容量
	
	this.x = (this.nIndex % maxColumn) * w;//当前起始x坐标
	this.y = Math.floor(this.nIndex / maxColumn) * h;//当前起始y坐标

	this.ms = ms;
	this.callIdx = callIdx;
	this.bkColor = bkColor;
	this.loadImage();
}

/**
 * 说点什么
 * dialogID:话框
 * array:话列表
 */
function Talk(dialogID, array) {
	this.dialogID = dialogID;
	this.array = array;
	this.arrayLength = this.array.length;
	
	this.go = function() {
		var wt = this.array[Math.floor(Math.random() * this.arrayLength)];
		$dialog = jQuery("#" + this.dialogID);
		if ($dialog.is(":hidden")) {
			$dialog.html(wt).show();
		} else {
			$dialog.hide();
		}
	}
}

/**
 * 点击显示
 * @param wrapperClass
 * @param showID
 */
function clickToHover(wrapperClass, showID) {
	var $showElement = jQuery(showID);
	if($showElement.is(":visible")) {//已显示
		$showElement.hide();
	} else {//已隐藏
		jQuery(wrapperClass).hide();
		$showElement.show();
	}
}

/**
 * 隐藏所有
 * @param wrapperClass
 */
function hideAll(wrapperClass) {
	jQuery(wrapperClass).hide();
}

/**
 * 浮动响应
 * wrapperClass:包装类（触发器）
 * hideElement:需要隐藏的元素(处理元素)
 */
function responseToHover(wrapperClass, hideID) {
	var timer = null;//延时器
	jQuery(wrapperClass).hover(
		function () {
			jQuery(hideID).show();
			clearTimeout(timer);
		},
		function () {
			timer = setTimeout(function() {//延时执行
				jQuery(hideID).hide();
			}, 50);
		}
	);
}

/**
 * 远程分页
 */
function paginate(wrapperSelector, loadSelector) {
	jQuery(document).on("click", ".pagination a", function() {
		jQuery(wrapperSelector).load(this.href + " " + loadSelector);
		window.history.pushState({}, "", this.href);
		return false;
	});
}

/**
 * 刷新确认框
 * @param htmlTitle 温馨提示
 * @param htmlContent 操作已结束？！！！
 * @param callFunction
 */
function reloadConfirm(htmlTitle, htmlContent, callFunction) {
	if(!htmlTitle) {
		htmlTitle = '温馨提示';
	}
	if(!htmlContent) {
		htmlContent = '操作已结束？！！！';
	}
	if(!callFunction) {
		callFunction = function() {
			window.location.reload();
		}
	}
	
	jQuery('<div class="reloadWrapper">' + htmlContent + '</div>').dialog({
		title:htmlTitle,
		modal:true,
		buttons: {
	    	"确定": function() {
	    		jQuery(this).dialog('close');
	    		callFunction();
	      	}
	    }
	});
}

/**
 * 删除表单（约定）
 */
jQuery(".deleteForm").ajaxForm({
	success:function(){
		window.location.reload();
	}
});