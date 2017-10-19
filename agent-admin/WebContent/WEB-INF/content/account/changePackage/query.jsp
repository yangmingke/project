<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/common/taglibs.jsp"%>

<u:authority menuId="107">
	<s:set var="menuId_107" value="true"/>
</u:authority>
<u:authority menuId="108">
	<s:set var="menuId_108" value="true"/>
</u:authority>

<html>
<head>
	<title>套餐变更日志列表</title>
</head>

<body>
	<div class="main_search">
	   <form method="post" id="mainForm" action="${ctx}/changePackage/query">
	   <ul>
	   <li>
	   	<input type="text" name="text" value="<s:property value="#parameters.text"/>" maxlength="40" placeholder="开发者ID/用户名" class="txt_177" />
	   </li>
	   <li class="time">
	       	<u:date id="start_date" value="${param.start_date}" placeholder="更新开始时间" maxId="end_date,-1" maxToday="-1" />
			<span>至</span>
	        <u:date id="end_date" value="${param.end_date}" placeholder="更新结束时间" minId="start_date,1" maxToday=""  />
		</li>
	  <li>
		<u:select id="status" value="${param.status}" placeholder="状态" dictionaryType="change_package_status" />
	  </li>
	  <li>
		<u:select id="type" value="${param.type}" placeholder="类型" dictionaryType="change_package_type" />
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
    	<th>序号</th>
    	<th>变更日志id</th>
    	<th>变更前的套餐</th>
    	<th>变更后的套餐</th>
    	<th>状态</th>
    	<th>类型</th>
    	<th>创建时间</th>
    	<th>更新时间</th>
    	<th>用户名</th>
    	<th>钱包余额</th>
    	<th>当前套餐</th>
    	<th>保底消费（元）</th>
    	<th>操作</th>
    </tr>
    <s:iterator value="page.list">
     <tr>
      <td>${rownum}</td>
      <td>${id}</td>
      <td><u:ucparams sqlId="allPackage" key="${old_package_id}" /></td>
      <td><u:ucparams sqlId="allPackage" key="${new_package_id}" /></td>
      <td>${status_name}</td>
      <td>${type_name}</td>
      <td>${create_date}</td>
      <td>${update_date}</td>
      <td>${email}</td>
      <td>${balance}</td>
      <td><u:ucparams sqlId="allPackage" key="${package_id}" /></td>
      <td>${min_consumption}</td>
      <td>
	     	<s:if test="menuId_107">
				<a href="javascript:;" onclick="history('${sid}')">变更历史</a>
			</s:if>
			
			<s:set var="restore">${menuId_108 && status==0 && type==2}</s:set>
	      	<s:if test="#restore">
				| <a href="javascript:;" onclick="restore(this, '${id}')">还原</a>
			</s:if>
		</td>
     </tr>
    </s:iterator>
   </tbody>
   </table>
   
	<u:page page="${page}" formId="mainForm" />
  </div>
      
<script type="text/javascript">
//变更历史
function history(sid){
	location.href="${ctx}/changePackage/history?sid=" + sid;
}

//还原
function restore(a, id){
	if(confirm("确定要"+ $(a).text() +"吗？")){
		$.ajax({
			type: "post",
			url: "${ctx}/changePackage/restore",
			data:{
				id : id
			},
			success: function(data){
				if(data.result==null){
					alert("服务器错误，请联系管理员");
					return;
				}
				
				alert(data.msg);
				if(data.result=="success"){
					location.reload(true);
				}
			}
		});
	}
}
</script>
</body>
</html>