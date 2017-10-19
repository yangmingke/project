<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/common/taglibs.jsp"%>

<s:set var="title" value="#parameters.news_id==null ? '添加' : '修改'"/>
<s:set var="title_btn" value="#parameters.news_id==null ? '添 加' : '修 改'"/>

<html>
<head>
	<title>${title}新闻</title>
</head>

<body>
      <div class="main_ctn">
        <h1>${title}新闻</h1>
        <div class="admin_material admin_form">
          <form method="post" id="mainForm">
          	<s:if test="data.news_id != null">
	            <p>
	              <label>新闻id</label>
	              ${data.news_id}
	              <input type="hidden" name="news_id" value="${data.news_id}" />
	            </p>
          	</s:if>
            <p>
              <label>标题</label>
              <textarea name="title" rows="3" cols="100" maxlength="100" style="height:auto;width:auto;">${data.title}</textarea>
            </p>
            <p>
              <label>副标题</label>
              <textarea name="subtitle" rows="3" cols="100" maxlength="100" style="height:auto;width:auto;">${data.subtitle}</textarea>
            </p>
            <p>
              <label>摘要</label>
              <textarea name="summary" rows="3" cols="100" maxlength="100" style="height:auto;width:auto;">${data.summary}</textarea>
            </p>
            <p>
              <label style="margin-bottom:20px;">内容</label>
				<u:ueditor id="content" value="${data.content}"/>
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
var validate;
$(function(){
	//表单验证规则
	validate = $("#mainForm").validate({
		rules: {
			title: "required",
			summary: "required",
			content: "required"
		},
		messages: {
			title: "请输入标题",
			summary: "请输入摘要",
			content: "请输入内容"
		}
	});
});

function save(btn){
	$("#msg").hide();
	var flag=true;
	if(!validate.form()){
		flag=false;
	}
	var content = $("#ueditor_textarea_content");
	if(content.length<1 || $.trim(content.val())=="") {
		$("#ueditor_textarea_content-error").text("请输入内容").show();
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
		url : "${ctx}/news/save",
		type : "post",
		timeout:30000
	};
	$("#mainForm").ajaxSubmit(options);
}
</script>
</body>
</html>