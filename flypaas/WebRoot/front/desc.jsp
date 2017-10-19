<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="s"  uri="/struts-tags"%>
<s:iterator value="descList" id="dc" status="index">
	<p>
		<label>版本号：</label>V<s:property value="#dc.version_index" />
	</p>
	<p>
		<label>更新时间：</label><s:date name="#dc.create_date" format="yyyy-MM-dd"/>
	</p>
	<pre style="font-family:微软雅黑;"><s:property value="#dc.version_desc"/></pre>
		<!--<dl class="step step${index.index+1 }">
  			<dt>版本：V<s:property value="#dc.version_index" />&nbsp;&nbsp;<s:date name="#dc.create_date" format="yyyy-MM-dd"/></dt>
  			<dd>
  				<div style="height:250px; overflow:auto; ">
  				<pre style="font-family:微软雅黑;"><s:property value="#dc.version_desc"/></pre>
  				</div>
  			</dd>
  		</dl>-->
</s:iterator>