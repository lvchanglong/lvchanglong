<%@ page import="lvchanglong.BkColor" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main"/>
		<title>元素管理${ grailsApplication.config.html.title.suffix }</title>
	</head>
	<body>
		<g:if test="${ session.uid }">
			<g:form name="instance-save-form" url="[controller:'protected', action:'saveElement']" class="clearfix saveForm">
				<g:textField name="biaoTi" value="" placeholder="标题" required="" autofocus="" class="form-control mb15w"/>
				<g:textArea name="neiRong" value="" placeholder="内容" class="ckeditor-stop" style="width:100%;height:300px;font-size:16px;"/>
				<g:hiddenField name="user.id" value="${ session.uid }"/>
				<g:submitButton name="faBu" value="发布" class="btn btn-primary" style="margin-top:8px;margin-right:10px;"/>
			</g:form>
		</g:if>

		<div class="row">
			<g:if test="${instanceList}">
				<g:each in="${instanceList}" status="i" var="instance">
					<g:if test="${ instance }">
						<div class="col-md-4">

							<div class="listBox" style="background-color:${lvchanglong.BkColor.getInst()};">
								<h1 style="margin-top:0;text-align: center;">
									${ instance.biaoTi }
								</h1>
								<div class="content relative clearfix">
									${ instance.neiRong }
								</div>
								<div class="text-text lineHeight30 block">
									<span class="dateCreated">
										<g:formatDate date="${instance.dateCreated}" format="yyyy.MM.dd" />
									</span>
									<span class="separator">|</span>
									<span class="yongHu">
										${instance.user.xingMing}
									</span>
									<span class="separator">|</span>
									<span>
										CODE-${instance.id}
									</span>
								</div>

								<g:if test="${session.uid}">
									<a href="#" data-toggle="modal" data-target="#updateElement${instance.id}" class="btn btn-bg btn-link" style="position:absolute;top:0;left:0;color:#fff;font-weight:bold;font-size:15px;">
										修改
									</a>
									<div class="modal fade" id="updateElement${instance.id}" tabindex="-1" role="dialog" aria-labelledby="updateElement${instance.id}Title" aria-hidden="true">
										<div class="modal-dialog">
											<div class="modal-content">
												<div class="modal-header">
													<button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
													<h3 class="modal-title text-left" id="updateElement${instance.id}Title" style="color:#000;">信息修改</h3>
												</div>
												<div class="modal-body">
													<g:form name="update-form" url="[controller:'protected', action:'updateElement']" method="POST" class="updateForm">
														<g:hiddenField name="id" value="${instance.id}" />
														<g:textField name="biaoTi" value="${instance.biaoTi}" placeholder="标题" required="" autofocus="" class="form-control mb15w"/>
														<g:textArea name="neiRong" value="${instance.neiRong}" placeholder="内容" class="ckeditor-stop" style="width:100%;height:300px;font-size:16px;color:#000;"/>

														<g:submitButton name="submit" value="修改" class="btn btn-bg btn-primary"/>
													</g:form>
												</div>
											</div>
										</div>
									</div>

									<g:form name="delete-form" url="[controller:'protected', action:'deleteElement']" method="DELETE" class="deleteForm">
										<g:hiddenField name="id" value="${instance.id}" />
										<g:submitButton name="submit" value="删除" class="btn btn-bg btn-link" style="position:absolute;top:0;right:0;color:#fff;font-weight:bold;"/>
									</g:form>
								</g:if>
							</div>

						</div>
					</g:if>
				</g:each>
				<div class="col-md-12">
					<div class="pagination" style="clear:both;margin-top:30px;">
						<g:paginate total="${instanceCount}"/>
					</div>
				</div>
			</g:if>
			<g:else>
				<div class="col-md-12">
					<g:render template="/layouts/plugins/errorInfo" model="[message:'没有数据呀（O _ O）']"/>
				</div>
			</g:else>
		</div>
	</body>
</html>