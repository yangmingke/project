    <%@ page contentType="text/html; charset=UTF-8"%>
    <div class="side">
            <div class="profile">
                <dl>
                    <dt><img src="<%=path%>/page/images/photo.png" /></dt>
                    <dd><s:property value="#session.user.userName" /></dd>
                    <dd class="user"><s:property value="#session.user.email" /></dd>
                </dl>
                <a href="<%=path%>/user/userCenter" class="edit">&nbsp;</a>
            </div>
            <div class="quick_link">
                <a href="<%=path %>/user/notice" class="msg">消 息<span id="countMsg">${sessionScope.user.countMsg }</span></a>
                <a href="<%=path %>/app/addPage" class="add_app">创建应用</a>
            </div>
            <%@include file="/page/left_menu.jsp" %>
    </div>