<asset:javascript src="SliderEvolution/jquery.slider.min.js"/>
<asset:stylesheet src="SliderEvolution/jquery.slider.min.css"/>

<div id="sliderEvolution" class="slider">
	<g:each in="${instanceList}" status="i" var="instance">
		<div>
	 		<a href="${ instance.href }" target="_blank">
				<img src="${ instance.url }" width="${width}" height="${ height }" alt="${ instance.alt }">
	        </a>
	        <div class="caption">
		    	${ instance.alt }
		    </div>
	    </div>
	</g:each>
</div>

<script type="text/javascript">
	jQuery171("#sliderEvolution").slideshow({
	    width      : '${width}',
	  	height     : '${height}',
	  	transition : 'bar'
	});
</script>
