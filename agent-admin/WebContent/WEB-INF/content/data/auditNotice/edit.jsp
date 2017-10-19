<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/common/taglibs.jsp"%>

<s:set var="title" value="#parameters.notice_id==null ? '添加' : '修改'"/>
<s:set var="title_btn" value="#parameters.notice_id==null ? '添 加' : '修 改'"/>

<html>
<head>
	<title>${title}审核通知时段</title>
</head>

<body>
      <div class="main_ctn">
        <h1>${title}审核通知时段</h1>
        <div class="admin_material admin_form audit_notice">
          <form method="post" id="mainForm">
            <p>
              <label>通知时段名称</label>
              <input type="text" name="name" value="${data.view.name}" maxlength="100"/>
            </p>
            <p>
              <label>时段</label>
              	<u:date id="start_date" value="${data.view.start_date}" placeholder="开始时间" maxId="end_date,-1" dateFmt="HH:mm:ss" startDate="00:00:00" />
				<span>至</span>
		       	<u:date id="end_date" value="${data.view.end_date}" placeholder="结束时间" minId="start_date,1" dateFmt="HH:mm:ss" />
		       	
		       	<label id="start_date-error" class="error" for="start_date" style="display: none;"></label>
		       	<label id="end_date-error" class="error" for="end_date" style="display: none;"></label>
            </p>
            <dl>
              <dt>已添加时段</dt>
			  <dd>
			  	<s:iterator value="data.allTimeRange" status="s">
					<s:set var="test">${notice_id != data.view.notice_id}</s:set>
					<s:if test="#test">
						${name}（${start_date}至${end_date}）<br/>
					</s:if>
				</s:iterator>
				</dd>
            </dl>
			<div class="clear"></div>
            <p>
              <label>接收管理员</label>
              <u:selectMultiple id="sid" sqlId="adminMobile" value="${data.view.sid}"/>
            </p>
			<span id="msg" class="error" style="display:none;"></span>
            <hr class="hr" />
            <p class="btn">
              <input type="hidden" name="notice_id" value="${data.view.notice_id}" />
          	  <input type="hidden" name="is_update_user" id="is_update_user"/>
              <input type="button" value="${title_btn}" class="org_btn" onclick="save(this)"/>
              <input type="button" value="取 消" class="grey_btn" onclick="location.href='${ctx}/auditNotice/query'"/>
            </p>
          </form>
        </div>
      </div>

<span id="allTimeRange" style="display:none;">
	[
	<s:iterator value="data.allTimeRange">
		<s:set var="test">${notice_id != data.view.notice_id}</s:set>
		<s:if test="#test">
			${notBlank ? "," : ""}
			{"start_date" : "${start_date}", "end_date" : "${end_date}"}
			<s:set var="notBlank" value="true"/>
		</s:if>
	</s:iterator>
	]
</span>

<script type="text/javascript">
var old_sid;//修改前的接收管理员
var allTimeRange;//已添加时段
var validate;
$(function(){
	old_sid = $("#sid").serialize();
	allTimeRange = eval($("#allTimeRange").text());
	
	//表单验证规则
	validate = $("#mainForm").validate({
		rules: {
			name: "required",
			start_date: "required",
			end_date: "required",
			sid: {
				maxlength : 5
			}
		},
		messages: {
			name: "请输入通知时段名称",
			start_date: "请选择时段开始时间",
			end_date: "请选择时段结束时间",
			sid: "最多可以选择{0}个管理员"
		}
	});
});

//校验时段
function validateTimeRange(){
	if(allTimeRange.length==0){
		return true;
	}
	var start_date = $("#start_date").val();
	var end_date = $("#end_date").val();
	var flag = false;
	$.each(allTimeRange, function(i, n){
		if(i==0 && end_date<n.start_date){
			flag = true;
			return false;
		}
		if(i>0 && start_date>allTimeRange[i-1].end_date && end_date<n.start_date){
			flag = true;
			return false;
		}
		if(i==allTimeRange.length-1 && start_date>n.end_date){
			flag = true;
			return false;
		}
	});
	return flag;
}

function save(btn){
	$("#msg").hide();
	var flag = true;
	if(!validate.form()){
		flag =false;
	}
	if(!validateTimeRange()){
		flag =false;
		$("#msg").text("时段不正确，请重新输入").show();
	}
	if(!flag){
		return;
	}
	
	if(old_sid != $("#sid").serialize()){
		$("#is_update_user").val(true);
	} else {
		$("#is_update_user").val(false);
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
			}else if(data.result=="success"){
				setTimeout(function(){
					location.reload(true);
				}, 1000);
			}
			
			$("#msg").text(data.msg).show();
		},
		url : "${ctx}/auditNotice/save",
		type : "post",
		timeout:30000
	};
	$("#mainForm").ajaxSubmit(options);
}
</script>
</body>
</html>