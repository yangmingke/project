/* ----检测邮箱是否存在    ---*/
function CheckEmail(){
	//邮箱正则表达式
	var email = $('#user-email').val();
	$.post("/userController/checkEmail.action",{"email":email},
			function(data){
				var data1 = eval(data);
				if(data1 == 1){
					$('#emailError').css("dispaly","none");
				}else{
					$('#emailError').css("dispaly","block");
				}
			}
	)
}
/* ----检测电话是否存在    ---*/
function CheckMobile(){
	//电话正则表达式
	var mobile = $('#user-mobile').val();
	$.post("/userController/checkEmail.action",{"mobile":mobile},
			function(data){
				var data1 = eval(data);
				if(data1 == 1){
					$('#mobileError').css("dispaly","none");
				}else{
					$('#mobileError').css("dispaly","block");
				}
			}
	)
}