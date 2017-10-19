// JavaScript Document

$(function(){
	$("#logout").click(function(){
		logout();
	});
	$("#toExtEmail").click(function(){
		var mail = $("#email").val();
		var begin = mail.indexOf("@");
		var end = mail.indexOf(".");
		mail = mail.substring(begin+1,end);
		var url = "http://mail."+mail+".com";
		post(url, null, "_blank");
	});
});
function popupBox(title,content,callback,cancel){
	$("#popup_smt").click(function(){
		eval(callback);
	});
	$("#popup_box").find("h1").html("&nbsp;&nbsp;"+title);
	$("#popup_box .float_ctn .float_field").html(content);
	if(cancel==2){
		$("#popup_smt").hide();
		$("#cancel").hide();
		setTimeout(function(){
			 $("#popup_box").fadeOut();
		     $(".background_box").fadeOut();
    		 $("body").removeClass("float_body");
		},2000);
	}
	$("#popup_box").show();
	$(".background_box").show();
    $("body").addClass("float_body");
}
function v(obj,pattern){
	if(pattern.test(obj)){
		return true;
	}
	return false;
}
var aCity={11:"北京",12:"天津",13:"河北",14:"山西",15:"内蒙古",21:"辽宁",22:"吉林",23:"黑龙江",31:"上海",32:"江苏",33:"浙江",34:"安徽",35:"福建",36:"江西",37:"山东",41:"河南",42:"湖北",43:"湖南",44:"广东",45:"广西",46:"海南",50:"重庆",51:"四川",52:"贵州" ,53:"云南",54:"西藏",61:"陕西",62:"甘肃",63:"青海",64:"宁夏",65:"新疆",71:"台湾",81:"香港",82:"澳门",91:"国外"}; 
function cidInfo(sId){ 
	var boo = true ;
	var iSum=0 ;
	if(!/^\d{17}(\d|x)$/i.test(sId))return false; 
	sId=sId.replace(/x$/i,"a"); 
	if(aCity[parseInt(sId.substr(0,2))]==null){
		boo = false; 
	}
	sBirthday=sId.substr(6,4)+"-"+Number(sId.substr(10,2))+"-"+Number(sId.substr(12,2)); 
	var d=new Date(sBirthday.replace(/-/g,"/")) ;
	if(sBirthday!=(d.getFullYear()+"-"+ (d.getMonth()+1) + "-" + d.getDate())){
		boo = false; 
	}
	for(var i = 17;i>=0;i --){
		iSum += (Math.pow(2,i) % 11) * parseInt(sId.charAt(17 - i),11) ;
	}
	if(iSum%11!=1){
		boo = false; 
	}
	return boo; 
}
//身份证验证
function vIDCard(id){
	if(cidInfo(id)){
		return true;
	}
	return false ;
}
//护照验证
function vPCard(opt){
	var pattern = /^[a-zA-Z0-9]+$/;
	var isright= v(opt,pattern);
	return isright; 
}
//非空判断
function vEmpty(id){
	var c = $("#"+id).val();
	if(c==null){
		return false;
	}
	return true;
}

//验证超链接
function checkURL(URL){
	var str=URL;
	//下面的代码中应用了转义字符"\"输出一个字符"/"
	var Expression=/^http(s)?:\/\/([\w-]+\.)+[\w-]+(\/[\w- .\/?%&=]*)?/;
	var objExp=new RegExp(Expression);
	if(objExp.test(str)){
	   return true;
	}else{
	   return false;
	}
}
//验证特殊字符串的方法
function validationSymbol(str){   //str被验证的字符串
    var a = new Array("@","#","￥","$","%","<", ">","/","!","~","`","^","&","*","(",")","=","+","{","}","?","《","》",",",".","。",":",";"," ") ;  //a为验证字符串的规则
    for (i=0; i < a.length; i++) {
        if (str.indexOf(a[i]) >= 0) { //此处进行验证判断是否存在指定的字符
            return true;    //返回值
        }
    }
    return false;
}
//验证特殊字符串的方法
function validationSymbolWl(str){   //str被验证的字符串
    var a = new Array("#","￥","$","<", ">","!","~","`","^","*","(",")","+","{","}","《","》",",","。",":"," ") ;  //a为验证字符串的规则
    for (i=0; i < a.length; i++) {
        if (str.indexOf(a[i]) >= 0) { //此处进行验证判断是否存在指定的字符
            return true;    //返回值
        }
    }
    return false;
}
//验证特殊字符串的方法
function validationBracket(str){   //str被验证的字符串
    var a = new Array("【","】","[","]") ;  //a为验证字符串的规则
    for (i=0; i < a.length; i++) {
        if (str.indexOf(a[i]) >= 0) { //此处进行验证判断是否存在指定的字符
            return true;    //返回值
        }
    }
    return false;
}
//公共提交表单
function handSubmit(id){
	$("#"+id).submit();
}
//发送普通的post请求
function post(URL, PARAMS,target) {      
    var temp = document.createElement("form");      
    temp.action = URL;      
    temp.method = "get";
    if(target!=""){
    	temp.target=target;
    }
    temp.style.display = "none";      
    for (var x in PARAMS) {      
        var opt = document.createElement("textarea");      
        opt.name = x;      
        opt.value = PARAMS[x];      
        //alert(opt.value);      
        temp.appendChild(opt);      
    }
    document.body.appendChild(temp);      
    temp.submit();      
    return temp;      
}     
//加载验证码
function loadPic(id){
	var src = $("#"+id).attr("src");
	$("#"+id).attr("src",src+"?nocache=" + new Date().getTime());
}

