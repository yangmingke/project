function getData(){
	var routeDomain = $('#routeDomain').val();
	var srcIP = $('#srcIP').val();
	var destIP = $('#destIP').val();
	var srcIPReg = /^(25[0-5]|2[0-4][0-9]|[0-1]{1}[0-9]{2}|[1-9]{1}[0-9]{1}|[1-9])\.(25[0-5]|2[0-4][0-9]|[0-1]{1}[0-9]{2}|[1-9]{1}[0-9]{1}|[1-9]|0)\.(25[0-5]|2[0-4][0-9]|[0-1]{1}[0-9]{2}|[1-9]{1}[0-9]{1}|[1-9]|0)\.(25[0-5]|2[0-4][0-9]|[0-1]{1}[0-9]{2}|[1-9]{1}[0-9]{1}|[0-9])$/g;
	var destIPReg = /^(25[0-5]|2[0-4][0-9]|[0-1]{1}[0-9]{2}|[1-9]{1}[0-9]{1}|[1-9])\.(25[0-5]|2[0-4][0-9]|[0-1]{1}[0-9]{2}|[1-9]{1}[0-9]{1}|[1-9]|0)\.(25[0-5]|2[0-4][0-9]|[0-1]{1}[0-9]{2}|[1-9]{1}[0-9]{1}|[1-9]|0)\.(25[0-5]|2[0-4][0-9]|[0-1]{1}[0-9]{2}|[1-9]{1}[0-9]{1}|[0-9])$/g;
	if(!srcIPReg.test(srcIP)){
		$('#error').text("请输入正确的源IP地址");
		$('#error').show();
		return false;
	}
	if(!destIPReg.test(destIP)){
		$('#error').text("请输入正确的目的IP地址");
		$('#error').show();
		return false;
	}
	$('#error').hide();
	$.post("/route/transmit",{"domain":routeDomain,"srcIP":srcIP,"destIP":destIP},function(data){
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
	var convertData = function (from,to) {
		var res = [];
        //城市坐标
        var fromCoord = [from.longitude,from.latitude];
        var toCoord = [to.longitude,to.latitude];
        if (fromCoord && toCoord) {
            res.push({
                coords: [fromCoord, toCoord],
            	connect: [from,to]
            });
        }
	    return res;
	};
	
	var series = [];
	series.push(//初始点
		    {
		        name: 'src',
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
		                color: 'orange'
		            }
		        },
		        data: [{
	            	name: data.srcIpInfo.cityName,
	                //泡泡大小
	                value: [data.srcIpInfo.longitude,data.srcIpInfo.latitude].concat(50),
	                nodes : data.srcIpInfo
	            }]
		    });
	series.push(//目的点
		    {
		        name: 'dest',
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
		                color: 'red'
		            }
		        },
		        data: [{
	            	name: data.destIpInfo.cityName,
	                //泡泡大小
	                value: [data.destIpInfo.longitude,data.destIpInfo.latitude].concat(50),
	                nodes : data.destIpInfo
	            }]
		    });
    series.push(//路由点
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
    
    for(var index = 0; index < data.routeList.length; index++){
    	var lines = data.routeList[index];
    	var startNode = lines[0].indexOf(",") > 0 ?  lines[0].split(",")[0]: lines[0];//首节点为两个内网节点
    	var endNode;
    	if(lines[lines.length - 1].indexOf(",") > 0){//最后一个值为两个内网节点
    		endNode = lines[lines.length - 1].split(",")[1];
    		series.push(//连接两个内网节点
				    //移动飞机和路线（也可以自定义图案）
				    {
				    	name: 'route'+ (index+1),
				        type: 'lines', //图像类型
				        zlevel: 2, //图层等级
				        lineStyle: {
				            normal: {
				                color: 'yellow',//颜色
				                width: 1,//宽度
				                opacity: 0.1,//透明度
				                curveness: 0//曲度
				            }
				        },
				        effect: {//线的特效
				            show: true,//是否显示
				            period: 2,  //2S出现一次	
				            trailLength: 0.7,//特效尾迹
				            color: '#fff',
				            symbolSize: 3
				        },
				        data: convertData(data.effectScatter[lines[lines.length - 1].split(",")[0]],data.effectScatter[lines[lines.length - 1].split(",")[1]])
				    });
    	}else{
    		endNode = lines[lines.length - 1];
    	}
	    series.push(//原点的连线
			    //移动飞机和路线（也可以自定义图案）
			    {
			    	name: 'route'+ (index+1),
			        type: 'lines', //图像类型
			        zlevel: 2, //图层等级
			        lineStyle: {
			            normal: {
			                color: 'orange',//颜色
			                width: 1,//宽度
			                opacity: 0.1,//透明度
			                curveness: 0//曲度
			            }
			        },
			        effect: {//线的特效
			            show: true,//是否显示
			            period: 2,  //2S出现一次	
			            trailLength: 0.7,//特效尾迹
			            color: '#fff',
			            symbolSize: 3
			        },
			        data: convertData(data.srcIpInfo,data.effectScatter[startNode])
			    });
	    series.push(//目的点连线
			    //移动飞机和路线（也可以自定义图案）
			    {
			    	name: 'route'+ (index+1),
			        type: 'lines', //图像类型
			        zlevel: 2, //图层等级
			        lineStyle: {
			            normal: {
			                color: 'red',//颜色
			                width: 1,//宽度
			                opacity: 0.1,//透明度
			                curveness: 0//曲度
			            }
			        },
			        effect: {//线的特效
			            show: true,//是否显示
			            period: 2,  //2S出现一次	
			            trailLength: 0.7,//特效尾迹
			            color: '#fff',
			            symbolSize: 3
			        },
			        data: convertData(data.effectScatter[endNode],data.destIpInfo)
			    });
		for(var i = 0; i < lines.length - 1; i++){
			var fromData = data.effectScatter[lines[i]];
			var toData = data.effectScatter[lines[i+1]];
			if(lines[i+1].indexOf(",") > 0){//最后一个值为两个内网节点
				toData = data.effectScatter[lines[i+1].split(",")[0]];
			}
			if(lines[i].indexOf(",") > 0){//首个值为两个内网节点
				fromData = data.effectScatter[lines[i].split(",")[1]];
				series.push(//内网连接
					    //移动飞机和路线（也可以自定义图案）
					    {
					    	name: 'route'+ (index+1),
					        type: 'lines', //图像类型
					        zlevel: 2, //图层等级
					        lineStyle: {
					            normal: {
					                color: 'yellow',//颜色
					                width: 1,//宽度
					                opacity: 0.1,//透明度
					                curveness: 0//曲度
					            }
					        },
					        effect: {//线的特效
					            show: true,//是否显示
					            period: 2,  //2S出现一次	
					            trailLength: 0.7,//特效尾迹
					            color: '#fff',
					            symbolSize: 3
					        },
					        data: convertData(data.effectScatter[lines[i].split(",")[0]],data.effectScatter[lines[i].split(",")[1]])
					    });
			}
			series.push(
			    //移动飞机和路线（也可以自定义图案）
			    {
			    	name: 'route'+ (index+1),
			        type: 'lines', //图像类型
			        zlevel: 2, //图层等级
			        lineStyle: {
			            normal: {
			                color: '#a6c84c',//颜色
			                width: 1,//宽度
			                opacity: 0.1,//透明度
			                curveness: 0//曲度
			            }
			        },
			        effect: {//线的特效
			            show: true,//是否显示
			            period: 2,  //2S出现一次	
			            trailLength: 0.7,//特效尾迹
			            color: '#fff',
			            symbolSize: 3
			        },
			        data: convertData(fromData,toData)
			    });
		}
    }
	
	
	option = {
	    backgroundColor: '#404a59',//背景色，默认无背景。
	    title : {
	        text: '路由传递图',//主标题文本，支持使用 \n 换行。
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
            	  var res = '<div class="hz_tip_title">'
                      + connect[0].cityName + " &rarr; " +  connect[1].cityName + "（" + obj.seriesName + "）"
                      + '</div>';
                  var res = res + '<table class="hz_tip_table">'
	              res = res + '<tr>';
	              res = res + '<td>'+connect[0].ip+ " &rarr; " + connect[1].ip + '</td>';
	              res = res + '</tr>';
                  return res;
              }
              var nodes = obj.data.nodes;
              var res = '<div class="hz_tip_title">'
                + obj.data.name
                + '</div>';
              var res = res + '<table class="hz_tip_table"><tr><th>名称</th><th>IP</th><th>运营商</th></tr>';
	          res = res + '<tr>';
	          res = res + '<td>'+nodes.name+'</td>' + '<td>'+nodes.ip+'</td>' + '<td>'+nodes.operatorName+'</td>';
	          res = res + '</tr>';
              return res;
            }
          },
          legend: {//以通过点击图例控制哪些系列不显示
  	        orient: 'vertical',//图例列表的布局朝向。horizontal'，'vertical'
  	        top: 'top',
  	        left: 'left',
  	        data: [{
  			    name: "route1",
  			    // 强制设置图形为圆。
  			    icon: 'roundRect',
  			    // 设置文本颜色
  			    textStyle: {
  			        color: '#a6c84c'
  			    }
  			},{
  			    name: "route2",
  			    // 强制设置图形为圆。
  			    icon: 'roundRect',
  			    // 设置文本颜色
  			    textStyle: {
  			        color: '#a6c84c'
  			    }
  			},{
  			    name: "route3",
  			    // 强制设置图形为圆。
  			    icon: 'roundRect',
  			    // 设置文本颜色
  			    textStyle: {
  			        color: '#a6c84c'
  			    }
  			},{
  			    name: "route4",
  			    // 强制设置图形为圆。
  			    icon: 'roundRect',
  			    // 设置文本颜色
  			    textStyle: {
  			        color: '#a6c84c'
  			    }
  			},{
  			    name: "route5",
  			    // 强制设置图形为圆。
  			    icon: 'roundRect',
  			    // 设置文本颜色
  			    textStyle: {
  			        color: '#a6c84c'
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