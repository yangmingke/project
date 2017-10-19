<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/common/taglibs.jsp"%>

<u:authority menuId="78">
	<s:set var="menuId_78" value="true"/>
</u:authority>
<u:authority menuId="79">
	<s:set var="menuId_79" value="true"/>
</u:authority>
<u:authority menuId="80">
	<s:set var="menuId_80" value="true"/>
</u:authority>

<html>
<head>
	<title>短信模板审核</title>
</head>

<body>
	<div class="main_search">
        <form method="post" id="mainForm" action="${ctx}/smsAudit/query">
          <ul>
            <li>
              <input type="text" name="text" value="<s:property value="#parameters.text"/>" placeholder="模板ID/模板名称/短信签名/归属开发者" class="txt_227" />
            </li>
            <li class="time">
              	<u:date id="start_date" value="${param.start_date}" placeholder="开始时间" maxId="end_date,-1" maxToday="-1" />
				<span>至</span>
            	<u:date id="end_date" value="${param.end_date}" placeholder="结束时间" minId="start_date,1" maxToday=""  />
            </li>
            <li>
            	<u:select id="status" value="${param.status}" placeholder="审核状态" dictionaryType="sms_template_status" includeValue=",1,2,3" />
            </li>
            <li>
              <input type="submit" value="查 询" class="search" />
            </li>
          </ul>
        </form>
      </div>
      <div class="clear"></div>
      <div class="table_ctn">
        <table class="noEdge">
          <tbody>
            <tr>
              <th width="50px">序号</th>
              <th>模板ID</th>
              <th>模板名称</th>
              <th>短信签名</th>
              <th>内容</th>
              <th>归属应用</th>
              <th>归属开发者</th>
              <th>模板类型</th>
              <th>提交时间</th>
              <th>审核状态</th>
              <th>操作</th>
            </tr>
            <s:iterator value="page.list">
	            <tr>
	            	<td>${rownum}</td>
	            	<td>${template_id}</td>
	            	<td>${name}</td>
	            	<td>
	            		<u:truncate length="5" value="${sign}"/>
	            	</td>
	            	<td>
	            		<u:truncate length="10" value="${content}"/>
	            	</td>
	            	<td>${app_name}</td>
	            	<td>${email}</td>
	            	<td><u:ucparams key="${type_fmt}" type="sms_template_type"/></td>
	            	<td>${update_date}</td>
	            	<td><u:ucparams key="${status}" type="sms_template_status"/></td>
	            	<td>
	            		<s:if test="status==1">
	            			<s:if test="menuId_79">
								<a href="javascript:;" onclick="view('${template_id}')">审核</a>
							</s:if>
	            		</s:if>
	            		<s:elseif test="status==2">
	            			<s:if test="menuId_78">
								<a href="javascript:;" onclick="view('${template_id}')">查看</a>
							</s:if>
	            		</s:elseif>
	            		<s:elseif test="status==3">
	            			<s:if test="menuId_78">
								<a href="javascript:;" onclick="view('${template_id}')">查看</a>
							</s:if>
	            			<s:if test="menuId_80">
								| <a href="javascript:;" onclick="showReason(this, '${template_id}')" class="reasons">补充原因</a>
								<textarea id="audit_desc_${template_id}" style="display:none;">${audit_desc}</textarea>
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
      <p>填写短信模板审核不通过原因：<br />
        <textarea id="audit_desc" name="audit_desc" maxlength="500"></textarea>
      </p>
      
      <u:select id="no_pass_reason" placeholder="快速填写不通过原因" dictionaryType="smsAudit_no_pass_reason" excludeValue="" callback="selectReason"/>
      <div class="clear"></div>
      <span id="msg" class="error" style="display:none;"></span>
      <p class="btn">
      	<input type="hidden" id="template_id" />
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
function view(template_id){
	location.href="${ctx}/smsAudit/view?template_id=" + template_id;
}

//显示原因
function showReason(a, template_id){
	$("#template_id").val(template_id);
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
	
	var template_id = $("#template_id").val();
	var audit_desc = $("#audit_desc").val();
	$(btn).attr("disabled", true);
	$.ajax({
		type: "post",
		url: "${ctx}/smsAudit/saveReason",
		data:{
			template_id : template_id,
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
					$("#audit_desc_"+template_id).val(audit_desc);
				}, 1000);
			}
			
			$("#msg").text(data.msg).show();
		}
	});
}
</script>
</body>
</html>