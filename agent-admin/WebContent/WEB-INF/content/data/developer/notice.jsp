<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>

<html>
<head>
<title>通知</title>
</head>

<body>
	<h1>
		<a href="javascript:void();" class="back" onclick="history.back()">返 回</a>通知
	</h1>
	<div class="main_ctn1 notce">
		<form method="post" id="mainForm" >
			<p>
				标题：<input type="text" id="msg_title" name="msg_title" maxlength="50" class="txt_227" />
			</p>
			<p>
				内容：<textarea id="msg_desc" name="msg_desc" maxlength="1000"></textarea>
			</p>
			<span id="msg" class="error" style="display:none;"></span>
			<hr class="hr" />
			<p class="btn">
				<input type="hidden" name="sid" value="${param.sid}"/>
			</p>
		</form>
		 <input type="submit" value="发送通知" class="org_btn" onclick="sendNotice(this);"/>
	</div>


<script type="text/javascript">
var validate;

$(function(){
	//表单验证规则
	validate = $("#mainForm").validate({
		rules: {
			msg_title: "required",
			msg_desc: "required"
		},
		messages: {
			msg_title: "请输入标题",
			msg_desc: "请输入内容"
		}
	});
});

//发送通知
function sendNotice(btn){
	$("#msg").hide();
	if(!validate.form()){
		return;
	}
	
	var options = {
		beforeSubmit : function() {
			$(btn).attr("disabled", true);
		},
		success : function(data) {
			$(btn).attr("disabled", false);
			
			if(data.result==null){
				$("#msg").text("服务器错误，请联系管理员").show();
				return;
			}
			
			if(data.result=="success"){
				$("#msg_title, #msg_desc").val("");
			}
			$("#msg").text(data.msg).show();
		},
		url : "${ctx}/developer/sendNotice",
		type : "post",
		timeout:30000
	};
	$("#mainForm").ajaxSubmit(options);
}
</script>
</body>
</html>