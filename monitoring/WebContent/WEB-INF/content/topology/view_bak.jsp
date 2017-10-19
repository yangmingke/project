<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/common/taglibs.jsp"%>
<html>
<head>
	<title>网络拓扑结构图</title>
	<meta name="apple-mobile-web-app-capable" content="no">
	<meta name="apple-mobile-web-app-status-bar-style" content="black">
	<meta name="viewport" content="width=device-width,initial-scale=0.69,user-scalable=yes,maximum-scale=1.00">
	
	<script type="text/javascript" src="${ctx}/js/topology/jquery-1.7.1.min.js"></script>
	<script type="text/javascript" src="${ctx}/js/topology/jquery.backgroundPosition.js"></script>
	<script type="text/javascript" src="${ctx}/js/topology/jquery.placeholder.min.js"></script>
	<script type="text/javascript" src="${ctx}/js/topology/jquery.ui.1.8.17.js"></script>
	<script type="text/javascript" src="${ctx}/js/topology/jquery.ui.select.js"></script>
	<script type="text/javascript" src="${ctx}/js/topology/jquery.ui.spinner.js"></script>
	<script type="text/javascript" src="${ctx}/js/topology/superfish.js"></script>
	<script type="text/javascript" src="${ctx}/js/topology/supersubs.js"></script>
	<script type="text/javascript" src="${ctx}/js/topology/jquery.datatables.js"></script>
	<script type="text/javascript" src="${ctx}/js/topology/fullcalendar.min.js"></script>
	<script type="text/javascript" src="${ctx}/js/topology/jquery.smartwizard-2.0.min.js"></script>
	<script type="text/javascript" src="${ctx}/js/topology/pirobox.extended.min.js"></script>
	<script type="text/javascript" src="${ctx}/js/topology/jquery.tipsy.js"></script>
	<script type="text/javascript" src="${ctx}/js/topology/jquery.elastic.source.js"></script>
	<script type="text/javascript" src="${ctx}/js/topology/jquery.jBreadCrumb.1.1.js"></script>
	<script type="text/javascript" src="${ctx}/js/topology/jquery.customInput.js"></script>
	<script type="text/javascript" src="${ctx}/js/topology/jquery.validate.min.js"></script>
	<script type="text/javascript" src="${ctx}/js/topology/jquery.metadata.js"></script>
	<script type="text/javascript" src="${ctx}/js/topology/jquery.filestyle.mini.js"></script>
	<script type="text/javascript" src="${ctx}/js/topology/jquery.filter.input.js"></script>
	<!-- <script type="text/javascript" src="${ctx}/js/topology/highcharts.js"></script> -->
    <script type="text/javascript" src="${ctx}/js/topology/exporting.js"></script>
	<script type="text/javascript" src="${ctx}/js/topology/echarts.js"></script>
	<script type="text/javascript" src="${ctx}/js/topology/jquery.graphtable-0.2.js"></script>
	<script type="text/javascript" src="${ctx}/js/topology/jquery.wysiwyg.js"></script>
	<script type="text/javascript" src="${ctx}/js/topology/wysiwyg.image.js"></script>
	<script type="text/javascript" src="${ctx}/js/topology/wysiwyg.link.js"></script>
	<script type="text/javascript" src="${ctx}/js/topology/wysiwyg.table.js"></script>
	<script type="text/javascript" src="${ctx}/js/topology/wysiwyg.rmFormat.js"></script>
	<script type="text/javascript" src="${ctx}/js/topology/costum.js"></script>
