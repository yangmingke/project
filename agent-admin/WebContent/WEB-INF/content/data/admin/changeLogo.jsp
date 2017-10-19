<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/common/taglibs.jsp"%>

<html>
<head>
	<title>管理员资料 - 修改logo</title>
	<script type="text/javascript" src="${ctx}/js/hex_ha_ha_ha_ha_ha.js"></script>
</head>

<body>
      <div class="main_ctn">
        <h1>管理员资料 - 修改logo</h1>
        <div class="admin_material material_form" style="text-align: center;">
          <form method="post" id="mainForm">
            <p>
              <label></label>
              <input  class="file_1" type="file"   onchange="onUploadFileChange(this)"  id="logoImg" name="logoImg"/>
            </p>
			<p><span id="msg" class="error" style="display:none;"></span></p>
            <hr class="hr" /> 
            <p class="btn">
				<input type="button" value="修 改" class="org_btn" onclick="save(this)"/>
				<input type="button" value="取 消" class="grey_btn" onclick="back()"/>
            </p>
          </form>
        </div>
      </div>

<script type="text/javascript">
function onUploadFileChange(sender) {
	var v = sender.value;
	$(sender).siblings(".file_txt").val(v);
	if(v==undefined || v==""){
		popupBox("提示",'请选择图片文件！','',2);
		return false;
	}
	return true;
}

//保存
function save(btn){
	$("label.error, span.error").hide();
	var logoImg = $("#logoImg").val();
	
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
			if(data.result=="success"){
				$("#old_mobile").val($("#mobile").val());
				$("#verify_code, #encrypt_verify_code").val("");
			}
			
			$("#msg").text(data.msg).show();
		},
		url : "${ctx}/admin/uploadLogo",
		type : "post",
		timeout:30000
	};
	$("#mainForm").ajaxSubmit(options);
}

</script>
</body>
</html>