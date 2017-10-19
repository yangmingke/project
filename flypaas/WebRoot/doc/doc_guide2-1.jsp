<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%><%@ taglib prefix="s"  uri="/struts-tags"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
	
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" /><meta http-equiv="X-UA-Compatible" content="IE=edge" />
<title>开发者协议</title>
<link rel="stylesheet" type="text/css" href="<%=path%>/doc/css/doc.css" /><%@include file="/front/resource.jsp"%><script type="text/javascript" src="<%=path%>/doc/js/doc.js"></script>
</head>

<body id="5">
<!--公共头部header bof-->
<%@include file="/front/head.jsp" %>

<!--公共头部header eof--> 

<!--中间部分middle bof-->
<div class="middle mid_box doc_box">
  <div class="display_wrapper">
    <div class="doc_menu">
      <div class="menu_tit">
        <h1><span class="home"><a href="<%=path%>/doc/doc.jsp">文档首页</a></span></h1>
      </div>
       <ul>
        <li class="active">
        	<span>新手指引<i class="parent">&nbsp;</i></span>
        	<dl>
                <dd><a href="<%=path%>/doc/doc_guide4-1.jsp">注册成为开发者</a></dd>
                <dd><a href="<%=path%>/doc/doc_guide5-1.jsp">管理中心使用指南</a></dd>   
				<dd><a href="<%=path%>/doc/doc_guide6-1.jsp">应用创建流程</a></dd>
                <dd><a href="<%=path%>/doc/doc_guide7-1.jsp">充值流程</a></dd>  
                <dd><a href="<%=path%>/doc/doc_guide3-1.jsp">应用审核规范</a></dd>				
                <dd><a href="<%=path%>/doc/doc_guide8-1.jsp">开发者资质审核规范</a></dd>                   
                <dd><a href="<%=path%>/doc/doc_guide2-1.jsp" class="active">开发者协议</a></dd>
        	</dl>
        </li>
        <li>
        	<span>REST API<i class="parent">&nbsp;</i></span>
        	<dl style="display:none;">
        		<dd><a href="<%=path%>/doc/doc_rest1-1.jsp">REST介绍</a></dd>
        		<dd class="parent">
        			<a href="javascript:void(0)"><em>账户管理</em></a>
        			<dl class="child">
        				<dd><a href="<%=path%>/doc/doc_rest2-1.jsp">开发者账号信息查询</a></dd>
        				<dd><a href="<%=path%>/doc/doc_rest2-2.jsp">申请Client账号</a></dd>
        				<dd><a href="<%=path%>/doc/doc_rest2-3.jsp">释放Client账号</a></dd>
        				<dd><a href="<%=path%>/doc/doc_rest2-4.jsp">获取Client账号</a></dd>
        				<dd><a href="<%=path%>/doc/doc_rest2-5.jsp">Client信息查询(Client账号)</a></dd>
                        <dd><a href="<%=path%>/doc/doc_rest2-9.jsp">Client信息查询(手机号码)</a></dd>
        				<dd><a href="<%=path%>/doc/doc_rest2-6.jsp">应用话单下载</a></dd>
        				<!--<dd><a href="<%=path%>/doc/doc_rest2-7.jsp">Client话单下载</a></dd>-->
        				<dd class="last"><a href="<%=path%>/doc/doc_rest2-8.jsp">Client账号充值</a></dd>
        			</dl>
        		</dd>
        		<dd class="parent">
        			<a href="javascript:void(0)"><em>呼叫</em></a>
        			<dl class="child">
        				<dd><a href="<%=path%>/doc/doc_rest3-1.jsp">双向回拨</a></dd>
        				<dd><a href="<%=path%>/doc/doc_rest3-2.jsp">语音验证码</a></dd>
						<dd class="last"><a href="<%=path%>/doc/doc_rest3-3.jsp">语音通知</a></dd>
        			</dl>
        		</dd>
        		<dd class="parent">
        			<a href="javascript:void(0)"><em>SMS</em></a>
        			<dl class="child">
        				<dd class="last"><a href="<%=path%>/doc/doc_rest4-1.jsp">短信验证码（模板短信）</a></dd>
        			</dl>
        		</dd>
        	</dl>
        </li>
        <li>
        	<span>Android SDK<i class="parent">&nbsp;</i></span>
        	<dl style="display:none;">
                <dd><a href="<%=path%>/doc/doc_android6-1.jsp">Android开发指南</a></dd>
        		<dd><a href="<%=path%>/doc/doc_android1-1.jsp">Android SDK介绍</a></dd>
        		<dd><a href="<%=path%>/doc/doc_android2-1.jsp">初始化及配置接口</a></dd>
        		<dd class="parent">
        			<a href="#"><em>VoIP接口</em></a>
        			<dl class="child">
        				<dd><a href="<%=path%>/doc/doc_android3-1.jsp">VoIP通话控制接口</a></dd>
        				<dd class="last"><a href="<%=path%>/doc/doc_android3-2.jsp">VoIP状态回调接口</a></dd>
        			</dl>
        		</dd>
        		<dd class="parent">
        			<a href="#"><em>IM接口</em></a>
        			<dl class="child">
        				<dd><a href="<%=path%>/doc/doc_android4-1.jsp">IM消息接口</a></dd>
        				<dd class="last"><a href="<%=path%>/doc/doc_android4-2.jsp">IM消息回调接口</a></dd>
        			</dl>
        		</dd>
                <dd><a href="<%=path%>/doc/doc_android7-1.jsp">视频通话控制接口</a></dd>
				<dd><a href="<%=path%>/doc/doc_android8-1.jsp">用户在线查询能力接口</a></dd>
                <dd><a href="<%=path%>/doc/doc_android9-1.jsp">智能验证接口</a></dd>
                <dd><a href="<%=path%>/doc/doc_android5-1.jsp">注意事项</a></dd>
        	</dl>
        </li>
         <li>
        	<span>iOS SDK<i class="parent">&nbsp;</i></span>
        	<dl style="display:none;">
                <dd><a href="<%=path%>/doc/doc_ios5-1.jsp">iOS开发指南</a></dd>
        		<dd><a href="<%=path%>/doc/doc_ios1-1.jsp">iOS SDK介绍</a></dd>
        		<dd class="parent">
        			<a href="#"><em>初始化及配置接口</em></a>
        			<dl class="child">
        				<dd><a href="<%=path%>/doc/doc_ios2-1.jsp">初始化及配置</a></dd>
        				<dd class="last"><a href="<%=path%>/doc/doc_ios2-2.jsp">初始化及配置代理接口</a></dd>
        			</dl>
        		</dd>
        		<dd class="parent">
        			<a href="#"><em>VoIP接口</em></a>
        			<dl class="child">
        				<dd><a href="<%=path%>/doc/doc_ios3-1.jsp">VoIP能力接口</a></dd>
        				<dd class="last"><a href="<%=path%>/doc/doc_ios3-2.jsp">VoIP能力代理接口</a></dd>
        			</dl>
        		</dd>
        		<dd class="parent">
        			<a href="#"><em>IM接口</em></a>
        			<dl class="child">
        				<dd><a href="<%=path%>/doc/doc_ios4-1.jsp">IM能力接口</a></dd>
        				<dd class="last"><a href="<%=path%>/doc/doc_ios4-2.jsp">IM能力代理接口</a></dd>
        			</dl>
        		</dd>
				<dd><a href="<%=path%>/doc/doc_ios6-1.jsp">视频通话能力接口</a></dd>
				<dd><a href="<%=path%>/doc/doc_ios7-1.jsp">用户在线状态能力接口</a></dd>
				<dd><a href="<%=path%>/doc/doc_ios8-1.jsp">用户在线状态能力代理接口</a></dd>
                <dd><a href="<%=path%>/doc/doc_ios9-1.jsp">智能验证接口</a></dd>
        	</dl>
        </li>
        <li>
        	<span>Windows SDK<i class="parent">&nbsp;</i></span>
        	<dl style="display:none;">
                <dd><a href="<%=path%>/doc/doc_windows5-1.jsp">Windows开发指南</a></dd>
        		<dd><a href="<%=path%>/doc/doc_windows1-1.jsp">Windows SDK介绍</a></dd>
        		<dd class="parent">
        			<a href="#"><em>初始化及配置接口</em></a>
        			<dl class="child">
        				<dd><a href="<%=path%>/doc/doc_windows2-1.jsp">初始化及配置</a></dd>
        				<dd class="last"><a href="<%=path%>/doc/doc_windows2-2.jsp">初始化及配置回调函数</a></dd>
        			</dl>
        		</dd>
        		<dd class="parent">
        			<a href="#"><em>VoIP接口</em></a>
        			<dl class="child">
        				<dd><a href="<%=path%>/doc/doc_windows3-1.jsp">VoIP能力接口</a></dd>
        				<dd class="last"><a href="<%=path%>/doc/doc_windows3-2.jsp">VoIP能力回调函数</a></dd>
        			</dl>
        		</dd>
        		<dd class="parent">
        			<a href="#"><em>IM接口</em></a>
        			<dl class="child">
        				<dd><a href="<%=path%>/doc/doc_windows4-1.jsp">IM能力接口</a></dd>
        				<dd class="last"><a href="<%=path%>/doc/doc_windows4-2.jsp">IM能力回调函数</a></dd>
        			</dl>
        		</dd>
				<dd><a href="<%=path%>/doc/doc_windows6-1.jsp">视频能力接口</a></dd>
        	</dl>
        </li>
        <li>
        	<span>应用服务器接口<i class="parent">&nbsp;</i></span>
        	<dl style="display:none;">
        		<!--<dd><a href="#">接收短信</a></dd>-->
        		<dd><a href="<%=path%>/doc/doc_server6-1.jsp">呼叫鉴权流程</a></dd>
        		<dd><a href="<%=path%>/doc/doc_server3-1.jsp">呼叫鉴权请求接口</a></dd>
        		<dd><a href="<%=path%>/doc/doc_server4-1.jsp">呼叫建立通知接口</a></dd>
        		<dd><a href="<%=path%>/doc/doc_server5-1.jsp">呼叫挂机计费接口</a></dd>
                <dd><a href="<%=path%>/doc/doc_server7-1.jsp">语音外呼状态通知接口</a></dd>
        	</dl>
        </li>
        <!--<li><span onclick="location.href='<%=path%>/doc/doc_demo1-1.jsp'">DEMO<i class="parent">&nbsp;</i></span></li>-->
        <li><!--<span onclick="location.href='doc_errorcode1-1.html'" style="background:#fff; width:188px;">错误代码<i class="parent">&nbsp;</i></span>-->
		    <span>错误代码<i class="parent">&nbsp;</i></span>
        	<dl style="display:none;">
        		<dd><a href="<%=path%>/doc/doc_errorcode1-1.jsp">Android错误码</a></dd>
        		<dd><a href="<%=path%>/doc/doc_errorcode2-1.jsp">iOS错误码</a></dd>
        		<dd><a href="<%=path%>/doc/doc_errorcode3-1.jsp">Windows错误码</a></dd>
                <dd class="last"><a href="<%=path%>/doc/doc_errorcode4-1.jsp">REST错误码</a></dd>
        	</dl>
		</li>         
        <li><span onclick="location.href='<%=path%>/doc/doc_faq1-1.jsp'">常见问题<i class="parent">&nbsp;</i></span></li>
		<li><span onclick="location.href='<%=path%>/doc/doc_dguide1-1.jsp'">开发者引导<i class="parent">&nbsp;</i></span></li>
      </ul>
    </div>
    <div class="doc_txt doc_guide">
      <div class="pathbox"><p><a href="<%=path%>/doc/doc.jsp">帮助文档首页</a> >  <a href="<%=path%>/doc/doc_guide4-1.jsp">新手指引</a> > <span>开发者协议</span></p></div>
      <div class="display_tit">
        <h1><span class="intro">开发者协议</span></h1>
      </div>
      <div class="display_ctn">
        <h3>快传融合通讯开放平台服务协议</h3>
        <p>尊敬的用户，欢迎使快传融合通讯开放平台（以下简称融合通讯开放平台）。本服务协议阐述之条款和条件适用于您使用的www.flypaas.com网站所提供的快传服务及相关技术、网络支持服务（以下简称服务）。</p>
        <b id="tgg2.1.1">接受条款</b>
        <p>您通过盖章、网络页面点击确认或以其他方式选择接受本协议，即表示您与快传融合通讯开放平台已达成协议，同意接受本协议的所有内容，并受其约束。</p>
        <p>在此特别提醒，在接受本协议之前，请您仔细阅读、充分理解本协议的所有内容。如果您对本协议的任何条款有疑问，请通过快传相关业务部门进行咨询；如果您不同意本协议的任何内容或者无法准确理解快传对本协议相关条款的解释，请不要进行后续操作。</p>
        <b id="tgg2.1.2">注册条款</b>
        <p>2.1 您通过快传融合通讯开放平台注册时，应根据要求提供真实、准确、即时、完整的信息，包括但不限于联系人和/或管理人姓名或名称、联系地址、联系电话、电子邮箱地址等，企业用户需要提交公司营业执照、组织机构代码证、法人信息、银行开户信息等；如果前述您的信息发生变更，您应自行在线更新并及时通知快传融合通讯开放平台；如果因您的信息不真实、不准确、不完整或联系人和/或管理人的行为或不作为而引起的任何后果由您自行承担，融合通讯开放平台有权暂停或终止您的账号。</p>
        <p>2.2 您应对自行存放在快传融合通讯开放平台上的数据及进入和管理快传融合通讯开放平台上各类产品与服务的口令、密码妥善保管，不得转让、转借其他第三人使用；如果因您维护不当或保管不当致使前述数据、口令、密码等丢失或泄漏所引起的任何损失及后果由您自行承担。</p>
        <b id="tgg2.1.3">服务须知</b>
        <p>3.1 您在使用快传融合通讯开放平台服务时应遵守相关法律法规、本协议及www.flypaas.com网站上的相关管理规范、流程等。除另有明确规定，快传融合通讯开放平台所推出的新产品、新功能和新服务，均适用本协议。您应知悉包括但不限于前述协议、规范、产品体系、名称和价格等内容可能会不时变更，如果发生前述变更，我们将在网站的适当版面公告变更内容。如果您不同意变更内容，您有权停止使用快传融合通讯开放平台服务，此等情况下，您应结算相关服务费（如有）；如果您继续使用快传融合通讯开放平台服务，则视为您接受前述变更内容并受其约束。</p>
        <p>3.2 快传融合通讯开放平台服务为预付费服务，您应按照网站页面提示的现时有效的价格体系及本协议的约定支付相应服务费用，前述服务费用将在订购页面上公示。您应知悉快传融合通讯开放平台价格体系中所有的赠送服务项目或活动均为快传融合通讯开放平台在正常服务价格之外的一次性特别优惠，优惠内容不包括赠送服务项目的修改、更新及维护费用，并且赠送服务项目不可折价冲抵服务价格。您预付费用后，我们将开始提供相关服务；与此同时，您应经常查询账户余额变化情况，提前做好充值准备。若您的预付费用使用完毕未及时充值或余额不足，我们有权不经通知随时中止所有提供服务和/或技术支持，对于由此造成的任何后果您应自行承担。</p>
        <p>3.3 您应负责处理和解决因非网络通信问题引起的用户咨询和投诉，提供有效通畅的投诉受理渠道，在需要时积极配合我们处理用户投诉。对双方均不能做出合理解释的用户投诉，最终由您负责进行处理和解决。</p>
        <p>3.4 快传公司或其合作伙伴有权对您提供的应用进行审核，包括但不限于内容审查、功能性测试、安全性测试等。如果发现您的应用不符合国家法律法规、政策规定，或您提供的应用可能侵犯他人合法权益或含有对其他第三方的广告信息等内容，或其他认为不符合要求的情况，快传公司有权不予接入和传输；已接入和传输的，快传公司有权立即停止接入和传输，保存有关记录，并向相关主管部门报告，不经通知自行决定删除或移除任何网站内容或者您发布的应用。但是，该项约定不视为快传公司对您提供的应用提供合法性担保，您应自行对其提供的应用提供保证，并承担由此引发的所有责任。</p>
        <p>3.5 您提供的应用一旦发生纠纷，包括但不限于应用侵犯他人的合法权利、诉讼、重大投诉、发生违约的重大情形等，快传公司有权停止该应用的测试、上线等，并冻结您的账户；对于前述纠纷，您应自行处理并承担由此引起的全部法律责任，及赔偿快传公司由此遭受的全部损失，包括但不限于遭第三方追索所造成的所有损失、诉讼费用、律师费用、差旅费用、和解金额或生效法律文书中规定的损害赔偿金额、软件使用费等；如果快传公司因您的原因卷入前述纠纷，快传公司除有权要求您先行赔偿前述全部损失外，亦有权先行处理并要求您提供必要支持、协助。尽管有前述规定，快传公司亦有权要求您按照本协议承担违约责任。</p>
        <p>3.6 服务期限内，快传融合通讯开放平台将为您提供7×24售后故障服务，并为您提供有效的联系方式并保证您能够联系到故障联系人，故障联系人在明确故障后及时进行反馈；快传公司仅负责快传融合通讯开放平台的底层部分及快传公司提供的软件的运营维护，如果故障原因是由于您未正确按照快传融合通讯开放平台提供的相关文档进行配置导致的无法访问或者您自身技术原因导致的服务故障，不在快传公司服务范畴之内。</p>
        <p>3.7 快传公司将消除您非人为操作所出现的故障，但因您的原因和/或不可抗力以及非快传公司控制范围之内的事项除外。</p>
        <p>3.8 您应知悉并认可，快传公司在必要时可能会进行机房迁移。快传公司进行上述操作前将提前7日通知您，由于进行上述操作可能需要修改您的相关域名的DNS，因此您需在接到快传公司通知后按照要求的时间将DNS修改到指定IP上，否则因此造成网站无法访问的，您应自行负责。</p>
        <p>3.9 快传公司保留在您未按照约定支付全部费用之前不向您提供服务和/或技术支持，或者终止服务和/或技术支持的权利。</p>
        <b id="tgg2.1.4">用户管理</b>
        <p>4.1 如果您使用快传公司提供的服务进行的经营活动需要获得国家有关部门的许可或批准的，您应事先获得该有关的许可或批准。您应依照《互联网信息服务管理办法》、《互联网电子公告服务管理规定》等法律法规的规定保留其网站的访问日志记录，包括发布的信息内容及其发布时间、互联网地址（IP）、域名等，国家有关机关依法查询时应配合提供。您应自行承担未按规定保留相关记录而引起的全部法律责任。</p>
        <p>4.2 您承诺：<br />
        A) 除获得事先书面许可外，不得修改、翻译、改编、出租、转许可、在信息网络上传播或转让快传公司提供的软件及代码，也不得逆向工程、反编译或试图以其他方式发现快传公司提供的软件的源代码；<br />
        B) 不得散布违反国家相关法律、规定及不利于快传公司的垃圾短信、骚扰短信、垃圾电话、骚扰电话、电子邮件广告；不得利用快传公司提供的服务散发大量不受欢迎的或者未经请求的电子广告或包含反动、色情等有害信息的文本、语音及视频内容；<br />
        C) 不得利用快传公司提供的资源和服务发布如下信息或者内容，不得为他人发布该等信息提供任何便利：<br />
        i. 违反国家规定的政治宣传和/或新闻信息；<br />
        ii. 涉及国家秘密和/或安全的信息；<br />
        iii. 封建迷信和/或淫秽、色情、下流的信息或教唆犯罪的信息；<br />
        iv. 博彩有奖、赌博游戏、“私服”、“外挂”等非法互联网出版活动；<br />
        v. 违反国家民族和宗教政策的信息；<br />
        vi. 防碍互联网运行安全的信息；<br />
        vii. 侵害他人合法权益的信息和/或其他有损于社会秩序、社会治安、公共道德的信息或内容；<br />
        viii. 其他违反法律法规、部门规章或国家政策的内容。<br />
        D) 不得建立或利用有关设备、配置运行与所购服务无关的程序或进程，导致大量占用快传公司云计算资源（如快传、网络带宽、存储空间等）所组成的平台（以下简称“快传融合通讯开放平台”）中服务器内存、CPU或者网络带宽资源，影响快传融合通讯开放平台与互联网或者快传融合通讯开放平台与特定网络、服务器及快传公司内部的通畅联系，或者导致快传融合通讯开放平台产品与服务或者快传公司的其他用户网站所在的服务器宕机、死机或者用户基于快传融合通讯开放平台的产品/应用不可访问等；<br />
        E) 不得进行任何改变或试图改变快传公司提供的系统配置或破坏系统安全的行为；<br />
        F) 不得从事其他违法、违规或违反本协议条款的行为。
        </p>
        <p>4.3 如果您违反上述条款的规定，快传公司有权根据情况采取相应的处理措施，包括但不限于立即终止/中止服务，保存有关记录，向相关主管部门报告，删除相应信息等。</p>
        <b id="tgg2.1.5">数据的保存、销毁与下载</b>
        <p>5.1 为便于为您服务，快传公司可能会向您发布信息，包括但不限于发布产品和服务信息等。</p>
        <p>5.2 您应知悉您的数据在下述情况下将部分或全部被披露：<br />
        A) 经您同意，向第三方披露；<br />
        B) 根据法律规定或行政、司法机构要求，向第三方或者行政、司法机构披露；<br />
        C) 为维护公众及快传公司合法利益；<br />
        D) 快传公司可能会与第三方合作向您提供相关软件或服务，如果您使用该类服务，则视为您同意该第三方知悉您的数据。
        </p>
        <p>5.3 除法定及本协议另行约定外，快传公司自本协议终止之日起7日后，将不再为您保留数据，您应知悉并自行承担其数据被销毁后引发的一切后果。</p>
        <b id="tgg2.1.6">保密条款</b>
        <p>6.1 保密资料指由一方向另一方披露的所有技术及非技术信息(包括但不限于产品资料，产品计划，价格，财务及营销规划，业务战略，客户信息，客户数据，研发，软件硬件，API应用数据接口，技术说明，设计，特殊公式，特殊算法等)。</p>
        <p>6.2 本协议任何一方应对获悉的对方之上述保密资料予以保密，并严格限制接触上述保密信息的员工遵守本条之保密义务。除非国家机关依法强制要求或上述保密资料已经进入公有领域外，接受保密资料的一方不得对外披露。</p>
        <p>6.3 本协议双方明确认可各自用户信息和业务数据等是各自的重要资产及重点保密信息。本协议双方应尽最大的努力保护上述保密信息等不被披露。一旦发现有上述保密信息泄露事件，双方应合作采取一切合理措施避免或者减轻损害后果的产生。</p>
        <b id="tgg2.1.7">知识产权</b>
        <p>7.1 您声明并保证其在快传融合通讯开放平台上发布的应用拥有知识产权，或者已事先合法取得合法权利人授权并以被许可方式在快传融合通讯开放平台使用。您不得在快传融合通讯开放平台上发布、传播或分享并非其原创或者未经授权使用的内容，但依据法律法规规定合理使用或法定许可除外。您应对通过快传融合通讯开放平台发布的应用及一切资料的知识产权权属自行承担一切法律责任。</p>
        <p>7.2 您如果发布含有气象、教育、医疗、交通、金融、影视、动漫、刊物、资讯等行业专业信息的应用，或含有公众人物、名人、个人的头像、标识、肢体语言等信息的应用，应确保拥有合法的使用权、肖像权或形象权等。</p>
        <p>7.3 您保证您通过快传融合通讯开放平台发布的应用不会侵犯任何第三方的合法权利。如果您所发布的应用存在侵犯任何第三方合法权利的情况，您将承担一切相关法律责任和风险。如果快传公司因您的应用侵犯第三方合法权利而涉入诉讼、索赔或其他司法程序（以下称“侵权诉讼”），您同意按照以下规定进行处理和赔偿：<br />
        （1） 快传公司应在发生上述侵权诉讼后迅速通知您，并在上述侵权诉讼过程中中止向您提供服务。<br />
        （2） 您应当在收到快传公司书面通知后，指派代表为快传公司的权益参与上述第三方提起的侵权诉讼，您应在上述侵权诉讼进行过程中就诉讼策略及其他事宜向快传公司提供必要的支持与协助，并承担所产生的一切诉讼费用、律师费用、差旅费用、和解金额或生效法律文书中规定的损害赔偿金额、软件使用费等费用。<br />
        （3） 快传公司有权按照本合同关于违约责任的规定要求您承担违约责任。
        </p>
        <b id="tgg2.1.8">期限与终止</b>
        <p>8.1 服务有效期自您的快传公司账户成功付费之日起（而非自您获取快传融合通讯开放平台的管理员登录号和密码之日）计算，并以您所缴纳的保底或月功能费款项数额为依据确认服务价格。开发者账户在线付费后服务即时生效，企业用户使用公对公账户打款付费后，2个工作日内服务生效。</p>
        <p>8.1 服务有效期自您的快传账户成功付费之日起（而非自您获取快传的管理员登录号和密码之日）计算，并以您所缴纳的保底或月功能费款项数额为依据确认服务价格。开发者账户在线付费后服务即时生效，企业用户使用公对公账户打款付费后，2个工作日内服务生效。</p>
        <p>8.2 发生下列情形，服务期限提前终止：<br />
        A) 双方协商一致提前终止的；<br />
        B) 您违反本协议（包括但不限于i.未按照协议约定履行付款义务，及/或ii.严重违反法律规定等），快传公司有权提前终止服务，并不退还您已经支付的费用；<br />
        C) 您知悉并充分认可，虽然快传公司已经建立（并将根据技术及市场的发展不断完善）必要的技术与商务措施来:<br />
        i. 防御包括计算机病毒、网络入侵和攻击破坏等危害网络安全事项或行为；<br />
        ii.组成完善的短信、语音中继、视频网络保障您的服务通畅；<br/>但鉴于网络安全技术的局限性、相对性以及该等行为的不可预见性，以及国家及运营商政策的不可预见性，因此若因技术或者政策原因，快传公司可自行决定暂停或终止服务。如果终止服务的，将按照实际提供服务计算服务费用，将剩余款项（如有）返还您。<br />
        D)快传公司可提前30天在www.flypaas.com上通告及以通知方式告知您终止本协议，无需说明理由，届时将退还您已支付但未消费的款项（如有）。
        </p>
        <b id="tgg2.1.9">免责声明</b>
        <p>9.1您知悉并认可，鉴于计算机、互联网的特殊性，下述情况不属于快传公司违约：<br />
        A) 快传公司在进行服务器配置、维护时，需要短时间中断服务；<br />
        B) 由于Internet上的通路阻塞造成您网站访问速度下降；<br />
        C) 因不可抗力、计算机病毒或黑客攻击、国家相关行业主管部门及运营商的调整、系统不稳定、您所在位置、您关机及其他任何技术、互联网络、通信线路原因等造成的服务中断或不能满足您的要求；<br />
        D) 由于行业技术水平无法避免的瑕疵造成快传公司提供的服务存在瑕疵。</p>
        <p>9.2 在任何情况下，快传公司均不就因使用快传融合通讯开放平台提供的服务所发生的任何间接性、后果性、惩戒性、偶然性、特殊性的损害，包括您使用快传融合通讯开放平台服务而遭受的利润损失承担责任（即使您已事先被告知该等损害发生的可能性）。</p>
        <p>9.3 您应知悉并认可，使用快传融合通讯开放平台提供服务可能存在来自任何第三方的包括威胁、诽谤或非法的内容或行为或对他人合法权利的侵犯的匿名或冒名信息的风险，您应自行承担前述风险，快传公司或合作公司对提供的服务不作任何类型的担保，不论是明示的或默示的，包括所有有关信息真实性、适用性、所有权和非侵权性的默示担保和条件，对因此导致任何因您不正当或非法使用服务产生的直接、间接、偶然、特殊及后续的损害，不承担任何责任。</p>
        <b id="tgg2.1.10">不可抗力</b>
        <p>因不可抗力或者其他意外事件，使得本协议的履行不可能、不必要或者无意义的，遭受不可抗力、意外事件的一方不承担责任。不可抗力、意外事件是指不能预见、不能克服并不能避免且对一方或双方当事人造成重大影响的客观事件，包括但不限于自然灾害如洪水、地震、瘟疫流行等以及社会事件如战争、动乱、政府行为、电信主干线路中断、黑客、网路堵塞、电信部门技术调整和政府管制等。</p>
        <b id="tgg2.1.11">法律适用及争议解决</b>
        <p>本协议受中华人民共和国法律管辖。在履行本协议过程中如发生纠纷，双方应及时协商解决。协商不成时，任何一方可直接向深圳市南山区人民法院提起诉讼。</p>
        <b id="tgg2.1.12">附则</b>
        <p>12.1 本协议任何附件、快传融合通讯开放平台页面上的服务说明、价格说明及您确认同意的订购页面内容等是本协议不可分割的一部分。如果前述具体约定与本协议有不一致之处，以前述具体约定为准。</p>
        <p>12.2 快传公司有权以提前30天在www.flypaas.com上通告及以通知方式告知您的方式将本协议的权利义务全部或者部分转让给快传公司的关联公司。</p>
        <p>12.3 如果本协议中的任何条款被判定为无效或不具执行力，不影响本协议其余条款的有效性及约束力。本协议中的包括但不局限于保证、保密、知识产权、法律和管辖地条款在本协议终止应继续存在。</p>
      </div>
    </div>
  </div>
  <div class="clear"></div>
