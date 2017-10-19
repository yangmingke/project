<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/common/taglibs.jsp"%>
<!DOCTYPE html>
<html>
<head>
	<title>SR节点间当前吞吐量</title>
	
	<script type="text/javascript">
    	$(function(){
    		doSearch();
    	});
    	function doSearch(){
    		var src_sr_id = $("#src_sr_id").val();
    		var dst_sr_id = $("#dst_sr_id").val();
    		var start_time = $("#start_time").val();
	  		var end_time = $("#end_time").val();
	  		var param = [];
	  		param.push('src_sr_id='+src_sr_id);
	  		param.push('dst_sr_id='+dst_sr_id);
	  		param.push('start_time='+start_time);
	  		param.push('end_time='+end_time);
    		$.ajax({
				url:"${ctx}/throughput/pieData",
				type:"post",
				data:param.join('&'),
				dataType: "json",
				success: function (data) {
					if(data){
						if(data.code == 1){
						var pieData = [];
						pieData.push(["目的进入源节点的数据包",parseFloat(data.in_pkts)]);
						pieData.push(["目的进入源节点的字节数",parseFloat(data.in_bytes)]);
						pieData.push(["源节点流出目的节点数据包",parseFloat(data.out_pkts)]);
						pieData.push(["源节点流出目的节点字节数",+parseFloat(data.out_bytes)]);
						drawPie(pieData);//饼状图
					}else{
						if(data.code == -1){
							alert(data.msg);
						}
					}
				 }
		        },
		        error: function (data) {
		        	alert(data.msg);
		        }
			});
    	}
    	
    	function drawPie(pieData){
    		$('#main').highcharts({
				chart: {
		            renderTo: 'container1',
		            plotBackgroundColor: null,
		            plotBorderWidth: null,
		            plotShadow: false
	        	},
	            title: {
	                text: '吞吐量分布图' ,  
	                subtext: '当前总吞吐量',
	            },  
	           tooltip: {//鼠标移动到每个饼图显示的内容  
	                pointFormat: '{point.name}: <b>{point.percentage.toFixed(2)}%</b>',  
	                percentageDecimals: 1,  
	                formatter: function() {  
	                    return this.point.name+':'+this.point.percentage.toFixed(2)+'%';  
	                }  
	            },
	            legend: {
					 layout:'vertical',//图例布局：垂直排列
					 floating:'true',
					 align:'left',
					 verticalAlign:'top',
					 x:90,
					 y:45,
					 labelFormatter:function(){
					 	return this.name + '(' + this.percentage.toFixed(2) + '%)';
					 }
				},  
	            plotOptions: { 
	                pie: {  
	                    size:'80%',  
	                    borderWidth: 0,
	                    animation:true,//是否在显示图表的时候使用动画   
	                    allowPointSelect: true,  
	                    cursor: 'pointer', 
	                    showInLegend: true,
	                    dataLabels: {  
	                    enabled: true,  
	                    color: '#000', 
	                    style: {                              
	                       color: (Highcharts.theme && Highcharts.theme.contrastTextColor) || 'black'  
	                    },  
	                    formatter: function(index) {      
	                            return  '<span>' + this.point.name + '</span>';  
	                       }  
	                  },  
	                 padding:20  
	                }  
	            },  
	            series: [{//设置每小个饼图的颜色、名称、百分比  
	                type: 'pie',  
	                name: null,  
	                data: pieData 
	            }]  
        	}); 
    	}
	</script>
</head>
<body menuId="6">
<!--Action boxes-->
  <div class="container-fluid">
    <hr>
    <div class="row-fluid">
      <div class="span12">
        <div class="widget-box">
          <div class="widget-title">
         	 <span class="icon">
         		 <i class="fa fa-th"></i>
         	 </span>
			 <h5></h5>
				<div class="search">
						<ul> 
						    <li>监控点：
						    	<u:select id="src_sr_id" value="${param.src_sr_id}" sqlId="sr_monitor" placeholder="监控点" style="width:180px" ></u:select>
						    </li>
						    <li>监控目标：
						    	<u:select id="dst_sr_id" value="${param.dst_sr_id}" sqlId="sr_monitor" placeholder="监控点" style="width:180px" ></u:select>
						    </li>
						    <!-- <li>
						    	<u:select id="mnos" value="${param.mnos}" 
						    		data="[{value:'',text:'运营商:全部'},{value:'0',text:'联通'},{value:'1',text:'电信'},{value:'2',text:'移动'},{value:'3',text:'联通加电信'},{value:'4',text:'联通加移动'},{value:'5',text:'电信加移动'},{value:'6',text:'三线'},{value:'7',text:'BGP'}]" 
						    		placeholder="节点所属运营商" style="width:130px">
						    	</u:select>
						    </li> -->
						    <li>
						    	<u:date id="start_time" value="${data.start_time}" placeholder="开始时间" params="minDate:'%y-%M-{%d-45} 00:00:00', maxDate:'#F{$dp.$D(\\'end_time\\')||\\'%y-%M-%d %H:%m:%s\\'}', isShowClear:false" />
								<span>至</span>
            					<u:date id="end_time" value="${data.end_time}" placeholder="结束时间" params="minDate:'#F{$dp.$D(\\'start_time\\')||\\'%y-%M-{%d-45} 00:00:00\\'}', maxDate:'%y-%M-%d %H:%m:%s', isShowClear:false" />
							</li>
							<li><a href="javascript:void(0)" onClick="doSearch()" class="link">查询</a></li>
						</ul>
				</div>
          </div>
           
          <div class="section">
			<div class="box">
				<div class="content">						
					<div id="main" class="main" style="height:420px;"></div>
				</div>
			</div>
		 </div>
			
        </div>
      </div>
    </div>
  </div>
 
</body>
</html>