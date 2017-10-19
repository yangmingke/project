<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/common/taglibs.jsp"%>

<html>
<head>
	<title>发票审核 - ${data.entity.id == null?'开具':'编辑'}发票</title>
</head>

<body>
	<h1><a href="javascript:;" class="back" onclick="back()">返 回</a>发票审核 / ${data.entity.id == null?'开具':'编辑'}发票</h1>
    <div class="main_ctn1 channel_detail">
        <form method="post" id="mainForm">
	    <ul class="ul_left">
	    	<li><label>开发者sid</label><input type="text" name="sid"  value="${data.entity.sid}" readonly="readonly"/></li>
	    	<li><label>开发者账号</label><input type="text" name="email" value="${data.entity.email}" readonly="readonly"/></li>
	    	<li><label>开具类型</label>
	    	<input type="hidden" name="opentype"  value="${data.entity.opentype}"/>
	    	<input type="text" id="opentype" value="${data.entity.opentype == 1?'个人':'企业'}" readonly="readonly"/></li>
	    	<li><label>可开金额</label><span>${data.entity.enable_invoice_fee}</span></li>
		    <li><label>发票金额</label><input type="text" name="money" value="${data.entity.money_fmt}"/></li>
			<li><label>发票抬头</label><input type="text" name="title" value="${data.entity.title}"/></li>
			<li><label>发票类型</label><u:select id="type" value="${empty data.entity.type?'2':data.entity.type}" callback="changeBillType" dictionaryType="bill_type" excludeValue=""/></li>
			<div class="clear">&nbsp;</div>
			<div id="12323_type_div">
			</div>
			<li><hr/></li>
			<li><label>邮寄地址</label>
				<u:select id="province" sqlId="loadProvince" value="${data.entity.province}" callback="reloadCity"/>
				&nbsp;
				<u:select id="city" sqlId="loadCityByProvinceId" sqlParams="{provinceId:'${data.entity.province== null?1:data.entity.province}'}"></u:select>
			</li>
			<div class="clear">&nbsp;</div>
			<li><label>街道地址</label><input type="text" name="street" value="${data.entity.street}"/></li>
			<li><label>邮政编码</label><input type="text" name="postnum" value="${data.entity.postnum}"/></li>
			<li><label>联系人</label><input type="text" name="contacts" value="${data.entity.contacts}"/></li>
			<li><label>联系电话</label><input type="text" name="contactmobile" value="${data.entity.contactmobile}"/></li>
			
			<li><label>快递公司</label>
			<u:select placeholder="快递公司" id="logistics_company" value="${data.entity.logistics_company}" dictionaryType="logistics_company_code" />
			</li>
			<div class="clear">&nbsp;</div>
			<li><label>快递单号</label><input placeholder="快递单号" type="text" name="logistics_no" value="${data.entity.logistics_no}"/></li>
			
			<p><span id="msg" class="error" style="display:none;"></span></p>
		</ul>
		<div class="clear"></div>
          <hr class="hr" />
          <p class="btn">
            <input type="hidden" name="id" value="${data.entity.id}" />
            <input type="button" value="${data.entity.id==null?'添 加':'修 改'}" class="org_btn" onclick="save(this)"/>
            <input type="button" value="取 消" class="grey_btn" onclick="back()"/>
          </p>
        </form>
      </div>
      
    <div id="12323_type_1" style="display: none;">
		<li><label>纳税人识别号</label><input type="text" name="identificationnum" value="${data.entity.identificationnum}" /></li>
		<!-- <li><label>一般纳税人资格认证</label><input  class="file_1" type="file"   onchange="onUploadFileChange(this)"  name="identificationimg"/></li> -->
		<li><label>银行账号</label><input type="text" name="bankaccount" value="${data.entity.bankaccount}"/></li>
		<li><label>开户行</label><input type="text" name="bankaddr" value="${data.entity.bankaddr}" /></li>
	</div>
	 <div id="12323_type_2" style="display: none;">
	</div>
      
<script type="text/javascript">
var validate;
$(function(){
	//表单验证规则
	validate = $("#mainForm").validate({
		rules: {
			sid: "required",
			email: "required",
			opentype: "required",
			money:{
				required :true,
				number:true,
				max:"${data.enable_invoice_fee}"
			},
			title: "required",
			type: "required",
			province: "required",
			city: "required",
			street: "required",
			postnum: "required",
			contacts: "required",
			contactmobile: "required",
		//	identificationnum: "required",
		//	${data.entity.id==null?'identificationimg:"required",':''}
			bankaccount: {
				required :true,
				number:true
			},
			bankaddr: "required"
			
			
		},
		messages: {
			sid: "请输入开发者sid",
			email: "请输入开发者账号",
			opentype: "请输入开票类型",
			money: {
				required:"请输入发票金额",
				number:"请输入合法金额数字",
				max:"不能大于可开金额"
			},
			title: "请输入发票抬头",
			type: "请输入发票类型",
			province: "请选择邮寄的省",
			city: "请选择邮寄的市",
			street: "请输入街道",
			postnum: "请输入邮政编码",
			contacts: "请输入联系人",
			contactmobile: "请输入联系电话",
			identificationnum: "请输入纳税人识别号",
		//	identificationimg: "请上传一般纳税人资格认证",
			bankaccount: {
				required:"请输入银行账号",
				number:"请输入合法银行账号"
			},
			bankaddr: "请输入开户行"
		}
	});
});

function onUploadFileChange(sender) {
	var v = sender.value;
	$(sender).siblings(".file_txt").val(v);
	if(v==undefined || v==""){
		popupBox("提示",'请选择文件！','',2);
		return false;
	}
	return true;
}

isFirstLoad = true
function reloadCity(provinceValue, provinceText){
	$.post("${ctx}/common/loadCityByProvinceId",{provinceId : provinceValue},function(json){
		$("#div_city li").remove();
		$("#city").val("");
		$("#div_city span").text("");
		var html = "";
		$.each(json,function(i,o){
			html += "<li val=\""+o.value+"\" onClick = 'selectCity(this);'>"+o.text+"</li>";
		});
		$("#div_city ul").html(html);
		if(isFirstLoad){
			selectCity($("#div_city li[val=${data.entity.city}]"));
			isFirstLoad = false;
		}
	},"json");
}
function selectCity(_this){
	$("#city").val($(_this).attr("val"));
	$("#city").val($(_this).attr("val"));
	$("#div_city span").text($(_this).text());
}
function changeCity(){
	var msg = $("#"+boxId+" [name='msg']");
	msg.hide();
	var city = $("#city").val();
}
function changeBillType(value,text){
	$("#12323_type_div").html($("#12323_type_"+value).html());
}


function save(btn){
	$("#msg").hide();
	if(!validate.form()){
		return;
	}
	var province = $("#province").val();
	var city = $("#city").val();
	if($.isEmptyObject(province)){
		$("#msg").text("请选择省").show();
		return ;
	}
	if($.isEmptyObject(city)){
		$("#msg").text("请选择市").show();
		return ;
	}
	
	var options = {
		beforeSubmit : function() {
			$(btn).attr("disabled", true);
		},
		success : function(data) {
			$(btn).attr("disabled", false);
			
			if(data.result==null){
				$("#msg").text("服务器错误，请联系管理员").show();
				return;
			}
			
			$("#msg").text(data.msg).show();
		},
		url : "${ctx}/invoiceAudit/save",
		type : "post",
		timeout:30000
	};
	$("#mainForm").ajaxSubmit(options);
}

</script> 
</body>
</html>