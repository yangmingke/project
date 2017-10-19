<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/common/taglibs.jsp"%>

<html>
<head>
	<title>邮件管理</title>
</head>

<body>
	<div class="main_ctn">
		<h1>邮件管理</h1>
		<div class="main_search">
			<form method="post" id="mainForm" action="${ctx}/email/query">
				<ul>
					<li>
						<input type="text" name="text" value="<s:property value="#parameters.text"/>" maxlength="40" placeholder="开发者ID/用户名/昵称" class="txt_250" />
					</li>
					<li class="time">
						<u:date id="start_date" value="${param.start_date}" placeholder="开始时间" maxId="end_date" maxToday="" />
						<span>至</span>
						<u:date id="end_date" value="${param.end_date}" placeholder="结束时间" minId="start_date" maxToday=""  />
					</li>
		            <li>
						<u:select id="email_status" value="${param.email_status}" placeholder="邮件状态" dictionaryType="email_status" />
					</li>
		            <li>
						<input type="submit" value="查 询" class="search"/>
					</li>
				   <u:authority menuId="286">
				   		<a href="javascript:;" onclick="add()">发送邮件</a>
				   </u:authority>
				</ul>
   			</form>
		</div>
   <div class="clear"></div>
   <div class="table_ctn">
   <table cellpadding="0" cellspacing="0" border="0">
      <tbody>
	      <tr>
		      <th width="50px">序号</th>
		      <th>开发者ID</th>
		      <th>用户名</th>
		      <th>昵称</th>
		      <th>邮件标题</th>
		      <th>邮件内容</th>
		      <th>备注</th>
		      <th>邮件状态</th>
		      <th>发送时间</th>
	      </tr>
	      <s:iterator value="page.list">
		      <tr>
			      <td>${rownum}</td>
			      <td>${sid}</td>
			      <td>${email}</td>
			      <td>${username}</td>
			      <td><u:truncate length="5" value="${title}" /></td>
			      <td><u:truncate length="5" value="${desc}" /></td>
			      <td><u:truncate length="5" value="${remark}" /></td>
			      <td><u:ucparams key="${email_status}" type="email_status"/></td>
			      <td>${create_date}</td>
		      </tr>
	      </s:iterator>
      </tbody>
     </table>
   
   <u:page page="${page}" formId="mainForm" />
   </div>
   </div>
      
<script type="text/javascript">
//发送
function add(){
	location.href="${ctx}/email/add";
}
</script>
</body>
</html>