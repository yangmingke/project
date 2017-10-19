<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/common/taglibs.jsp"%>

<s:set var="title" value="#parameters.id==null ? '添加' : '修改'"/>
<s:set var="title_btn" value="#parameters.id==null ? '添 加' : '修 改'"/>

<html>
<head>
	<title>${title}音频驱动智能适配</title>
	<style type="text/css">
		.main_ctn p label, .main_ctn .select_box label{width:182px;}
	</style>
</head>

<body>
      <div class="main_ctn">
        <h1>${title}音频驱动智能适配</h1>
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
              <label>手机品牌</label>
              <input type="text" name="phonebrand" value="${data.phonebrand}" maxlength="20"/>
              Htc、huawei
            </p>
            <p>
              <label>优先级</label>
              <input type="text" name="priority" value="${data.priority}" onfocus="inputControl.setNumber(this, 1, 0, false)"/>
              0～9值越小级别越高
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
			phonebrand: "required",
			priority: "required",
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
			phonebrand: "请输入手机品牌",
			priority: "请输入优先级",
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
		url : "${ctx}/voiceDeviceAuto/save",
		type : "post",
		timeout:30000
	};
	$("#mainForm").ajaxSubmit(options);
}
</script>
</body>
</html>