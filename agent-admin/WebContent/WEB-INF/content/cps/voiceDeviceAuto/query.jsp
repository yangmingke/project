<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/common/taglibs.jsp"%>

<u:authority menuId="274">
	<s:set var="menuId_274" value="true"/>
</u:authority>
<u:authority menuId="275">
	<s:set var="menuId_275" value="true"/>
</u:authority>
<u:authority menuId="276">
	<s:set var="menuId_276" value="true"/>
</u:authority>

<html>
<head>
	<title>音频驱动智能适配列表</title>
</head>

<body>
   <div class="main_ctn">
     <h1>音频驱动智能适配列表</h1>
     <div class="main_search">
   <form method="post" id="mainForm" action="${ctx}/voiceDeviceAuto/query">
   <ul>
   <li>
   	<input type="text" name="phonebrand" value="<s:property value="#parameters.phonebrand"/>" placeholder="手机品牌" maxlength="40" class="txt_177" />
   </li>
   <li><input type="submit" value="查 询" class="search" /></li>
   </ul>
   <u:authority menuId="273">
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
    	<th>手机品牌</th>
    	<th>优先级</th>
    	<th>媒体采集驱动音频数据流模式</th>
    	<th>更新时间</th>
    	<th>操作</th>
    </tr>
    <s:iterator value="page.list">
     <tr>
      <td>${rownum}</td>
      <td>${id}</td>
      <td>${phonebrand}</td>
      <td>${priority}</td>
      <td><u:ucparams type="cps_recordsource" key="${recordsource}" /></td>
      <td>${updatetime}</td>
      <td>
      		<s:if test="menuId_274">
				<a href="javascript:;" onclick="view(${id})">查看</a>
			</s:if>
      		<s:if test="menuId_275">
				| <a href="javascript:;" onclick="edit(${id})">编辑</a>
			</s:if>
      		<s:if test="menuId_276">
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
	location.href="${ctx}/voiceDeviceAuto/add";
}

//查看
function view(id){
	location.href="${ctx}/voiceDeviceAuto/view?id=" + id;
}

//编辑
function edit(id){
	location.href="${ctx}/voiceDeviceAuto/edit?id=" + id;
}

//删除
function deleteData(id){
	if(confirm("确定要删除吗？")){
		$.ajax({
			type: "post",
			url: "${ctx}/voiceDeviceAuto/delete",
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