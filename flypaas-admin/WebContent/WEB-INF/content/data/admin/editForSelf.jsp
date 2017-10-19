<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/common/taglibs.jsp"%>

<html>
<head>
	<title>管理员资料 - 修改</title>
	<script type="text/javascript" src="${ctx}/js/hex_ha_ha_ha_ha_ha.js"></script>
</head>

<body>
      <div class="main_ctn">
        <h1>管理员资料 - 修改</h1>
        <div class="admin_material material_form">
          <form method="post" id="mainForm">
            <p>
              <label>管理员身份</label>
              <span>${data.role_name}</span>
            </p>
            <p>
              <label>管理员账号</label>
              <input type="text" name="email" value="${data.email}" maxlength="50"/>
            </p>
            <p>
              <label>真实姓名</label>
              <input type="text" name="realname" value="${data.realname}" maxlength="20"/>
            </p>
            <p class="phone">
              <label>联系手机</label>
              <input type="text" id="mobile" name="mobile" value="${data.mobile}" onfocus="inputControl.setNumber(this, 11, 0, false)"/>
              <input type="hidden" id="old_mobile" value="${data.mobile}"/>
              <input type="button" value="获取验证码" onclick="sendVerifyCode(this)"/>
              <label id="mobile-error" class="error" for="mobile" style="display: none;"></label>
              
              <span class="tips">用于接收安全、业务监控报警、重要变更等通知</span> </p>
            <p class="phone_code">
              <label>手机验证码</label>
              <input type="text" id="verify_code" onfocus="inputControl.setNumber(this, 4, 0, false)"/>
              <input type="hidden" id="encrypt_verify_code"/>
              <label id="verify_code-error" class="error" for="verify_code" style="display: none;"></label>
            </p>
			<p><span id="msg" class="error" style="display:none;"></span></p>
            <hr class="hr" />
            <p class="btn">
            	<input type="hidden" name="sid" value="${data.sid}"/>
				<input type="button" value="修 改" class="org_btn" onclick="save(this)"/>
				<input type="button" value="取 消" class="grey_btn" onclick="back()"/>
            </p>
          </form>
        </div>
      </div>

<script type="text/javascript">
var validate;

$(function(){
	//表单验证规则
	validate = $("#mainForm").validate({
		rules: {
			email: {
				required: true,
				email2: true
			},
			realname: "required",
			mobile: {
				required: true,
				mobile: true
			}
		},
		messages: {
			email: {
				required:"请输入管理员账号"
			},
			realname: "请输入真实姓名",
			mobile: {
				required: "请输入联系手机",
				mobile: "请输入有效的联系手机"
			}
		}
	});
});

//保存
function save(btn){
	$("label.error, span.error").hide();
	if(!validate.form()){
		return;
	}
	
	var mobile = $("#mobile").val();
	var verify_code = $("#verify_code").val();
	var encrypt_verify_code = $("#encrypt_verify_code").val();
	if(mobile != $("#old_mobile").val()){
		if(encrypt_verify_code==""){
			$("#mobile-error").text("联系手机已修改，请点击“获取验证码”").show();
			return;
		}
		
		if(verify_code==""){
			$("#verify_code-error").text("请输入手机验证码").show();
			return;
		}
		verify_code = hex_ha_ha_ha_ha_ha(mobile+verify_code);
		if(verify_code!=encrypt_verify_code){
			$("#verify_code-error").text("手机验证码错误，请重新输入").show();
			return;
		}
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
				$("#old_mobile").val($("#mobile").val());
				$("#verify_code, #encrypt_verify_code").val("");
			}
			
			$("#msg").text(data.msg).show();
		},
		url : "${ctx}/admin/save",
		type : "post",
		timeout:30000
	};
	$("#mainForm").ajaxSubmit(options);
}

//发送短信验证码
function sendVerifyCode(button){
	$("label.error, span.error").hide();
	if(!validate.form()){
		return;
	}
	if($("#mobile").val() == $("#old_mobile").val()){
		$("#mobile-error").text("联系手机已经被绑定，请重新输入").show();
		return;
	}
	var btn = $(button);
	btn.attr("disabled", true);
	
	$.ajax({
		type: "post",
		url: "${ctx}/admin/sendVerifyCode",
		data:{
			mobile : $("#mobile").val()
		},
		success: function(data){
			
			if(data.result==null){
				$("#mobile-error").text("服务器错误，请联系管理员").show();
				return;
			}
			if(data.result=="success"){
				$("#encrypt_verify_code").val(data.encrypt_verify_code);
			}
			$("#mobile-error").text(data.msg).show();
		}
	});
	
	var i=120;
	var interval = setInterval(function(){
		btn.val((i--)+"秒后重新获取验证码");
		if(i<0){
			clearInterval(interval);
			btn.val("获取验证码");
			btn.attr("disabled", false);
		}
	}, 1000);
}
</script>
</body>
</html>