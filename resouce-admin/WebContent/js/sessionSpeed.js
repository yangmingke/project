function queryData(sid){
	$('#error').hide();
	if($('#sessionID').val() == ""){
		$('#error').text("请输入会话ID");
		$('#error').show();
		return false;
	}
	$.post("/operation/querySessionSpeed",
			{"routeDoamin":$('#routeDoamin').val(),"routePolicy":$('#routePolicy').val(),"routeId":$('#routeId').val(),"routeType":$('#routeType').val(),"sessionID":$('#sessionID').val()},
			function(data){
				var json = eval("("+data+")");
				$('#path').text(json.path == undefined ? "" : json.path);
				createMap(json.nodeSpeedList);
	});
}

function createMap(result){
	var coordinate =null;//横坐标轴
	var legendData = new Array();
	var seriesData = new Array();
	for(var ip in result){
		legendData.push(ip);
		seriesData.push({
            name:ip,
            type:'line',
            connectNulls:true,
//            animation:false,
            data:result[ip]
        });
		if(coordinate == null){
			coordinate = new Array(result[ip].length);
			for(var i = 0; i <  coordinate.length; i++){
				coordinate[i]=i+1;
			}
		}
	}
	
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
		        data:legendData,
			    x: 'left',
			    padding: [10, 20,0,20]
		    },
		    dataZoom: [
	           {
	               show: true,
	               realtime: true,
	               start: 0,
	               end: 100
	           }
	       ],
		    xAxis: [
		        {
		            type: 'category',
		            axisTick: {
		                alignWithLabel: true
		            },
		            data: coordinate
		        }
		    ],
		    yAxis: [
		        {
		            type: 'value',
		            name: '速率',
		            position: 'left',
		            axisLine : {onZero: true},
		            axisLabel: {
		                formatter: '{value} KB/S'
		            }
		        }
		    ],
		    series: seriesData
		};

	myChart.setOption(option);    //载入图表
}
