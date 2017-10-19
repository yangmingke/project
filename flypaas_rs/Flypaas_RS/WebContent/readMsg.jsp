<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<!--[if lt IE 9]>
<script type="text/javascript" src="http://libs.useso.com/js/html5shiv/3.7/html5shiv.min.js"></script>
<script type="text/javascript" src="http://libs.useso.com/js/respond.js/1.4.2/respond.min.js"></script>
<script type="text/javascript" src="http://cdn.bootcss.com/css3pie/2.0beta1/PIE_IE678.js"></script>
<![endif]-->
<link type="text/css" rel="stylesheet" href="${ctx}/css/H-ui.css"/>
<link type="text/css" rel="stylesheet" href="${ctx}/css/H-ui.admin.css"/>
<link type="text/css" rel="stylesheet" href="${ctx}/font/font-awesome.min.css"/>
<!--[if IE 7]>
<link href="http://www.bootcss.com/p/font-awesome/assets/css/font-awesome-ie7.min.css" rel="stylesheet" type="text/css" />
<![endif]-->
<title>Insert title here</title>
</head>
<body>
<nav class="Hui-breadcrumb"><i class="icon-home"></i> 我的消息<span class="c-gray en">&gt;</span> 消息详情<span class="c-gray en"></span> <a class="btn btn-success radius r mr-20" style="line-height:1.6em;margin-top:3px" href="javascript:location.replace(location.href);" title="刷新" ><i class="icon-refresh"></i>刷新</a><a class="btn btn-success radius r mr-20" style="line-height:1.6em;margin-top:3px" href="javascript:history.go(-1)" title="刷新" ><i class="icon-refresh"></i>返回</a></nav>
	<div class="codeView docs-example ">
		<form action="##" method="post"><input type="text" value="<%=request.getParameter("id") %>" id="msgId" style="display: none;"/>
				<table class="table table-border table-bordered table-striped" style="font-size: 12px;">
					<tbody>
						<tr>
							<th width="100"><label>消息标题:</label></th>
							<td ><input type="text" class="input-text" value="<%=new String(request.getParameter("title").getBytes("ISO-8859-1"),"utf-8")%>"></td>
						</tr>
						<tr>
							<th width="100"><label>发布日期:</label></th>
							<td ><input type="text" class="input-text" value="<%=new String(request.getParameter("date").getBytes("ISO-8859-1"),"utf-8")%>" ></td>
						</tr>
						<tr>
							<th width="100" valign="top"><label>消息内容:</label></th>
							<td >
								<textarea rows="20" cols="100" style="text-align: left; line-height: 25px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<%=new String(request.getParameter("msgDesc").getBytes("ISO-8859-1"),"utf-8")%></textarea>
							</td>
						</tr>
					</tbody>
				</table>
			</form>
		</div>
</body>
<script type="text/javascript" src="${ctx}/js/jquery-1.8.2.min.js"></script>
<script type="text/javascript">
	$(function(){
		var msgId = $('#msgId').val();
		$.post("/messageController/updateMsg.action",{"id":msgId},function(data){
			
		})
	});
</script>
</html>