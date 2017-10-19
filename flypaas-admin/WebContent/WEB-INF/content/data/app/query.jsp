<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/common/taglibs.jsp"%>

<u:authority menuId="45">
	<s:set var="menuId_45" value="true"/>
</u:authority>
<u:authority menuId="46">
	<s:set var="menuId_46" value="true"/>
</u:authority>
<u:authority menuId="47">
	<s:set var="menuId_47" value="true"/>
</u:authority>

<html>
<head>
	<title>应用管理</title>
</head>

<body>
      <div class="main_search">
        <form method="post" id="mainForm" action="${ctx}/app/query">
          <ul>
            <li>
              <input type="text" name="text" value="<s:property value="#parameters.text"/>" placeholder="应用ID/应用名称/开发者" class="txt_177" />
            </li>
            <li class="time">
              	<u:date id="start_date" value="${param.start_date}" placeholder="开始时间" maxId="end_date,-1" maxToday="-1" />
				<span>至</span>
            	<u:date id="end_date" value="${param.end_date}" placeholder="结束时间" minId="start_date,1" maxToday=""  />
            </li>
            <li>
				<u:select id="industry" value="${param.industry}" placeholder="归属行业" dictionaryType="industry" excludeValue="0" />
            </li>
            <li>
				<u:select id="status" value="${param.status}" placeholder="审核状态" dictionaryType="app_status" />
            </li>
            <li>
              <input type="submit" value="查 询" class="search" />
            </li>
          </ul>
        </form>
      </div>
      <div class="clear"></div>
      <div class="table_ctn">
        <table cellpadding="0" cellspacing="0" border="0">
          <tbody>
            <tr>
              <th width="50px">序号</th>
              <th>应用ID</th>
              <th>应用名称</th>
              <th>应用类型</th>
              <th>归属行业</th>
              <th>审核状态</th>
              <th>开发者</th>
              <!-- <th>品牌</th> -->
              <th>代理商</th>
              <th>路由域</th>
              <th>创建时间</th>
              <th>操作</th>
            </tr>
            <s:iterator value="page.list">
			      <tr>
				      <td>${rownum}</td>
				      <td>${app_sid}</td>
				      <td>${app_name}</td>
				      <td><u:ucparams key="${app_kind}" type="app_kind"/></td>
				      <td><u:ucparams key="${industry}" type="industry"/></td>
				      <td><u:ucparams key="${status}" type="app_status"/></td>
				      <td>${email}</td>
				      <%-- <td>${brand}</td> --%>
				      <td>${agentName}</td>
				      <td>${route_area}</td>
				      <td>${create_date}</td>
				      <td>
				      	<%-- <s:if test="menuId_45"> --%>
					      	<a href="javascript:;" onclick="view('${app_sid}')">查看</a> 
					    <%-- </s:if> --%>
					    <s:if test="status==1">
				      		<%-- <s:if test="menuId_47"> --%>
								<a href="javascript:;" onclick="updateStatus(this, '${app_sid}', 5)">强制下线</a>
						   <%--  </s:if> --%>
					    </s:if>
					</td>
			      </tr>
		      </s:iterator>
          </tbody>
        </table>
        
       <u:page page="${page}" formId="mainForm" />
      </div>

<script type="text/javascript">
//查看
function view(app_sid){
	location.href="${ctx}/app/view?view=1&app_sid=" + app_sid;
}

//审核
function audit(app_sid){
	// location.href="${ctx}/app/view?app_sid=" + app_sid;
}

//强制下线
function updateStatus(a, app_sid, status){
	if(confirm("确定要强制下线吗？")){
		$.ajax({
			type: "post",
			url: "${ctx}/app/updateStatus",
			data:{
				app_sid : app_sid,
				status : status
			},
			success: function(data){
				if(data.result==null){
					alert("服务器错误，请联系管理员");
					return;
				}
				alert(data.msg);
				if(data.result=="success"){
					$("#mainForm").submit();
				}
			}
		});
	}
}
</script>
</body>
</html>