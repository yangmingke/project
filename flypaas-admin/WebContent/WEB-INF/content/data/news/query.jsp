<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/common/taglibs.jsp"%>

<u:authority menuId="92">
	<s:set var="menuId_92" value="true"/>
</u:authority>
<u:authority menuId="93">
	<s:set var="menuId_93" value="true"/>
</u:authority>
<u:authority menuId="94">
	<s:set var="menuId_94" value="true"/>
</u:authority>
<u:authority menuId="95">
	<s:set var="menuId_95" value="true"/>
</u:authority>
<u:authority menuId="96">
	<s:set var="menuId_96" value="true"/>
</u:authority>
<u:authority menuId="97">
	<s:set var="menuId_97" value="true"/>
</u:authority>

<html>
<head>
	<title>新闻列表</title>
</head>

<body>
   <div class="main_ctn">
     <h1>新闻列表</h1>
     <div class="main_search">
   <form method="post" id="mainForm" action="${ctx}/news/query">
   <ul>
   <li>
   	<input type="text" name="text" value="<s:property value="#parameters.text"/>" maxlength="40" placeholder="新闻id/标题" class="txt_177" />
   </li>
   <li class="time">
       	<u:date id="start_date" value="${param.start_date}" placeholder="更新开始时间" maxId="end_date,-1" maxToday="-1" />
		<span>至</span>
       	<u:date id="end_date" value="${param.end_date}" placeholder="更新结束时间" minId="start_date,1" maxToday=""  />
	</li>
  <li>
	<u:select id="status" value="${param.status}" placeholder="状态" dictionaryType="news_status" />
  </li>
   <li><input type="submit" value="查 询" class="search" /></li>
   </ul>
   <u:authority menuId="91">
   		<a href="javascript:;" onclick="add()">添加新闻</a>
   </u:authority>
   </form>
   </div>
   <div class="clear"></div>
   <div class="table_ctn">
   <table cellpadding="0" cellspacing="0" border="0">
   <tbody>
    <tr>
    	<th>序号</th>
    	<th>新闻id</th>
    	<th>标题</th>
    	<th>副标题</th>
    	<th>摘要</th>
    	<th>更新时间</th>
    	<th>状态</th>
    	<th>操作</th>
    </tr>
    <s:iterator value="page.list">
     <tr>
      <td>${rownum}</td>
      <td>${news_id}</td>
      <td><u:truncate length="20" value="${title}" /></td>
      <td><u:truncate length="10" value="${subtitle}" /></td>
      <td><u:truncate length="10" value="${summary}" /></td>
      <td>${update_date}</td>
      <td name="status_name">${status_name}</td>
      <td>
	   		<%--0关闭 --%>
	   		<span name="operate_0" ${status==0 ? "" : "style='display:none;'"}>
	      		<s:if test="menuId_92">
					<a href="javascript:;" onclick="view('${news_id}')">查看</a>
				</s:if>
	      		<s:if test="menuId_93">
					| <a href="javascript:;" onclick="updateStatus(this, '${news_id}', 1)">启用</a>
				</s:if>
	      		<s:if test="menuId_95">
					| <a href="javascript:;" onclick="edit('${news_id}')">编辑</a>
				</s:if>
	      		<s:if test="menuId_96">
					| <a href="javascript:;" onclick="updateStatus(this, '${news_id}', 2)">删除</a>
				</s:if>
	   		</span>
	   		
	   		<%--1启用 --%>
	   		<span name="operate_1" ${status==1 ? "" : "style='display:none;'"}>
	      		<s:if test="menuId_92">
					<a href="javascript:;" onclick="view('${news_id}')">查看</a>
				</s:if>
	      		<s:if test="menuId_94">
					| <a href="javascript:;" onclick="updateStatus(this, '${news_id}', 0)">关闭</a>
				</s:if>
	   		</span>
	   		
	   		<%--2已删除 --%>
	   		<span name="operate_2" ${status==2 ? "" : "style='display:none;'"}>
	      		<s:if test="menuId_92">
					<a href="javascript:;" onclick="view('${news_id}')">查看</a>
				</s:if>
	      		<s:if test="menuId_97">
					| <a href="javascript:;" onclick="updateStatus(this, '${news_id}', 0)">恢复</a>
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
function view(news_id){
	location.href="${ctx}/news/view?news_id=" + news_id;
}

//添加
function add(){
	location.href="${ctx}/news/add";
}

//编辑
function edit(news_id){
	location.href="${ctx}/news/edit?news_id=" + news_id;
}

//修改状态：关闭、启用、删除
function updateStatus(a, news_id, status){
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
			url: "${ctx}/news/updateStatus",
			data:{
				news_id : news_id,
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