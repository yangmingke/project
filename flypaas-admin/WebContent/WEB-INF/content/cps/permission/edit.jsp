<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/common/taglibs.jsp"%>

<s:set var="title" value="#parameters.id==null ? '添加' : '修改'"/>
<s:set var="title_btn" value="#parameters.id==null ? '添 加' : '修 改'"/>

<html>
<head>
	<title>${title}策略权限</title>
</head>

<body>
      <div class="main_ctn">
        <h1>${title}策略权限</h1>
        <div class="admin_material admin_form">
          <form method="post" id="mainForm">
          	<s:if test="data.id != null">
	            <p>
	              <label>id</label>
	              ${data.id}
	              <input type="hidden" name="id" value="${data.id}" />
	            </p>
          	</s:if>
            <div class="select_box">
              <label>手机平台</label>
              <u:select id="pv" value="${data.pv}" dictionaryType="cps_pv" showAll="false" />
              0：android，1：iphone，2：pc
            </div>
            <div class="clear"></div>
            <p>
              <label>客户端SDK版本</label>
              <input type="text" name="ver" value="${data.ver==null ? 'ALL' : data.ver}" maxlength="50"/>
              V1.1.2，默认值：ALL
            </p>
            <p>
              <label>P2P探测使能</label>
              <u:radio name="iceenable" value="${data.iceenable==null ? 0 : data.iceenable}" dictionaryType="cps_enable" />
            </p>
            <p>
              <label>音频FEC使能</label>
              <u:radio name="audiofec" value="${data.audiofec==null ? 0 : data.audiofec}" dictionaryType="cps_enable" />
            </p>
            <p>
              <label>日志上报使能</label>
              <u:radio name="logreport" value="${data.logreport==null ? 0 : data.logreport}" dictionaryType="cps_enable" />
            </p>
            <p>
              <label>驱动自动适配</label>
              <u:radio name="autoadapter" value="${data.autoadapter==null ? 0 : data.autoadapter}" dictionaryType="cps_enable" />
            </p>
            <p>
              <label>语音质量监控使能</label>
              <u:radio name="vqmenable" value="${data.vqmenable==null ? 0 : data.vqmenable}" dictionaryType="cps_enable" />
            </p>
            <p>
              <label>Rtp压缩使能</label>
              <u:radio name="prtpenable" value="${data.prtpenable==null ? 0 : data.prtpenable}" dictionaryType="cps_enable" />
            </p>
            <p>
              <label>Vp8使能</label>
              <u:radio name="vp8enable" value="${data.vp8enable==null ? 0 : data.vp8enable}" dictionaryType="cps_enable" />
            </p>
            <p>
              <label>H264使能</label>
              <u:radio name="h264enable" value="${data.h264enable==null ? 0 : data.h264enable}" dictionaryType="cps_enable" />
            </p>
            <div class="select_box">
              <label>网络类型</label>
              <u:select id="nettype" value="${data.nettype}" dictionaryType="cps_nettype" showAll="false" callback="change_nettype" />
              0：2G，1：WiFi ，2：3G，3：4G，4：Cabel(宽带，有线网络)
            </div>
            <div class="clear"></div>
            <p>
              <label>起始码率</label>
              <span id="span_startbitrate">${data.startbitrate}</span>
	          <input type="hidden" id="startbitrate" name="startbitrate" value="${data.startbitrate}" />
            </p>
            <p>
              <label>最低码率</label>
              <span id="span_minbitrate">${data.minbitrate}</span>
	          <input type="hidden" id="minbitrate" name="minbitrate" value="${data.minbitrate}" />
            </p>
           <p>
              <label>最大码率</label>
              <span id="span_maxbitrate">${data.maxbitrate}</span>
	          <input type="hidden" id="maxbitrate" name="maxbitrate" value="${data.maxbitrate}" />
            </p>
            <div class="select_box">
              <label>默认视频codec</label>
              <u:select id="firstpt" value="${data.firstpt}" dictionaryType="cps_firstpt" showAll="false" />
              Vp8：122，H264：121
            </div>
            <div class="clear"></div>
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
//网络类型
function change_nettype(value, text, isInit){
	value = value.split(",");
	$("#nettype").val(value[0]);
	$("#span_startbitrate").text(value[1]);
	$("#startbitrate").val(value[1])
	$("#span_minbitrate").text(value[2]);
	$("#minbitrate").val(value[2])
	$("#span_maxbitrate").text(value[3]);
	$("#maxbitrate").val(value[3])
}

var validate;
$(function(){
	//表单验证规则
	validate = $("#mainForm").validate({
		rules: {
			pv: "required",
			ver: "required",
			nettype: "required",
			firstpt: "required"
		},
		messages: {
			pv: "请选择手机平台",
			ver: "请输入客户端SDK版本",
			nettype: "请选择网络类型",
			firstpt: "请选择默认视频codec"
		}
	});
	
	var nettype = $("#nettype").val();
	$("#div_nettype span").text($("#div_nettype li[val^='"+nettype+",']").text());
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
		url : "${ctx}/permission/save",
		type : "post",
		timeout:30000
	};
	$("#mainForm").ajaxSubmit(options);
}
</script>
</body>
</html>