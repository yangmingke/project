<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/common/taglibs.jsp"%>

<html>
<head>
	<title>发票审核 - 审核</title>
</head>

<body>
	<h1><a href="javascript:;" class="back" onclick="back()">返 回</a>发票审核/审核</h1>
      <div class="main_ctn1 qualification_view">
        <table cellpadding="0" cellspacing="0" border="0">
          <tbody>
            <tr>
              <th>用户名</th>
              <th>发票抬头信息</th>
              <th>发票类型</th>
              <th>开具类型</th>
              <th>发票金额</th>
              <th>提交时间</th>
              <th>状态</th>
            </tr>
            <tr>
              <td>${data.entity.email}</td>
              <td title="${data.entity.title}"><u:truncate length="20" value="${data.entity.title}"/></td>
              <td><u:ucparams key="${data.entity.type}" type="bill_type"/></td>
	          <td><u:ucparams key="${data.entity.opentype}" type="bill_opentype"/></td>
	          <td>${data.entity.money_fmt}</td>
	          <td>${data.entity.update_date_fmt}</td>
              <td id="oauth_status_name"><u:ucparams key="${data.entity.status}" type="bill_status"/></td>
            </tr>
          </tbody>
        </table>
        <s:if test="data.entity.type == 1">
        <table cellpadding="0" cellspacing="0" border="0" class="table_2">
          <tbody>
            <tr>
              <th colspan="4">信息</th>
            </tr>
            <tr>
            	<td>纳税人识别号：</td>
            	<td>${data.entity.identificationnum}</td>
            </tr>
            <tr>
            	<td>银行账号：</td>
            	<td>${data.entity.bankaccount}</td>
            	<td>开户行地址：</td>
            	<td>${data.entity.bankaddr }</td>
            </tr>
          </tbody>
        </table>
        </s:if>
        
         <table cellpadding="0" cellspacing="0" border="0" class="table_2">
          <tbody>
            <tr>
              <th colspan="4">邮寄信息</th>
            </tr>
            <tr>
            	<td>邮寄地址：</td>
            	<td colspan="3">
					${data.entity.province_name} ${data.entity.city_name} ${data.entity.street}
				</td>
			</tr>
            <tr>
            	<td>邮政编码：</td>
              	<td colspan="3">${data.entity.postnum}</td>
            </tr>
            <tr>
            	<td>联系人：</td>
            	<td>${data.entity.contacts}</td>
            	<td>联系电话：</td>
            	<td>${data.entity.contactmobile}</td>
            </tr>
            <tr>
            	<td>快递公司：</td>
            	<td><u:ucparams key="${data.entity.logistics_company}"  type="logistics_company_code"/></td>
            	<td>快递单号：</td>
            	<td>${data.entity.logistics_no}</td>
            </tr>
          </tbody>
        </table>
        
        <s:if test="data.entity.status==1"><%--状态是待审核 --%>
	        <%-- <u:authority menuId="128"> --%>
	        	<div id="div_operate">
			        <div class="clear"></div>
			        <hr class="hr" />
			        <p class="btn">
			          <input type="button" value="审核通过" class="org_btn" onclick="showBox('audit_box')" />
			          <input type="button" value="审核不通过" class="grey_btn" onclick="showAuditUnBox()" />
			        </p>
	        	</div>
	       <%--  </u:authority> --%>
        </s:if>
        <s:if test="data.entity.status==2"><%--状态是待审核 --%>
        	<%-- <u:authority menuId="130"> --%>
	        	<div id="div_operate">
			        <div class="clear"></div>
			        <hr class="hr" />
			        <p class="btn">
			          <input type="button" value="邮寄" class="org_btn" onclick="showBox('post_bill_box')" />
			        </p>
	        	</div>
        	<%-- </u:authority> --%>
        </s:if>
      </div>

<!--弹层(审核通过)-->
<div class="float_box audit_box" id="audit_box" style="display:none;">
  <div class="float_tit">
    <h3>审核通过</h3>
  </div>
  <div class="float_ctn">
    <form method="post" id="audit_form">
      <p>是否确认发票通过审核</p>
      <span name="msg" class="error" style="display:none;"></span>
      <p class="btn">
        <input type="button" value="确 定" onclick="audit(this, 'audit_box', 1)" />
        <input type="button" value="取 消" onclick="hideBox('audit_box')" class="cancel_btn grey_btn" />
      </p>
    </form>
  </div>
</div>

<!--弹层(邮寄)-->
<div class="float_box audit_box" id="post_bill_box" style="display:none;">
  <div class="float_tit">
    <h3>邮寄</h3>
  </div>
  <div class="float_ctn">
    <form method="post" id="audit_form_post">
      <p>是否确认发票邮寄?<br/>
      <u:select id="logistics_company" placeholder="快递公司" dictionaryType="logistics_company_code"/>
      <input type="text" placeholder="快递单号" class="txt_177" id="logistics_no" name="logistics_no" />
      </p>
      <span name="msg" class="error" style="display:none;"></span>
      <p class="btn">
        <input type="button" value="确 定" onclick="postBill(this, 'post_bill_box')" />
        <input type="button" value="取 消" onclick="hideBox('post_bill_box')" class="cancel_btn grey_btn" />
      </p>
    </form>
  </div>
