<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/common/taglibs.jsp"%>

<u:authority menuId="118">
	<s:set var="menuId_118" value="true"/>
</u:authority>
<u:authority menuId="119">
	<s:set var="menuId_119" value="true"/>
</u:authority>
<u:authority menuId="120">
	<s:set var="menuId_120" value="true"/>
</u:authority>

<html>
<head>
	<title>保障金提现审核</title>
</head>

<body>
	<div class="main_search">
        <form method="post" id="mainForm" action="${ctx}/securityExtraction/query">
          <ul>
            <li>
              <input type="text" name="text" value="<s:property value="#parameters.text"/>" placeholder="开发者ID/用户名" class="txt_177" />
            </li>
            <li>
            	<u:select id="status" value="${param.status}" placeholder="审核状态" dictionaryType="security_relieve_applyfor_status" includeValue=",0,1,2" />
            </li>
            <li>
              <input type="submit" value="查 询" class="search" />
            </li>
          </ul>
        </form>
      </div>
      <div class="clear"></div>
      <div class="table_ctn">
        <table cellpadding="0" cellspacing="0" border="0">
          <tbody>
            <tr>
              <th width="50px">序号</th>
              <th>ID</th>
              <th>钱包ID</th>
              <th>用户名</th>
              <th>保障金金额</th>
              <th>提现金额</th>
              <th>提交时间</th>
              <th>状态</th>
              <th>操作</th>
            </tr>
            <s:iterator value="page.list">
	            <tr>
	            	<td>${rownum}</td>
	            	<td>${id}</td>
	            	<td>${bid}</td>
	            	<td>${email}</td>
	            	<td>${balance_fmt}</td>
	            	<td>${money_fmt}</td>
	            	<td>${update_date_fmt}</td>
	            	<td><u:ucparams key="${status}" type="security_relieve_applyfor_status"/></td>
	            	<td>
	            		<s:if test="status==1">
	            			<s:if test="menuId_119">
								<a href="javascript:;" onclick="view('${id}')">审核</a>
							</s:if>
	            		</s:if>
	            		<s:elseif test="status==0">
	            			<s:if test="menuId_118">
								<a href="javascript:;" onclick="view('${id}')">查看</a>
							</s:if>
	            		</s:elseif>
	            		<s:elseif test="status==2">
	            			<s:if test="menuId_118">
								<a href="javascript:;" onclick="view('${id}')">查看</a>
							</s:if>
	            			<s:if test="menuId_120">
								| <a href="javascript:;" onclick="showReason(this, '${sid}')" class="reasons">补充原因</a>
								<textarea id="audit_desc_${sid}" style="display:none;">${audit_desc}</textarea>
							</s:if>
	            		</s:elseif>
					</td>
	            </tr>
	        </s:iterator>
          </tbody>
        </table>
		
		<u:page page="${page}" formId="mainForm" />
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
      <span id="msg" class="error" style="display:none;"></span>
       <p class="btn">
      	<input type="hidden" id="sid" />
        <input type="button" value="确 定" onclick="saveReason(this, 'audit_un_box')" />
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

//查看
function view(id){
	location.href="${ctx}/securityExtraction/view?id=" + id;
}

//显示原因
function showReason(a, sid){
	$("#sid").val(sid);
	$("#audit_desc").val($(a).siblings("textarea").val());
	$("#audit_un_box label.error, #audit_un_box span.error").hide();
	showBox("audit_un_box");
}

//快速填写不通过原因
function selectReason(value, text){
	$("#audit_desc").val(text);
}

//保存补充原因
function saveReason(btn, boxId){
	$("#msg").hide();
	if(!validate.form()){
		return;
	}
	
	var sid = $("#sid").val();
	var audit_desc = $("#audit_desc").val();
	$(btn).attr("disabled", true);
	$.ajax({
		type: "post",
		url: "${ctx}/contract/saveReason",
		data:{
			sid : sid,
			audit_desc : audit_desc
		},
		success: function(data){
			$(btn).attr("disabled", false);
			
			if(data.result==null){
				$("#msg").text("服务器错误，请联系管理员").show();
				return;
			}else if(data.result=="success"){
				setTimeout(function(){
					hideBox(boxId);
					$("#audit_desc_"+sid).val(audit_desc);
				}, 1000);
			}
			
			$("#msg").text(data.msg).show();
		}
	});
}
</script>
</body>
</html>