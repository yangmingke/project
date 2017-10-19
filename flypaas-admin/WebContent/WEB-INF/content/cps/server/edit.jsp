<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/common/taglibs.jsp"%>

<s:set var="title" value="#parameters.id==null ? '添加' : '修改'"/>
<s:set var="title_btn" value="#parameters.id==null ? '添 加' : '修 改'"/>

<html>
<head>
	<title>${title}服务器地址</title>
</head>

<body>
      <div class="main_ctn">
        <h1>${title}服务器地址</h1>
        <div class="admin_material admin_form">
          <form method="post" id="mainForm">
          	<s:if test="data == null"><%--添加 --%>
	            <div class="select_box">
	              <label>服务器类型</label>
	              <u:select id="server" dictionaryType="cps_server" showAll="false" />
	            </div>
	            <div class="clear"></div>
          	</s:if>
          	<s:else><%--修改 --%>
	            <p>
	              <label>服务器类型</label>
	              <u:ucparams type="cps_server" key="${param.server}" />
	              <input type="hidden" name="server" value="${param.server}" />
	            </p>
	            <p>
	              <label>id</label>
	              ${data.id}
	              <input type="hidden" name="id" value="${data.id}" />
	            </p>
          	</s:else>
            <p>
              <label>ip地址</label>
              <input type="text" name="ipaddress" value="${data.ipaddress}" maxlength="50" />
              	服务器地址
            </p>
            <p>
              <label>端口号</label>
              <input type="text" name="port" value="${data.port}" onfocus="inputControl.setNumber(this, 8, 0, false)" />
              	服务器开放端口
            </p>
            <p>
              <label>描述信息</label>
              <textarea name="describe" maxlength="200">${data.describe}</textarea>
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
			server: "required",
			ipaddress: {
				required: true,
				ipv4: true
			},
			port: "required",
			describe: "required"
		},
		messages: {
			server: "请选择服务器类型",
			ipaddress: {
				required: "请输入ip地址",
				ipv4: "请输入有效的ip地址"
			},
			port: "请输入端口号",
			describe: "请输入描述信息"
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
		url : "${ctx}/server/save",
		type : "post",
		timeout:30000
	};
	$("#mainForm").ajaxSubmit(options);
}
</script>
</body>
</html>