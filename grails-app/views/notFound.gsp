<%@ page import="lvchanglong.BkColor" %>
<%@ page import="lvchanglong.GuShi" %>
<!doctype html>
<html>
    <head>
        <title>Page Not Found</title>
        <meta name="layout" content="main">
        <g:if env="development"><asset:stylesheet src="errors.css"/></g:if>
    </head>
    <body>
    	<div class="row">
			<div class="col-md-4">
				<ul class="listBox" style="background-color:${BkColor.getInst()};">
		            <li>Error: Page Not Found (404)</li>
		            <li>Path: ${request.forwardURI}</li>
		        </ul>
			</div>
			<div class="col-md-8">
				<ul class="listBox" style="background-color:${BkColor.getInst()};">
					<li>
						我叫<strong style="font-size:26px;">${ session.uname }</strong>，${ session.uinfo }
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
    </body>
</html>
