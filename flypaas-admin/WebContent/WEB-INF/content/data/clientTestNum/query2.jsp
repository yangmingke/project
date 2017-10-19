<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/common/taglibs.jsp"%>


<u:authority menuId="250">
	<s:set var="menuId_250" value="true"/>
</u:authority>

<html>
<head>
	<title>client账务</title>
</head>

<body>
           <div class="main_search">
        <form method="post" id="mainForm" action="${ctx}/clientBill/query">
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
				<u:select id="status" value="${param.status}" placeholder="审核状态" dictionaryType="app_status" />
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
              <th>账户序号</th>
              <th width="50px">client账户</th>
              <th>应用名称</th>
              <th>用户账号</th>
              <th>归属行业</th>
              <th>联系手机</th>
              <th>用户余额</th> 
              <th>开发套餐</th>
              <th>审核状态</th>
              <th  width="150px">创建时间</th>
              <th  width="150px">操作</th>
            
            </tr>
            <s:iterator value="page.list">
	            <tr>
	                <td>${acct_id} </td>
	            	<td>${client_number}</td>
	            	<td>${app_name}</td>
				    <td>${email}</td>
				    <td><u:ucparams key="${industry}" type="industry"/></td>
				    <td>${mobile}</td>
	            	<td>${balance}</td>
	            	<td>${package_name}</td>
	            	<td>${enable_flag}</td>
	            	<td>${create_time}</td>
	                <td>
				      		<a href="javascript:;" onclick="view('${client_number}')">查看明细</a>
	                </td>
	                </tr>
            </s:iterator>
          </tbody>
        </table>
         <u:page page="${page}" formId="mainForm" />
      </div>   
   
      
   <script type="text/javascript">
   function view(client_number){
	location.href="${ctx}/clientBill/view?client_number=" + client_number;

}   
   </script> 
   
</body>
</html>