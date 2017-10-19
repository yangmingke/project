<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/common/taglibs.jsp"%>

<u:authority menuId="282">
	<s:set var="menuId_282" value="true"/>
</u:authority>
<u:authority menuId="283">
	<s:set var="menuId_283" value="true"/>
</u:authority>
<u:authority menuId="284">
	<s:set var="menuId_284" value="true"/>
</u:authority>

<s:set var="server">${param.server==null ? 1 : param.server}</s:set>

<html>
<head>
	<title>服务器地址列表</title>
</head>

<body>
   <div class="main_ctn">
     <h1>服务器地址列表</h1>
     <div class="main_search">
   <form method="post" id="mainForm" action="${ctx}/server/query">
   <ul>
  <li>
	<u:select id="server" value="${server}" placeholder="服务器类型" dictionaryType="cps_server" showAll="false" />
  </li>
   <li>
   	<input type="text" name="ipaddress" value="<s:property value="#parameters.ipaddress"/>" placeholder="ip地址" maxlength="40" class="txt_177" />
   </li>
   <li>
   	<input type="text" name="describe" value="<s:property value="#parameters.describe"/>" placeholder="描述信息" maxlength="40" class="txt_177" />
   </li>
   <li><input type="submit" value="查 询" class="search" /></li>
   </ul>
   <u:authority menuId="281">
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
    	<th>服务器类型</th>
    	<th>id</th>
    	<th>ip地址</th>
    	<th>端口号</th>
    	<th>描述信息</th>
    	<th>创建时间</th>
    	<th>操作</th>
    </tr>
    <s:iterator value="page.list">
     <tr>
      <td>${rownum}</td>
      <td><u:ucparams type="cps_server" key="${server}" /></td>
      <td>${id}</td>
      <td>${ipaddress}</td>
      <td>${port}</td>
      <td><u:truncate length="10" value="${describe}" /> </td>
      <td>${createtime}</td>
      <td>
      		<s:if test="menuId_282">
				<a href="javascript:;" onclick="view(${id})">查看</a>
			</s:if>
      		<s:if test="menuId_283">
				| <a href="javascript:;" onclick="edit(${id})">编辑</a>
			</s:if>
      		<s:if test="menuId_284">
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
var server = ${server};
//添加
function add(){
	location.href="${ctx}/server/add";
}

//查看
function view(id){
	location.href="${ctx}/server/view?server="+server+"&id=" + id;
}

//编辑
function edit(id){
	location.href="${ctx}/server/edit?server="+server+"&id=" + id;
}

//删除
function deleteData(id){
	if(confirm("确定要删除吗？")){
		$.ajax({
			type: "post",
			url: "${ctx}/server/delete",
			data:{
				server : server,
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