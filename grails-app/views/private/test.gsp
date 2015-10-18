<%@ page import="lvchanglong.YongHu" %>

<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main"/>
		<title>测试${ grailsApplication.config.html.title.suffix }</title>
	</head>
	<body>
		
		<g:render template="/layouts/plugins/3D" />
		
		<div style="line-height:30px;">
			我是一个苦心修行了30年的和尚，即将修成正果...山下有个镇，镇里有名大员外，这大员外很是阴险毒辣，总想破坏我的名节。某日，大员外对自家一个颇有几分姿色的丫鬟说：你今晚到庙里去，一定要把那我勾上床。成功了，我一定重重奖赏你，并给你找个好人家，但明天鸡叫时分若不能成功，我就将你乱棍打死。那丫鬟当然只得照办，于是进入庙中，施展了浑身解术来色诱我。哪知我整夜坐禅居然丝毫不为所动。眼看就要鸡叫了，那丫鬟心想自己生命不保，便大哭起来。我觉得很纳闷儿，忍不住问她哭为何故？丫鬟指着我骂道：你不是一个真和尚！我有点不服气地反问：我做错何事，怎么不是真和尚？丫鬟喝道：佛经上说，救人一命，胜造七级浮屠，而你却见死不救。我听后，连忙追问发生了何事？丫鬟就把事情经过从头讲了一遍。这下子，我着实碰到了两难的选择：见死不救，成不了佛；遇色成奸，同样也成不了佛。我该怎么办？
			<hr/>
			
			<a class="story btn btn-primary" href="javascript:void(0)" id="Y" title="A">见死不救</a>
			<a class="story btn btn-primary" href="javascript:void(0)" id="N" title="A">没羞没臊</a>
			
			<div class="none" id="YX">
				丫鬟死了，但我的名节却保住了...
			</div>
			<div class="none" id="NX">
				丫鬟受到了重赏并最终找到了好的归宿，而我却已经声名狼藉...
			</div>
		</div>
		
		<g:javascript>
			xuanZeXiangYing();
		</g:javascript>
		
		<%--
		<g:form name="ajaxForm" controller="private" action="ajaxTest">
			<span id="console">控制台</span>
			<g:submitButton name="submit" value="提交"/>
		</g:form>
		
		<g:javascript>
			jQuery("#ajaxForm").ajaxForm({
				success:function(data,textStatus){
					success(data,textStatus,'#console');
				}, 
				error:function(XMLHttpRequest,textStatus,errorThrown){
					error(XMLHttpRequest,textStatus,errorThrown,'#console');
				}
			});
		</g:javascript>
		--%>
		
		<content tag="header">
		</content>
		
		<content tag="footer">
		</content>
	</body>
</html>