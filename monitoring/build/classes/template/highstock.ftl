<div id="div_${id}" class="chart_box">
	<input type="button" value="全屏" class="chart_fullScreen" onclick="chartFunction.fullScreen(this, '${id}')" />
	<br/>
	<div id="${id}"></div>
	
	<div class="chart_selectDiv">
		<#list chart.selectButton as item>
			<input type="button" value="隐藏曲线(${item.name})" onclick="chartFunction.showSeries(this, '${id}', '${item.seriesIndex}')" />
		</#list>
	</div>
</div>

<script type="text/javascript">
var ${id}_chartInfo = ${chartInfo};//图表信息

$(function () {
	
	if(chart_chartInfo.dataMap){
		var chartData = chart_chartInfo.dataMap;
	}else{
		var chartData = chartFunction.getData(${id}_chartInfo);	//图表数据
	}
	
	Highcharts.setOptions({
		global: {
			useUTC: false
		}
	});
	
	$("#${id}").highcharts("StockChart", {			//图表展示容器，与div的id保持一致
		chart: {
			animation: Highcharts.svg,
			type: "line",							//指定图表的类型，默认是折线图（line）
			height: 700,							//高度
			events: {
				<#if (onLoad?exists) >
					load: ${onLoad}
				</#if>
			}
		},
		plotOptions: {								//绘图选项
			series: {
				turboThreshold: 100000000,			//点的最大个数
				dataGrouping: {
					dateTimeLabelFormats: {			//数据提示框中的时间格式
						second: [],
						minute: [],
						hour: [],
						day: ["%Y-%m-%d"],
						week: ["%Y-%m-%d"]
					}
				}
			}
		},
		rangeSelector: {							//范围选择器
			buttons: [{
				type: "minute",
				count: 1,
				text: "1分钟"
			},{
				type: "minute",
				count: 5,
				text: "5分钟"
			},{
				type: "minute",
				count: 10,
				text: "10分钟"
			}, {
				type: "minute",
				count: 30,
				text: "半小时"
			}, {
				type: "hour",
				count: 1,
				text: "1小时"
			}, {
				type: "hour",
				count: 6,
				text: "6小时"
			}, {
				type: "hour",
				count: 12,
				text: "12小时"
			}, {
				type: "day",
				count: 1,
				text: "1天"
			}, {
				type: "week",
				count: 1,
				text: "1周"
			}, {
				type: "month",
				count: 1,
				text: "1个月"
			}, {
				type: "month",
				count: 3,
				text: "3个月"
			}, {
				type: "month",
				count: 6,
				text: "半年"
			}, {
				type: "ytd",
				text: "今年迄今"
			}, {
				type: "year",
				count: 1,
				text: "1年"
			}, {
				type: "all",
				text: "所有"
			}],
			//selected: 0,							//默认选中第几个按钮，从0开始
			//enabled:chart_chartInfo.rangeSelectorEnable,
			inputEnabled: false,
            inputBoxWidth: 140,
			inputDateFormat: "%Y-%m-%d %H:%M:%S",
			inputEditDateFormat: "%Y-%m-%d %H:%M:%S",
			inputDateParser: function (value) {
				value = value.split(/[- :]/);
				return Date.UTC(
					parseInt(value[0], 10),
					parseInt(value[1], 10)-1,
					parseInt(value[2], 10),
					parseInt(value[3], 10),
					parseInt(value[4], 10),
					parseInt(value[5], 10),
					0
				);
			}
		},
		title: {
			text: "${chart.title!}"					//指定图表标题
		},
		subtitle: {
			text: "${chart.subtitle!}"				//指定图表副标题
		},
		legend: {									//图例
			enabled: true
		},
		credits: {									//版权信息
			enabled: false
		},
		exporting: {								//导出功能按钮
			enabled: true
		},
		xAxis: {									//指定x轴分组
			title: {
				text: "${chart.xTitle!}"			//指定x轴的标题
			},
			tickPixelInterval: 150,					//刻度线的间隔，单位：px
			dateTimeLabelFormats: {
				day: "%Y-%m-%d",
				week: "%Y-%m-%d"
			}
			<#if chart.xMin??>
				, min: ${chart.xMin ?c}
			</#if>
			<#if chart.xMax??>
				, max: ${chart.xMax ?c}
			</#if>
		},
		yAxis: {
			title: {
				text: "${chart.yTitle!}"			//指定y轴的标题
			},
			opposite: false,						//是否显示在右边
			gridLineColor: "#EAE9E9"
			<#if chart.yUnit??>
				, labels: {
					format: "{value} ${chart.yUnit}"
				}
			</#if>
			<#if chart.yMin??>
				, min: ${chart.yMin ?c}
			</#if>
			<#if chart.yMax??>
				, max: ${chart.yMax ?c}
			</#if>
		},
		tooltip: {									//数据提示框
			shared: true,
			crosshairs: {
				width: 1,
				color: "#7AC943",
				dashStyle: "shortdot"
			},
			useHTML: true,
			headerFormat: "{point.key}<table style='width:100%;'>",
			<#if chart.isFloat>
				pointFormat: "<tr><td style='color: {series.color};'>{series.name}：</td><td style='text-align:right;'><strong>{point.y:.2f}%</strong></td></tr>",
			<#else>
				pointFormat: "<tr><td style='color: {series.color};'>{series.name}：</td><td style='text-align:right;'><strong>{point.y}</strong></td></tr>",
			</#if>
			footerFormat: "</table>"
			<#if chart.yUnit??>
				, valueSuffix: " ${chart.yUnit}"
			</#if>
		},
		series: [
			<#list chart.seriesList as item>
				{									//曲线
					name: "${item.name}",			//曲线名称
					<#if chart.changeData>
						data: chartData["${item.name}"],
					<#else>
						data: chartData.${item.yKey},
					</#if>
					visible: ${item.showSeries}
				}
				<#if item_has_next>
					,
				</#if>
			</#list>
		],
		navigator: {								//导航图
			margin: -10,
			height: 40,
			//baseSeries: 0,						//基于第几条曲线绘图，从0开始
			
			xAxis: {
				tickPixelInterval: 150,
				dateTimeLabelFormats: {
					day: "%Y-%m-%d",
					week: "%Y-%m-%d"
				},
				labels: {
					align: "center",
					style: {
						color: "#888"
					},
					x: 0,
					y: 12
				}
				<#if chart.xMin??>
					, min: ${chart.xMin ?c}
				</#if>
				<#if chart.xMax??>
					, max: ${chart.xMax ?c}
				</#if>
			},
			series:{
				type: "line",
				data: chartData.navigator_yKey
			}
		}
	});
});
</script>