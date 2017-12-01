<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/common/taglibs.jsp"%>
<html>
<head>
	<title>client管理</title>
</head>
<body>
           <div class="main_search">
        <form method="post" id="mainForm" action="${ctx}/client/query">
          <ul>
            <li>
              <input type="text" name="text" value="<s:property value="#parameters.text"/>" placeholder="关键字查询" class="txt_177" />
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
              <input type="submit" value="查 询" class="search" />
            </li>
          </ul>
        </form>
      </div>
      <div class="clear">
      </div>
   <div class="table_ctn">
        <table cellpadding="0" cellspacing="0" border="0">
          <tbody>
            <tr>
              <th>client 账户</th>
              <th>开发者sid</th>
              <th>归属开发者</th>
              <th>应用id</th>
              <th>归属应用</th>
              <th>归属行业</th>
              <!-- <th>状态</th>  -->
              <th width="150px">注册时间</th>
              <th width="150px">操作</th>
            </tr>
            <s:iterator value="page.list">
	            <tr>
	            	<td>${client_number}</td>
	                <td>${sid} </td>
				    <td>${username}</td>
				    <td>${app_sid}</td>
	            	<td>${app_name}</td>
	            	<td><u:ucparams key="${industry}" type="industry"/></td>
	            	<%-- <td><u:ucparams key="${status}" type="client_status"/></td> --%>
	                <td>${create_date}</td>
	                <td>
			      		<a href="javascript:;" onclick="view('${client_number}')">查看</a>
	                </td>
	                </tr>
            </s:iterator>
          </tbody>
        </table>
         <u:page page="${page}" formId="mainForm" />
      </div>   
      
   <script type="text/javascript">
	   function view(client_number){
		   location.href="${ctx}/client/view?client_number=" + client_number;
		}   
   </script> 
</body>
</html>