<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/common/taglibs.jsp"%>
<!-- 
199	审核
200	试听
201	上传
 -->
<u:authority menuId="288">
	<s:set var="menuId_288" value="true"/>
</u:authority>
<u:authority menuId="289">
	<s:set var="menuId_289" value="true"/>
</u:authority>
<html>
<head>
	<title>号码审核</title>
</head>
<body>
	<div class="main_search">
        <form method="post" id="mainForm" action="${ctx}/showNbrsAudit/query">
          <ul>
            <li>
              <input type="text" name="text" value="<s:property value="#parameters.text"/>" placeholder="号码/应用名称/归属开发者" class="txt_227" />
            </li>
           <li class="time">
              	<u:date id="start_date" value="${param.start_date}" placeholder="开始时间" maxId="end_date,-1" maxToday="-1" />
				<span>至</span>
            	<u:date id="end_date" value="${param.end_date}" placeholder="结束时间" minId="start_date,1" maxToday=""  />
            </li>
            <li>
            	<u:select id="status" value="${param.status}" placeholder="审核状态" dictionaryType="shownbr_status" includeValue=",2,3,4" />
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
              <th>开发者账号</th>
              <th>应用ID</th>
              <th>应用名称</th>
              <th>号码</th>
              <th>创建时间</th>
              <th>更新时间</th>
              <th>有效时间</th>
              <th>审核状态</th>
              <th>操作</th>
            </tr>
            <s:iterator value="page.list">
	            <tr>
	            	<td>${rownum}</td>
	            	<td>${email}</td>
	            	<td>${app_sid}</td>
	            	<td>${app_name}</td>
	            	<td>${nbr}</td>
	            	<td>${create_date}</td>
	            	<td>${update_date}</td>
	            	<td>${exp_date}</td>
	            	<td id="${id}_audit_name"><u:ucparams key="${status}" type="shownbr_status"/></td>
	            	<td>
	            		<s:if test="status==2">
	            			<s:if test="menuId_288">
	            				<a href="javascript:;" onclick="pass_showBox('${id}','${sid}','${email}','${app_sid}');">通过</a>
	            				| <a href="javascript:;" onclick="unpass_showBox('${id}','${sid}','${email}','${app_sid}');">不通过</a>
	            			</s:if>
						</s:if>
	            		<s:if test="status==3">
	            			<s:if test="menuId_288">
	            				<a href="javascript:;" onclick="unpass_showBox('${id}','${sid}','${email}','${app_sid}');">关闭</a>
	            			</s:if>
						</s:if>
						<s:elseif test="status==4">
	            			<s:if test="menuId_289">
								<a href="javascript:;" onclick="unpass_showReason(this, '${id}','${sid}','${audit_desc}')" class="reasons">补充原因</a>
								<textarea id="audit_desc_${id}" style="display:none;">${audit_desc}</textarea>
							</s:if>
	            		</s:elseif>
					</td>
	            </tr>
	        </s:iterator>
          </tbody>
        </table>
		<u:page page="${page}" formId="mainForm" />
      </div>
<!--弹层(审核通过)-->
<div class="float_box audit_box" id="audit_box_pass" style="display:none;">
  <div class="float_tit">
    <h3>审核通过</h3>
  </div>
  <div class="float_ctn">
    <form method="post" id="audit_form">
      <p>是否确认此号码通过审核</p>
      <input type="hidden" id="pass_audit_id" name="id"/>
      <input type="hidden" id="pass_audit_sid" name="sid"/>
      <input type="hidden" id="pass_audit_app_sid" name="app_sid"/>
      <input type="hidden" id="pass_audit_email" name="email"/>
      <input type="hidden" id="pass_audit_type" name="type" value="1" />
      <p>有效时间 <u:date id="exp_date" value="${exp_date_default}" minToday="-1"/></p>
      <span name="msg" class="error" style="display:none;"></span>
      <p class="btn">
        <input type="button" value="确 定" onclick="audit(this,'audit_form','audit_box_pass',1);" />
        <input type="button" value="取 消" onclick="hideBox('audit_box_pass')" class="cancel_btn grey_btn" />
      </p>
    </form>
  </div>
</div>

<!--弹层(审核不通过)-->
<div class="float_box audit_box" id="audit_box_unpaas" style="display:none;">
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
	      <input type="hidden" id="unpass_audit_id" name="id"/>
	      <input type="hidden" id="unpass_audit_sid" name="sid"/>
	      <input type="hidden" id="unpass_audit_app_sid" name="app_sid"/>
	      <input type="hidden" id="unpass_audit_email" name="email"/>
	      <input type="hidden" id="unpass_audit_type" name="type" value="0" />
        <input type="button" value="确 定" onclick="audit(this, 'audit_un_form','audit_box_unpaas',0)" />
        <input type="button" value="取 消" onclick="hideBox('audit_box_unpaas')" class="cancel_btn grey_btn" />
      </p>
    </form>
  </div>
