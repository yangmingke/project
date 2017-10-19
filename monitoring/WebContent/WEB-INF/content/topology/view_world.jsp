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
<%-- 	<script type="text/javascript" src="${ctx}/js/topology/jquery.ui.select.js"></script> --%>
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
    <div class="row-fluid">
      <div class="span12">
       		<div class="tabbable" id="tabs-578884">
				<ul class="nav nav-tabs" style="margin: 0px;">
					<li >
						<a href="#panel-125117" data-toggle="tab" onclick="window.location.href='${ctx}/network/topology'">中国</a>
					</li>
					<li class="active">
						<a href="#panel-835071" data-toggle="tab">世界</a>
					</li>
				</ul>
				<div class="tab-content">
					<div class="tab-pane" id="panel-125117" >
						
					</div>
					<div class="tab-pane  active" id="panel-835071">
						 <div class="widget-box" style="margin: 0px;">
					          <div class="widget-title">
					         	 <span class="icon">
					         		 <i class="fa fa-th"></i>
					         	 </span>
								 <div class="search">
										<form method="post" id="nodeForm" action="${ctx}/network/topologyworld">
											<ul> 
											    <li>
											    	<!--<u:date id="start_time" value="${data.start_time}" placeholder="开始时间" params="minDate:'%y-%M-{%d-45} 00:00:00', maxDate:'#F{$dp.$D(\\'end_time\\')||\\'%y-%M-%d %H:%m:%s\\'}', isShowClear:false" />
													<span>至</span>
					            					<u:date id="end_time" value="${data.end_time}" placeholder="结束时间" params="minDate:'#F{$dp.$D(\\'start_time\\')||\\'%y-%M-{%d-45} 00:00:00\\'}', maxDate:'%y-%M-%d %H:%m:%s', isShowClear:false" />
												</li>-->
												<li><input type="text" value="<s:property value="data.end_time"/>" name="end_time" style="width:150px;" disabled="disabled"/></li>
												<!-- <li><input type="submit" value="查 询" /></li> -->
											</ul>
									     </form>
									</div>
								
								
								 
								 
					
					          </div>
							    <div class="section">
									<div class="box">
										<div class="content">						
											<div id="main" class="main" style="height:580px;"></div>
										</div>
									</div>
								</div>
						</div>
					</div>
				</div>
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
               loadPoints(option,myChart,nodeList);
               loadData(option,myChart,nodeList);
               
               //setInterval(function () {
					//loadData(option,myChart,nodeList);
				//}, 10000);
			
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
									var bestPath = {name:'最短路径',type: 'map',mapType: 'world',data:[],markLine: {itemStyle : {normal: {borderWidth:2,borderColor:'red'}},data:[]}};
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
						if(param.data.type == 0 || param.data.type == 1){//点线
							return param.name;
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
					    mapType: 'world',
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
					},
					{
					 	name: '中继节点',
					    type: 'map',
					    mapType: 'world',
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
					},
					{
					 	name: '核心节点',
					    type: 'map',
					    mapType: 'world',
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
					},
					{
					 	name: '',
					    type: 'map',
					    mapType: 'world',
					    hoverable: false,
					    roam:true,
					    data : [],
					    markLine : {
					      	symbol: ['circle', 'circle'],
					    	symbolSize : 1, 
							itemStyle : {
								normal: {
			                        borderWidth:1,
			                        borderColor:'rgba(30,144,255,1)',
			                        borderWidth:1,
			                        label: {
			                            show: false
			                        },
			                        lineStyle: {
			                            type: 'solid',
			                        }
                    			}
							},
							data : []
						 }		            
					},
					{
					 	name: '',
					    type: 'map',
					    mapType: 'world',
					    hoverable: false,
					    roam:true,
					    data : [],
					    markLine : {
					      	symbol: ['circle', 'circle'],
					    	symbolSize : 1,
					    	smooth:true, 
							itemStyle : {
								normal: {
			                        borderWidth:1,
			                        borderColor:'rgba(30,144,255,0.5)',
			                        borderWidth:1,
			                        label: {
			                            show: false
			                        },
			                        lineStyle: {
			                            type: 'solid',
			                        }
                    			}
							},
							data : []
						 }		            
					},
					{
					 	name: '',
					    type: 'map',
					    mapType: 'world',
					    hoverable: false,
					    roam:true,
					    data : [],
					    markLine : {
					      	symbol: ['circle', 'circle'],
					    	symbolSize : 1, 
							itemStyle : {
								normal: {
			                        borderWidth:1,
			                        borderColor:'rgba(255,144,255,1)',
			                        borderWidth:1,
			                        label: {
			                            show: false
			                        },
			                        lineStyle: {
			                            type: 'solid',
			                        }
                    			}
							},
							data : []
						 }		            
					},
					{
					 	name: '',
					    type: 'map',
					    mapType: 'world',
					    hoverable: false,
					    roam:true,
					    data : [],
					    markLine : {
					      	symbol: ['circle', 'circle'],
					    	symbolSize : 1,
					    	smooth:true, 
							itemStyle : {
								normal: {
			                        borderWidth:1,
			                        borderColor:'rgba(255,144,255,0.5)',
			                        borderWidth:1,
			                        label: {
			                            show: false
			                        },
			                        lineStyle: {
			                            type: 'solid',
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
        
        function loadPoints(option,myChart,nodeList){
            var jrMarkPoint = [];//接入节点
            var zjMarkPoint = [];//中继节点
            var hxMarkPoint = [];//核心节点
            $.ajax({
				type:'post',
				url:'${ctx}/node/getNodeList',
				data:$('#nodeForm').serialize(),
				async:false,
				datatype:'json',
				error:function(){}, //非必须
				success:function(data){//非必须
					if(data.code == 1){
						if(data.nodeList){//标记所有SR节点
							var nodes = data.nodeList;
							for(var i=0;i<nodes.length;i++){
								//将节点放在集合中
								nodeList[nodes[i].sr_id] = [nodes[i].lng,nodes[i].lat];
								if(nodes[i].level == 0){//接入节点
									jrMarkPoint.push({name:nodes[i].sr_name+'('+nodes[i].sr_id+'-'+nodes[i].city+')',
										geoCoord : [nodes[i].lng,nodes[i].lat],type:0,id:nodes[i].sr_id,isLoaded:false,msg:''
									});
								}else if(nodes[i].level == 1){//中继节点
									zjMarkPoint.push({name:nodes[i].sr_name+'('+nodes[i].sr_id+'-'+nodes[i].city+')',
										geoCoord : [nodes[i].lng,nodes[i].lat],type:0,id:nodes[i].sr_id,isLoaded:false,msg:''
									});
								}else{
									hxMarkPoint.push({name:nodes[i].sr_name+'('+nodes[i].sr_id+'-'+nodes[i].city+')',
										geoCoord : [nodes[i].lng,nodes[i].lat],type:0,id:nodes[i].sr_id,isLoaded:false,msg:''
									});
								}
							}
						}
					}
					//else{
						//alert(data.msg);
					//}
				}
			});
			
			//加载SR节点
			option.series[0].markPoint.data = jrMarkPoint;
			option.series[1].markPoint.data = zjMarkPoint;
			option.series[2].markPoint.data = hxMarkPoint;
			
        	// 为echarts对象加载数据 
            myChart.setOption(option);
        }
        
        function loadData(option,myChart,nodeList){
            var simpleChain = [];//单链连线
            var multitink = [];//多链连线
            var simpleChainTemp = [];//单链连线(临时)
            var multitinkTemp = [];//多链连线(临时)
            $.ajax({
				type:'post',
				url:'${ctx}/network/getTopology',
				data:$('#nodeForm').serialize(),
				async:false,
				datatype:'json',
				error:function(){}, //非必须
				success:function(data){//非必须
					if(data.code == 1){
						//SR节点拓扑图
						if(data.topologyList && data.topologyList.length>=1){
							var topologyList = data.topologyList;
							for(var i=0;i<topologyList.length;i++){
								if(topologyList[i].isTemp != true){
									if(topologyList[i].simpleChain == 0){//多链
										multitink.push([{geoCoord:[topologyList[i].sr_lng,topologyList[i].sr_lat],name:topologyList[i].src_sr_name+':'+topologyList[i].src_iframe,type:1,isLoaded:false,msg:'',smoothness:topologyList[i].smoothness},{geoCoord:[topologyList[i].dst_sr_lng,topologyList[i].dst_sr_lat],name:topologyList[i].dst_sr_name+":"+topologyList[i].dst_iframe,type:1,isLoaded:false,msg:''}]);
									}else{//单链
										simpleChain.push([{geoCoord:[topologyList[i].sr_lng,topologyList[i].sr_lat],name:topologyList[i].src_sr_name+':'+topologyList[i].src_iframe,type:1,isLoaded:false,msg:''},{geoCoord:[topologyList[i].dst_sr_lng,topologyList[i].dst_sr_lat],name:topologyList[i].dst_sr_name+":"+topologyList[i].dst_iframe,type:1,isLoaded:false,msg:''}]);
									}
								}else{
									if(topologyList[i].simpleChain == 0){//多链
										multitinkTemp.push([{geoCoord:[topologyList[i].sr_lng,topologyList[i].sr_lat],name:topologyList[i].src_sr_name+':'+topologyList[i].src_iframe,type:1,isLoaded:false,msg:'',smoothness:topologyList[i].smoothness},{geoCoord:[topologyList[i].dst_sr_lng,topologyList[i].dst_sr_lat],name:topologyList[i].dst_sr_name+":"+topologyList[i].dst_iframe,type:1,isLoaded:false,msg:''}]);
									}else{//单链
										simpleChainTemp.push([{geoCoord:[topologyList[i].sr_lng,topologyList[i].sr_lat],name:topologyList[i].src_sr_name+':'+topologyList[i].src_iframe,type:1,isLoaded:false,msg:''},{geoCoord:[topologyList[i].dst_sr_lng,topologyList[i].dst_sr_lat],name:topologyList[i].dst_sr_name+":"+topologyList[i].dst_iframe,type:1,isLoaded:false,msg:''}]);
									}
								}
								
							}
						}
					}
					//else{
						//alert(data.msg);
					//}
				}
				});
			
			//SR关系图
			option.series[3].markLine.data = simpleChain;
			option.series[4].markLine.data = multitink;
			option.series[5].markLine.data = simpleChainTemp;
			option.series[6].markLine.data = multitinkTemp;
        	// 为echarts对象加载数据 
            myChart.setOption(option);
        }
    </script> 

 
</body>
</html>