<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/common/taglibs.jsp"%>

<s:set var="title" value="#parameters.word_id==null ? '添加' : '修改'"/>
<s:set var="title_btn" value="#parameters.word_id==null ? '添 加' : '修 改'"/>

<html>
<head>
	<title>${title}敏感字</title>
</head>

<body>
      <div class="main_ctn">
        <h1>${title}敏感字</h1>
        <div class="admin_material admin_form">
          <form method="post" id="mainForm">
          	<s:if test="data.word_id != null">
	            <p>
	              <label>敏感字id</label>
	              ${data.word_id}
	              <input type="hidden" name="word_id" value="${data.word_id}" />
	            </p>
          	</s:if>
            <p>
              <label>敏感字</label>
              <input type="text" id="word" name="word" value="${data.word}" maxlength="100" />
				统一保存为小写
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
			word: "required"
		},
		messages: {
			word: "请输入敏感字"
		}
	});
});

function save(btn){
	$("#msg").hide();
	if(!validate.form()){
		return;
	}
	
	var word = $("#word");
	word.val(word.val().toLowerCase());
	
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
		url : "${ctx}/keyword/save",
		type : "post",
		timeout:30000
	};
	$("#mainForm").ajaxSubmit(options);
}
</script>
</body>
</html>