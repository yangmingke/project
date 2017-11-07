<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="s"  uri="/struts-tags"%>
<%@ taglib prefix="u"  uri="/flypaas-tags" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html>
<html lang="zh-cn">
<head>
	<meta charset="UTF-8"><meta http-equiv="X-UA-Compatible" content="IE=edge" />
	<title>flypaas开放平台管理中心——创建应用</title>
	<%@include file="/page/resource.jsp"%>
	<script type="text/javascript" src="<%=path%>/page/js/app.js"></script>
	<style type="text/css">
		.set_ctn .file input.file_txt{
			width: 369px;
		}
	</style>
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
					<li class="crumbs1"><a href="<%=path %>/app/appManager">应用管理</a></li>
					<li><a href="<%=path %>/app/appManager">应用列表</a></li>
					<li class="active"><a href="javascript:void(0)">[${app.appName}]</a></li>
				</ul>
			</div>
			
			<div class="main_tab_tit">
				<ul>
					<li class="active">应用信息</li>
					<!-- <a href="javascript:void(0)" onclick="nbrManager()"><li>号码管理</li></a> -->
					<!-- <a href="javascript:void(0)" onclick="lyManager()"><li>录音管理</li></a> -->
				</ul>
			</div>
			
			<s:form namespace="/app" action="update" method="post" theme="simple" name="appForm" id="appForm" class="app_form" enctype="multipart/form-data">
				<!--基础配置 bof-->
				<div class="edit_box edit_basic">
					<h1>基础配置</h1>
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
								<dd><span class="txt">${app.appName }</span><input type="hidden" id="appName" value="${app.appName }"/></dd>
								<input type="hidden" value="${app.appSid }"  id="appSid" name="app.appSid" />
								<input type="hidden" value="${app.appName }"  id="appName" name="app.appName"  />
								<input type="hidden" value="${app.appName }"  id="compareName" name="compareName" />
								<input type="hidden" value="1"  id="happName" name="happName" />
							</dl>
						</div>

						<div class="edit_field">
							<dl>
								<dt>应用类型</dt>
								<dd>
									<div class="select">
										<span class="select_txt" id="span_appKind">应用类型<i>&nbsp;</i></span>
										<s:if test="appKindList!=null" >
													<ul>
														 <s:iterator value="appKindList" var="k">
															<li value="${k.paramKey}" <s:if test="app.appKind==#k.paramKey">class="selected"</s:if> name="kind" id="${k.paramKey}" onclick="buildAppKind('${k.paramKey}')" ><s:property value="#k.paramValue" /></li>
															<s:if test="app.appKind==#k.paramKey">
																<script type="text/javascript">
																var appKind = "${app.appKind}";
																var key = "${k.paramKey}";
																var value="${k.paramValue}";
																if(appKind==key){
																	$("#span_appKind").html(value+"<i>&nbsp;</i>");
																}
															</script>
															</s:if>
														 </s:iterator>
													</ul>
										</s:if>
										<span id="kind_error"  class="error" style="display:none"></span>
									</div>
									<input type="hidden" name="app.appKind" value="${app.appKind }"  id="kind"/>
								</dd>						
							</dl>
						</div>

						<div class="edit_field">
							<dl>
								<dt>所属行业</dt>
								<dd>
									<div class="select">
										<span class="select_txt" id="span_industry">请选择行业<i>&nbsp;</i></span>
										<ul>
										<s:if test="paramsList!=null" >
											<s:iterator value="paramsList" var="p">
												<s:if test="#p.paramKey!=0">
													<li <s:if test="app.industry==#p.paramKey">class="selected"</s:if> value="${p.paramKey}"><s:property value="#p.paramValue" /></li>
													<s:if test="app.industry==#p.paramKey">
														<script type="text/javascript">
															var appKind = "${app.industry}";
															var key = "${p.paramKey}";
															var value="${p.paramValue}";
															if(appKind==key){
																$("#span_industry").html(value+"<i>&nbsp;</i>");
															}
														</script>
													</s:if>
												</s:if>
											</s:iterator>
										</s:if>
										</ul>
										<input type="hidden" name="app.industry" value="${app.industry }" id="industry"/>
										<span id="industry_error"  class="error" style="display:none"></span>
									</div>
								</dd>						
							</dl>
						</div>

						<div class="edit_field">
							<dl>
								<dt>服务器白名单</dt>
								<dd><textarea id="white">${whiteList.whiteAddress }</textarea></dd>
								<input type="hidden" name="whiteListStr" id="whiteStr"/>
								<dd><span class="tips">允许域名或者IP地址，以英文输入法分号分隔，例如：app.google.com; 8.8.8.8 设定白名单地址后，快传服务器在识别该应用请求时将只接收白名单内服务器发送的请求，能有效提升帐号安全性。 如未设置默认不生效</span></dd>
								<dd><span id="wl_error"  class="error" style="display:none"></span></dd>
							</dl>
						</div>
					</div>
				</div>
				<!--基础配置 eof-->

				<!--高级配置 bof-->
				<div class="edit_box edit_advanced">
					<h1>高级配置</h1>
					<div class="edit_ctn">
						<%-- <div class="edit_field">
							<dl>
								<dt>路由策略</dt>
								<dd class="dd_nobg">
									<div class="select">
										<span class="select_txt" id="route_policy">质量优先<i>&nbsp;</i></span>
										<ul>
										<s:if test="routePolicyList!=null" >
											<s:iterator value="routePolicyList" var="p">
												<s:if test="#p.paramKey!=0">
													<li <s:if test="app.routePolicy==#p.paramKey">class="selected"</s:if> value="${p.paramKey}"><s:property value="#p.paramValue" /></li>
													<s:if test="app.routePolicy==#p.paramKey">
														<script type="text/javascript">
															var routePolicy = "${app.routePolicy}";
															var key = "${p.paramKey}";
															var value="${p.paramValue}";
															if(routePolicy==key){
																$("#route_policy").html(value+"<i>&nbsp;</i>");
															}
														</script>
													</s:if>
												</s:if>
											</s:iterator>
										</s:if>
										</ul>
										<input type="hidden" name="app.routePolicy" value="${app.routePolicy }" id="routePolicy"/>
										<span id="routePolicy_error"  class="error" style="display:none"></span>
									</div>
								</dd>						
							</dl>
						</div>
						<div class="edit_field">
							<dl>
								<dt>会话路由数</dt>
								<dd class="dd_nobg">
									<div class="select">
										<span class="select_txt" id="route_num">1条<i>&nbsp;</i></span>
										<ul>
										<s:if test="routeNumList!=null" >
											<s:iterator value="routeNumList" var="p">
												<s:if test="#p.paramKey!=0">
													<li <s:if test="app.routeNum==#p.paramKey">class="selected"</s:if> value="${p.paramKey}"><s:property value="#p.paramValue" /></li>
													<s:if test="app.routeNum==#p.paramKey">
														<script type="text/javascript">
															var routeNum = "${app.routeNum}";
															var key = "${p.paramKey}";
															var value="${p.paramValue}";
															if(routeNum==key){
																$("#route_num").html(value+"<i>&nbsp;</i>");
															}
														</script>
													</s:if>
												</s:if>
											</s:iterator>
										</s:if>
										</ul>
										<input type="hidden" name="app.routeNum" value="${app.routeNum}" id="routeNum"/>
										<span id="routeNum_error"  class="error" style="display:none"></span>
									</div>
								</dd>						
							</dl>
						</div>
						<div class="edit_field edit_verify">
							<dl>
								<dt>节点最高单价</dt>
								<dd>
									<h2>
										<input type="checkbox" id="maxPriceCk" <s:if test="app.nodeMaxPrice!=null && app.nodeMaxPrice > 0">checked="checked"</s:if>/>
										开启
										<span class="tips">限制节点单跳最高单价（分/GB），默认无限制</span>
									</h2>
									<div class="set_box set_box1">
										<ul>
											<li class="set_tit">
												节点最高单价
											</li>
											<li class="set_ctn">
												<input type="text" name="app.nodeMaxPrice" id="nodeMaxPrice" 
												onkeyup="if(this.value.length==1){this.value=this.value.replace(/[^1-9]/g,'')}else{this.value=this.value.replace(/\D/g,'')}" 
												onafterpaste="if(this.value.length==1){this.value=this.value.replace(/[^1-9]/g,'')}else{this.value=this.value.replace(/\D/g,'')}"
												<s:if test="app.nodeMaxPrice!=null && app.nodeMaxPrice > 0"> value="${app.nodeMaxPrice }" </s:if>/>
												<span>（分/GB）</span>
												<span id="nodeMaxPrice_error" class="error" style="display:none;"></span>
											</li>
										</ul>	
									</div>
									<div class="clear"></div>
								</dd>
							</dl>
						</div>
						<div class="edit_field edit_verify">
							<dl>
								<dt>路由最大跳数</dt>
								<dd>
									<h2>
										<input type="checkbox" id="maxHopNumCk" <s:if test="app.maxHopNum!=null && app.maxHopNum > 0">checked="checked"</s:if>/>
										开启
										<span class="tips">限制路由最大跳数，默认无限制</span>
									</h2>
									<div class="set_box set_box1">
										<ul>
											<li class="set_tit">
												路由最大跳数
											</li>
											<li class="set_ctn">
												<input type="text" name="app.maxHopNum" id="maxHopNum" 
												onkeyup="if(this.value.length==1){this.value=this.value.replace(/[^1-9]/g,'')}else{this.value=this.value.replace(/\D/g,'')}" 
												onafterpaste="if(this.value.length==1){this.value=this.value.replace(/[^1-9]/g,'')}else{this.value=this.value.replace(/\D/g,'')}"
												<s:if test="app.maxHopNum!=null && app.maxHopNum > 0"> value="${app.maxHopNum }" </s:if>/>
												<span id="maxHopNum_error" class="error" style="display:none;"></span>
											</li>
										</ul>	
									</div>
									<div class="clear"></div>
								</dd>
							</dl>
						</div> --%>
						<div class="edit_field">
									<dl>
										<dt>回调地址功能配置</dt>
										<s:if test="cbfunList!=null">
										   <s:iterator value="cbfunList" var="cbfun" status="index">
											<dd>
												<h2>
													<input type="checkbox" name="cbfun" <s:if test="#cbfun.tcallBack!=null">checked="checked"</s:if> value="${cbfun.paramKey}"/>
													<s:property value="#cbfun.paramValue" />
													<span class="ask">
														<i class="ask_icon">&nbsp;</i>
														<em class="ask_ctn">
															<s:property value="#cbfun.paramTypeName" />
														</em>
													</span>										
												</h2>
												<div class="set_box" <s:if test="#index.index!=0">style="display:none;"</s:if>>
													<input type="text"  onblur="isCkBox(this)" <s:if test="#cbfun.tcallBack!=null">value="${cbfun.tcallBack.callAddress }"</s:if> name="cbfunurl" placeholder=" 输入回调地址 http://" />
													<div class="select">
														<span id="zcbfunmethod${index.index }" class="select_txt">POST<i>&nbsp;</i></span>
														<ul>
															<li onclick="methodOpt('Post','${index.index }')">POST</li>
														</ul>
													</div>
												</div>
											</dd>
										</s:iterator>
									</s:if>
									<dd class="error"><span id="cb_error"  class="error" style="display:none"></span></dd>
									 <input type="hidden" name="cbfunStr" id="cbfunStr"/>
					                 <input type="hidden" name="cbfunurlStr" id="cbfunurlStr"/>
					                 <input type="hidden" name="cbfunmethodStr" id="cbfunmethodStr"/>
					                 <input type="hidden" name="cb" id="cb" value="1"/>					                 
									</dl>
						</div>

						<div class="edit_field edit_verify">
							<dl>
								<dt>智能验证</dt>
								<dd>
									<h2>
										<input type="checkbox" id="znyzck" <s:if test="app.ckKey!=''&&app.ckKey!=null">checked="checked"</s:if>/>
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
												<input type="text" name="app.ckKey" value="${app.ckKey }" id="ckKey" placeholder="（必填）最大32位字母、数字大小写混合" />
												<span id="ckKey_error"  class="error" style="display:none"></span>
											</li>
										</ul>
										<ul>
											<li class="set_tit">
												回调地址
											</li>
											<li class="set_ctn">
												<input type="text" name="app.ckCallbackUrl" value="${app.ckCallbackUrl }"  id="ckCallbackUrl" placeholder=" 输入回调地址 http://" />
												<span id="ckCallbackUrl_error" class="error" style="display:none;"></span>
											</li>
										</ul>	
										<ul>
											<li class="set_tit">
												有效时间
											</li>
											<li class="set_ctn set_ctn1">
												<input type="text" name="app.ckEnddate" value="<s:if test='app.ckEnddate!=0'>${app.ckEnddate }</s:if>" id="ckEnddate" />分钟<span class="tips">当短信下发时开始记时，系统默认20分钟</span>
												<span id="ckEnddate_error"  class="error" style="display:none"></span>
											</li>
										</ul>
