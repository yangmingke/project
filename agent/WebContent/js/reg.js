	var path_fo_js = "http://www.flypaas.com";
	//注册提交表单
	function regSubmit() {
		if (verifyReg()) {
			reg();
		}
	}

	// 注册前验证
	function verifyReg() {
		var uname = $("#vemail").val();
		isright = verifyMail(uname);
		if (uname == "") {
			errorInfo("请输入正确的邮箱", false);
			$("#vemail").focus();
			return false;
		} else if (!isright) {
			errorInfo("请输入正确的邮箱", false);
			$("#vemail").focus();
			return false;
		} 
		return true;
	}

	// 注册验证
	function reg() {
		$("#error").hide();
		$("#reg_btn").val("注册中...");
		var vemail = $("#vemail").val();
		var agentId = $("#agentId").val();
		$.jsonp({
			url : path_fo_js + "/user/addAgentEmail",
			type : "post",
			data : {
				vemail : vemail,
				agentId :agentId
			},
			jsonp:'regCallback',
			dataType : "jsonp",
			jsonpCallback:"regCallback"
		});
	}

	// 注册后的回调函数
	function regCallback(data) {
		info = data.info;
		if(info == "ok"){
			alert("已发送一封验证邮件到你的邮箱，此邮箱将作为登录用户名");
		}else{
			errorInfo(info, false);
		}
		$("#reg_btn").val("注    册");
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