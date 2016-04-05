<%@ page import="lvchanglong.BkColor" %>

<div class="listBox mt0" style="background-color:#563d7c;">
	<table class="table table-bordered mt15">
		<tr>
			<td class="textAlignCenter">
				<h1 style="margin-bottom:30px;">术语检索</h1>
			</td>
		</tr>
		<tr>
			<td>
				<div class="row">
					<div class="col-md-12">
						<ul>
							<li style="padding:30px 30px 0 30px;">
								<g:form name="termForm" controller="term" action="getEntry" method="POST">
									<div class="input-group">
										<span class="input-group-addon" id="termBasicAddon">术语&nbsp;></span>
										<g:textField id="termAutoComplete" name="autoTerm" autofocus="" class="form-control" placeholder="Search for..." aria-describedby="termBasicAddon"/>
										<g:hiddenField name="term" value=""/>
									</div>
								</g:form>

								<script type="text/javascript">
									jQuery("#termAutoComplete").autocomplete({
										source: "${ createLink(controller:'term', action:'searchTerm') }",
										autoFocus: true,
										select: function(event, ui) {
											var label = ui.item.label;
											jQuery("#term").val(label);//在此方法中提交from前需要同步值
											jQuery("#termForm").submit();
										}
									});
									jQuery("#termForm").ajaxForm({
										success:function(data,textStatus){
											jQuery("#termWrapper").removeClass("alert alert-warning").html(data);
										},
										error:function(XMLHttpRequest,textStatus,errorThrown){
											error(XMLHttpRequest,textStatus,errorThrown,'#termWrapper');
										}
									});
								</script>
							</li>
							<li style="padding:0 30px 0 30px;">
								<div id="termWrapper" class="paddingNormal">

								</div>
							</li>
						</ul>
					</div>
				</div>
			</td>
		</tr>
	</table>
</div>