<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/common/taglibs.jsp"%>

<s:set var="title" value="#parameters.id==null ? '添加' : '修改'"/>
<s:set var="title_btn" value="#parameters.id==null ? '添 加' : '修 改'"/>

<html>
<head>
	<title>${title}业务配置</title>
</head>

<body>
      <div class="main_ctn">
        <h1>${title}业务配置</h1>
        <div class="admin_material admin_form">
          <form method="post" id="mainForm">
	        <input type="hidden" name="id" value="${data.id}" />
	        <input type="hidden" name="status" value="1" />
            <p>
              <label>开发者ID/账号</label>
              <input type="text" name="sid" placeholder="开发者ID/账号" value="${data.sid}"/>
            </p>
            <div class="select_box"><label>类型</label>
              <u:select id="stype" placeholder='类型' dictionaryType="tb_user_dsp_stype" defaultIndex="1" excludeValue="" clazz="sel_221"/>
            </div>
            <div class="clear"></div>
             <p>
              <label>金额</label>
              <input type="text" name="money" id="money" placeholder="金额" onfocus="inputControl.setNumber(this, 10, 3, false)"  value="${empty data.money?0:data.money}"/>
            </p>
             <p>
              <label>其它</label>
              <textarea rows="10" cols="50"  name="ext" placeholder="其它参数" >${data.ext}</textarea>
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
			sid: "required",
			stype: "required"
		},
		messages: {
			title: "请输入开发者sid",
			stype: "请选择类型"
		}
	});
});

function save(btn){
	$("#msg").hide();
	if(!validate.form()){
		return false;
	}
	var money = $("#money").val();
	var stype = $("#stype").val();
	if("2" == stype && money < 1 ){
		$("#msg").text("请配置金额!").show();
		return false;
	}
	if("1" == stype){
		 $("#money").val(0);
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
		url : "${ctx}/businessConfig/save",
		type : "post",
		timeout:30000
	};
	$("#mainForm").ajaxSubmit(options);
}
</script>
</body>
</html>