$(function(){
	queryData();
});
function queryData(){
	$.post("/operation/queryNodeConcurrent",{"date":$('#dateTime').val()},function(data){
		var json = eval("("+data+")");
		createMap(json);
	});
}

function createMap(data){
	$('#dateTime').val(data.date); 
	
	//画统计图
	var myChart = echarts.init(document.getElementById('main'));
	var time = data.time;
	var nodeData = data.nodeData;
	var legendData=[];//线段名称
	var series=[];//线段      series=[{line1}，{line2}，{line3}]
	
	for(var ip in nodeData){
		legendData.push(ip);
		var nodeConcurrent = nodeData[ip];
		var line = new Object();
		line.name = ip;
		line.type = 'line';
		line.data = [];
		for(var i in nodeConcurrent){
			line.data.push([nodeConcurrent[i].datetimeStr,nodeConcurrent[i].concurrence]);
		}
		series.push(line);
	}

	option = {
			grid : {
				left : '9%'
			},
			dataZoom: [
	           {
	               show: true,
	               realtime: true,
	               start: 0,
	               end: 100
	           },
	           {
	               type: 'inside',
	               realtime: true,
	               start: 0,
	               end: 100
	           }
	       ],
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
				name : '并发数',
				position : 'left',
			}],
			tooltip: {//提示框
		    	trigger: 'axis',
	            padding: 10,
	            borderWidth: 1,
	            formatter: function (obj) {
	            	  var existCconcurrent = false;
	                  var res = '<div class="">'
	                    + '</div>';
	                  var res = res + '<table class="hz_tip_table"><tr><th>IP地址</th><th>并发数</th><th>时间</th></tr>';
	                  for(var i in obj){
	                	  if(Number(obj[i].data[1]) == 0){
	                		  continue;
	                	  }
	                	  existCconcurrent = true;
		                  res = res + '<tr>';
		                  res = res + '<td><span style="display:inline-block;margin-right:5px;border-radius:10px;width:9px;height:9px;background-color:'+obj[i].color+'"></span>'+obj[i].seriesName+'</td>';
		                  res = res + '<td style="text-align: center;">'+Number(obj[i].data[1])+'</td>';
		                  res = res + '<td style="text-align: center;">'+obj[i].data[0]+'</td>';
		                  res = res + '</tr>';
	                  }
	                  if(existCconcurrent){
	                  	return res;
	                  }else{
	                	  return "";
	                  }
	            }
	          },
			series : series
		};
	
	myChart.setOption(option);    //载入图表
}
