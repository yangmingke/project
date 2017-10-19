<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="s"  uri="/struts-tags"%>
<%@ taglib prefix="u" uri="/flypaas-tags" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html>
<html lang="zh-cn">
<head>
	<meta charset="UTF-8"><meta http-equiv="X-UA-Compatible" content="IE=edge" />
	<title>flypaas开放平台管理中心——短信模板</title>
	<%@include file="/page/resource.jsp"%>
	<script type="text/javascript">
		function checkAddItem(_this){
			if("1"==$("input[name='opt']").val()){
				$(".add_items").slideDown();
			}else{
				$(".add_items").slideUp();
			}
		};
		function checkOpt(_this){
			if(isEmptyObject($("#opt").val())){
				$("#opt_error").html("选择模板类型");
				return false;
			}else{
				$("#opt_error").html("");
			}
			return true;
		}
		function checkAppSid(_this){
			if(isEmptyObject($("#smsTemplate\\.appSid").val())){
				$("#appSid_error").html("选择应用");
				return false;
			}else{
				$("#appSid_error").html("");
			}
			return true;
		}
		function checkTitle(_this){
			var title = $("#title").val();
			if(isEmptyObject(title)){
				$("#title_error").html("请填写标题");
				return false;
			}else{
				if(getByteLen(title) > 20){
					$("#title_error").html("标题过长");
					return false;
				}else{
					$("#title_error").html("");
				}
			}
			return true;
		}
		function checkSign(_this){
			var sign = $("#sign").val();
			if(isEmptyObject(sign)){
				$("#sign_error").html("请填写短信签名");
				return false;
			}else{
				if(getByteLen(sign) > 20){
					$("#sign_error").html("短信签名过长");
					return false;
				}else{
					$("#sign_error").html("");
				}
			}
			return true;
		}
		function checkContent(_this){
			if(isEmptyObject($("#content").val())){
				$("#content_error").html("请输入模板内容");
				return false;
			}else{
				$("#content_error").html("");
			}
			return true;
		}		
		function checkLen(_this){
			if(isEmptyObject($("#smsTemplate\\.len").val())){
				$("#len_error").html("选择长度");
				return false;
			}else{
				$("#len_error").html("");
			}
			return true;
		}
		function checkType(_this){
			if(isEmptyObject($("#smsTemplate\\.type").val())){
				$("#type_error").html("选择云验证类型");
				return false;
			}else{
				$("#type_error").html("");
			}
			return true;
		}
		function checkRule(_this){
			if(isEmptyObject($("input[name='smsTemplate_rule']:checked").val())){
				$("#rule_error").html("选择云验证规则");
				return false;
			}else{
				$("#rule_error").html("");
			}
			return true;
		}
		function checkEditForm(_this){
			var isOk = true;
			if(!checkOpt(_this)){
				isOk = false;
			};
			if(!checkAppSid(_this)){
				isOk = false;
			};
			if(!checkTitle(_this)){
				isOk = false;
			};
			if(!checkSign(_this)){
				isOk = false;
			}
			if(!checkContent(_this)){
				isOk = false;
			};
			if('1' == $("#opt").val()){
				if(!checkLen(_this)){
					isOk = false;
				};
				if(!checkType(_this)){
					isOk = false;
				};
				if(!checkRule(_this)){
					isOk = false;
				};
			}
			var rule = 0;
			$("input[name='smsTemplate_rule']:checked").each(function(i,o){
				rule += parseInt($(o).val());
			});
			$("#smsTemplate\\.rule").val(rule);
			 return isOk;	
		}
	</script>
