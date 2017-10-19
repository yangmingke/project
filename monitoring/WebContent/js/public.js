//公共js脚本

/**
 * 显示弹层
 * 
 * @param id
 *            弹层id
 * @param url
 *            弹层页面，可以为null
 */
function showBox(id, url) {
	if ($(".background_box").length == 0) {
		$(".float_box:first").before(
				"<div class='background_box' style='display:none;'></div>");
	}
	if ($("#" + id).length == 0) {
		$("body").append("<div id='" + id + "' style='display:none;'></div>");
		$("#" + id).load(url);
	}
	$(".background_box").show();
	$("#" + id).show();
}

/**
 * 隐藏弹层
 * 
 * @param id
 *            弹层id
 */
function hideBox(id) {
	$(".background_box").hide();
	$("#" + id).hide();
}

/**
 * 新窗口查看文件
 * 
 * @param img
 *            img标签
 */
function viewFile(img) {
	open($(img).attr("src"), "viewFile");
}

var fullScreenCallback;// 全屏事件回调函数，一个参数：是否全屏
$(document)
		.on(
				"fullscreenchange MSFullscreenChange mozfullscreenchange webkitfullscreenchange",
				function() {
					if (fullScreenCallback != null) {
						fullScreenCallback(document.fullScreenElement
								|| document.msFullscreenElement
								|| document.mozFullScreenElement
								|| document.webkitFullscreenElement);
					}
				});
/**
 * 切换全屏模式
 * 
 * @param id
 *            全屏元素的id
 * @param callback
 *            全屏事件回调函数，一个参数：是否全屏
 */
function toggleFullScreen(id, callback) {
	var divObj = document.documentElement;
	fullScreenCallback = callback;

	if (divObj.requestFullscreen) {
		if (document.fullScreenElement) {
			document.cancelFullScreen();
		} else {
			divObj.requestFullscreen();
		}
	} else if (divObj.msRequestFullscreen) {// IE
		if (document.msFullscreenElement) {
			document.msExitFullscreen();
		} else {
			divObj.msRequestFullscreen();
		}
	} else if (divObj.mozRequestFullScreen) {// Firefox
		if (document.mozFullScreenElement) {
			document.mozCancelFullScreen();
		} else {
			divObj.mozRequestFullScreen();
		}
	} else if (divObj.webkitRequestFullscreen) {// Chrome
		if (document.webkitFullscreenElement) {
			document.webkitCancelFullScreen();
		} else {
			divObj.webkitRequestFullscreen();
		}
	}
}

var chartSize = new Object(); // 图表大小
$(function() {
	chartSize.screen_width = window.screen.width; // 屏幕宽度
	chartSize.screen_height = window.screen.height - 45; // 屏幕高度
});

/**
 * 图表的相关函数
 */

