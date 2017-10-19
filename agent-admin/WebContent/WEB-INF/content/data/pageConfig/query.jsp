<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/common/taglibs.jsp"%>
<html>
<head>
	<title>前台页面配置</title>
</head>
<body>
   <div class="main_ctn">
     <h1>前台页面配置</h1>
     <div class="main_search">
   <form method="get" id="mainForm" action="${ctx}/pageConfig/query">
   <u:authority menuId="300">
   		<a href="javascript:;" onclick="edit('')">添加</a>
   </u:authority>
   </ul>
   </form>
   </div>
      <div class="table_ctn">
        <table cellpadding="0" cellspacing="0" border="0">
          <tbody>
            <tr>
              <th width="50px">序号</th>
              <th>名称</th>
              <th>Key</th>
              <th>内容</th>
              <th>更新时间</th>
              <th>操作</th>
            </tr>
            <s:iterator value="page.list">
			      <tr>
				      <td>${rownum}</td>
				      <td>${page_name}</td>
				      <td>${page_key}</td>
				      <td><u:truncate length="20" value="${content}"></u:truncate> </td>
				      <td>${update_date}</td>
				      <td>
				      		<u:authority menuId="300">
						      	<a href="javascript:;" onclick="edit('${id}')">编辑</a> 
				      		</u:authority>
				      		<u:authority menuId="301">
								| <a href="javascript:;" onclick="updateStatus(this, '${id}',0,${config_status})">删除</a>
				      		</u:authority>
					</td>
			      </tr>
		      </s:iterator>
          </tbody>
        </table>
        
       <u:page page="${page}" formId="mainForm" />
      </div>

<script type="text/javascript">
//查看
function edit(id){
	location.href="${ctx}/pageConfig/edit?id=" + id;
}

//删除
function updateStatus(a, id,config_status,old_status){
	var text = $(a).text();
	if(confirm("确定要 "+text+" 吗？")){
		$.ajax({
			type: "post",
			url: "${ctx}/pageConfig/updateStatus",
			data:{
				id : id,
				old_status : old_status,
				config_status : config_status
			},
			success: function(data){
				if(data.result==null){
					alert("服务器错误，请联系管理员");
					return;
				}
				
				if(data.result=="success"){
					setTimeout(function(){
						alert(data.msg);
						$("#mainForm").submit();
					}, 1000);
				}else{
					alert(data.msg);
				}
			}
		});
	};
};
</script>
</body>
</html>