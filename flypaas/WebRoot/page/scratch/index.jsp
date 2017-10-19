<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="s"  uri="/struts-tags"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!doctype html>
<html>
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=no">
<meta content="telephone=no" name="format-detection">
<title>快传刮奖</title>
<link rel="stylesheet" href="<%=path%>/css/scratch/main.css" />
</head>
<body>
<div class="main">
  <div class="banner"><img src="<%=path%>/images/scratch_banner.png" width="100%" /></div>
  <div class="shave_area">
    <div class="shave_wrap">
	      <canvas id="myCanvas" class="myCanvas"> your browser does not support the canvas element </canvas>
	      <div class="luck">
	      
	      </div>
    </div>
  </div>
  <div class="link"><a href="<%=path %>/user/toSign" class="enter" target="_blank"><img src="<%=path%>/images/link1.png" width="100%" /></a><a href="<%=path %>/page/doc/doc_guide7-1.jsp" target="_blank" class="step"><img src="<%=path%>/images/link2.png" width="100%" /></a></div>
  <div class="code"><img src="<%=path%>/images/code.png" /></div>
</div>
<div class="footer" id="footer">
  <p>活动最终解释权归深圳市快传技术有限公司所有</p>
  <p>www.flypaas.com</p>
</div>
<script type="text/javascript" src="<%=path%>/js/zepto.js"></script>
<script type="text/javascript">
$(document).ready(function () {
	var shave_w = $(".shave_wrap").width();
	$(".shave_wrap").height(shave_w/2);
    var shave_h = $(".shave_wrap").height();
	$("#myCanvas,.luck").width(shave_w);
	$("#myCanvas,.luck").height(shave_h);
	$(window).resize(function(){
		var shave_w = $(".shave_wrap").width();
		$(".shave_wrap").height(shave_w/2);
	    var shave_h = $(".shave_wrap").height();
		$("#myCanvas,.luck").width(shave_w);
		$("#myCanvas,.luck").height(shave_h);
	})
	var hasTouch = 'ontouchstart' in window;
	var canvas;
	var ctx;
	var lines = [];
	var points = [];
	
	    var offset = $("#myCanvas").offset();
	    var canvas_left = offset.left;
	    var canvas_top = offset.top;

	    canvas = document.getElementById('myCanvas');
	    ctx = canvas.getContext('2d');
	    var img=new Image();
	     img.onload = function () {  
                ctx.drawImage(img, 0, 0, 300, 150);  
        }  
        img.src = "<%=path%>/images/shave_bg.png";


	    //移动
	    var START_EV = hasTouch ? 'touchstart' : 'mousedown';
	    var MOVE_EV = hasTouch ? 'touchmove' : 'mousemove';
	    var END_EV = hasTouch ? 'touchend' : 'mouseup';
	    document.getElementById("myCanvas").addEventListener(START_EV, touchCheck, false);
	    document.getElementById("myCanvas").addEventListener(MOVE_EV, touchCheck, false);
	    document.getElementById("myCanvas").addEventListener(END_EV, touchCheck, false);
	    var startTouch;
	    var isDrag = false;
	    var lastPoint;
	    function touchCheck(evt) {
	        evt.preventDefault();
	        var touchX;
	        var touchY;
	        if (hasTouch) {
	            if (evt.touches.length > 0) {
	                touchX = evt.touches[0].pageX - canvas_left;
	                touchY = evt.touches[0].pageY - canvas_top;
	            }
	            else {
	                if (lastPoint) {
	                    touchX = lastPoint.x;
	                    touchY = lastPoint.y;
	                }
	            }
	        }
	        else {
	            touchX = evt.pageX - canvas_left;
	            touchY = evt.pageY - canvas_top;
	        }
	        
	        switch (evt.type) {
	            case START_EV:
	                DrawLine();
	                points = [];
	                points.push({ x: touchX, y: touchY });
	                isDrag = true;
	                lastPoint = { x: touchX, y: touchY };
					//alert('按下')
	                break;
	            case MOVE_EV:
	                if (isDrag) {
	                    points.push({ x: touchX, y: touchY });
	                    lastPoint = { x: touchX, y: touchY };
	                    DrawLine();
	                }
	                break;
	            case END_EV:
	                isDrag = false;
	                if (points.length > 0) {
	                    points.push({ x: touchX, y: touchY });
	                    lines.push(points);
	                    DrawLine();
						//alert('刮开')
	                }
	                break;
	        }
	    }
	    function DrawSingleLine(ctx, line) {
	        for (var j = 0; j < line.length; j++) {
	            var point = line[j];
	            if (j == 0)
	                ctx.moveTo(point.x, point.y);
	            else
	                ctx.lineTo(point.x, point.y);
	        }
	    }
	    function DrawLine() {
	        ctx.globalCompositeOperation = "source-over";
	        ctx.globalCompositeOperation = "destination-out";
	        ctx.beginPath();
	        ctx.lineWidth = 10;
	        var tempLines = lines;
	        if (tempLines.length == 0) {
	            tempLines.push(points);
	        }
	        for (var i = 0; i < tempLines.length; i++) {
	            var line = tempLines[i];
	            DrawSingleLine(ctx, line);
	        }
	        if (isDrag && points.length > 0)
	            DrawSingleLine(ctx, points);
	        ctx.stroke();
	        ctx.closePath();
	    }
	    var meetId = "${meetId}" ;
	    $.ajax({
	    	url:"<%=path%>/coupon/couponDate",
			type:"post",
			data:"meetId="+meetId,
			dataType: "text",
			success: function (data) {
			 if(data!=null){
				 var json = eval("("+data+")");
		         var money = json.money;
		         var couponnum = json.couponnum;
		         var htm = "" ;
		         if(money!=0){
		        	 htm = "<span class=\"money\">￥"+money+"</span><br />&nbsp;&nbsp;&nbsp;&nbsp;兑换码："+couponnum;
		         }else{
		        	 htm = "<span class=\"money money_none\">啊哦，人品不及格，再试一次吧</span>";
		        	
		         }
			 }else{
				 htm = "<span class=\"money money_none\">本次展会的兑换码已发完！</span>";
			 }
	        $(".luck").append(htm);
	        $(".money_none").width(shave_w);
     		$(".money_none").height(shave_h);
     	    $(".money_none").css("margin-top",shave_h/3);
     	   	$(window).resize(function(){
	     	   	$(".money_none").width(shave_w);
	     		$(".money_none").height(shave_h);
	     	    $(".money_none").css("margin-top",shave_h/3);
	   	    });
	        }
	    });
	   
	    
	});
</script>
</body>
</html>
