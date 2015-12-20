<%@ page import="lvchanglong.BkColor" %>
<%@ page import="lvchanglong.GuShi" %>
<!doctype html>
<html>
    <head>
        <title><g:if env="development">Grails Runtime Exception</g:if><g:else>Error</g:else></title>
        <meta name="layout" content="main">
        <g:if env="development"><asset:stylesheet src="errors.css"/></g:if>
    </head>
    <body>
        <g:if env="development">
            <g:if test="${Throwable.isInstance(exception)}">
                <g:renderException exception="${exception}" />
            </g:if>
            <g:elseif test="${request.getAttribute('javax.servlet.error.exception')}">
                <g:renderException exception="${request.getAttribute('javax.servlet.error.exception')}" />
            </g:elseif>
            <g:else>
                <ul class="errors">
                    <li>An error has occurred</li>
                    <li>Exception: ${exception}</li>
                    <li>Message: ${message}</li>
                    <li>Path: ${path}</li>
                </ul>
            </g:else>
        </g:if>
        <g:else>
            <div class="row">
				<div class="col-md-12 autoHide">
					<g:render template="/layouts/plugins/3D" />
				</div>
				<div class="col-md-4">
					<ul class="listBox" style="background-color:${BkColor.getInst()};">
                		<li>An error has occurred</li>
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
			</div>
        </g:else>
    </body>
</html>
