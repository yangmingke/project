<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/common/taglibs.jsp"%>

<u:authority menuId="320">
	<s:set var="menuId_320" value="true"/>
</u:authority>
<u:authority menuId="321">
	<s:set var="menuId_321" value="true"/>
</u:authority>

<html>
<head>
	<title>敏感字列表</title>
</head>

<body>
   <div class="main_ctn">
     <h1>敏感字列表</h1>
     <div class="main_search">
   <form method="post" id="mainForm" action="${ctx}/keyword/query">
   <ul>
   <li>
   	<input type="text" name="text" value="<s:property value="#parameters.text"/>" maxlength="40" placeholder="敏感字id/敏感字" class="txt_177" />
   </li>
   <li class="time">
       	<u:date id="start_date" value="${param.start_date}" placeholder="更新开始时间" maxId="end_date" maxToday="" />
		<span>至</span>
       	<u:date id="end_date" value="${param.end_date}" placeholder="更新结束时间" minId="start_date" maxToday=""  />
	</li>
   <li><input type="submit" value="查 询" class="search" /></li>
   </ul>
   <u:authority menuId="319">
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
    	<th>敏感字id</th>
    	<th>敏感字</th>
    	<th>更新时间</th>
    	<th>操作</th>
    </tr>
    <s:iterator value="page.list">
     <tr>
      <td>${rownum}</td>
      <td>${word_id}</td>
      <td><u:truncate length="20" value="${word}" /></td>
      <td>${update_date}</td>
      <td>
	     	<s:if test="menuId_320">
				<a href="javascript:;" onclick="edit('${word_id}')">编辑</a>
			</s:if>
	     		<s:if test="menuId_321">
				| <a href="javascript:;" onclick="deleteData(this, '${word_id}')">删除</a>
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
	location.href="${ctx}/keyword/add";
}

//编辑
function edit(word_id){
	location.href="${ctx}/keyword/edit?word_id=" + word_id;
}

//删除
function deleteData(a, word_id){
	if(confirm("确定要删除吗？")){
		$.ajax({
			type: "post",
			url: "${ctx}/keyword/delete",
			data:{
				word_id : word_id
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