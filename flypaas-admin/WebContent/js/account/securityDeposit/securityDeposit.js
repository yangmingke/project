//查看
function view(id,sid){
	location.href=ctx +"/securityDeposit/view?id=" + id+"&sid="+sid;
}
//初始化弹层(冻结,解冻,注销) 
function enableFlagBox(dataSelector,mngType,enableFlag){
	var boxId = "enable_flag_box";
	var tds = $(dataSelector);
	$("#"+boxId+ " .enable_flag_mng").text(mngType);
	$("#"+boxId+ " span.v_enable_flag").text(enableFlag);
	var title;
	$.each(tds,function(i,o){
		var td = $(o);
		title = td.attr("tit");
		$("#"+boxId+ " span."+title).text(td.text());
	});
	showBox(boxId);
}
//处理弹层(冻结,解冻,注销) 
function updateEnableFlag(boxId){
	var mngType = $("#"+boxId + " h3.enable_flag_mng").text();
	var enableFlag= $("#"+boxId + " span.v_enable_flag").text();
	var sid= $("#"+boxId + " span.v_sid").text();
	var enableFlagOld = $("#"+boxId + " span.v_wallet_status").text();
	$.ajax({
		type: "post",
		url: ctx + "/securityDeposit/updateEnableFlag",
		data:{
			mngType : mngType,
			enableFlag : enableFlag,
			sid : sid,
			enableFlagOld : enableFlagOld
		},
		success: function(data){
			if(data.result==null){
				alert("服务器错误，请联系管理员");
				return;
			}
			if(data.result=="success"){
				hideBox(boxId);
				alertBox("操作提示",data.msg,"location.reload(true)");
			}else{
				alertBox("操作提示",data.msg,"hideBox('sus_box')");
			}
		}
	});
}
//提示信息
function alertBox(title,message,onclick){
	$("#sus_title").text(title);
	$("#sus_message").text(message);
	$("#back_btn").attr("onclick",onclick);
	showBox("sus_box");
}
//初始化弹层(充值,赠送,扣费) 
function balanceBox(dataSelector,mngTypeName){
	var boxId = "balance_box";
	var tds = $(dataSelector);
	$("#"+boxId+ " .mng_type_name").text(mngTypeName);
	var title;
	$.each(tds,function(i,o){
		var td = $(o);
		title = td.attr("tit");
		$("#"+boxId+ " span."+title).text(td.text());
	});
	showBox(boxId);
}

//处理(充值,赠送,扣费) 
function updateBalance(boxId){
	var mngType = $("#"+boxId + " h3.mng_type_name").text();
	var sid= $("#"+boxId + " span.v_sid").text();
	var changeValue= $("#changeValue").val();
	var changeValueCheck= $("#changeValueCheck").val();
	if(!changeValue||changeValue=="" ||changeValue <= 0){
		alert("请填写有效的金额数字");
		return;
	}
	if(changeValue !==changeValueCheck){
		alert("两次输入金额需一致");
		return ;
	}
	if(window.confirm("您确定需要 [ "+mngType+"  "+changeValue+" ] 吗?")){
		$.ajax({
			type: "post",
			url: ctx + "/securityDeposit/updateBalance",
			data:{
				mngType : mngType,
				sid : sid,
				changeValue:changeValue,
				changeValueCheck : changeValueCheck
			},
			success: function(data){
				if(data.result==null){
					alert("服务器错误，请联系管理员");
					return;
				}
				if(data.result=="success"){
					hideBox(boxId);
					alertBox("操作提示",data.msg,"location.reload(true)");
				}else{
					hideBox(boxId);
					alertBox("操作提示",data.msg,"hideBox('sus_box')");
				}
			}
		});
	}
}


function back2query(url,cookieName){
	var cv = store.get(cookieName);
	if(null != cv){
		 $.post(url,cv,function(html){
			 $("body").html(html);
		 },"text");
	}else{
		location.href = url;
	}
}