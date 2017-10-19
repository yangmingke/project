<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=edge" />
<title>提示</title>
<style type="text/css">
.reuter_page { padding: 200px 0px; width: 600px; margin: 0 auto;}
.reuter_page ul li { float: left; display: inline;}
.reuter_page ul li.num { color: #ed6d49; font-size: 65px; margin-right: 30px;}
.reuter_page ul li.txt { margin-top: 25px;color: #000000; font-size: 30px;letter-spacing: 3px;}
.reuter_page ul li.txt .deadline{color: #f70006; font-size: 50px;font-weight: bold;}
.reuter_page ul li h2 { color: #333;}
.reuter_page ul li p { line-height: 25px;}
</style>
</head>
<body onload='setTimeout("mm()",5000)'>
<script type="text/javascript"> 
	function mm(){    
		window.open('','_self','');   
		window.close(); 
	}
</script> 
<div class="content">
  <div class="content_wrapper">
    <div class="content_box">
      <div class="reuter_page">
        <ul>
          <li class="num">邮件链接已经失效</li>
          <li class="txt">本页面<span class="deadline">&nbsp;5&nbsp;</span>秒后将自动关闭</li>
        </ul>
      </div>
    </div>
  </div>
</div>
</body>
</html>
