<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/common/taglibs.jsp"%>

<u:authority menuId="261">
	<s:set var="menuId_261" value="true"/>
</u:authority>
<u:authority menuId="262">
	<s:set var="menuId_262" value="true"/>
</u:authority>
<u:authority menuId="263">
	<s:set var="menuId_263" value="true"/>
</u:authority>

<html>
<head>
	<title>策略权限过滤列表</title>
</head>

<body>
   <div class="main_ctn">
     <h1>策略权限过滤列表</h1>
     <div class="main_search">
   <form method="post" id="mainForm" action="${ctx}/permissionFilter/query">
   <ul>
   <li>
   	<input type="text" name="text" value="<s:property value="#parameters.text"/>" placeholder="用户帐号" maxlength="40" class="txt_177" />
   </li>
   <li><input type="submit" value="查 询" class="search" /></li>
   </ul>
   <u:authority menuId="260">
   		<a href="javascript:;" onclick="add()">添加</a>
   </u:authority>
   </form>
   </div>
   <div class="clear"></div>
   <div class="table_ctn">
   <table cellpadding="0" cellspacing="0" border="0">
   <tbody>
    <tr>
    	<th>序号</th>
    	<th>id</th>
    	<th>用户帐号</th>
    	<th>手机平台</th>
    	<th>客户端SDK版本</th>
    	<th>系统版本号</th>
    	<th>品牌</th>
    	<th>型号</th>
    	<th>配置权限</th>
    	<th>描述信息</th>
    	<th>创建时间</th>
    	<th>操作</th>
    </tr>
    <s:iterator value="page.list">
     <tr>
      <td>${rownum}</td>
      <td>${id}</td>
      <td><u:truncate value="${uid}" length="10" /></td>
      <td><u:ucparams type="cps_pv" key="${pv}" /></td>
      <td><u:truncate value="${ver}" length="10" /></td>
      <td><u:truncate value="${osv}" length="10" /></td>
      <td><u:truncate value="${brand}" length="10" /></td>
      <td><u:truncate value="${model}" length="10" /></td>
      <td><u:truncate value="${permission}" length="10" /></td>
      <td><u:truncate value="${describe}" length="10" /></td>
      <td>${createtime}</td>
      <td>
      		<s:if test="menuId_261">
				<a href="javascript:;" onclick="view(${id})">查看</a>
			</s:if>
      		<s:if test="menuId_262">
				| <a href="javascript:;" onclick="edit(${id})">编辑</a>
			</s:if>
      		<s:if test="menuId_263">
				| <a href="javascript:;" onclick="deleteData(${id})">删除</a>
			</s:if>
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
	location.href="${ctx}/permissionFilter/add";
}

//查看
function view(id){
	location.href="${ctx}/permissionFilter/view?id=" + id;
}

//编辑
function edit(id){
	location.href="${ctx}/permissionFilter/edit?id=" + id;
}

//删除
function deleteData(id){
	if(confirm("确定要删除吗？")){
		$.ajax({
			type: "post",
			url: "${ctx}/permissionFilter/delete",
			data:{
				id : id
			},
			success: function(data){
				if(data.result==null){
					alert("服务器错误，请联系管理员");
					return;
				}
				
				alert(data.msg);
				if(data.result=="success"){
					location.reload(true);
				}
			}
		});
	}
}
</script>
</body>
</html>