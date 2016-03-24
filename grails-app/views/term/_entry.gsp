<table class="table table-bordered mt15 bold">
	<tr>
		<td>术语</td>
		<td>学科</td>
		<td>语种</td>
		<td>来源</td>
		<td>地域</td>
		<td>定义</td>
	</tr>
	<g:each in="${termInstanceList}" status="i" var="termInstance">
		<tr>
			<td>${termInstance.name}</td>
			<td>${termInstance.discipline}</td>
			<td>${termInstance.lan}</td>
			<td>${termInstance.ly}</td>
			<td>${termInstance.area}</td>
			<td>${termInstance.dy}</td>
		</tr>
	</g:each>
</table>