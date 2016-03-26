<%@ page import="lvchanglong.YongHu" %>

<g:set var="dangQianYongHu" value="${ YongHu.get(session.uid) }" />

<div class="mb10 h1 text-center block">
	<g:if test="${ dangQianYongHu?.isMyShiTi(shiTi.id) }">
		<g:link controller="protected" action="editShiTi" id="${ shiTi.id }" target="_blank" onclick="reloadConfirm();" class="link"><span class="glyphicon glyphicon-edit"></span></g:link>
	</g:if>
	
	<g:link controller="public" action="showShiTi" id="${ shiTi.id }" target="_blank" class="link">
		${ shiTi.biaoTi }
	</g:link>
	
	<span style="margin-left:5px;font-size:18px;color:lightgray;">
		${ shiTi.id }
	</span>
</div>
<div class="content relative clearfix" id="htmlForPreview">
	${ shiTi.neiRong }
</div>

<div class="text-right lineHeight30">
	<g:render template="/layouts/plugins/zhuanZai"/>
	<span class="separator">|</span>
	<span class="dateCreated">
		<g:formatDate date="${shiTi.dateCreated}" format="yyyy.MM.dd" />
	</span>
	<span class="separator">|</span>
	<span class="yongHu">
		${shiTi.getYongHuAsString()}
	</span>
</div>

<div style="clear:both;"></div>