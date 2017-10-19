<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/common/taglibs.jsp"%>

<!--sidebar-menu-->

<div id="sidebar" class="sidebar">
	<ul>
	<s:iterator value="data.menuRoot.subMenus">
	 	<li class="submenu" id="menu_id_${menu_id}" pid="${parent_id}" title="${menu_name}" ><a href="${empty menu_url?'':ctx}${empty menu_url?'#':menu_url}"><span>${menu_name}</span></a>
			<ul>
				<s:iterator value="subMenus" var="sub">
					<li class="subsubmenu" id="menu_id_${menu_id}" pid="${sub.parent_id}"  title="${sub.menu_name}" ><a href="#" rel="${empty menu_url?'':ctx}${empty menu_url?'#':menu_url}" title="${sub.menu_name}">${sub.menu_name}</a>
					<ul>
					<s:iterator value="subMenus" var="sub" status="index">
						<li id="menu_id_${menu_id}" pid="${sub.parent_id}"  title="${sub.menu_name}"><a href="${empty menu_url?'':ctx}${empty menu_url?'#':menu_url}">${sub.menu_name}</a></li>
					</s:iterator>
					</ul>
					</li>
				</s:iterator>
			</ul>
		</li>
	</s:iterator>
  </ul>
</div>
<!--sidebar-menu-->
