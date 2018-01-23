<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/common/taglibs.jsp"%>

<html>
<head>
	<title>计费类型</title>
</head>

<body>
	<div class="main_search">
        <form method="post" id="mainForm" action="${ctx}/feeType/feeTypeConfig" >
          <ul>
            <li>
              <input type="text" name="text" value="<s:property value="#parameters.text"/>" placeholder="计费类型名称" class="txt_177" />
            </li>
            <li>
              <input type="submit" value="查 询" class="search" />
            </li>
          </ul>
        </form>
      </div>
      <div class="clear"></div>
      <div class="table_ctn">
        <table class="noEdge">
          <tbody>
            <tr>
              <th width="50px">序号</th>
              <th>计费编号</th>
              <th>类型名称</th>
              <th>开发者数量</th>
              <th>创建时间</th>
              <th>修改时间</th>
              <th>操作</th>
            </tr>
            <s:iterator value="page.list">
	            <tr id="tr_${rownum}">
	            	<td tit="v_rownum">${rownum}</td>
	            	<td tit="v_fee_type_id">${fee_type_id}</td>
	            	<td tit="v_fee_type_name">${fee_type_name}</td>
	            	<td tit="v_count">${count}</td>
	            	<td tit="v_create_time">${create_time}</td>
	            	<td tit="v_update_time">${update_time}</td>
	            	<td>
						<a href="javascript:;" onclick="view('${fee_type_id}')">查看计费项目</a>
					</td>
	            </tr>
	        </s:iterator>
          </tbody>
        </table>
		<u:page page="${page}" formId="mainForm" />
      </div>

<script type="text/javascript">
	function view(feeTypeId){
		window.location.href = "${ctx}/feeType/feeTypeItem?feeTypeId="+feeTypeId;
	}
</script>
</body>
</html>