<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/common/taglibs.jsp"%>

<html>
<head>
	<title>查看策略权限过滤</title>
</head>

<body>
<div class="main_ctn">
	<h1>
		<a href="javascript:;" class="back" onclick="history.back();">返 回</a>查看策略权限过滤
	</h1>
	<div class="admin_material">
		<p>
			<label>id</label>
			<span>${data.id}</span>
		</p>
		<p>
			<label>用户帐号</label>
			<span>${data.uid}</span>
		</p>
		<p>
			<label>手机平台</label>
			<span><u:ucparams type="cps_pv" key="${data.pv}" /></span>
		</p>
		<p>
			<label>客户端SDK版本</label>
			<span>${data.ver}</span>
		</p>
		<p>
			<label>系统版本号</label>
			<span>${data.osv}</span>
		</p>
		<p>
			<label>品牌</label>
			<span>${data.brand}</span>
		</p>
		<p>
			<label>型号</label>
			<span>${data.model}</span>
		</p>
		<p>
			<label>P2P探测使能</label>
			<span><u:ucparams type="cps_enable" key="${data.iceenable}" /></span>
		</p>
		<p>
			<label>音频FEC使能</label>
			<span><u:ucparams type="cps_enable" key="${data.audiofec}" /></span>
		</p>
		<p>
			<label>日志上报使能</label>
			<span><u:ucparams type="cps_enable" key="${data.logreport}" /></span>
		</p>
		<p>
			<label>驱动自动适配</label>
			<span><u:ucparams type="cps_enable" key="${data.autoadapter}" /></span>
		</p>
		<p>
			<label>语音质量监控使能</label>
			<span><u:ucparams type="cps_enable" key="${data.vqmenable}" /></span>
		</p>
		<p>
			<label>Rtp压缩使能</label>
			<span><u:ucparams type="cps_enable" key="${data.prtpenable}" /></span>
		</p>
		<p>
			<label>Vp8使能</label>
			<span><u:ucparams type="cps_enable" key="${data.vp8enable}" /></span>
		</p>
		<p>
			<label>H264使能</label>
			<span><u:ucparams type="cps_enable" key="${data.h264enable}" /></span>
		</p>
		<p>
			<label>网络类型</label>
			<span id="span_nettype"></span>
		</p>
		<div style="display:none;">
			<u:select id="nettype" value="${data.nettype}" dictionaryType="cps_nettype" showAll="false" />
		</div>
		<p>
			<label>起始码率</label>
			${data.startbitrate}
		</p>
		<p>
			<label>最低码率</label>
			${data.minbitrate}
		</p>
		<p>
			<label>最大码率</label>
			${data.maxbitrate}
		</p>
		<p>
			<label>默认视频codec</label>
			<span><u:ucparams type="cps_firstpt" key="${data.firstpt}" /></span>
		</p>
		<p>
			<label>描述信息</label>
			<span><s:property value="data.describe" /></span>
		</p>
		<p>
			<label>创建时间</label>
			<span>${data.createtime}</span>
		</p>
	</div>
</div>

<script type="text/javascript">
$(function(){
	var nettype = $("#nettype").val();
	$("#span_nettype").text($("#div_nettype li[val^='"+nettype+",']").text());
});
</script>
</body>
</html>