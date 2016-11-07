<%@ page import="lvchanglong.YongHu" %>
<%@ page import="lvchanglong.ChineseName" %>

<g:set var="dangQianYongHu" value="${ YongHu.get(session.uid) }" />
<g:set var="autoName" value="${ ChineseName.getInst() }" />

<div class="topLines">
	<div class="line1"></div>
	<div class="line2"></div>
</div>

<div class="container header">
	<div class="row" style="border-bottom: 3px solid #000000;">
		<div class="col-md-4">
			<ul class="hNav text-left">
				<li>
					<span class="glyphicon glyphicon-tree-conifer" aria-hidden="true"></span>&nbsp;<g:link controller="public" action="index" params="[leiBie:'默认']" class="link" style="font-size:20px;line-height:30px;" >默认</g:link>
				</li>
				<li>
					<span class="glyphicon glyphicon-book" aria-hidden="true"></span>&nbsp;<g:link controller="public" action="index" params="[leiBie:'书籍']" class="link" style="font-size:20px;line-height:30px;" >书籍</g:link>
				</li>
				<li>
					<span class="glyphicon glyphicon-info-sign" aria-hidden="true"></span>&nbsp;<g:link controller="public" action="index" params="[leiBie:'情报']" class="link" style="font-size:20px;line-height:30px;" >情报</g:link>
				</li>
			</ul>
		</div>
		<div class="col-md-4">
			<g:link uri="/">
				<asset:image src="WenZi/吕常龙.png" class="toCenter logo" style="height:130px;"/>
			</g:link>
		</div>
		<div class="col-md-4">
			<ul class="hNav text-right">
				<g:if test="${ dangQianYongHu }">
					<li>
						<a href="#" data-toggle="modal" data-target="#hoverGaiXinXi" class="link"><strong class="uname"><i class="fa fa-user"></i>${ dangQianYongHu.xingMing }</strong></a>	
						<div class="modal fade text-left" id="hoverGaiXinXi" tabindex="-1" role="dialog" aria-labelledby="hoverGaiXinXiTitle">
							<div class="modal-dialog" role="document" aria-hidden="true">
								<div class="modal-content">
									<div class="modal-header">
										<button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
						       	 		<h4 class="modal-title text-left" id="hoverGaiXinXiTitle">生当作人杰，死亦为鬼雄&nbsp;&nbsp;&nbsp;——&nbsp;李清照</h4>
									</div>
									<div class="modal-body paddingWrapper">
										<div class="row">
											<div class="col-md-6">
												<g:form name="miMaXiuGai" url="[controller:'protected', action:'miMaXiuGai']" method="PUT">
										        	<div id="miMaXiuGaiTiShi" class="alert alert-info" role="alert">
										        		<strong>原密、新密、确认</strong>
												    </div>
													
													<g:passwordField name="yuanMiMa" value="" required="" autofocus="" class="form-control" placeholder="原始密码"/>
									        		<g:passwordField name="xinMiMa" value="" class="form-control" placeholder="新的密码"/>
													<g:passwordField name="queRenMiMa" value="" class="form-control" placeholder="确认密码"/>
													
													<g:submitButton name="xiuGai" value="修改" class="btn btn-lg btn-primary btn-block"/>
											    </g:form>
												<g:javascript>
													jQuery("#miMaXiuGai").ajaxForm({
														success:function(data,textStatus){
															success(data,textStatus,'#miMaXiuGaiTiShi');
														}, 
														error:function(XMLHttpRequest,textStatus,errorThrown){
															error(XMLHttpRequest,textStatus,errorThrown,'#miMaXiuGaiTiShi');
														}
													});
												</g:javascript>
											</div>
											<div class="col-md-6">
												<g:uploadForm useToken="true" controller="protected" action="touXiangShangChuan" onsubmit="wenJianShangChuan(jQuery(this).find([type=file])[0].files, '${ createLink(controller:"protected", action:"touXiangShangChuan") }', '#kaiShiShangChuan', jQuery(this).serialize());return false" style="width:180px;margin:0 auto;">									
													<div id="touXiangWrapper" style="text-align:center;margin-bottom:15px;margin-top:5px;">
														<g:img uri="${ createLink(controller:'public', action:'loadTouXiang', id:dangQianYongHu?.id) }" width="180px" height="180px" alt="头像" class="img-thumbnail"/>
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
										</div>
							      	</div>
								</div>
							</div>
						</div>
					</li>
					<li>
						<a href="#" onclick="jQuery.post('${ createLink(controller:'public', action:"yongHuZhuXiao") }', function(){window.location.reload();});" class="link">退出</a>
					</li>
				</g:if>
				<g:else>
					<li>
						<a href="#" data-toggle="modal" data-target="#hoverDengLu" class="link"><strong class="uname"><i class="fa fa-user"></i>${ autoName }</strong></a>
						<div class="modal fade text-left" id="hoverDengLu" tabindex="-1" role="dialog" aria-labelledby="hoverDengLuTitle">
							<div class="modal-dialog" role="document" aria-hidden="true">
								<div class="modal-content">
									<div class="modal-header">
										<button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
						       	 		<h4 class="modal-title text-left" id="hoverDengLuTitle">举世皆浊我独清，众人皆醉我独醒&nbsp;&nbsp;&nbsp;——&nbsp;屈原</h4>
									</div>
									<div class="modal-body paddingWrapper">
							        	<g:form name="yongHuDengLu" url="[controller:'public', action:'yongHuDengLu']">
								        	<div id="dengLuTiShi" class="alert alert-info" role="alert">
								        		姓名、密码
										    </div>
									        
									        <label for="inputXingMing" class="sr-only">姓名</label>
									        <g:textField id="inputXingMing" name="xingMing" value="${ autoName }" required="" autofocus="" class="form-control" placeholder="姓名"/>
									        
									        <label for="inputPassword" class="sr-only">密码</label>
									        <g:passwordField id="inputPassword" name="miMa" value="" class="form-control" placeholder="密码"/>
									        
									        <g:submitButton name="dengLu" value="登录" class="btn btn-lg btn-primary btn-block"/>
									    </g:form>
									    <g:javascript>
											jQuery("#yongHuDengLu").ajaxForm({
												success:function(data,textStatus){
													success(data,textStatus,'#dengLuTiShi');
													window.location.reload();
												}, 
												error:function(XMLHttpRequest,textStatus,errorThrown){
													error(XMLHttpRequest,textStatus,errorThrown,'#dengLuTiShi');
												}
											});
										</g:javascript>
							      	</div>
								</div>
							</div>
						</div>
					</li>
					<li>
						<a href="#" data-toggle="modal" data-target="#hoverZhuCe" class="link">注册</a>
						<div class="modal fade text-left" id="hoverZhuCe" tabindex="-1" role="dialog" aria-labelledby="hoverZhuCeTitle">
							<div class="modal-dialog" role="document" aria-hidden="true">
								<div class="modal-content">
									<div class="modal-header">
										<button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
						       	 		<h4 class="modal-title text-left" id="hoverZhuCeTitle">十年生死两茫茫，不思量，自难忘&nbsp;&nbsp;&nbsp;——&nbsp;苏轼</h4>
									</div>
									<div class="modal-body paddingWrapper">
							        	<g:form name="yongHuZhuCe" url="[controller:'public', action:'yongHuZhuCe']">
								        	<div id="zhuCeTiShi" class="alert alert-info" role="alert">
								        		姓名、密码、确认
										    </div>
									        <g:textField name="xingMing" value="${ autoName }" required="" autofocus="" class="form-control" placeholder="姓名"/>
									        <g:passwordField name="miMa" value="" class="form-control" placeholder="密码"/>
											<g:passwordField name="queRenMiMa" value="" class="form-control" placeholder="确认"/>

									        <g:submitButton name="zhuCe" value="注册" class="btn btn-lg btn-primary btn-block"/>
									    </g:form>
									    <g:javascript>
											jQuery("#yongHuZhuCe").ajaxForm({
												success:function(data,textStatus){
													success(data,textStatus,'#zhuCeTiShi');
												}, 
												error:function(XMLHttpRequest,textStatus,errorThrown){
													error(XMLHttpRequest,textStatus,errorThrown,'#zhuCeTiShi');
												}
											});
										</g:javascript>
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
						<div class="col-md-10 col-md-offset-2">
							<g:form name="gongGongJianSuo" url="[controller:'public', action:'index']" method="GET">
								<g:hiddenField name="leiBie" value="${params.leiBie}"/>
						    	<div class="input-group">
						      		<input type="text" name="text" class="form-control" placeholder="请输入关键词（标题 | 内容）" value="${ params.text  }">
						      		<span class="input-group-btn">
						        		<button class="btn btn-primary" type="submit">检索</button>
						      		</span>
						    	</div>
					    	</g:form>
					  	</div>
					</div><!-- /.row -->
				</div>
			</div>
			
			<g:if test="${ dangQianYongHu }">
				<ul class="hNav text-right clearBoth">
					<li style="padding-right:0;">
						<g:link controller="protected" action="listShiTi" params="[leiBie: '默认']" class="btn btn-default">默认</g:link>
					</li>
					<li style="padding-right:0;">
						<g:link controller="protected" action="listShiTi" params="[leiBie: '书籍']" class="btn btn-default">书籍</g:link>
					</li>
					<li style="padding-right:0;">
						<g:link controller="protected" action="listShiTi" params="[leiBie: '情报']" class="btn btn-default">情报</g:link>
					</li>
					<g:if test="${ dangQianYongHu.isAdmin() }">
						<li style="padding-right:0;">
							<g:link controller="private" action="users" class="btn btn-default">用户</g:link>
						</li>
					</g:if>
				</ul>
			</g:if>
		</div>
	</div>
</div>