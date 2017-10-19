$(function(){
	getData();
});

function getData(){
	var domain = $('#domain').val();
	var netLevel = $('#netLevel').val();
	$.post("/route/linkStatusNew",{"domain":domain,"netLevel":netLevel},function(data){
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
	
	/**
	 * 返回[出发地，目的地，[出发坐标，目的坐标]]List
	 */
	var convertData = function (line) {
		var res = [];
        //城市坐标
        var fromCoord = [line.fromLongitude,line.fromLatitude];
        var toCoord = [line.toLongitude,line.toLatitude];
        if (fromCoord && toCoord) {
            res.push({
                coords: [fromCoord, toCoord],
                connect: line
            });
        }
	    return res;
	};
	
	function opt(line){
		var value = line.value;//根于其值选定颜色
		if(value < 600){
			return 0;
		}else if(value < 1200){
			return 1;
		}else if(value < 1800){
			return 2;
		}else if(value < 2400){
			return 3;
		}else if(value < 3000){
			return 4;
		}else{
			return 5;
		}
		
	}
	var color = ['#BBFF00', '#a6c84c', '#EEFFBB', '#FFFF00', '#FF7744', '#FF3333'];
	var level = ['[0 ~ 600) 非常好', '[600 ~ 1200) 好', '[1200 ~ 1800)  良好','[1800 ~ 2400)  一般','[2400 ~ 3000)  差','[3000 ~ 5000]  很差'];
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
    
    var lines = data.lines;
	for(var i in lines){
		var lineList = lines[i];
		for(var j in lineList){
			var line = lineList[j]
			series.push(
		    //移动飞机和路线（也可以自定义图案）
		    {
		    	name: level[opt(line)],
		        type: 'lines', //图像类型
		        zlevel: 2, //图层等级
		        lineStyle: {
		            normal: {
		                color: color[opt(line)],//颜色
		                width: 0.2,//宽度
		                opacity: 0.5,//透明度
		                curveness: j/20//曲度
		            }
		        },
		        effect: {//线的特效
		            show: true,//是否显示
		            period: 6,  //6S出现一次
		            trailLength: 0.6,//特效尾迹
		            color: '#fff',
		            symbolSize: 3
		        },
		        data: convertData(line)
		    });
		}
	}
    
	
	option = {
	    backgroundColor: '#404a59',//背景色，默认无背景。
	    title : {
	        text: '链路状态图',//主标题文本，支持使用 \n 换行。
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
              if(obj.seriesType == 'lines'){//线提示框
            	  var connect = obj.data.connect;
                  var res = '<div class="">'
                    + '</div>';

                  var res = res + '<table class="hz_tip_table"><tr><th>城市</th><th>IP</th><th>COST值</th></tr>';
                  res = res + '<tr>';
                  res = res + '<td>'+connect.city+'</td>';
                  res = res + '<td>'+connect.ip+'</td>';
                  res = res + '<td style="text-align: center;">'+connect.value+'</td>';
                  res = res + '</tr>';
                  return res;
              }
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
          legend: {//以通过点击图例控制哪些系列不显示
  	        orient: 'vertical',//图例列表的布局朝向。horizontal'，'vertical'
  	        top: 'top',
  	        left: 'left',
  	        data: [{
  			    name: level[0],
  			    // 强制设置图形为圆。
  			    icon: 'roundRect',
  			    // 设置文本为红色
  			    textStyle: {
  			        color: color[0]
  			    }
  			},{
  			    name: level[1],
  			    // 强制设置图形为圆。
  			    icon: 'roundRect',
  			    // 设置文本为红色
  			    textStyle: {
  			        color: color[1]
  			    }
  			},{
  			    name: level[2],
  			    // 强制设置图形为圆。
  			    icon: 'roundRect',
  			    // 设置文本为红色
  			    textStyle: {
  			        color: color[2]
  			    }
  			},{
  			    name: level[3],
  			    // 强制设置图形为圆。
  			    icon: 'roundRect',
  			    // 设置文本为红色
  			    textStyle: {
  			        color: color[3]
  			    }
  			},{
  			    name: level[4],
  			    // 强制设置图形为圆。
  			    icon: 'roundRect',
  			    // 设置文本为红色
  			    textStyle: {
  			        color: color[4]
  			    }
  			},{
  			    name: level[5],
  			    // 强制设置图形为圆。
  			    icon: 'roundRect',
  			    // 设置文本为红色
  			    textStyle: {
  			        color: color[5]
  			    }
  			}],
  	        textStyle: {
  	            color: '#fff'
  	        },
  	        selectedMode: 'multiple'//'single' 或者 'multiple'或者'true ' 或者 'false '
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
//	                areaColor: '#2a333d'
	            	areaColor: '#323c48',
	                borderColor: '#404a59'
	            }
	        }
	    },
	    series: series
	};
	 myChart.setOption(option);    //载入图表
}