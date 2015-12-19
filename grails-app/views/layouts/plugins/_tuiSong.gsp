<span class="inlineBlock">
	<span id="kongjian-wrapper-h1">信息推送</span>
	<g:javascript>
		var source = new EventSource("${ createLink(controller:'public', action:"jinQiGongGao") }");
		source.onmessage = function(event) {
			jQuery("#kongjian-wrapper-h1").html(event.data);//近期公告
		};
	</g:javascript>
</span>

