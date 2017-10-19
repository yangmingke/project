<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/common/taglibs.jsp"%>

<%-- <u:authority menuId="242"> --%>
	<s:set var="menuId_242" value="true"/>
<%-- </u:authority> --%>

<html>
<head>
	<title>消息通知列表</title>
</head>

<body>
	<div class="main_ctn">
		<h1>消息通知列表</h1>
		<div class="main_search">
			<form method="post" id="mainForm" action="${ctx}/msg/query">
				<ul>
					<li>
						<input type="text" name="text" <%-- value="<s:property value="#request.form.text"/>" --%> maxlength="100" placeholder="用户ID/用户名/标题/内容" class="txt_177" />
					</li>
					<li class="time">
						<u:date id="start_date" value="${param.start_date}" placeholder="开始时间" maxId="end_date" maxToday="" />
						<span>至</span>
						<u:date id="end_date" value="${param.end_date}" placeholder="结束时间" minId="start_date" maxToday=""  />
					</li>
					<li>
						<u:select id="msg_type" value="${param.msg_type}" placeholder="消息类型" dictionaryType="msg_type" />
					</li>
					<li>
						<u:select id="hasread" value="${form.hasread}" placeholder="是否已读" dictionaryType="msg_hasread" />
					</li>
					<li><input type="submit" value="查 询" class="search" /></li>
				   <u:authority menuId="236">
				   		<a href="javascript:;" onclick="add()">添加消息</a>
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
    	<th>用户ID</th>
    	<th>用户名</th>
    	<th>消息类型</th>
    	<th>标题</th>
    	<th>内容</th>
    	<th>创建时间</th>
    	<th>是否已读</th>
    	<th>操作</th>
    </tr>
    <s:iterator value="page.list">
     <tr>
      <td>${rownum}</td>
      <td><u:truncate value="${sid}" length="10" /></td>
      <td><u:truncate value="${email}" length="20" /></td>
      <td><u:ucparams key="${msg_type}" type="msg_type" /></td>
      <td><u:truncate value="${msg_title}" length="10" /></td>
      <td><u:truncate value="${msg_desc}" length="20" /></td>
      <td>${create_date}</td>
      <td><u:ucparams key="${hasread}" type="msg_hasread" /></td>
      <td>
		<s:if test="menuId_242">
  			<a href="javascript:;" onclick="view('${msg_id}')">查看</a>
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
function view(msg_id){
	location.href="${ctx}/msg/view?msg_id=" + msg_id;
}

//添加
function add(){
	location.href="${ctx}/msg/add";
}
</script>
</body>
</html>