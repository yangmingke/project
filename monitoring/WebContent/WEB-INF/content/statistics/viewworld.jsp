<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/common/taglibs.jsp"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
	<title>SR链路质量图</title>
	<meta name="apple-mobile-web-app-capable" content="no">
	<meta name="apple-mobile-web-app-status-bar-style" content="black">
	<meta name="viewport" content="width=device-width,initial-scale=0.69,user-scalable=yes,maximum-scale=1.00">
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
<body menuId="13">
<!--Action boxes-->
<div class="container-fluid">
    <hr>
    <div class="row-fluid">
      <div class="span12">
      		<div class="tabbable" id="tabs-578884">
				<ul class="nav nav-tabs" style="margin: 0px;">
					<li>
						<a href="#panel-125117" data-toggle="tab" onclick="window.location.href='${ctx}/link/quality'">中国</a>
					</li>
					<li class="active">
						<a href="#panel-835071" data-toggle="tab">世界</a>
					</li>
				</ul>
				<div class="tab-content">
					<div class="tab-pane" id="panel-125117">
						 
						
					</div>
					<div class="tab-pane active" id="panel-835071">
						<div class="widget-box" style="margin: 0px;">
					          <div class="widget-title">
					         	 <span class="icon">
					         		 <i class="fa fa-th"></i>
					         	 </span>
								 <h5></h5>
									<div class="search">
										<form method="post" id="nodeForm" action="${ctx}/link/qualityworld">
											<input type="hidden" id="hiddenId" name="dst_id"  value="${param.dst_sr_id}"/>
											<ul> 
												<li>监控点<select id="sr_id" name="sr_id">
																<c:forEach items="${data.map}" var="var">
																	<c:if test="${var.text!=null && param.sr_id==var.value}">
																		<option value="${var.value}" selected="selected">${var.text}</option>
																	</c:if>
																	<c:if test="${param.sr_id!=var.value}">
																		<option value="${var.value}">${var.text}</option>
																	</c:if>
																</c:forEach>
														</select></li>
														<li>监控目标<select id="dst_sr_id" name="dst_sr_id">	
														</select></li>
														 <li><input type="submit" value="查 询" /></li>
											   <li>自动刷新时间:<input id="time" value="${data.time}" name="time" style="width:30px"/>s
											   <input type="text" value="<s:property value="data.end_time"/>" name="end_time" style="width:150px;" disabled="disabled"/>
											   </li>
											   <!--  
											    <li>
											    	<u:date id="start_time" value="${data.start_time}" placeholder="开始时间" params="minDate:'%y-%M-{%d-45} 00:00:00', maxDate:'#F{$dp.$D(\\'end_time\\')||\\'%y-%M-%d %H:%m:%s\\'}', isShowClear:false" />
													<span>至</span>
					            					<u:date id="end_time" value="${data.end_time}" placeholder="结束时间" params="minDate:'#F{$dp.$D(\\'start_time\\')||\\'%y-%M-{%d-45} 00:00:00\\'}', maxDate:'%y-%M-%d %H:%m:%s', isShowClear:false" />
												</li>
												-->
												
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
	  $(function(){
		   $.post("${ctx}/link/dst_sr_id",{sr_id:$("#sr_id").val()},function(data){
		    	data=eval(data);
		    	$("#dst_sr_id").empty();
		    	for(var o in data){
		    		if($("#hiddenId").val()==data[o].nbrid){
		    			$("#dst_sr_id").append("<option value='"+data[o].nbrid+"' selected='selected'>"+data[o].dst_name+"</option>");
		    		}else{
		    			$("#dst_sr_id").append("<option value='"+data[o].nbrid+"'>"+data[o].dst_name+"</option>");
		    		}
		    	}
		    })
		    $('select#sr_id').chosen();
	  })
	  
		$("#sr_id").change(function() {
		    $.post("${ctx}/link/dst_sr_id",{sr_id:$("#sr_id").val()},function(data){
		    	data=eval(data);
		    	$("#dst_sr_id").empty();
		    	for(var o in data){
		    		$("#dst_sr_id").append("<option value='"+data[o].nbrid+"'>"+data[o].dst_name+"</option>");
		    	}
		    	
		     })
			})
	</script>
