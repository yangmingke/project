<div class="pagenum">
	<span>共<i>${page.totalCount ?c}</i>条记录 每页显示${page.pageRowCount ?c}条 当前${page.currentPage ?c}/${page.totalPage ?c}页</span>
	<#if (page.totalPage>1)>
		<#if (page.currentPage>1)>
			<a href="javascript:;" class="first" onclick="getPage(1)">&nbsp;</a>
			<a href="javascript:;" class="prev" onclick="getPage(${(page.currentPage-1) ?c})">&nbsp;</a>
		</#if>
		<#if (page.currentPage<page.totalPage)>
			<a href="javascript:;" class="next" onclick="getPage(${(page.currentPage+1) ?c})">&nbsp;</a>
			<a href="javascript:;" class="last" onclick="getPage(${page.totalPage ?c})"></a>
		</#if>
		<span><input type="text" id="inputPage" onfocus="inputControl.setNumber(this, 10, 0, false)"/>
		<input type="button" value="GO" class="go" onclick="inputPage1()"/></span>
	</#if>
</div>

<script type="text/javascript">
var searchForm = $("#${formId}"); //查询的表单
var ajax = "${ajax}"; //是否使用异步提交
var divId = "${divId}"; //异步提交结果呈现容器
var dataDivId = "${dataDivId}"; //异步提交结果标示
$(function(){
	if(searchForm.find("#currentPage").length == 0){
		searchForm.append("<input type='hidden' id='currentPage' name='page.currentPage'/>");
	}
});

//查询某一页
function getPage(currentPage){
	if(/^[1-9]\d*$/.test(currentPage)){
		var b = ${page.totalPage ?c};
		var a = parseInt(currentPage);
		if(a >b){
			a = b;
		}
		if(a <1){
			a = 1;
		}
		searchForm.find("#currentPage").val(a);
		if(ajax==0){
			searchForm.submit();
		}else{
			var ly_div= $("#"+divId);
			var loading ="<div class='loading' style='text-align:center; padding-top:80px;'><img src='/images/loading.gif'/></div>" ;
			$.ajax({
				url:searchForm.attr("action"),
				type:"post",
				data:searchForm.serialize(),
				dataType: "text",
				success: function (html) {
					if(html.indexOf(dataDivId)>0){
						ly_div.html(html);
					}else{
						window.location.reload();
					}				
	            },
	            beforeSend:function(XMLHttpRequest){
	            	ly_div.html(loading);
              	},
	            error: function (msg) {
	            }
			});
		}
	}
}

//输入页号查询
function inputPage1(){
	var currentPage = $("#inputPage").val();
	getPage(currentPage);
}
</script>