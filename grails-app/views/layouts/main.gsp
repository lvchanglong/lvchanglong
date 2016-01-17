<!doctype html>
<html lang="zh-CN" class="no-js">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
		<meta name="viewport" content="width=device-width, initial-scale=1">
		
		<meta name="renderer" content="webkit">
		
		<meta name="Keywords" content="吕常龙"/>
		<meta name="Description" content="个人网站"/>

		<title><g:layoutTitle default="发现矛盾，解决矛盾${ grailsApplication.config.html.title.suffix }"/></title>
		
		<link rel="shortcut icon" href="${assetPath(src: 'favicon.ico')}" type="image/x-icon">
		<link rel="apple-touch-icon" href="${assetPath(src: 'apple-touch-icon.png')}">
		<link rel="apple-touch-icon" sizes="114x114" href="${assetPath(src: 'apple-touch-icon-retina.png')}">
		
		<g:javascript>
			var _hmt = _hmt || [];
			(function() {
			  var hm = document.createElement("script");
			  hm.src = "//hm.baidu.com/hm.js?d3cb23257863ed5f5f23889f7bfe3238";
			  var s = document.getElementsByTagName("script")[0]; 
			  s.parentNode.insertBefore(hm, s);
			})();
		</g:javascript>
		
		<g:javascript>
		    if (top.location !== self.location) {
			    //top.location = self.location;
			}
		</g:javascript>
		
		<link href="//cdn.bootcss.com/bootstrap/3.3.5/css/bootstrap.min.css" rel="stylesheet">
		<link href="//cdn.bootcss.com/font-awesome/4.3.0/css/font-awesome.min.css" rel="stylesheet">
		<link href="//cdn.bootcss.com/nprogress/0.2.0/nprogress.min.css" rel="stylesheet">
		<link href="//cdn.bootcss.com/jqueryui/1.11.4/jquery-ui.min.css" rel="stylesheet">
		<%--
		<asset:stylesheet src="bootstrap-3.3.5-dist/css/bootstrap.min.css"/>
		<asset:stylesheet src="font-awesome-4.3.0/css/font-awesome.min.css"/>
		<asset:stylesheet src="nprogress-master/nprogress.css"/>
		<asset:stylesheet src="jquery-ui-1.11.4/jquery-ui.min.css"/>
		--%>
		<asset:stylesheet src="application.css"/>
		
		<script src="//cdn.bootcss.com/jquery/2.1.4/jquery.min.js"></script>
		<script src="//cdn.bootcss.com/jquery.form/3.51/jquery.form.min.js"></script>
		<script src="//cdn.bootcss.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>
		<script src="//cdn.bootcss.com/ckeditor/4.4.5/ckeditor.js"></script>
		<script src="//cdn.bootcss.com/nprogress/0.2.0/nprogress.min.js"></script>
		<script src="//cdn.bootcss.com/jqueryui/1.11.4/jquery-ui.min.js"></script>
		<!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
	    <!--[if lt IE 9]>
	    	<script src="http://cdn.bootcss.com/html5shiv/3.7.2/html5shiv.min.js"></script>
	    	<script src="http://cdn.bootcss.com/respond.js/1.4.2/respond.min.js"></script>
	    <![endif]-->
		<%--
		<asset:javascript src="jquery-2.1.3.js"/>
		<asset:javascript src="form-master/jquery.form.js"/>
		<asset:javascript src="bootstrap-3.3.5-dist/js/bootstrap.min.js"/>
		<asset:javascript src="ckeditor/ckeditor.js"/>
		<asset:javascript src="nprogress-master/nprogress.js"/>
		<asset:javascript src="jquery-ui-1.11.4/jquery-ui.min.js"/>
		--%>
		<asset:javascript src="application.js"/>
		<g:layoutHead/>
	</head>
	<body>
		<g:pageProperty name="page.header" default="${ render(template:"/layouts/header") }"/>		
		
		<div class="container main">
			<g:layoutBody/>
	    </div>
	    
		<g:pageProperty name="page.footer" default="${ render(template:"/layouts/footer") }"/>
		
		<div id="spinner">
			<asset:image src="SuCai/奔跑.png" alt="处理中"/>
			<div class="tiShi"></div>
		</div>
		
		<g:javascript>
			jQuery(document).ready(function(){
				NProgress.configure({
					showSpinner: false
				});
				NProgress.start();
			});
			jQuery(window).load(function(){
				NProgress.done();
			});
		</g:javascript>
	</body>
</html>