</head>
<body menuId="2">
<!--Action boxes-->
  <div class="container-fluid">
    <hr>
    <div class="section">
		<div class="box">
			<div class="content">						
				<div id="main" class="main" style="height:500px;"></div>
			</div>
		</div>
	</div>
  </div>
  
  <script type="text/javascript">
        // 路径配置
        require.config({
            paths: {
                echarts: '${ctx}/js/topology'
            }
        });

        // 使用
        require(
            [
                'echarts',
                'echarts/chart/map' // 使用柱状图就加载bar模块，按需加载
            ],
            function (ec) {
               // 基于准备好的dom，初始化echarts图表
               var myChart = ec.init(document.getElementById('main')); 
               
               //初始化参数
			   var option = initOption();
               myChart.setOption(option);
               
               var nodeList={};//所有节点
               
               //加载地图
               loadData(option,myChart,nodeList);
               
               //markPoint单击事件
               var ecConfig = require('echarts/config'); 
			   myChart.on(ecConfig.EVENT.CLICK, function(param){
			   	getBestPath(param,myChart,option,nodeList);
			   });
            }
        );
        
        var selectPoint = [];//选择的点
        
        //获取最短路径
        function getBestPath(param,myChart,option,nodeList){
        	//删除显示的最短路径
        	if(option.series.length == 4){
        		option.series.pop();
        		myChart.setOption(option,true);
        	}
        	
        	if(param.data.type == 0){//保证点击的是点
        		if(selectPoint.length == 1){//第二次点击
	        		$.ajax({
						type:'post',
						url:'${ctx}/network/getBestPath',
						data:'src_sr_id='+selectPoint[0]+'&dst_sr_id='+param.data.id,
						async:false,
						datatype:'json',
						error:function(){}, //非必须
						success:function(data){
							if(data.code == 1){
								var line = [];
								if(data.bestPath && data.bestPath.length > 0){
									for(var i=0;i<data.bestPath.length;i++){
										if(nodeList){
											line.push([{geoCoord:nodeList[data.bestPath[i].src_sr_id]},{geoCoord:nodeList[data.bestPath[i].dst_sr_id]}]);
										}
									}
									var bestPath = {name:'最短路径',type: 'map',mapType: 'china',data:[],markLine: {itemStyle : {normal: {borderWidth:2,borderColor:'red'}},data:[]}};
									bestPath.markLine.data = line;
									
									option.series.push(bestPath);
									myChart.setOption(option);
								}
							}else{
								alert(data.msg);
							}
						}
					});
	        		
	        		//清空
	        		selectPoint.splice(0,selectPoint.length);
	        	}else{//第一次点击保存ID
	        		selectPoint.push(param.data.id);
	        	}
        	}
        }
        
        //初始化参数
        function initOption(){
        	var option = {
        		color: ['#ff7f50','#32cd32','#6495ed'],
				title : {
					text: 'SR节点拓扑图',
					x:'center'
				},
    			tooltip : {
					trigger: 'item',
					formatter:function(param){
						var msg = showNetworkData(param);
						if(msg != '-1'){
							return msg;
						}else{
							return '';
						}
					}
				},
				legend: {
					orient: 'vertical',
					     x:'left',
					  data:['接入节点','中继节点','核心节点']
				},
				toolbox: {
					show : true,
					orient : 'vertical',
					x: 'right',
					y: 'center',
					feature : {
						mark : {show: true},
					    dataView : {show: true, readOnly: false},
					    restore : {show: true},
					    saveAsImage : {show: true}
					}
				},
			    series : [
			        {
					 	name: '接入节点',
					    type: 'map',
					    mapType: 'china',
					    hoverable: false,
					    roam:true,
					    data : [],
					    markPoint : {
					    	symbol:'circle',
					    	symbolSize: 2,       // 标注大小，半宽（半径）参数，当图形为方向或菱形则总宽度为symbolSize * 2
					        itemStyle: {
						        normal: {
							        borderWidth: 1,            // 标注边线线宽，单位px，默认为1
							        label: {
							            show: false
							        }
						         },
						         emphasis: {
						            borderWidth: 5,
						            label: {
						            	show: false
						            }
						          }
					          },
					          data : []
					      },
					    markLine : {
					    	symbol: ['circle', 'circle'],
					    	symbolSize : 1, 
					    	smooth:true,
							itemStyle : {
								normal: {
			                        borderWidth:1,
			                        label: {
			                            show: false
			                        },
			                        lineStyle: {
			                            type: 'solid'
			                        }
                    			}
							},
							data : []
						}		            
					},
					{
					 	name: '中继节点',
					    type: 'map',
					    mapType: 'china',
					    hoverable: false,
					    roam:true,
					    data : [],
					    markPoint : {
					    	symbol:'circle',
					    	symbolSize: 2,       // 标注大小，半宽（半径）参数，当图形为方向或菱形则总宽度为symbolSize * 2
					        itemStyle: {
						        normal: {
							        borderWidth: 1,            // 标注边线线宽，单位px，默认为1
							        label: {
							            show: false
							        }
						         },
						         emphasis: {
						            borderWidth: 5,
						            label: {
						            	show: false
						            }
						          }
					          },
					          data : []
					      },
					      markLine : {
					      	symbol: ['circle', 'circle'],
					    	symbolSize : 1, 
					    	smooth:true,
							itemStyle : {
								normal: {
			                        borderWidth:1,
			                        label: {
			                            show: false
			                        },
			                        lineStyle: {
			                            type: 'solid',
			                            shadowBlur: 10
			                        }
                    			}
							},
							data : []
						 }		            
					},
					{
					 	name: '核心节点',
					    type: 'map',
					    mapType: 'china',
					    hoverable: false,
					    roam:true,
					    data : [],
					    markPoint : {
					    	symbol:'circle',
					    	symbolSize: 2,       // 标注大小，半宽（半径）参数，当图形为方向或菱形则总宽度为symbolSize * 2
					        itemStyle: {
						        normal: {
							        borderWidth: 1,            // 标注边线线宽，单位px，默认为1
							        label: {
							            show: false
							        }
						         },
						         emphasis: {
						            borderWidth: 4,
						            label: {
						            	show: false
						            }
						          }
					          },
					          data : []
					      },
					      markLine : {
					      	symbol: ['circle', 'circle'],
					    	symbolSize : 1,
					    	smooth:true, 
							itemStyle : {
								normal: {
			                        borderWidth:1,
			                        label: {
			                            show: false
			                        },
			                        lineStyle: {
			                            type: 'solid',
			                            shadowBlur: 10
			                        }
                    			}
							},
							data : []
						 }			            
					}
				]
			};
			
			return option;
        }
        
        function loadData(option,myChart,nodeList){
            var jrMarkLine = [];//接入节点
            var zjMarkLine = [];//中继节点
            var hxMarkLine = [];//核心节点
            var jrMarkPoint = [];//接入节点
            var zjMarkPoint = [];//中继节点
            var hxMarkPoint = [];//核心节点
            $.ajax({
				type:'post',
				url:'${ctx}/network/getTopology',
				data:'',
				async:false,
				datatype:'json',
				error:function(){}, //非必须
				success:function(data){//非必须
					if(data.code == 1){
						if(data.nodes){//标记所有SR节点
							var nodes = data.nodes;
							for(var i=0;i<nodes.length;i++){
								//将节点放在集合中
								nodeList[nodes[i].sr_id] = [nodes[i].lng,nodes[i].lat];
								if(nodes[i].level == 0){//接入节点
									jrMarkPoint.push({name:nodes[i].sr_id,
										geoCoord : [nodes[i].lng,nodes[i].lat],type:0,id:nodes[i].sr_id,isLoaded:false,msg:''
									});
								}else if(nodes[i].level == 1){//中继节点
									zjMarkPoint.push({name:nodes[i].sr_id,
										geoCoord : [nodes[i].lng,nodes[i].lat],type:0,id:nodes[i].sr_id,isLoaded:false,msg:''
									});
								}else{
									hxMarkPoint.push({name:nodes[i].sr_id,
										geoCoord : [nodes[i].lng,nodes[i].lat],type:0,id:nodes[i].sr_id,isLoaded:false,msg:''
									});
								}
							}
						}else{
							alert("SR节点信息为空!");
						}
								
						//SR节点拓扑图
						if(data.topologyList && data.topologyList.length>=1){
							var topologyList = data.topologyList;
							for(var i=0;i<topologyList.length;i++){
								if(topologyList[i].level == 0){//接入节点
									jrMarkLine.push([{geoCoord:[topologyList[i].sr_lng,topologyList[i].sr_lat],name:topologyList[i].src_sr_id,type:1,isLoaded:false,msg:''},{geoCoord:[topologyList[i].dst_sr_lng,topologyList[i].dst_sr_lat],name:topologyList[i].dst_sr_id,type:1,isLoaded:false,msg:''}]);
								}else if(topologyList[i].level == 1){//中继节点
									zjMarkLine.push([{geoCoord:[topologyList[i].sr_lng,topologyList[i].sr_lat],name:topologyList[i].src_sr_id,type:1,isLoaded:false,msg:''},{geoCoord:[topologyList[i].dst_sr_lng,topologyList[i].dst_sr_lat],name:topologyList[i].dst_sr_id,type:1,isLoaded:false,msg:''}]);
								}else{//核心节点
									hxMarkLine.push([{geoCoord:[topologyList[i].sr_lng,topologyList[i].sr_lat],name:topologyList[i].src_sr_id,type:1,isLoaded:false,msg:''},{geoCoord:[topologyList[i].dst_sr_lng,topologyList[i].dst_sr_lat],name:topologyList[i].dst_sr_id,type:1,isLoaded:false,msg:''}]);
								}
								
							}
						}
					}else{
						alert(data.msg);
					}
				}
				});
            
			//加载SR节点
			option.series[0].markPoint.data = jrMarkPoint;
			option.series[1].markPoint.data = zjMarkPoint;
			option.series[2].markPoint.data = hxMarkPoint;
			
			//SR关系图
			option.series[0].markLine.data = jrMarkLine;
			option.series[1].markLine.data = zjMarkLine;
			option.series[2].markLine.data = hxMarkLine;
			
        	// 为echarts对象加载数据 
            myChart.setOption(option);
        }
        
        //显示网络数据
        function showNetworkData(param){
        	//是否已经加载过
        	if(param.data.isLoaded){
				return param.data.msg;
			}
        	var name=param.name;
			var src_sr_id = -1;
			var dst_sr_id = -1;
			var msg = '';
			if(param.data.type == 0){
				src_sr_id = param.data.id;
			}else if(param.data.type == 1){
				var ids = name.split(">");
					src_sr_id = ids[0];
					dst_sr_id = ids[1];
			}else{
				return '-1';
			}
			$.ajax({
				type:'post',
				url:'${ctx}/network/getNetworkData',
				data:'src_sr_id='+src_sr_id+'&dst_sr_id='+dst_sr_id,
				async:false,
				datatype:'json',
				error:function(){}, //非必须
				success:function(data){
					if(data.code == 1){
						if(param.data.type == 1){
							msg = name+"的丢包率："+data.lost_rate + ",平均延时:" + data.average_delay;
						}else{
							msg = name+"的吞吐量："+data.throughput;
						}
						param.data.msg = msg;
						param.data.isLoaded = true;
					}else{
						alert(data.msg);
					}
				}
			});
			
			return msg;
        }
    </script>
</body>
</html>