<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/common/taglibs.jsp"%>

<u:authority menuId="93">
	<s:set var="menuId_93" value="true"/>
</u:authority>

<html>
<head>
	<title>任务日志列表</title>
</head>

<body>
	<div class="main_ctn">
		<h1>任务日志列表</h1>
		<div class="main_search">
			<form method="post" id="mainForm" action="${ctx}/taskLog/query">
				<ul>
					<li>
						<input type="text" name="text" value="<s:property value="#parameters.text"/>" maxlength="40" placeholder="任务日志id/数据时间" class="txt_250" />
					</li>
					<li class="time">
						<u:date id="start_date" value="${param.start_date}" placeholder="开始时间" maxId="end_date,-1" maxToday="-1" />
						<span>至</span>
						<u:date id="end_date" value="${param.end_date}" placeholder="结束时间" minId="start_date,1" maxToday=""  />
					</li>
					<li>
						<u:select id="task_id" value="${param.task_id}" clazz="sel_280" placeholder="任务" sqlId="task"  />
					</li>
					<li>
						<u:select id="task_type" value="${param.task_type}" placeholder="任务类型" dictionaryType="task_type" />
					</li>
					<li>
						<u:select id="duration" value="${param.duration}" placeholder="持续时间" dictionaryType="duration" />
					</li>
					<li>
						<u:select id="status" value="${param.status}" placeholder="状态" dictionaryType="task_log_status" />
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
    	<th width="50px">序号</th>
    	<th>任务日志id</th>
		<th>任务id</th>
		<th>任务名称</th>
		<th>任务类型</th>
		<th>数据时间</th>
		<th>开始时间</th>
		<th>持续时间(时:分:秒)</th>
    	<th>状态</th>
    	<th>操作</th>
    </tr>
    <s:iterator value="page.list">
     <tr>
      <td>${rownum}</td>
      <td>${log_id}</td>
      <td>${task_id}</td>
      <td><u:truncate value="${task_name}" length="20" /></td>
      <td><u:ucparams key="${task_type}" type="task_type" /></td>
      <td>${data_date}</td>
      <td>${start_date}</td>
      <td>${duration}</td>
      <td><u:ucparams key="${status}" type="task_log_status" /></td>
      <td>
		<%-- <s:if test="menuId_93"> --%>
  			<a href="javascript:;" onclick="view('${log_id}')">查看</a>
		<%-- </s:if> --%>
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
function view(log_id){
	location.href="${ctx}/taskLog/view?log_id=" + log_id;
}
</script>
</body>
</html>