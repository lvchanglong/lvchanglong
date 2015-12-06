<%@ page import="lvchanglong.YongHu" %>

<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main"/>
		<title>组件${ grailsApplication.config.html.title.suffix }</title>
	</head>
	<body>
		<div class="row" style="padding-top:20px;">
            <div class="col-md-3">
            	<div class="largeBox">
	            	<h4>信息推送</h4>
					<h6 style="color:black;">
						<span id="kongjian-wrapper-h1">信息推送</span>
						<g:javascript>
							var source = new EventSource("${ createLink(controller:'public', action:"jinQiGongGao") }");
							source.onmessage = function(event) {
								jQuery("#kongjian-wrapper-h1").html(event.data);//近期公告
							};
						</g:javascript>
					</h6>
				</div>
            </div>
            <div class="col-md-6">
            	<div class="largeBox">
	            	<h4>当前时间</h4>
					<g:render template="/layouts/plugins/shiZhong"/>
				</div>
            </div>
            <div class="col-md-3">
            	<div class="largeBox">
	            	<h4>卡通动画</h4>
					<g:render template="/layouts/plugins/kaTong" />
				</div>
            </div>
            
            <div class="col-md-3">
            	<div class="largeBox">
	            	<h4>生肖查询</h4>
					<g:render template="/layouts/plugins/shengXiao"/>
				</div>
            </div>
            <div class="col-md-9">
            	<div class="largeBox">
	            	<h4>性别表示</h4>
					<asset:image src="SuCai/女.png" alt="女" title="女" style="float:left;width:250px;height:250px;margin-right:30px;"/>
					<asset:image src="SuCai/男.png" alt="男" title="男" style="float:left;width:250px;height:250px;margin-right:30px;"/>
					<asset:image src="SuCai/未知.png" alt="未知" title="未知" style="float:left;width:250px;height:250px;"/>
				</div>
            </div>
            
            <div class="col-md-12">
            	<div class="largeBox">
					<h4>腾云驾雾</h4>
					<g:render template="/layouts/plugins/feiTeng"/>
				</div>
            </div>
        </div>
	</body>
</html>