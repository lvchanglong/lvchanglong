<%@ page import="lvchanglong.ChineseName; lvchanglong.User; lvchanglong.Element; lvchanglong.BkColor" %>

<div class="container-fluid stickup-wrapper" style="width:100%;z-index:9999;">
	<div class="row" style="margin-top: 5px;">
		<div class="col-md-12">
			<nav class="navbar navbar-inverse navbar-embossed" role="navigation">
				<div class="navbar-header">
					<button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#navbar-collapse-header">
						<span class="sr-only">Toggle navigation</span>
					</button>
					<g:link uri="/" class="navbar-brand">lvchanglong.com</g:link>
				</div>
				<div class="collapse navbar-collapse" id="navbar-collapse-header">
					<ul class="nav navbar-nav navbar-left">
						<li>
							<g:link controller="public" action="videos" target="_self">
								<span class="glyphicon glyphicon-film"></span>
								视频
							</g:link>
						</li>
						<li>
							<g:link controller="public" action="links" target="_self">
								<span class="glyphicon glyphicon-tag"></span>
								链接
							</g:link>
						</li>
						<li>
							<g:link controller="public" action="texts" target="_self">
								<span class="glyphicon glyphicon-file"></span>
								文本
							</g:link>
						</li>
						<li>
							<g:form controller="public" action="${params.action}" role="search" class="navbar-form" method="GET">
								<div class="form-group">
									<div class="input-group">
										<input class="form-control" id="navbarInput" type="search" placeholder="Search" name="q" value="${params.q}">
										<span class="input-group-btn">
											<button type="submit" class="btn"><span class="fui-search"></span></button>
										</span>
									</div>
								</div>
							</g:form>
						</li>
					</ul>
					<ul class="nav navbar-nav navbar-right">
						<g:if test="${session.uid}">
							<li class="dropdown">
								<a href="#" class="dropdown-toggle" data-toggle="dropdown">
									${User.get(session.uid)}&nbsp;<b class="caret"></b>
								</a>
								<span class="dropdown-arrow"></span>
								<ul class="dropdown-menu">
									%{--<li class="divider"></li>--}%
									<li>
										<a href="#" onclick="jQuery.post('${ createLink(controller:'public', action:"logout") }', function(){window.location.reload();});" class="link">退出</a>
									</li>
								</ul>
							</li>
						</g:if>
						<g:else>
							<li>
								<a href="#" data-toggle="modal" data-target="#hoverDengLu" class="link"><strong class="uname"><i class="fa fa-user"></i>${ ChineseName.getInst() }</strong></a>
								<div class="modal fade" id="hoverDengLu" tabindex="-1" role="dialog" aria-labelledby="hoverDengLuTitle" aria-hidden="true">
									<div class="modal-dialog">
										<div class="modal-content">
											<div class="modal-header">
												<button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
												<h3 class="modal-title text-left" id="hoverDengLuTitle">举世皆浊我独清，众人皆醉我独醒&nbsp;&nbsp;&nbsp;——&nbsp;屈原</h3>
											</div>
											<div class="modal-body">
												<g:form name="login-form" url="[controller:'public', action:'login']" class="submitForm">
													<g:textField name="zhangHao" value="" required="" autofocus="" class="form-control" placeholder="账号"/>

													<g:submitButton name="submit" value="登录" class="btn btn-lg btn-primary btn-block"/>
												</g:form>
											</div>
										</div>
									</div>
								</div>
							</li>
						</g:else>
					</ul>
				</div><!-- /.navbar-collapse -->
			</nav><!-- /navbar -->
		</div>
	</div>
</div>