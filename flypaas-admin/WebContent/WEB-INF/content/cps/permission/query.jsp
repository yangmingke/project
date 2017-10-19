<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/common/taglibs.jsp"%>

<u:authority menuId="256">
	<s:set var="menuId_256" value="true"/>
</u:authority>
<u:authority menuId="257">
	<s:set var="menuId_257" value="true"/>
</u:authority>
<u:authority menuId="258">
	<s:set var="menuId_258" value="true"/>
</u:authority>

<html>
<head>
	<title>策略权限列表</title>
</head>

<body>
   <div class="main_ctn">
     <h1>策略权限列表</h1>
     <div class="main_search">
   <form method="post" id="mainForm" action="${ctx}/permission/query">
   <ul>
  <li>
	<u:select id="pv" value="${param.pv}" placeholder="手机平台" dictionaryType="cps_pv" />
  </li>
   <li>
   	<input type="text" name="text" value="<s:property value="#parameters.text"/>" placeholder="客户端SDK版本" maxlength="40" class="txt_177" />
   </li>
   <li><input type="submit" value="查 询" class="search" /></li>
   </ul>
   <u:authority menuId="255">
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
    	<th>手机平台</th>
    	<th>客户端SDK版本</th>
    	<th>P2P探测使能</th>
    	<th>音频FEC使能</th>
    	<th>日志上报使能</th>
    	<th>驱动自动适配</th>
    	<th>语音质量监控使能</th>
    	<th>更新时间</th>
    	<th>操作</th>
    </tr>
    <s:iterator value="page.list">
     <tr>
      <td>${rownum}</td>
      <td>${id}</td>
      <td><u:ucparams type="cps_pv" key="${pv}" /></td>
      <td>${ver}</td>
      <td><u:ucparams type="cps_enable" key="${iceenable}" /></td>
      <td><u:ucparams type="cps_enable" key="${audiofec}" /></td>
      <td><u:ucparams type="cps_enable" key="${logreport}" /></td>
      <td><u:ucparams type="cps_enable" key="${autoadapter}" /></td>
      <td><u:ucparams type="cps_enable" key="${vqmenable}" /></td>
      <td>${updatetime}</td>
      <td>
      		<s:if test="menuId_256">
				<a href="javascript:;" onclick="view(${id})">查看</a>
			</s:if>
      		<s:if test="menuId_257">
				| <a href="javascript:;" onclick="edit(${id})">编辑</a>
			</s:if>
      		<s:if test="menuId_258">
				| <a href="javascript:;" onclick="deleteData(${id})">删除</a>
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
	location.href="${ctx}/permission/add";
}

//查看
function view(id){
	location.href="${ctx}/permission/view?id=" + id;
}

//编辑
function edit(id){
	location.href="${ctx}/permission/edit?id=" + id;
}

//删除
function deleteData(id){
	if(confirm("确定要删除吗？")){
		$.ajax({
			type: "post",
			url: "${ctx}/permission/delete",
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