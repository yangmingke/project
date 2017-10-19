<div id="div_${id}" class="chart_box">
	<input type="button" value="全屏" class="chart_fullScreen" onclick="chartFunction.fullScreen(this, '${id}')" />
	<br/>
	<div id="${id}"></div>
	<span>注：拖动鼠标可以放大图表，按住shift键再拖动鼠标可以平移图表</span>
</div>

<script type="text/javascript">
var ${id}_chartInfo = ${chartInfo};//图表信息

$(function () {
	var chartData = chartFunction.getData(${id}_chartInfo);	//图表数据
	
	Highcharts.setOptions({
		global: {
			useUTC: false
		}
	});
	
	$("#${id}").highcharts({						//图表展示容器，与div的id保持一致
		chart: {
			animation: Highcharts.svg,
			type: "line",							//指定图表的类型，默认是折线图（line）
			height: 700,							//高度
			zoomType: "x",							//通过拖动鼠标进行缩放，可以是x、y、xy
			panning: true,							//允许平移
			panKey: "shift",						//设置一键在缩放和平移之间切换
			resetZoomButton: {						//缩放按钮的位置
				position: {
					y: 35
				},
				relativeTo: "chart"
			},
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
			type: "datetime",
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
			pointFormat: "<tr><td style='color: {series.color};'>{series.name}：</td><td style='text-align:right;'><strong>{point.y}</strong></td></tr>",
			footerFormat: "</table>"
			<#if chart.yUnit??>
				, valueSuffix: " ${chart.yUnit}"
			</#if>
		},
		series: [
			<#list chart.seriesList as item>
				{									//曲线
					name: "${item.name}",			//曲线名称
					data: chartData.${item.yKey},
					visible: ${item.showSeries}
				}
				<#if item_has_next>
					,
				</#if>
			</#list>
		]
	});
});
</script>