</head>
<body id="02-3">
	<!--头部header bof-->
	<%@include file="/page/head.jsp" %>
	<!--头部header eof-->
	<!--主体content bof-->
	<div class="content">
		<!--侧边side bof-->
		<%@include file="/page/left.jsp" %>
		<!--侧边side bof-->
		<!--右侧main bof-->
		<div class="main" style="overflow:visible;">
			<div class="breadcrumbs">
				<ul>
					<li><a href="#">应用管理</a></li>
					<li class="active"><a href="#">短信管理</a></li>
				</ul>
			</div>
			<div class="main_tab_tit">
				<ul>
					<li class="active">短信模板</li>
					<li onclick="href2url('<%=path%>/app/smsNum/query');">短信号码</li>
				</ul>
			</div>
			<!--说明state_box bof-->
			<div class="state_box">
				<h1>说明</h1>
				<p>1、每个短信模板只允许关联一个应用</p>
				<p>2、短信模板通过审核后才允许商用下发</p>
				<p>3、新增短信模板都需重新审核</p>
				<p>4、模板短信使用说明请阅读「开发文档」内说明</p>
			</div>
			<!--说明state_box eof-->
			<form method="post" id="editForm" onsubmit=" return checkEditForm(this);" action="/app/smsTemplate/save">
				<!--基础配置 bof-->
				<div class="edit_box edit_basic">
					<h1>${empty smsTemplate?'添加':'修改'}模板</h1>
					<div class="edit_ctn">
						<div class="edit_field">
							<dl>
								<dt>模板类型</dt>
								<dd class="select_type">
									<div input='opt' onblur="checkOpt(this)" tabindex="2" class="select" defaultValue="${empty smsTemplate?'':smsTemplate.type==0?'0':'1'}" onclick="checkAddItem(this)" >
										<span>选择模板类型<i>&nbsp;</i></span>
										<ul>
											<li val="">选择模板类型</li>
											<li val='0'>普通模板</li>
											<li val='4'>验证码模板</li>
											<li val='1'>云验证模板</li>
										</ul>
									</div>
									<span  id="opt_error" class="error"></span>
								</dd>						
							</dl>
						</div>
						<div class="edit_field">
							<dl>
								<dt>归属应用</dt>
								<dd class="select_type">
									<div input="smsTemplate.appSid" tabindex="2" onblur="checkAppSid(this)"  class="select" defaultValue="${smsTemplate.appSid}">	
										<span>请选择应用<i>&nbsp;</i></span>
											<ul>
												<s:if test="appList!=null">
							                		<s:iterator value="appList" var="app">
							                		  	<li val="${app.appSid}">${app.appName}</li>
							                		</s:iterator>
					                			</s:if>
					                		</ul>
									</div>
									<span id="appSid_error" class="error"></span>
								</dd>						
							</dl>
						</div>

						<div class="edit_field">
							<dl>
								<dt>标题</dt>
								<dd><input type="text" id="title" maxlength="20" onblur="checkTitle(this)" name="smsTemplate.name" value="${smsTemplate.name}"/>
									<span id="title_error" class="error"></span>
								</dd>
								<dd><span class="tips">限20字符，模板备注用，只做识别使用</span></dd>
							</dl>
						</div>
						<div class="edit_field">
							<dl>
								<dt>短信签名</dt>
								<dd><input type="text" id="sign" maxlength="20" onblur="checkTitle(this)" name="smsTemplate.sign" value="${smsTemplate.sign}"/>
									<span id="sign_error" class="error"></span>
								</dd>
								<dd><span class="tips">限20字符，短信签名</span></dd>
							</dl>
						</div>
						<div class="edit_field">
							<dl>
								<dt>内容</dt>
								<dd class="textarea_type"><textarea id="content" onblur="checkContent(this)" name="smsTemplate.content" >${smsTemplate.content}</textarea>
									<span id="content_error" class="error"></span>
								</dd>
								<dd><span class="tips">模板撰写 变量内容请使用{ 标识 } 短信模板和短信签名超过70个字按多条处理 <a href="http://docs.flypaas.com/doku.php?id=%E7%9F%AD%E4%BF%A1%E9%AA%8C%E8%AF%81%E7%A0%81_%E6%A8%A1%E6%9D%BF%E7%9F%AD%E4%BF%A1">模板说明</a></span></dd>
							</dl>
						</div>
						<div class="add_items" style="display:none" >
							<div class="edit_field">
								<dl>
									<dt>长度</dt>
									<dd class="select_type">
										<div input="smsTemplate.len" onblur="checkLen(this)" tabindex="2" class="select" defaultValue="${smsTemplate.len>0?smsTemplate.len:''}" style="float:left; display:inline; width:228px; margin-right:10px;">
											<span>请选择长度<i>&nbsp;</i></span>
											<ul>
												 <li val="4">4</li>
							                     <li val="5">5</li>
							                     <li val="6">6</li>
							                     <li val="7">7</li>
							                     <li val="8">8</li>
											</ul>
										</div>
										<span id="len_error" class="error" style="left:298px;"></span>
										<span class="tips" style="line-height:40px;">允许4-8位</span>
									</dd>
											
								</dl>
							</div>
							<div class="edit_field" tabindex="2" onblur="checkRule(this)">
								<dl>
									<dt>规则</dt>
									<dd>
										<span class="checkbox"><input type="checkbox" value="1" name="smsTemplate_rule" ${smsTemplate.rule==1||smsTemplate.rule==3?'checked="checked"':''}/>数字</span>
										<span class="checkbox"><input type="checkbox" value="2" name="smsTemplate_rule" ${smsTemplate.rule==2||smsTemplate.rule==3?'checked="checked"':''}/>字母</span>
										<span class="tips">同时勾选为数字+字母混合型</span>
										<span id="rule_error" class="error"></span>
										<input id="smsTemplate.rule" type="hidden" name="smsTemplate.rule" value="${smsTemplate.rule}" />
									</dd>
								</dl>
							</div>
							<div class="edit_field">
								<dl>
									<dt>短信类型</dt>
									<dd class="select_type">
										<div input="smsTemplate.type" tabindex="2" onblur="checkType(this)" class="select" defaultValue="${smsTemplate.type>0?smsTemplate.type:''}" style="width:228px;">
											<span>请选择短信类型<i>&nbsp;</i></span>
											<ul style="max-height:100px;">
												<li val="1">云验证码注册类短信</li>
												<li val="2">云验证码登录类短信</li>
												<li val="3">云验证码手机绑定类短信</li>
											</ul>
										</div>
										<span id="type_error" class="error"></span>
									</dd>						
								</dl>
							</div>
						</div>
					</div>
				</div>
				<!--基础配置 eof-->

				<!--编辑提交按钮 bof-->
				<div class="edit_btn">
					<input type="hidden" name="templateId" value="${smsTemplate.templateIdStr}"/>
					<input type="submit" value="保存" class="confirm_btn" />
					<input type="button" value="取消" class="cancel_btn"  onclick="history.go(-1)"/>
				</div>
				<!--编辑提交按钮 bof-->
			</form>
		</div>
		<!--右侧main bof-->
	</div>

	<!--底部footer bof-->
	<%@include file="/page/foot.jsp" %>
	<!--底部footer eof-->
</body>
</html>