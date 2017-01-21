<%@ page import="lvchanglong.Feedback" %>

<h2 style="margin-top: 0;">
	关于本<asset:image src="SuCai/网站.png" alt="网站" style="width:104px;height:51px;"/>，你想<asset:image src="SuCai/说.png" alt="说" style="width:56px;height:51px;"/>什么？
</h2>
<g:form name="save-form" controller="public" action="saveFeedback" class="saveForm">
	<g:hiddenField name="xxx" value="${Feedback.count().encodeAsMD5()}"/>

	<g:textArea name="neiRong" required="" autofocus="" class="form-control borderRadius" style="width:100%;" placeholder="写点什么吧？"/>
	<g:submitButton name="submit" value="发布" class="btn btn-primary"/>
</g:form>