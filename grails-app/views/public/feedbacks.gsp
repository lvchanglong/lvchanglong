<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main"/>
		<title>反馈列表${ grailsApplication.config.html.title.suffix }</title>
		<style>
			.table td {
				vertical-align: middle !important;
			}
		</style>
	</head>
	<body>
		<div class="row">
			<div class="col-md-12">
				<table class="table table-bordered table-hover" style="margin-top:30px;">
					<tr>
						<th>内容</th>
						<th style="width:110px">时间</th>
						<th style="width:100px">操作</th>
					</tr>
					<g:each in="${instanceList}" status="i" var="instance">
						<tr>
							<td>${instance.neiRong}</td>
							<td><g:formatDate date="${instance.dateCreated}" format="yyyy-MM-dd"/></td>
							<td>
								<g:if test="${session.uid}">
									<g:form name="delete-form" url="[controller:'public', action:'deleteFeedback']" method="DELETE" class="deleteForm">
										<g:hiddenField name="id" value="${instance.id}" />
										<g:submitButton name="submit" value="删除" class="btn btn-danger btn-sm"/>
									</g:form>
								</g:if>
								<g:else>
									无
								</g:else>
							</td>
						</tr>
					</g:each>
				</table>
				<div class="pagination">
					<g:paginate total="${instanceCount}" />
				</div>
			</div>
		</div>
	</body>
</html>