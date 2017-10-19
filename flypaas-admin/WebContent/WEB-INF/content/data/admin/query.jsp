<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/common/taglibs.jsp"%>

<u:authority menuId="33">
	<s:set var="menuId_33" value="true"/>
</u:authority>
<u:authority menuId="34">
	<s:set var="menuId_34" value="true"/>
</u:authority>

<html>
<head>
	<title>管理员列表</title>
</head>

<body>
   <div class="main_ctn">
     <h1>管理员列表</h1>
     <div class="main_search">
   <form method="post" id="mainForm" action="${ctx}/admin/query">
   <ul>
   <li>
   	<input type="text" name="text" value="<s:property value="#parameters.text"/>" maxlength="40" placeholder="管理员id/管理员账号/联系手机/真实姓名" class="txt_250" />
   </li>
  <li>
	<u:select id="role_id" value="${param.role_id}" placeholder="管理员身份" sqlId="role" />
  </li>
   <li><input type="submit" value="查 询" class="search" /></li>
   </ul>
   <u:authority menuId="32">
   		<a href="javascript:;" onclick="add()">添加管理员</a>
   </u:authority>
   </form>
   </div>
   <div class="clear"></div>
   <div class="table_ctn">
   <table cellpadding="0" cellspacing="0" border="0">
   <tbody>
    <tr>
    	<th width="50px">序号</th>
    	<th>管理员id</th>
    	<th>管理员账号</th>
    	<th>联系手机</th>
    	<th>管理员身份</th>
    	<th>真实姓名</th>
    	<th>状态</th>
    	<th>创建时间</th>
    	<th>操作</th>
    </tr>
    <s:iterator value="page.list">
     <tr>
      <td>${rownum}</td>
      <td>${sid}</td>
      <td>${email}</td>
      <td>${mobile}</td>
      <td>${role_name}</td>
      <td>${realname}</td>
      <td name="status_name">${status==6 ? "已删除" : "正常"}</td>
      <td>${create_date}</td>
      <td>
      		<span name="operate_6" ${status==6 ? "" : "style='display:none;'"}>
		      <a href="javascript:;" onclick="updateStatus(this, '${sid}', 1)">恢复</a>
		     </span>
      		<span name="operate_1" ${status==6 ? "style='display:none;'" : ""}>
      			<s:if test="menuId_33">
		      		<a href="javascript:;" onclick="edit('${sid}')">编辑</a>
      			</s:if>
      			<s:if test="menuId_34">
		     		| <a href="javascript:;" onclick="updateStatus(this, '${sid}', 6)">删除</a>
		      	</s:if>
		     </span>
      </td>
     </tr>
    </s:iterator>
   </tbody>
   </table>
   
   <u:page page="${page}" formId="mainForm" />
   </div>
   </div>
      
<script type="text/javascript">
//添加
function add(){
	location.href="${ctx}/admin/add";
}

//编辑
function edit(sid){
	location.href="${ctx}/admin/edit?sid=" + sid;
}

//修改状态：恢复、删除
function updateStatus(a, sid, status){
	var status_name="";
	switch(status){
	case 1:	status_name = "正常";
			break;
	case 6:	status_name = "已删除";
			break;
	}
	
	if(confirm("确定要"+$(a).text()+"吗？")){
		$.ajax({
			type: "post",
			url: "${ctx}/admin/updateStatus",
			data:{
				sid : sid,
				status : status
			},
			success: function(data){
				if(data.result==null){
					alert("服务器错误，请联系管理员");
					return;
				}
				
				if(data.result=="success"){
					$(a).parents("tr").remove();
					/* 
					$(a).parent().hide();
					$(a).parents("tr").find("[name='operate_"+status+"']").show();
					$(a).parents("tr").find("[name='status_name']").text(status_name);
					*/
				}
				alert(data.msg);
			}
		});
	}
}

</script>
</body>
</html>