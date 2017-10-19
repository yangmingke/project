<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/common/taglibs.jsp"%>

<html>
<head>
	<title>应用审核 - 查看</title>
</head>

<body>
	<h1><a href="javascript:;" class="back" onclick="back()">返 回</a>应用审核/审核应用</h1>
      <div class="main_ctn1 qualification_view">
        <table cellpadding="0" cellspacing="0" border="0">
          <tbody>
            <tr>
              <th>应用id</th>
              <th>应用名称</th>
              <th>应用行业</th>
              <th>应用类型</th>
              <th>提交时间</th>
              <th>归属开发者</th>
              <th>审核状态</th>
            </tr>
            <tr>
              <td>${data.app.app_sid}</td>
              <td>${data.app.app_name}</td>
              <td><u:ucparams key="${data.app.industry}" type="industry"/></td>
	          <td><u:ucparams key="${data.app.app_kind}" type="app_kind"/></td>
              <td>${data.app.update_date}</td>
              <td>${data.app.email}</td>
              <td id="status_name"><u:ucparams key="${data.app.status}" type="app_status"/></td>
            </tr>
          </tbody>
        </table>
       	<table cellpadding="0" cellspacing="0" border="0" class="table_2">
          <tbody>
            <tr>
              <th>回调类型</th>
              <th>回调地址</th>
            </tr>
            <s:iterator value="data.callback">
	            <tr>
	              <td class="tb_tit">${call_type_name}</td>
	              <td class="tb_ctn">${call_address}</td>
	            </tr>
            </s:iterator>
          </tbody>
        </table>
        <table cellpadding="0" cellspacing="0" border="0" class="table_2">
          <tbody>
            <tr>
              <th class="tb_tit">服务器白名单</th>
              <th class="tb_ctn">${data.app.white_address}</th>
            </tr>
          </tbody>
        </table>
        <table cellpadding="0" cellspacing="0" border="0" class="table_2">
          <tbody>
            <tr>
              <td class="tb_tit">显号模式</td>
              <td class="tb_ctn">${data.app.is_show_nbr==1 ? "开启" : "关闭"}</td>
            </tr>
          </tbody>
        </table>
        
         <table cellpadding="0" cellspacing="0" border="0" class="table_2">
          <tbody>
            <tr>
              <td class="tb_tit">国际通话业务</td>
              <td class="tb_ctn">${data.app.call_fr==1 ? "开启" : "关闭"}</td>
            </tr>
			<tr>
              <td class="tb_tit">智能验证秘钥</td>
              <td class="tb_ctn">${data.app.ck_key}</td>
            </tr>
            <tr>
              <td class="tb_tit">智能验证回调地址</td>
              <td class="tb_ctn">${data.app.ck_callback_url}</td>
            </tr>
            <tr>
              <td class="tb_tit">智能验证有效时间</td>
              <td class="tb_ctn">${data.app.ck_enddate}分钟</td>
            </tr>
           <tr>
              <td class="tb_tit">智能验证验证类型</td>
              <td class="tb_ctn"><u:ucparams key="${data.app.ck_way}" type="cloud_check"/></td>
            </tr>
             <tr>
              <td class="tb_tit">身份验证最大值</td>
              <td class="tb_ctn">${data.app.ck_num}次</td>
            </tr>
          </tbody>
        </table>
        <s:if test="#parameters.view==null && (data.app.status==4 || data.app.status==6)"><%--不是查看且状态是待审核 --%>
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
      <p>是否确认应用通过审核</p>
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
      <p>填写应用审核不通过原因：<br />
        <textarea id="audit_desc" name="audit_desc" maxlength="500"></textarea>
      </p>
      
      <u:select id="no_pass_reason" placeholder="快速填写不通过原因" dictionaryType="appAudit_no_pass_reason" excludeValue="" callback="selectReason"/>
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
		url: "${ctx}/appAudit/audit",
		data:{
			app_sid : "${data.app.app_sid}",
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