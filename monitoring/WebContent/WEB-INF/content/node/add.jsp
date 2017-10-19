<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/common/taglibs.jsp"%>
<html>
<head>
	<title>SR节点配置信息</title>
	
	<script type="text/javascript">
		var sr_id = decodeURIComponent(getUrlParam("sr_id"));
		$(function(){			
			if(sr_id){
				$("#operate").val(sr_id);
				getNodeInfo();
			}
		});
		
		function getNodeInfo(){
			$.ajax({
				type:'post',
				url:'${ctx}/node/getNodeInfo',
				data:{sr_id:sr_id},
				datatype:'json',
				error:getnodeInfoSubmitError, //非必须
				success:getnodeInfoSubmitSuccess//非必须
			});
		}
		function getnodeInfoSubmitError(data){}
		
		function getnodeInfoSubmitSuccess(data){
			if(data.code == 1){
				$("#sr_id").val(data.sr_id);
				$("#sr_id").attr('disabled','');
				$("#sr_name").val(data.sr_name);
				$("#level").val(data.level);
				$("#mnos").val(data.mnos);
				city(data.provinceId,"",false);
				province(data.countryId,"",false);
				$("#city").val(data.cityId);
				$("#cityId").val(data.cityId);
				$("#cityName").val(data.cityName);
				$("#province").val(data.provinceId);
				$("#provinceId").val(data.provinceId);
				$("#provinceName").val(data.provinceName);
				$("#countryId").val(data.countryId);
				$("#country").val(data.countryId);
				$("#countryName").val(data.countryName);
				$("#resetBtn").remove();
			}else{
				alert(data.msg);
			}
		}
		
		function nodeInfoSubmit(){
			var sr_id=$.trim($("#sr_id").val());
			var sr_name=$.trim($("#sr_name").val());
			var level=$.trim($("#level").val());
			var mnos=$.trim($("#mnos").val());
			var city=$.trim($("#city").val());
			
			if(sr_id==''){
				$('#msg').text("SR节点ID不能为空");
				setTimeout(function(){ $('#msg').text(''); },2000);
				return;
			}
			if(sr_name==''){
				$('#msg').text("SR节点名称不能为空");
				setTimeout(function(){ $('#msg').text(''); },2000);
				return;
			}
			if(level==''){
				$('#msg').text("SR节点所属层不能为空");
				setTimeout(function(){ $('#msg').text(''); },2000);
				return;
			}
// 			if(mnos==''){
// 				$('#msg').text("SR节点所属运营商不能为空");
// 				setTimeout(function(){ $('#msg').text(''); },2000);
// 				return;
// 			}
			if(city==''){
				$('#msg').text("SR节点所属地市不能为空");
				setTimeout(function(){ $('#msg').text(''); },2000);
				return;
			}
			$('#submitBtn').attr('disabled','');
			$("#nodeInfo").ajaxSubmit({
				type:"post",
				url:"${ctx}/node/confirmNodeInfo",
				dataType:'json',//非必须.默认text
				async:false,
				error:nodeInfoSubmitError, //非必须
				success:nodeInfoSubmitSuccess//非必须
			});
		}
		
		function nodeInfoSubmitError(data){
			$('#msg').html(data.msg);
			$('#submitBtn').attr('disabled',false);
			return;
		}
		
		function nodeInfoSubmitSuccess(data){
			if(data.result == 'success'){
				alert(data.msg);
				location.href='${ctx}/node/view';
			}else{
				alert(data.msg);
				$('#submitBtn').attr('disabled',false);
			}
			//if(data.msg == '修改SR节点信息成功'){
			//	location.href='${ctx}/node/view';
			//}
		}
		
		//获取url参数
		function getUrlParam(sName)
		{	
			if(sName)
			{
				var sValue='';
				var re= new RegExp("\\b"+sName+"\\b=([^&=]+)");
				var st=null;
				st=window.location.search.match(re);
				if(st&&st.length==2)
				{	
					st[1]=st[1].replace(/^\s*|\s*$/g,'');
					sValue=st[1];
				}
				return sValue;
			}
			else
			{
				alert("参数不能为空");
				return false;
			}
		}
		
		function backBtn(){
			location.href='${ctx}/node/view';
		}
		
		function province(value, text, isInit){
			if(value){
				$.ajax({
					type:'post',
					url:'${ctx}/node/getProvinces',
					data:'countryId='+value,
					datatype:'json',
					async:false,
					error:function(){}, //非必须
					success:function(data){
						if(data.code == 1){
							var html = '';
							html += '<option value="">省份</option>';
							if(data.provinces.length){
								for(var i=1;i<data.provinces.length;i++){
									html += '<option value="'+data.provinces[i].value+'">'+data.provinces[i].text+'</option>';
								}
							}
							$("#province").html(html);
						}else{
							alert(data.msg);
						}
					}//非必须
				});
			}else{
				$("#city").html('<option value="">地级市</option>');
			}
		}
		
		$("#province").change(function(){
			
		})
		function city(value){
				$.ajax({
					type:'post',
					url:'${ctx}/node/getCitys',
					data:'provinceId='+value,
					datatype:'json',
					async:false,
					error:function(){}, //非必须
					success:function(data){
						if(data.code == 1){
							var html = '';
							html += '<option value="">地级市</option>';
							if(data.cities.length){
								for(var i=1;i<data.cities.length;i++){
									html += '<option value="'+data.cities[i].value+'">'+data.cities[i].text+'</option>';
								}
							}
							$("#city").html(html);
						}else{
							alert(data.msg);
						}
					}//非必须
				});
		}
		
		
		
		function citySelect(value,text){
			$("#cityId").val(value);
			$("#cityName").val(text);
		}
		
		function provinceSelect(value,text){
			$("#provinceId").val(value);
			$("#provinceName").val(text);
			city(value);
		}
	</script>
