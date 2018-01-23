var validate;
$(function(){
	//表单验证规则
	validate = $("#developerTransferForm").validate({
		rules: {
			select_package: "required",
			is_send_msg: "required"
		},
		messages: {
			select_package: "请选择套餐档",
			is_send_msg: "请选择是否推送消息"
		}
	});
});

//查看
function view(sid){
	location.href=ctx +"/developerAccount/view?sid=" + sid;
}
//查看流量使用情况
function trafficView(sid,realname){
	location.href=ctx +"/developerAccount/trafficView?sid=" + sid + "&realname=" + encodeURI(encodeURI(realname));
}

//查看使用时长计费情况
function feeTimeView(sid,realname,fee_type){
	location.href=ctx +"/developerAccount/feeTimeView?sid=" + sid + "&fee_type=" + fee_type + "&realname=" + encodeURI(encodeURI(realname));
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
		url: ctx + "/developerAccount/updateEnableFlag",
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
submiting = false;
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
	if(submiting){
		alert("订单提交中,请稍等...");
		return ;
	}
	if(window.confirm("您确定需要 [ "+mngType+" "+changeValue+" ] 吗?")){
		submiting = true;
		$.ajax({
			type: "post",
			url: ctx + "/developerAccount/updateBalance",
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
				submiting =  false;
			}
		});
	}
}


//初始化弹层(配置套餐) 
function transferBox(dataSelector,mngTypeName,package_id){
	$("#div_select_package li[val^='"+package_id+"__']").hide().siblings().show(); //隐藏自己
	
	var boxId = "transfer_box";
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

//选择套餐
function selectPackage(value, text){
	var v = value.split("__");
	$("#box_package_id").text(v[0]);
	$("#box_package_name").text(v[1]);
	$("#box_min_consumption").text("￥"+v[2]);
	$("#new_package_id").val(v[0]);
}


//保存开发者转移
function developerTransfer(btn){
	$("#msg").hide();
	if(!validate.form()){
		return;
	}
	
	var options = {
		beforeSubmit : function() {
			$(btn).attr("disabled", true);
		},
		success: function(data){
			$(btn).attr("disabled", false);
			if(data.result==null){
				alert("服务器错误，请联系管理员");
				return;
			}
			
			if(data.result=="success"){
				hideBox("transfer_box");
				alertBox("操作提示",data.msg,"location.reload(true)");
			}else{
				$("#msg").text(data.msg).show();
			}
		},
		url: ctx + "/package/developerTransfer",
		type : "post",
		timeout:30000
	};
	$("#developerTransferForm").ajaxSubmit(options);
}


function billBox(oid){
	queryDownload(new Date());
	showBox("bill_box");
}
function queryDownload(d){
	var func = $("#billDownFunction").val();
	var download = $("#download");
	var now = new Date();
	if(!d){
		var edTmp =$("#end_date").val();
		if(edTmp&&"" != edTmp){
			d = new Date(edTmp);
		}
	}
	if(!d){
		 d = now;
	}
	var html = "";
	if(now.getFullYear() == d.getFullYear() && now.getMonth() == d.getMonth()){
		d.setMonth(d.getMonth() -1);
	}
	var start_date = null;
	var sdTmp =$("#start_date").val();
	if(sdTmp&&"" != sdTmp){
		var t = new Date(sdTmp);
		start_date = t.getFullYear();
		var a = t.getMonth() + 1;
		if(a < 10){
			start_date +="0"+a;
		}else{
			start_date +=a;
		}
	}
	for (var i = 1; i < 13; i++) {
		var a = d.getMonth() + 1;
		var tmp = d.getFullYear() + "";
		if(a < 10){
			tmp +="0"+a;
		}else{
			tmp +=a;
		}
		if(null != start_date && tmp < start_date){
			continue;
		}
		if(tmp >= '201406'){
			if(i %2 == 1){
				html+="<tr>";
			}
			html+="<td>"+tmp+"出账单<a onclick=\""+func+"('"+tmp+"')\">下载</a></td>";
			if(i %2 == 0){
				html+="</tr>";
			}
		}
		d.setMonth(d.getMonth() -1);
	}
	download.html(html);
}
function clientBillDownload(d){
	var client_number = $("#client_number").val();
	var app_sid = $("#app_sid").val();
	var url = ctx + "/download/clientBill?type=month&app_sid="+app_sid+"&client_number="+client_number+"&date="+d;
	download_file(url);
}

function developerBillDownload(d){
	var sid = $("#sid").val();
	var url = ctx + "/download/developerBill?type=month&sid="+sid+"&date="+d;
	download_file(url);
}

function download_file(url)
{
	if(typeof(download_file.iframe)== "undefined")
	{
	var iframe = document.createElement("iframe");
	download_file.iframe = iframe;
	document.body.appendChild(download_file.iframe); 
	}
	// alert(download_file.iframe);
	download_file.iframe.src = url;
	download_file.iframe.style.display = "none";
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
//设置信用额度
function saveCreditBalance(sid,acct_id){
	var credit_balance = $("#credit_balance_text").val();
	if(/^(([0-9]|([1-9][0-9]{0,9}))((\.[0-9]{1,4})?))$/.test(credit_balance)){
			if(window.confirm("您确定需要设置信用额度为 "+credit_balance+" 元 吗")){
				$.post(ctx + "/developerAccount/saveCreditBalance",{
					'sid':sid,
					'acct_id':acct_id,
					'credit_balance':credit_balance},function(data){
						if(data.result==null){
							alert("服务器错误，请联系管理员").show();
							return;
						}
						alert(data.msg).show();
				});
			}
		}else{
			alert("请正确填写信用额度,纯数字格式,最多十位整数四位小数.");
		}
}
/**
 * 转化计费类型
 * @param sid
 * @param feeType
 */
function changeFeeType(sid,feeType){
	var confirmText='';
	var change2="";
	if(feeType==101){
		confirmText = "确定转化为时间计费吗？";
		change2 = 102;
	}else if(feeType==102){
		confirmText = "确定转化为流量计费吗？";
		change2 = 101;
	}
	if(confirm(confirmText)){
		$.post("/developerAccount/changeFeeType",{"sid":sid,"feeType":change2},function(data){
			if(data.result == "success"){
				alert(data.msg);
				document.location.reload();//当前页面 
			}else{
				alert(data.msg);
			}
		});
	}
}