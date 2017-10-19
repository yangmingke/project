<%@ page import="java.util.*,org.apache.commons.lang3.StringUtils" contentType="text/html;charset=UTF-8" %>
<%@ include file="/common/taglibs.jsp"%>
<s:set var="title">${data.role_id==null? '添加' : '修改'}</s:set>

<html>
<head>
	<title>权限管理 - ${title}</title>
</head>

<body>
      <div class="main_ctn">
        <h1>${title}管理权限</h1>
        <div class="author_modify">
        <form method="post" id="mainForm">
          <p><label>管理身份</label><input type="text" name="role_name" value="${data.role_name}" maxlength="20"/></p>
          <p><label>权限配置</label></p>	
          <%
			Map<String, Object> data = (Map<String, Object>)request.getAttribute("data");
			Map<String, List<Map<String, Object>>> menuMap = (Map<String, List<Map<String, Object>>>)data.get("menuMap");
			
			//System.out.println(menuMap);
			
			showMenu(menuMap,"0", out);
			out.println("</dl>");
			%>
		  <p><span id="msg" class="error" style="display:none;"></span></p>
          <hr class="hr" />
          <p class="btn">
          	  <input type="hidden" name="role_id" value="${data.role_id}"/>
          	  <input type="hidden" name="isUpdateMenu" id="isUpdateMenu"/>
	          <input type="button" value="确 定" class="org_btn" onclick="save(this)"/>
	          <input type="button" value="取 消" class="grey_btn" onclick="back()"/>
          </p>
        </form>
        </div>
      </div>

<%!
/**
*显示树形菜单
*/
private void showMenu(Map<String, List<Map<String, Object>>> menuMap, String parentId, JspWriter out) throws Exception{
	List<Map<String, Object>> menuList = menuMap.get(parentId);
	
	for(int i = 0; i<menuList.size(); i++){
		Map<String, Object> menu = menuList.get(i);
		String menuId = menu.get("menu_id").toString();
		String menuName = menu.get("menu_name").toString();
		int level = Integer.parseInt(menu.get("level").toString());
		String checked = menu.get("checked").toString();
		String childParentId;
		
		if(level==1){
			parentId = "";
			childParentId = menuId;
			
			//System.out.println("-------------");
			if(i>0){
				out.println("</dl>");
			}
			out.println("<dl><dt></dt>");
		}else{
			parentId = menu.get("parent_id").toString();
			childParentId = parentId + "," + menuId;
		}
		
		out.print("<dd>");
		for(int j=1; j<level; j++){
			//System.out.print("\t");
			out.print("<span></span>");
		}
		//System.out.println(parentId+"--"+menu);
		out.println("<label class='checkbox_label'><input type='checkbox' id='menu_"+menuId+"' name='menu_id' value='"+menuId+"' parentId='"+parentId+"' "+ checked +" onclick='clickMenu(this)'/>"+menuName+"</label>");
		if(menuMap.containsKey(childParentId)){//存在子菜单，则递归显示
			showMenu(menuMap, childParentId, out);
		}
		out.print("</dd>");
	}
}
%>

<script type="text/javascript">
var validate;
var selectMenu;

$(function(){
	//表单验证规则
	validate = $("#mainForm").validate({
		rules: {
			role_name: "required"
		},
		messages: {
			role_name: "请输入管理身份"
		}
	});
	
	selectMenu = $(":checkbox").serialize();
});

//保存
function save(btn){
	$("#msg").hide();
	if(!validate.form()){
		return;
	}
	
	var newSelectMenu = $(":checkbox").serialize();
	$("#isUpdateMenu").val(selectMenu != newSelectMenu); //菜单是否已修改
	selectMenu = newSelectMenu;
	
	var options = {
		beforeSubmit : function() {
			$(btn).attr("disabled", true);
		},
		success : function(data) {
			$(btn).attr("disabled", false);
			
			if(data.result==null){
				$("#msg").text("服务器错误，请联系管理员").show();
				return;
			}
			
			$("#msg").text(data.msg).show();
		},
		url : "${ctx}/authority/save",
		type : "post",
		timeout:30000
	};
	$("#mainForm").ajaxSubmit(options);
}

//点击一个菜单
function clickMenu(menu){
	var value = $(menu).val();
	var checked = $(menu).is(":checked");
	var parentId = $(menu).attr("parentId");
	
	if(parentId != ""){
		var parentIdArray = parentId.split(",").reverse();
		
		for(var i=0; i<parentIdArray.length; i++){
			var box = $(":checkbox#menu_"+parentIdArray[i]); //父菜单
			
			if(checked){
				box.attr("checked", true).prop("checked", true);
				box.parent("span").addClass("checked");
				
			}else{
				/* 取消菜单不影响父菜单的选中，即一个菜单可以没有子菜单
				var childParentId = box.val();
				if(box.attr("parentId")!=""){
					childParentId = box.attr("parentId")+"," + childParentId;
				}
				var child = $(":checkbox[parentId='"+childParentId+"']:checked"); //子菜单
				
				if(child.length==0){
					box.attr("checked", false).prop("checked", false);
					box.parent("span").removeClass("checked");
				}
				 */
			}
		}
	}
	
	var childParentId = value;
	if(parentId!=""){
		childParentId = parentId+"," + childParentId;
	}
	
	var box2 = $(":checkbox[parentId='" +childParentId+ "'], :checkbox[parentId^='" +childParentId+ ",']");
	box2.attr("checked", checked).prop("checked", checked);//选中或取消所有子菜单
	if(checked) {
		box2.parent("span").addClass("checked");
	}else{
		box2.parent("span").removeClass("checked");
	}
}
</script>
</body>
</html>