// JavaScript Document


$(function(){
	
	 $("input[type='checkbox']").click(function(){
	      if($(this).attr("checked")){
	        $(this).parent("span").parent("dl").next(".callback").show();
	      }else{
	        $(this).parent("span").parent("dl").next(".callback").hide();
	      }
	    });
	    
	 $("#appName").blur(function(){
			var appname = $(this).val();
			appNameExist(appname);
		});
	
	$("li").click(function(){
		var value = $(this).val();
		$(this).parent("ul").siblings("input").val(value);
	});
	
	$("a[name='kind']").click(function(){
		var kind = $(this).attr("id");
		$("#kind").val(kind);
	});
	
	$("#ckivr").click(function(){
		var kind =$("#ckivr").attr("checked");
		if(kind=="checked"){
			$("#ivr").val("1");
		}else{
			$("#ivr").val("0");
		}
		
	});
	
	$("#cktts").click(function(){
		var kind =$("#cktts").attr("checked");
		if(kind=="checked"){
			$("#tts").val("1");
		}else{
			$("#tts").val("0");
		}
		
	});
	
	$("#ckshownbr").click(function(){
		var kind =$("#ckshownbr").attr("checked");
		if(kind=="checked"){
			$("#shownbr").val("1");
		}else{
			$("#shownbr").val("0");
		}
		
	});
	$("#ckIsOfficernbrStatus").click(function(){
		var kind =$("#ckIsOfficernbrStatus").attr("checked");
		if(kind=="checked"){
			$("#isOfficernbrStatus").val("1");
		}else{
			$("#isOfficernbrStatus").val("0");
		}
		
	});
	$("#ckIsVoicecodenbrStatus").click(function(){
		var kind =$("#ckIsVoicecodenbrStatus").attr("checked");
		if(kind=="checked"){
			$("#isVoicecodenbrStatus").val("1");
		}else{
			$("#isVoicecodenbrStatus").val("0");
		}
		
	});
	$("#subi").click(function(){
		if(check()){
			if(!checkIOS()){
				return false;
			}
			setWhiteStr();
			getCbfun();
			getCbfunUrl();
			getCbfunMethod();
			var cpName = $("#appName").val();
			appNameExist(cpName,99);
		}
	});
	 $("#ckshownbr").click(function(){
     	if($(this).attr("checked")){
     		$(this).parents(".number_display").find(".set_list").show();
     	}else{
     		$(this).parents(".number_display").find(".set_list").hide();
     	}
     });
	 
	 $("input:checkbox").each(function(){
			if($(this).attr("checked")){
				$(this).parent("h2").siblings(".set_box").slideDown();				
				$(this).parents("dd").not(".dd_nobg").css("padding-bottom","10px");
			}else{
				$(this).parent("h2").siblings(".set_box").slideUp();
				$(this).parents("dd").not(".dd_nobg").css("padding-bottom","0px");
			}
		});
		$("input:checkbox").click(function(){
			if($(this).attr("checked")){
				$(this).parent("h2").siblings(".set_box").slideDown();
				$(this).parents("dd").not(".dd_nobg").css("padding-bottom","10px");	
			}else{
				$(this).parent("h2").siblings(".set_box").slideUp();
				$(this).parents("dd").not(".dd_nobg").css("padding-bottom","0px");
			}
		});
});

function checkIOS(){
	var result = true;
	if($("#IOSck").is(':checked')){
		var validFile = $('#validFile').val();
		if(validFile == ""){
			$("#validFile_error").text("错误：不能为空").show();
			result = false;
		}else{
			$("#validFile_error").hide();
		}
		var oauthAppId = $('#oauthAppId').val();
		if(oauthAppId == ""){
			$("#oauthAppId_error").text("错误：不能为空").show();
			result = false;
		}else{
			$("#oauthAppId_error").hide();
		}
	}else{
		$('#validFile').val("");
		$('#oauthAppId').val("");
	}
	return result;
}

function setWhiteStr(){
	var whiteStr = $("#white").val();
	$("#whiteStr").val(whiteStr);
}
function setINdustry(value){
	$("#industry").val(value);
}

