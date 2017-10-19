<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/common/taglibs.jsp"%>

<u:authority menuId="110">
	<s:set var="menuId_110" value="true"/>
</u:authority>

<html>
<head>
	<title>测试号码</title>
</head>

<body>
      <div class="main_search">
        <form method="post" id="mainForm" action="${ctx}/client/testNum">
          <ul>
            <li>
              <input type="text" name="text" value="<s:property value="#parameters.text"/>"  placeholder="开发者账号" class="txt_358" />
            </li>
            <li>
              <input type="submit" value="查 询" class="search" />
            </li>
          </ul>
        </form>
      </div>
      <div class="clear">
      </div>
      <div class="table_ctn">
        <table cellpadding="0" cellspacing="0" border="0">
          <tbody>
            <tr>
              <th width="50px">序号</th>
              <th>client账号</th>
              <th>联系手机</th>
              <th>操作</th>
            </tr>
            <s:iterator value="page.list">
	            <tr>
	            	<td>${rownum}</td>
	            	<td>${client_number}</td>
	            	<td><input type="text" id="test_id_${rownum}"  value="${test_id}"/></td>
					<td>
				      	<s:if test="menuId_110">
							<a href="javascript:;" onclick="saveTestId(${rownum},'${nbr_id}','${sid}','${app_sid}','${client_number}')">保存</a>
						</s:if>
					</td>
	            </tr>
            </s:iterator>
          </tbody>
        </table>
      </div>
 <script type="text/javascript">
	function saveTestId(rownum,nbr_id,sid,app_sid,client_number){
		var test_id = $("#test_id_" + rownum).val();
		if(!/^1[0-9]{10}$/.test(test_id)){
			alert("请输入标准的手机号");
			return ;
		};
		$.post("${ctx}/client/bindTestNum",{
			'nbr_id':nbr_id,
			'sid':sid,
			'app_sid':app_sid,
			'client_number':client_number,
			'test_id':test_id
		},function(data){
			alert(data.msg);
		},"json");
	};
</script>
</body>
</html>