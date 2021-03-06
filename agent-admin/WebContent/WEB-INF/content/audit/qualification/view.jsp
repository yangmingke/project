<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/common/taglibs.jsp"%>

<html>
<head>
	<title>资质审核 - 查看(${data.developer.user_type_name})</title>
</head>

<body>
	<h1><a href="javascript:;" class="back" onclick="back()">返 回</a>资质审核/审核资质</h1>
      <div class="main_ctn1 qualification_view">
        <table cellpadding="0" cellspacing="0" border="0">
          <tbody>
            <tr>
              <th>开发者id</th>
              <th>用户名</th>
              <th>手机号</th>
              <th>认证名称</th>
              <th>资质类型</th>
              <th>提交时间</th>
              <th>认证状态</th>
            </tr>
            <tr>
              <td>${data.developer.sid}</td>
              <td>${data.developer.email}</td>
              <td>${data.developer.mobile}</td>
              <td>${data.developer.realname}</td>
              <td>${data.developer.user_type_name}</td>
              <td>${data.developer.update_date}</td>
              <td id="oauth_status_name">${data.developer.oauth_status_name}</td>
            </tr>
          </tbody>
        </table>
        
        <s:if test="data.developer.user_type==2"><%--企业 --%>
	       	<table cellpadding="0" cellspacing="0" border="0" class="table_2">
	          <tbody>
	            <tr>
	              <th class="tb_tit">公司地址</th>
	              <th class="tb_ctn">${data.developer.address}</th>
	            </tr>
	          </tbody>
	        </table>
        </s:if>
        
        <table cellpadding="0" cellspacing="0" border="0" class="table_2">
          <tbody>
            <tr>
              <th>证件类型</th>
              <th>证件号码</th>
              <th>证件照片</th>
            </tr>
            <s:iterator value="data.id">
	            <tr>
	              <td class="tb_tit">${id_type_name}</td>
	              <td class="tb_ctn">${id_nbr}</td>
	              <td class="tb_ctn">
					<img src="${ctx}/file/view?encode_path=<u:des3 value="${img_url}" />" width="50" height="35" style="cursor:pointer;" title="点击查看大图" onclick="viewFile(this)"/>
	              </td>
	            </tr>
            </s:iterator>
          </tbody>
        </table>
        
        <s:if test="data.developer.user_type==2"><%--企业 --%>
        	<table cellpadding="0" cellspacing="0" border="0" class="table_2">
	          <tbody>
	            <tr>
	              <td class="tb_tit">法定代表人</td>
	              <td class="tb_ctn">${data.developer.legal_person}</td>
	            </tr>
	            <tr>
	              <td class="tb_tit">公司电话</td>
	              <td class="tb_ctn">${data.developer.company_nbr}</td>
	            </tr>
	            <tr>
	              <td class="tb_tit">公司网站</td>
	              <td class="tb_ctn">${data.developer.web_site}</td>
	            </tr>
	          </tbody>
	        </table>
        </s:if>
        
        <s:if test="data.developer.oauth_status==2"><%--状态是待审核 --%>
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
      <p>是否确认资质通过审核</p>
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
		url: "${ctx}/qualification/audit",
		data:{
			sid : "${data.developer.sid}",
			email : "${data.developer.email}",
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
					$("#oauth_status_name").text(type==0 ? "认证不通过" : "证件已认证");
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