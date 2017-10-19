function crudObject(config){
	var crudObject = this;
	 var defaultConfig = {searchForm : $("#searchForm"),
		 container : $("#container"),highcharts:{
			 title: {
	              text: '统计',
	              x: -20 //center
	          },
	          subtitle: {
	              text: '',
	              x: -20
	          },
	          legend: {
	              layout: 'vertical',
	              align: 'right',
	              verticalAlign: 'middle',
	              borderWidth: 0
	          }
		 }
	 };
	 crudObject.configInfo = $.extend(defaultConfig,config);
	 
	 this.search = function(){
		 var action = crudObject.configInfo.searchForm.attr("action");
		 $.ajax({
			 url:action,
			 type:"POST",
			 data: crudObject.configInfo.searchForm.serialize(),
			 success : this.ajaxSuccess,
			 error: function (msg) {
		        	alert("系统异常，请稍后再试");
		     }
		 });
	 };
	 
	 this.ajaxSuccess = function(data){
		var highcharts_config = $.extend(crudObject.configInfo.highcharts,data);
		crudObject.configInfo.container.highcharts(highcharts_config);
	 };
	 return this;
};