<div style="padding:15px 70px 10px 70px;text-align: center;">
	<table id="fileListTable" class="table table-bordered mt15 bkColorInherit">
		<g:each in="${arrayHM}" status="i" var="hm">
			<tr>
				<td>
					${hm.get("from")}
				</td>
				<td>
					${hm.get("to")}
				</td>
			</tr>
		</g:each>
	</table>
</div>