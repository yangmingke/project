package com.network.monitor.constant;

import com.network.monitor.model.Highstock;

/**
 * 图表常量
 *
 */
public class ChartConstant {

	/**
	 * 图表信息：SR延时曲线（实时）
	 */
	public static final Highstock delay_realtime;

	/**
	 * 图表信息：SR丢包曲线（实时）
	 */
	public static final Highstock lost_realtime;
	
	/**
	 * 图表信息：SR吞吐量曲线（实时）
	 */
	public static final Highstock throughput_realtime;
	
	static {
		delay_realtime = new Highstock();
		delay_realtime.setTitle("SR节点间延时曲线图");
		delay_realtime.setyTitle("延时时间");
		delay_realtime.setyUnit("ms");
		delay_realtime.setxKey("time1");
		delay_realtime.setIsFloat(false);
		
		lost_realtime = new Highstock();
		lost_realtime.setTitle("SR节点丢包曲线图");
		lost_realtime.setyTitle("百分比(%)");
		lost_realtime.setyUnit("%");
		lost_realtime.setxKey("time1");
		lost_realtime.setIsFloat(true);
		
		throughput_realtime = new Highstock();
		throughput_realtime.setTitle("SR节点吞吐量曲线图");
		throughput_realtime.setyTitle("吞吐量(w)");
		throughput_realtime.setyUnit("w");
		throughput_realtime.setxKey("throughtTime");
	}

}
