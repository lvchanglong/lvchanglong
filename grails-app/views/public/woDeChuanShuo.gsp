<%@ page import="lvchanglong.YongHu" %>
<%@ page import="lvchanglong.ShiTi" %>
<%@ page import="lvchanglong.BkColor" %>
<%@ page import="lvchanglong.GuShi" %>

<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main"/>
		<title>我的传说${ grailsApplication.config.html.title.suffix }</title>
	</head>
	<body>
		<div class="row" style="font-size:20px;font-weight:bold;line-height:34px;color:#fff;">
			<div class="col-md-12 autoHide">
				<g:render template="/layouts/plugins/3D" />
				<hr/>
			</div>
			
			<div class="col-md-4">
				<div style="padding-top:40px;background-color:#2489C5;min-height:500px;">
					<g:img uri="${ createLink(controller:'public', action:'loadTouXiang', id:session.uid) }" width="180px" height="180px" alt="头像" class="img-rounded toCenter"/>
				</div>
			</div>
			<div class="col-md-8">
				<ul style="padding:30px 35px;background-color:#2c313a;min-height:500px;">
					<li>
						我叫<strong style="font-size:26px;">${ session.uname }</strong>，${ session.uinfo }
					</li>
					<li style="margin-top:10px;">
						${ GuShi.getInst() }
					</li>
				</ul>
			</div>
		</div>
	</body>
</html>