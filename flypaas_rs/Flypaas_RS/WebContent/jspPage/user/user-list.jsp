<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %> 
<!DOCTYPE HTML>
<html>
<head>
<meta charset="utf-8">
<meta name="renderer" content="webkit|ie-comp|ie-stand">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width,initial-scale=1,minimum-scale=1.0,maximum-scale=1.0,user-scalable=no" />
<meta http-equiv="Cache-Control" content="no-siteapp" />
<!--[if lt IE 9]>
<script type="text/javascript" src="http://libs.useso.com/js/html5shiv/3.7/html5shiv.min.js"></script>
<script type="text/javascript" src="http://libs.useso.com/js/respond.js/1.4.2/respond.min.js"></script>
<script type="text/javascript" src="http://cdn.bootcss.com/css3pie/2.0beta1/PIE_IE678.js"></script>
<![endif]-->
<link type="text/css" rel="stylesheet" href="${ctx}/css/H-ui.css"/>
<link type="text/css" rel="stylesheet" href="${ctx}/css/H-ui.admin.css"/>
<link type="text/css" rel="stylesheet" href="${ctx}/font/font-awesome.min.css"/>
<link type="text/css" rel="stylesheet" href="${ctx}/css/1.0.8/iconfont.css"/>
<!--[if IE 7]>
<link href="http://www.bootcss.com/p/font-awesome/assets/css/font-awesome-ie7.min.css" rel="stylesheet" type="text/css" />
<![endif]-->
<title>用户管理</title>
</head>
<body>
<nav class="Hui-breadcrumb"><i class="icon-home"></i> 首页 <span class="c-gray en">&gt;</span> 用户管理<span class="c-gray en">&gt;</span> 用户列表<a class="btn btn-success radius r mr-20"  style="line-height:1.6em;margin-top:3px" href="javascript:location.replace(location.href);" title="刷新" ><i class="icon-refresh"></i>刷新</a></nav>
<div class="pd-20">
  <!-- <div class="text-c"> 日期范围：
    <input type="text" onfocus="WdatePicker({maxDate:'#F{$dp.$D(\'datemax\')||\'%y-%M-%d\'}'})" id="datemin" class="input-text Wdate" style="width:120px;">
    -
    <input type="text" onfocus="WdatePicker({minDate:'#F{$dp.$D(\'datemin\')}',maxDate:'%y-%M-%d'})" id="datemax" class="input-text Wdate" style="width:120px;">
    <input type="text" class="input-text" style="width:250px" placeholder="输入用户名称、电话、邮箱" id="" name=""><button type="submit" class="btn btn-success" id="" name=""><i class="icon-search"></i> 搜用户</button>

  </div> -->
  <div class="cl pd-5 bg-1 bk-gray mt-20">
    <!-- <span class="l"><a href="javascript:;" onclick="datadel()" class="btn btn-danger radius"><i class="icon-trash"></i> 批量删除</a> -->
    <a href="javascript:;" onclick="user_add('550','','添加用户','${ctx}/jspPage/user/user-add.jsp')" class="btn btn-primary radius"><i class="icon-plus"></i> 添加用户</a></span>
    <span class="r">共有数据：<strong>${userCount}</strong> 条</span>
  </div>
  <table class="table table-border table-bordered table-hover table-bg table-sort" style="font-size: 12px;">
    <thead>
      <tr class="text-c">
        <th style="width: 4%"><input type="checkbox" name="" value=""></th>
        <th style="width: 4%">序号</th>
        <th style="width: 8%">用户名</th>
        <th style="width: 8%">担任角色</th>
        <th style="width: 8%">真实姓名</th>
        <th style="width: 10%">手机</th>
        <th style="width: 10%">邮箱</th>
        <th style="width: 12%">创建时间</th>
        <th style="width: 10%">联系地址</th>       
        <th style="width: 8%">状态</th>
        <th style="width: 8%">登录次数</th>
        <th style="width: 10%">操作</th>
      </tr>
    </thead>
    <tbody><% int i = 1; %>
    <c:forEach items="${userList}" var="userList">
      <tr class="text-c">
        <td style="width: 4%"><input type="checkbox" value="1" name=""></td>
        <td style="width: 4%"><%=i++%></td>
        <td style="width: 8%">${userList.username}</td>
        <c:forEach items="${userList.role}" var="role">
       	 	<td style="width: 8%">${role.roleName} &nbsp;&nbsp;</td>
        </c:forEach>
        <td style="width: 8%">${userList.realname}</td>
        <td style="width: 10%">${userList.mobile}</td>
        <td style="width: 10%">${userList.email}</td>
        <td style="width: 12%"><fmt:formatDate value="${userList.createDate}" pattern="yyyy-MM-dd" /></td>
        <td style="width: 12%">${userList.address}</td>
        <td class="user-status" style="width: 8%">
        <c:if test="${userList.status==1}">
         <span class="label label-success">正常</span>
        </c:if>
        <c:if test="${userList.status==0}">
         <span class="label">已停用</span>
        </c:if>
        </td>
        <td style="width: 8%">${userList.loginTimes}</td>
        <td class="f-14 user-manage" style="width: 8%">
         <c:forEach items="${userList.role}" var="role">
        	 <c:if test="${role.roleId != 0}">	 
		         <c:if test="${userList.status==1}">
			         	<a style="text-decoration:none" onClick="user_stop(this,'${userList.sid}','${userList.status}','${userList.role}')"  title="停用" href="javascript:;"><i class="Hui-iconfont">&#xe631;</i></a>
		         </c:if>
		         <c:if test="${userList.status==0}">
		         	<a style="text-decoration:none" onClick="user_start(this,'${userList.sid}','${userList.status}')"  title="启用" href="javascript:;"><i class="Hui-iconfont">&#xe601;</i></a>
		         </c:if>
		         <a style="text-decoration:none" onclick="user_edit('${userList.sid}','550','650','修改用户信息','${ctx}/jspPage/user/user-edit.jsp')"title="修改" href="javascript:;"  class="ml-5" ><i class="Hui-iconfont">&#xe60c;</i></a> 
		         <a style="text-decoration:none" onClick="user_password_edit('${userList.sid}','${userList.username}','400','400','重置密码','${ctx}/jspPage/user/user-password-edit2.jsp')"class="ml-5"  href="javascript:;" title="重置密码"><i class="Hui-iconfont">&#xe63f;</i></a> 
	         </c:if>
         </c:forEach>
	        <%-- <a style="text-decoration:none" onclick="user_del(this,'${userList.sid}')" title="删除" href="javascript:;"  class="ml-5" ><i class="Hui-iconfont">&#xe6e2;</i></a> --%>
        </td>
      </tr>
   </c:forEach>
   </tbody>
  </table>
