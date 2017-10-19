<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/common/taglibs.jsp"%>

<html>
<head>
	<title>${data.entity.id == null?'添加':'编辑'}渠道</title>
</head>

<body>
   <h1><a href="javascript:;" class="back" onclick="back()">返 回</a>渠道管理/${data.entity.id == null?'添加':'编辑'}</h1>
   <div class="main_ctn1 channel_detail">
          <form method="post" id="mainForm">
		    <ul class="ul_left">
			    <li><label>渠道ID</label><span>${empty data.entity.id?'&nbsp;':data.entity.id}</span></li>
				<li><label>渠道名称</label><input type="text" name="channel_name" value="${data.entity.channel_name}" maxlength="20"/></li>
				<li style="padding-bottom:40px;"><label>渠道类型</label><u:select id="channel_type" value="${empty data.entity.channel_type?'1':data.entity.channel_type}" dictionaryType="channel_type" excludeValue=""/></li>
				<li style="padding-bottom:40px;"><label>渠道模式</label><u:select id="channel_mode" value="${empty data.entity.channel_mode?'1':data.entity.channel_mode}" dictionaryType="channel_mode" excludeValue=""/></li>
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
			channel_name: {
				required: true,
				maxlength: 32
			}
		},
		messages: {
			channel_name: {
				required: "请输入渠道名称",
				maxlength: "请输入有效渠道名称"
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
		url : "${ctx}/channel/save",
		type : "post",
		timeout:30000
	};
	$("#mainForm").ajaxSubmit(options);
}
</script>
</body>
</html>