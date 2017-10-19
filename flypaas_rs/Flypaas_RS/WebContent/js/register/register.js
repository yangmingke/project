/**
 * 提交form表单
 */
function submit(){
	var sid = $('#sid').val();
	var netId = $('#netId').val();
	var email = $('#emailId').val();
	var phone = $('#telId').val();
	var username = $('#uId').val();
	var realname = $('#rId').val();
	var password = $('#pwd').val();
	var password2 = $('#pwd2').val();
	var alipayAccount = $('#alipayAccount').val();
	var alipayName = $('#alipayName').val();
	
	var username1 = /^[\u4e00-\u9fa5a-zA-Z0-9_]+$/;
	var password1 = /^(?!\D+$)(?![^a-zA-Z]+$)\S{6,20}$/; 
	var name1 = /^[\u4e00-\u9fa5 ]{2,20}$/;
	var name2 =  /^[a-zA-Z\/ ]{2,20}$/;
	var telephone1 = /^[1]{1}[0-9]{10}/;
	if(!username1.test(username)){
		alert("请输入正确格式的用户名");
		return false;
	}
	if(!telephone1.test(phone)){
		alert("请输入正确手机号");
	 	return false;
	}
	if(realname==""){
		alert("请输入真实姓名");
		return false;
	}
	if(!password1.test(password)){
		alert("请输入正确格式的密码");
		return false;
	}
	if(password!=password2){
		alert("请确保两次输入密码一致");
		return false;
	}
	if(alipayAccount==""){
		alert("请输入支付宝账户");
		return false;
	}
	if(alipayName==""){
		alert("请输入支付宝姓名");
		return false;
	}
	$.ajax({
        url : "/registerController/registerUser.action",
        type: "post",
        data: {"sid":sid,"netId":netId,"email":email,"phone":phone,"username":username,"realname":realname,"password":password,"alipayAccount":alipayAccount,"alipayName":alipayName},
        success : function(data1) {
        	var data = eval(data1);
        	if(data==1){
				location.href="/roleController/assignRole.action?sid="+sid;
			}
			if(data==0){
				alert("注册失败");
			}
        }
    });
}

/**
 * 判断验证码
 */
function validateCode(){
	var email = $('#email').val();
	var email1 = /\w[-\w.+]*@([A-Za-z0-9][-A-Za-z0-9]+\.)+[A-Za-z]{2,14}/;
	if(!email1.test(email)){
		alert("请输入正确的格式邮箱");
		return false;
	}
	$.post("registerController/sendValiCode.action",{"email":email},function(data1){
		var data = eval(data1);
		if(data == 0 || data == 2){
			alert("激活码已发送至该邮箱,登录邮箱点击激活");
		}
		if(data == 1){
			alert("激活失败,邮箱已被注册");
		}
	});
}