</div>
<div class="doc_aside" id="doc_aside">
    <h4>目录 [<a href="javascript:void(0);" id="aside_fold_link">隐藏</a>] </h4>
  <div class="aside_ctn">
    <ul>
      <li><a href="#tgg2.1.1">1. 接受条款</a></li>
      <li><a href="#tgg2.1.2">2. 注册条款</a></li>   	
      <li><a href="#tgg2.1.3">3. 服务须知</a></li>
      <li><a href="#tgg2.1.4">4. 用户管理</a></li>   
      <li><a href="#tgg2.1.5">5. 数据的保存、销毁与下载</a></li>
      <li><a href="#tgg2.1.6">6. 保密条款</a></li>   
      <li><a href="#tgg2.1.7">7. 知识产权</a></li>
      <li><a href="#tgg2.1.8">8. 期限与终止</a></li>   
      <li><a href="#tgg2.1.9">9. 免责声明</a></li>
      <li><a href="#tgg2.1.10">10. 不可抗力</a></li> 
      <li><a href="#tgg2.1.11">11. 法律适用及争议解决</a></li> 
      <li><a href="#tgg2.1.12">12. 附则</a></li>   	  
    </ul>
  </div>
</div>
<div class="aside_fold" id="aside_fold" style="display:none;"><img src="<%=path%>/doc/images/aside_fold.png" />目录导航</div>
<a href="javascript:void(0);" id="go_top" style="display:none;">&nbsp;</a>
<!--中间部分middle eof--> 

<!--公共底部bottom bof-->

<!--公共底部bottom eof--> 

<!--公共版权copyright bof-->
<%@include file="/front/foot.jsp" %>
<!--公共版权copyright eof-->

<script type="text/javascript">
$(function(){
	$("#h_menu_5").css("color","#05c040");
})
</script>
</body>
</html>
