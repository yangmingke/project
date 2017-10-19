<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/common/taglibs.jsp"%>

<s:set var="title" value="#parameters.id==null ? '添加' : '修改'"/>
<s:set var="title_btn" value="#parameters.id==null ? '添 加' : '修 改'"/>

<html>
<head>
	<title>${title}音频驱动适配清单</title>
	<style type="text/css">
		.main_ctn p label, .main_ctn .select_box label{width:182px;}
	</style>
</head>

<body>
      <div class="main_ctn">
        <h1>${title}音频驱动适配清单</h1>
        <div class="admin_material admin_form">
          <form method="post" id="mainForm">
          	<s:if test="data.id != null">
	            <p>
	              <label>id</label>
	              ${data.id}
	              <input type="hidden" name="id" value="${data.id}" />
	            </p>
          	</s:if>
            <p>
              <label>平台信息</label>
              android
              <input type="hidden" name="pv" value="android" />
            </p>
            <p>
              <label>版本信息</label>
              <input type="text" name="app_ver" value="${data.app_ver==null ? 'ALL' : data.app_ver}" maxlength="20"/>
              V1.2.0，默认值：ALL
            </p>
            <p>
              <label>客户端SDK版本</label>
              <input type="text" name="ver" value="${data.ver==null ? 'ALL' : data.ver}" maxlength="20"/>
              V1.1.2，默认值：ALL
            </p>
            <p>
              <label>系统版本</label>
              <input type="text" name="system_ver" value="${data.system_ver==null ? 'ALL' : data.system_ver}" maxlength="20"/>
              2.1，默认值：ALL
            </p>
            <p>
              <label>系统API LEVEL</label>
              <input type="text" name="api_level" value="${data.api_level==null ? 'ALL' : data.api_level}" maxlength="20"/>
              “5”，默认值：ALL
            </p>
            <p>
              <label>手机品牌</label>
              <input type="text" name="phonebrand" value="${data.phonebrand}" maxlength="20"/>
              Htc、huawei
            </p>
            <p>
              <label>型号</label>
              <input type="text" name="phonemodel" value="${data.phonemodel==null ? 'ALL' : data.phonemodel}" maxlength="20"/>
			     例如HTC的G10、G11等，默认值：ALL
            </p>
            <p>
              <label>imei</label>
              <input type="text" name="imei" value="${data.imei==null ? 'ALL' : data.imei}" maxlength="60"/>
			     手机序列码，默认值：ALL
            </p>
            <div class="select_box">
              <label>媒体采集驱动音频数据流模式</label>
              <u:select id="recordsource" value="${data.recordsource}" dictionaryType="cps_recordsource" showAll="false" />
              	MediaRecorder.AudioSource
            </div>
            <div class="clear"></div>
            <div class="select_box">
              <label>采集音频通道格式</label>
              <u:select id="recordchannel" value="${data.recordchannel}" dictionaryType="cps_recordchannel" showAll="false" />
              AudioFormat
            </div>
            <div class="clear"></div>
            <div class="select_box">
              <label>音频采集驱动采样频率</label>
              <u:select id="recordsamplerate" value="${data.recordsamplerate}" dictionaryType="cps_recordsamplerate" showAll="false" />
            </div>
            <div class="clear"></div>
            <div class="select_box">
              <label>播放驱动音频数据流类型</label>
              <u:select id="playstreamtype" value="${data.playstreamtype}" dictionaryType="cps_playstreamtype" showAll="false" />
            </div>
            <div class="clear"></div>
            <div class="select_box">
              <label>音频播放通道格式</label>
              <u:select id="playchannel" value="${data.playchannel}" dictionaryType="cps_playchannel" showAll="false" />
            </div>
            <div class="clear"></div>
            <div class="select_box">
              <label>音频播放采用频率</label>
              <u:select id="playsamplerate" value="${data.playsamplerate}" dictionaryType="cps_recordsamplerate" showAll="false" />
            </div>
            <div class="clear"></div>
            <div class="select_box">
              <label>外放音频驱动模式</label>
              <u:select id="speakermode" value="${data.speakermode}" dictionaryType="cps_speakermode" showAll="false" />
            </div>
            <div class="clear"></div>
            <div class="select_box">
              <label>听筒音频驱动模式</label>
              <u:select id="earpiecemode" value="${data.earpiecemode}" dictionaryType="cps_speakermode" showAll="false" />
            </div>
            <div class="clear"></div>
            <div class="select_box">
              <label>通话音频驱动模式</label>
              <u:select id="callmode" value="${data.callmode}" dictionaryType="cps_speakermode" showAll="false" />
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
var validate;
$(function(){
	//表单验证规则
	validate = $("#mainForm").validate({
		rules: {
			app_ver: "required",
			ver: "required",
			system_ver: "required",
			api_level: "required",
			phonebrand: "required",
			phonemodel: "required",
			imei: "required",
			recordsource: "required",
			recordchannel: "required",
			recordsamplerate: "required",
			playstreamtype: "required",
			playchannel: "required",
			playsamplerate: "required",
			speakermode: "required",
			earpiecemode: "required",
			callmode: "required"
		},
		messages: {
			app_ver: "请输入版本信息",
			ver: "请输入客户端SDK版本",
			system_ver: "请输入系统版本",
			api_level: "请输入系统API LEVEL",
			phonebrand: "请输入手机品牌",
			phonemodel: "请输入型号",
			imei: "请输入imei",
			recordsource: "请选择媒体采集驱动音频数据流模式",
			recordchannel: "请选择采集音频通道格式",
			recordsamplerate: "请选择音频采集驱动采样频率",
			playstreamtype: "请选择播放驱动音频数据流类型",
			playchannel: "请选择音频播放通道格式",
			playsamplerate: "请选择音频播放采用频率",
			speakermode: "请选择外放音频驱动模式",
			earpiecemode: "请选择听筒音频驱动模式",
			callmode: "请选择通话音频驱动模式"
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
		url : "${ctx}/voiceDevice/save",
		type : "post",
		timeout:30000
	};
	$("#mainForm").ajaxSubmit(options);
}
</script>
</body>
</html>