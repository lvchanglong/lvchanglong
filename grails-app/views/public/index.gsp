<%@ page import="lvchanglong.YongHu" %>
<%@ page import="lvchanglong.ShiTi" %>

<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main"/>
	</head>
	<body>
		<g:if test="${ kongJianList }">
			<div class="row">
				<div class="col-md-8">
					<div id="carousel-example-generic" class="carousel slide" data-ride="carousel">
						<ol class="carousel-indicators">
							<g:each in="${kongJianList}" status="i" var="kongJian">
								<li data-target="#carousel-example-generic" data-slide-to="${ i }" class="${ (i == 0)?"active":"" }"></li>
							</g:each>
							
							<li data-target="#carousel-example-generic" data-slide-to="${ kongJianCount }" class=""></li>
					  	</ol>
					  	
					  	<div class="carousel-inner" role="listbox" style="">
					  		<g:each in="${kongJianList}" status="i" var="kongJian">
					  			<div class="item ${ (i == 0)?"active":"" }">
					  				<g:link controller="public" action="showKongJian" id="${ kongJian.id }" class="block colorWhite" target="_blank">
						      			<div class="row slider">
						      				<div class="col-md-12 sliderImg">
						      					${ kongJian.fuJian }
						      				</div>
						      				<div class="col-md-12 sliderText">
						      					<div style="padding:0 30px 30px 30px;">
						      						<h1 class="text-center bold">
								      					${ kongJian.biaoTi }
								      				</h1>
								      				${ kongJian.miaoShu }
						      					</div>
						      				</div>
						      			</div>
						      		</g:link>
					    		</div>
					  		</g:each>
					  		
					  		<div class="item" >
					  			<g:link controller="public" action="chaKanLiShi" class="block colorWhite" target="_blank">
					      			<div class="row slider">
					      				<div class="col-md-12 sliderImg">
					      					<asset:image src="SuCai/20059142011524.jpg" style="width:100%;height:370px;"/>
					      				</div>
					      				<div class="col-md-12 sliderText">
					      					<div style="padding:0 30px 30px 30px;">
					      						<h1 class="text-center bold">
							      					查看历史
							      				</h1>
							      				从前有个山，山里有个庙，庙里有个老和尚在讲故事…
							      				从前有个山，山里有个庙，庙里有个老和尚在讲故事…
							      				从前有个山，山里有个庙，庙里有个老和尚在喝水…...
					      					</div>
					      				</div>
					      			</div>
					      		</g:link>
					  		</div>
					  	</div>
					  	
					  	<a class="left carousel-control" href="#carousel-example-generic" role="button" data-slide="prev" style="background-image:none;">
					    	<span class="glyphicon glyphicon-chevron-left" aria-hidden="true"></span>
					    	<span class="sr-only">Previous</span>
					  	</a>
					  	<a class="right carousel-control" href="#carousel-example-generic" role="button" data-slide="next" style="background-image:none;">
					    	<span class="glyphicon glyphicon-chevron-right" aria-hidden="true"></span>
					    	<span class="sr-only">Next</span>
					  	</a>
					</div>
				</div>
				
				<div class="col-md-4">
					<div class="rightTopBox" style="background-color:#2489C5;">
						<div style="padding-top:40px;">
							<div style="position:absolute;right:0;top:0;font-size:24px;font-weight:bold;padding:15px 45px 0 0;" class="text-right">
								<g:link controller="public" action="woDeChuanShuo" target="_self" class="toCenter">
									<span class="glyphicon glyphicon-chevron-right"></span>
								</g:link>
							</div>
							<g:img uri="${ createLink(controller:'public', action:'loadTouXiang', id:session.uid) }" width="180px" height="180px" alt="头像" class="img-rounded toCenter"/>
						</div>
					</div>
					<div class="rightBottomBox" style="background-color:#2c313a;">
						<div style="padding:40px 30px 0 30px;font-weight:bold;font-size:20px;line-height:26px;color:#fff;">
							我叫<strong style="font-size:26px;">${ session.uname }</strong>，${ session.uinfo }
						</div>
					</div>
				</div>
				
				<div class="col-md-12">
					<hr/>
				</div>
				
				<div class="col-md-12">
					<ul>
						<g:each in="${kongJianList}" status="i" var="kongJian">
							<li>
								<g:render template="/public/kongJian" model="[kongJian:kongJian]"/>
								<hr/>
							</li>
						</g:each>
					</ul>
				</div>
			</div>
		</g:if>
		<g:else>
			<h1>（T_T）页面被外星人劫走了...</h1>
		</g:else>
	</body>
</html>