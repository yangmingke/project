<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="s"  uri="/struts-tags"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html>
<html lang="zh-cn">
<head>
	<meta charset="UTF-8"><meta http-equiv="X-UA-Compatible" content="IE=edge" />
	<title>flypaas开放平台管理中心——账户信息</title>
	<%@include file="/page/resource.jsp"%>
	<script type="text/javascript" src="<%=path%>/js/jquery-1.8.2.js"></script>
	<script type="text/javascript" src="<%=path%>/js/Highcharts-4.0.1/highcharts.js" ></script>
	<script type="text/javascript" src="<%=path%>/js/Highcharts-4.0.1/modules/exporting.js" ></script>
	<script type="text/javascript" src="<%=path%>/js/form.js"></script>
	<script type="text/javascript" src="<%=path%>/js/md5.js"></script>
</head>
<body id="03-1">
	<!--头部header bof-->
	<%@include file="/page/head.jsp" %>
	<!--头部header eof-->

	<!--主体content bof-->
	<div class="content">

		<!--侧边side bof-->
		<%@include file="/page/left.jsp" %>
		<!--侧边side bof-->
		<input type="hidden" value="<%=path%>" id="path_fo_js" />
		<!--侧边side bof-->

		<!--右侧main bof-->
		<div class="main">
			<div class="breadcrumbs">
				<ul>
					<li><a href="javascript:;">财务管理</a></li>
					<li class="active"><a href="javascript:;">财务总览</a></li>
				</ul>
			</div>

			<div class="main_tab_tit">
				<ul>
					<li class="active">账户信息</li>
					<%-- <li onclick="location.href='<%=path%>/user/feeConfig'">资费配置</li> by yangmingke--%>
					<%-- <li onclick="location.href='<%=path%>/app/EventBalance'">红包账户</li> --%>
				</ul>
			</div>

			<!--说明state_box bof-->
			<div class="state_box">
				<p>1、配置余额不足提醒可在余额降低到一定阀值进行短信提醒，请留意余额不足导致的应用停止服务</p>
			</div>
			<!--说明state_box eof-->

			<div class="item_box account_info">
				<div class="item_left">
					<div class="box">
						<h1>我的余额<!-- <a href="javascript:;" class="float_link">余额提醒</a> by yangmingke--></h1>
						<ul>
							<li>
								<label>账户余额：</label>
								<span class="green1">
									<s:if test="data.finance.balance!=null">
					                	￥<s:property value="data.finance.balance"/>元
				                	</s:if>
				                	<s:else>
				                		￥0.0000元
				                	</s:else>
								</span>
							</li>
							<li>
								<s:if test="data.compareConsume < 0">
									<s:set var="style" value="'before_down'" />
								</s:if>
								<s:elseif test="data.compareConsume == 0">
									<s:set var="style" value="'before'" />
								</s:elseif>
								<s:else>
									<s:set var="style" value="'before_up'" />
								</s:else>
								<dl><label>昨日消费：</label><span>￥<s:property value="data.ysdConsume"/>元</span></dl>
								<dl><label>较前日：</label><span class="${style}">￥<s:property value="data.increaseConsume"/>元</span></dl>
							</li>
							<li class="last">
								<label>前日消费：</label><span>￥<s:property value="data.beforeConsume"/>元</span>
							</li>
						</ul>
					</div>
				</div>

				<div class="item_right">
					<div class="box">
						<h1>资费类型<%-- <a href="<%=path%>/user/feeConfig">查看</a> by yangmingke--%></h1>
						<div class="note">${data.finance.package_name}</div>
					</div>
				</div>
			</div>

			<div class="item_box">
				<div class="box">
					<h1>月消费曲线图<span class="green1">（${data.month}月份）</span>
					<div class="item_search">
							<div class="select_box">
								<label>选择应用：</label>
								<div class="select">
									<span>${data.app.appName}<i>&nbsp;</i></span>
									<ul id="appList">
										<s:if test="data.appList!=null">
					                  		<s:iterator value="data.appList" var="ap" status="i">
					                  			<s:if test="#i.index==0">
					                  				<li val="${ap.appSid}" class="selected">${ap.appName}</li>
					                  			</s:if>
					                  			<s:else>
							                    	<li val="${ap.appSid}">${ap.appName}</li>
					                  			</s:else>
					                  		</s:iterator>
					                  	</s:if>
									</ul>
								</div>
							</div>
							<input type="button" value="查询" class="search" onclick="appCsm()" />
					</div>
					</h1>
					<div class="account_chart">
						<div class="chart_desc">
							<p><label>消费总额：</label><span id="allAppCsm">￥0.0元 </span><%-- <label>应用余额：</label><span id="appBalance">￥0.0元</span> --%></p>							
						</div>
						<div id="drawDiv"></div>
					</div>
				</div>
			</div>
		</div>
		<!--右侧main bof-->

	</div>
	
	<!--弹层（余额提醒） bof-->
  <div class="background_box">&nbsp;</div>
  <div class="float_box balance_box">
    <div class="float_tit">
      <h1>余额提醒</h1>
    </div>
    <div class="float_ctn">
    	<form method="post" id="remindForm" action="<%=path%>/user/balanceRemind">
        <div class="float_field">
          <dl>
            <dt>当前绑定手机号：</dt>
            <dd>
              <span class="txt">${data.finance.mobile}</span>
            </dd>
          </dl>
        </div>
        <div class="float_field">
          <dl>
            <dt>当账户余额低于：</dt>
            <dd>
              <input type="text" id="money" name="remind.money" value="${data.finance.remind_money}" onfocus="inputControl.setNumber(this, 10, 0, false)" /><em style="font-style:normal; display:inline-block; margin-left:-20px; margin-right:10px; background:#fff;">元</em>后提醒
              <span style="display:none" id="error_money" class="error">余额输入错误，请重新输入。</span>
          </dd>
          <dd><span class="tips">* 账户产生消费时提醒，提醒费用从账户扣除</span></dd>
          </dl>
        </div>
        <div class="float_field">
          <dl>
            <dt>提醒方式：</dt>
            <dd>
              <span class="txt">手机短信通知</span>
            </dd>
          </dl>
        </div>
        <div class="float_btn">
          <input type="button" value="取消" class="cancel_btn" />
          <input type="submit" value="确定" class="confirm_btn" />
      </div>
    	</form>
    </div>
  </div>
  <!--弹层（余额提醒） eof-->
	<!--主体content eof-->
	
	<!--底部footer bof-->
	<%@include file="/page/foot.jsp" %>
	<!--底部footer eof-->

