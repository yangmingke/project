var path_fo_js = "" ;		
$(function(){
			$("#log_username").blur(function(){
				var username = $(this).val();
				var reg = /^[a-zA-Z0-9\._-]+@[a-zA-Z0-9_-]+\.[a-zA-Z0-9_-]{2,4}|13[0-9]{1}[0-9]{8}$|15[0189]{1}[0-9]{8}$|189[0-9]{8}$/;
				isright= reg.test(username);
				if(!isright){
				$("#error").fadeIn();
					$("#error").text("请输入正确的邮箱或手机号");
					return;
				}
				$("#error").hide();
			});
			path_fo_js = $("#path_fo_js").val();
		});
		//获取cookie中的用户名
		function userCookie(){
			var username = getcookie("username");
			if(username!=""){
				$("#log_username").val(username);
				//$("#log_username").siblings("span").hide();
				//$("#log_username").removeAttr("placeholder");
			}
		}
		userCookie();
		//显示验证码
		function dispCheckCode(){
			var c = "";
			if($("#this_is_the_login_page").size() > 0){
				 c =  "<label>验证码：</label><input type='text' id='code' value='请输入验证码' onfocus=\"if(this.value=='请输入验证码') this.value='';\" onblur=\"if(this.value=='') this.value='请输入验证码';\" />"+
			     "<img src='"+path_fo_js+"/checkcode/picCheckCode' id='checkcodepic' onclick='loadPic(\"checkcodepic\")' align='middle'/>";
			}else{
				 c =  "<input type='text' id='code' placeholder='验证码' />"+
			     "<img src='"+path_fo_js+"/checkcode/picCheckCode' id='checkcodepic' align='middle'/>"+
			     "<a href='javascript:void(0)'"+" onclick='loadPic(\"checkcodepic\")'"+" class='refresh'>点击刷新</a>";
			}

			 $("#temp").html(c);
			 addCodeBlur();
		}
		
		//注册验证码输入校验事件
		function addCodeBlur(){
			$("#code").blur(function(){
				var code = $(this).val();
				ajaxCode1(code);
			});
		}
		
		function ajaxCode1(code,type){
			$.ajax({
				url:path_fo_js + "/page/user/checkcode.jsp",
				type:"post",
				data:"checkCode="+code,
				dataType: "text",
				success: function (data) {
		          if(data!=1){
		              errorInfo("验证码有误，请重新输入。", false);
		  			  $("#reg_email").focus();
		          }
		          $("#checkcode").val(data);
		          if(type==99 && data!=2){
		        	  login();
		          }
		        },
		        error: function (msg) {
		        	 errorInfo("", true);
		        }
			});
		}
		
		function errorInfo(msg,isHide){
			$("#error").text(msg);
			if(isHide){
				$("#error").hide();
			}else{
				$("#error").show();
			}
		}
		
		//登录
		function login(){
			$("#error").hide();
			$("#log_btn").val("登录中...");
			var userid = $("#log_username").val();
			var password = $("#log_password").val();
			password = hex_md5(password);
			password = encodeURIComponent(password);
			$.ajax({
				url:path_fo_js + "/user/login",
				type:"post",
				data:{
					userid : userid,
					password : password,
					authType : $("#authType").val(),
					authId : $("#authId").val()
				},
				dataType: "text",
				success: loginCallback,
		        error: function (msg) {
		        	 errorInfo("", true);
		        	 $("#vM").val("0");
		        	 $("#log_btn").val("登    录");
		        }
			});
		}
		
		//登录后的回调函数
		function loginCallback(data) {
			  var index = data.indexOf("|");
			  var sid = "" ;
			  var errorCount = 0 ;
			  if(index>0){
			   var array = data.split("|");
			   data = array[0];
			   sid = array[1];
			   errorCount = array[2];
			  }
			  if(data=="viewResetPwd21"){
				  post(path_fo_js + '/user/resetPwdTiming', {email :sid},"");
				  return;
			  }
			  
			  if(errorCount>=3){
				  dispCheckCode();
			  }
			  //用户名错误
	          if(data=="1"){
	        	  $("#log_btn").val("登    录");
		          errorInfo("用户名不正确", false);
		          $("#log_username").focus();
		          loadPic("checkcodepic");
	          }
	          //密码错误
	          else if(data=="2"){
	        	  $("#log_btn").val("登     录");
		          errorInfo("密码不正确", false);
		          $("#log_password").focus();
		          loadPic("checkcodepic");
	          }
	          //未激活
	          else if(data=="3"){
	        	  $("#log_btn").val("登    录");
		          errorInfo("用户未激活", false);
		          $("#log_username").focus();
		          post(path_fo_js + '/user/sendverifyMail', {sid :sid},"");
	          }
	          //用户已过期
	          else if(data=="4"){
	        	  $("#log_btn").val("登    录");
		          errorInfo("用户已关闭,请联系客服", false);
		          $("#log_username").focus();
	          }else if(data=="5"){
	        	  $("#log_btn").val("登    录");
		          errorInfo("连续7次密码错误,用户被锁定，请联系客服。", false);
	          }else if(data=="6"){
	        	  $("#log_btn").val("登    录");
		          errorInfo("该用户不能在此页面登录", false);
	          }else{
	        	  //请求到用户界面
	        	  //调用方法 如      
	        	  var fr = $("#fr").val();
	        	  if(null != fr && "" != fr){
	        		location.href = path_fo_js + "/rd.jsp?fr="+fr;
	        		return ;
	        	  }else{
	        		  addcookie("user_log_cookie",sid,0.5);
	        		  post(path_fo_js + '/user/account', {sid :sid},"");
	        	  }
	          }
	        }
		
		function addfavorite(){
			try{
				var url = document.URL ;
				var title = document.title ;
				if (document.all)
				{
				  window.external.addFavorite(url,title);
				}
				else if(window.sidebar)
				{
				   window.sidebar.addPanel(title, url, "");
				}
			}catch(e){
				alert("加入收藏失败，请使用Ctrl+D进行添加");
			}

		} 

		//键盘enter键登录
	    document.onkeydown=function(event){
	            var e = event || window.event || arguments.callee.caller.arguments[0];
	            var disp = $("#background_box").css("display");
	            if(disp=="none"){
	            	return;
	            }
	             if(e && e.keyCode==13){ 
				    logSubmit('login');
	            }
	    };
	  //登录提交表单
	   function logSubmit(id){
	    	remUsername();
	    	if(verifyLog()){
	    		login();
	    	}
	    }
	 //记住用户名
	   function remUsername(){
	   	var remU = $("#remusername").val();
	   	if(remU!="" && remU=="1"){
	   		var username = $("#log_username").val();
	   		addcookie("username",username,720);
	   	}
	   }
	 //登录验证
	   function verifyLog(){
	   	   var uname = $.trim($("#log_username").val());
	       var passwd = $.trim($("#log_password").val());
	       var codeObj = document.getElementById("code");
	       var username= $("#log_username").val();
		   isright= verifyMail(username);
		   isright1 = verifyMobile(username);
		   if(uname == ""&&passwd == "") {
		           errorInfo("请输入正确的邮箱或手机号", false);
		   		$("#log_username").focus();
		   		return false;	
		   }else if(uname == "") {
		           errorInfo("请输入正确的邮箱或手机号", false);
		   		$("#log_username").focus();
		           return false;
		    }else if(!isright&&!isright1){
		   		 errorInfo("请输入正确的邮箱或手机号", false);
		   		$("#log_username").focus();
		   		return false;	
		    }else if(passwd == "") {
		           errorInfo("密码不能为空", false);
		   		$("#log_password").focus();
		           return false;
		    }
		   	if(codeObj!=null){
		   		var code = codeObj.value;
		   		if(code==""){
		   	         errorInfo("验证码不能为空", false);
		   			 $("#log_password").focus();
		   			 return false;
		   		}
		   		ajaxCode1(codeObj.value,99);
		   		return false;
		   	}
		   	return true;
	   }
		
