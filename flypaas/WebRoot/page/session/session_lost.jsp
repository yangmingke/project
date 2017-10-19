<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="s"  uri="/struts-tags"%>
<%@ taglib prefix="u"  uri="/flypaas-tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html>
<html lang="zh-cn">
<head>
<meta charset="UTF-8"><meta http-equiv="X-UA-Compatible" content="IE=edge" />
<title>flypaas开放平台管理中心——会话丢包</title>
<%@include file="/page/resource.jsp"%>
<link rel="stylesheet" type="text/css" href="<%=path%>/js/jquery-ui/css/theme/all.css" />
<link rel="stylesheet" type="text/css" href="<%=path%>/js/jquery-ui/css/demos.css" />
<script type="text/javascript" src="<%=path%>/js/jquery-ui/js/core.js"></script>
<script type="text/javascript" src="<%=path%>/js/jquery-ui/js/datepicker.js"></script>
<script type="text/javascript" src="<%=path%>/js/jquery-ui/js/widget.js"></script>
<script type="text/javascript" src="<%=path%>/js/datepicker.js"></script>
<script type="text/javascript" src="<%=path%>/js/echarts.min.js"></script>
<style type="text/css">
	.hz_tip_table > tbody > tr > td,.hz_tip_table > tbody > tr > th  {
	  padding: 1px 4px;
	}
	h4{  
			margin-left: 20px;  
		}  
	p{  
		text-align: center;  
	}  
	.modal{  
		display: none;  
		width: 100%;  
		height: 100%;  
		position: fixed;  
		left: 0;  
		top: 0;  
		z-index: 1000;  
		background-color: rgba(0,0,0,0.5);  
	}  
	.modal-content{  
		display: flex;  
		flex-flow: column nowrap;  
		justify-content: space-between;  
		width: auto;  
		max-width: 1000px;  
		height: auto;  
		max-height: 1000px;  
		margin: 100px auto;  
		border-radius:10px;  
		background-color:#fff;  
		-webkit-animation: zoom 0.6s;  
		animation: zoom 0.6s;  
		resize: both;  
		overflow: auto;  
	}  
	@-webkit-keyframes zoom{  
		from {-webkit-transform: scale(0)}  
		to {-webkit-transform: scale(1)}  
	}  
	@keyframes zoom{  
		from {transform: scale(0)}  
		to {transform: scale(1)}  
	}  
	.modal-header{  
		box-sizing:border-box;  
		border-bottom:1px solid #ccc;  
		display: flex;  
		justify-content: space-between;  
		align-items: center;  
	}  
	.close{  
		color: #b7b7b7;  
		font-size: 30px;  
		font-weight: bold;  
		margin-right: 20px;  
		transition: all 0.3s;  
	}  
	.close:hover, .close:focus{  
		color: #95b4ed;  
		text-decoration: none;  
		cursor: pointer;  
	}  
	.modal-body{  
		padding: 10px;  
		font-size: 16px;  
		box-sizing:border-box;  
	}  
	@media only screen and (max-width: 700px){  
		.modal-content {  
			width: 80%;  
		}  
	}
</style>
</head>
<body id="01-2">
<!--头部header bof-->
<%@include file="/page/head.jsp" %>
<!--头部header eof--> 