<script type="text/javascript">
$(function(){
	$("#remindForm").submit(function(){
		  var money = $("#money").val();
		  var boo = verifyNumber(money);
		  if(!boo){
			  $("#error_money").fadeIn();
			  return false;
		  }else{
			  $("#error_money").hide();
		  }
		  return true;
	});
	
	appCsm();
});

function appCsm(){
	var appSid = $("#appList li.selected").attr("val");
	
	$.ajax({
		url:"<%=path%>/bill/appCsmDataByMonth",
		type:"post",
		data:"appSid="+appSid,
		dataType: "text",
		success: function (data) {
			var array = data.split("T");
			var appAllCsm = array[0];
			var appBalance = array[1];
			$("#allAppCsm").text("￥"+appAllCsm+"元");
			$("#appBalance").text("￥"+appBalance+"元");
			var x = array[2];
			x = eval("("+x+")");
			data = array[3];
			data = eval("("+data+")");
			drawTodayConsumeInfo(data,x);
        },
        error: function (msg) {
        }
	});
}
var chart ;
function drawTodayConsumeInfo(data,x){
	chart = new Highcharts.Chart({
	chart: {
           renderTo: 'drawDiv',
           defaultSeriesType: 'spline',
           events: {
               //load: requestData
           }
       },
       title: {
           text: '应用当月消费走势',
           x: -20 //center
       },
       subtitle: {
           text: '',
           x: -20
       },
       xAxis: {
           categories: x,
           gridLineWidth: 1, //设置网格宽度为1 
           lineWidth: 1,  //基线宽度 
           labels:{y:20}  //x轴标签位置：距X轴下方26像素
           
       },
       yAxis: {
           title: {
               text: '' //左侧边栏
           },
           min:0,
           lineWidth: 1, //基线宽度 
           plotLines: [{
               value: 0,
               width: 1,
               color: '#808080'
           }]
       },
       tooltip: {
           valueSuffix: '元'
       },
       //设置图例
       legend: {
       	enabled:false //去掉图例
       },
  		//右下角不显示LOGO 
       credits: { 
           enabled: false   
       },
       series:data
   });
}
</script>
</body>
</html>