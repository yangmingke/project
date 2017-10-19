<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/common/taglibs.jsp"%>
<%@ taglib prefix="decorator" uri="http://www.opensymphony.com/sitemesh/decorator" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<title><decorator:title /></title>
	<cache:cache key="meta_jsp" time="-1">
		<%@ include file="/common/meta.jsp"%>
	</cache:cache>
	
	<decorator:head />
</head>

<body>
	<%--
	根据角色缓存菜单，默认scope="application"，time永远不过期
	可以调用OSCacheUtils.flushMenuCache(roleId)来刷新
	 --%>
	<cache:cache key="headerCache_${login_role_id}" time="-1" groups="menuCache_${login_role_id}">
		<s:action namespace="/menu" name="header" executeResult="true" flush="false" /><%--页面顶部1、2级菜单 --%>
	</cache:cache>
	<!--主体部分content bof-->
	<div class="content">
		<div class="content_wrapper">
			<div class="content_box1">
				
				<s:if test="#request.select_menu.menu3_id != null">
					<cache:cache key="sideMenuCache_${login_role_id}_${select_menu.menu2_id}" time="-1" groups="menuCache_${login_role_id}">
						<s:action namespace="/menu" name="sideMenu" executeResult="true" flush="false" /><%--页面左边3级菜单 --%>
					</cache:cache>
				</s:if>
				<decorator:body />
				
			</div>
		</div>
	</div>
	<!--主体部分content eof--> 
	<cache:cache key="footer_jsp" time="-1">
		<%@ include file="/common/footer.jsp"%>
	</cache:cache>

<script type="text/javascript">
//初始化选中菜单
$(function(){
	var menu1_id = "${select_menu.menu1_id}";
	var menu2_id = "${select_menu.menu2_id}";
	var menu3_id = "${select_menu.menu3_id}";
	if(menu2_id != ""){
		var menu2 = $("#sub_menu_a_" + menu2_id);
		if(menu2.length>0){
			$("#main_menu_" + menu1_id).addClass("active");
			$("#sub_menu_" + menu1_id).show();
			menu2.addClass("active");
		}
	}
	if(menu3_id != ""){
		var menu3 = $("#side_menu_" + menu3_id);
		if(menu3.length>0){
			menu3.addClass("active");
			$(".content_box1").removeClass("content_box1").addClass("content_box");
		}
	}
	
	refreshMsg();//刷新页面顶部未读的消息个数
});

//选择1级菜单
function selectMenu1(a, menuId){
	$(a).parent("li").addClass("active").siblings("li").removeClass("active");
	$("#sub_menu_" + menuId).show().siblings(".sub_menu").hide();
}

//返回
function back(){
	var referer = "${referer}";
	if(referer != ""){
		location.href = referer;
	}else{
		history.back();
	}
}
</script>
</body>
</html>