function hideError(){
	$("#kind_error").hide();
	$("#name_error").hide();
	$("#industry_error").hide();
}
function methodOpt(value,id){
	$("#zcbfunmethod"+id).val(value);
}
function cbfunUrlNotEmpty(){
	var cbfunObj = $("input[name='cbfun']:checked");
	if(cbfunObj.length==0){
		$("#cb_error").hide();
		$("#cb").val(1);
		return;
	}
	$.each(cbfunObj,function(index){
		var value = $(this).parents("h2").siblings(".set_box").find("input[type='text']").val();
		if(value==""){
			$("#cb_error").text("错误：请配置回调地址");
			$("#cb_error").fadeIn();
			$("#cb").val(2);
			 return false;
		}else{
			if(!checkURL(value)){
				$("#cb_error").text("错误：请输入合法的回调地址");
				$("#cb_error").fadeIn();
				$("#cb").val(2);
				 return false;
			}else{
				$("#cb_error").hide();
				$("#cb").val(1);
			}
		}
	});
}

function getCbfun(){
	var ckStr = "" ;
	var cbfunObj = $("input[name='cbfun']");
	$.each(cbfunObj,function(index){
		var ck = $(this).attr("checked");
		if(ck=="checked"){
			ckStr=ckStr+$(this).val()+",";
		}else{
			ckStr=ckStr+"null"+",";
		}
		
//		if(index==0){
//			ckStr=$(this).val()+",";
//		}else if(0<index && index<cbfunObj.length-1){
//			ckStr+=$(this).val()+",";
//		}else{
//			ckStr+=$(this).val();
//		}
	});
	$("#cbfunStr").val(ckStr);
}
function getCbfunUrl(){
	var ckStr = "" ;
	var cbfunObj = $("input[name='cbfunurl']");
	$.each(cbfunObj,function(index){
		if(index==0){
			ckStr=$(this).val()+",";
		}else if(0<index && index<cbfunObj.length-1){
			ckStr+=$(this).val()+",";
		}else{
			ckStr+=$(this).val();
		}
	});
	$("#cbfunurlStr").val(ckStr);
}
function getCbfunMethod(){
	var ckStr = "" ;
	var cbfunObj = $("span[id^='zcbfunmethod']");
	$.each(cbfunObj,function(index){
		if(index==0){
			ckStr=$(this).text()+",";
		}else if(0<index && index<cbfunObj.length-1){
			ckStr+=$(this).text()+",";
		}else{
			ckStr+=$(this).text();
		}
	});
	$("#cbfunmethodStr").val(ckStr);
}
function check(){
	hideError();
	var cpName = $("#appName").val();
	var kind = $("#kind").val();
	var industry = $("#industry").val();
	var wl = $("#white").val();
	
	if(cpName==""){
		$("#name_error").fadeIn();
		return false;
	}else if (getByteLen(cpName) >20){
		$("#name_error").fadeIn();
		return false;
	}else{
		var boo = validationSymbol(cpName);
		if(boo){
			$("#name_error").fadeIn();
			return false;
		}else{
			$("#name_error").hide();
		}
	}
	var happName =  $("#happName").val();
	if(happName==2){
		$("#name_error").fadeIn();
		return false;
	}
	if(happName==""){
		$("#name_error").fadeIn();
		$("#name_error").html("正在检查应用名称是否可用");
		return false;
	}
	if(kind==""){
		$("#kind_error").fadeIn();
		$("#kind_error").html("错误：请选择应用类型");
		return false;
	}
	if(industry==""){
		$("#industry_error").fadeIn();
		$("#industry_error").html("错误：请选择应用行业");
		return false;
	}
	if(null != wl && wl!=""){
		var boo = checkWl(wl);
		if(boo){
			$("#wl_error").fadeIn();
			$("#wl_error").html("错误：请输入合法的白名单");
			return false;
		}else{
			$("#wl_error").hide();
		}
	}else{
		$("#wl_error").hide();
	}
	cbfunUrlNotEmpty();
	var cb = $("#cb").val();
	if(cb==2){
		$("#cburl_error").fadeIn();
		$("#cburl_error").html("错误：请输入合法的回调地址");
		return false;
	}
	var ack = $("#znyzck").attr("checked");
	if(ack=="checked"){
		var ckKey=$("#ckKey").val();
		var ckCallbackUrl=$("#ckCallbackUrl").val();
		var ckEnddate=$("#ckEnddate").val();
		var ckNum=$("#ckNum").val();
		var ckWay=$("#ckWay").val();
		if(ckKey==""||!verifyStr(ckKey)){
			$("#ckKey_error").text("错误：不合法");
			$("#ckKey_error").show();
			return false;
		}else{
			$("#ckKey_error").hide();
		}
		if(ckCallbackUrl!=""&&!checkURL(ckCallbackUrl)){
			$("#ckCallbackUrl_error").text("错误：不合法");
			$("#ckCallbackUrl_error").show();
			return false;
		}else{
			$("#ckCallbackUrl_error").hide();
		}
		
		if(ckEnddate!="" && !verifyNumber(ckEnddate)){
			$("#ckEnddate_error").text("错误：不合法");
			$("#ckEnddate_error").show();
			return false;
		}else{
			$("#ckEnddate_error").hide();
		}
		
		if(ckNum!="" && !verifyNumber(ckNum)){
			$("#ckNum_error").text("错误：不合法");
			$("#ckNum_error").show();
			return false;
		}else{
			$("#ckNum_error").hide();
		}
		
		if(ckKey!=""&& ckWay==""){
			$("#ckWay_error").text("错误：不合法");
			$("#ckWay_error").show();
			return false;
		}else{
			$("#ckWay_error").hide();
		}
		if(ckWay==""){
			$("#ckKey_error").text("错误：不合法");
			$("#ckKey_error").show();
			return false;
		}else{
			$("#ckKey_error").hide();
		}
	}
	return true;
}
function checkWl(str){
    var bb = str.split(";");
    for ( var tmp in bb) {
		var t = $.trim(bb[tmp]);
		if(!/^(((([a-z]|\d|-|\.|_|~|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(%[\da-f]{2})|[!\$&'\(\)\*\+,;=]|:)*@)?(((\d|[1-9]\d|1\d\d|2[0-4]\d|25[0-5])\.(\d|[1-9]\d|1\d\d|2[0-4]\d|25[0-5])\.(\d|[1-9]\d|1\d\d|2[0-4]\d|25[0-5])\.(\d|[1-9]\d|1\d\d|2[0-4]\d|25[0-5]))|((([a-z]|\d|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(([a-z]|\d|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])([a-z]|\d|-|\.|_|~|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])*([a-z]|\d|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])))\.)+(([a-z]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(([a-z]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])([a-z]|\d|-|\.|_|~|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])*([a-z]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])))\.?)(:\d*)?)$/i.test(t)){
			return true;
		}
	}
    return false;
}
function isCkVoiceShowNbr(obj){
	var value = $(obj).val();
	if(value!=""){
		$(obj).parents('.list').siblings('p').find('span').addClass('checked');
		$(obj).parents('.list').siblings('p').find('span').find('input').attr('checked','checked');
		$(obj).parents('.list').siblings('p').find('input[type=\'hidden\']').val('1');
	}else{
		$(obj).parents('.list').siblings('p').find('span').removeClass('checked');
		$(obj).parents('.list').siblings('p').find('span').find('input').removeAttr('checked');
		$(obj).parents('.list').siblings('p').find('input[type=\'hidden\']').val('0');
	}
}
function isCkShowNbr(){
	var showNbr1 = $("#showNbr1").val();
	var showNbr2 = $("#showNbr2").val();
	var showNbr3 = $("#showNbr3").val();
	if(showNbr1=="" && showNbr2=="" && showNbr3==""){
		$("#ckIsOfficernbrStatus").removeAttr('checked');
		$("#ckIsOfficernbrStatus").parent("span").removeClass('checked');
		$("#isOfficernbrStatus").val(0);
	}else{
		$("#ckIsOfficernbrStatus").attr('checked','checked');
		$("#ckIsOfficernbrStatus").parent("span").addClass('checked');
		$("#isOfficernbrStatus").val(1);
	}
}
function buildAppKind(val){
	$("#kind").val(val);
}
