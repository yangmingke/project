<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/common/taglibs.jsp"%>

<u:authority menuId="75">
	<s:set var="menuId_75" value="true"/>
</u:authority>
<u:authority menuId="76">
	<s:set var="menuId_76" value="true"/>
</u:authority>
<u:authority menuId="77">
	<s:set var="menuId_77" value="true"/>
</u:authority>

<html>
<head>
	<title>应用审核</title>
</head>

<body>
	<div class="main_search">
        <form method="post" id="mainForm" action="${ctx}/appAudit/query">
          <ul>
            <li>
              <input type="text" name="text" value="<s:property value="#parameters.text"/>" placeholder="应用ID/应用名称/归属开发者" class="txt_227" />
            </li>
           <li class="time">
              	<u:date id="start_date" value="${param.start_date}" placeholder="开始时间" maxId="end_date,-1" maxToday="-1" />
				<span>至</span>
            	<u:date id="end_date" value="${param.end_date}" placeholder="结束时间" minId="start_date,1" maxToday=""  />
            </li>
            <li>
            	<u:select id="status" value="${param.status}" placeholder="审核状态" dictionaryType="app_status" includeValue=",1,2,4" />
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
              <th>应用ID</th>
              <th>应用名称</th>
              <th>应用行业</th>
              <th>应用类型</th>
              <th>提交时间</th>
              <th>归属开发者</th>
              <th>审核状态</th>
              <th>操作</th>
            </tr>
            <s:iterator value="page.list">
	            <tr>
	            	<td>${rownum}</td>
	            	<td>${app_sid}</td>
	            	<td>${app_name}</td>
	            	<td><u:ucparams key="${industry}" type="industry"/></td>
	            	<td><u:ucparams key="${app_kind}" type="app_kind"/></td>
	            	<td>${update_date}</td>
	            	<td>${email}</td>
	            	<td><u:ucparams key="${status}" type="app_status"/></td>
	            	<td>
	            		<s:if test="status==4 || status==6">
							<a href="javascript:;" onclick="view('${app_sid}')">审核</a>
	            		</s:if>
	            		<s:elseif test="status==1">
							<a href="javascript:;" onclick="view('${app_sid}')">查看</a>
	            		</s:elseif>
	            		<s:elseif test="status==2">
							<a href="javascript:;" onclick="view('${app_sid}')">查看</a>
							| <a href="javascript:;" onclick="showReason(this, '${app_sid}')" class="reasons">补充原因</a>
							<textarea id="audit_desc_${app_sid}" style="display:none;">${audit_desc}</textarea>
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
      <p>填写应用审核不通过原因：<br />
        <textarea id="audit_desc" name="audit_desc" maxlength="500"></textarea>
      </p>
      
      <u:select id="no_pass_reason" placeholder="快速填写不通过原因" dictionaryType="appAudit_no_pass_reason" excludeValue="" callback="selectReason"/>
      <div class="clear"></div>
      <span id="msg" class="error" style="display:none;"></span>
      <p class="btn">
      	<input type="hidden" id="app_sid" />
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
function view(app_sid){
	location.href="${ctx}/appAudit/view?app_sid=" + app_sid;
}

//显示原因
function showReason(a, app_sid){
	$("#app_sid").val(app_sid);
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
	
	var app_sid = $("#app_sid").val();
	var audit_desc = $("#audit_desc").val();
	$(btn).attr("disabled", true);
	$.ajax({
		type: "post",
		url: "${ctx}/appAudit/saveReason",
		data:{
			app_sid : app_sid,
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
					$("#audit_desc_"+app_sid).val(audit_desc);
				}, 1000);
			}
			
			$("#msg").text(data.msg).show();
		}
	});
}
</script>
</body>
</html>