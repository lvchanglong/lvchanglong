<div id="kaTong" class="relative" style="width:230px;">
	<canvas id="kaTong-canvas">
			不支持 Canvas
	</canvas>
	
	<asset:image src="SuCai/白云.png" style="float:right;" alt="白云"/>

	<div id="kaTong-dialog" style="float:right;font-weight:bold;">

	</div>
	<g:javascript>
		var shortTalk = new Talk("kaTong-dialog", ["哇哇", "哈哈", "呀呀", "我打", "看招", "嗯嗯", "不错", "嘿嘿", "暴击", "连击", "觉醒"]);
		new Motion("kaTong-canvas", "${ assetPath(src:"SuCai/功夫.png") }", 100, 100, 10, 1, 4, 350, function() {
			shortTalk.go();
		}, 3, "#fff");
	</g:javascript>
</div>

