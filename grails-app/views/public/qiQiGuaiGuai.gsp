<%@ page import="lvchanglong.YongHu" %>
<%@ page import="lvchanglong.ShiTi" %>
<%@ page import="lvchanglong.BkColor" %>
<%@ page import="lvchanglong.GuShi" %>

<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main"/>
		<title>奇奇怪怪${ grailsApplication.config.html.title.suffix }</title>
	</head>
	<body>
		<div class="row" style="font-size:20px;font-weight:bold;line-height:34px;color:#fff;">
			<div class="col-md-12 autoHide">
				<g:render template="/layouts/plugins/3D" />
			</div>
			<div class="col-md-12">
				<ul class="listBox" style="background-color:${BkColor.getInst()};">
					<li>
						我叫<strong style="font-size:26px;">${ session.uname }</strong>，${ session.uinfo }
					</li>
					<li style="margin-top:10px;">
						${ GuShi.getInst() }
					</li>
				</ul>
			</div>
			<div class="col-md-3">
            	<div class="listBox" style="background-color:${BkColor.getInst()};">
				</div>
            </div>
            <div class="col-md-6">
            	<div class="listBox" style="background-color:${BkColor.getInst()};">
				</div>
            </div>
            <div class="col-md-3">
            	<div class="listBox" style="background-color:${BkColor.getInst()};">
				</div>
            </div>
            <div class="col-md-3">
            	<div class="listBox" style="background-color:${BkColor.getInst()};">
				</div>
            </div>
            <div class="col-md-9">
            	<div class="listBox" style="background-color:${BkColor.getInst()};">
				</div>
            </div>
            <div class="col-md-12">
            	<div class="listBox" style="background-color:${BkColor.getInst()};">
				</div>
            </div>
		</div>
	</body>
</html>