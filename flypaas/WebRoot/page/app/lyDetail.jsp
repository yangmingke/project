<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="s"  uri="/struts-tags"%>
<%@ taglib prefix="u" uri="/flypaas-tags" %>
<table id="ly_csm_div" cellpadding="0" cellspacing="0" border="0" width="100%">
			<tr>
              <th width="17%">主叫</th>
              <th width="17%">被叫</th>
              <th width="32%">文件名</th>
              <th width="17%">录音大小</th>
              <th width="17%">操作</th>
            </tr>
              <s:if test="page.list!=null&&page.list.size()>0">
            	<s:iterator value="page.list" var="t">
	            <tr>
	            	<td>${t.from_number }</td>
	            	<td>${t.to_number }</td>
	            	<td>${t.file_name }</td>
	            	<td>${t.file_size } M</td>
	            	<td><a href="/download/lyFile?path=<u:des3 value='${t.remote_path }'/>">下载</td>
	            </tr>
	            </s:iterator>
            </s:if>
</table>
<form action="/app/lyDetail" id="lyDetailFrm" name="lyDetailFrm">
	<input type="hidden" name="statId" value="${statId}">
</form>
<u:page page="${page}" formId="lyDetailFrm" ajax="1" divId="td_list" dataDivId="ly_csm_div"></u:page>