</div>

<!--补充原因弹层-->
<div class="float_box audit_box" id="audit_add_reason_box" style="display:none;">
  <div class="float_tit">
    <h3>审核不通过</h3>
  </div>
  <div class="float_ctn">
  	<form method="post" id="audit_un_form_reason">
      <p>填写应用审核不通过原因：<br />
        <textarea id="audit_desc_reason" name="audit_desc" maxlength="500"></textarea>
      </p>
      <u:select id="no_pass_reason_add" placeholder="快速填写不通过原因" dictionaryType="appAudit_no_pass_reason" excludeValue="" callback="selectReason_add"/>
      <div class="clear"></div>
      <span id="msg" class="error" style="display:none;"></span>
      <p class="btn">
      	 <input type="hidden" id="unpass_audit_reason" name="id"/>
      	 <input type="hidden" id="unpass_sid_reason" name="sid"/>
        <input type="button" value="确 定" onclick="saveReason(this, 'audit_un_form_reason','audit_add_reason_box')" />
        <input type="button" value="取 消" onclick="hideBox('audit_add_reason_box')" class="cancel_btn grey_btn" />
      </p>
    </form>
  </div>
</div>

<script type="text/javascript">
	$(function(){
		//表单验证规则
		validate_obj = $("#audit_un_form").validate({
			rules: {
				audit_desc: "required"
			},
			messages: {
				audit_desc: "请输入原因"
			}
		});
		validate_obj2 = $("#audit_un_form_reason").validate({
			rules: {
				audit_desc: "required"
			},
			messages: {
				audit_desc: "请输入原因"
			}
		});
	});

function pass_showBox(id,sid,email,app_sid){
	$("#pass_audit_id").val(id);
	$("#pass_audit_sid").val(sid);
	$("#pass_audit_app_sid").val(app_sid);
	$("#pass_audit_email").val(email);
	showBox('audit_box_pass');
};

function unpass_showBox(id,sid,email,app_sid){
	$("#unpass_audit_id").val(id);
	$("#unpass_audit_sid").val(sid);
	$("#unpass_audit_app_sid").val(app_sid);
	$("#unpass_audit_email").val(email);
	showBox('audit_box_unpaas');
};


//显示原因
function unpass_showReason(a,id,sid,reason){
	$("#unpass_audit_reason").val(id);
	$("#unpass_sid_reason").val(sid);
	$("#audit_desc_reason").val(reason);
	$("#audit_add_reason_box label.error, #audit_add_reason_box span.error").hide();
	showBox("audit_add_reason_box");
}

//快速填写不通过原因
function selectReason(value, text){
	$("#audit_desc").val(text);
}
//快速填写不通过原因
function selectReason_add(value, text){
	$("#audit_desc_reason").val(text);
}

//保存补充原因
function saveReason(btn,formId, boxId){
	$("#msg").hide();
	if(!validate_obj2.form()){
		return;
	}
	var audit_desc = $("#audit_desc_reason").val();
	$(btn).attr("disabled", true);
	var formData = $("#" + formId).serializeArray();
	var params = {};
	$.each(formData,function(i,o){
		params[o.name] = o.value;
	});
	params['audit_desc'] = audit_desc;
	$.ajax({
		type: "post",
		url: "${ctx}/showNbrsAudit/saveReason",
		data:params,
		success: function(data){
			$(btn).attr("disabled", false);
			
			if(data.result==null){
				$("#msg").text("服务器错误，请联系管理员").show();
				return;
			}else if(data.result=="success"){
				setTimeout(function(){
					hideBox(boxId);
					$("#mainForm").submit();
				}, 1000);
			}
			$("#msg").text(data.msg).show();
		}
	});
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
function audit(btn, formId,boxId,type){
	var msg = $("#"+boxId+" [name='msg']");
	msg.hide();
	var audit_desc = "";
	if(type==0){
		if(!validate_obj.form()){
			return;
		}
		audit_desc = $("#audit_desc").val();
	}
	var exp_date = $("#exp_date").val();
	if($.isEmptyObject(exp_date)){
		msg.text("请输入有效时间").show();
		return false;
	}
	
	var formData = $("#" + formId).serializeArray();
	var params = {};
	$.each(formData,function(i,o){
		params[o.name] = o.value;
	});
	params['audit_desc'] = audit_desc;
	$(btn).attr("disabled", true);
	$.ajax({
		type: "post",
		url: "${ctx}/showNbrsAudit/audit",
		data:params,
		success: function(data){
			$(btn).attr("disabled", false);
			if(data.result==null){
				msg.text("服务器错误，请联系管理员").show();
				return;
			}
			if(data.result=="success"){
				setTimeout(function(){
					hideBox(boxId);
					$("#mainForm").submit();
				}, 1000);
			}
			msg.text(data.msg).show();
		}
	});
}
</script>
</body>
</html>