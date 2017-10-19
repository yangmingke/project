<input type="text" id="${id}_input" name="${id}_input" placeholder="${placeholder}" onclick="${id}_showTree()" readonly="readonly" class="txt_250" />
<input type="hidden" id="${id}" name="${id}" value="${value}" />
<input type="hidden" id="${id}_name" name="${id}_name" />
<input type="hidden" id="${id}_pid" name="${id}_pid" />
<input type="hidden" id="${id}_pname" name="${id}_pname" />

<script type="text/javascript">
//zTree对象
var ${id}_zTree;

//树形下拉框的数据
var ${id}_zNodes = ${data};

//树形下拉框的配置
var ${id}_setting = {
	view: {
		dblClickExpand: false
	},
	data: {
		simpleData: {
			enable: true
		}
	},
	callback: {
		beforeClick: ${id}_beforeClick,
		onClick: ${id}_onClick
	}
};

//用于捕获单击节点之前的事件回调函数，并且根据返回值确定是否允许单击操作
function ${id}_beforeClick(treeId, treeNode) {
	/*
	if (!treeNode || treeNode.isParent){//不能选择父节点
		return false;
	}
	return true;
	*/
	<#if beforeClick??>
		return ${beforeClick}(treeId, treeNode);
	<#else>
		return true;
	</#if>
}

//用于捕获节点被点击的事件回调函数
function ${id}_onClick(e, treeId, treeNode) {
	var zTree = ${id}_zTree,
	nodes = zTree.getSelectedNodes(),
	id = "",
	name = "";
	pid = "",
	pname = "";
	nodes.sort(function compare(a,b){return a.id-b.id;});
	for (var i=0, l=nodes.length; i<l; i++) {
		id += "," + nodes[i].id;
		name += "," + nodes[i].name;
		var parentNode = nodes[i].getParentNode();
		if(parentNode!=null){
			pid += "," + parentNode.id;
			pname += "," + parentNode.name;
		}
	}
	if (id!="")
		id = id.substring(1);
	if (name!="")
		name = name.substring(1);
	if (pid!="")
		pid = pid.substring(1);
	if (pname!="")
		pname = pname.substring(1);
	
	<#if showPname>
		$("#${id}_input").attr("value", pname=="" ? name : pname+"-"+name);
	<#else>
		$("#${id}_input").attr("value", name);
	</#if>
	$("#${id}").attr("value", id);
	$("#${id}_name").attr("value", name);
	$("#${id}_pid").attr("value", pid);
	$("#${id}_pname").attr("value", pname);
	
	<#if onClick??>
		${onClick}(id, name, pid, pname);
	</#if>
}

//显示树
function ${id}_showTree() {
	var inputObj = $("#${id}_input");
	var inputOffset = inputObj.offset();
	$("#div_${id}").css({left:inputOffset.left + "px", top:inputOffset.top + inputObj.outerHeight() + "px"}).slideDown("fast");

	$("body").bind("mousedown", ${id}_onBodyDown);
}

//隐藏树
function ${id}_hideTree() {
	$("#div_${id}").fadeOut("fast");
	$("body").unbind("mousedown", ${id}_onBodyDown);
}

//点击页面其他地方
function ${id}_onBodyDown(event) {
	if (!(event.target.id == "menuBtn" || event.target.id == "div_${id}" || $(event.target).parents("#div_${id}").length>0)) {
		${id}_hideTree();
	}
}

$(function(){
	if($("#div_${id}").length == 0){
		$("body").append("<div id='div_${id}' class='div_ztree'><ul id='ul_${id}' class='ztree'></ul></div>");
	}
	${id}_zTree = $.fn.zTree.init($("#ul_${id}"), ${id}_setting, ${id}_zNodes);//初始化
	
	var value="${value}";
	if(value!=""){
		var zTree = ${id}_zTree;
		var nodes = zTree.getNodesByParam("id", value, null);
		if (nodes.length>0) {
			zTree.selectNode(nodes[0]);//初始化选中
			var parentNode = nodes[0].getParentNode(),
			name = nodes[0].name;
			pid = "",
			pname = "";
			if(parentNode!=null){
				pid=parentNode.id;
				pname=parentNode.name;
			}
			
			<#if showPname>
				$("#${id}_input").attr("value", pname=="" ? name : pname+"-"+name);
			<#else>
				$("#${id}_input").attr("value", name);
			</#if>
			$("#${id}_name").val(name);
			$("#${id}_pid").val(pid);
			$("#${id}_pname").val(pname);
		}
	}
});
</script>