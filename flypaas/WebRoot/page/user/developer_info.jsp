<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%@ taglib prefix="s"  uri="/struts-tags"%>
<!DOCTYPE html>
<html lang="zh-cn">
<head>
	<meta charset="UTF-8"><meta http-equiv="X-UA-Compatible" content="IE=edge" />
	<title>flypaas开放平台管理中心——用户信息</title>
	<%@include file="/page/resource.jsp" %>
</head>

<script type="text/javascript">

	$(function(){
		//绑定手机表单验证
		 disabl('smscodeinput','voicecodeinput');
		$("#ph_confirm_btn").click(function(){
			if(frm.phone()/* &&frm.phoneCode() */){
				var phone = $("#phone").val();
				$.ajax({
					url:"<%=path %>/user/updateMobile",
					type:"post",
					data:"user.mobile="+phone,
					dataType: "text",
					success: function (data) {
			        	location.href=location.href;
			        }
				});
			}
		});
		//修改密码
		$("#pwd_confirm_btn").click(function(){
			 
		 	  if(pwdFrm.currentPwd()){
				  var cpwd = $("#current_pwd").val();
				  cpwd = encodeURIComponent(cpwd);
				  $.ajax({
						url:"<%=path%>/user/thePwdIsRight",
						type:"post",
						data:"password="+cpwd,
						dataType: "text",
						success: function (data) {
			            if(data=="0"){
			          	  $("#current_pwd_error").show();
			    		  $("#current_pwd").focus();
			    		  return;
			            }
			            
		          	    $("#current_pwd_error").hide();
		          	    if(pwdFrm.newPwd()&&pwdFrm.confirmPwd()){
		          			ajaxUpdatePwd(cpwd,$("#new_pwd").val());
			            }
			          }
				  });
		 	  }
		});
		//页面加载按钮操作
		$("#dev_cancel_btn").click(function(){
			location.href=location.href;
		});
		$("li").click(function(){
			actiBtn();
		});
		$("#address").click(function(){
			actiBtn();
		});
		
		$("#developForm").submit(function(){
			if(developForm.addres()){
				return true;
			}
			return false;
		});
		
	});
	
	function actiBtn(){
		$("#dev_confirm_btn").removeAttr("disabled");
		$("#dev_confirm_btn").removeClass("grey_btn");
		$("#dev_confirm_btn").addClass("confirm_btn");
	}
	 /** 省市联动  **/
	 function ckLi(id,value){
	 	$("#cityId").val(id);
	 	$("#city").html(value+"<i>&nbsp;</i>");
	 }
	 function findCity(provinceId,cityId){
	 	$("#provinceId").val(provinceId);
  		$.ajax({
				url:"<%=path%>/user/getCity",
				type:"post",
				data:"provinceId="+provinceId,
				dataType: "text",
				success: function (data) {
		          if(data!=""){
		        	 var jsonStr = eval('('+data +')');
		        	 var ctStr ="" ;
	        	  	 $("#city").html("");
	        	  	 $("#cityList").empty();
		        	  for(var i =0 ;i<jsonStr.length;i++){
		        	  	 if(cityId==null){
		        	  	 	ckLi(jsonStr[0].id,jsonStr[0].name);
		        	  	 }else{
			        	  	 var id= jsonStr[i].id;
			        	  	 if(id==cityId){
			        	  	 	$("#city").html(jsonStr[i].name);
			        	  	 }
		        	  	 }
		        	  	 if(ctStr==""){
		        	  	 	ctStr = "<li onclick='ckLi(\""+jsonStr[i].id+"\",\""+jsonStr[i].name+"\")' value='"+jsonStr[i].id+"'>"+jsonStr[i].name+"</li>";
		        	  	 }else{
			        	  	 ctStr += "<li onclick='ckLi(\""+jsonStr[i].id+"\",\""+jsonStr[i].name+"\")' value='"+jsonStr[i].id+"'>"+jsonStr[i].name+"</li>";
		        	  	 }
		        	  }
		        	 $("#cityList").append(ctStr);
		          }
		          $("li").click(function(){
						actiBtn();
				  });
		        },
		        error: function (msg) {
		        }
			});
 	 }
	 /** 省市联动  **/
	 
	 
	 /** 修改密码  **/
	 
	 var pwdFrm = {
			 currentPwd:function(){
				 var current_pwd = $("#current_pwd").val();
				 if(current_pwd==""){
					 $("#current_pwd_error").show();
			         $("#current_pwd").focus();
			         return false;
				 }
				 $("#current_pwd_error").hide();
				 return true;
			 },
			 newPwd:function(){
				 var new_pwd =$("#new_pwd").val();
				 if(new_pwd==""){
					 $("#new_pwd_error").show();
			         $("#new_pwd").focus();
			         return false;
				 }
				 if(!vPwd(new_pwd)){
					 $("#new_pwd_error").show();
			         $("#new_pwd").focus();
			         return false;
				 }
				 $("#new_pwd_error").hide();
				 return true;
			 },
			 confirmPwd:function(){
				 var confirm_pwd=$("#confirm_pwd").val();
				 var new_pwd =$("#new_pwd").val();
				 if(confirm_pwd==""){
					 $("#confirm_pwd_error").show();
			         $("#confirm_pwd").focus();
			         return false;
				 }
				 if(!vPwd(confirm_pwd)){
					 $("#confirm_pwd_error").show();
			         $("#confirm_pwd").focus();
			         return false;
				 }
				 if(new_pwd!=confirm_pwd){
					 $("#confirm_pwd_error").show();
			         $("#confirm_pwd").focus();
			         return false;
				 }
				 $("#confirm_pwd_error").hide();
				 return true;
			 }
	 };
	 
	 
	 var ajaxUpdatePwd = function(current_pwd,pwd){
		 $.ajax({
				url:"<%=path%>/user/correctPwd",
				type:"post",
				data:"current_pwd="+current_pwd+"&user.password="+pwd,
				dataType: "text",
				success: function (data) {
					location.href=location.href;
	          	}
			}); 
	 };
	 /** 修改密码 **/
	 
	 var developForm = {
			 addres:function(){
				 var address = $("#address").val();
				 if(verifyHtml(address)){
					 $("#address_error").show();
					 return false;
				 }
				 $("#address_error").hide();
				 return true;
			 }
	 }