</div>
<script type="text/javascript" src="${ctx}/js/jquery-1.8.2.min.js"></script>
<script type="text/javascript" src="${ctx}/layer/layer.min.js"></script>
<script type="text/javascript" src="${ctx}/js/pagenav.cn.js"></script>
<script type="text/javascript" src="${ctx}/js/H-ui.js"></script>
<script type="text/javascript" src="${ctx}/plugin/My97DatePicker/WdatePicker.js"></script>
<script type="text/javascript" src="${ctx}/js/jquery.dataTables.min.js"></script>
<script type="text/javascript" src="${ctx}/js/H-ui.admin.js"></script>
<script type="text/javascript">
window.onload = (function(){
    // optional set
    pageNav.pre="&lt;上一页";
    pageNav.next="下一页&gt;";
    // p,当前页码,pn,总页面
    pageNav.fn = function(p,pn){$("#pageinfo").text("当前页:"+p+" 总页: "+pn);};
    //重写分页状态,跳到第三页,总页33页
    pageNav.go(1,5);
});
$('.table-sort').dataTable({
	//"lengthMenu":false,//显示数量选择 
	"bFilter": true,//过滤功能
	"bPaginate": true,//翻页信息
	"bInfo": true,//数量信息
	"aaSorting": [[ 1, "desc" ]],//默认第几个排序
	"bStateSave": true,//状态保存
	"aoColumnDefs": [
	  //{"bVisible": false, "aTargets": [ 3 ]}, //控制列的隐藏显示
	  {"orderable":true,"aTargets":[0,8,9]}// 制定列不参与排序
	]
});

</script>
</body>
</html>