</head>
<body menuId="10">
<!--Action boxes-->
<div class="container-fluid">
  <hr>
  <div>
  	<form id="nodeInfo" onsubmit="nodeInfoSubmit();return false;">
	  	<ul>
	  		<li>
	  			<label class="text-label">SR节点ID:</label>
	  			<input type="text" name="sr_id" id="sr_id" style="width:205px;" /><input type="hidden" value="" id="operate" name="operate">
	  		</li>
	  		<li>
	  			<label class="text-label">SR节点名称:</label>
	  			<input type="text" name="sr_name" id="sr_name" style="width:205px;" />
	  		</li>
	  		<li>
	  			<label class="text-label">SR节点所属层:</label>
	  			<u:select id="level" value="${param.level}" placeholder="SR所属层面" dictionaryType="sr_level" />
	  		</li>
	  		<li>
	  			<label class="text-label">SR节点所属运营商:</label>
	  			<u:select id="mnos" value="${param.mnos}" placeholder="节点所属运营商" dictionaryType="sr_mnos" />
	  		</li>
	  		<li>
	  			<label class="text-label">SR节点所属区域:</label>
	  			<u:select id="country" value="${param.country}" sqlId="country" placeholder="请选择省份" style="width:105px" callback="province"></u:select>
	  			
	  			<!-- <input type="text" name="area" id="area" style="width:205px;" /> -->
	  			 
	  			  <select style="width:110px" id="province" onchange="provinceSelect(this.options[this.options.selectedIndex].value,this.options[this.options.selectedIndex].text)" >
	  			 	<option value="">省份</option>
	  			 </select>
	  			 <!--<u:select id="city" value="${param.city}" sqlId="city" sqlParams="" placeholder="请选择城市" style="width:111px"></u:select>-->
	  			 <select style="width:110px" id="city" onchange="citySelect(this.options[this.options.selectedIndex].value,this.options[this.options.selectedIndex].text)" >
	  			 	<option value="">地级市</option>
	  			 </select>
	  			 <input type="hidden" name="cityId" id="cityId" />
	  			 <input type="hidden" name="cityName" id="cityName" />
	  			 <input type="hidden" name="provinceId" id="provinceId" />
	  			 <input type="hidden" name="provinceName" id="provinceName" />
	  			 <input type="hidden" name="countryId" id="countryId" />
	  			 <input type="hidden"  name="countryName" id="countryName" />
	  		</li>
	  		<!-- <li>
	  			<label class="text-label">SR节点大区编号:</label>
	  			<input type="text" name="region" id="region" style="width:205px;" />
	  		</li> -->
	  		<li>
	  			<input type="submit" value="提交" id="submitBtn" style="background-color: #da542e;color: #fff;font-weight: bold;line-height: 14px;padding: 5px 10px;width:80px;border:none;margin-top:20px;"/>
	  			<input type="reset" value="重置" id="resetBtn" style="background-color: #da542e;color: #fff;font-weight: bold;line-height: 14px;padding: 5px 10px;width:80px;border:none;margin-top:20px"/>
	  			<input type="button" value="返回" onclick="backBtn()" style="background-color: #da542e;color: #fff;font-weight: bold;line-height: 14px;padding: 5px 10px;width:80px;border:none;margin-top:20px"/>
	  		</li>
	  		<li>
	  			<label id="msg" style="color:red"></label>
	  		</li>
	  	</ul>
  	</form>
  </div>
</div>
</body>
</html>