		// 检查起始时间不能超过3天  
		function checkTimeInOneMonth(startDate, endDate){  
		var startTime = getTimeByDateStr(startDate);  
		    var endTime = getTimeByDateStr(endDate);  
		    if((endTime - startTime) > 2*24*60*60*1000){  
		        return false;  
		    }  
		    return true;  
		}  
		  
		  
		//根据日期字符串取得其时间  
		function getTimeByDateStr(dateStr){  
		    var year = parseInt(dateStr.substring(0,4));  
		    var month = parseInt(dateStr.substring(5,7),10)-1;  
		    var day = parseInt(dateStr.substring(8,10),10);  
		    return new Date(year, month, day).getTime();  
		} 
		$(function(){
			//没有时间限制
			var dates = $("input[id^='beginDate'],input[id^='endDate']"); 
			dates.datepicker({
				changeMonth: true,
				changeYear: true
			});
			$.datepicker.regional['zh-CN'] = {  
	                    closeText: '关闭',  
	                    prevText: '<上月',  
	                    nextText: '下月>',  
	                    currentText: '今天',  
	                    monthNames: ['一月','二月','三月','四月','五月','六月',  
	                    '七月','八月','九月','十月','十一月','十二月'],  
	                    monthNamesShort: ['一','二','三','四','五','六',  
	                    '七','八','九','十','十一','十二'],  
	                    dayNames: ['星期日','星期一','星期二','星期三','星期四','星期五','星期六'],  
	                    dayNamesShort: ['周日','周一','周二','周三','周四','周五','周六'],  
	                    dayNamesMin: ['日','一','二','三','四','五','六'],  
	                    weekHeader: '周',  
	                    dateFormat: 'yy-mm-dd',  
	                    firstDay: 1,  
	                    isRTL: false,  
	                    showMonthAfterYear: true,  
	                    yearSuffix: '年',
	                    showButtonPanel:true,
	                    gotoCurrent:true,
	                    onSelect: function(selectedDate){
	                    	if(this.id=="beginDateLimit" || this.id=="endDateLimit"){
	                    		if(this.id == "beginDateLimit"){
	                    			// 如果是选择了开始时间（startDate）设置结束时间（endDate）的最小时间和最大时间
	                    			option = "minDate"; //最小时间
	                    			var selectedTime = getTimeByDateStr(selectedDate);
	                    			var minTime = selectedTime;
	                    			//最小时间 为开第一个日历控制选择的时间
	                    			targetDate = new Date(minTime); 
	                    			//设置结束时间的最大时间
	                    			optionEnd = "maxDate";
	                    			//因为只能做三天内的查询  所以是间隔2天  当前时间加上44*24*60*60*1000
	                    			targetDateEnd = new Date(minTime+44*24*60*60*1000);
	                    		}else{
	                    			// 如果是选择了结束时间（endDate）设置开始时间（startDate）的最小时间和最大时间
	                    			option = "maxDate"; //最大时间
	                    			var selectedTime = getTimeByDateStr(selectedDate);
	                    			var maxTime = selectedTime;
	                    			targetDate = new Date(maxTime);
	                    			//设置最小时间 
	                    			optionEnd = "minDate";
	                    			targetDateEnd = new Date(maxTime-44*24*60*60*1000);
	                    		}
	                    		dates.not(this).datepicker("option", option, targetDate);  
	                    		dates.not(this).datepicker("option", optionEnd, targetDateEnd);  
	                    	}
	                      }
			};
			$.datepicker._gotoToday = function (id) {
				var target = $(id);
				var inst = this._getInst(target[0]);
				var date = new Date();
				inst.selectedDay = date.getDate();
				inst.drawMonth = inst.selectedMonth = date.getMonth();
				inst.drawYear = inst.selectedYear = date.getFullYear();
				this._setDateDatepicker(target, date);
				this._selectDate(id, this._getDateDatepicker(target));
				this._notifyChange(inst);
				this._adjustDate(target);
			}; 
	        $.datepicker.setDefaults($.datepicker.regional['zh-CN']);
		});