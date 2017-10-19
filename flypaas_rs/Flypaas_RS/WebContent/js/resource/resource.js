$(function(){
		//查询所有的省份  城市
		$.ajax({
			url:"/provinceController/queryAllProvince.action",
			type:"post",
			success:function(data){
				var objArray = eval("("+data+")");
				//遍历json数组 
				$.each(objArray, function(i, item) {
					$('#province').append("<option value="+item.id+">"+item.name+"</option>");
				});
		}
		})
});


