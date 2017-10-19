<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/common/taglibs.jsp"%>

<s:set var="title" value="#parameters.id==null ? '添加' : '修改'"/>

<html>
<head>
	<title>${title}配置</title>
</head>

<body>
      <div class="main_ctn">
        <h1>${title}配置</h1>
        <div class="admin_material admin_form">
          <form method="post" id="mainForm">
             <p>
              <label>名称</label>
              <input type="text" name="page_name" value="${data.entity.page_name}" maxlength="100" />
            </p>
           
            <p>
              <label>Key</label>
              <input type="text" name="page_key" value="${data.entity.page_key}" maxlength="100" />
            </p>
           
            <p>
              <label>内容</label>
              <textarea rows="10" cols="22" name="content">${data.entity.content}</textarea>
            </p>
            
			<span id="msg" class="error" style="display:none;"></span>
            <hr class="hr" />
            <p class="btn">
            	<input type="hidden" name="id" value="${data.entity.id}" />
              <input type="button" value="保存" class="org_btn" onclick="save(this)"/>
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
			page_name: "required",
			page_key: "required",
			content: "required"
		},
		messages: {
			page_name: "请输入名称",
			page_key: "请输入Key",
			content: "请输入内容"
		}
	});
});

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
		url : "${ctx}/pageConfig/save",
		type : "post",
		timeout:30000
	};
	$("#mainForm").ajaxSubmit(options);
}
</script>
</body>
</html>