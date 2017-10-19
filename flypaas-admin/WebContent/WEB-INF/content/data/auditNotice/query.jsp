<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/common/taglibs.jsp"%>

<u:authority menuId="100">
	<s:set var="menuId_100" value="true"/>
</u:authority>
<u:authority menuId="101">
	<s:set var="menuId_101" value="true"/>
</u:authority>
<u:authority menuId="102">
	<s:set var="menuId_102" value="true"/>
</u:authority>
<u:authority menuId="103">
	<s:set var="menuId_103" value="true"/>
</u:authority>
<u:authority menuId="104">
	<s:set var="menuId_104" value="true"/>
</u:authority>
<u:authority menuId="105">
	<s:set var="menuId_105" value="true"/>
</u:authority>

<html>
<head>
	<title>审核通知时段列表</title>
</head>

<body>
   <div class="main_ctn">
     <h1>审核通知时段列表</h1>
     <div class="main_search">
   <form method="post" id="mainForm" action="${ctx}/auditNotice/query">
   <ul>
   <li>
   	<input type="text" name="text" value="<s:property value="#parameters.text"/>" maxlength="40" placeholder="通知时段id/通知时段名称" class="txt_177" />
   </li>
   <li class="time">
       	<u:date id="start_date" value="${param.start_date}" placeholder="时段开始时间" maxId="end_date,-1" dateFmt="HH:mm:ss" startDate="00:00:00" />
		<span>至</span>
       	<u:date id="end_date" value="${param.end_date}" placeholder="时段结束时间" minId="start_date,1" dateFmt="HH:mm:ss" />
	</li>
  <li>
	<u:select id="status" value="${param.status}" placeholder="状态" dictionaryType="audit_notice_status" />
  </li>
   <li><input type="submit" value="查 询" class="search" /></li>
   </ul>
   <u:authority menuId="99">
   		<a href="javascript:;" onclick="add()">添加审核通知时段</a>
   </u:authority>
   </form>
   </div>
   <div class="clear"></div>
   <div class="table_ctn">
   <table cellpadding="0" cellspacing="0" border="0">
   <tbody>
    <tr>
    	<th width="50px">序号</th>
    	<th>通知时段id</th>
    	<th>通知时段名称</th>
    	<th>时段</th>
    	<th>接收管理员</th>
    	<th>更新时间</th>
    	<th>状态</th>
    	<th>操作</th>
    </tr>
    <s:iterator value="page.list">
     <tr>
      <td>${rownum}</td>
      <td>${notice_id}</td>
      <td><u:truncate length="20" value="${name}" /></td>
      <td>${start_date}至${end_date}</td>
      <td><u:truncate length="20" value="${mobile}" /></td>
      <td>${update_date}</td>
      <td name="status_name">${status_name}</td>
      <td>
	   		<%--0关闭 --%>
	   		<span name="operate_0" ${status==0 ? "" : "style='display:none;'"}>
	      		<s:if test="menuId_100">
					<a href="javascript:;" onclick="view('${notice_id}')">查看</a>
				</s:if>
	      		<s:if test="menuId_101">
					| <a href="javascript:;" onclick="updateStatus(this, '${notice_id}', 1)">启用</a>
				</s:if>
	      		<s:if test="menuId_103">
					| <a href="javascript:;" onclick="edit('${notice_id}')">编辑</a>
				</s:if>
	      		<s:if test="menuId_104">
					| <a href="javascript:;" onclick="updateStatus(this, '${notice_id}', 2)">删除</a>
				</s:if>
	   		</span>
	   		
	   		<%--1启用 --%>
	   		<span name="operate_1" ${status==1 ? "" : "style='display:none;'"}>
	      		<s:if test="menuId_100">
					<a href="javascript:;" onclick="view('${notice_id}')">查看</a>
				</s:if>
	      		<s:if test="menuId_102">
					| <a href="javascript:;" onclick="updateStatus(this, '${notice_id}', 0)">关闭</a>
				</s:if>
	   		</span>
	   		
	   		<%--2已删除 --%>
	   		<span name="operate_2" ${status==2 ? "" : "style='display:none;'"}>
	      		<s:if test="menuId_100">
					<a href="javascript:;" onclick="view('${notice_id}')">查看</a>
				</s:if>
	      		<s:if test="menuId_105">
					| <a href="javascript:;" onclick="updateStatus(this, '${notice_id}', 0)">恢复</a>
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
//查看
function view(notice_id){
	location.href="${ctx}/auditNotice/view?notice_id=" + notice_id;
}

//添加
function add(){
	location.href="${ctx}/auditNotice/add";
}

//编辑
function edit(notice_id){
	location.href="${ctx}/auditNotice/edit?notice_id=" + notice_id;
}

//修改状态：关闭、启用、删除
function updateStatus(a, notice_id, status){
	var status_name="";
	switch(status){
	case 0:	status_name = "关闭";
			break;
	case 1:	status_name = "启用";
			break;
	case 2:	status_name = "已删除";
			break;
	}
	
	if(confirm("确定要"+ $(a).text() +"吗？")){
		$.ajax({
			type: "post",
			url: "${ctx}/auditNotice/updateStatus",
			data:{
				notice_id : notice_id,
				status : status
			},
			success: function(data){
				if(data.result==null){
					alert("服务器错误，请联系管理员");
					return;
				}
				
				if(data.result=="success"){
					$(a).parent().hide();
					$(a).parents("tr").find("[name='operate_"+status+"']").show();
					$(a).parents("tr").find("[name='status_name']").text(status_name);
					alert($(a).text() + "成功");
				}else{
					alert(data.msg);
				}
			}
		});
	}
}
</script>
</body>
</html>