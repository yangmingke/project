<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/common/taglibs.jsp"%>

<s:set var="title" value="#parameters.task_id==null ? '添加' : '修改'"/>
<s:set var="title_btn" value="#parameters.task_id==null ? '添 加' : '修 改'"/>

<html>
<head>
	<title>${title}任务</title>
</head>

<body>
      <div class="main_ctn">
        <h1>${title}任务</h1>
        <div class="admin_material admin_form">
          <form method="post" id="mainForm">
          	<s:if test="data.task_id!=null">
	            <p>
	              <label>任务id</label>
	              ${data.task_id}
	              <input type="hidden" name="task_id" value="${data.task_id}" />
	            </p>
          	</s:if>
            
            <p>
              <label>任务名称</label>
              <input type="text" name="task_name" value="${data.task_name}" maxlength="100" />
            </p>
            <div class="select_box">
              <label>任务类型</label>
              <u:select id="task_type" value="${data.task_type}" defaultIndex="1"  dictionaryType="task_type" excludeValue="" callback="change_task_type" />
            </div>
            <div class="clear"></div>
            <div class="select_box">
              <label>连接的数据库</label>
              <u:select id="db_type" value="${data.db_type}" defaultIndex="1" dictionaryType="task_db_type" excludeValue="" />
             </div>
            <div class="clear"></div>
            <p>
              <label>存储过程名称</label>
              <input type="text" id="procedure_name" name="procedure_name" value="${data.procedure_name}" maxlength="200" />
              <span>两个参数：第一个为入参（统计时间），第二个为出参（结果，1表示成功）</span>
            </p>
            <div class="select_box">
              <label>执行类型</label>
              <u:select id="execute_type" value="${data.execute_type}" defaultIndex="1" dictionaryType="task_execute_type" excludeValue="" callback="change_execute_type" />
             </div>
            <div class="clear"></div>
            <p>
              <label>下次执行时间</label>
              <u:date id="execute_next" value="${data.execute_next}" params="dateFmt: execute_next_fmt" />
            </p>
            <p>
              <label>执行周期</label>
              <input type="text" id="execute_period" name="execute_period" value="${data.execute_period}" class="txt_177" onfocus="inputControl.setNumber(this, 9, 0, false)" />
              <span id="span_execute_period"></span>
            </p>
            <div class="select_box">
              <label>扫描类型</label>
              <u:select id="scan_type" value="${data.scan_type}" defaultIndex="1" dictionaryType="task_scan_type" excludeValue="" callback="change_scan_type" />
             </div>
            <div class="clear"></div>
            <p>
              <label>下次扫描时间</label>
              <u:date id="scan_next" value="${data.scan_next}" dateFmt="yyyy-MM-dd HH:mm:00" />
            </p>
            <p>
              <label>扫描周期</label>
              <input type="text" name="scan_period" value="${data.scan_period}" class="txt_177" onfocus="inputControl.setNumber(this, 9, 0, false)" />
              <span id="span_scan_period"></span>
            </p>
            <div class="select_box">
              <label>是否每次扫描<br/>都执行</label>
              <u:select id="scan_execute" value="${data.scan_execute}" defaultIndex="1" dictionaryType="task_scan_execute" excludeValue="" />
             </div>
            <div class="clear"></div>
            <div class="select_box">
              <label>依赖任务</label>
				<u:selectMultiple id="dependency" value="${data.dependency}" sqlId="task" excludeValue="${data.task_id}, " />
             </div>
            <div class="clear"></div>
            <p>
              <label>允许执行的时段</label>
				<u:date id="allow_start" value="${data.allow_start}" placeholder="开始时间" maxId="allow_end" dateFmt="HH:mm:00" startDate="00:00:00" />
				<span>至</span>
				<u:date id="allow_end" value="${data.allow_end}" placeholder="结束时间" minId="allow_start" dateFmt="HH:mm:00" />
				<label for="allow_start" class="error" style="display: none;"></label>
				<label for="allow_end" class="error" style="display: none;"></label>
            </p>
            <p>
              <label>分组</label>
              <input type="text" name="group" value="${data.group}" class="txt_177" onfocus="inputControl.setNumber(this, 9, 0, false)" />
            </p>
            <p>
              <label>组内排序</label>
              <input type="text" name="order" value="${data.order}" class="txt_177" onfocus="inputControl.setNumber(this, 9, 0, false)" />
            </p>
            
			<span id="msg" class="error" style="display:none;"></span>
            <hr class="hr" />
            <p class="btn">
              <input type="button" value="${title_btn}" class="org_btn" onclick="save(this)"/>
              <input type="button" value="取 消" class="grey_btn" onclick="back()"/>
            </p>
          </form>
        </div>
      </div>

