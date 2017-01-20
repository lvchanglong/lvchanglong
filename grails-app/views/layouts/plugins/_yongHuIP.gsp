<div id="yongHuIP">
	<ul>
		<li>
			您的ＩＰ：${ request.getRemoteAddr() }
		</li>
		<li id="yongHuIP-ip138">
			
		</li>
	</ul>
	
	<g:javascript>
		jQuery.post("${ createLink(controller:'share', action:'ipXiangQing') }", {ip: '${ request.getRemoteAddr() }'}, function(data,textStatus){
			if(data && data != 'null'){
				jQuery('#yongHuIP-ip138').html(data);
			} else {
				jQuery('#yongHuIP-ip138').html('暂无数据');
			}
		});
	</g:javascript>
</div>