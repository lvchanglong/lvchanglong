<%@ page import="lvchanglong.YongHu" %>
<%@ page import="lvchanglong.BkColor" %>

<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main"/>
		<g:set var="dangQianYongHu" value="${ YongHu.get(session.uid) }" />
	</head>
	<body>
			<div class="row">

				<div class="col-md-4">
					<div class="listBox" style="background-color:${BkColor.getInst()};">
						<div style="font-size:30px;margin-bottom:50px;text-align:center;">用户检索</div>
						<g:form name="yongHuJianSuo" url="[controller:'private', action:'users']" method="GET">
							<div class="input-group">
								<input type="text" name="q" class="form-control" placeholder="请输入姓名" value="${ params.q  }">
								<span class="input-group-btn">
									<button class="btn btn-primary" type="submit">检索</button>
								</span>
							</div>
						</g:form>
					</div>
				</div>

				<g:if test="${yongHuList}">
					<g:each in="${yongHuList}" status="j" var="yongHu">
						<g:if test="${ yongHu }">
							<div class="col-md-4">
								<div class="listBox" style="background-color:${BkColor.getInst()};">

									<span style="margin-left:5px;font-size:25px;">
										${yongHu}&nbsp;/&nbsp;<span style="font-size:14px;">${ yongHu.quanXian }</span>
									</span>
								
									<div class="content relative clearfix">
										<div class="row">
											<div class="col-md-12 col-xs-12">
												<g:img uri="${ createLink(controller:'public', action:'loadTouXiang', id:yongHu?.id) }" width="130px" height="130px" alt="头像" class="img-thumbnail" style="display:block;margin:0 auto;"/>
											</div>
										</div>
									</div>
									
									<div class="text-text lineHeight30 block">
										<g:form name="yongHu-delete-form" url="[controller:'private', action:'deleteYongHu']" method="DELETE" class="deleteForm">
											<g:hiddenField name="id" value="${yongHu.id}" />
											<g:submitButton name="submit" value="删除" class="btn btn-danger"/>
										</g:form>
									</div>
								</div>
							</div>
						</g:if>
					</g:each>
					
					<div class="col-md-12">
						<div class="pagination" style="clear:both;margin-top:30px;">
							<g:paginate total="${yongHuCount}" params="[q:params.q]"/>
						</div>
					</div>
				</g:if>
				
			</div>
	</body>
</html>