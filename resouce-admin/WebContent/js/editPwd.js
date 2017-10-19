$(function(){
	$('#pwdSubmit').click(function(){
		var renewpass = $('#renewpass').val();
		var newpass = $('#newpass').val();
		if(renewpass != newpass){
			 $(".warn_text").text("两次输入的密码不一致");
             $(".form_warn").css("display","block");
             return;
		}
		if(newpass.length < 5){
			$(".warn_text").text("新密码不能小于5位");
            $(".form_warn").css("display","block");
            return;
		}
		
		var mpass = $('#mpass').val();
		$.ajax({
	         url : "/adminController/editpass",
	         data: {
	         	"newpass":newpass,
	         	"mpass":mpass
	         },
	         type:"post",
	         success : function(data) {
	             if(data == "0"){
	            	 window.wxc.xcConfirm("修改成功！", window.wxc.xcConfirm.typeEnum.info,{
		        			onOk:function(v){
		        				window.location="/jsp/admin/editPwd.jsp";
		        			}
		        		});
	             }else if(data == "2"){
	            	 $(".warn_text").text("原始密码错误");
                     $(".form_warn").css("display","block");
	             }else{
	            	 $(".warn_text").text("系统参数发生异常，请联系管理员！");
                     $(".form_warn").css("display","block");
	             }
	         }
	     });
	});
})