		$(function(){
			
			//第一步：绑定邮箱
			$("#subBtn").click(function(){
				var email = $("#reg_email").val();
				vMobile(email);
			});
			//第二步：更新其他信息
			$("#infoSubt").click(function(){
				if(userInfoFrm.password1() && userInfoFrm.password2()
							&& userInfoFrm.usernameExits() && userInfoFrm.compareCode()){
					var value = $("#phone").val();
					$.ajax({
						url:"/user/ckMobileAndmailEnable",
						type:"post",
						data:"vmobile="+value,
						dataType: "text",
						success: function (data) {
				          if(data=="1"){
				        	  $("#phone_error").fadeIn();
				  			  $("#phone_error").html("手机号码已经被绑定");
				  			  $("#vp").val("2");
				          }else{
				        	  $("#phone_error").hide();
				        	  $("#userInfoFrm").submit();
				          }
				        },
				        error: function (msg) {
				        	 $("#phone_error").hide();
				        	 $("#vp").val("2");
				        }
					});
				}
			});
			
		
		});
		var path_fo_js = $("#path_fo_js").val();
		
		function ajaxForUserName(userName){
			$.ajax({
				url:path_fo_js+"/user/ckUserName",
				type:"post",
				data:"vuserName="+userName,
				dataType: "text",
				success: function (data) {
	              if(data!="0"){
	            	  $("#userName_error").text("昵称已经存在");
	      			  $("#userName_error").fadeIn();
	      			  $("#vusername").val("1");
	              }else{
	            	  $("#userName_error").hide();
	            	  $("#vusername").val("0");
	              }
	            },
	            error: function (msg) {
	            }
			});
		}
		
		function vMobile(email){
			if(email == "") {
				$("#email_error").fadeIn();
				$("#email_error").text("邮箱不能为空");
				return false;
			}else if(!verifyMail(email)){
				$("#email_error").fadeIn();
	            $("#email_error").text("邮箱输入错误");
				return false;
			}
			ajx(email);
			
		}
		function ajx(email){
			$.ajax({
				url:path_fo_js+"/user/ckMobileAndmailEnable",
				type:"post",
				data:"vemail="+email,
				dataType: "text",
				success: function (data) {
	              if(data=="1"){
	            	  $("#email_error").fadeIn();
	                  $("#email_error").text("邮箱已被注册");
	              }else{
            		  $("#subBtn").val("注册中...");
          			  $("#subBtn").unbind("click");
            		  $("#register").submit();
	              }
	            },
	            error: function (msg) {
	            	 $(".form_tips span").text("");
	            }
			});
		}
		$("#reg_password").change(function(){
			userInfoFrm.password1();
		});
		$("#reg_password_confirm").change(function(){
			userInfoFrm.password2();
		});
		//输入用户名判断是否已存在
		$("#userName").keyup(function(){
			userInfoFrm.username();
		});
		var userInfoFrm = {
			password1:function(){
				var boo = false;
				var password1 = $.trim($("#password1").val());
				if(password1 == "") {
			        $("#password1_error").fadeIn();
			        $("#password1_error").text("密码不能为空");
					$("#password1").focus();
			    }else if(password1.length<8||password1.length>16){
			    	 $("#password1_error").fadeIn();
			         $("#password1_error").text("密码长度不符合规范");
					 $("#password1").focus();
			    }else if(!vPwd(password1)){
			    	 $("#password1_error").fadeIn();
			         $("#password1_error").text("密码不符合规范");
					 $("#password1").focus();
			    }else{
			    	$("#password1_error").hide();
			    	boo = true;
			    }
				return boo;
			},
			password2:function(){
				var boo = false;
				var password2 = $.trim($("#password2").val());
				var password1 = $.trim($("#password1").val());
				if(password2 == ""){
					$("#password2_error").fadeIn();
					$("#password2_error").text("确认密码不能为空");
					$("#password2").focus();
				}else if(password2!=password1){
					$("#password2_error").fadeIn();
					$("#password2_error").text("两次输入密码不一致");
					$("#password2").focus();
				}else{
					$("#password2_error").hide();
					boo = true;
				}
				return boo;
			},
			usernameExits:function(){
				 var boo = false;
				 var userName = $.trim($("#userName").val());
				 var boo = verifyNicheng(userName);
				 if(!boo){
					  $("#userName_error").text("昵称不符合规范");
	      			  $("#userName_error").fadeIn();
	      			  return boo;
				 }
				 if(getByteLen(userName)>12){
					 $("#userName_error").text("昵称不符合规范");
	      			 $("#userName_error").fadeIn();
	      			 return boo;
				 }
				 var vusername = $.trim($("#vusername").val());
				 if(vusername=="1"){
					$("#userName_error").fadeIn();
					$("#userName_error").text("昵称已存在");
					$("#userName").focus();
				 }else{
					 $("#userName_error").hide();
					 boo = true;
				 }
				 return boo;
			},
			compareCode:function(){
				var boo = false;
				/*if(frm.phone()&&frm.phoneCode()){
					boo = true;
				}*/
				if(frm.phone()){
					boo = true;
				}
				return boo;
			},
			username:function(){
				var userName = $.trim($("#userName").val());
				var boo = verifyNumAndLiter(userName);
				if(boo){
					ajaxForUserName(userName);
				}
			}
		};