<script type="text/javascript">
var execute_type = "${data.execute_type}";
var execute_next_fmt = "yyyyMMddHHmm";
var validate;
$(function(){
	//表单验证规则
	validate = $("#mainForm").validate({
		rules: {
			task_name: "required",
			task_type: "required",
			db_type: "required",
			procedure_name: "required",
			execute_next: "required",
			execute_period: {
				required:true,
				min:1
			},
			scan_type: "required",
			scan_next: "required",
			scan_period: {
				required:true,
				min:1
			},
			scan_execute: "required",
			allow_start: {
				bindRequired: "allow_end"
			},
			allow_end: {
				bindRequired: "allow_start"
			},
			group: "required",
			order: "required"
		},
		messages: {
			task_name: "请输入任务名称",
			task_type: "请输入任务类型",
			db_type: "请输入连接的数据库",
			procedure_name: "请输入存储过程名称",
			execute_next: "请输入下次执行时间",
			execute_period: {
				required:"请输入执行周期",
				min:"执行周期必须大于0"
			},
			scan_type: "请输入扫描类型",
			scan_next: "请输入下次扫描时间",
			scan_period: {
				required:"请输入扫描周期",
				min:"扫描周期必须大于0"
			},
			scan_execute: "请输入是否每次扫描都执行",
			allow_start: "请输入允许执行的开始时间",
			allow_end: "请输入允许执行的结束时间",
			group: "请输入分组",
			order: "请输入组内排序"
		}
	});
});

//任务类型
function change_task_type(value, text){
	if(value==1){
		$("#procedure_name").attr("disabled", false);
	}else{
		$("#procedure_name").attr("disabled", true).val("");
		$("#procedure_name-error").hide();
	}
}

//执行类型
function change_execute_type(value, text){
	if(value=="0"){
		$("#execute_next, #execute_period").attr("disabled", true).val("");
		$("#execute_next-error, #execute_period-error").hide();
		$("#span_execute_period").empty();
		return;
	}else{
		$("#execute_next, #execute_period").attr("disabled", false);
		$("#span_execute_period").text(text);
	}
	
	var dateFmt;
	switch(parseInt(value)){
		case 1 : dateFmt="yyyyMMddHHmm"; break;
		case 2: dateFmt="yyyyMMddHH"; break;
		case 3: 
		case 4: dateFmt="yyyyMMdd"; break;
		case 5: 
		case 6: dateFmt="yyyyMM"; break;
		case 7: dateFmt="yyyy"; break;
	}
	
	if(execute_type != value){
		$("#execute_next").val("");
	}
	execute_type = value;
	execute_next_fmt = dateFmt;
}

//扫描类型
function change_scan_type(value, text){
	$("#span_scan_period").text(text);
}

function save(btn){
	$("#msg").hide();
	if(!validate.form()){
		return;
	}
	
	var options = {
		beforeSubmit : function() {
			$(btn).attr("disabled", true);
		},
		success : function(data) {
			$(btn).attr("disabled", false);
			
			if(data.result==null){
				$("#msg").text("服务器错误，请联系管理员").show();
				return;
			}
			
			$("#msg").text(data.msg).show();
		},
		url : "${ctx}/task/save",
		type : "post",
		timeout:30000
	};
	$("#mainForm").ajaxSubmit(options);
}
</script>
</body>
</html>