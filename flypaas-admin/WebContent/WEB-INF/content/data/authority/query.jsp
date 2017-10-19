<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/common/taglibs.jsp"%>

<u:authority menuId="36">
	<s:set var="menuId_36" value="true"/>
</u:authority>
<u:authority menuId="37">
	<s:set var="menuId_37" value="true"/>
</u:authority>

<html>
<head>
	<title>权限管理</title>
</head>

<body>
      <div class="main_ctn">
        <h1>权限管理</h1>
        <div class="main_search">
          <form method="post" id="mainForm" action="${ctx}/authority/query">
            <ul>
              <li>
                <input type="text" name="role_name" value="<s:property value="#parameters.role_name"/>" maxlength="20" placeholder="身份名称" class="txt_177" />
              </li>
              <li>
                <input type="submit" value="查 询" class="search" />
              </li>
            </ul>
            <u:authority menuId="35">
            	<a href="javascript:;" onclick="add()">添加管理身份</a>
            </u:authority>
          </form>
        </div>
        <div class="clear"></div>
        <div class="table_ctn">
          <table cellpadding="0" cellspacing="0" border="0">
            <tbody>
              <tr>
              	<th width="50px">序号</th>
                <th>身份id</th>
                <th>身份名称</th>
    			<th>状态</th>
                <th>操作</th>
              </tr>
              <s:iterator value="page.list">
	              <tr>
	              	<td>${rownum}</td>
	              	<td>${role_id}</td>
	              	<td>${role_name}</td>
	              	<td name="status_name">${status==0 ? "已删除" : "正常"}</td>
	                <td>
			      		<span name="operate_0" ${status==0 ? "" : "style='display:none;'"}>
					      <a href="javascript:;" onclick="updateStatus(this, '${role_id}', 1)">恢复</a>
					     </span>
			      		<span name="operate_1" ${status==0 ? "style='display:none;'" : ""}>
			      			<s:if test="menuId_36">
					      		<a href="javascript:;" onclick="edit('${role_id}')">编辑</a>
					      	</s:if>
					      	<s:if test="menuId_37">
					      		<s:if test="role_id != 1"><%--超级管理员不能删除--%>
						           	| <a href="javascript:;" onclick="updateStatus(this, '${role_id}', 0)">删除</a>
					      		</s:if>
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
	location.href="${ctx}/authority/add";
}
//编辑
function edit(role_id){
	location.href="${ctx}/authority/edit?role_id=" + role_id;
}

//修改状态：恢复、删除
function updateStatus(a, role_id, status){
	var status_name="";
	switch(status){
	case 1:	status_name = "正常";
			break;
	case 0:	status_name = "已删除";
			break;
	}
	
	if(confirm("确定要"+$(a).text()+"吗？")){
		$.ajax({
			type: "post",
			url: "${ctx}/authority/updateStatus",
			data:{
				role_id : role_id,
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