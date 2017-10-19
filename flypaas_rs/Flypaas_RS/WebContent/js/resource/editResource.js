$(function(){
	fun1();//查询所有国家列表（）
    fun2();//根据默认国家查询其所有省份列表
	fun3();//查询省份下面的市
	fun5();//查询所有运营商
	fun4();//节点赋值
});

 function fun1(){
	 $('#country').empty();
		$.ajax({
			url:"/cityController/queryAllCountryAjax.action",
			dataType:"json",
			async:false,
			success:function (data){
				var json = eval("(" +data + ")");
				$.each(json, function (index, item) {  
					$('#country').append("<option value = '"+item.countryid+"'>"+item.country+"</option>"); 
				})
			}
		});
}

   function fun2(){
		$('#province').empty();
		var countryId = $('#countryId').val();
		$.ajax({
			url:'/cityController/queryProvinceByCountryId',
			data:{
				"countryId":countryId
			},
			async:false,
			success:function(msg){
				var obj = eval("(" + msg + ")");
				var json = eval("(" + obj + ")");
				$.each(json, function (index, item) {  
					$('#province').append("<option value = '"+item.provinceid+"'>"+item.province+"</option>"); 
				});
			},
			error:function(){
				alert("出错了...");
			}
		})
	} 

	function fun3(){
		$('#city').empty();
		var provinceId = $('#provinceId').val();
		$.ajax({
			url:'/cityController/queryCityBypId',
			data:{
				provinceId:provinceId
			},
			async:false,
			success:function(msg){
				var obj = eval("(" + msg + ")");
				var json = eval("(" + obj + ")");
				$.each(json, function (index, item) {  
					$('#city').append("<option value = '"+item.cityid+"'>"+item.city+"</option>"); 
				});
			}
		})
	} 
/* 
 function fun2(){
$('#province').empty();
$.ajax({
	url:"/provinceController/queryAllProvince.action",
	dataType:"json",
	async:false,
	success:function (data){
		var json = eval("(" +data + ")");
		$('#province').append("<option value = '0'>--请选择省份--</option>");
		$.each(json, function (index, item) {  
			$('#province').append("<option value = '"+item.id+"'>"+item.name+"</option>"); 
		});
	}
})
}

function fun3(){
$('#city').empty();
$.ajax({
	url:"/cityController/queryAllcity.action",
	dataType:"json",
	async:false,
	success:function (data){
		var json = eval("(" +data + ")");
		$('#city').append("<option value = '0'>--请选择市区--</option>");
		$.each(json, function (index, item) {  
			$('#city').append("<option value = '"+item.cityid+"'>"+item.city+"</option>"); 
		});
	}
})
}
	 */
$('#country').change(function(){
	$('#province').empty();
	var countryId = $('#country option:selected').val();
	$.post("/cityController/queryProvinceByCountryId?countryId="+countryId,function(data){
		var obj = eval("(" + data + ")");
		var json = eval("(" + obj + ")");
		$.each(json, function (index, item) {  
			$('#province').append("<option value = '"+item.provinceid+"'>"+item.province+"</option>"); 
		});
		$('#province').change();
	})
})

$('#province').change(function(){
	$('#city').empty();
	var provinceId = $('#province option:selected').val();
	$.post("/cityController/queryCityBypId?provinceId="+provinceId,function(data){
		var obj = eval("(" + data + ")");
		var json = eval("(" + obj + ")");
		if(json.length == 1 && json[0].cityid == undefined || json[0].cityid == 0){
			$('#city').append("<option value = '0'>"+'暂无信息'+"</option>"); 
		}else{
			$.each(json, function (index, item) {  
				$('#city').append("<option value = '"+item.cityid+"'>"+item.city+"</option>"); 
			});
		}
	})
})

function fun5(){
	 $.ajax({
		url:"/cityController/queryAllIsp.action",
		dataType:"json",
		async:false,
		success:function (data){
			var json = eval("(" +data + ")");
			$.each(json, function (index, item) {  
				$('#operatorId').append("<option value = '"+item.ispid+"'>"+item.name+"</option>"); 
			})
		}
	});
 }
