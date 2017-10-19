<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="s"  uri="/struts-tags"%>
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
					<li><a href="#">用户控制台</a></li>
					<li class="active"><a href="#">用户控制台</a></li>
				</ul>
			</div>
			
			<s:form namespace="/app" action="add" method="post" theme="simple" name="appForm" id="appForm" class="app_form" enctype="multipart/form-data">
				<!--基础配置 bof-->
				<div class="edit_box edit_basic">
					<h1>基础配置</h1>
					<div class="edit_ctn">
						<div class="edit_field">
							<dl>
								<dt>应用名称</dt>
								<dd><input type="text" id="appName" name="app.appName"/><span class="error" id="name_error" style="display:none">错误：应用名称不合法</span></dd>
								<input type="hidden" value="" id="happName" />
								<dd><span class="tips">当前账户下要求唯一 ，不超过10个汉字或20个字母</span></dd>
							</dl>
						</div>

						<div class="edit_field">
							<dl>
								<dt>应用类型</dt>
								<dd class="select_type">
									<div class="select">
										<span class="select_txt">应用类型<i>&nbsp;</i></span>
										<s:if test="appKindList!=null" >
													<ul>
														 <s:iterator value="appKindList" var="k">
															<li value="${k.paramKey}" name="kind" id="${k.paramKey}" onclick="buildAppKind('${k.paramKey}')"><s:property value="#k.paramValue" /></li>
														 </s:iterator>
													</ul>
										</s:if>										
									</div>
									<span id="kind_error"  class="error" style="display:none"></span>
									<input type="hidden" name="app.appKind"  id="kind"/>
								</dd>						
							</dl>
						</div>

						<div class="edit_field">
							<dl>
								<dt>所属行业</dt>
								<dd class="select_type">
									<div class="select">
										<span>请选择行业<i>&nbsp;</i></span>
										<ul>
										<s:if test="paramsList!=null" >
											<s:iterator value="paramsList" var="p">
												<s:if test="#p.paramKey!=0">
													<li value="${p.paramKey}" onclick="setINdustry('${p.paramKey}')"><s:property value="#p.paramValue" /></li>
												</s:if>
											</s:iterator>
										</s:if>
										</ul>										
									</div>
									<span id="industry_error"  class="error" style="display:none"></span>
									<input type="hidden" name="app.industry" id="industry"/>									
								</dd>						
							</dl>
						</div>

						<div class="edit_field">
							<dl>
								<dt>服务器白名单</dt>
								<dd><textarea id="white"></textarea></dd>
								<input type="hidden" name="whiteListStr" id="whiteStr">
								<dd><span class="tips">允许域名或者IP地址，以英文输入法分号分隔，例如：www.google.com; 8.8.8.8 设定白名单地址后，快传服务器在识别该应用请求时将只接收白名单内服务器发送的请求，能有效提升帐号安全性。 如未设置默认不生效</span></dd>
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
						<div class="edit_field">
									<dl>
										<dt>回调地址功能配置</dt>
										<s:if test="cbfunList!=null">
										   <s:iterator value="cbfunList" var="cbfun" status="index">
											<dd>
												<h2>
													<input type="checkbox" name="cbfun" value="${cbfun.paramKey}"/>
													<s:property value="#cbfun.paramValue" />
													<span class="ask">
														<i class="ask_icon">&nbsp;</i>
														<em class="ask_ctn">
															<s:property value="#cbfun.paramTypeName" />
														</em>
													</span>										
												</h2>
												<div class="set_box" <s:if test="#index.index!=0">style="display:none;"</s:if>>
													<input type="text" value="" name="cbfunurl" placeholder=" 输入回调地址 http://" />
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
										<input type="checkbox" id="znyzck" />
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
												<input type="text" name="app.ckKey"  id="ckKey" placeholder="（必填）最大32位字母、数字大小写混合" />
												<span id="ckKey_error"  class="error" style="display:none"></span>
											</li>
										</ul>
										<ul>
											<li class="set_tit">
												回调地址
											</li>
											<li class="set_ctn">
												<input type="text" name="app.ckCallbackUrl"  id="ckCallbackUrl" placeholder=" 输入回调地址 http://" />
												<span id="ckCallbackUrl_error" class="error" style="display:none;"></span>
											</li>
										</ul>	
										<ul>
											<li class="set_tit">
												有效时间
											</li>
											<li class="set_ctn set_ctn1">
												<input type="text" name="app.ckEnddate" id="ckEnddate" />分钟<span class="tips">当短信下发时开始记时，系统默认20分钟</span>
												<span id="ckEnddate_error"  class="error" style="display:none"></span>
											</li>
										</ul>
										<ul>
											<li class="set_tit">
												<i class="star">&nbsp;</i>
												验证类型
											</li>
											<li class="set_ctn">
												<div class="select verify_select">
													<span class="select_txt">请选择<i>&nbsp;</i></span>
													<ul>
														<s:if test="cloudList!=null" >
															<s:iterator value="cloudList" var="p">
																<s:if test="#p.paramKey!=0">
																<li value="${p.paramKey}"><s:property value="#p.paramValue" /></li>
																</s:if>
															</s:iterator>
														</s:if>
													</ul>
													<input type="hidden" name="app.ckWay" id="ckWay"/>
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
										<input type="checkbox" id="znyzck"/>
										开启
										<span class="tips">IOS系统应用需要勾选</span>
									</h2>
									<div class="set_box set_box1">
										<ul>
											<li class="set_tit">
												IOS系统验证文件
											</li>
											<li class="set_ctn">
												<input type="file" class="file_1" name="validFile"  id="validFile" value=""/>
												<span id="ckKey_error"  class="error" style="display:none"></span>
											</li>
										</ul>
										<ul>
											<li class="set_tit">
												IOS系统appId
											</li>
											<li class="set_ctn">
												<input type="text" name="app.oauthAppId" value=""/>
												<span id="ckCallbackUrl_error" class="error" style="display:none;"></span>
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
								<input type="text" name="app.ckNum" id="ckNum" />次
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
										<input type="checkbox" value="app.isShowNbr" id="ckshownbr"  />
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
										<input type="checkbox" name="app.callFr" id="callFr" onclick="if(this.checked==1){this.value=1;}else{this.value=0;}" />
										开启
										<span class="tips">如果您勾选该项，则开通国际通话功能，应用内的用户可以拨打国际电话。</span>
									</h2>
								</dd>
							</dl>
						</div>
					</div>
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
			$("#validFile").change(function(){
				$("input[name='file_txt']").val($(this).val())
			});
			
			function appNameExist(appname,fun){
				$.ajax({
					url:"<%=path%>/app/appNameExist",
					type:"post",
					data:"appName="+appname,
					dataType: "text",
					success: function (data) {
			          if(data>0){
			        	  $("#name_error").fadeIn();
			              $("#name_error").text("应用名称已存在");
			  			  $("#appName").focus();
			  			  $("#happName").val(2);
			          }else{
			          	 $("#name_error").hide();
			          	 $("#happName").val(1);
			          	 if(fun==99){
			          		$("#subi").unbind("click");
			          		$("#subi").val("保存中...");
			          		$("#appForm").submit();
			          	 }
			          }
			        },
			        error: function (msg) {
			        	 $(".form_tips span").text("");
			        }
				});
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