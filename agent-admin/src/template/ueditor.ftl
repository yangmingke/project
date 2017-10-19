<#if (type=="edit")>
	<!-- 加载编辑器的容器 -->
	<script id="${id}" name="${id}" type="text/plain">
		${value}
	</script>
	
	<!-- 实例化编辑器 -->
	<script type="text/javascript">
		var ue = UE.getEditor("${id}");
	</script>
	<label id="ueditor_textarea_${id}-error" for="ueditor_textarea_${id}" class="error" style="display: none;"></label>
	
<#else>
	<div id="${id}" class="ueditor_ctn">${value}</div>
	<script type="text/javascript">
		uParse("#${id}");
	</script>
</#if>