//座机验证
function validationTelephone(opt){
	  var pattern = /^0\d{2,3}[-]?\d{8}|\(0\d{2,3}\)[-]?\d{8}$/;
	  var isright= v(opt,pattern);
	  return isright;
}
//验证邮箱verifyMbOrMail
function verifyMail(opt){
	  var pattern = /^((([a-z]|\d|[!#\$%&'\*\+\-\/=\?\^_`{\|}~]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])+(\.([a-z]|\d|[!#\$%&'\*\+\-\/=\?\^_`{\|}~]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])+)*)|((\x22)((((\x20|\x09)*(\x0d\x0a))?(\x20|\x09)+)?(([\x01-\x08\x0b\x0c\x0e-\x1f\x7f]|\x21|[\x23-\x5b]|[\x5d-\x7e]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(\\([\x01-\x09\x0b\x0c\x0d-\x7f]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF]))))*(((\x20|\x09)*(\x0d\x0a))?(\x20|\x09)+)?(\x22)))@((([a-z]|\d|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(([a-z]|\d|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])([a-z]|\d|-|\.|_|~|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])*([a-z]|\d|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])))\.)+(([a-z]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(([a-z]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])([a-z]|\d|-|\.|_|~|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])*([a-z]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])))$/;
	  var isright= v(opt,pattern);
	  return isright;
}
//验证手机
function verifyMobile(opt){
	var pattern = /^1[3|4|5|7|8]\d{9}$/;
	 var isright= v(opt,pattern);
	 return isright;
}
//数字验证
function verifyNumber(numStr){
	var pattern = /^\+?[1-9][0-9]*$/ ;
	var isright= v(numStr,pattern);
	return isright;
}
//数字+字母验证
function verifyStr(numStr){
	var pattern = /^[A-Za-z0-9]+$/ ;
	var isright= v(numStr,pattern);
	return isright;
}
//数字金额
function verfiyMoney(money){
//	var pattern = /^(\d|[1-9]\d+)(\.\d+)?$/ ; //包含小数
	var pattern = /^[1-9]\d*$/;
	var isright= v(money,pattern);
	return isright;
}
//验证组织机构号
function verifyOrgId(orgNum){
	var pattern = /^[a-zA-Z0-9]{8}-[a-zA-Z0-9]$/;
	var isright= v(orgNum,pattern);
	return isright;
}
//验证税务登记证
function verifyItt(ittNum){
	var pattern = /^[a-zA-Z0-9]{15,20}$/;
	var isright= v(ittNum,pattern);
	return isright;
}
//验证营业执照号
function bsNum(bsNum){
	var pattern = /^\d{15}$/;
	var isright= v(bsNum,pattern);
	return isright;
}
function verifyNumAndLiter(orgNum){
	var pattern = /^[a-zA-Z0-9]{4,15}$/;
	var isright= v(orgNum,pattern);
	return isright;
}
function verifyNicheng(userName){
	var pattern = /[\u4e00-\u9fa5A-Za-z0-9]/;
	var isright= v(userName,pattern);
	return isright;
}
//400和座机验证
function verify400So(phone){
	var pattern = /(^400\d{7}$)|(^0\d{2,3}\d{8}$)/;
	var isright= v(phone,pattern);
	return isright;
}
//银行卡校验
function verifyBankNum(phone){
	var pattern = /^[0-9]+$/;
	var isright= v(phone,pattern);
	return isright;
}
//纳税人识别号
function verifyIdentificationnum(phone){
	var pattern = /(\d{15})|(\d{17})|(\d{18})|(\d{20})/;
	var isright= v(phone,pattern);
	return isright;
}
//邮编
function verifyPostNum(phone){
	var pattern = /^[0-9][0-9]{5,6}$/;
	var isright= v(phone,pattern);
	return isright;
}
//验证手机和座机
function verifyMobileAndFixPhone(phone){
	var pattern = /(^1[3|4|5|7|8]\d{9}$)|(^0\d{2,3}\d{8}$)/;
	var isright= v(phone,pattern);
	return isright;
}
//强行刷新页面
function refresh(){
	if(window.name != "bencalie"){
	    location.reload();
	    window.name = "bencalie";
	}
	else{
	    window.name = "";
	}
}
//去掉空格
function trim(value){
	return value.replace(/[ ]/g,"");
}
//是否存在空格
function spaceExists(value){
	var isright = false;
	if(value.indexOf(" ")>=0){
		isright =  true;
	}
	return isright;
	
}
//验证密码格式
function vPwd(value){
	var pattern = /^([A-Za-z]+\d+|\d+[A-Za-z]+)[A-Za-z\d]*$/;
	var isRight = v(value,pattern);
	return isRight;
}
function verifyHtml(value){
	var pattern = /<(S*?)[^>]*>.*?|<.*?\/>/;
	var isRight = v(value,pattern);
	return isRight;
}
function bindLoginBox(id){
	$("#"+id).bind("click",function(){
		$(".log_box").show();
		$("#background_box").show();
	});
}

/**
 * 计算字串长度。
 */
function getByteLen(val) {
	if(!val){
		return 0;
	}
	var len = 0;
	for (var i = 0; i < val.length; i++) {
		if (val[i].match(/[^\x00-\xff]/ig) != null) //全角,中文
			len += 2;
		else
			len += 1;
	}
	return len;
};
function logout(){
	location.href="/user/logout";
	/*$.ajax({
		url:"/user/logout",
		type:"post",
		dataType: "text",
		success: function (data) {
			deletecookie("user_log_cookie");
			location.href="/" ;		        	
        },
        error: function (msg) {
        }
	});*/
}



var time1 = function(id,id1,cc,phone){
	$.ajax({
		url:"/checkcode/getSessionCountdown",
		type:"post",
		data:"phone="+phone,
		dataType: "text",
		success: function (data) {
           if(data!="0"){
        	   $("#"+id).attr("cursor", "pointer");
               $("#"+id).val( data + "秒");
               setTimeout(function () {
                   time1(id,id1,cc,phone);
               },
               1000);
           }else{
        	   $("#"+id).removeAttr("disabled");
        	   $("#"+id1).removeAttr("disabled");
        	   $("#"+id).val(cc);
        	   $("#movecode").val("");
        	   
           }
        },
        error: function (msg) {
          $("#movecode").val("");
        }
	});
};
var voiceCodeAjax = function(phone){
	$.ajax({
		url:"/checkcode/voiceCheckCode",
		type:"post",
		async:false,
		data:"phone="+phone,
		dataType: "text",
		success: function (data) {
          $("#movecode").val(data);
        },
        error: function (msg) {
          $("#movecode").val("");
        }
	});
};
var smsCodeAjax = function(phone,type){
	$.ajax({
		url:"/checkcode/smsCheckCode",
		type:"post",
		async:false,
		data:"phone="+phone+"&smsTempType="+type,
		dataType: "text",
		success: function (data) {
          $("#movecode").val(data);
        },
        error: function (msg) {
          $("#movecode").val("");
        }
	});
};

var isExistsPhone = function(type,id1,id2,type1){
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
  			  disabl(id1, id2);
          }else{
        	  $("#phone_error").hide();
        	  if(type==1){
      					voiceCodeAjax(value);
      					time1(id2,id1,"语音验证",value);
        	  }else if(type==2){
      					smsCodeAjax(value,type1);
      					time1(id1,id2,"短信验证",value);
        	  }
          }
        },
        error: function (msg) {
        	 $("#phone_error").hide();
        	 $("#vp").val("2");
        }
	});
};


