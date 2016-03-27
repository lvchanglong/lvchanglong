<%@ page import="lvchanglong.BkColor" %>

<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main"/>
		<title>术语导入${ grailsApplication.config.html.title.suffix }</title>
		<style type="text/css">
			.widthKey {
				vertical-align: middle !important;
				width:75px;
			}
			.widthValue {
				vertical-align: middle !important;
			}
			.widthValue .form-control {
				margin-bottom:0;
			}
			.widthPreview {
				vertical-align: middle !important;
				text-align: left;
				font-size:20px;
			}
		</style>
	</head>
	<body>
		<g:form name="termImportForm" controller="protected" action="termImporting" useToken="true">
			<div class="listBox" style="background-color:${BkColor.getInst()};">
				<table class="table table-bordered mt15">
					<tr>
						<td colspan="2" class="textAlignCenter">
							<h1 style="margin-bottom:30px;">术语导入</h1>
						</td>
					</tr>
					<tr>
						<td class="widthKey">学科</td>
						<td class="widthValue">
							<div class="row">
								<div class="col-md-12">
									<g:if test="${disciplineInstance.parent}">
										${disciplineInstance.parent.name}&nbsp;>&nbsp;
									</g:if>
									${disciplineInstance.name}
								</div>
							</div>
						</td>
					</tr>
					<tr>
						<td class="widthKey">方向</td>
						<td class="widthValue">
							<div class="row">
								<div class="col-md-12">
									${sourceInstance.name}&nbsp;->&nbsp;${targetInstance.name}
								</div>
							</div>
						</td>
					</tr>
					<tr>
						<td class="widthKey">来源</td>
						<td class="widthValue">
							<div class="row">
								<div class="col-md-12">
									${ly}
								</div>
							</div>
						</td>
					</tr>
					<tr>
						<td colspan="2" class="textAlignCenter">
							<div class="row">
								<div class="col-md-12 textAlignCenter">
									<g:render template="/protected/fileUpload"/>
								</div>
							</div>
						</td>
					</tr>
					<tr>
						<td colspan="2" class="textAlignCenter">
							<div class="row">
								<div class="col-md-12 textAlignCenter">
									<g:hiddenField name="did" value="${disciplineInstance.id}"/>
									<g:hiddenField name="sid" value="${sourceInstance.id}"/>
									<g:hiddenField name="tid" value="${targetInstance.id}"/>
									<g:hiddenField name="ly" value="${ly}"/>
									<g:submitButton name="termImportSubmitButton" value="开始导入" class="btn btn-primary"/>
								</div>
							</div>
						</td>
					</tr>
				</table>
			</div>
		</g:form>
		<g:javascript>
			jQuery("#termImportForm").ajaxForm({
				beforeSubmit:function () {
					jQuery("#termImportSubmitButton").attr("disabled","disabled").val("后台服务器自动处理中~温馨提示，您的任何操作都不会影响到本次结果");
					return true;
				},
				success:function(data,textStatus){
					alert(data);
				},
				error:function(XMLHttpRequest,textStatus,errorThrown){
					//alert(XMLHttpRequest.responseText);
				}
			});
		</g:javascript>
	</body>
</html>