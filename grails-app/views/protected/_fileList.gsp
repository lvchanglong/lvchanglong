<table id="fileListTable" class="table table-bordered mt15 bkColorInherit">
	<g:each in="${files}" status="i" var="file">
		<tr>
			<td>
				<div class="pull-left" style="font-size:20px;text-align:left;font-weight:bold;">
					${file}
				</div>
				<div class="pull-right">
					<g:form name="file-delete-form" url="[controller:'protected', action:'deleteFile']" method="DELETE" class="fileListDeleteForm">
						<g:hiddenField name="fileName" value="${file}" />
						<g:submitButton name="submit" value="删除" class="btn btn-danger"/>
					</g:form>
				</div>

				<div class="clearBoth"></div>

				<div id="file${i}">
					%{--_previewFile--}%
				</div>
				<g:javascript>
					$.ajax({
						url: "${createLink(controller:'protected', action:'previewFile')}",
						data: {'fileName':"${file}"},
						success: function(data,textStatus){
							$("#file${i}").html(data);
						}
					});
				</g:javascript>
			</td>
		</tr>
	</g:each>
	<g:javascript>
		jQuery(".fileListDeleteForm").ajaxForm({
			success:function(data,textStatus){
				jQuery("#fileListTable").replaceWith(data);
			}
		});
	</g:javascript>
</table>
