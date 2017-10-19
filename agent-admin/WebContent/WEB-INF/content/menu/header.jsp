<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/common/taglibs.jsp"%>

<!--公共头部header bof-->
<div class="header">
	<div class="header_wrapper">
		<div class="u_logo"><a href="${ctx}/admin/view"><img src="${ctx}/images/u_logo.png"></a></div>
		<div class="menu" id="menu">
			<ul>
				<s:iterator value="data.menu1">
					<li class="${menu_class}" id="main_menu_${menu_id}">
						<a href="javascript:;" title="${menu_name}" onclick="selectMenu1(this, '${menu_id}')">
							<i>&nbsp;</i>${menu_name}
						</a>
					</li>
				</s:iterator>
			</ul>
		</div>
		<div class="header_link">
			<%-- <span><a href="${ctx}/admin/view">管理员中心</a></span> --%>
			<span><a href="${ctx}/msg/query?myself=1" id="header_msg">消息(0)</a></span>
			<a href="${ctx}/logout">退出</a>
		</div>
	</div>
	<div class="clear"></div>
	<div class="sub_menu" style="display:none;" id="sub_menu_${data.menu2[0].parent_id}"><span>&nbsp;</span>
	
		<s:set var="parentId" value="-1"/>
		<s:iterator value="data.menu2">
			<s:if test="#parentId!=-1 && #parentId!=parent_id">
				</div>
				<div class="sub_menu" style="display:none;" id="sub_menu_${parent_id}"><span>&nbsp;</span>
			</s:if>
			<s:set var="parentId" value="parent_id"/>
			
			<s:if test="menu_url==null||menu_url==''">
				<s:set var="url">javascritp:;</s:set>
			</s:if>
			<s:else>
				<s:set var="url">${ctx}${menu_url}</s:set>
			</s:else>
			<a href="${url}" title="${menu_name}" id="sub_menu_a_${menu_id}">${menu_name}</a><span>&nbsp;</span>
		</s:iterator>
	</div>
</div>
<!--公共头部header eof--> 