
function jumpTo(maxPage){
	var page = $("#jumpTo").val();
	if(page > maxPage || page < 1){
	alert("对不起，无法到达该页")
	}else{
		$('body').load('/logController/queryLog?page=' + page);
	}
}

function jumpToResource(maxPage){
	var page = $("#jumpTo").val();
	if(page > maxPage || page < 1){
	alert("对不起，无法到达该页")
	}else{
		$('body').load('/resourceController/queryAllResourceFenYe?page=' + page);
	}
}