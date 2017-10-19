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
	$.post("/operation/queryPaketLossResource",{"destIp":$('#ip').val(),"date":$('#dateTime').val()},function(data){
		var json = eval("("+data+")");
		createMap(json);
		if(json.today == json.date){
//			ref();
		}
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
		if(data.ipList[i] == data.destIp){
			options = options +"<option value = '"+data.ipList[i]+"' selected='selected'>目的IP："+data.ipList[i]+"</option>";
		}else{
			options = options +"<option value = '"+data.ipList[i]+"'>目的IP："+data.ipList[i]+"</option>";
		}
	}
	$('#ip').html(options); 
	var ipList = new Array();
	var packetLossList = new Array();
	var resultList = data.resultList;
	for(var i in resultList){
		ipList.push(resultList[i].src_ip);//ip列表
		packetLossList.push({value:resultList[i].count, name:resultList[i].src_ip});//画饼
	}
	//画统计图
	var myChart = echarts.init(document.getElementById('main'));

	option = {
		    title : {
		        text: 'RTPP-'+data.destIp+' 丢包来源分布统计',
		        subtext: '丢包总计：' +(data.count == undefined ? 0 : data.count)+'次',
		        x:'center'
		    },
		    tooltip : {
		        trigger: 'item',
		        formatter: "{a} <br/>{b} : {c} ({d}%)"
		    },
		    legend: {
		        orient: 'vertical',
		        left: 'left',
		        data: ipList
		    },
		    series : [
		        {
		            name: '源RTPP',
		            type: 'pie',
		            radius : '55%',
		            center: ['50%', '60%'],
		            data:packetLossList,
		            itemStyle: {
		                emphasis: {
		                    shadowBlur: 10,
		                    shadowOffsetX: 0,
		                    shadowColor: 'rgba(0, 0, 0, 0.5)'
		                }
		            }
		        }
		    ]
		};
	
	myChart.setOption(option);    //载入图表
}
