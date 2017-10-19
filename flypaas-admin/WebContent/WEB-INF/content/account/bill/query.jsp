<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/common/taglibs.jsp"%>

<u:authority menuId="69">
	<s:set var="menuId_69" value="true"/>
</u:authority>
<u:authority menuId="70">
	<s:set var="menuId_70" value="true"/>
</u:authority>
<u:authority menuId="71">
	<s:set var="menuId_71" value="true"/>
</u:authority>

<html>
<head>
	<title>账单信息</title>
</head>
<body>
	<div class="main_search">
        <form method="post" id="mainForm" action="${ctx}/bill/query">
          <ul>
            <li>
              <input type="text" name="text" value="<s:property value="#parameters.text"/>" placeholder="业务单号/开发者" class="txt_227" />
            </li>
            <li>
              <input type="submit" value="查 询" class="search" />
            </li>
          </ul>
        </form>
      </div>
      <div class="clear"></div>
      <div class="table_ctn">
        <table class="noEdge">
          <tbody>
            <tr>
              <th width="50px">序号</th>
              <th>业务单号</th>
              <th>业务类型</th>
              <th>业务状态</th>
              <th>业务金额</th>
              <th>开发者</th>
              <th>生成时间</th>
              <th>操作</th>
            </tr>
            <s:iterator value="page.list">
	            <tr>
	            	<td>${rownum}</td>
	            	<td>${order_id}</td>
	            	<td><u:ucparams key="${charge_type}" type="charge_type"/></td>
	            	<td><u:ucparams key="${status}" type="payment_order_status"/></td>
	            	<td>${charge}</td>
	            	<td>${email}</td>
	            	<td>${create_date}</td>
	            	<td>
				      <%-- 	<s:if test="menuId_69"> --%>
	            			<a href="javascript:;" onclick="view('${order_id}')">查看</a>
						<%-- </s:if> --%>
	            		<s:if test="status == 1">
					      	<%-- <s:if test="menuId_70"> --%>
								| <a href="javascript:;" onclick="closeOrder('${order_id}','${sid}')">关闭</a>
							<%-- </s:if>
					      	<s:if test="menuId_71"> --%>
								| <a href="javascript:;" onclick="notice('${sid}')">通知</a>
							<%-- </s:if> --%>
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
function view(order_id){
	location.href="${ctx}/bill/view?order_id=" + order_id;
}
//通知
function notice(sid){
	location.href="${ctx}/bill/notice?sid=" + sid;
}
//关闭
function closeOrder(order_id,sid){
	if(window.confirm("你确定需要关闭这个订单吗")){
		$.post("${ctx}/bill/closeOrder",{order_id:order_id},function(data){
			if(data.result==null){
				alert("服务器错误，请联系管理员");
				return;
			}
			if(data.result=="success"){
				alert(data.msg);
				location.reload();
			}else{
				alert(data.msg);
			}
		});
	}
};

</script>
</body>
</html>