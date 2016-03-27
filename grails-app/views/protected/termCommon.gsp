<%@ page import="lvchanglong.BkColor" %>

<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main"/>
		<title>基础信息${ grailsApplication.config.html.title.suffix }</title>
		<style type="text/css">
			.widthKey {
				vertical-align: middle !important;
				width:75px;
			}
			.widthValue {
				vertical-align: middle !important;
				1width:400px;
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
		<g:form controller="protected" action="termImport">
			<div class="listBox" style="background-color:${BkColor.getInst()};">
				<table class="table table-bordered mt15">
					<tr>
						<td colspan="2" class="textAlignCenter">
							<h1 style="margin-bottom:30px;">基础信息</h1>
						</td>
					</tr>
					<tr>
						<td class="widthKey">学科</td>
						<td class="widthValue">
							<div class="row">
								<div class="col-md-6">
									<g:textField id="disciplineAutoComplete" name="autoDiscipline" value="" class="form-control" placeholder="经济、心理、石油..."/>
									<g:hiddenField name="discipline" value=""/>
									<script type="text/javascript">
										jQuery("#disciplineAutoComplete").autocomplete({
											source: "${ createLink(controller:'term', action:'searchDiscipline') }",
											autoFocus: true,
											select: function(event, ui) {
												var label = ui.item.label;
												jQuery("#discipline").val(label);
												jQuery("#disciplinePreview").html(ui.item.label);
											}
										});
									</script>
								</div>
								<div class="col-md-6">
									<div id="disciplinePreview" class="widthPreview">
										<span class="glyphicon glyphicon-question-sign"></span>
									</div>
								</div>
							</div>
						</td>
					</tr>
					<tr>
						<td class="widthKey">源语言</td>
						<td class="widthValue">
							<div class="row">
								<div class="col-md-6">
									<g:textField id="sourceAutoComplete" name="autoSource" autofocus="" value="" class="form-control" placeholder="中、英、韩..."/>
									<g:hiddenField name="source" value=""/>
									<script type="text/javascript">
										jQuery("#sourceAutoComplete").autocomplete({
											source: "${ createLink(controller:'term', action:'searchLan') }",
											autoFocus: true,
											select: function(event, ui) {
												var label = ui.item.label;
												jQuery("#source").val(label);
												jQuery("#sourcePreview").html(ui.item.label);
											}
										});
									</script>
								</div>
								<div class="col-md-6">
									<div id="sourcePreview" class="widthPreview">
										<span class="glyphicon glyphicon-question-sign"></span>
									</div>
								</div>
							</div>
						</td>
					</tr>
					<tr>
						<td class="widthKey">目标语言</td>
						<td class="widthValue">
							<div class="row">
								<div class="col-md-6">
									<g:textField id="targetAutoComplete" name="autoTarget" value="" class="form-control" placeholder="中、英、韩..."/>
									<g:hiddenField name="target" value=""/>
									<script type="text/javascript">
										jQuery("#targetAutoComplete").autocomplete({
											source: "${ createLink(controller:'term', action:'searchLan') }",
											autoFocus: true,
											select: function(event, ui) {
												var label = ui.item.label;
												jQuery("#target").val(label);
												jQuery("#targetPreview").html(ui.item.label);
											}
										});
									</script>
								</div>
								<div class="col-md-6">
									<div id="targetPreview" class="widthPreview">
										<span class="glyphicon glyphicon-question-sign"></span>
									</div>
								</div>
							</div>
						</td>
					</tr>
					<tr>
						<td class="widthKey">来源</td>
						<td class="widthValue">
							<div class="row">
								<div class="col-md-12">
									<g:textField name="ly" value="" class="form-control" placeholder="考拉翻译、互联网、日常生活..."/>
								</div>
							</div>
						</td>
					</tr>
					<tr>
						<td class="widthKey">流程选择</td>
						<td class="widthValue">
							<div class="row">
								<div class="col-md-6 textAlignCenter">
									<g:submitButton name="submit" value="导入" class="btn btn-primary"/>
								</div>
								<div class="col-md-6 textAlignCenter">
									<a href="#" class="btn btn-primary" disabled="disabled">添加</a>
								</div>
							</div>
						</td>
					</tr>
				</table>
			</div>
		</g:form>
	</body>
</html>