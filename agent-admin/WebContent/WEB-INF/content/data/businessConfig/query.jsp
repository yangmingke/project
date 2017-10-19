<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/common/taglibs.jsp"%>
<html>
<head>
	<title>开发者业务配置</title>
</head>
<u:authority menuId="250">
	<s:set var="menuId_250" value="true"/>
</u:authority>
<body>
   <div class="main_ctn">
     <h1>开发者业务配置</h1>
     <div class="main_search">
   <form method="post" id="mainForm" action="${ctx}/businessConfig/query">
   <ul>
   <li>
   	<input type="text" name="text" value="<s:property value="#parameters.text"/>" maxlength="40" placeholder="配置id/开发者Sid/账号" class="txt_177" />
   </li>
   <li class="time">
       	<u:date id="start_date" value="${param.start_date}" placeholder="更新开始时间" maxId="end_date,-1" maxToday="-1" />
		<span>至</span>
       	<u:date id="end_date" value="${param.end_date}" placeholder="更新结束时间" minId="start_date,1" maxToday=""  />
	</li>
  <li>
	<u:select id="stype" value="${param.stype}" placeholder="类型" dictionaryType="tb_user_dsp_stype" />
  </li>
   <li><input type="submit" value="查 询" class="search" /></li>
   </ul>
   <u:authority menuId="249">
   		<a href="javascript:;" onclick="add()">添加</a>
   </u:authority>
   </form>
   </div>
   <div class="clear"></div>
   <div class="table_ctn">
   <table cellpadding="0" cellspacing="0" border="0">
   <tbody>
    <tr>
    	<th>序号</th>
    	<th>id</th>
    	<th>开发者sid</th>
    	<th>开发者账号</th>
    	<th>类型</th>
    	<th>更新时间</th>
    	<th>金额</th>
    	<th>其它</th>
    	<th>状态</th>
    	<th>操作</th>
    </tr>
    <s:iterator value="page.list">
     <tr>
      <td>${rownum}</td>
      <td>${id}</td>
      <td>${sid}</td>
      <td>${email}</td>
      <td><u:ucparams key="${stype}" type="tb_user_dsp_stype"/></td>
      <td>${update_date}</td>
      <td>${money_fmt}</td>
      <td>${ext}</td>
      <td name="status_name"><u:ucparams key="${status}" type="tb_user_dsp_status"/></td>
      <td>
     	 	<s:if test="menuId_250">
      			<s:if test="1==status"><a href="javascript:;" onclick="updateStatus(this, '${id}', 0)">删除</a></s:if>
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
//添加
function add(){
	location.href="${ctx}/businessConfig/edit";
}

//修改状态：关闭、启用、删除
function updateStatus(a, id, status){
	var status_name="";
	switch(status){
	case 0:	status_name = "失效";
			break;
	case 1:	status_name = "生效";
			break;
	}
	if(confirm("确定要"+ $(a).text() +"吗？")){
		$.ajax({
			type: "post",
			url: "${ctx}/businessConfig/updateStatus",
			data:{
				id : id,
				status : status
			},
			success: function(data){
				if(data.result==null){
					alert("服务器错误，请联系管理员");
					return;
				}
				if(data.result=="success"){
					alert($(a).text() + "成功");
					$("#mainForm").submit();
				}else{
					alert(data.msg);
				}
			}
		});
	}
}
</script>
</body>
</html>