<!--主体content bof-->
<div class="content"> 
  
  <!--侧边side bof-->
  <%@include file="/page/left.jsp" %>
  <!--侧边side bof--> 
  
  <!--右侧main bof-->
  <div class="main">
    <div class="breadcrumbs">
      <ul>
        <li><a href="javascript:void(0)">控制台</a></li>
        <li class="active"><a href="javascript:void(0)">会话丢包</a></li>
      </ul>
    </div>
    <div class="main_tab_tit">
      <ul>
        <li class="active">会话丢包</li>
      </ul>
    </div>
    <div class="bill_search">
      <s:form theme="simple" namespace="/user" action="querySessionPacketLoss" method="post" name="form" id="form">
        <ul>
          <li>
            <label>日期：</label>
            <input type="text" name="datetime" id="datetime" value="${datetime}"/><span>
            <div class="select_box select_app">
				<label>选择应用：</label>
				<div input="appSid" tabindex="2" class="select"
					defaultValue="${appSid}">
					<span>请选择应用<i>&nbsp;</i></span>
					<ul>
						<s:if test="appList!=null">
							<s:iterator value="appList" var="app">
								<li val="${app.appSid}">${app.appName}</li>
							</s:iterator>
						</s:if>
					</ul>
				</div>
			</div> 
			<label>会话ID：</label>
            <input type="text" name="cookieId" id="cookieId" placeholder="会话ID" value="${cookieId}"/>
            <input type="button" value="查询" onclick="query()"/>
            <span ><font color="red" id="error" style="display: none;"></font></span>
            <span style="float:right;margin-top:2px;">
            	<s:if test="sessionLoss.resultList!=null&&sessionLoss.resultList.size()>0">
 	    			<input id="showPicture" type="button" onclick="show('showPicture')" value="统计图" />
 	    		</s:if>
 	    		<input style="display: none;" id="showList" type="button" onclick="show('showList')"  value="列表" />
 	    	</span>
          </li>
     	</ul>
      </s:form>
    </div>
    
    <!--表格列表 table_box bof-->
 	<s:if test="sessionLoss.resultList!=null&&sessionLoss.resultList.size()>0">
	<div class="table_box" id="list">
		<table cellpadding="0" cellspacing="0" border="0">
			<thead>
				<tr>
					<th>序号</th>
					<th>源ip</th>
					<th>目的ip</th>
					<th>路径</th>
					<th>流ID</th>
					<th>采样次数</th>
					<th>丢包次数</th>
					<th>操作</th>
				</tr>
			</thead>
			<tbody>
				 <%int i = 1; %> 
	     		 <s:iterator value="sessionLoss.resultList" var="result">
					<tr>
						<td ><%=i++%></td>
				        <td>${result.src_ip}</td>
				        <td>${result.dst_ip}</td>
				        <td>${result.from_ip} &rarr; ${result.to_ip}</td>
				        <td >${result.stream_id}</td>
				        <td>${result.seg_cnt}</td>
				        <td>${result.lost_cnt}</td>
				        <td class="blue"><a href="javascript:void(0)"  onclick="otherInfo('${result.from_ip} &rarr; ${result.to_ip}','${result.cookie}','${result.sid}','${result.appid}','${result.snapshoot}')">其他信息</a></td>
					</tr>
				</s:iterator>
			</tbody>
		</table>
	</div>
	<div id="picture" style="height:480px;" ></div>
	</s:if>
  </div>
  <div id="modal" class="modal">  
	    <div class="modal-content">  
	        <header class="modal-header">  
	            <h4><span id="title"></span> 其他信息</h4>  
	            <span class="close" >x</span>  
	        </header>  
	        <div class="modal-body">  
	            <table style="font-size:14px;" border="1" cellspacing="0" cellpadding="0">
     				<tr>
     					<th width="10%">会话ID</td>
     					<td id="cookie">2</td>
     				</tr>
     				<tr>
     					<th>用户ID</th>
     					<td id="sid">2</td>
     				</tr>
     				<tr>
     					<th>应用ID</th>
     					<td id="appid">2</td>
     				</tr>
     				<tr>
     					<th>丢包情况</th>
     					<td id="snapshoot">2</td>
     				</tr>
     			</table>	
	        </div>  
	    </div>  
	</div>  
		<!--右侧main bof-->   
</div>

