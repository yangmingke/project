<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/common/taglibs.jsp"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
	<title>开发者时长计费</title>
</head>

<body>
	<div class="main_search">
        <form method="post" id="mainForm" action="${ctx}/developerAccount/feeTimeView">
        	<input value="${param.sid}" name="sid" hidden="hidden">
        	<input value="${param.realname}" name="realname" hidden="hidden">
        	<input value="${param.fee_type}" name="fee_type" hidden="hidden">
          <ul>
            <li class="time">
            	<u:date id="date" value="${param.date}"  maxToday="" dateFmt="yyyy-MM-dd"/>
            </li>
            <li>
              <input type="submit" value="查 询" class="search" />
              <a class="back" onclick="back2Query()" style="margin-top: 5px;"/>返回</a>
            </li>
          </ul>
        </form>
      </div>
      <div class="clear"></div>
      	<div style="font-size: 12px">
	      	开发者：<span id="realname"></span>（${param.sid}）
      	</div>
      <div class="table_ctn">
        <table class="noEdge">
          <tbody>
            <tr>
              <th width="50px">序号</th>
              <th>时长（S）</th>
              <c:if test="${param.fee_type==101}" >
              	<th>费率（分/G）</th>
              </c:if>
              <c:if test="${param.fee_type==102}" >
              	<th>费率（分/min）</th>
              </c:if>
              <th>费用（分）</th>
              <th>入流量（B）</th>
              <th>出流量（B）</th>
              <th>开始时间</th>
              <th>结束时间</th>
            </tr>
            <s:iterator value="page.list">
	            <tr id="tr_${rownum}">
	            	<td tit="v_rownum">${rownum}</td>
	            	<td tit="v_feetime">${feetime}</td>
	            	<td tit="v_userfeerate">${userfeerate}</td>
	            	<td tit="v_userfee">${userfee}</td>
	            	<td tit="v_total_traffic_in">${total_traffic_in}</td>
	            	<td tit="v_total_traffic_out">${total_traffic_out}</td>
	            	<td tit="v_starttime">${starttime}</td>
	            	<td tit="v_endtime">${endtime}</td>
	            </tr>
	        </s:iterator>
          </tbody>
        </table>
		<u:page page="${page}" formId="mainForm" />
      </div>
      <script type="text/javascript">
      	$(function(){
      		$('#realname').text(decodeURI('${param.realname}'));
      		if($('#date').val()==""){//首次进入页面赋值
      			$('#date').val(getNowFormatDate());
      		}
      	});
     	 //获取当前时间，格式YYYY-MM-DD
        function getNowFormatDate() {
            var date = new Date();
            var seperator1 = "-";
            var year = date.getFullYear();
            var month = date.getMonth() + 1;
            var strDate = date.getDate();
            if (month >= 1 && month <= 9) {
                month = "0" + month;
            }
            if (strDate >= 0 && strDate <= 9) {
                strDate = "0" + strDate;
            }
            var currentdate = year + seperator1 + month + seperator1 + strDate;
            return currentdate;
        }
     	 
     	function back2Query(){
     		window.location.href="/developerAccount/query";
     	}
     	
      </script>
</body>
</html>