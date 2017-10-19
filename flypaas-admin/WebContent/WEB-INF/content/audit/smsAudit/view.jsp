<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/common/taglibs.jsp"%>

<html>
<head>
	<title>短信模板审核 - 查看</title>
</head>

<body>
	<h1><a href="javascript:;" class="back" onclick="back()">返 回</a>短信模板审核/审核模板</h1>
      <div class="main_ctn1 qualification_view">
        <table cellpadding="0" cellspacing="0" border="0">
          <tbody>
            <tr>
              <th>模板id</th>
              <th>模板名称</th>
              <th>短信签名</th>
              <th>归属应用</th>
              <th>归属开发者</th>
              <th>模板类型</th>
              <th>提交时间</th>
              <th>审核状态</th>
            </tr>
            <tr>
              <td>${data.template_id}</td>
              <td>${data.name}</td>
              <td>${data.sign}</td>
              <td>${data.app_name}</td>
              <td>${data.email}</td>
              <td><u:ucparams key="${data.type_fmt}" type="sms_template_type"/></td>
              <td>${data.update_date}</td>
              <td id="status_name"><u:ucparams key="${data.status}" type="sms_template_status"/></td>
            </tr>
          </tbody>
        </table>
       	<table cellpadding="0" cellspacing="0" border="0">
          <tbody>
            <tr>
              <th class="tb_tit" width="100px">短信模板内容</th>
              <td><s:property value="data.content"/></td>
            </tr>
          </tbody>
        </table>
        
        <s:if test="data.status==1"><%--状态是待审核 --%>
        	<div id="div_operate">
		        <div class="clear"></div>
		        <s:if test="data.keyword != null">
		        	<span class="error" id="keyword">
		        		短信模板内容包含需要过滤的敏感字：${data.keyword}，不能审核通过
		        	</span>
		        </s:if>
		        <hr class="hr" />
		        <p class="btn">
		          <input type="button" value="审核通过" class="org_btn" onclick="showAuditBox()" />
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
      <p>是否确认短信模板通过审核</p>
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
      <p>填写短信模板审核不通过原因：<br />
        <textarea id="audit_desc" name="audit_desc" maxlength="500"></textarea>
      </p>
      
      <u:select id="no_pass_reason" placeholder="快速填写不通过原因" dictionaryType="smsAudit_no_pass_reason" excludeValue="" callback="selectReason"/>
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

//显示弹层(审核通过)
function showAuditBox(){
	var keyword = $("#keyword");
	if(keyword.length>0){
		alert($.trim(keyword.text()));
		return;
	}
	
	showBox("audit_box");
}

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
		url: "${ctx}/smsAudit/audit",
		data:{
			template_id : "${data.template_id}",
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
					$("#status_name").text(type==0 ? "审核不通过" : "审核通过");
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