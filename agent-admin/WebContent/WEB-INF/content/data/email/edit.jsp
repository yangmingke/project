<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/common/taglibs.jsp"%>

<html>
<head>
	<title>发送邮件</title>
	<style type="text/css">
		input[type="checkbox"] {opacity:1;}
		.developer_tr {cursor:pointer;}
		.developer_select {min-width:600px;}
	</style>
</head>

<body>
      <div class="main_ctn">
        <h1>发送邮件</h1>
        <div class="admin_material admin_form">
          <form method="post" id="mainForm">
            <p>
              <label>发送类型</label>
              <u:radio name="type" data="[{value:'1',text:'发送新一轮邮件'}, {value:'0',text:'发送上一轮未完成的邮件'}]" />
            </p>
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
            
            <p>
              <label>邮件标题</label>
              <input type="text" name="subject" value="${data.subject}" maxlength="200" />
            </p>
            <p>
              <label>邮件内容</label>
              <textarea name="text" rows="10" cols="100" maxlength="10000" style="height:auto;width:auto;"><s:property value="data.text"/></textarea>
              <label></label>[@username@]表示昵称
            </p>
            
			<span id="msg" class="error" style="display:none;"></span>
            <hr class="hr" />
            <p class="btn">
              <input type="button" value="发 送" class="org_btn" onclick="save(this)"/>
              <input type="button" value="返 回" class="grey_btn" onclick="back()"/>
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
			type: "required",
			developer: "required",
			sid: "required",
			subject: "required",
			text: "required"
		},
		messages: {
			type: "请选择发送类型",
			developer: "请选择开发者",
			sid: "请选择部分开发者",
			subject: "请输入邮件标题",
			text: "请输入邮件内容"
		}
	});
});

function save(btn){
	$("#developer_select option").attr("selected", true);
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
		url : "${ctx}/email/save",
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