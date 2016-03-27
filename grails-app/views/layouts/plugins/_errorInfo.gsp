<%@ page import="lvchanglong.BkColor" %>
<%@ page import="lvchanglong.GuShi" %>
<%@ page import="lvchanglong.JianJie" %>

<div class="row">
	<div class="col-md-4">
		<ul class="listBox" style="background-color:${BkColor.getInst()};">
			<li>Error</li>
			<li>${message}</li>
		</ul>
	</div>
	<div class="col-md-8">
		<ul class="listBox" style="background-color:${BkColor.getInst()};">
			<li>
				我叫<strong style="font-size:26px;">${ session.uname }</strong>，${ JianJie.getInst() }
			</li>
			<li style="margin-top:10px;">
				${ GuShi.getInst() }
			</li>
		</ul>
	</div>
	<div class="col-md-12 autoHide mt15">
		<g:render template="/layouts/plugins/3D" />
	</div>
</div>