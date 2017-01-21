<%@ page import="lvchanglong.Feedback" %>
<g:form name="save-form" controller="public" action="saveFeedback" class="saveForm">
	<div class="row">
		<div class="col-md-12">
			<h2 style="margin-top: 0;margin-bottom:5px;">
				关于本<asset:image src="SuCai/网站.png" alt="网站" style="width:104px;height:51px;"/>，你想<asset:image src="SuCai/说.png" alt="说" style="width:56px;height:51px;"/>什么？
			</h2>
		</div>
		<div class="col-md-12">
			<g:textArea name="neiRong" required="" autofocus="" class="form-control borderRadius" style="width:100%;" placeholder="写点什么吧？"/>
		</div>
		<div class="col-md-12">
			<g:hiddenField name="xxx" value="${Feedback.count().encodeAsMD5()}"/>
			<g:submitButton name="submit" value="发布" class="btn btn-primary btn-lg"/>
		</div>
	</div>
</g:form>