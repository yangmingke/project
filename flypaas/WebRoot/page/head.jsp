  <%@ page contentType="text/html; charset=UTF-8"%>
  <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
  <div class="header">
		<div class="logo">
			<c:if test="${user.agentUrl!=null}" >
				<a href="${user.agentUrl}">
			</c:if>
			<c:if test="${user.agentUrl == null}">
				<a href="<%=path%>/index">
			</c:if>
			<c:if test="${user.agentLogo!=null}"> 
				<img src="<%=path%>/page/images/${user.agentLogo}" alt="用户通讯开放平台"/>
			</c:if>
			<c:if test="${user.agentLogo == null}">
				<img src="<%=path%>/page/images/logo.png" alt="用户通讯开放平台"/>
			</c:if>
			</a>
		</div>
		<div class="nav">
			<ul>
				<%-- <li><a href="<%=path%>/index">首页</a></li>
				<li><a href="<%=path%>/product_service/download">下载</a></li>
				<li><a href="http://docs.flypaas.com">文档</a></li>
				<li><a href="http://docs.flypaas.com/doku.php?id=faqs">帮助</a></li>
				<li><a href="<%=com.flypaas.utils.SysConfig.getInstance().getProperty("bbs_host")%>">社区</a></li> --%>
			</ul>
			<div class="header_right">
				<%-- <a href="<%=path %>/user/userCenter" class="link">&nbsp;</a> --%>
				<a href="javascript:void(0)" class="log_out" id="logout">&nbsp;</a>
			</div>
		</div>		
	</div>
	
	
	
	
	<!--弹层（添加号码） bof-->
  <div class="background_box">&nbsp;</div>
	<div class="float_box0 addnum_box" id="popup_box">
	  <div class="float_tit0">
	    <h3></h3>
	    <span class="close"></span>
	  </div>
	  <div class="float_ctn">
	    <div class="float_field" style="font-size: 14px;color: #4c4c4c;"></div>
	    <div class="float_btn">
	      <input type="button" value="取 消" class="cancel_btn" id="cancel"/>
	      <input type="button" value="确 定" class="confirm_btn" id="popup_smt" />
	    </div>
	  </div>
	</div>
  <!--弹层（添加号码） eof-->