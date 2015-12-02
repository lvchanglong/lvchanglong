<%@ page import="lvchanglong.YongHu" %>
<%@ page import="lvchanglong.ShiTi" %>
<%@ page import="lvchanglong.BkColor" %>

<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main"/>
		<g:set var="dangQianYongHu" value="${ YongHu.get(session.uid) }" />
	</head>
	<body>
			<div class="row">
			
				<g:if test="${shiTiList}">
					<g:each in="${shiTiList}" status="j" var="shiTi">
						<g:if test="${ shiTi }">
							<div class="col-md-4">
								<div class="listBox" style="background-color:${BkColor.getInst()};">
									<g:if test="${ dangQianYongHu?.shiFouGuanLiYuan() }">
										<g:link controller="protected" action="editShiTi" id="${ shiTi.id }" target="_blank" onclick="reloadConfirm();" class="link">
											<span class="glyphicon glyphicon-edit"></span>
										</g:link>
									</g:if>
									
									<g:link controller="public" action="showShiTi" id="${ shiTi.id }" target="_blank" class="link listHeader">
										${ shiTi.biaoTi }
									</g:link>
									
									<span style="margin-left:5px;font-size:18px;color:lightgray;">
										${ shiTi.id }
									</span>
								
									<div class="content relative clearfix">
										<div class="row">
											<div class="col-md-12 col-xs-12">
												${ shiTi.neiRong }
											</div>
										</div>
									</div>
									
									<div class="text-text lineHeight30 block">
										<span class="yongHu">
											${shiTi.getYongHuAsString()}
										</span>
										<span class="shortSeparator">|</span>
										<span class="dateCreated">
											<g:formatDate date="${shiTi.dateCreated}" format="yyyy年MM月dd日" />
										</span>
										<span class="shortSeparator">|</span>
										<g:render template="/layouts/plugins/zhuanZai"/>
									</div>
								</div>
							</div>
						</g:if>
					</g:each>
					<div class="pagination" style="clear:both;">
						<g:paginate total="${shiTiCount}"/>
					</div>
				</g:if>
				
				<div class="col-md-12">
					<hr/>
				</div>
				
				<div class="col-md-4">
					<div class="listBox" style="background-color:#FF9BAB;">
						<div style="padding-top:0;">
							<g:img uri="${ createLink(controller:'public', action:'loadTouXiang', id:session.uid) }" width="180px" height="180px" alt="头像" class="img-rounded toCenter"/>
						</div>
					</div>
				</div>
				<div class="col-md-4">
					<div class="listBox" style="background-color:darkgreen;">
						<div style="padding:0 30px 0 30px;font-weight:bold;font-size:20px;line-height:26px;color:#fff;">
							我叫<strong style="font-size:26px;">${ session.uname }</strong>，${ session.uinfo }
						</div>
					</div>
				</div>
				<div class="col-md-4">
					<div class="listBox" style="background-color:#9790F3;">
						<div style="padding-top:0;">
							<g:link controller="public" action="woDeChuanShuo" target="_self" class="toCenter" style="font-size:24px;">
								<span class="glyphicon glyphicon-chevron-right"></span>奇怪的地方
							</g:link>
						</div>
					</div>
				</div>
				
			</div>
	</body>
</html>