<script type="text/javascript">
	//弹框
	var close = document.getElementsByClassName('close')[1];  
	var modal = document.getElementById('modal');  
	close.addEventListener('click', function(){  
	    modal.style.display = "none";  
	});  
	function otherInfo(title,cookie,sid,appid,snapshoot){
    	$('#title').text(title);
    	$('#cookie').text(cookie);
    	$('#sid').text(sid);
    	$('#appid').text(appid);
    	$('#snapshoot').text(snapshoot);
    	var modal = document.getElementById('modal');  
        modal.style.display = "block";  
    }
	
	function query(){
		var datetime= $('#datetime').val();
		var cookieId= $('#cookieId').val();
		if(datetime == ""){
			$('#error').text('请输入查询日期');
			$('#error').show();
			return false;
		}
		if(cookieId == ""){
			$('#error').text('请至输入会话ID');
			$('#error').show();
			return false;
		}
		$('#form').submit();
	}
	
	$(function(){
		//表格隔行变色
		$(".table_box table tr:even").addClass("even");
		
		$("#datetime").datepicker({
			 dateFormat:"yy-mm-dd",
			 maxDate:0
		});
		
		if($("#datetime").val() == ''){
			$("#datetime").datepicker( 'setDate' , new Date());
		}
		
		if('${resultJson}' != '[]' && '${resultJson}' != ''){
			createMap('${resultJson}','${packetLossTime}');
			$('#picture').hide();
		}
	});
	
	function show(order){
		if(order == 'showPicture'){
			$('#showList').show();
			$('#picture').show();
			$('#showPicture').hide();
			$('#list').hide();
		}else if(order == 'showList'){
			$('#showPicture').show();
			$('#list').show();
			$('#picture').hide();
			$('#showList').hide();
		}
	}
	
	function createMap(resultJson,packetLossTime) {
		var lineList = eval(resultJson);
		var time = eval(packetLossTime);
		var colors = [ '#d14a61', '#5793f3','blue','orange' ];
		var legendData=[];//线段名称
		var series=[];//线段      series=[{line1}，{line2}，{line3}]
		
		for(var i in lineList){
			legendData[i] = lineList[i].from_ip + "->" + lineList[i].to_ip;
			var line = new Object();
			line.name = legendData[i];
			line.type = 'line';
			
			line.data = [];
			var snapshoot = lineList[i].snapshoot.replace("[", "").replace("]", "").trim();
			var snapshootArray = snapshoot.split("  ");
			var j = 0;
			for(var k =0;k < time.length; k++){
				var snapshootTime = snapshootArray[j].split(" ")[0];
				var snapshootRate = snapshootArray[j].split(" ")[1];
				if(snapshootTime == time[k]){
					line.data.push([snapshootTime,snapshootRate*100]);
					if(j < snapshootArray.length - 1){
						j++;
					}
				}else{
					line.data.push([time[k],0]);
				}
			}
			//line.data.push([snapshootTime,snapshootRate*100]);
			series.push(line);
		}
		//画统计图
		var myChart = echarts.init(document.getElementById('picture'));
		
		option = {
			/* tooltip : {
				trigger : 'axis'
			}, */
			grid : {
				left : '20%'
			},
			legend : {
				orient:'vertical',
				data : legendData,
				x : 'left'
			},
			xAxis : [ {
				type : 'category',
				axisTick : {
					alignWithLabel : true
				},
				data : time
			} ],
			yAxis : [ {
				type : 'value',
				name : '丢包率',
				position : 'left',
				axisLine : {
					lineStyle : {
						color : colors[0]
					}
				},
				axisLabel : {
					formatter : '{value} %'
				}
			}],
			tooltip: {//提示框
		    	trigger: 'axis',
	            padding: 10,
	            borderWidth: 1,
	            formatter: function (obj) {
	            	  var existPacketLoss = false;
	                  var res = '<div class="">'
	                    + '</div>';
	                  var res = res + '<table class="hz_tip_table"><tr><th>路径</th><th>时间</th><th>丢包率</th></tr>';
	                  for(var i in obj){
	                	  if(Number(obj[i].data[1]) == 0){
	                		  continue;
	                	  }
	                	  existPacketLoss = true;
		                  res = res + '<tr>';
		                  res = res + '<td><span style="display:inline-block;margin-right:5px;border-radius:10px;width:9px;height:9px;background-color:'+obj[i].color+'"></span>'+obj[i].seriesName+'</td>';
		                  res = res + '<td>'+obj[i].data[0]+'</td>';
		                  res = res + '<td style="text-align: center;">'+Number(obj[i].data[1]).toFixed(2)+'%</td>';
		                  res = res + '</tr>';
	                  }
	                  if(existPacketLoss){
	                  	return res;
	                  }else{
	                	  return "";
	                  }
	            }
	          },
			series : series
		};

		myChart.setOption(option); //载入图表
	}
</script>

<!--主体content eof--> 

<!--底部footer bof-->
<%@include file="/page/foot.jsp" %>
<!--底部footer eof-->
</body>
</html>