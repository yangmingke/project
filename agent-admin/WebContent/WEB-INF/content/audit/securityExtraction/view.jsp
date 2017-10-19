<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/common/taglibs.jsp"%>

<html>
<head>
	<title>保障金提现审核 - 审核</title>
</head>

<body>
	<h1><a href="javascript:;" class="back" onclick="back()">返 回</a>保障金提现审核/审核</h1>
      <div class="main_ctn1 qualification_view">
        <table cellpadding="0" cellspacing="0" border="0">
          <tbody>
            <tr>
              <th>ID</th>
              <th>钱包id</th>
              <th>用户名</th>
              <th>保障金金额</th>
              <th>提现金额</th>
              <th>提交时间</th>
              <th>状态</th>
            </tr>
            <tr>
              <td>${data.entity.id}</td>
              <td>${data.entity.bid}</td>
              <td>${data.entity.email}</td>
              <td>${data.entity.balance_fmt}</td>
              <td>${data.entity.money_fmt}</td>
              <td>${data.entity.update_date_fmt}</td>
              <td id="oauth_status_name"><u:ucparams key="${data.entity.status}" type="security_relieve_applyfor_status"/></td>
            </tr>
          </tbody>
        </table>
        
     <table cellpadding="0" cellspacing="0" border="0" class="table_2">
          <tbody>
            <tr>
              <th colspan="4">银行信息</th>
            </tr>
            <tr>
            	<td>收款单位：</td>
            	<td>${data.entity.company}</td>
            </tr>
            <tr>
            	<td>银行账号：</td>
            	<td>${data.entity.banknum}</td>
            </tr>
             <tr>
           <td>开户行地址：</td>
            	<td>${data.entity.bankaddr }</td>
            </tr>
          </tbody>
        </table>
        
        <s:if test="data.entity.status==1"><%--状态是待审核 --%>
        	<div id="div_operate">
		        <div class="clear"></div>
		        <hr class="hr" />
		        <p class="btn">
		          <input type="button" value="审核通过" class="org_btn" onclick="showBox('audit_box')" />
		          <input type="button" value="审核不通过" class="grey_btn" onclick="showAuditUnBox()" />
		        </p>
        	</div>
        </s:if>
      </div>

<!--弹层(审核通过)-->
<div class="float_box audit_box" id="audit_box" style="display:none;">
  <div class="float_tit">
    <h3>审核通过</h3>
  </div>
  <div class="float_ctn">
    <form method="post" id="audit_form">
      <p>是否确认保障金提现通过审核</p>
      <span name="msg" class="error" style="display:none;"></span>
      <p class="btn">
        <input type="button" value="确 定" onclick="audit(this, 'audit_box', 1)" />
        <input type="button" value="取 消" onclick="hideBox('audit_box')" class="cancel_btn grey_btn" />
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
		url: "${ctx}/securityExtraction/audit",
		data:{
			id : "${data.entity.id}",
			sid : "${data.entity.sid}",
			email : "${data.entity.email}",
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
				setTimeout(function(){
					hideBox(boxId);
					$("#oauth_status_name").text(type==0 ? "审核不通过" : "审核通过");
					$("#div_operate").remove();
				}, 1000);
			}
			
			msg.text(data.msg).show();
		}
	});
}
</script> 
</body>
</html>