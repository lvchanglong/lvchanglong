<table id="entryTable" class="table table-bordered mt15" style="background-color: transparent;">
	<tr>
		<td>术语</td>
		<td>学科</td>
		<td>语种</td>
		<td>来源</td>
		<td>地域</td>
	</tr>
	<g:each in="${termInstanceList}" status="i" var="termInstance">
		<tr>
			<td>
                <a href="#" data-toggle="tooltip" title="${termInstance.dy}" class="colorInherit">
                    ${termInstance.name}
                </a>
            </td>
			<td>${termInstance.discipline}</td>
			<td>${termInstance.lan}</td>
			<td>${termInstance.ly}</td>
			<td>${termInstance.area}</td>
		</tr>
	</g:each>
</table>

<g:javascript>
	jQuery('#entryTable [data-toggle="tooltip"]').tooltip();
</g:javascript>