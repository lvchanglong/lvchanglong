<asset:stylesheet src="HuanDengPian/stylesheets/slider-min.css"/>
<asset:javascript src="HuanDengPian/javascripts/jquery.slider.pack-min.js"/>
<asset:javascript src="HuanDengPian/javascripts/jquery.easing-min.js"/>

<div id="huanDengPian">
    <div class="cycleslider-wrap">
       
       <div id="slider" class="cycleslider" style="position:relative; width:${width}; height:${ height };margin:0 auto;">
	       	 <g:each in="${instanceList}" status="i" var="instance">
				  <div class="cycle-slider">
	             	<a href="${ instance.href }" target="_blank">
	   					<img src="${ instance.url }" width="${width}" height="${ height }" alt="${ instance.alt }" title="${ instance.alt }">
	             	</a>
	              </div>
			 </g:each>
        </div>
        
        <a id="cycle-prev" href="#">Prev</a>
        <a id="cycle-next" href="#">Next</a>
        <div id="cycle-nav"></div>
        
    </div>
    
    <script type="text/javascript">
		jQuery(function() {
			jQuery('#cycle-prev, #cycle-next').css({opacity: '0'});
			
			jQuery('.cycleslider-wrap').hover(function(){
				jQuery('#cycle-prev',this).stop().animate({left: '-31', opacity: '1'},200,'easeOutCubic');
				jQuery('#cycle-next',this).stop().animate({right: '-31', opacity: '1'},200,'easeOutCubic');	 
			}, function() {
				jQuery('#cycle-prev',this).stop().animate({left: '-50', opacity: '0'},400,'easeInCubic');
				jQuery('#cycle-next',this).stop().animate({right: '-50', opacity: '0'},400,'easeInCubic');		
			});
			
			jQuery(".cycleslider-wrap").fadeIn(1000);
			
			jQuery("#slider").cycle({
				fx:  "scrollDown",
				speed:  1000, 
				timeout: 5000,
				easing:  "easeOutBounce",
				next:  "#cycle-next",
				prev:  "#cycle-prev",
				pager:  "#cycle-nav",
				pause:  1,
				pauseOnPagerHover: true
			});
		});
	</script>
</div>

