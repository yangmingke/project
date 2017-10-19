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
	<title>flypaas开放平台管理中心——语音通知</title>
	<%@include file="/page/resource.jsp"%>
	<script type="text/javascript">
		$(function(){
			$("#menu_2_5").addClass("active");
			$(".child p").eq(1).show().siblings("p").hide();
		});
		function checkAppSid(_this){
			$("#appSid_error").html("");
			if(isEmptyObject($("#ncf\\.appSid").val())){
				$("#appSid_error").html("选择应用");
				return false;
			}
			return true;
		}
		function checkContent(_this){
			$("#content_error").html("");
			if(0== $("input[name=type]:checked").val() && isEmptyObject($("#content").val())){
				$("#content_error").html("请填写语音通知内容");
				return false;
			}
			return true;
		}
		function checkFile(_this){
			$("#file_error").html("");
			if(1== $("input[name=type]:checked").val() ){
				var callBackFile = $("#callBackFile").val();
				if(isEmptyObject(callBackFile)){
					$("#file_error").html("选择上传文件");
					return false;			
				}else if(callBackFile!="" && !callBackFile.match(/(.mp3)$|(.wav)$/)){
					$("#file_error").html("语音文件格式无效");
					return false;
				}
			}
			return true;
		}
		function checkSubmit(_this){
			var isOk = true;
			if(!checkAppSid(_this)){
				isOk = false;
			};
			if(!checkContent(_this)){
				isOk = false;
			};
			if(!checkFile(_this)){
				isOk = false;
			};
			if(isOk){
				$("#subi").unbind("click");
				$("#subi").val("保存中...");
			}
			return isOk;	
		}
		function onUploadFileChange(sender) {
			var v = sender.value;
			$(sender).siblings(".file_txt").val(v);
			if(v==undefined || v==""){
				popupBox("提示",'请选择语音文件！','',2);
				return false;
			}
			if (!v.match(/(.mp3)$|(.wav)$/)) {
				popupBox("提示",'语音文件格式无效！','',2);
				return false;
			}
			return true;
		}
	</script>
</head>

<body id="02-4">
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
				<li><a href="#">应用管理</a></li>
				<li class="active"><a href="#">语音库管理</a></li>
			</ul>
		</div>
		<div class="main_tab_tit">
			<ul>
				<li onclick="href2url('<%=path%>/app/ring/query')">铃声</li>
				<li class="active" onclick="href2url('<%=path%>/app/cloudNotice')">语音通知</li>
			</ul>
		</div>
		<div class="state_box">
			<h1>说明</h1>
			<p>1、选择回拨铃声、语音验证码欢迎音类型，每个应用只能上传一个欢迎语音，若重复上传则覆盖上一个语音。选择语音通知类型不限制。</p>
		</div>
		  <s:form namespace="/app" action="addCloudNotice" theme="simple" method="post" onsubmit="return checkSubmit(this);" class="app_form" enctype="multipart/form-data">
			<!--基础配置 bof-->
			<div class="edit_box edit_basic">
				<h1>添加语音通知</h1>
				<div class="edit_ctn">
					<div class="edit_field">
						<dl>
							<dt>所属应用</dt>
							<dd class="select_type">
								<div input="ncf.appSid" class="select" defaultValue="${ncf.appSid}" tabindex="2" onblur="checkAppSid(this)" >	
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
							<dt>语音文件</dt>
							<dd>
							<input type="file" value="" class="file_1 file_1_short" onblur="checkFile(this)" id="callBackFile" name="voice" onchange="onUploadFileChange(this)"/>
								<span id="file_error" class="error"></span>
								<input type="hidden" id="type_1" name="type" checked="checked" value="1" />
							</dd>
							<dd><span class="tips">1、选择回拨铃声、语音验证码欢迎音类型，每个应用只能上传一个欢迎语音，若重复上传则覆盖上一个语音。选择语音通知类型不限制。<br />2、语音的格式必须是wav或mp3(不支持直接改后缀名)，文件大小不能超过1M。</span></dd>
						</dl>
					</div>
				</div>
			</div>
			<!--基础配置 eof-->

			<!--编辑提交按钮 bof-->
			<div class="edit_btn">
				<input id="subi" type="submit" value="保存" class="confirm_btn" />
				<input type="button" value="取消"  onclick="history.go(-1)"  class="cancel_btn" />
			</div>
			<!--编辑提交按钮 bof-->
		</s:form>
		</div>
		<!--右侧main bof-->
	</div>	
	<!--主体content eof-->
	<!--底部footer bof-->
	<%@include file="/page/foot.jsp" %>
	<!--底部footer eof-->
</body>
</html>
