<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/common/taglibs.jsp"%>

<%-- <u:authority menuId="63"> --%>
	<s:set var="menuId_63" value="true"/>
<%-- </u:authority>
<u:authority menuId="64"> --%>
	<s:set var="menuId_64" value="true"/>
<%-- </u:authority>
<u:authority menuId="65"> --%>
	<s:set var="menuId_65" value="true"/>
<%-- </u:authority>
<u:authority menuId="66"> --%>
	<s:set var="menuId_66" value="true"/>
<%-- </u:authority>
<u:authority menuId="67"> --%>
	<s:set var="menuId_67" value="true"/>
<%-- </u:authority>
<u:authority menuId="68"> --%>
	<s:set var="menuId_68" value="true"/>
<%-- </u:authority>
<u:authority menuId="85"> --%>
	<s:set var="menuId_85" value="true"/>
<%-- </u:authority> --%>

<html>
<head>
	<title>资费套餐</title>
</head>

<body>
	<div class="main_search">
        <form method="post" id="mainForm" action="${ctx}/package/query">
          <ul>
            <li>
              <input type="text" name="text" value="<s:property value="#parameters.text"/>" placeholder="套餐名称" class="txt_177" />
            </li>
            <li>
				<u:select id="package_type" value="${param.package_type}" placeholder="套餐类型" dictionaryType="package_type" />
			</li>
             <li>
              <input type="submit" value="查 询" class="search" />
            </li>
            <li>
            	<%-- <u:authority menuId="62"> --%>
              		<a href="javascript:;" onclick="add()">创建套餐</a>
              	<%-- </u:authority> --%>
            </li>
          </ul>
        </form>
      </div>
      <div class="clear"></div>
      <div class="table_ctn">
        <table cellpadding="0" cellspacing="0" border="0">
          <tbody>
            <tr>
              <th width="50px">序号</th>
              <th>套餐编号</th>
              <th>套餐名称</th>
              <th>套餐类型</th>
              <th>付费类型</th>
              <th>套餐状态</th>
              <th>保底消费（元）</th>
              <th>开发者数量</th>
              <th>创建时间</th>
              <th>更新时间</th>
              <th>操作</th>
            </tr>
            <s:iterator value="page.list">
	            <tr>
	            	<td>${rownum}</td>
	            	<td>${package_id}</td>
	            	<td>${package_name}</td>
	            	<td>${package_type_name}</td>
	            	<td>${pay_type_name}</td>
	            	<td name="status_name">${status_name}</td>
	            	<td>${min_consumption}</td>
	            	<td name="developer_number">${developer_number}</td>
	            	<td>${create_date}</td>
	            	<td>${update_date}</td>
	            	<td>
	            		<%--0关闭 --%>
	            		<span name="operate_0" ${status==0 ? "" : "style='display:none;'"}>
				      		<%-- <s:if test="menuId_63"> --%>
								<a href="javascript:;" onclick="view('${package_id}')">查看</a>
							<%-- </s:if>
				      		<s:if test="menuId_64"> --%>
								| <a href="javascript:;" onclick="updateStatus(this, '${package_id}', 1)">启用</a>
							<%-- </s:if>
				      		<s:if test="menuId_66"> --%>
								| <a href="javascript:;" onclick="edit('${package_id}')">编辑</a>
							<%-- </s:if>
				      		<s:if test="menuId_67"> --%>
								| <a href="javascript:;" onclick="updateStatus(this, '${package_id}', 2)">删除</a>
							<%-- </s:if> --%>
	            		</span>
	            		
	            		<%--1启用 --%>
	            		<span name="operate_1" ${status==1 ? "" : "style='display:none;'"}>
				      		<%-- <s:if test="menuId_63"> --%>
								<a href="javascript:;" onclick="view('${package_id}')">查看</a>
							<%-- </s:if>
				      		<s:if test="menuId_65"> --%>
								| <a href="javascript:;" onclick="updateStatus(this, '${package_id}', 0)">关闭</a>
							<%-- </s:if>
				      		<s:if test="menuId_66"> --%>
								| <a href="javascript:;" onclick="edit('${package_id}')">编辑</a>
							<%-- </s:if>
				      		<s:if test="menuId_68"> --%>
								| <a href="javascript:;" onclick="showDeveloperTransfer(this, '${package_id}')">开发者转移</a>
							<%-- </s:if> --%>
	            		</span>
	            		
	            		<%--2已删除 --%>
	            		<span name="operate_2" ${status==2 ? "" : "style='display:none;'"}>
				      		<%-- <s:if test="menuId_63"> --%>
								<a href="javascript:;" onclick="view('${package_id}')">查看</a>
							<%-- </s:if>
				      		<s:if test="menuId_85"> --%>
								| <a href="javascript:;" onclick="updateStatus(this, '${package_id}', 0)">恢复</a>
							<%-- </s:if> --%>
	            		</span>
					</td>
	            </tr>
	        </s:iterator>
          </tbody>
        </table>
		
		<u:page page="${page}" formId="mainForm" />
      </div>


