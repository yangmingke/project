<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/common/taglibs.jsp"%>

<html>
<head>
	<title>${data.sid==null?'添加':'修改'}管理员</title>
</head>

<body>
      <div class="main_ctn">
        <h1>${data.sid==null?'添加':'修改'}管理员</h1>
        <div class="admin_material admin_form">
          <form method="post" id="mainForm">
            <div class="select_box">
              <label>管理员身份</label>
              <u:select id="role_id" value="${data.role_id}" sqlId="role" excludeValue=""/>
            </div>
            <div class="clear"></div>
            <p>
              <label>管理员账号</label>
              <input type="text" name="email" value="${data.email}" maxlength="100"/>
            </p>
            <p>
              <label>真实姓名</label>
              <input type="text" name="realname" value="${data.realname}" maxlength="20"/>
            </p>
            <p class="phone">
              <label>联系手机</label>
              <input type="text" name="mobile" value="${data.mobile}" onfocus="inputControl.setNumber(this, 11, 0, false)"/>
              <span class="tips">用于接收安全、业务监控报警、重要变更等通知</span>
			</p>
            <p>
              <label>登录密码</label>
              <input type="password" name="password" maxlength="20"/>
              <s:if test="data.sid!=null">
	              <span class="tips">为空则不修改</span>
              </s:if>
            </p>
			<p><span id="msg" class="error" style="display:none;"></span></p>
            <hr class="hr" />
            <p class="btn">
              <input type="hidden" name="old_role_id" value="${data.role_id}" />
              <input type="hidden" name="sid" value="${data.sid}" />
              <input type="button" value="${data.sid==null?'添 加':'修 改'}" class="org_btn" onclick="save(this)"/>
              <input type="button" value="取 消" class="grey_btn" onclick="back()"/>
            </p>
          </form>
        </div>
      </div>

<script type="text/javascript">
var validate;

$(function(){
	var pwd = ${data.sid==null?'true':'false'}; //修改时密码不是必填
	//表单验证规则
	validate = $("#mainForm").validate({
		rules: {
			role_id: "required",
			email: {
				required: true,
				email2: true
			},
			realname: "required",
			mobile: {
				required: true,
				mobile: true
			},
			password: {
				required: pwd,
				rangelength:[6,20]
			}
		},
		messages: {
			role_id: "请输入管理员身份",
			email: {
				required:"请输入管理员账号"
			},
			realname: "请输入真实姓名",
			mobile: {
				required: "请输入联系手机",
				mobile: "请输入有效的联系手机"
			},
			password: {
				required:"请输入登录密码",
				rangelength:"密码长度为{0}~{1}位"
			}
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
		url : "${ctx}/admin/save",
		type : "post",
		timeout:30000
	};
	$("#mainForm").ajaxSubmit(options);
}
</script>
</body>
</html>