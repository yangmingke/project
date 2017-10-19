//点击登录按钮
function login(){
     //校验验证码
    checkSum();
}

//提交表单
function submitForm(username,password){
   $("#login_form").submit();
}

//获取验证码
function getVerify(obj){
    obj.src = "loginController/getVerify.action?"+Math.random();
}

//校验验证码
function checkSum(){
    var inputStr = $(".check_input").val();
    var email = $("#user").val();
    var password = $("#pwd").val();
    if(inputStr!=null && inputStr!="" && email!=null && email!="" && password!=null && password!=""){
        inputStr = inputStr.toUpperCase();//将输入的字母全部转换成大写
        $.ajax({
            url : "/loginController/checkVerify.action",
            data: {
            	"inputStr":inputStr,
            	"email":email,
            	"password":password
            },
            type:"post",
            success : function(data1) {
            	var data = eval(data1);
                if(data == 1){
                	location.href="menuController/queryMenu.action";
                }
                if(data == 2){
                    $(".check_input").val("");
                    $(".warn_text").text("验证码输入错误");
                    $(".login_form_warn").css("display","block");
                    $("#imgVerify").attr("src","loginController/getVerify.action?"+Math.random());
                }
                if(data == 3){
                    $(".check_input").val("");
                    $(".warn_text").text("用户账号已被锁定");
                    $(".login_form_warn").css("display","block");
                    $("#imgVerify").attr("src","loginController/getVerify.action?"+Math.random());
                }
                if(data == 4){
                    $(".check_input").val("");
                    $(".warn_text").text("资源方已被锁定");
                    $(".login_form_warn").css("display","block");
                    $("#imgVerify").attr("src","loginController/getVerify.action?"+Math.random());
                }
                if(data == 0){
                	$(".check_input").val("");
                    $(".warn_text").text("用户名或密码错误");
                    $(".login_form_warn").css("display","block");
                    $("#imgVerify").attr("src","loginController/getVerify.action?"+Math.random());
                }
            }
        });
    }else{
        $(".warn_text").text("用户名/密码/验证码均不能为空");
        $(".login_form_warn").css("display","block");
    }
}