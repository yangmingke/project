
$(document.body).ready(function () {
    //首次获取验证码
	$("#imgVerify").attr("src","adminController/getVerify?"+Math.random());
});

//点击登录按钮
function login(){
     //校验验证码
    checkSum();
}


//获取验证码
function getVerify(obj){
    obj.src = "adminController/getVerify.action?"+Math.random();
}

//登陆校验
function checkSum(){
    var inputStr = $("#veryCode").val();
    var username = $("#user").val();
    var password = $("#pwd").val();
    if(inputStr!=null && inputStr!="" && username!=null && username!="" && password!=null && password!=""){
        inputStr = inputStr.toUpperCase();//将输入的字母全部转换成大写
        $.ajax({
            url : "/adminController/checkVerify",
            data: {
            	"inputStr":inputStr,
            	"username":username,
            	"password":password
            },
            type:"post",
            success : function(data) {
                if(data == "0"){
                	location.href="index.jsp";
                }
                if(data == "2"){
                    $(".check_input").val("");
                    $(".warn_text").text("验证码输入错误");
                    $(".login_form_warn").css("display","block");
                    $("#imgVerify").attr("src","adminController/getVerify?"+Math.random());
                }
                if(data == "1"){
                	$(".check_input").val("");
                    $(".warn_text").text("用户名或密码错误");
                    $(".login_form_warn").css("display","block");
                    $("#imgVerify").attr("src","adminController/getVerify?"+Math.random());
                }
            }
        });
    }else{
        $(".warn_text").text("用户名/密码/验证码均不能为空");
        $(".login_form_warn").css("display","block");
    }
}