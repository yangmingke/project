<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="s"  uri="/struts-tags"%>
<%@ taglib prefix="u"  uri="/flypaas-tags"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html>
<html lang="zh-cn">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=edge" />
<title>应用管理 - 应用详情</title>
<%@include file="/page/resource.jsp" %>
</head>

<body id="02-1">
	<!--头部header bof-->
	<%@include file="/page/head.jsp" %>
	<!--头部header eof-->

	<!--主体content bof-->
	<div class="content">

		<!--侧边side bof-->
		<%@include file="/page/left.jsp" %>
		<!--侧边side bof-->

		<!--右侧main bof-->
		<div class="main">
			<div class="breadcrumbs">
				<ul>
					<li><a href="<%=path %>/app/appManager">应用管理</a></li>
					<li class="active"><a href="<%=path %>/app/appManager">应用列表</a></li>
				</ul>
			</div>
			
			<div class="main_tab_tit">
				<ul>
					<li class="active">应用信息</li>
				</ul>
			</div>
			
			<s:form namespace="/app" action="update" method="post" theme="simple" name="appForm" id="appForm" class="app_form">
				<!--基础配置 bof-->
				<div class="edit_box edit_basic">
					<h1>基础配置<a href="<%=path %>/app/edit?appSid=<u:des3 value='${app.appSid }' />">编辑应用</a></h1>
					<div class="edit_ctn">
						<div class="edit_field">
							<dl>
								<dt>应用ID</dt>
								<dd><span class="txt">${app.appSid }</span></dd>
							</dl>
						</div>
						<div class="edit_field">
							<dl>
								<dt>应用名称</dt>
								<dd><span class="txt">${app.appName }</span></dd>
							</dl>
						</div>

						<div class="edit_field">
							<dl>
								<dt>应用类型</dt>
								<dd>
									<span class="txt">
									<s:if test="app.appKind==1">网站</s:if><s:elseif test="app.appKind==2">移动应用</s:elseif><s:else>其他</s:else>
									</span>
								</dd>						
							</dl>
						</div>

						<div class="edit_field">
							<dl>
								<dt>所属行业</dt>
								<dd>
									<span class="txt">${app.paramValue }&nbsp;</span>
								</dd>						
							</dl>
						</div>

						<div class="edit_field">
							<dl>
								<dt>服务器白名单</dt>
								<dd>
								<span class="txt">
								<textarea id="white" disabled="disabled">${whiteList.whiteAddress }</textarea>
								</span>
								</dd>
							</dl>
						</div>
					</div>
				</div>
				<!--基础配置 eof-->

				<!--高级配置 bof-->
				<div class="edit_box edit_advanced">
					<h1>高级配置</h1>
					<div class="edit_ctn">
						<div class="edit_field">
									<dl>
										<dt>回调地址功能配置</dt>
										<s:if test="cbfunList!=null">
										   <s:iterator value="cbfunList" var="cbfun" status="index">
											<dd class="dd_txt">
												<h2>
													<input type="checkbox" disabled="disabled" name="cbfun" <s:if test="#cbfun.tcallBack!=null">checked="checked"</s:if> value="${cbfun.paramKey}"/>
													<s:property value="#cbfun.paramValue" />
													<span class="ask">
														<i class="ask_icon">&nbsp;</i>
														<em class="ask_ctn">
															<s:property value="#cbfun.paramTypeName" />
														</em>
													</span>										
												</h2>
												<div class="set_box set_box1 set_callback" <s:if test="#index.index!=0">style="display:none;"</s:if>>
													<ul>
														<li class="set_ctn">
															<input type="text" disabled="disabled"  onblur="isCkBox(this)" <s:if test="#cbfun.tcallBack!=null">value="${cbfun.tcallBack.callAddress }"</s:if> name="cbfunurl" placeholder=" 输入回调地址 http://" />
															<input type="text" disabled="disabled" value="POST" class="short"  />
														</li>
													</ul>
												</div>
												<div class="clear"></div>
											</dd>
										</s:iterator>
									</s:if>
									</dl>
						</div>

						<div class="edit_field edit_verify">
							<dl>
								<dt>智能验证</dt>
								<dd>
									<h2>
										<input type="checkbox" id="znyzck" disabled="disabled" <s:if test="app.ckKey!=''&&app.ckKey!=null">checked="checked"</s:if>/>
										开启
										<span class="tips">如移动应用集成智能验证码SDK需要勾选</span>
									</h2>
									<div class="set_box set_box1">
										<ul>
											<li class="set_tit">
												<i class="star">&nbsp;</i>
												秘钥
											</li>
											<li class="set_ctn">
												<input type="text" disabled="disabled" name="app.ckKey" value="${app.ckKey }" id="ckKey" placeholder="（必填）最大32位字母、数字大小写混合" />
												<span id="ckKey_error"  class="error" style="display:none"></span>
											</li>
										</ul>
										<ul>
											<li class="set_tit">
												回调地址
											</li>
											<li class="set_ctn">
												<input type="text" disabled="disabled" name="app.ckCallbackUrl" value="${app.ckCallbackUrl }"  id="ckCallbackUrl" placeholder=" 输入回调地址 http://" />
												<span id="ckCallbackUrl_error" class="error" style="display:none;"></span>
											</li>
										</ul>	
										<ul>
											<li class="set_tit">
												有效时间
											</li>
											<li class="set_ctn set_ctn1">
												<input type="text" disabled="disabled" name="app.ckEnddate" value="${app.ckEnddate }" id="ckEnddate" />分钟<span class="tips">当短信下发时开始记时，系统默认20分钟</span>
												<span id="ckEnddate_error"  class="error" style="display:none"></span>
											</li>
										</ul>
										<ul>
											<li class="set_tit">
												<i class="star">&nbsp;</i>
												验证类型
											</li>
											<li class="set_ctn">
												<span class="txt">
													<input disabled="disabled"  type="text" value="<s:if test="app.ckWay==1">短信</s:if><s:elseif test="app.ckWay==2">语音</s:elseif><s:elseif test="app.ckWay==3">短信+语音</s:elseif>" class="short"/>
												</span>
											</li>
										</ul>								
									</div>
									<div class="clear"></div>
								</dd>
							</dl>
						</div>
						
						<div class="edit_field">
							<dl>
								<dt>身份验证最大值</dt>
								<dd class="dd_nobg">
								<input type="text" disabled="disabled" name="app.ckNum"  id="ckNum" <s:if test="app.ckNum>0">value="${app.ckNum }"</s:if><s:else>value="10"</s:else>  />次
								</dd>
							</dl>
						</div>
						
						<s:if test="user.userType==2">
						<div class="edit_field">
							<dl>
								<dt>显号服务</dt>
								<dd class="dd_nobg">
									<h2>
										<input disabled="disabled" <s:if test="app.isShowNbr==1">checked="checked"</s:if> type="checkbox" value="app.isShowNbr" id="ckshownbr"  />
										<input type="hidden" name="app.isShowNbr" id="shownbr" value="0"/>
										开启
										<span class="tips">如您勾选该项，将默认允许应用内用户之间通话使用显号模式，同时您还可以根据应用的需求通过接口进行针对个别用户特殊配置。如需查看详细显号规则，请点击<a href="http://docs.flypaas.com/doku.php?id=显号规则说明" class="set_link">《显号规则说明》</a></span>
									</h2>
								</dd>
							</dl>
						</div>
						</s:if>
						<div class="edit_field">
							<dl>
								<dt>国际通话业务</dt>
								<dd class="dd_nobg">
									<h2>
										<input disabled="disabled" <s:if test='app.callFr==1'>checked="checked"</s:if> type="checkbox" name="app.callFr" id="callFr" onclick="if(this.checked==1){this.value=1;}else{this.value=0;}" />
										开启
										<span class="tips">如果您勾选该项，则开通国际通话功能，应用内的用户可以拨打国际电话。</span>
									</h2>
								</dd>
							</dl>
						</div>
					</div>
				</div>
				<!--高级配置 eof-->

			</s:form>
		</div>
		<!--右侧main bof-->

	</div>
	<!--主体content eof-->

	<script type="text/javascript">
		$(function(){
			$("input:checkbox").each(function(){
				if($(this).attr("checked")){
					$(this).parent("h2").siblings(".set_box").slideDown();
					$(this).parents("dd").not(".dd_nobg").css("padding-bottom","10px");

					//展开隐藏项之后，右侧菜单高度
					var h1 = $(".main").height()+40;
					var h3 = $(".profile").height();
					$(".tab,.tab ul").css("height",h1-h3);


				}else{
					$(this).parent("h2").siblings(".set_box").slideUp();
					$(this).parents("dd").not(".dd_nobg").css("padding-bottom","0px");
				}
			});
			
			$("input:checkbox").click(function(){
				if($(this).attr("checked")){
					$(this).parent("h2").siblings(".set_box").slideDown();
					$(this).parents("dd").not(".dd_nobg").css("padding-bottom","10px");

					//点击展开隐藏项之后，右侧菜单高度
					var h1 = $(".main").height()+40;
					var h3 = $(".profile").height();
					setTimeout($(".tab,.tab ul").css("height",h1-h3),5);

				}else{
					$(this).parent("h2").siblings(".set_box").slideUp();
					$(this).parents("dd").not(".dd_nobg").css("padding-bottom","0px");
				}
			});
		});
		</script>
	<!--底部footer bof-->
	<%@include file="/page/foot.jsp" %>
	<!--底部footer eof-->
</body>
</html>
