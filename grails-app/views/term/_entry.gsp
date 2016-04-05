<table id="entryTable" class="table table-bordered bkColorInherit textAlignCenter">
	<tr>
		<td>术语</td>
		<td>语种</td>
		<td>学科</td>
		<td>详情</td>
	</tr>
	<g:each in="${termInstanceList}" status="i" var="termInstance">
		<tr>
			<td>
				${termInstance.name}
			</td>
			<td>${termInstance.lan}</td>
			<td>${termInstance.discipline}</td>
			<td>
                <a href="#" data-html="true" data-toggle="tooltip" title="${termInstance.termInfo}" class="colorInherit">
                    <span class="glyphicon glyphicon-info-sign"></span>
                </a>
            </td>
		</tr>
	</g:each>
</table>

<g:javascript>
	jQuery(document).tooltip({//重新绑定
		content: function () {
			return jQuery(this).attr('title');
		}
	});
</g:javascript>