<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/common/taglibs.jsp"%>

<html>
<head>
	<title>添加消息</title>
	<style type="text/css">
		input[type="checkbox"] {opacity:1;}
		.developer_tr {cursor:pointer;}
		.developer_select {min-width:600px;}
	</style>
</head>

<body>
      <div class="main_ctn">
        <h1>添加消息</h1>
        <div class="admin_material admin_form">
          <form method="post" id="mainForm">
            <p>
              <label>选择开发者</label>
              <u:radio name="developer" data="[{value:'1',text:'所有开发者'}, {value:'0',text:'部分开发者'}]" callback="developerRadio" />
            </p>
			<div id="developer_div" style="display:none; margin-left:147px;">
              <input type="button" value="选择部分开发者" onclick="showBox('queryDeveloper', '${ctx}/msg/queryDeveloper')" />
				已选择<span id="developer_num">0</span>个开发者
              <br/>
              <select id="developer_select" name="sid" multiple="multiple" size="5" class="developer_select"></select>
            </div>
            
            <div class="select_box">
              <label>消息类型</label>
              <u:select id="msg_type" placeholder="消息类型" dictionaryType="msg_type" />
            </div>
            <div class="clear"></div>
            <p>
              <label>标题</label>
              <textarea name="msg_title" rows="3" cols="100" maxlength="100" style="height:auto;width:auto;">${data.msg_title}</textarea>
            </p>
            <p>
              <label style="margin-bottom:20px;">内容</label>
				<u:ueditor id="msg_desc" value="${data.msg_desc}"/>
            </p>
			<span id="msg" class="error" style="display:none;"></span>
            <hr class="hr" />
            <p class="btn">
              <input type="button" value="添 加" class="org_btn" onclick="save(this)"/>
              <input type="button" value="取 消" class="grey_btn" onclick="back()"/>
            </p>
          </form>
        </div>
      </div>

<script type="text/javascript">
var validate;
$(function(){
	//表单验证规则
	validate = $("#mainForm").validate({
		rules: {
			developer: "required",
			sid: "required",
			msg_type: "required",
			msg_title: "required",
			msg_desc: "required"
		},
		messages: {
			developer: "请选择开发者",
			sid: "请选择部分开发者",
			msg_type: "请选择消息类型",
			msg_title: "请输入标题",
			msg_desc: "请输入内容"
			
		}
	});
});

function save(btn){
	$("#developer_select option").attr("selected", true);
	$("#msg").hide();
	var flag=true;
	if(!validate.form()){
		flag=false;
	}
	var msg_desc = $("#ueditor_textarea_msg_desc");
	if(msg_desc.length<1 || $.trim(msg_desc.val())=="") {
		$("#ueditor_textarea_msg_desc-error").text("请输入内容").show();
		flag=false;
	}
	if(!flag){
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
		url : "${ctx}/msg/save",
		type : "post",
		timeout:30000
	};
	$("#mainForm").ajaxSubmit(options);
}

//选择开发者
function developerRadio(value, text){
	if(value==1){
		$("#developer_div").hide();
		$("#developer_select").attr("disabled", true);
	}else{
		$("#developer_div").show();
		$("#developer_select").attr("disabled", false);
	}
}

</script>
</body>
</html>