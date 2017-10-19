<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/common/taglibs.jsp"%>

<html>
<head>
	<title>${data.entity.id == null?'添加':'编辑'}版本</title>
</head>
<body>
   <h1><a href="javascript:;" class="back" onclick="back()">返 回</a>版本管理/${data.entity.id == null?'添加':'编辑'}</h1>
   <div class="main_ctn1 channel_detail version_ctn">
          <form method="post" id="mainForm">
		    <ul class="ul_left">
			    <li><label>产品名称</label>
			    	<s:if test="data.entity.id==null">
			    		<u:select id="version_name_key" dictionaryType="version_name" clazz="sel_280"  excludeValue=""/>
			    	</s:if>
			    	<s:if test="data.entity.id!=null">
			    		<input type="hidden" name="version_name_key" value="${data.entity.version_name_key}">
			    		<u:ucparams key="${data.entity.version_name_key}" type="version_name" />
			    	</s:if>
			    </li>
			    <s:if test="data.entity.id==null">
			    	<div class="clear">&nbsp;</div>
			    </s:if>
				<li><label>版本号</label><input type="text" name="version_index" value="${data.entity.version_index}" ${data.entity.id == null?"":" readonly='readonly'"}/></li>
				<li>
					<label>上传文件</label><input class="file_1" type="file" name="version_file" onchange="onUploadFileChange(this)" />
				</li>
				
				<li>
					<label>版本说明</label><textarea rows="60" cols="80" name="version_desc">${data.entity.version_desc}</textarea>
				</li>
			<p><span id="msg" class="error" style="display:none;"></span></p>
			</ul>
			<div class="clear"></div>
            <hr class="hr" />
            <p class="btn">
              <input type="hidden" name="id" value="${data.entity.id}" />
              <input type="button" value="${data.entity.id==null?'添 加':'修 改'}" class="org_btn" onclick="save(this)"/>
              <input type="button" value="取 消" class="grey_btn" onclick="back()"/>
            </p>
          </form>
        </div>

<script type="text/javascript">
var validate;

$(function(){
	//表单验证规则
	validate = $("#mainForm").validate({
		rules: {
			version_name_key: "required",
			version_desc: "required",
			${data.entity.id==null?'version_path_file:"required",':''}
			version_index: {
				required :true,
				version_index :true
			}
		},
		messages: {
			version_name_key: "请输入产品名称",
			version_desc: "请输入版本说明",
			${data.entity.id==null?'version_path_file:"请上传文件",':''}
			version_index: {
				required:"请输入版本号",
				version_index :"请按正确规则填写版本号 x.x.x 如1.1.1"
			}
		}
	});
});
function onUploadFileChange(sender) {
	var v = sender.value;
	$(sender).siblings(".file_txt").val(v);
	if(v==undefined || v==""){
		popupBox("提示",'请选择语音文件！','',2);
		return false;
	}
	return true;
}
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
		url : "${ctx}/version/save",
		type : "post",
		timeout:200000
	};
	$("#mainForm").ajaxSubmit(options);
}
</script>
</body>
</html>