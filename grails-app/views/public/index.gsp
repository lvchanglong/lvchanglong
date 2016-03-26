<%@ page import="lvchanglong.YongHu" %>
<%@ page import="lvchanglong.ShiTi" %>
<%@ page import="lvchanglong.BkColor" %>
<%@ page import="lvchanglong.GuShi" %>

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
									<g:if test="${ dangQianYongHu?.isMyShiTi(shiTi.id) }">
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
					
					<div class="col-md-12">
						<div class="pagination" style="clear:both;margin-top:30px;">
							<g:paginate total="${shiTiCount}" params="[text:params.text]"/>
						</div>
					</div>
				</g:if>
				<g:else>
					<g:render template="/layouts/plugins/error" model="[message:'（O _ O）：糟了，数据被妖怪抓走了！！！']"/>
				</g:else>
				
			</div>
	</body>
</html>