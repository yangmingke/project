<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="java.net.URLDecoder" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html lang="zh-cn">
	<style>
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
    <script src="${ctx}/js/echarts.min.js"></script>
    <div id="modal" class="modal">  
	    <div class="modal-content">  
	        <header class="modal-header">  
	            <h4><span id="title"></span> 其他信息</h4>  
	            <span class="close"></span>  
	        </header>  
	        <div class="modal-body">  
	            <table class="table table-hover text-center" style="font-size:12px;">
     				<tr class="text-c">
     					<th width="10%">会话ID</td>
     					<td id="cookie">2</td>
     				</tr>
     				<tr class="text-c">
     					<th>用户ID</th>
     					<td id="sid">2</td>
     				</tr>
     				<tr class="text-c">
     					<th>应用ID</th>
     					<td id="appid">2</td>
     				</tr>
     				<tr class="text-c">
     					<th>丢包情况</th>
     					<td id="snapshoot">2</td>
     				</tr>
     			</table>	
	        </div>  
	    </div>  
	</div>
    <table class="table table-hover text-center" id="list">
      <tr class="text-c">
		<th>序号</th>
		<th>源ip</th>
		<th>目的ip</th>
		<th>路径</th>
		<th>流id</th>
		<th>采样次数</th>
		<th>丢包次数</th>
		<th>操作</th>
      </tr>
      <%int i = 1; %> 
      <c:forEach var="result" items="${resultList}"> 
      <tr>
        <td ><%=i++%></td>
        <td>${result.src_ip}</td>
        <td>${result.dst_ip}</td>
        <td>${result.from_ip} &rarr; ${result.to_ip}</td>
        <td>${result.stream_id}</td>
        <td>${result.seg_cnt}</td>
        <td>${result.lost_cnt}</td>
        <td><a class="button border-green" href="javascript:void(0)" onclick="otherInfo('${result.from_ip} &rarr; ${result.to_ip}','${result.cookie}','${result.sid}','${result.appid}','${result.snapshoot}')" title="其他信息"><span class="icon-list-alt"></span></a></td>
      </tr>
     </c:forEach>
    </table>
    <div id="picture" style="height:480px;"></div>
    
    <script type="text/javascript">
    	//弹框
	    var close = document.getElementsByClassName('close')[0];  
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
	    
    	//画图
		$(function() {
			createMap('${resultJson}','${packetLossTime}');
			$('#picture').hide();
			$('#showList').hide();
			if('${resultJson}' == '[]'){
				$('#showPicture').hide();
			}else{
				$('#showPicture').show();
			}
		});

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
					left : '15%'
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
</html>