<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/common/taglibs.jsp"%>

<u:authority menuId="134">
	<s:set var="menuId_134" value="true"/>
</u:authority>
<u:authority menuId="151">
	<s:set var="menuId_151" value="true"/>
</u:authority>
<u:authority menuId="152">
	<s:set var="menuId_152" value="true"/>
</u:authority>
<html>
<head>
	<title>渠道管理</title>
</head>

<body>
      <div class="main_search">
        <form method="post" id="mainForm" action="${ctx}/channel/query">
          <ul>
            <li>
              <input type="text" name="text" value="<s:property value="#parameters.text"/>" placeholder="渠道ID/渠道名称" class="txt_177" />
            </li>
            <li>
				<u:select id="channel_type" value="${param.channel_type}" placeholder="渠道类型" dictionaryType="channel_type" />
            </li>
            <li>
              <input type="submit" value="查 询" class="search" />
            </li>
          </ul>
          <u:authority menuId="212">
           	<a href="javascript:;" onclick="edit('')">新增渠道</a>
          </u:authority>
        </form>
      </div>
      <div class="clear"></div>
      <div class="table_ctn">
        <table cellpadding="0" cellspacing="0" border="0">
          <tbody>
            <tr>
              <th width="50px">序号</th>
              <th>渠道ID</th>
              <th>渠道名称</th>
              <th>渠道类型</th>
              <th>渠道模式</th>
              <th>状态</th>
              <th>创建时间</th>
              <th>操作</th>
            </tr>
            <s:iterator value="page.list">
			      <tr>
				      <td>${rownum}</td>
				      <td>${id}</td>
				      <td>${channel_name}</td>
				      <td><u:ucparams key="${channel_type}" type="channel_type"/></td>
				      <td><u:ucparams key="${channel_mode}" type="channel_mode"/></td>
				        <td><u:ucparams key="${channel_status}" type="channel_status"/></td>
				      <td>${create_date}</td>
				      <td>
				      	<s:if test="menuId_134">
					      	<a href="javascript:;" onclick="edit('${id}')">编辑</a> 
					      	<s:if test="menuId_152">
					      		<a href="${ctx}/channel/url?id=${id}">生成链接</a>
					      	 </s:if>
					    </s:if>
					   <s:if test="channel_status==1"> 
				      		<s:if test="menuId_151">
							<a href="javascript:;" onclick="updateStatus(this, '${id}', 2,${channel_status})">删除</a>
						    </s:if>
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
function edit(id){
	location.href="${ctx}/channel/edit?id=" + id;
}

//删除
function updateStatus(a, id, channel_status,old_status){
	if(confirm("确定要删除吗？")){
		$.ajax({
			type: "post",
			url: "${ctx}/channel/updateStatus",
			data:{
				id : id,
				old_status : old_status,
				channel_status : channel_status
			},
			success: function(data){
				if(data.result==null){
					alert("服务器错误，请联系管理员");
					return;
				}
				
				if(data.result=="success"){
					$(a).hide();
				}
				alert(data.msg);
			}
		});
	};
};
</script>
</body>
</html>