</div>
  
  <script type="text/javascript">

			function myrefresh() {
				var mainFrame =this.parent.document.getElementsByName('mainFrame')[0];
		//	var mainFrame = document.frames("mainFrame");
// alert(1);
// 				var mainFrame=   document.getElementsByName('mainFrame');\
				$("#nodeForm").submit();
			}
				var time=$("#time").val();
				setTimeout('myrefresh()', 1000*time);
			// 路径配置
			require.config({
				paths : {
					echarts : '${ctx}/js/topology'
				}
			});

			// 使用
			require([ 'echarts', 'echarts/chart/map' // 使用柱状图就加载bar模块，按需加载
			], function(ec) {
				// 基于准备好的dom，初始化echarts图表
				var myChart = ec.init(document.getElementById('main'));

				//初始化参数
				var option = initOption();
				myChart.setOption(option);

				//加载地图
				loadPoints(option, myChart);
				loadData(option, myChart);
			});

			//初始化参数
			function initOption() {
				var option = {
					title : {
						text : 'SR节点链路质量图',
						x : 'center'
					},
					tooltip : {
						trigger : 'item',
						formatter : function(param) {
							if (param.data.type == 0) {//点
								return param.name;
							} else if (param.data.type == 1) {//线
								return param.name + ',' + param.data.nbrip
										+ '的丢包率：' + param.data.lost_rate
										+ '%,延时:' + param.data.average_delay
										+ 'ms'+",metric:"+param.data.metric;
							} else {
								return '';
							}
						}
					},
					toolbox : {
						show : true,
						orient : 'vertical',
						x : 'right',
						y : 'center',
						feature : {
							mark : {
								show : true
							},
							dataView : {
								show : true,
								readOnly : false
							},
							restore : {
								show : true
							},
							saveAsImage : {
								show : true
							}
						}
					},
					dataRange : {
						x : 'left',
						y : 'bottom',
						splitList : [ /* {
							start : 800,
							label : '很差',
							color : '#FF0000'
						}, {
							start : 400,
							end : 800,
							label : '一般',
							color : '#FF9900'
						}, {
							start : 200,
							end : 400,
							label : '良好',
							color : '#FFFF00'
						}, {
							start : 0,
							end : 200,
							label : '优秀',
							color : '#00FF00'
						}, */ 
						{
							start : 0,
							end : 600,
							label : '非常好',
							color : '#BBFF00'
						}, {
							start : 600,
							end : 1200,
							label : '好',
							color : '#a6c84c'
						}, {
							start : 1200,
							end : 1800,
							label : '良好',
							color : '#EEFFBB'
						}, {
							start : 1800,
							end : 2400,
							label : '一般',
							color : '#FFFF00'
						},{
							start : 2400,
							end : 3000,
							label : '差',
							color : '#FF7744'
						}, {
							start : 3000,
							label : '很差',
							color : '#FF3333'
						},{
							start : -1,
							end : -1,
							label : '接入节点',
							color : '#ff7f50'
						}, {
							start : -2,
							end : -2,
							label : '中继节点',
							color : '#32cd32'
						}, {
							start : -3,
							end : -3,
							label : '核心节点',
							color : '#6495ed'
						}, ],
					},
					series : [ {
						name : '接入节点',
						type : 'map',
						mapType : 'world',
						hoverable : false,
						roam : true,
						data : [],
						markPoint : {
							symbol : 'circle',
							symbolSize : 2, // 标注大小，半宽（半径）参数，当图形为方向或菱形则总宽度为symbolSize * 2
							itemStyle : {
								normal : {
									borderWidth : 1, // 标注边线线宽，单位px，默认为1
									label : {
										show : false
									}
								},
								emphasis : {
									borderWidth : 5,
									label : {
										show : false
									}
								}
							},
							data : []
						},
					}, {
						name : '中继节点',
						type : 'map',
						mapType : 'world',
						hoverable : false,
						roam : true,
						data : [],
						markPoint : {
							symbol : 'circle',
							symbolSize : 2, // 标注大小，半宽（半径）参数，当图形为方向或菱形则总宽度为symbolSize * 2
							itemStyle : {
								normal : {
									borderWidth : 1, // 标注边线线宽，单位px，默认为1
									label : {
										show : false
									}
								},
								emphasis : {
									borderWidth : 5,
									label : {
										show : false
									}
								}
							},
							data : []
						},
					}, {
						name : '核心节点',
						type : 'map',
						mapType : 'world',
						hoverable : false,
						roam : true,
						data : [],
						markPoint : {
							symbol : 'circle',
							symbolSize : 2, // 标注大小，半宽（半径）参数，当图形为方向或菱形则总宽度为symbolSize * 2
							itemStyle : {
								normal : {
									borderWidth : 1, // 标注边线线宽，单位px，默认为1
									label : {
										show : false
									}
								},
								emphasis : {
									borderWidth : 4,
									label : {
										show : false
									}
								}
							},
							data : []
						},
					}, ]
				};

				return option;
			}

			function loadPoints(option, myChart) {
				var jrMarkPoint = [];//接入节点
				var zjMarkPoint = [];//中继节点
				var hxMarkPoint = [];//核心节点
				$.ajax({
					type : 'post',
					url : '${ctx}/node/getNodeList',
					data : $('#nodeForm').serialize(),
					async : false,
					datatype : 'json',
					error : function() {
					}, //非必须
					success : function(data) {//非必须
						if (data.code == 1) {
							if (data.nodeList) {//标记所有SR节点
								var nodes = data.nodeList;
								for (var i = 0; i < nodes.length; i++) {
									if (nodes[i].level == 0) {//接入节点
										jrMarkPoint.push({
											name : nodes[i].sr_name + '('
													+ nodes[i].sr_id + '-'
													+ nodes[i].city + ')',
											geoCoord : [ nodes[i].lng,
													nodes[i].lat ],
											value : -1,
											type : 0,
											id : nodes[i].sr_id,
											isLoaded : false,
											msg : ''
										});
									} else if (nodes[i].level == 1) {//中继节点
										zjMarkPoint.push({
											name : nodes[i].sr_name + '('
													+ nodes[i].sr_id + '-'
													+ nodes[i].city + ')',
											geoCoord : [ nodes[i].lng,
													nodes[i].lat ],
											value : -2,
											type : 0,
											id : nodes[i].sr_id,
											isLoaded : false,
											msg : ''
										});
									} else {
										hxMarkPoint.push({
											name : nodes[i].sr_name + '('
													+ nodes[i].sr_id + '-'
													+ nodes[i].city + ')',
											geoCoord : [ nodes[i].lng,
													nodes[i].lat ],
											value : -3,
											type : 0,
											id : nodes[i].sr_id,
											isLoaded : false,
											msg : ''
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

			function loadData(option, myChart) {
				$.ajax({
							type : 'post',
							url : '${ctx}/link/drawQuality',
							data : $('#nodeForm').serialize(),
							async : false,
							datatype : 'json',
							error : function() {
							}, //非必须
							success : function(data) {//非必须
								if (data.code == 1) {
									//SR节点拓扑图
									if (data.qualityList
											&& data.qualityList.length >= 1) {
										var qualityList = data.qualityList;
										var link = [];//链路质量图
										var path = {
											name : '',
											type : 'map',
											mapType : 'world',
											data : [],
											markLine : {
												smooth : true,
												effect : {
													show : true,
													scaleSize : 1,
													period : 30,
													color : '#fff',
													shadowBlur : 1
												},
												itemStyle : {
													normal : {
														borderWidth : 1,
														label : {
															show : false
														},
														lineStyle : {
															type : 'solid'
														}
													}
												},
												data : []
											}
										};
										for (var i = 0; i < qualityList.length; i++) {
											link
													.push([
															{
																geoCoord : [
																		qualityList[i].sr_lng,
																		qualityList[i].sr_lat ],
																name : qualityList[i].src_sr_name
																		+ '('
																		+ qualityList[i].src_sr_id
																		+ ')',
																type : 1,
																smoothness : qualityList[i].smoothness,
																lost_rate : qualityList[i].lost_rate,
																average_delay : qualityList[i].average_delay,
																nbrip : qualityList[i].nbrip,
																metric : qualityList[i].metric
															},
															{
																geoCoord : [
																		qualityList[i].dst_sr_lng,
																		qualityList[i].dst_sr_lat ],
																name : qualityList[i].dst_sr_name
																		+ '('
																		+ qualityList[i].dst_sr_id
																		+ ')',
																type : 1,
																value : qualityList[i].metric,
																lost_rate : qualityList[i].lost_rate,
																average_delay : qualityList[i].average_delay,
																nbrip : qualityList[i].nbrip,
																metric : qualityList[i].metric
															} ]);
											src_id = qualityList[i].src_sr_id;
										}
										option.series.push(path);
										path.markLine.data = link;
										// 为echarts对象加载数据 
										myChart.setOption(option);
									}
								}
								//else{
								//alert(data.msg);
								//}
							}
						});

			}
		</script>
</body>
</html>