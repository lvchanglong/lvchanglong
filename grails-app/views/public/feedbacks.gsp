<%@ page import="lvchanglong.BkColor" %>
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
			<g:each in="${instanceList}" status="i" var="instance">
				<div class="col-md-4">
					<div class="listBox" style="background-color:${BkColor.getInst()};">
						<span style="margin-left:5px;font-size:25px;">
							<g:formatDate date="${instance.dateCreated}" format="yyyy-MM-dd"/>&nbsp;/&nbsp;<span style="font-size:14px;">${instance.neiRong}</span>
						</span>
						<g:if test="${session.uid}">
							<g:form name="delete-form" url="[controller:'public', action:'deleteFeedback']" method="DELETE" class="deleteForm">
								<g:hiddenField name="id" value="${instance.id}" />
								<g:submitButton name="submit" value="删除" class="btn btn-sm btn-link" style="position:absolute;top:0;right:0;"/>
							</g:form>
						</g:if>
					</div>
				</div>
			</g:each>
			<div class="col-md-12">
				<div class="pagination">
					<g:paginate total="${instanceCount}" />
				</div>
			</div>
		</div>
	</body>
</html>