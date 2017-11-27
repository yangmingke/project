<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/common/taglibs.jsp"%>

<html>
<head>
	<title>创建应用</title>
	<style type="text/css">
		select.developer_select{
			width:358px;
			height: 46px;
			margin: 2px 0px;
		}
    </style>
</head>

<body>
      <div class="main_ctn">
        <h1>创建应用</h1>
        <div class="admin_material admin_form">
          <form method="post" id="mainForm">
            <p>
              <label>开发者名称</label>
              <span>${data.username}</span>
              <span id="msg" class="error" style="display:none;"/>
              <input name="sid" id="sid" value="${data.sid}"  hidden="hidden"/>
            </p>
            <p>
              <label>应用名称</label>
              <input type="text" name="appName" id="appName" maxlength="50"/><span id="appNameMsg" class="error" style="display:none;"/>
            </p>
            <p>
              <label>应用类型</label>
              <select class="developer_select" id="appKind" name="appKind">
	              <s:iterator value="data.appKindList">
	              	<option value="${paramKey}">${paramValue}</option>
	              </s:iterator>
              </select>
            </p>
            <p>
              <label>所属行业</label>
              <select class="developer_select" id="industry" name="industry">
	              <s:iterator value="data.industryList">
	              	<option value="${paramKey}">${paramValue}</option>
	              </s:iterator>
              </select>
			</p>
            <p>
              <label style="float: left;padding-top:40px;">服务器白名单</label>
              <textarea id="whiteListStr" name="whiteListStr" placeholder="允许域名或者IP地址，以英文输入法分号分隔，例如：www.google.com; 8.8.8.8 设定白名单地址后，快传服务器在识别该应用请求时将只接收白名单内服务器发送的请求，能有效提升帐号安全性。 如未设置默认不生效"></textarea>
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
		var appName = $('#appName').val();
		var sid = $('#sid').val();
		$(".error").hide();
		if(appName == ""){
			$("#appNameMsg").text("请输应用名称").show();
			return false;
		}
		if(sid == ""){
			$("#msg").text("系统发生错误，请联系管理员").show();
			return false;
		}
		var options = {
			beforeSubmit : function() {
				$(btn).attr("disabled", true);
			},
			success : function(data) {
				$(btn).attr("disabled", false);

				if (data.result == null) {
					$("#msg").text("服务器错误，请联系管理员").show();
					return;
				}
				if(data.result == 'fail'){
					$("#appNameMsg").text(data.msg).show();
				}else{
					alert("创建成功");
					location.href="${ctx}/app/query";
				}
			},
			url : "${ctx}/app/createApp",
			type : "post",
			timeout : 30000
		};
		$("#mainForm").ajaxSubmit(options);
	}
</script>
</body>
</html>