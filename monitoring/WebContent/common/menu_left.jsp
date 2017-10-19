<%@ page contentType="text/html;charset=UTF-8" %>
	<!--Header-part-->
	<div id="header">
	  <h1><a href="${ctx}/index">云验证系统</a></h1>
	</div>

	<div id="user-nav" class="navbar navbar-inverse">
	  <ul class="nav">
	    <li  class="dropdown" id="profile-messages" ><a title="" href="#" data-toggle="dropdown" data-target="#profile-messages" class="dropdown-toggle"><i class="fa fa-user"></i>  <span class="text">欢迎光临，${sessionScope.readName}</span><b class="caret"></b></a>
	      <ul class="dropdown-menu">
	        <li><a href="#"><i class="fa fa-user"></i> 个人资料</a></li>
	        <li class="divider"></li>
	        <li><a href="#"><i class="fa fa-edit"></i> 修改密码</a></li>
	      </ul>
	    </li>
	    <li class="dropdown" id="menu-messages"><a href="#" data-toggle="dropdown" data-target="#menu-messages" class="dropdown-toggle"><i class="fa fa-envelope"></i> <span class="text">消息</span> <span class="label label-important">5</span> <b class="caret"></b></a>
	      <ul class="dropdown-menu">
	        <li><a class="sAdd" title="" href="#"><i class="fa fa-plus"></i> 新消息</a></li>
	        <li class="divider"></li>
	        <li><a class="sInbox" title="" href="#"><i class="fa fa-envelope"></i> 收件箱</a></li>
	        <li class="divider"></li>
	        <li><a class="sOutbox" title="" href="#"><i class="fa fa-arrow-up"></i> 发件箱</a></li>
	        <li class="divider"></li>
	        <li><a class="sTrash" title="" href="#"><i class="fa fa-trash-o"></i> 垃圾箱</a></li>
	      </ul>
	    </li>
	    <li class=""><a title="" href="#"><i class="fa fa-cog"></i> <span class="text">设置</span></a></li>
	    <li class=""><a title="" href="${ctx}/logout"><i class="fa fa-power-off"></i> <span class="text">退出</span></a></li>
	  </ul>
	</div>
	<!--close-top-Header-menu-->
