<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="zh-cn">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport"
	content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no" />
<meta name="renderer" content="webkit">
<title></title>
<link rel="stylesheet" href="${ctx}/css/pintuer.css">
<link rel="stylesheet" href="${ctx}/css/admin.css">
<script src="${ctx}/js/jquery.js"></script>
<script src="${ctx}/js/pintuer.js"></script>
<script src="${ctx}/js/editPwd.js"></script>
<script src="${ctx}/js/xcConfirm.js" type="text/javascript" charset="utf-8"></script>
<style type="text/css">
	.floatleft{
		float: left;
	}
	.routeTitle{
		font-size:20px;
	}
</style>
</head>
<body>
	<div class="panel admin-panel">
		<div class="panel-head">
			<strong><span class="icon-key"></span> 配置 <font color="green">${area}</font> 域 <font color="green">${ip}</font> 邻居节点</strong>
		</div>
		<div class="body-content">
			<form id="form1" name="form1">
				<div>
					<span class="floatleft routeTitle" style="margin:0 0 0 110px;"><strong><font color="green" >邻居域</font></strong></span>
					<span class="floatleft routeTitle" style="margin:0 0 0 244px;"><strong>可选节点</strong></span>
					<span class="routeTitle" style="margin:0 0 0 230px;"><strong><font color="green">非邻居域</font></strong></span>
				</div>
				<div style="padding-top: 1px ">
					<select name="neighbor" size="20" multiple=""  id="neighbor" style="width: 200px;margin:0 0 0 39px;" class="floatleft">
						<c:forEach items="${nbList}" var="nbIp">
							<option value="${nbIp}">节点：${nbIp}</option>
						</c:forEach>
					</select> 
					<button type="button" onclick="allsel(document.form1.others,document.form1.neighbor);"
						class="button bg-main icon-arrow-left floatleft" style="margin: 130px 0 0 20px;">添加 </button>
					<button type="button"  onclick="allsel(document.form1.neighbor,document.form1.others);"
						class="button bg-main icon-arrow-right floatleft" style="margin: 200px 0 0 -72px;">移除</button>
					<select name="others" size="20" multiple=""  id="others" style="width: 200px;margin:0 333px 0 21px;" class="floatleft" >
						<c:forEach items="${otherIpList}" var="otherIp">
							<c:if test="${ip !=  otherIp}">	
								<option value="${otherIp}">节点：${otherIp}</option>
							</c:if>
						</c:forEach>
					</select>
					<button type="button" onclick="allsel(document.form1.others,document.form1.notNeighbor);"
						class="button bg-main icon-arrow-right floatleft" style="margin: 130px 0 0 -314px;">添加 </button>
					<button type="button"  onclick="allsel(document.form1.notNeighbor,document.form1.others);"
						class="button bg-main icon-arrow-left floatleft" style="margin: 200px 0 0 -314px;">移除</button>
					<select name="notNeighbor" size="20" multiple=""  id="notNeighbor" style="width: 200px;margin:0 31px 0 -222px;" class="floatleft" >
						<c:forEach items="${nnbList}" var="nnbIp">
							<option value="${nnbIp}">节点：${nnbIp}</option>
						</c:forEach>
					</select>
				</div>
			</form>
			<div class="form-group" style="padding: 378px 0 0 360px;" >
				<div class="field">
					<button class="button bg-main icon-check-square-o" type="button" onclick="editNeighbor('${ip}','${area}');">提交</button>
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					<button  class="button bg-main icon-undo" type="button" onclick="window.history.go(-1);">返回</button>
				</div>
			</div>
		</div>
	</div>
	<script language="javascript">
		$(function(){
			$("#neighbor").dblclick(function(){
				var $option = $("#neighbor option:selected");
				$option.appendTo("#others");
			});
			$("#others").dblclick(function(){
				var $option = $("#others option:selected");
				$option.appendTo("#neighbor");
			});
			$("#notNeighbor").dblclick(function(){
				var $option = $("#notNeighbor option:selected");
				$option.appendTo("#others");
			});
		})
		
		function allsel(n1, n2) {
			while (n1.selectedIndex != -1) {
				var indx = n1.selectedIndex;
				var t = n1.options[indx].text;
				var v = n1.options[indx].value;
				n2.options.add(new Option(t, v));
				n1.remove(indx);
			}
		}
		function editNeighbor(ip,area) {
			var neighbor = "";
			$("#neighbor option").each(function() {
				var val = $(this).val(); //获取单个value
				neighbor += val + ",";
			});
			neighbor = neighbor.substring(0, neighbor.length - 1);
			var notNeighbor = "";
			$("#notNeighbor option").each(function() {
				var val = $(this).val(); //获取单个value
				notNeighbor += val + ",";
			});
			notNeighbor = notNeighbor.substring(0, notNeighbor.length - 1);
			$.post("/resourceController/editNeighbor", {
				"neighbor" : neighbor,
				"notNeighbor" : notNeighbor,
				"ip" : ip,
				"routeDomain":area
			}, function(data) {
				if(data == 1){
					window.wxc.xcConfirm(ip+"邻居域配置成功！", window.wxc.xcConfirm.typeEnum.info);
					return;
				}else{
					window.wxc.xcConfirm("邻居域配置失败！", window.wxc.xcConfirm.typeEnum.info);
					return;					
				}
			});
		}
	</script>
</body>
</html>