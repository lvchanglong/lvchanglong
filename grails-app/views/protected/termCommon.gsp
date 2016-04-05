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
			}
			.widthValue .form-control {
				margin-bottom:0;
			}
			.widthValue .glyphicon-question-sign {
				vertical-align: middle;
				font-size:18px;
			}
			.widthPreview {
				vertical-align: middle !important;
				text-align: left;
				line-height: 34px;
			}
		</style>
	</head>
	<body>
		<g:form controller="protected" action="termImport">
			<div class="listBox mt0" style="background-color:#563d7c;">
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
									<g:textField id="disciplineAutoComplete" name="autoDiscipline" required="required" autofocus="autofocus" value="" class="form-control" placeholder="必填"/>
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
										<span class="glyphicon glyphicon-question-sign"></span>&nbsp;例：经济、冶金、未知...
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
									<g:textField id="sourceAutoComplete" name="autoSource" value="" required="required" class="form-control" placeholder="必填"/>
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
										<span class="glyphicon glyphicon-question-sign"></span>&nbsp;例：中、英、德、俄、日、韩、法...
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
									<g:textField id="targetAutoComplete" name="autoTarget" value="" required="required" class="form-control" placeholder="必填"/>
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
										<span class="glyphicon glyphicon-question-sign"></span>&nbsp;例：中、英、德、俄、日、韩、法...
									</div>
								</div>
							</div>
						</td>
					</tr>
					<tr>
						<td class="widthKey">来源</td>
						<td class="widthValue">
							<div class="row">
								<div class="col-md-6">
									<g:textField name="ly" value="" class="form-control" required="required" placeholder="必填" onkeyup="jQuery('#lyPreview').html(this.value);"/>
								</div>
								<div class="col-md-6">
									<div id="lyPreview" class="widthPreview">
										<span class="glyphicon glyphicon-question-sign"></span>&nbsp;例：世纪英汉科技大词典、网络、日常生活...
									</div>
								</div>
							</div>
						</td>
					</tr>
					<tr>
						<td class="widthKey">流程选择</td>
						<td class="widthValue">
							<div class="row">
								<div class="col-md-6 textAlignCenter">
									<g:submitButton name="submit" value="导入" class="btn btn-primary" style="margin-bottom:3px;"/>
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