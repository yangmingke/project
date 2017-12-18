function queryData(sid){
	$.post("/developerAccount/queryTraffic",{"sid":sid,"date":$('#dateTime').val()},function(data){
		createMap(data);
	});
}

function createMap(data){
	var timeCoordinate =new Array(0,1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23);//时间横坐标轴
	var trafficOutList = new Array(0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0);
	var trafficInList = new Array(0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0);
	var totalList = new Array(0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0);
	var feeList = new Array(0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0);
	var totalDayTraffic = 0;
	var totalDayFee = 0;
	
	for(var i in data.developerTrafficList){
		var hour = data.developerTrafficList[i].hour;
		var trafficOut = data.developerTrafficList[i].traffic_out;
		var trafficIn = data.developerTrafficList[i].traffic_in;
		var total = data.developerTrafficList[i].total;
		var fee = data.developerTrafficList[i].fee;
		
		trafficOutList[hour]=trafficOut;
		trafficInList[hour]=trafficIn;
		totalList[hour]=total;
		feeList[hour]=fee;
		totalDayTraffic = totalDayTraffic + total;
		totalDayFee = totalDayFee + fee;
	}
	$('#totalDayTraffic').text('日总流量:' + totalDayTraffic +'M');
	$('#totalDayFee').text('日总费用:' + totalDayFee +'元');
	//画统计图
	var myChart = echarts.init(document.getElementById('main'));
	
	option = {
		    tooltip: {
		        trigger: 'axis'
		    },
		    toolbox: {
		        feature: {
		            dataView: {show: true, readOnly: false},
		            restore: {show: true},
		            saveAsImage: {show: true}
		        }
		    },
		    legend: {
		        data:['入流量','出流量','总流量','费用'],
		        selected:{
		        	'入流量':false,
		        	'出流量':false
		        },
			    x: 'left',
			    padding: [10, 20,0,20]
		    },
		    xAxis: [
		        {
		            type: 'category',
		            axisTick: {
		                alignWithLabel: true
		            },
		            data: timeCoordinate
		        }
		    ],
		    yAxis: [
		        {
		            type: 'value',
		            name: '流量',
		            position: 'left',
		            axisLine : {onZero: true},
		            axisLabel: {
		                formatter: '{value} MB'
		            }
		        },
		        {
		            type: 'value',
		            name: '费用',
		            position: 'right',
		            axisLine : {onZero: true},
		            axisLabel: {
		                formatter: '{value} 元'
		            }
		        }
		    ],
		    series: [
		       {
		            name:'入流量',
		            type:'line',
		            yAxisIndex: 0,
//		            animation:false,
		            data:trafficInList
		        },
		        {
		            name:'出流量',
		            type:'line',
		            yAxisIndex: 0,
//		            animation:false,
		            data:trafficOutList
		        },
		        {
		            name:'总流量',
		            type:'line',
		            yAxisIndex: 0,
//		            animation:false,
		            data:totalList
		        },
		        {
		            name:'费用',
		            type:'line',
		            yAxisIndex: 1,
		            connectNulls:true,
//		            animation:false,
		            data:feeList
		        }
		    ]
		};

	myChart.setOption(option);    //载入图表
}