function fun4 (){
	var id = $('#Id').val();
	$.ajax({
		type:"post",
		url:"/resourceController/queryResourceById.action",
		data:{"rtppSid":id},
		dataType:'json',
        cache: false,
        async:false,
		success:function(data){
			var obj = eval("("+data+")");
			$('#nameId').val(obj.name);
			$('#ipId').val(obj.ip);
			$('#ipId1').val(obj.ip);
			$('#mainIPId').val(obj.mainIp);
			$('#priceId').val(obj.price);
			$('#blockpriceId').val(obj.blockPrice);
			$('#operatorId').val(obj.operator);
			$('#typeId').val(obj.type);
			$('#netLevelId').val(obj.netLevel);
			$('#country').val(obj.country);
			$('#province').val(obj.province);
			$('#city').val(obj.zone);
			if(obj.isToLine==1){
				$("#radio-1").attr("checked","true");
				$("#toI").show();
			}
			if(obj.isToLine==0){
				$("#radio-2").attr("checked","true");
				$("#toI").hide();
			}
			$('#toIpId').val(obj.toIp);
			$('#bandwidthLimitId').val(obj.bandwidthLimit);
			$('#concurrencyLimitId').val(obj.concurrencyLimit);
			var objPrice = obj.blockPrice;
			if(objPrice !=null ){
				$('#Y').attr("checked","true");
				$('#blockTr').show();
				var objP = eval("(" + objPrice + ")");
				//var jsonList =[{"starttime":"0100","endtime":"0100","price":"120"},{"starttime":"0200","endtime":"0300","price":"130"}];
				var index = 1;
				$(objP).each(function(){
					var str = '<div id="div' + index + '"><select style="width: 100px" name="startTime'+ index + '" id="startTime' + index +'">';
					str = str + '<option value="" selected="selected">--请选择时间段--</option>';
					str = str + '<option value="0000">00:00</option><option value="0100">01:00</option><option value="0200">02:00</option><option value="0300">03:00</option>';
					str = str + '<option value="0400">04:00</option><option value="0500">05:00</option><option value="0600">06:00</option><option value="0700">07:00</option>';
					str = str + '<option value="0800">08:00</option><option value="0900">09:00</option><option value="1000">10:00</option><option value="1100">11:00</option>';
					str = str + '<option value="1200">12:00</option><option value="1300">13:00</option><option value="1400">17:00</option><option value="1500">15:00</option>';
					str = str + '<option value="1600">16:00</option><option value="1700">17:00</option><option value="1800">18:00</option><option value="1900">19:00</option>';
					str = str + '<option value="2000">20:00</option><option value="2100">21:00</option><option value="2200">22:00</option><option value="2300">23:00</option>';
					str = str + '</select>&nbsp;--&nbsp;';
					str = str + '<select style="width: 100px" name="endTime'+ index + '" id="endTime' + index +'">';
					str = str + '<option value="" selected="selected">--请选择时间段--</option>';
					str = str + '<option value="0000">00:00</option><option value="0100">01:00</option><option value="0200">02:00</option><option value="0300">03:00</option>';
					str = str + '<option value="0400">04:00</option><option value="0500">05:00</option><option value="0600">06:00</option><option value="0700">07:00</option>';
					str = str + '<option value="0800">08:00</option><option value="0900">09:00</option><option value="1000">10:00</option><option value="1100">11:00</option>';
					str = str + '<option value="1200">12:00</option><option value="1300">13:00</option><option value="1400">17:00</option><option value="1500">15:00</option>';
					str = str + '<option value="1600">16:00</option><option value="1700">17:00</option><option value="1800">18:00</option><option value="1900">19:00</option>';
					str = str + '<option value="2000">20:00</option><option value="2100">21:00</option><option value="2200">22:00</option><option value="2300">23:00</option>';
					str = str + '</select>&nbsp;--&nbsp;';
					str = str + '<input type="text" name="price'+ index + '" placeholder="请填入价格" style="width: 70px" id="price'+ index + '"/><div><br/></div></div>';
					$('#d1').append(str);
					$('#startTime'+ index).val(this.starttime);
					$('#endTime' + index).val(this.endtime);
					$('#price' + index).val(this.price);
					index++;
				});
			}else{
				$('#blockPriceTr').hidden();
				$('#N').attr("checked");
			}
		}
	});
	
}