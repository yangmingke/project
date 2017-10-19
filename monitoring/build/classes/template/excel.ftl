<form action="/ucpaas-operate/excel" id="excelFrm" method="post" class="excelFrm">
<input type="button" id="exportBtn" value="导出到excel" />
</form>
<style>
.widget-title .search .excelFrm input[type='button'] { box-shadow: 0 1px 2px rgba(0, 0, 0, 0.3) inset, 0 1px 0 #ffffff; float: right; margin: 6px 11px 0 0; padding: 5px 10px;}
</style>
<script type="text/javascript">
$(function(){
	$("#exportBtn").click(function(){
		$("#excelFrm").submit();
	});
})
</script>