<%-- 										<ul> --%>
<!-- 											<li class="set_tit"> -->
<!-- 												同号码最大值 -->
<!-- 											</li> -->
<!-- 											<li class="set_ctn set_ctn1"> -->
<%-- 												<input type="text" name="app.ckNum" value="<s:if test='app.ckNum!=0'>${app.ckNum }</s:if>" id="ckNum" <s:if test="app.ckNum>0">value="${app.ckNum }"</s:if>/>次<span class="tips">同一号码一天内最大允许接收验证码次数</span> --%>
<%-- 												<span id="ckNum_error"  class="error" style="display:none"></span> --%>
<!-- 											</li> -->
<%-- 										</ul> --%>
										<ul>
											<li class="set_tit">
												<i class="star">&nbsp;</i>
												验证类型
											</li>
											<li class="set_ctn">
												<div class="select verify_select">
													<span class="select_txt" id="ckWaySpan">请选择<i>&nbsp;</i></span>
													<ul>
														<s:if test="cloudList!=null" >
															<s:iterator value="cloudList" var="p">
																<s:if test="#p.paramKey!=0">
																<s:if test="app.ckWay==#p.paramKey">
																<script type="text/javascript">
																		$("#ckWaySpan").html("${p.paramValue}"+"<i>&nbsp;</i>");
																</script>
																</s:if>
																<s:if test="#p.paramKey!=0">
																	<li value="${p.paramKey}"><s:property value="#p.paramValue" /></li>
																</s:if>
																</s:if>
															</s:iterator>
														</s:if>
													</ul>
													<input type="hidden" name="app.ckWay" value="${app.ckWay }" id="ckWay"/>
												</div>
												<span id="ckWay_error"  class="error" style="display:none"></span>
											</li>
										</ul>								
									</div>
									<div class="clear"></div>
								</dd>
							</dl>
						</div>
						
						<div class="edit_field edit_verify">
							<dl>
								<dt>IOS系统验证</dt>
								<dd>
									<h2>
										<input type="checkbox" id="IOSck"/>
										开启
										<span class="tips">IOS系统应用需要勾选,请谨慎选择。修改后，应用需要<font color="#FF0000">重新申请上线</font> </span>
									</h2>
									<div class="set_box set_box1">
										<ul>
											<li class="set_tit">
												IOS系统验证文件
											</li>
											<li class="set_ctn">
												<input type="file" class="file_1" name="validFile"  id="validFile" value=""/>
												<span id="validFile_error"  class="error" style="display:none"></span>
											</li>
										</ul>
										<ul>
											<li class="set_tit">
												IOS系统appId
											</li>
											<li class="set_ctn">
												<input type="text" name="app.oauthAppId" id="oauthAppId" value=""/>
												<span id="oauthAppId_error" class="error" style="display:none;"></span>
											</li>
										</ul>	
									</div>
									<div class="clear"></div>
								</dd>
							</dl>
						</div>
						
						<%--<div class="edit_field">
							<dl>
								<dt>身份验证最大值</dt>
								<dd class="dd_nobg">
								<input type="text" name="app.ckNum" value="<s:if test='app.ckNum!=0'>${app.ckNum }</s:if>" id="ckNum" <s:if test="app.ckNum>0">value="${app.ckNum }"</s:if>/>次
								<span class="tips">配置应用下同一号码每天可接受短信，语音验证码最大次数</span>
								<span id="ckNum_error"  class="error" style="display:none"></span>
								</dd>
							</dl>
						</div>
						
						<s:if test="user.userType==2">
						<div class="edit_field">
							<dl>
								<dt>显号服务</dt>
								<dd class="dd_nobg">
									<h2>
										<input <s:if test="app.isShowNbr==1">checked="checked"</s:if> type="checkbox" id="ckshownbr"  />
										<input type="hidden" name="app.isShowNbr" id="shownbr" value="${app.isShowNbr }"/>
										开启
										<span class="tips">如您勾选该项，将默认允许应用内用户之间通话使用显号模式，同时您还可以根据应用的需求通过接口进行针对个别用户特殊配置。如需查看详细显号规则，请点击<a href="http://docs.flypaas.com/doku.php?id=显号规则说明" class="set_link">《显号规则说明》</a></span>
									</h2>
								</dd>
							</dl>
						</div>
						</s:if> --%>
						<%-- <div class="edit_field">
							<dl>
								<dt>国际通话业务</dt>
								<dd class="dd_nobg">
									<h2>
										<input <s:if test='app.callFr==1'>checked="checked"</s:if> type="checkbox"  onclick="if(this.checked==1){$('#callFr').val(1);}else{$('#callFr').val(0);}" />
										开启
										<span class="tips">如果您勾选该项，则开通国际通话功能，应用内的用户可以拨打国际电话。</span>
										<input type="hidden" name="app.callFr" id="callFr" value="${app.callFr }"/>
									</h2>
								</dd>
							</dl>
						</div> --%>
					</div>
					<input type="hidden" name="znyzck" id="znyzckid" value="<s:if test='app.ckKey!=null&&app.ckKey!=\'\''><u:des3 value='1'/></s:if><s:else><u:des3 value='2'/></s:else>"/>
				</div>
				<!--高级配置 eof-->

				<!--编辑提交按钮 bof-->
				<div class="edit_btn">
					<input type="button" value="保存" class="confirm_btn" id="subi"/>
					<input type="button" value="取消" class="cancel_btn" onclick="history.go(-1)"/>
				</div>
				<!--编辑提交按钮 bof-->
			</s:form>
		</div>

		<!--展开隐藏列表项 bof-->
		<script type="text/javascript">
		$(function(){
			$("#validFile").change(function(){
				$("input[name='file_txt']").val($(this).val())
			});
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
					setTimeout($(".tab,.tab ul").css("height",h1-h3),50);

				}else{
					$(this).parent("h2").siblings(".set_box").slideUp();
					$(this).parents("dd").not(".dd_nobg").css("padding-bottom","0px");
				}
			});
			
			$('#maxPriceCk').click(function(){
				if($(this).attr("checked")==undefined){
					$("#nodeMaxPrice").val("");
				}
			});
			
			$('#maxHopNumCk').click(function(){
				if($(this).attr("checked")==undefined){
					$("#maxHopNum").val("");
				}
			});
			
			$('#routeNumCk').click(function(){
				if($(this).attr("checked")==undefined){
					$("#routeNum").val("");
				}
			});
			
			$("#znyzck").click(function(){
				if($(this).attr("checked")=="checked"){
					$("#znyzckid").val("<u:des3 value='1'/>");
				}else{
					$("#znyzckid").val("<u:des3 value='2'/>");
				}
			});
		});
		
		function appNameExist(appname,fun){
			var compname = $("#compareName").val();
			if(appname==compname){
				$("#name_error").hide();
				 if(fun==99){
					$("#appForm").submit();
				 }
				return;
			}
		}
		function isCkBox(obj){
			var value = $(obj).val();
			if(value!=""){
				$(obj).parents('.callback').siblings('dl').find('span').addClass('checked');
				$(obj).parents('.callback').siblings('dl').find('span').find('input').attr('checked','checked');
			}else{
				$(obj).parents('.callback').siblings('dl').find('span').removeClass('checked');
				$(obj).parents('.callback').siblings('dl').find('span').find('input').removeAttr('checked');
			}
		}
		function nbrManager(){
			var userType = "${user.userType}";
			var oauthStatus = "${user.oauthStatus}";
			var st = "${app.status}";
			var isShowNbr = "${app.isShowNbr}";
			if(userType==2){
				if(oauthStatus=="3"){
					if(st=="1"){
						if(isShowNbr==1){
							location.href="<%=path %>/app/showNbrList?appSid=<u:des3 value='${app.appSid}' />";
						}else{
							popupBox('提示','应用未开启显号功能','',2);
						}
					}else{
						popupBox('提示','应用上线了，才能进行号码管理','',2);
					}
				}else{
					popupBox('提示','请先完成资质认证','',2);
				}
			}else{
				popupBox('提示','该功能暂时只针对企业客户开放，请先完成企业认证。','',2);
			}
			
		}
		function lyManager(){
			location.href="<%=path %>/app/lyList?appSid=<u:des3 value='${app.appSid}' />";
		}
		</script>
		<!--展开隐藏列表项 eof-->

		<!--右侧main bof-->

	</div>
	<!--主体content eof-->


	<!--底部footer bof-->
	<%@include file="/page/foot.jsp" %>
	<!--底部footer eof-->
</body>
</html>