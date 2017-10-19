	var path_fo_js = "http://www.flypaas.com";
	//登录提交表单
	function logSubmit() {
		if (verifyLog()) {
			login();
		}
	}

	// 登录前验证
	function verifyLog() {
		var uname = $.trim($("#log_username").val());
		var passwd = $.trim($("#log_password").val());
		var username = $("#log_username").val();
		isright = verifyMail(username);
		if (uname == "" && passwd == "") {
			errorInfo("请输入正确的邮箱", false);
			$("#log_username").focus();
			return false;
		} else if (uname == "") {
			errorInfo("请输入正确的邮箱", false);
			$("#log_username").focus();
			return false;
		} else if (!isright) {
			errorInfo("请输入正确的邮箱", false);
			$("#log_username").focus();
			return false;
		} else if (passwd == "") {
			errorInfo("密码不能为空", false);
			$("#log_password").focus();
			return false;
		}
		return true;
	}

	// 登录验证
	function login() {
		$("#error").hide();
		$("#log_btn").val("登录中...");
		var userid = $("#log_username").val();
		var agentId = $("#agentId").val();
		var password = $("#log_password").val();
		password = hex_md5(password);
		password = encodeURIComponent(password);
		$.jsonp({
			url : path_fo_js + "/user/login",
			type : "post",
			data : {
				userid : userid,
				password : password,
				agentId :agentId
			},
			jsonp:'loginCallback',
			dataType : "jsonp",
			jsonpCallback:"loginCallback"
		});
	}

	// 登录后的回调函数
	function loginCallback(data) {
		data = data.code;
		var index = data.indexOf("|");
		var sid = "";
		var errorCount = 0;
		if (index > 0) {
			var array = data.split("|");
			data = array[0];
			sid = array[1];
			errorCount = array[2];
		}
		if (data == "viewResetPwd21") {
			post(path_fo_js + '/user/resetPwdTiming', {
				email : sid
			}, "");
			return;
		}

		// 用户名错误
		if (data == "1") {
			$("#log_btn").val("登    录");
			errorInfo("用户名不正确", false);
			$("#log_username").focus();
		}
		// 密码错误
		else if (data == "2") {
			$("#log_btn").val("登     录");
			errorInfo("密码不正确", false);
			$("#log_password").focus();
		}
		// 未激活
		else if (data == "3") {
			$("#log_btn").val("登    录");
			errorInfo("用户未激活", false);
			$("#log_username").focus();
			post(path_fo_js + '/user/sendverifyMail', {
				sid : sid
			}, "");
		}
		// 用户已过期
		else if (data == "4") {
			$("#log_btn").val("登    录");
			errorInfo("用户已关闭,请联系客服", false);
			$("#log_username").focus();
		} else if (data == "5") {
			$("#log_btn").val("登    录");
			errorInfo("连续7次密码错误,用户被锁定，请联系客服。", false);
		} else if (data == "6") {
			$("#log_btn").val("登    录");
			errorInfo("系统参数错误，请联系客服。", false);
		}else if (data == "7") {
			$("#log_btn").val("登    录");
			errorInfo("系统被锁定，请联系客服。", false);
		}else if (data == "8") {
			$("#log_btn").val("登    录");
			errorInfo("系统暂停登录，请联系客服。", false);
		}else {
			var password = $("#log_password").val();
			password = hex_md5(password);
			password = encodeURIComponent(password);
			$('#password').val(password);
			$("#form").submit();
		}
	}

	function errorInfo(msg,isHide){
		$("#error").text(msg);
		if(isHide){
			$("#error").hide();
		}else{
			$("#error").show();
		}
	}
	
	//验证邮箱verifyMbOrMail
   function verifyMail(opt){
	  var pattern = /^((([a-z]|\d|[!#\$%&'\*\+\-\/=\?\^_`{\|}~]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])+(\.([a-z]|\d|[!#\$%&'\*\+\-\/=\?\^_`{\|}~]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])+)*)|((\x22)((((\x20|\x09)*(\x0d\x0a))?(\x20|\x09)+)?(([\x01-\x08\x0b\x0c\x0e-\x1f\x7f]|\x21|[\x23-\x5b]|[\x5d-\x7e]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(\\([\x01-\x09\x0b\x0c\x0d-\x7f]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF]))))*(((\x20|\x09)*(\x0d\x0a))?(\x20|\x09)+)?(\x22)))@((([a-z]|\d|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(([a-z]|\d|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])([a-z]|\d|-|\.|_|~|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])*([a-z]|\d|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])))\.)+(([a-z]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(([a-z]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])([a-z]|\d|-|\.|_|~|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])*([a-z]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])))$/;
	  var regRxp = new RegExp(pattern);
	  var isright= regRxp.test(opt);
	  return isright;
   }