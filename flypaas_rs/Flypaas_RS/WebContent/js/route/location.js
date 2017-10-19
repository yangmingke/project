$(function(){
	getData();
});

function getData(){
	$.post("/route/locationNew",{},function(data){
		var json = eval("("+data+")");
		createMap(json);
	});
}

function createMap(data){
	var myChart = echarts.init(document.getElementById('main'));

	// 指定图表的配置项和数据
	var cityNode=[];
	for(var id in data.effectScatter){
		var rtpp = data.effectScatter[id];
		cityNode.push(rtpp);
	}
	
	
	var color = ['#BBFF00', '#a6c84c', '#EEFFBB', '#FFFF00', '#FF7744', '#FF3333'];
	var series = [];
    series.push(
    {
        name: 'effectScatter',
        type: 'effectScatter',//气泡图（小圈圈）
        coordinateSystem: 'geo',//使用地理坐标系
        zlevel: 2,
        rippleEffect: {
            brushType: 'fill'//波纹的绘制方式，可选 'stroke' 和 'fill'
        },
        label: {//图形上的文本标签，可用于说明图形的一些数据信息
            normal: {
            	textStyle:{
            		color:'#a6c84c'
            	},
                show: false,
                position: 'right',//城市名称位置
                formatter: '{b}' //{a}、{b}、{c}，分别表示系列名，数据名，数据值
            }
        },
        symbolSize: 3,
        itemStyle: {
            normal: {//normal 是图形在默认状态下的样式；emphasis 是图形在高亮状态下的样式，比如在鼠标悬浮或者图例联动高亮时。
                color: '#BBFF00'
            }
        },
        data: cityNode.map(function (dataItem) {
            return {
            	name: dataItem.cityName,
                //泡泡大小
                value: [dataItem.longitude,dataItem.latitude].concat(50),
                nodes : dataItem
            };
        })
    });
    
	
	option = {
	    backgroundColor: '#404a59',//背景色，默认无背景。
	    title : {
	        text: '节点拓扑图',//主标题文本，支持使用 \n 换行。
	        subtext: '',//副标题文本，支持使用 \n 换行
	        left: 'center',
	        textStyle : {//主标题文字的颜色。
	            color: '#fff'
	        }
	    },
	    tooltip: {//提示框
	    	trigger: 'item',
            padding: 10,
            borderWidth: 1,
            formatter: function (obj) {
              var nodes = obj.data.nodes;
              var res = '<div class="hz_tip_title">'
                + obj.data.name
                + '</div>';

              var res = res + '<table class="hz_tip_table"><tr><th>名称</th><th>IP</th><th>运营商</th></tr>';
	          res = res + '<tr>';
	          res = res + '<td>'+nodes.name+'</td>';
	          res = res + '<td>'+nodes.ip+'</td>';
	          res = res + '<td>'+nodes .operatorName+'</td>';
              return res;
            }
          },
	    geo: {
	        map: 'world',
	        label: {
	            emphasis: {
	                show: false
	            }
	        },
	        roam: true,//是否开启鼠标缩放和平移漫游
	        itemStyle: {
	            normal: {
	                areaColor: '#323c48',
	                borderColor: '#404a59'
	            },
	            emphasis: {
	            	areaColor: '#323c48',
	                borderColor: '#404a59'
	            }
	        }
	    },
	    series: series
	};
	 myChart.setOption(option);    //载入图表
}