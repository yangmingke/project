<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/common/taglibs.jsp"%>

<html>
<head>
	<title>查看音频驱动智能适配</title>
	<style type="text/css">
		.main_ctn p label, .main_ctn .select_box label{width:182px;}
	</style>
</head>

<body>
<div class="main_ctn">
	<h1>
		<a href="javascript:;" class="back" onclick="history.back();">返 回</a>查看音频驱动智能适配
	</h1>
	<div class="admin_material">
		<p>
			<label>id</label>
			<span>${data.id}</span>
		</p>
		<p>
			<label>手机品牌</label>
			<span>${data.phonebrand}</span>
		</p>
		<p>
			<label>优先级</label>
			<span>${data.priority}</span>
		</p>
		<p>
			<label>媒体采集驱动音频数据流模式</label>
			<span><u:ucparams type="cps_recordsource" key="${data.recordsource}" /></span>
		</p>
		<p>
			<label>采集音频通道格式</label>
			<span><u:ucparams type="cps_recordchannel" key="${data.recordchannel}" /></span>
		</p>
		<p>
			<label>音频采集驱动采样频率</label>
			<span><u:ucparams type="cps_recordsamplerate" key="${data.recordsamplerate}" /></span>
		</p>
		<p>
			<label>播放驱动音频数据流类型</label>
			<span><u:ucparams type="cps_playstreamtype" key="${data.playstreamtype}" /></span>
		</p>
		<p>
			<label>音频播放通道格式</label>
			<span><u:ucparams type="cps_playchannel" key="${data.playchannel}" /></span>
		</p>
		<p>
			<label>音频播放采用频率</label>
			<span><u:ucparams type="cps_recordsamplerate" key="${data.playsamplerate}" /></span>
		</p>
		<p>
			<label>外放音频驱动模式</label>
			<span><u:ucparams type="cps_speakermode" key="${data.speakermode}" /></span>
		</p>
		<p>
			<label>听筒音频驱动模式</label>
			<span><u:ucparams type="cps_speakermode" key="${data.earpiecemode}" /></span>
		</p>
		<p>
			<label>通话音频驱动模式</label>
			<span><u:ucparams type="cps_speakermode" key="${data.callmode}" /></span>
		</p>
		<p>
			<label>创建时间</label>
			<span>${data.createtime}</span>
		</p>
		<p>
			<label>更新时间</label>
			<span>${data.updatetime}</span>
		</p>
	</div>
</div>
</body>
</html>