<!--弹层(开发者转移) bof-->
<div class="float_box package_box" id="transfer_box" style="display:none;">
  <div class="float_tit">
    <h3>开发者转移</h3>
  </div>
  <div class="float_ctn">
	<form method="post" id="developerTransferForm">
      <div class="select_box">
        <label>请选择转移套餐：</label>
        <u:select id="select_package" sqlId="package" callback="selectPackage"/>
      </div>
      <div class="clear"></div>
      <p><b>确认信息：</b></p>
      <p><label>套餐编号：</label><span id="box_package_id"></span></p>
      <p><label>套餐名称：</label><span id="box_package_name"></span></p>
      <p><label>保底消费：</label><span id="box_min_consumption" class="money"></span></p>
      <div class="select_box">
        <label>是否推送消息：</label>
        <u:radio name="is_send_msg" dictionaryType="is_send_msg" />
      </div>
      <div class="clear"></div>
      <span id="msg" class="error" style="display:none;"></span>
      <hr class="hr" />
      <p class="btn">
      	<input type="hidden" id="package_id" name="package_id"/>
      	<input type="hidden" id="new_package_id" name="new_package_id"/>
        <input type="button" value="转 移" onclick="developerTransfer(this)" />
        <input type="button" value="取 消" onclick="hideBox('transfer_box')"/>
      </p>
     </form>
  </div>
</div>
<!--弹层(开发者转移) eof-->

<!--弹层(转移成功) bof-->
<div class="float_box package_box success_box" id="transfer_sus_box" style="display:none;">
  <div class="float_tit">
    <h3>转移成功</h3>
  </div>
  <div class="float_ctn">
      <p>账户转移成功，请<a href="javascript:;" class="cancel_btn" onclick="closeBox()">返回</a></p>
  </div>
</div>
<!--弹层(转移成功) eof-->

<script type="text/javascript">
var validate;
$(function(){
	//表单验证规则
	validate = $("#developerTransferForm").validate({
		rules: {
			select_package: "required",
			is_send_msg: "required"
		},
		messages: {
			select_package: "请选择转移套餐",
			is_send_msg: "请选择是否推送消息"
		}
	});
});

//添加
function add(){
	location.href="${ctx}/package/add";
}
//修改
function edit(package_id){
	location.href="${ctx}/package/edit?package_id=" + package_id;
}
//查看
function view(package_id){
	location.href="${ctx}/package/view?package_id=" + package_id;
}

//修改套餐状态：关闭、启用、删除
function updateStatus(a, package_id, status){
	var status_name="";
	switch(status){
	case 0:	status_name = "关闭";
			break;
	case 1:	status_name = "启用";
			break;
	case 2:	status_name = "已删除";
			break;
	}
	
	if((status==0 || status==2) && parseInt($(a).parents("tr").find("[name='developer_number']").text()) > 0){
		alert("开发者数量大于0，不能"+status_name+"，请先进行“开发者转移”操作");
		return;
	}
	
	if(confirm("确定要"+ $(a).text() +"吗？")){
		$.ajax({
			type: "post",
			url: "${ctx}/package/updateStatus",
			data:{
				package_id : package_id,
				status : status
			},
			success: function(data){
				if(data.result==null){
					alert("服务器错误，请联系管理员");
					return;
				}
				
				if(data.result=="success"){
					$(a).parent().hide();
					$(a).parents("tr").find("[name='operate_"+status+"']").show();
					$(a).parents("tr").find("[name='status_name']").text(status_name);
					alert($(a).text() + "成功");
					location.reload(true);
					
				}else{
					alert(data.msg);
				}
			}
		});
	}
}

//显示开发者转移弹层
function showDeveloperTransfer(a, package_id){
	if(parseInt($(a).parents("tr").find("[name='developer_number']").text()) == 0){
		alert("开发者数量为0，不需要转移");
		return;
	}
	
	$("#package_id").val(package_id);
	$("#select_package").val("").next().empty();
	$("#div_select_package li[val^='"+package_id+"__']").hide().siblings().show(); //隐藏自己
	$("#box_package_id, #box_package_name, #box_min_consumption").empty();
	$("#msg").hide();
	$("#new_package_id").val("");
	showBox("transfer_box");
}

//选择套餐
function selectPackage(value, text){
	var v = value.split("__");
	$("#box_package_id").text(v[0]);
	$("#box_package_name").text(v[1]);
	$("#box_min_consumption").text("￥"+v[2]);
	$("#new_package_id").val(v[0]);
}

//保存开发者转移
function developerTransfer(btn){
	$("#msg").hide();
	if(!validate.form()){
		return;
	}
	
	var options = {
		beforeSubmit : function() {
			$(btn).attr("disabled", true);
		},
		success: function(data){
			$(btn).attr("disabled", false);
			if(data.result==null){
				alert("服务器错误，请联系管理员");
				return;
			}
			
			if(data.result=="success"){
				hideBox("transfer_box");
				showBox("transfer_sus_box");
			}else{
				$("#msg").text(data.msg).show();
			}
		},
		url: "${ctx}/package/developerTransfer",
		type : "post",
		timeout:30000
	};
	$("#developerTransferForm").ajaxSubmit(options);
}

//返回
function closeBox(){
	hideBox("transfer_sus_box");
	location.reload(true);
}
</script>
</body>
</html>