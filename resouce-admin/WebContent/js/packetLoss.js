$(function(){
	init();
});
/**
 * 刷新页面 
 */
function ref(){
	setTimeout('init()',30*1000);//30S刷新一次页面
}
function init(){
	$.post("/operation/queryPaketLoss",{"ip":$('#ip').val(),"date":$('#dateTime').val()},function(data){
		var json = eval("("+data+")");
		createMap(json);
		/*if(json.today == json.date){
			ref();
		}*/
	});
}

function queryData(){
	init();
}

function createMap(data){
	$('#dateTime').val(data.date); 
	
	//设置下拉选项
	var options = "";
	for(var i in data.ipList){
		if(data.ipList[i] == data.ip){
			options = options +"<option value = '"+data.ipList[i]+"' selected='selected'>节点："+data.ipList[i]+"</option>";
		}else{
			options = options +"<option value = '"+data.ipList[i]+"'>节点："+data.ipList[i]+"</option>";
		}
	}
	$('#ip').html(options); 
	
	//画统计图
	var myChart = echarts.init(document.getElementById('main'));
	var rateList = data.rate[0]; 
	var countList = data.rate[1]; 
	var time = data.time;
	var rateMax = data.rateMax;
	if(rateMax > 80){
		rateMax = 80;
	}
	var countMax = data.countMax;
	
	var colors = ['#d14a61', '#5793f3'];

	option = {
	    color: colors,
	    tooltip: {
	        trigger: 'axis'
	    },
	    grid: {
	        right: '20%'
	    },
	    toolbox: {
	        feature: {
	            dataView: {show: true, readOnly: false},
	            restore: {show: true},
	            saveAsImage: {show: true}
	        }
	    },
	    legend: {
	        data:['丢包率','丢包次数'],
		    x: 'left',
		    padding: [10, 20,0,20]
	    },
	    xAxis: [
	        {
	            type: 'category',
	            axisTick: {
	                alignWithLabel: true
	            },
	            data: time
	        }
	    ],
	    yAxis: [
	        {
	            type: 'value',
	            name: '丢包率',
	            min: 0,
	            max: (rateMax + 20).toFixed(2),
	            position: 'left',
	            axisLine: {
	                lineStyle: {
	                    color: colors[0]
	                }
	            },
	            axisLabel: {
	                formatter: '{value} %'
	            }
	        },
	        {
	            type: 'value',
	            name: '丢包次数',
	            min: 0,
	            max: Math.round(countMax+0.5),
	            position: 'right',
	            axisLine: {
	                lineStyle: {
	                    color: colors[1]
	                }
	            },
	            axisLabel: {
	                formatter: '{value} 次'
	            }
	        }
	    ],
	    series: [
	        {
	            name:'丢包率',
	            type:'line',
//	            animation:false,
	            data:rateList
	        },
	        {
	            name:'丢包次数',
	            type:'line',
	            yAxisIndex: 1,
//	            animation:false,
	            data:countList
	        }
	    ]
	};
	
	myChart.setOption(option);    //载入图表
}
