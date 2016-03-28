<table id="entryTable" class="table table-bordered mt15 bkColorInherit textAlignCenter">
	<tr>
		<td>术语</td>
		<td>语种</td>
		<td>学科</td>
		<td>地域</td>
		<td>定义</td>
		<td>来源</td>
	</tr>
	<g:each in="${termInstanceList}" status="i" var="termInstance">
		<tr>
			<td>
				<strong>${termInstance.name}</strong>
			</td>
			<td>${termInstance.lan}</td>
			<td>${termInstance.discipline}</td>
			<td>${termInstance.area}</td>
			<td>
                <a href="#" data-toggle="tooltip" title="${termInstance.dy}" class="colorInherit">
                    <span class="glyphicon glyphicon-info-sign"></span>
                </a>
            </td>
			<td>${termInstance.ly}</td>
		</tr>
	</g:each>
</table>

<g:javascript>
	jQuery('#entryTable [data-toggle="tooltip"]').tooltip();
</g:javascript>