<div class="row">
	<div class="col-md-12">
		<ul class="listBox" style="background-color:#206F96;">
			<li>
				<g:form name="termForm" controller="term" action="searchEntry" method="POST">
					<div class="input-group">
						<span class="input-group-addon" id="termBasicAddon">术语&nbsp;></span>
						<g:textField id="termAutoComplete" name="term" autofocus="" class="form-control" placeholder="Search for..." aria-describedby="termBasicAddon"/>
					</div>
				</g:form>

				<script type="text/javascript">
					jQuery("#termForm").ajaxForm({
						success:function(data,textStatus){
							jQuery("#termWrapper").html(data);
						},
						error:function(XMLHttpRequest,textStatus,errorThrown){
							error(XMLHttpRequest,textStatus,errorThrown,'#termWrapper');
						}
					});

					jQuery("#termAutoComplete").autocomplete({
						source: "${ createLink(controller:'term', action:'searchTerm') }",
						select: function(event, ui) {
							jQuery(this).val(ui.item.value);//值同步
							jQuery("#termForm").submit();
						}
					});
				</script>
			</li>
			<li>
				<div id="termWrapper">

				</div>
				<div style="margin-top:15px;">
					测试版：仅支持检索“水银、汞、监狱、jail、prision”
				</div>
			</li>
		</ul>
	</div>
</div>