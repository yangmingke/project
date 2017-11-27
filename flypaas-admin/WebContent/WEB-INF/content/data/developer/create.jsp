<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/common/taglibs.jsp"%>

<html>
<head>
	<title>创建开发者</title>
</head>

<body>
      <div class="main_ctn">
        <h1>创建开发者</h1>
        <div class="admin_material admin_form">
          <form method="post" id="mainForm">
            <div class="select_box">
              <label>开发者邮箱</label>
              <input type="text" name="email" id="email" maxlength="100"/><span id="emailMsg" class="error" style="display:none;"/>
            </div>
            <div class="clear"></div>
            <p>
              <label>开发者昵称</label>
              <input type="text" name="userName" maxlength="20" id="userName" placeholder="最多20个字符"/><span id="userNameMsg" class="error" style="display:none;"/>
            </p>
            <p class="phone">
              <label>联系手机</label>
              <input type="text" name="mobile" id="mobile" onfocus="inputControl.setNumber(this, 11, 0, false)"/><span id="mobileMsg" class="error" style="display:none;"/>
			</p>
            <p>
              <label>登录密码</label>
              <span>默认为“kc123456”</span>
            </p>
            <hr class="hr" />
            <p class="btn">
              <input type="button" value="创建" class="org_btn" onclick="save(this)"/>
              <input type="button" value="取 消" class="grey_btn" onclick="back()"/>
            </p>
          </form>
        </div>
      </div>

<script type="text/javascript">

function save(btn){
		var email = $('#email').val();
		var mobile = $('#mobile').val();
		var userName = $('#userName').val();
		$(".error").hide();
		if (!/^((([a-z]|\d|[!#\$%&'\*\+\-\/=\?\^_`{\|}~]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])+(\.([a-z]|\d|[!#\$%&'\*\+\-\/=\?\^_`{\|}~]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])+)*)|((\x22)((((\x20|\x09)*(\x0d\x0a))?(\x20|\x09)+)?(([\x01-\x08\x0b\x0c\x0e-\x1f\x7f]|\x21|[\x23-\x5b]|[\x5d-\x7e]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(\\([\x01-\x09\x0b\x0c\x0d-\x7f]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF]))))*(((\x20|\x09)*(\x0d\x0a))?(\x20|\x09)+)?(\x22)))@((([a-z]|\d|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(([a-z]|\d|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])([a-z]|\d|-|\.|_|~|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])*([a-z]|\d|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])))\.)+(([a-z]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(([a-z]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])([a-z]|\d|-|\.|_|~|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])*([a-z]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])))$/i.test(email)){
			$("#emailMsg").text("请输入正确的邮箱").show();
			return false;
		}
		if(userName == ""){
			$("#userNameMsg").text("请输开发者昵称").show();
			return false;
		}
		if(!/^1[0-9]{10}$/.test(mobile)){
			$("#mobileMsg").text("请输入正确的手机号码").show();
			return false;
		}
		var options = {
			beforeSubmit : function() {
				$(btn).attr("disabled", true);
			},
			success : function(data) {
				$(btn).attr("disabled", false);

				if (data.result == null) {
					$("#emailMsg").text("服务器错误，请联系管理员").show();
					return;
				}
				if(data.result == 'fail'){
					$("#emailMsg").text(data.msg).show();
				}else{
					alert("创建成功");
					location.href="${ctx}/developer/query";
				}
			},
			url : "${ctx}/developer/create",
			type : "post",
			timeout : 30000
		};
		$("#mainForm").ajaxSubmit(options);
	}
</script>
</body>
</html>