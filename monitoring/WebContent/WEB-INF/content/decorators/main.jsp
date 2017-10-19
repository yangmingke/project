<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/common/taglibs.jsp"%>
<%@ taglib prefix="decorator" uri="http://www.opensymphony.com/sitemesh/decorator" %>
<!DOCTYPE html>
<html lang="zh-cn">
<head>
	<title></title>
	<meta charset="UTF-8" />
	<meta name="viewport" content="width=device-width, initial-scale=1.0" />
	<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
	<head />
</head>
<body class="body_all">

	<%--头 --%>
	<cache:cache key="headerCache_user_${LOGIN_USER_ID}" time="-1" groups="menuCache_user_${LOGIN_USER_ID}">
		<%--页面顶部用户信息加载 --%>
		<s:action namespace="/admin" name="topHeader" executeResult="true" flush="false" />
	</cache:cache>
	<%--根据角色缓存菜单，默认scope="application"，time永远不过期可以调用OSCacheUtils.flushMenuCache(roleId)来刷新 --%>
	<cache:cache key="headerCache_role_${LOGIN_ROLE_ID}" time="1" groups="menuCache_role_${LOGIN_ROLE_ID}">
		<s:action namespace="/menu" name="leftmenu" executeResult="true" flush="false" /><%--页面顶部1、2级菜单 --%>
	</cache:cache>
	<div id="content" class="content">
		<div class="tab">
			<div class="tab_tit">
				<ul></ul>
			</div>
			<div class="tab_ctn"></div>
		</div>
	</div>
	<!--主体部分content eof  xxx--> 
	<!-- <cache:cache key="footer_jsp" time="-1">
		<%@ include file="/common/footer.jsp"%>
	</cache:cache> -->
<script src="${ctx}/js/jquery.min.js"></script>
<script src="${ctx}/js/bootstrap.min.js"></script> 
<script src="${ctx}/js/matrix.js"></script>
<script type="text/javascript">
//初始化选中菜单
$(function(){
	var curr_id = '<decorator:getProperty property="body.menuId"/>';
	var mi = $("#menu_id_"+curr_id);
	var html = "";
	var active = true;
	while(mi.size()>0){
		if(active){
			mi.addClass("active");
		}else{
			mi.addClass("open");
		}
		active =  false;
		html = '<a href="'+mi.find("a:first").attr("href")+'">'+mi.attr("title")+'</a>' + html;
		mi = $("#menu_id_"+mi.attr("pid"));
	}
	if(html.indexOf('<decorator:title />') < 0 ){
		html = html + '<a href="#"><decorator:title /></a>';
	}
	var breadcrumb = $("#breadcrumb");
	breadcrumb.html('<a href="${ctx}/index" title="返回首页" class="tip-bottom"><i class="fa fa-home"></i>首页</a>' + html);
	breadcrumb.find("a:last").addClass("current");
});
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