</div>

<!--弹层(审核不通过)-->
<div class="float_box audit_box" id="audit_un_box" style="display:none;">
  <div class="float_tit">
    <h3>审核不通过</h3>
  </div>
  <div class="float_ctn">
    <form method="post" id="audit_un_form">
      <p>填写审核不通过原因：<br />
        <textarea id="audit_desc" name="audit_desc" maxlength="500"></textarea>
      </p>
      
      <u:select id="no_pass_reason" placeholder="快速填写不通过原因" dictionaryType="qualification_no_pass_reason" excludeValue="" callback="selectReason"/>
      <div class="clear"></div>
      <span name="msg" class="error" style="display:none;"></span>
      <p class="btn">
        <input type="button" value="确 定" onclick="audit(this, 'audit_un_box', 0)" />
        <input type="button" value="取 消" onclick="hideBox('audit_un_box')" class="cancel_btn grey_btn" />
      </p>
    </form>
  </div>
</div>

<script type="text/javascript">
var validate;
var validate_post;
$(function(){
	//表单验证规则
	validate = $("#audit_un_form").validate({
		rules: {
			audit_desc: "required"
		},
		messages: {
			audit_desc: "请输入原因"
		}
	});
	
	validate_post = $("#audit_form_post").validate({
		rules: {
			logistics_company: "required",
			logistics_no:"required"
		},
		messages: {
			logistics_company: "请选择快递公司",
			logistics_no:"请填写快递单号"
		}
	});
	
});

//显示弹层(审核不通过)
function showAuditUnBox(){
	$("#audit_un_box label.error, #audit_un_box span.error").hide();
	showBox("audit_un_box");
}

//快速填写不通过原因
function selectReason(value, text){
	$("#audit_desc").val(text);
}

//审核
function audit(btn, boxId, type){
	var msg = $("#"+boxId+" [name='msg']");
	msg.hide();
	var audit_desc = "";
	if(type==0){
		if(!validate.form()){
			return;
		}
		audit_desc = $("#audit_desc").val();
	}
	
	$(btn).attr("disabled", true);
	$.ajax({
		type: "post",
		url: "${ctx}/invoiceAudit/audit",
		data:{
			id : "${data.entity.id}",
			sid : "${data.entity.sid}",
			bill_type : "${data.entity.type}",
			type : type,
			audit_desc : audit_desc
		},
		success: function(data){
			$(btn).attr("disabled", false);
			
			if(data.result==null){
				msg.text("服务器错误，请联系管理员").show();
				return;
			}
			if(data.result=="success"){
					hideBox(boxId);
				//setTimeout(function(){
				//	$("#oauth_status_name").text(type==0 ? "审核不通过" : "开票中");
				//	$("#div_operate").remove();
				//}, 1000);
				window.location.reload();
			}
			
			msg.text(data.msg).show();
		}
	});
}

//邮寄
function postBill(btn, boxId){
	var msg = $("#"+boxId+" [name='msg']");
	msg.hide();
	if(!validate_post.form()){
		return;
	}
	$(btn).attr("disabled", true);
	$.ajax({
		type: "post",
		url: "${ctx}/invoiceAudit/postBill",
		data:{
			id : "${data.entity.id}",
			sid : "${data.entity.sid}",
			email : "${data.entity.email}",
			bill_type : "${data.entity.type}",
			logistics_company : $("#logistics_company").val(),
			logistics_company_name:$("#logistics_company").next().text(),
			logistics_no : $("#logistics_no").val()
		},
		success: function(data){
			$(btn).attr("disabled", false);
			
			if(data.result==null){
				msg.text("服务器错误，请联系管理员").show();
				return;
			}
			if(data.result=="success"){
					hideBox(boxId);
				//setTimeout(function(){
				//	$("#oauth_status_name").text(type==0 ? "审核不通过" : "开票中");
				//	$("#div_operate").remove();
				//}, 1000);
				window.location.reload();
			}
			msg.text(data.msg).show();
		}
	});
}

function reloadCity(provinceValue, provinceText){
	$.post("${ctx}/common/loadCityByProvinceId",{provinceId : provinceValue},function(json){
		console.info(json);
		$("#div_city li").remove();
		$("#city").val("");
		$("#div_city span").text("");
		var html = "";
		$.each(json,function(i,o){
			html += "<li val=\""+o.value+"\" onClick = 'selectCity(this);'>"+o.text+"</li>";
		});
		$("#div_city ul").html(html);
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
	var city = $("#city").val();
}
</script> 
</body>
</html>