<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/common/taglibs.jsp"%>

<html>
<head>
	<title>查看任务</title>
</head>

<body>
<div class="main_ctn">
	<h1>
		<a href="javascript:;" class="back" onclick="history.back();">返 回</a>查看任务
	</h1>
	<div class="admin_material">
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
			<label>连接的数据库</label>
			<span><u:ucparams key="${data.db_type}" type="task_db_type" /></span>
		</p>
		<p>
			<label>存储过程名称</label>
			<span>${data.procedure_name}</span>
		</p>
		<p>
			<label>执行类型</label>
			<span><u:ucparams key="${data.execute_type}" type="task_execute_type" /></span>
		</p>
		<p>
			<label>下次执行时间</label>
			<span>${data.execute_next}</span>
		</p>
		<p>
			<label>执行周期</label>
			<span>${data.execute_period}<u:ucparams key="${data.execute_type}" type="task_execute_type" /></span>
		</p>
		<p>
			<label>扫描类型</label>
			<span><u:ucparams key="${data.scan_type}" type="task_scan_type" /></span>
		</p>
		<p>
			<label>下次扫描时间</label>
			<span>${data.scan_next}</span>
		</p>
		<p>
			<label>扫描周期</label>
			<span>${data.scan_period}<u:ucparams key="${data.scan_type}" type="task_scan_type" /></span>
		</p>
		<p>
			<label>是否每次扫描<br/>都执行</label>
			<span><u:ucparams key="${data.scan_execute}" type="task_scan_execute" /></span>
		</p>
		<p>
			<label>依赖任务</label>
			<span><u:selectMultiple id="dependency" value="${data.dependency}" sqlId="task" excludeValue="${data.task_id}, " showSelectAll="false" disabled="true" /></span>
		</p>
		<p>
			<label>允许执行的时段</label>
			<s:if test="data.allow_start != null">
				<span>${data.allow_start}至${data.allow_end}</span>
			</s:if>
		</p>
		<p>
			<label>分组</label>
			<span>${data.group}</span>
		</p>
		<p>
			<label>组内排序</label>
			<span>${data.order}</span>
		</p>
		<p>
			<label>创建时间</label>
			<span>${data.create_date}</span>
		</p>
		<p>
			<label>更新时间</label>
			<span>${data.update_date}</span>
		</p>
		<p>
			<label>状态</label>
			<span><u:ucparams key="${data.status}" type="task_status" /></span>
		</p>
	</div>
</div>
</body>
</html>