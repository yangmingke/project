<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html>
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
	.floatLayer{ width:100%; height:100%; position:fixed; background:#000;  z-index:9990; top:0px; left:0px;filter:alpha(Opacity=50);-moz-opacity:0.50;opacity: 0.50;}
	.liadloging{ width:100%; height:auto; position:absolute; top:35%; left:43%; z-index:9995;  }
	.floatleft{
		float: left;
	}
	.routeTitle{
		font-size:20px;
	}
</style>
</head>
<body>
	<div id="loadingDiv" style="display: none;">
		<div class="liadloging">
	        <img src="${ctx}/img/loadingImg.gif"/>
		</div>
		<!--灰色遮罩层 begin-->
		<div class="floatLayer"></div> 
		<!--灰色遮罩层 end-->
	</div>
	<div class="panel admin-panel">
		<div class="panel-head">
			<strong><span class="icon-key"></span> 分析 
			<select id="route">
				<c:forEach var="routeKey" items="${routeKeyList}">
					<option value="${fn:substringAfter(routeKey,'RTPP_CONCURR_ZSET_')}" <c:if test="${fn:substringAfter(routeKey,'RTPP_CONCURR_ZSET_') == routeDomain }">selected="selected"</c:if>>${fn:substringAfter(routeKey,'RTPP_CONCURR_ZSET_')}</option>
				</c:forEach>	
			</select> 域节点信息</strong>
		</div>
		<div class="body-content">
			<form id="form1" name="form1">
				<div>
					<span class="floatleft routeTitle" style="margin:0 0 0 309px;"><strong>可选节点</strong></span>
					<span class="floatleft routeTitle" style="margin:0 0 0 233px;"><strong><font color="green" >分析节点</font></strong></span>
				</div>
				<div style="padding-top: 30px ">
					<select name="ipList" size="20" multiple=""  id="ipList" style="width: 200px;margin:0 0 0 250px;" class="floatleft">
						<c:forEach items="${ipList}" var="ip">
							<option value="${ip}">节点：${ip}</option>
						</c:forEach>
					</select> 
					<button type="button" onclick="allsel(document.form1.ipList,document.form1.analysislist);"
						class="button bg-main icon-arrow-right floatleft" style="margin: 130px 0 0 20px;">添加 </button>
					<button type="button"  onclick="allsel(document.form1.analysislist,document.form1.ipList);"
						class="button bg-main icon-arrow-left floatleft" style="margin: 200px 0 0 -72px;">移除</button>
					<select name="analysislist" size="20" multiple=""  id="analysislist" style="width: 200px;margin:0 333px 0 21px;" class="floatleft" >
					</select>
				</div>
			</form>
			<div class="form-group" style="padding: 378px 0 0 467px;" >
				<div class="field">
					<button class="button bg-main icon-check-square-o" type="button" onclick="analysis();">分析</button>
				</div>
			</div>
		</div>
	</div>
	<script language="javascript">
		$(function(){
			$("#ipList").dblclick(function(){
				var $option = $("#ipList option:selected");
				$option.appendTo("#analysislist");
			});
			$("#analysislist").dblclick(function(){
				var $option = $("#analysislist option:selected");
				$option.appendTo("#ipList");
			});
			$('#route').change(function(){
				window.location.href="/route/analysisPage?routeDomain=" + $(this).val();
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
		function analysis(){
			var analysisStr = "";
			if($("#analysislist option").size() < 2){
				alert("请选择两个或两个以上IP节点进行分析");
				return false;
			}
			$("#analysislist option").each(function() {
				var val = $(this).val(); //获取单个value
				analysisStr += val + ",";
			});
			analysisStr = analysisStr.substring(0, analysisStr.length - 1);
			$("#loadingDiv").show();
			$('body').load("/route/analysis", {
				"analysisStr" : analysisStr,
				"routeDomain" : $('#route').val()
			});
		}
	</script>
</body>
</html>