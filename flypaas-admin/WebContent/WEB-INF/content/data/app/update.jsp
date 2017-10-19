<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/common/taglibs.jsp"%>

<html>
<head>
	<title>应用管理 - 查看</title>
	<script type="text/javascript" src="${ctx}/js/jquery/jquery.jplayer.min.js"></script>
	<script type="text/javascript">
		$(function(){
			var obj = $("#jquery_jplayer");
			obj.jPlayer({ready: function () {
				            $(this).jPlayer("setMedia", {
				                mp3: ""
				            });
				        },
				        swfPath: "swf",
				        supplied: "mp3"
				    });
			play_fun  = function (url){
				obj.jPlayer("setMedia", { mp3: url});
				obj.jPlayer("play");
			}
		});
	</script>
	
	<style type="text/css">
		select.developer_select{
			width:358px;
			height: 46px;
			margin: 2px 0px;
		}
    </style>
</head>

<body>
	<h1><a href="javascript:;" class="commit" onclick="commit()">提交</a><a href="javascript:;" class="back" onclick="back()">取消</a>应用管理/查看</h1>
      <div class="main_ctn1 qualification_view">
        <table cellpadding="0" cellspacing="0" border="0">
          <tbody>
            <tr>
              <th>应用id</th>
              <th>应用名称</th>
              <th>应用行业</th>
              <th>应用类型</th>
              <th>提交时间</th>
              <th>归属开发者</th>
              <th>审核状态</th>
            </tr>
            <tr>
              <td>${data.app.app_sid}</td>
              <td>${data.app.app_name}</td>
              <td><u:ucparams key="${data.app.industry}" type="industry"/></td>
	          <td><u:ucparams key="${data.app.app_kind}" type="app_kind"/></td>
              <td>${data.app.update_date}</td>
              <td>${data.app.email}</td>
              <td id="status_name"><u:ucparams key="${data.app.status}" type="app_status"/></td>
            </tr>
          </tbody>
        </table>
        <form id="appForm" action="${ctx}/app/updateApp">
        	<input value="${data.app.app_sid}" name="app_sid" hidden="hidden"/>
        	<table cellpadding="0" cellspacing="0" border="0" class="table_2">
				<tbody>
					<tr>
						<th>路由域</th>
						<th><input type="text" id="route_area" name="route_area" value="${data.app.route_area}"/></th>
					</tr>
				</tbody>
			</table>
	        <table cellpadding="0" cellspacing="0" border="0" class="table_2">
				<tbody>
					<tr>
						<th>传输网络</th>
						<th>
							<select class="developer_select" name="ver_can">
								<option value="v3" <s:if test="data.app.ver_can == 'v3'">selected="selected"</s:if> >flySR</option>
								<option value="v4" <s:if test="data.app.ver_can == 'v4'">selected="selected"</s:if>>flyCAN</option>
							</select>
						</th>
					</tr>
				</tbody>
			</table>
			<table cellpadding="0" cellspacing="0" border="0" class="table_2">
				<tbody>
					<tr>
						<th>路由最大跳数</th>
						<th><input type="text" id="max_hop_num" name="max_hop_num" value="${data.app.max_hop_num}" onkeyup="this.value=this.value.replace(/[^0-9]/g,'')"/>次</th>
					</tr>
				</tbody>
			</table>
			<table cellpadding="0" cellspacing="0" border="0" class="table_2">
				<tbody>
					<tr>
						<th>会话路由数</th>
						<th><input type="text" id="route_num" name="route_num" value="${data.app.route_num}" onkeyup="this.value=this.value.replace(/[^0-9]/g,'')"/>条</th>
					</tr>
				</tbody>
			</table>
			<table cellpadding="0" cellspacing="0" border="0" class="table_2">
				<tbody>
					<tr>
						<th>路由策略</th>
						<th>
							<select class="developer_select" name="route_policy">
								<option value="1" <s:if test="data.app.route_policy == 1">selected="selected"</s:if>>质量优先</option>
								<option value="2" <s:if test="data.app.route_policy == 2">selected="selected"</s:if>>价格低优先</option>
								<option value="3" <s:if test="data.app.route_policy == 3">selected="selected"</s:if>>性价比高优先</option>
							</select>
						</th>
					</tr>
				</tbody>
			</table>
			<table cellpadding="0" cellspacing="0" border="0" class="table_2">
				<tbody>
					<tr>
						<th>节点最高单价</th>
						<th><input type="text" id="node_max_price" name="node_max_price" value="${data.app.node_max_price}" onkeyup="this.value=this.value.replace(/[^0-9]/g,'')"/>分/KB</th>
					</tr>
				</tbody>
			</table>
		</form>
       	<table cellpadding="0" cellspacing="0" border="0" class="table_2">
          <tbody>
            <tr>
              <th>回调类型</th>
              <th>回调地址</th>
            </tr>
            <s:iterator value="data.callback">
	            <tr>
	              <td class="tb_tit">${call_type_name}</td>
	              <td class="tb_ctn">${call_address}</td>
	            </tr>
            </s:iterator>
          </tbody>
        </table>
        <table cellpadding="0" cellspacing="0" border="0" class="table_2">
          <tbody>
            <tr>
              <th class="tb_tit">服务器白名单</th>
              <th class="tb_ctn">${data.app.white_address}</th>
            </tr>
          </tbody>
        </table>
        <table cellpadding="0" cellspacing="0" border="0" class="table_2">
          <tbody>
            <tr>
              <th>显号模式</th>
              <th>${data.app.is_show_nbr==1 ? "开启" : "关闭"}</th>
            </tr>
			<s:iterator value="data.showbrs">
				<s:if test="1==nbr_type">
					<tr>
		              <td class="tb_tit">官号</td>
		              <td class="tb_ctn">${nbr}</td>
		            </tr>
				</s:if>
            </s:iterator>
            
            <s:iterator value="data.showbrs">
	            <s:if test="2==nbr_type">
					<tr>
		              <td class="tb_tit">语音验证码</td>
		              <td class="tb_ctn">${nbr}</td>
		            </tr>
				</s:if>
            </s:iterator>
          </tbody>
        </table>


		<table cellpadding="0" cellspacing="0" border="0" class="table_2">
			<tbody>
				<tr>
					<th>分配余额</th>
					<th>${data.app.app_balance}</th>
				</tr>
			</tbody>
		</table>

		<table cellpadding="0" cellspacing="0" border="0" class="table_2">
          <tbody>
            <tr>
              <th>国际通话业务</th>
              <th>${data.app.call_fr==1 ? "开启" : "关闭"}</th>
            </tr>
			<tr>
              <td class="tb_tit">智能验证秘钥</td>
              <td class="tb_ctn">${data.app.ck_key}</td>
            </tr>
            <tr>
              <td class="tb_tit">智能验证回调地址</td>
              <td class="tb_ctn">${data.app.ck_callback_url}</td>
            </tr>
            <tr>
              <td class="tb_tit">智能验证有效时间</td>
              <td class="tb_ctn">${data.app.ck_enddate}分钟</td>
            </tr>
           <tr>
              <td class="tb_tit">智能验证验证类型</td>
              <td class="tb_ctn"><u:ucparams key="${data.app.ck_way}" type="cloud_check"/></td>
            </tr>
             <tr>
              <td class="tb_tit">身份验证最大值</td>
              <td class="tb_ctn">${data.app.ck_num}次</td>
            </tr>
            
          </tbody>
        </table>
        
        <div id="jquery_jplayer" class="jp-jplayer"></div>
        <table cellpadding="0" cellspacing="0" border="0" class="table_2">
          <tbody>
          	<tr>
              <th>铃声类型</th>
              <th>操作</th>
            </tr>
	          <s:iterator value="data.rings">
						<tr>
			              <td class="tb_tit"><u:ucparams key="${type}" type="ring_type"/></td>
			              <td class="tb_ctn"><a href="javascript:;" onclick="play_fun('${ctx}/file/view?path=${RING_BASE}/${file_name}');">播放</a></td>
			            </tr>
	            </s:iterator>
          </tbody>
        </table>
      </div>
      <script type="text/javascript">
      	function saveBrand(){
      		var brand = $("#brand").val();
      		if(/^[0-9A-Za-z]{1,7}$/.test(brand)){
      			$.post("${ctx}/app/saveBrand",{
      				'app_sid':'${data.app.app_sid}',
      				'brand':brand},function(data){
      					if(data.result==null){
      						alert("服务器错误，请联系管理员").show();
      						return;
      					}
      					alert(data.msg).show();
      			});
      		}else{
      			alert("请正确填写品牌,0到7位数字或字母");
      		}
      	};
      	
      	function commit(){
  			var max_hop_num = $('#max_hop_num').val();
  			var route_num = $('#route_num').val();
  			var node_max_price = $('#node_max_price').val();
  			var route_area = $('#route_area').val();
  			if(max_hop_num =="" || route_num==""||node_max_price==""||route_area==""){
  				alert("输入框不能为空！");
  				return;
  			}
			$('#appForm').submit(); 			
      	};
      	
      </script>
</body>
</html>