var voiceCode = function(id1,id2){
	if(frm.phone(id1,id2)){
		isExistsPhone(1,id2,id1);
	}
};
var smsCode = function(id1,id2,type){
	if(frm.phone(id1,id2)){
		isExistsPhone(2,id1,id2,type);
	}
};

var frm = {
		phone:function(id1,id2){
			var phone = $("#phone").val();
			if(!verifyMobileAndFixPhone(phone)){
				  $("#phone_error").text("错误：手机号码非法");
			      $("#phone_error").fadeIn();
			      return false;
			}else{
				$("#phone_error").hide();
			}
			noDisabl(id1,id2);
			return true;
		},
		phoneCode:function(){
			var phone = $("#phone").val();
			var movecode = $("#movecode").val();
			var inputmovecode = $("#inputmovecode").val();
			value = hex_md5(phone+inputmovecode);
			if(movecode!=value){
				$("#move_phone_code_error").text("验证码错误");
				$("#move_phone_code_error").fadeIn();
				return false ;
			}
			$("#move_phone_code_error").hide();
		    return true;
		}
};

var disabl = function(id1,id2){
	$("#"+id1).removeAttr("disabled");
	$("#"+id2).removeAttr("disabled");
};

var noDisabl = function(id1,id2){
	$("#"+id1).attr("disabled", true);
	$("#"+id2).attr("disabled", true);
};
