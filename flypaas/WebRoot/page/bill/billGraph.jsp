<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="s"  uri="/struts-tags"%>
<%
String path = request.getContextPath();
request.setAttribute("path", path);
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=edge" />
<title>消费账单</title>
<%@include file="/page/resource.jsp"%>
<script type="text/javascript" src="<%=path%>/js/Highcharts-4.0.1/highcharts.js" ></script>
<script type="text/javascript" src="<%=path%>/js/Highcharts-4.0.1/modules/exporting.js" ></script>
<script type="text/javascript">
$(function(){
	$(".child p").eq(2).show().siblings("p").hide();
	$("#menu_3_1").addClass("active");
});
</script>
</head>

<body>
<!--公共头部header bof-->
<%@include file="/page/head.jsp"%>
<!--公共头部header eof--> 


<!--公共导航菜单 bof-->
<%@include file="/page/menu.jsp"%>
<!--公共导航菜单 eof--> 


<!--主体部分content bof-->
  <div class="content_wrapper">
    <div class="content_box">
      <div class="tab_tit">
          <ul>
            <li  onclick="location.href='<%=path%>/bill/bill'">消费账单</li>
            <li  class="active" onclick="location.href='<%=path%>/bill/billGraph'">消费曲线图</li>
          </ul>
        </div>
      <div class="main_ctn">
        <div class="tab_ctn bills_ctn">
          <div class="info">
            <div class="develop_info">
              <ul class="basic_info">
                <li>
                  <dl>
                    <dt>选择应用</dt>
                    <dd><div class="select"><span id="txt_select">${app.appName}</span>
                  <ul style="display:none;">
                    <s:if test="appList!=null">
                  		<s:iterator value="appList" var="ap" status="i">
                  			<s:if test="#i.index==0">
                  				<li id="${ap.appSid }" onclick="appCsm('${ap.appSid}')" class="selected">${ap.appName}</li>
                  			</s:if>
                  			<s:else>
		                    	<li id="${ap.appSid }" onclick="appCsm('${ap.appSid}')">${ap.appName}</li>
                  			</s:else>
                  		</s:iterator>
                  	</s:if>
                  </ul>
                </div></dd>
                  </dl>
                </li>
                <li class="time">
                  <dl>
                    <dt>消费总额</dt>
                    <dd id="allAppCsm"></dd>
                  </dl>
                </li>
                <li class="time">
                  <dl>
                    <dt>应用余额</dt>
                    <dd id="appBalance"></dd>
                  </dl>
                </li>
              </ul>
            </div>
            <div class="bills_tab">
<!--           <div class="chart_ctn"> -->
            	<div id="drawDiv"></div>
<!--           </div> -->
          	</div>                 
        </div>
      </div>
    </div>
  </div>
</div>
<!--主体部分content eof--> 

<!--公共底部footer bof-->
<%@include file="/page/foot.jsp"%>
<!--公共底部footer bof-->
<script type="text/javascript">
    $(function(){
    	var appSid = "${app.appSid}";
// 		var appSid = "9177b422d61f4ac98037882820046188";
    	appCsm(appSid);
    });
	function appCsm(appSid){
		$.ajax({
			url:"<%=path%>/bill/appCsmDataByMonth",
			type:"post",
			data:"appSid="+appSid,
			dataType: "text",
			success: function (data) {
				var array = data.split("T");
				var appAllCsm = array[0];
				var appBalance = array[1];
				$("#allAppCsm").text(appAllCsm);
				$("#appBalance").text(appBalance);
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
