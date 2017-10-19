	<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
	<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
	<!--底部footer bof-->
	<div class="footer">
		<div class="footer_side">
			<div class="side1">&nbsp;</div>
			<div class="side2">&nbsp;</div>
		</div>
		<c:if test="${user.superSid == null}">
			<div class="copyright">
				<p>© 深圳市快传技术有限公司. All Rights Reserved </p>
				<p>粤ICP备16104934号</p>
			</div>
		</c:if>
	</div>
	<!--数据分析：-->
	<!-- WPA Button Begin -->
	<script charset="utf-8" type="text/javascript" src="http://wpa.b.qq.com/cgi/wpa.php?key=XzkzODA2NDAwMF8yNDE4NTdfNDAwMDk3MDAyMF8"></script>
	<!-- WPA Button END -->
	<!--底部footer eof-->
	
	<!--
	訪客找回統計
	-->
	<script type="text/javascript">
	var _py = _py || [];
	_py.push(['a', '0N..Y89v2vQ98C5_Bxxg0nNI70']);
	_py.push(['domain','stats.ipinyou.com']);
	_py.push(['e','']);
	-function(d) {
	var s = d.createElement('script'),
	e = d.body.getElementsByTagName('script')[0]; e.parentNode.insertBefore(s, e),
	f = 'https:' == location.protocol;
	s.src = (f ? 'https' : 'http') + '://'+(f?'fm.ipinyou.com':'fm.p0y.cn')+'/j/adv.js';
	}(document);
	</script>
	<noscript><img src="//stats.ipinyou.com/adv.gif?a=0N..Y89v2vQ98C5_Bxxg0nNI70&e=" style="display:none;"/></noscript>
