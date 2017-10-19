<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/common/taglibs.jsp"%>

<html>
<head>
	<title>查看任务日志</title>
</head>

<body>
<div class="main_ctn">
	<h1>
		<a href="javascript:;" class="back" onclick="history.back();">返 回</a>查看任务日志
	</h1>
	<div class="admin_material">
		<p>
			<label>任务日志id</label>
			<span>${data.log_id}</span>
		</p>
		<p>
			<label>任务id</label>
			<span>${data.task_id}</span>
		</p>
		<p>
			<label>任务名称</label>
			<span>${data.task_name}</span>
		</p>
		<p>
			<label>任务类型</label>
			<span><u:ucparams key="${data.task_type}" type="task_type" /></span>
		</p>
		<p>
			<label>数据时间</label>
			<span>${data.data_date}</span>
		</p>
		<p>
			<label>开始时间</label>
			<span>${data.start_date}</span>
		</p>
		<p>
			<label>结束时间</label>
			<span>${data.end_date}</span>
		</p>
		<p>
			<label>持续时间<br/>(时:分:秒)</label>
			<span>${data.duration}</span>
		</p>
		<p>
			<label>备注</label>
			<span>${data.remark}</span>
		</p>
		<p>
			<label>状态</label>
			<span><u:ucparams key="${data.status}" type="task_log_status" /></span>
		</p>
	</div>
</div>
</body>
</html>