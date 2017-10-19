<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%@ taglib prefix="s"  uri="/struts-tags"%>
<%@ taglib prefix="u"  uri="/flypaas-tags"%>
<!DOCTYPE html>
<html lang="zh-cn">
<head>
	<meta charset="UTF-8"><meta http-equiv="X-UA-Compatible" content="IE=edge" />
	<title>快传开放平台管理中心_消息</title>
	<link rel="stylesheet" type="text/css" href="<%=path%>/page/css/style.css" />
	<%@include file="/page/resource.jsp"%>
</head>
<body id="5">
	<!--头部header bof-->
	<%@include file="/page/head.jsp" %>
	<!--头部header eof-->

	<!--主体content bof-->
	<div class="content">

		<!--侧边side bof-->
		<%@include file="/page/left.jsp" %>
		<!--侧边side bof-->
		<input type="hidden" value="<%=path%>" id="path_fo_js" />
		<!--右侧main bof-->
		<div class="main">
			<div class="breadcrumbs">
				<ul>
					<li><a href="<%=path %>/user/account">用户控制台</a></li>
					<li class="active"><a href="#">消息</a></li>
				</ul>
			</div>

			<div class="msg_box">
				<s:if test="page.list.size()>0">
       	     		<s:iterator value="page.list" id="u">
						<div id="${u.msg_id}" <s:if test="#u.hasread==1">class="msg_list read"</s:if><s:else>class="msg_list"</s:else>>
							<h1><s:if test="#u.msg_title.length()>50"><s:property value="#u.msg_title.substring(0,50)+'...'"/></s:if><s:else>${u.msg_title }</s:else></h1>
							${u.msg_desc }
							<p><a href="javascript:void(0)" class="del" onclick="popupBox('提示','确认删除?','delNotice(\'${u.msg_id}\')',null)">&nbsp;</a></p>
						</div>
						<script type="text/javascript">
							var hasrd = ${u.hasread};
							var id = ${u.msg_id};
							if(hasrd==0){
								//鼠标放上去变成已读
								$("#"+id).hover(function(){
									$(this).addClass("read");
									$(this).unbind("hover");
									var id = $(this).attr("id");
									updateHasRead(id);
								});
							}
						</script>
					</s:iterator>
				</s:if>
				<s:else>
					<div>
					<p>&nbsp;</p>
					<p>&nbsp;暂无数据！</p>
					</div>
				</s:else>
			</div>
			<!--分页pagebox bof-->
				 <s:form namespace="/user" action="notice" id="ntFrm" theme="simple">
		          	<input type="hidden" id="currentPage" name="page.currentPage"/>
		         </s:form>
       	  		<u:page page="${page}" formId="ntFrm" ></u:page>
				<!--分页pagebox eof-->
		</div>
		<!--右侧main bof-->

	</div>
	<!--主体content eof-->


	<!--底部footer bof-->
	<%@include file="/page/foot.jsp" %>
	<!--底部footer eof-->
	<script type="text/javascript" src="<%=path%>/js/cookie.js"></script>
	<script type="text/javascript" src="<%=path%>/js/login.js"></script>
	
	 <script type="text/javascript">
              function delNotice(msg_id){
            	  $.ajax({
            		 url:"<%=path%>/user/delNotice", 
            		 data:"msgId="+msg_id,
            		 type:"post",
            		 dataType:"text",
            		 success: function (data) {
         				window.location.reload();
	                 },
                     error: function (msg) {
                     }
            	  });
              }
              function updateHasRead(id){
      			  $("#"+id).onmouseover=null;
            	  $.ajax({
          			url:"<%=path%>/user/updateNotice",
          			type:"post",
          			data:"msgId="+id,
          			dataType: "text",
          			success: function (data) {
          				var count = $("#countMsg").text();
          				$("#countMsg").text(count-1);
                    },
                    error: function (msg) {
                    }
          		});
              }
              </script> 
</body>
</html>