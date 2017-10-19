$(function(){
	$(document).bind("contextmenu",function(e){
        return false;
    });
	$('th').mousedown(function(e){
		var aa = $(this).attr("ordername");
		if(aa=="" || aa==undefined){
			return;
		}
		var id = e.which;
		if(id==1){
			var orderName = $(this).attr("orderName");
			if(orderName!=undefined && orderName!=""){
				var ys = "<input type='hidden' name='orderName' value='"+orderName+"'>";
				$("form").append(ys);
				$("form").submit();
			}
		}else if(id==3){
			var orderName = $(this).attr("orderName");
			if(orderName!=undefined && orderName!=""){
				var ys = "<input type='hidden' name='orderName' value='"+orderName+" desc'>";
				$("form").append(ys);
				$("form").submit();
			}
		}
		return;
		
	});
	
	var order = $("#order").val();
	var index = order.indexOf("desc");
	var id;
	var order1;
	if(index>0){
		var ay = order.split(" ");
		id = ay[0];
		order1 = "desc";
	}else{
		id=order;
		order1 = "asc";
	}
	$("th[orderName="+id+"]").addClass("sorting_"+order1);
});