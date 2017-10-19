<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/common/taglibs.jsp"%>
<%--页面左边3级菜单 --%>

<s:if test="data.menu3 != null">
	<div class="side_menu">
		<ul>
			<s:iterator value="data.menu3">
				<li class="${menu_class}" id="side_menu_${menu_id}">
					<s:if test="menu_url==null||menu_url==''">
						<s:set var="url">javascritp:;</s:set>
					</s:if>
					<s:else>
						<s:set var="url">${ctx}${menu_url}</s:set>
					</s:else>
					<a href="${url}" title="${menu_name}"><i>&nbsp;</i>${menu_name}</a>
				</li>
			</s:iterator>
		</ul>
	</div>
</s:if>