<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/common/taglibs.jsp"%>

      <div class="main_search">
      <form method="post" id="queryDeveloperForm" action="${ctx}/msg/queryDeveloper" onsubmit="return submitDiv(this, 'queryDeveloper');">
      	<ul>
            <li>
				<input type="text" name="text" value="<s:property value="#parameters.text"/>" maxlength="40" placeholder="开发者ID/用户名/手机号/昵称/认证名称" class="txt_250" />
			</li>
            <li class="time">
            	<u:date id="start_date" value="${param.start_date}" placeholder="开始时间" maxId="end_date,-1" maxToday="-1" />
				<span>至</span>
            	<u:date id="end_date" value="${param.end_date}" placeholder="结束时间" minId="start_date,1" maxToday=""  />
			</li>
            <li>
				<u:select id="user_status" value="${param.user_status}" placeholder="开发者状态" dictionaryType="user_status" />
			</li>
            <li>
				<u:select id="oauth_status" value="${param.oauth_status}" placeholder="认证状态" dictionaryType="oauth_status" />
			</li>
            <li>
				<u:select id="package_id" value="${param.package_id}" placeholder="套餐名称" sqlId="selectPackage" />
			</li>
            <li>
				<input type="submit" value="查 询" class="search"/>
			</li>
           </ul>
      </form>
      </div>
      <div class="clear"></div>
		<input type="button" value="确 定" class="org_btn" onclick="hideBox('queryDeveloper')"/>
		<input type="button" value="清空" class="grey_btn" onclick="clearDeveloper()" />
		已选择<span id="developer_num_float">0</span>个开发者
		<br/>
		<select id="developer_select_float" multiple="multiple" size="5" class="developer_select"></select>
      <div class="table_ctn">
      <table cellpadding="0" cellspacing="0" border="0" id="queryDeveloper_table">
      		<thead>
		      <tr>
			      <th><label><input type="checkbox" onclick="selectAll(this)" />全选</label></th>
			      <th>序号</th>
			      <th>开发者ID</th>
			      <th>用户名</th>
			      <th>手机号</th>
			      <th>昵称</th>
			      <th>认证名称</th>
			      <th>开发者状态</th>
			      <th>认证状态</th>
			      <th>套餐名称</th>
			      <th>注册时间</th>
		      </tr>
      		</thead>
		     <tbody id="queryDeveloper_tbody">
		      <s:iterator value="page.list">
			      <tr class="developer_tr" onclick="selectTr(this)" select="false" value="${sid}" text="${sid}【${email}】【${username}】">
				      <td><input type="checkbox" /></td>
				      <td>${rownum}</td>
				      <td>${sid}</td>
				      <td>${email}</td>
				      <td>${mobile}</td>
				      <td>${username}</td>
				      <td>${realname}</td>
				      <td><u:ucparams type="user_status" key="${status}"/></td>
				      <td><u:ucparams type="oauth_status" key="${oauth_status}"/></td>
				      <td><u:ucparams sqlId="allPackage" key="${package_id}" /></td>
				      <td>${create_date}</td>
			      </tr>
		      </s:iterator>
	      </tbody>
      </table>
      
      <u:page page="${page}" formId="queryDeveloperForm" />
      </div>

<script type="text/javascript">
var developer_select = $("#developer_select, #developer_select_float");
var developer_num = $("#developer_num, #developer_num_float");

$(function(){
	developer_num.text($("#developer_select option").length);
	$("#developer_select_float").html($("#developer_select").html());
	$("#developer_select option").each(function(){
		var tr = $("#queryDeveloper_tbody tr[value='"+$(this).attr("value")+"']");
		tr.attr("select", true);
		tr.find(":checkbox").prop("checked", true);
	});
});

//清空
function clearDeveloper(){
	developer_num.text(0);
	developer_select.empty();
	$("#queryDeveloper_table tr").attr("select", false);
	$("#queryDeveloper_table :checkbox").prop("checked", false);
}

//全选
function selectAll(box){
	var select = $(box).is(":checked");
	
	$("#queryDeveloper_tbody tr").each(function(){
		addDeveloper($(this), select);
	});
	developer_num.text($("#developer_select option").length);
}

//选择一行
function selectTr(tr){
	addDeveloper($(tr), $(tr).attr("select")=="false");
	developer_num.text($("#developer_select option").length);
}

function addDeveloper(tr, select){
	tr.attr("select", select);
	tr.find(":checkbox").prop("checked", select);
	
	var value = tr.attr("value");
	var text = tr.attr("text");
	var option = developer_select.find("option[value='"+value+"']");
	if(select){
		if(option.length==0){
			developer_select.append("<option value='"+value+"'>"+text+"</option>");
		}
	}else{
		option.remove();
	}
}
</script>
