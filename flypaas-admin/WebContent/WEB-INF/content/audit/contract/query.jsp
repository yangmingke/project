<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/common/taglibs.jsp"%>

<u:authority menuId="115">
	<s:set var="menuId_115" value="true"/>
</u:authority>
<u:authority menuId="116">
	<s:set var="menuId_116" value="true"/>
</u:authority>
<u:authority menuId="117">
	<s:set var="menuId_117" value="true"/>
</u:authority>

<html>
<head>
	<title>协议用户审核</title>
</head>

<body>
	<div class="main_search">
        <form method="post" id="mainForm" action="${ctx}/contract/query">
          <ul>
            <li>
              <input type="text" name="text" value="<s:property value="#parameters.text"/>" placeholder="开发者ID/用户名/手机号" class="txt_177" />
            </li>
           <li class="time">
              	<u:date id="start_date" value="${param.start_date}" placeholder="开始时间" maxId="end_date,-1" maxToday="-1" />
				<span>至</span>
            	<u:date id="end_date" value="${param.end_date}" placeholder="结束时间" minId="start_date,1" maxToday=""  />
            </li>
            <li>
            	<u:select id="status" value="${param.status}" placeholder="审核状态" dictionaryType="security_status" includeValue=",1,2,3" />
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
              <th>开发者ID</th>
              <th>用户名</th>
              <th>手机号</th>
              <th>企业名称</th>
              <th>提交时间</th>
              <th>状态</th>
              <th>操作</th>
            </tr>
            <s:iterator value="page.list">
	            <tr>
	            	<td>${rownum}</td>
	            	<td>${sid}</td>
	            	<td>${email}</td>
	            	<td>${mobile}</td>
	            	<td><u:truncate length="20" value="${realname}"/></td>
	            	<td>${update_date}</td>
	            	<td><u:ucparams key="${status}" type="security_status"/></td>
	            	<td>
	            		<s:if test="menuId_115">
							<a href="javascript:;" onclick="view('${sid}')">查看</a>
						</s:if>
	            		<s:if test="status==1">
	            			<s:if test="menuId_116">
								| <a href="javascript:;" onclick="view('${sid}')">审核</a>
							</s:if>
	            		</s:if>
	            		<s:if test="status==2">
	            			<s:if test="menuId_116">
								| <a href="javascript:;" onclick="setContract('${sid}','${email}')">设置协议用户</a>
							</s:if>
	            		</s:if>
	            		<s:elseif test="status==3">
	            			<s:if test="menuId_117">
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
function view(sid){
	location.href="${ctx}/contract/view?sid=" + sid;
}

function setContract(sid,email){
	if(window.confirm("您确定要设置 [ "+email+" ] 为协议用户?")){
		$.ajax({
			type: "post",
			url: "${ctx}/contract/setContract",
			data:{
				sid : sid
			},
			success: function(data){
				if(data.result==null){
					alert("服务器错误，请联系管理员");
					return;
				}else if(data.result=="success"){
					setTimeout(function(){
						alert(data.msg);
						$("#mainForm").submit();
					}, 1000);
				}else{
					alert(data.msg);
				}
			}
		});
	}
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