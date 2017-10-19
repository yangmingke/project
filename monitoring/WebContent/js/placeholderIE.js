$(function() {
	//判断是否为IE浏览器
	var ie = !-[ 1, ];
	if (ie) {
		$('input:submit').click(function() {
			{
				$("input").each(function(index, item) {
					if (($(this).val() == "开始时间") || $(this).val() == "结束时间") {
						$(this).removeAttr("placeholder");
						$(this).val("");
					}
				})
				$("form").submit();
			}
		})
	}
})