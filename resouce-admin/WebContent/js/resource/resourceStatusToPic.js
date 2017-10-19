function searchStatus(ip,dateTime){
    // 基于准备好的dom，初始化echarts实例
    var myChart = echarts.init(document.getElementById('main'));
    // 指定图表的配置项和数据
    var option = {
        tooltip: {
            trigger: 'axis', //坐标轴触发提示框，多用于柱状、折线图中
            formatter: '{b0}<br/><font color=#FF3333>&nbsp;●&nbsp;</font>{a0} : {c0} KB' + 
				           '<br/><font color=#53FF53>&nbsp;●&nbsp;</font>{a1} : {c1} KB ' + 
				           '<br/><font color=#B15BFF>&nbsp;●&nbsp;</font>{a2} : {c2} KB ' 
            
        },
        legend: {    //图表上方的类别显示               
            show:true,
            data:['总流量','流量流入','流量流出']
        },
        color:[
               '#FF3333',    //并发量曲线颜色
               '#53FF53',    //入吞吐量曲线颜色
               '#B15BFF'    //出吞吐量图颜色
        ],
        toolbox: {    //工具栏显示             
            show: true,
            feature: {                
                saveAsImage: {}        //显示“另存为图片”工具
            }
        },
        
        yAxis :{     //Y轴  
            type : 'value',
            data : []
        },
        xAxis:  {    //X轴           
            type : 'category',
            data : dates    //先设置数据值为空，后面用Ajax获取动态数据填入
        },
        series : [    //系列（内容）列表                      
                    {
                        name:'总流量KB',
                        type:'line',    //折线图表示（生成并发量曲线）
                        symbol:'circle',    //设置折线图中表示每个坐标点的符号；emptycircle：空心圆；emptyrect：空心矩形；circle：实心圆；emptydiamond：菱形                        
                        data:[]        //数据值通过Ajax动态获取
                    },
                    
                    {
                        name:'流量流入KB',
                        type:'line',
                        symbol:'emptycircle',
                        data:[]
                    },
                    
                    {
                        name:'流量流出KB',
                        type:'line',
                        symbol:'emptycircle',    //标识符号为实心圆 
                        data:[]
                    }
        ]
    };
     myChart.showLoading();    /*数据加载完之前先显示一段简单的loading动画*/
     var total = [];
     var traffic_in = [];
     var traffic_out = [];
     var dates = [];        
     $.ajax({ 
	     type : "post",      
	     async : true,        
	     url : "/resourceStatusController/queryResourceStatusToPic.action",    
	     data : {
	    	 "ip":ip,
	    	 "dateTime":dateTime
	     },        
	     dataType : "json",        
	     success : function(json) {
	    	 var result = eval("("+ json +")");
	    	 if (result != null) {
                for(var i=0;i<result.length;i++){       
                	total.push(result[i].total);
                	traffic_in.push(result[i].traffic_in);
                	traffic_out.push(result[i].traffic_out);
                   dates.push(result[i].hour+'时');
                }
                myChart.hideLoading({text:'正在努力加载数据中....'});    //隐藏加载动画
                myChart.setOption({        //载入数据
                    xAxis: {
                        data: dates     //填入X轴数据
                    },
                    series: [    //填入系列（内容）数据
                        {
                            // 根据名字对应到相应的系列
                        	name: '总流量',
                            data: total
                        },
                        {
                            name: '流量流入',
                            data: traffic_in
                        },
                        {
                            name: '流量流出',
                            data: traffic_out
                        }
                    ]
                });
	    	 }
	     },
	     error : function(errorMsg) {
	    	 //请求失败时执行该函数
	    	 alert("图表请求数据失败，可能是服务器开小差了");
	    	 myChart.hideLoading({text:'正在努力加载数据中....'});        
	     }
     });
     myChart.setOption(option);    //载入图表   
}