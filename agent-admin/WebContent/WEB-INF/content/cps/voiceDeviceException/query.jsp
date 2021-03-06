<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/common/taglibs.jsp"%>

<u:authority menuId="278">
	<s:set var="menuId_278" value="true"/>
</u:authority>
<u:authority menuId="279">
	<s:set var="menuId_279" value="true"/>
</u:authority>

<html>
<head>
	<title>音频驱动适配异常列表</title>
</head>

<body>
   <div class="main_ctn">
     <h1>音频驱动适配异常列表</h1>
     <div class="main_search">
   <form method="post" id="mainForm" action="${ctx}/voiceDeviceException/query">
   <ul>
   <li>
   	<input type="text" name="phonebrand" value="<s:property value="#parameters.phonebrand"/>" placeholder="手机品牌" maxlength="40" class="txt_177" />
   </li>
   <li><input type="submit" value="查 询" class="search" /></li>
   </ul>
   </form>
   </div>
   <div class="clear"></div>
   <div class="table_ctn">
   <table cellpadding="0" cellspacing="0" border="0">
   <tbody>
    <tr>
    	<th>序号</th>
    	<th>id</th>
    	<th>平台信息</th>
    	<th>版本信息</th>
    	<th>客户端SDK版本</th>
    	<th>系统版本</th>
    	<th>系统API LEVEL</th>
    	<th>手机品牌</th>
    	<th>型号</th>
    	<th>imei</th>
    	<th>mac</th>
    	<th>更新时间</th>
    	<th>操作</th>
    </tr>
    <s:iterator value="page.list">
     <tr>
      <td>${rownum}</td>
      <td>${id}</td>
      <td><u:truncate value="${pv}" length="10" /></td>
      <td><u:truncate value="${app_ver}" length="10" /></td>
      <td><u:truncate value="${ver}" length="10" /></td>
      <td><u:truncate value="${system_ver}" length="10" /></td>
      <td><u:truncate value="${api_level}" length="10" /></td>
      <td><u:truncate value="${phonebrand}" length="10" /></td>
      <td><u:truncate value="${phonemodel}" length="10" /></td>
      <td><u:truncate value="${imei}" length="10" /></td>
      <td><u:truncate value="${mac}" length="10" /></td>
      <td>${updatetime}</td>
      <td>
      		<s:if test="menuId_278">
				<a href="javascript:;" onclick="view(${id})">查看</a>
			</s:if>
      		<s:if test="menuId_279">
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
//查看
function view(id){
	location.href="${ctx}/voiceDeviceException/view?id=" + id;
}

//删除
function deleteData(id){
	if(confirm("确定要删除吗？")){
		$.ajax({
			type: "post",
			url: "${ctx}/voiceDeviceException/delete",
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