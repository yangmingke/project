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
        <form method="post" id="mainForm" action="${ctx}/developerCharge/query">
          <ul>
            <li>
              <input type="text" name="text" value="<s:property value="#parameters.text"/>" placeholder="模糊查询" class="txt_177" />
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
              <th>订单id</th>
              <th width="50px">订单金额</th>
              <th>订单类型</th>
              <th>创建时间</th>
              <th>用户</th>
              <th>账号</th> 
              <th>mobile</th>
              <th>审核状态</th>
              <th  width="150px">操作</th>
            
            </tr>
            <s:iterator value="page.list">
	            <tr>
	                <td>${order_id} </td>
	            	<td>${charge}</td>
	            	<td><u:ucparams key="${charge_type}" type="charge_type"/></td>
				    <td>${create_date}</td>
				    <td>${username}</td>
	            	<td>${email}</td>
	            	<td>${mobile}</td>
	            	<td><u:ucparams key="${status}" type="payment_order_status"/></td>
	                <td>
				      		<a href="javascript:;" onclick="view('${order_id}')">查看明细</a>
	                </td>
	                </tr>
            </s:iterator>
          </tbody>
        </table>
         <u:page page="${page}" formId="mainForm" />
      </div>   
   
      
   <script type="text/javascript">
   function view(order_id){
	location.href="${ctx}/developerCharge/view?order_id=" + order_id;

}   
   </script> 
   
</body>
</html>