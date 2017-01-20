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