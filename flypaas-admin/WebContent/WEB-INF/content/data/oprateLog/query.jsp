<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/common/taglibs.jsp"%>

<u:authority menuId="87">
	<s:set var="menuId_87" value="true"/>
</u:authority>

<html>
<head>
	<title>操作日志列表</title>
</head>

<body>
	<div class="main_ctn">
		<h1>操作日志列表</h1>
		<div class="main_search">
			<form method="post" id="mainForm" action="${ctx}/oprateLog/query">
				<ul>
					<li>
						<input type="text" name="text" value="<s:property value="#parameters.text"/>" maxlength="40" placeholder="管理员账号/管理员ip/请求url/详情" class="txt_250" />
					</li>
					<li class="time">
						<u:date id="start_date" value="${param.start_date}" placeholder="开始时间" maxId="end_date,-1" maxToday="-1" />
						<span>至</span>
						<u:date id="end_date" value="${param.end_date}" placeholder="结束时间" minId="start_date,1" maxToday=""  />
					</li>
					<li>
						<u:select id="op_type" value="${param.op_type}" placeholder="操作类型" dictionaryType="op_type" />
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
    	<th>操作日志id</th>
    	<th>管理员账号</th>
    	<th>真实姓名</th>
    	<th>管理员ip</th>
    	<th>操作类型</th>
    	<th>请求url</th>
    	<th>详情</th>
    	<th>创建时间</th>
    	<th>操作</th>
    </tr>
    <s:iterator value="page.list">
     <tr>
      <td>${rownum}</td>
      <td>${log_id}</td>
      <td>${email}</td>
      <td>${realname}</td>
      <td>${ip}</td>
      <td><u:ucparams key="${op_type}" type="op_type" /></td>
      <td><u:truncate length="10" value="${page_url}" /></td>
      <td><u:truncate length="10" value="${op_desc}" /></td>
      <td>${create_date}</td>
      <td>
		<%-- <s:if test="menuId_87"> --%>
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
	location.href="${ctx}/oprateLog/view?log_id=" + log_id;
}
</script>
</body>
</html>