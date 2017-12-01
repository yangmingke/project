<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/common/taglibs.jsp"%>

<html>
<head>
	<title>client创建</title>
</head>

<body>
      <div class="main_ctn">
        <h1>client创建</h1>
        <div class="admin_material admin_form">
          <form method="post" id="mainForm">
          	<p>
              <label>开发者ID</label>
              <span>${data.sid}</span>
              <input name="sid" id="sid" value="${data.sid}"  hidden="hidden"/>
              <input name="token" id="token" value="${data.token}"  hidden="hidden"/>
            </p>
            <p>
              <label>开发者名称</label>
              <span>${data.username}</span>
            </p>
            <p>
              <label>应用ID</label>
              <span>${data.app_sid}</span>
              <input name="app_sid" id="app_sid" value="${data.app_sid}"  hidden="hidden"/>
            </p>
            <p>
              <label>应用名称</label>
              <span>${data.app_name}</span>
            </p>
            <p>
              <label>创建client个数</label>
              <input type="text" id="count" placeholder="1-100" maxlength="3" onkeyup="this.value=this.value.replace(/[^0-9]/g,'');"/>个<span id="clientCountMsg" class="error" style="display:none;"/>
              <input type="text" name="clientCount" id="clientCount" style="display:none;"/>
            </p>
            <hr class="hr" />
            <p class="btn">
              <input type="button" value="创建" class="org_btn" onclick="create(this)"/>
              <input type="button" value="取 消" class="grey_btn" onclick="back()"/>
            </p>
          </form>
        </div>
      </div>

<script type="text/javascript">

function create(btn){
		var count = $('#count').val();
		var app_sid = $('#app_sid').val();
		var sid = $('#sid').val();
		var token = $('#token').val();
		$(".error").hide();
		if(count == ""){
			$("#clientCountMsg").text("请输入创建client个数").show();
			return false;
		}else{
			var clientCount = Number(count);
			if((clientCount < 1) || (clientCount > 100)){
				$("#clientCountMsg").text("client个数必须为1-100").show();
				return false;
			};
			$('#clientCount').val(clientCount);
				
		}
		if(app_sid == "" || sid == "" || token == ""){
			$("#msg").text("系统发生错误，请联系管理员").show();
			return false;
		}
		var options = {
			beforeSubmit : function() {
				$(btn).val("创建中……");
				$('input').attr("disabled", true);
			},
			success : function(data) {
				$('input').attr("disabled", false);
				$(btn).val("创建");
				if (data.result == null) {
					$("#appNameMsg").text("服务器错误，请联系管理员").show();
					return;
				}
				if(data.result == 'fail'){
					$("#appNameMsg").text(data.msg).show();
				}else{
					alert("创建成功");
					location.href="${ctx}/client/query";
				}
			},
			url : "${ctx}/client/create",
			type : "post",
			timeout : 30000
		};
		$("#mainForm").ajaxSubmit(options);
	}
</script>
</body>
</html>