</script>
<body id="04-1">
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
					<li><a href="#">用户信息</a></li>
					<li class="active"><a href="#">基础信息</a></li>
				</ul>
			</div>
			<s:if test="resultCode=='success'">
				<div class="prompt_box success">
					<p>保存成功！</p>
				</div>
			</s:if>

			 <form action="<%=path %>/user/modifyDevelop" method="post" name="developForm" id="developForm">
				<!--基础信息 bof-->
				<div class="edit_box edit_basic developer_info">
					<h1>基础信息</h1>
					<div class="edit_ctn">
						<div class="edit_field">
							<dl class="dl1">
								<dt>用户账号</dt>
								<dd><span class="txt">${user.email }</span></dd>
							</dl>
							<dl>
								<dt>用户认证：</dt>
								<dd>
									<span class="txt">
									<s:if test="user.userType==1">
										<s:if test="user.oauthStatus==3">
			                				个人用户
			                			</s:if>
			                			<s:else>
			                				注册用户
			                			</s:else>
										<s:if test="user.oauthStatus==null">
											<a href="<%=path %>/user/oAuthDispather" class="do">立即认证</a>
										</s:if>
										<s:if test="user.oauthStatus==3">
											<a href="<%=path %>/user/oAuthDispather" class="upgrade">升级企业认证</a>
										</s:if>
									</s:if>
									<s:elseif test="user.userType==2">
										<s:if test="user.oauthStatus==3">
	 										企业用户
	 									</s:if>
	 									<s:else>
			                				个人用户
			                			</s:else>
									</s:elseif>
									</span>
								</dd>
							</dl>
						</div>
						<div class="edit_field">
							<dl>
								<dt>昵称</dt>
								<dd><span class="txt">${user.userName }</span></dd>
							</dl>
						</div>
						<div class="edit_field">
							<dl>
								<dt>注册手机号</dt>
								<dd><span class="txt">${user.mobile }</span><a href="javascript:;" class="blue float_link">变更手机号</a></dd>
							</dl>
						</div>

						<div class="edit_field">
							<dl>
								<dt>联系地址</dt>
								<dd>
									<div class="select select_province">
										<span id="province">省/直辖市<i>&nbsp;</i></span>
										<ul>
											<s:iterator value="provinceList" var="p" status="s">
						                 	 <s:if test="#p.id==user.province">
						                 	 	<script type="text/javascript"> 
						                 	 		$("#province").html("${p.name}"+"<i>&nbsp;</i>");
						                 	 		var provinceId = "${user.province}" ;
						                 	 		var cityId = "${user.city}" ;
						                 	 		findCity(provinceId,cityId);
						                 	 	</script>
						                 	 </s:if>
						                 	 <li value="${p.id}" onclick="findCity(this.value)">${p.name}</li>
						                 	</s:iterator>
										</ul>
									</div>
									<div class="select select_city">
										<span id="city">市<i>&nbsp;</i></span>
										<ul id="cityList">
										</ul>
									</div>
								</dd>
								<dd>
								<input type="text" name="user.address" id="address" value="${user.address }" />
								<span class="error" id="address_error" style="display:none">错误：地址非法</span>
								<input type="hidden" id="cityId" value="${user.city}" name="user.city" />
								<input type="hidden" id="provinceId" value="${user.province}" name="user.province"/>
								</dd>						
							</dl>
						</div>

						<div class="edit_field">
							<dl>
								<dt>登录密码</dt>
								<dd><span class="txt">*******</span><a href="javascript:void(0)" class="correct_pwd float_link1">修改密码</a></dd>
							</dl>
						</div>
					</div>
				</div>
				<!--基础信息 eof-->
				

				<!--编辑提交按钮 bof-->
				<div class="edit_btn">
					<input type="submit" value="保存" class="confirm_btn  grey_btn" id="dev_confirm_btn" disabled="disabled"/>
					<input type="button" value="取消" class="cancel_btn" id="dev_cancel_btn"/>
				</div>
				<!--编辑提交按钮 bof-->
			</form>
		</div>
		<!--右侧main bof-->

	</div>
	 <!--弹层（添加号码） bof-->
	  <div class="background_box">&nbsp;</div>
	  <div class="float_box addnum_box">
	    <div class="float_tit">
	      <h1>添加号码</h1>
	    </div>
	    <div class="float_ctn">
	      <div class="float_field">
	        <dl>
	          <dt>输入真实号码</dt>
	          <dd><input type="text" id="phone" name="user.mobile"/><span class="error" id="phone_error" style="display:none">格式错误</span></dd>
	          <dd><span class="tips">输入格式例如：13800138000  或 075523456789</span></dd>
	          <%-- <dd class="relate_link"><input type="button" id="smscodeinput" onclick="smsCode('smscodeinput','voicecodeinput')" value="短信验证" /><span class="tips">或</span><input type="button" onclick="voiceCode('voicecodeinput','smscodeinput')" id="voicecodeinput" value="语音验证" /></dd> --%>
	        </dl>
	      </div>
	      <div class="float_field">
	        <%-- <dl>
	          <dt>输入获取的验证码</dt>
	          <dd><input type="text" id="inputmovecode"/><input type="hidden" id="movecode"  /><span class="error" style="display:none" id="move_phone_code_error">正确</span></dd>
	        </dl> --%>
	      </div>
	      <div class="float_btn">
	        <input type="button" value="取消" class="cancel_btn" />
	        <input type="button" value="确定" class="confirm_btn" id="ph_confirm_btn"/>
	      </div>
	    </div>
	  </div>
	  <!--弹层（添加号码） eof-->

	  <!--弹层（修改密码） bof-->
	  <div class="float_box1 addnum_box">
	    <div class="float_tit">
	      <h1>修改密码</h1>
	    </div>
	    <div class="float_ctn">
	      <div class="float_field">
	        <dl>
	          <dt>当前密码</dt>
	          <dd>
	          <input type="password" id="current_pwd"/><span id="current_pwd_error" style="display:none" class="error">错误：密码错误</span>
	          </dd>
	        </dl>
	      </div>
	      <div class="float_field">
	        <dl>
	          <dt>新密码</dt>
	          <dd>
	          <input type="password" name="user.password" id="new_pwd"/><span id="new_pwd_error" class="error" style="display:none">错误：密码不合法,8-16位数字字母组合</span>
	          </dd>	          
	          <dd><span class="tips">请尽量使用更为复杂的混合密码</span></dd>
	        </dl>
	      </div>
	      <div class="float_field">
	        <dl>
	          <dt>确认新密码</dt>
	          <dd>
	          <input type="password" id="confirm_pwd"/><span id="confirm_pwd_error" class="error" style="display:none">错误：密码不合法,8-16位数字字母组合</span>
	          </dd>	          
	        </dl>
	      </div>
	      <div class="float_btn">
	        <input type="hidden" value="${param.fr}" name="fr" />
	        <input type="button" value="取消" class="cancel_btn" />
	        <input type="submit" value="确定" class="confirm_btn" id="pwd_confirm_btn"/>
	      </div>
	    </div>
	  </div>
	  <!--弹层（修改密码） eof-->
	<!--主体content eof-->


	<!--底部footer bof-->
	<%@include file="/page/foot.jsp" %>
	<!--底部footer eof-->
	
</body>
</html>