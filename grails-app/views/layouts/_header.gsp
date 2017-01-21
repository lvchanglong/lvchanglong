<%@ page import="lvchanglong.User; lvchanglong.ChineseName" %>

<g:set var="cUser" value="${ User.get(session.uid) }" />

<div class="topLines">
	<div class="line3"></div>
	<div class="line4"></div>
</div>

<div class="container header">
	<div class="row" style="border-bottom: 3px solid #000000;">
		<div class="col-md-4">
			<ul class="hNav text-left">
				<li>
					<g:link controller="public" action="videos" target="_black">
						<span class="glyphicon glyphicon-film"></span>
						视频
					</g:link>
				</li>
				<li>
					<g:link controller="public" action="links" target="_black">
						<span class="glyphicon glyphicon-tag"></span>
						链接
					</g:link>
				</li>
				<li>
					<g:link controller="public" action="texts" target="_black">
						<span class="glyphicon glyphicon-file"></span>
						文本
					</g:link>
				</li>
				<g:if test="${ cUser }">
					<li>
						<a href="#" data-toggle="modal" data-target="#hoverZhuCe" class="link"><span class="glyphicon glyphicon-flash" aria-hidden="true"></span>&nbsp;注册</a>
						<div class="modal fade" id="hoverZhuCe" tabindex="-1" role="dialog" aria-labelledby="hoverZhuCeTitle" aria-hidden="true">
							<div class="modal-dialog">
								<div class="modal-content">
									<div class="modal-header">
										<button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
										<h3 class="modal-title text-left" id="hoverZhuCeTitle">十年生死两茫茫，不思量，自难忘&nbsp;&nbsp;&nbsp;——&nbsp;苏轼</h3>
									</div>
									<div class="modal-body">
										<g:form name="register-form" url="[controller:'public', action:'register']" class="submitForm">
											<g:textField name="zhangHao" value="" required="" autofocus="" class="form-control" placeholder="账号"/>
											<g:textField name="xingMing" value="" class="form-control" placeholder="姓名"/>

											<g:submitButton name="submit" value="注册" class="btn btn-lg btn-primary btn-block"/>
										</g:form>
									</div>
								</div>
							</div>
						</div>
					</li>
					<li>
						<g:link controller="protected" action="elements"><span class="glyphicon glyphicon-screenshot" aria-hidden="true"></span>&nbsp;元素</g:link>
					</li>
				</g:if>
				<g:if test="${ cUser && cUser.isAdmin() }">
					<li>
						<g:link controller="public" action="users" class="link"><span class="glyphicon glyphicon-fire" aria-hidden="true"></span>&nbsp;用户</g:link>
					</li>
					<li>
						<g:link controller="public" action="feedbacks" class="link"><span class="glyphicon glyphicon-send" aria-hidden="true"></span>&nbsp;反馈</g:link>
					</li>
				</g:if>
			</ul>
		</div>
		<div class="col-md-4" style="text-align: center;">
			<g:link uri="/">
				<asset:image src="WenZi/吕常龙.png" class="toCenter logo" style="height:130px;"/>
			</g:link>
		</div>
		<div class="col-md-4">
			<ul class="hNav text-right">
				<g:if test="${ cUser }">
					<li>
						<a href="#" data-toggle="modal" data-target="#hoverGaiXinXi" class="link"><strong class="uname"><i class="fa fa-user"></i>${ cUser.xingMing }</strong></a>
						<div class="modal fade text-left" id="hoverGaiXinXi" tabindex="-1" role="dialog" aria-labelledby="hoverGaiXinXiTitle" aria-hidden="true">
							<div class="modal-dialog">
								<div class="modal-content">
									<div class="modal-header">
										<button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
						       	 		<h3 class="modal-title text-left" id="hoverGaiXinXiTitle">生当作人杰，死亦为鬼雄&nbsp;&nbsp;&nbsp;——&nbsp;李清照</h3>
									</div>
									<div class="modal-body">
										<div class="row">
											<div class="col-md-6" style="border-right: 1px solid lightgray;">
												<g:uploadForm useToken="true" controller="protected" action="uploadTouXiang" onsubmit="wenJianShangChuan(jQuery(this).find([type=file])[0].files, '${ createLink(controller:"protected", action:"uploadTouXiang") }', '#kaiShiShangChuan', jQuery(this).serialize());return false" style="width:180px;margin:0 auto;">
													<div id="touXiangWrapper" style="text-align:center;margin-bottom:15px;margin-top:5px;">
														<g:img uri="${ createLink(controller:'public', action:'loadTouXiang', id:cUser.id) }" width="180px" height="180px" alt="头像" class="img-thumbnail"/>
													</div>
													<div class="xuanZeWenJian">
														<div id="tuPianXuanZe" class="btn btn-primary btn-block">
															选择图片
														</div>
														<input type="file" name="file" onchange="tuPianChaKan(this.files, '#touXiangWrapper', '#tuPianXuanZe', '#kaiShiShangChuan');" multiple="false" class="btn btn-primary btn-block up"/>
													</div>
													<g:submitButton id="kaiShiShangChuan" name="shangChuan" value="开始上传" class="btn btn-default btn-block form-control" style="margin-top:10px;"/>
												</g:uploadForm>
											</div>
											<div class="col-md-6">
												<g:form name="xinXiXiuGai" url="[controller:'protected', action:'updateUser']" method="PUT" class="submitForm" style="width:180px;margin:0 auto;">
													<g:textField name="zhangHao" value="${cUser.zhangHao}" required="" autofocus="" class="form-control" placeholder="账号"/>
													<g:textField name="xingMing" value="${cUser.xingMing}" class="form-control" placeholder="姓名"/>

													<g:submitButton name="submit" value="修改" class="btn btn-lg btn-primary btn-block"/>
												</g:form>
											</div>
										</div>
							      	</div>
								</div>
							</div>
						</div>
					</li>
					<li>
						<a href="#" onclick="jQuery.post('${ createLink(controller:'public', action:"logout") }', function(){window.location.reload();});">退出</a>
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
			
			<div class="share">
				<a class="weibo" title="新浪微博" href="http://weibo.com/234008728" target="_blank"></a>
			</div>
			
			<div class="relative text-right pull-right" style="font-size:14px;font-weight:normal;">
				<div style="padding:5px 0 5px 0;">
					<div class="row">
						<div class="col-md-11 col-md-offset-1">
							<g:form name="gongGongJianSuo" url="[controller:'public', action:'index']" method="GET">
						    	<div class="input-group">
						      		<input type="text" name="text" class="form-control" placeholder="请输入关键词（标题 | 内容）" value="${ params.text }">
						      		<span class="input-group-btn">
						        		<button class="btn btn-primary" type="submit">检索</button>
						      		</span>
						    	</div>
					    	</g:form>
					  	</div>
					</div><!-- /.row -->
				</div>
			</div>
		</div>
	</div>
</div>