var chartFunction = {
		

	/**
	 * 图表全屏
	 * 
	 * @param button
	 *            按钮
	 * @param id
	 *            图表id
	 */
	fullScreen : function(button, id) {
		var c = $("#" + id);
		var chart = c.highcharts();
		button = $(button);
		if (chartSize[id + "_width"] == null) {
			chartSize[id + "_width"] = c.width(); // 图表宽度
			chartSize[id + "_height"] = c.height(); // 图表高度
		}

		toggleFullScreen("div_" + id, function(isFull) {
			if (isFull) {
				chart.setSize(chartSize.screen_width, chartSize.screen_height);
				button.val("取消全屏");
			} else {
				chart.setSize(chartSize[id + "_width"], chartSize[id
						+ "_height"]);
				button.val("全屏");
			}
		});
	},

	/**
	 * 获取图表数据
	 * 
	 * @param chartInfo
	 *            图表信息
	 */
	getData : function(chartInfo) {
		var xMin = chartInfo.xMin;
		var xMax = chartInfo.xMax;
		var interval = chartInfo.interval;
		var xKey = chartInfo.xKey;
		var seriesList = chartInfo.seriesList;
		var dataList = chartInfo.dataList;
		if (dataList == null) {
			dataList = [];
		}

		var yKeyList = [];
		var defaultData = new Object();
		$.each(seriesList, function(i, series) {
			yKeyList.push(series.yKey);
			defaultData[series.yKey] = series.defaultValue;
		});

		if (interval != null) {// 补全曲线
			if (dataList.length > 0) {
				if (xMin == null) {
					xMin = dataList[0][xKey];
				}
				if (xMax == null) {
					xMax = dataList[dataList.length - 1][xKey];
				}
			}

			if (xMin != null && xMax != null) {
				var resultList = [];
				var data = null;
				var length = dataList.length;
				var index = 0;
				var x = 0;
				for (var i = xMin; i <= xMax; i += interval) {
					if (data == null && index < length) {
						data = dataList[index++];
						x = data[xKey];
					}
					if (i == x) {
						resultList.push(data);
						data = null;
					} else {
						var defaultDataV = new Object();
						defaultDataV[xKey] = i;
						resultList.push(defaultDataV);
					}
				}
				dataList = resultList;
			}
		}

		var chartData = new Object();
		$.each(yKeyList, function(i, yKey) {
			chartData[yKey] = [];
		});
		chartData.navigator_yKey = [];

		$.each(dataList, function(i, data) {
			var x = data[xKey];
			var navigator_y = null;
			$.each(yKeyList, function(j, yKey) {
				var y = data[yKey];
				if (y == null) {
					y = defaultData[yKey];
				}
				if (y != null) {
					y = parseFloat(y);
					navigator_y += y;
					chartData[yKey].push([ x, y ]);
				}
			});
			if (navigator_y != null) {
				chartData.navigator_yKey.push([ x, navigator_y ]);
			}
		});
		return chartData;
	},

	/**
	 * 获取图表信息
	 * 
	 * @param id
	 *            图表id
	 */
	getChartInfo : function(id) {
		return window[id + "_chartInfo"];
	},

	/**
	 * 添加点
	 * 
	 * @param id
	 *            图表id
	 * @param dataList
	 *            图表数据
	 */
	addPoint : function(id, dataList) {
		var redraw = false;
		var shift = true;
		var chart = $("#" + id).highcharts();
		var chartInfo = chartFunction.getChartInfo(id);
		var xKey = chartInfo.xKey;
		var seriesList = chartInfo.seriesList;
		var chartSeries = chart.series;
		var isHighstock = chartSeries.length > seriesList.length;// 是否是股票图表

		$.each(dataList, function(i, data) {
			var x = data[xKey], y, navigator_y = null;

			$.each(seriesList, function(j, series) {
				y = data[series.yKey];
				if (y == null) {
					y = series.defaultValue;
				}
				if (y != null) {
					y = parseFloat(y);
					navigator_y += y;
					chartSeries[j].addPoint([ x, y ], redraw, shift);
				}
			});
			if (isHighstock && navigator_y != null) {
				chartSeries[chartSeries.length - 1].addPoint(
						[ x, navigator_y ], redraw, shift);
			}
		});
		chart.redraw();
	},

	/**
	 * 重画
	 * 
	 * @param id
	 *            图表id
	 * @param chartInfo
	 *            图表信息
	 */
	redraw : function(id, chartInfo) {
		var redraw = false;
		var chart = $("#" + id).highcharts();
		var seriesList = chartInfo.seriesList;
		var chartSeries = chart.series;
		var isHighstock = chartSeries.length > seriesList.length;// 是否是股票图表
		
		var chartData;
		if(chartInfo.changeData){
			chartData = chartInfo.dataMap;
		}else{
			chartData = chartFunction.getData(chartInfo);
		}
		
		$.each(seriesList, function(j, series) {
			if(chartInfo.changeData){
				chartSeries[j].setData(chartData[series.name]);
			}else{
				chartSeries[j].setData(chartData[series.yKey], redraw);
			}
		});
		if (isHighstock) {
			if(chartInfo.changeData){
				chartSeries[chartSeries.length - 1].setData(
						chartData[seriesList[0].name], redraw);
			}else{
				chartSeries[chartSeries.length - 1].setData(
						chartData.navigator_yKey, redraw);
			}
		}
		chart.redraw();
	},

	/**
	 * 显示或隐藏曲线
	 * 
	 * @param button
	 *            按钮
	 * @param id
	 *            图表id
	 * @param seriesIndex
	 *            控制的曲线索引（从0开始），中间用,分割
	 */
	showSeries : function(button, id, seriesIndex) {
		button = $(button);
		seriesIndex = seriesIndex.split(",");
		var chartSeries = $("#" + id).highcharts().series;

		if (button.attr("show") != 1) {
			button.attr("show", "1");
			button.val(button.val().replace("隐藏", "显示"));
			$.each(seriesIndex, function(i, value) {
				chartSeries[value].hide();
			});
		} else {
			button.attr("show", "0");
			button.val(button.val().replace("显示", "隐藏"));
			$.each(seriesIndex, function(i, value) {
				chartSeries[value].show();
			});
		}
	}

};
