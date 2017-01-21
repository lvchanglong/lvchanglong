<%@ page import="lvchanglong.BkColor" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main"/>
		<title>用户列表${ grailsApplication.config.html.title.suffix }</title>
		<style>
			.table td {
				vertical-align: middle !important;
			}
		</style>
	</head>
	<body>
		<div class="row">
			<div class="col-md-4">
				<div class="listBox" style="background-color:${BkColor.getInst()};">
					<div style="font-size:30px;margin-bottom:50px;text-align:center;">用户检索</div>
					<g:form name="yongHuJianSuo" url="[controller:'public', action:'users']" method="GET">
						<div class="input-group">
							<input type="text" name="q" class="form-control" placeholder="请输入关键词（账号 | 姓名）" value="${ params.q }">
							<span class="input-group-btn">
								<button class="btn btn-primary" type="submit">检索</button>
							</span>
						</div>
					</g:form>
				</div>
			</div>
			<g:each in="${instanceList}" status="i" var="instance">
				<div class="col-md-4">
					<div class="listBox" style="background-color:${BkColor.getInst()};">
						<span style="margin-left:5px;font-size:25px;">
							${instance.xingMing}（${instance.zhangHao}）&nbsp;/&nbsp;<span style="font-size:14px;">${ instance.quanXian }</span>
						</span>
						<div class="content relative clearfix">
							<div class="row">
								<div class="col-md-12">
									<g:img uri="${ createLink(controller:'public', action:'loadTouXiang', id:instance?.id) }" width="130px" height="130px" alt="头像" class="img-thumbnail" style="display:block;margin:0 auto;"/>
								</div>
							</div>
						</div>
					</div>
				</div>
			</g:each>
			<div class="col-md-12">
				<div class="pagination">
					<g:paginate total="${instanceCount}" params="[q:params.q]"/>
				</div>
			</div>
		</div>
	</body>
</html>