<div id="div_${id}" class="select ${clazz}">
	<input type="hidden" id="${id}" name="${id}" value="${value}"/>
	
	<span>${placeholder}</span>
	<ul style="display:none;">
		<#if (list?exists) && (list?size>0)  >
			<#list list as item>
				<li val="${item.value}" onClick="${id}_onClick(this, false)">${item.text}</li>
			</#list>
		</#if>
	</ul>
</div>
<label id="${id}-error" for="${id}" class="error" style="display:none;"></label>

<script type="text/javascript">
$(function(){
	var li = $("#div_${id} li[val='${value}']");
	if(li.length>0){
		${id}_onClick(li[0], true);
	}
	
	$("#div_${id}").click(function(e) {
		$(this).children("ul").toggle();
		$(this).addClass("focus");
		e.stopPropagation();
	});
});

//选择下拉框
function ${id}_onClick(obj, isInit){
	var li = $(obj);
	var li_val = li.attr("val");
	var li_text = li.text();
	li.addClass("selected").siblings("li").removeClass("selected");
	li.parent("ul").siblings("span").text(li_text).attr("title", li_text);
	
	$("#${id}").val(li_val);
	<#if callback!="">
		${callback}(li_val, li_text, isInit);
	</#if>
}
</script>
