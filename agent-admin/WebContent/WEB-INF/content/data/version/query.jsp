<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/common/taglibs.jsp"%>

<u:authority menuId="223">
	<s:set var="menuId_223" value="true"/>
</u:authority>
<u:authority menuId="224">
	<s:set var="menuId_224" value="true"/>
</u:authority>
<u:authority menuId="225">
	<s:set var="menuId_225" value="true"/>
</u:authority>
<u:authority menuId="231">
	<s:set var="menuId_231" value="true"/>
</u:authority>
<html>
<head>
	<title>版本管理</title>
</head>

<body>
      <div class="main_search">
        <form method="post" id="mainForm" action="${ctx}/version/query">
          <ul>
            <li>
              <input type="text" name="version_index" value="<s:property value="#parameters.version_index"/>" placeholder="版本号" class="txt_177" />
            </li>
            <li>
               <u:select id="version_name_key" value="${param.version_name_key}" placeholder="版本名称" dictionaryType="version_name" clazz="sel_221" />
             </li>
            <li>
				<u:select id="version_type" value="${param.version_type}" placeholder="版本类型" dictionaryType="version_type" clazz="sel_221" />
            </li>
             <li>
				<u:select id="version_status" value="${param.version_status}" placeholder="版本状态" dictionaryType="version_status" includeValue=",1,2,4" />
            </li>
            <li>
              <input type="submit" value="查 询" class="search" />
            </li>
          </ul>
          <u:authority menuId="222">
           	<a href="javascript:;" onclick="edit('')">新增版本</a>
          </u:authority>
        </form>
      </div>
      <div class="clear"></div>
      <div class="table_ctn">
        <table cellpadding="0" cellspacing="0" border="0">
          <tbody>
            <tr>
              <th width="50px">序号</th>
              <th>产品名称</th>
              <th>版本号</th>
              <th>下载次数</th>
              <th>类型</th>
              <th>状态</th>
              <th>更新时间</th>
              <th>操作</th>
            </tr>
            <s:iterator value="page.list">
			      <tr>
				      <td>${rownum}</td>
				      <td>${version_name}</td>
				      <td>${version_index}</td>
				      <td>${download_count}</td>
				      <td><u:ucparams key="${version_type}" type="version_type"/></td>
				      <td><u:ucparams key="${version_status}" type="version_status"/></td>
				      <td>${update_date}</td>
				      <td>
				      <s:if test="version_status != 3">
				      		<s:if test="menuId_223">
						      	<a href="javascript:;" onclick="edit('${id}')">编辑</a> 
				      		</s:if>
				      	</s:if>
				      	<s:if test="version_status == 1 ||version_status == 4">
				      		<s:if test="menuId_224">
				      			| <a href="javascript:;" onclick="updateStatus(this, '${id}','${version_name_key}', 2,${version_status})">设置当前版本</a>
				      		</s:if>
				      		<s:if test="menuId_223">
								| <a href="javascript:;" onclick="updateStatus(this, '${id}','${version_name_key}', 3,${version_status})">删除</a>
				      		</s:if>
				      	</s:if>
				      
				      	<s:if test="version_status==2">
				      		<s:if test="menuId_231">
				      			| <a href="javascript:;" onclick="updateStatus(this, '${id}','${version_name_key}', 4,${version_status})">下线</a>
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
	location.href="${ctx}/version/edit?id=" + id;
}

//删除
function updateStatus(a, id,version_name_key,version_status,old_status){
	var text = $(a).text();
	if(confirm("确定要 "+text+" 吗？")){
		$.ajax({
			type: "post",
			url: "${ctx}/version/updateStatus",
			data:{
				id : id,
				version_name_key:version_name_key,
				old_status : old_status,
				version_status : version_status
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