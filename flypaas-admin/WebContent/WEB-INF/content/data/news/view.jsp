<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/common/taglibs.jsp"%>

<html>
<head>
	<title>查看新闻</title>
</head>

<body>
<div class="main_ctn">
	<h1>
		<a href="javascript:;" class="back" onclick="history.back();">返 回</a>查看新闻
	</h1>
	<div class="admin_material">
		<p>
			<label>新闻id</label>
			<span>${data.news_id}</span>
		</p>
		<p>
			<label>更新时间</label>
			<span>${data.update_date}</span>
		</p>
		<p>
			<label>状态</label>
			<span>${data.status_name}</span>
		</p>
		<p>
			<label>标题</label>
			<span>${data.title}</span>
		</p>
		<p>
			<label>副标题</label>
			<span>${data.subtitle}</span>
		</p>
		<p>
			<label>摘要</label>
			<span>${data.summary}</span>
		</p>
		<p>
			<label>内容</label>
			<u:ueditor id="content" value="${data.content}" type="view"/>
		</p>
	</div>
</div>
</body>
</html>