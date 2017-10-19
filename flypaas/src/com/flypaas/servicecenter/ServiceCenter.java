package com.flypaas.servicecenter;

import org.springframework.beans.factory.annotation.Autowired;

import com.flypaas.service.AcctBalanceService;
import com.flypaas.service.AcctPackageRelService;
import com.flypaas.service.AgreementService;
import com.flypaas.service.AppBalanceService;
import com.flypaas.service.AppService;
import com.flypaas.service.AppShowNbrsService;
import com.flypaas.service.CallBackService;
import com.flypaas.service.ChannelService;
import com.flypaas.service.CityService;
import com.flypaas.service.ClientService;
import com.flypaas.service.CloudNoticeService;
import com.flypaas.service.CountryMobilePrefixService;
import com.flypaas.service.CouponService;
import com.flypaas.service.EventBalanceService;
import com.flypaas.service.FeeItemRelService;
import com.flypaas.service.FeeItemService;
import com.flypaas.service.InvoiceAddrService;
import com.flypaas.service.InvoiceService;
import com.flypaas.service.MailService;
import com.flypaas.service.NewsService;
import com.flypaas.service.OtherService;
import com.flypaas.service.PackageService;
import com.flypaas.service.ParamsService;
import com.flypaas.service.PayMentOrderService;
import com.flypaas.service.ProvinceService;
import com.flypaas.service.RemindService;
import com.flypaas.service.RingService;
import com.flypaas.service.SDDownloadCountService;
import com.flypaas.service.SecurityBalanceService;
import com.flypaas.service.SecurityRelieveApplyforService;
import com.flypaas.service.ConsumeService;
import com.flypaas.service.TestWhilteListService;
import com.flypaas.service.UserLogService;
import com.flypaas.service.UserMsgService;
import com.flypaas.service.UserPicService;
import com.flypaas.service.UserService;
import com.flypaas.service.WhiteListService;
import com.flypaas.service.app.SmsNumService;
import com.flypaas.service.app.SmsTemplateService;


public abstract class ServiceCenter   {
	@Autowired
	protected AppService appService ;
	@Autowired
	protected WhiteListService whiteService;
	@Autowired
	protected CallBackService cbService;
	@Autowired
	protected UserPicService userPicService;
	@Autowired
	protected ClientService clientService;
	@Autowired
	protected CountryMobilePrefixService mobilePrefixService;
	@Autowired
	protected PackageService packageService;
	@Autowired
	protected AcctPackageRelService packageRelService;
	@Autowired
	protected FeeItemRelService itemRelService;
	@Autowired
	protected FeeItemService itemService;
	@Autowired
	protected AcctPackageRelService pckRelService;
	@Autowired
	protected UserMsgService userMsgService;
	@Autowired
	protected UserService userService;
	@Autowired
	protected MailService mailService;
	@Autowired
	protected AcctBalanceService acctBalanceService;
	@Autowired
	protected ProvinceService provinceService;
	@Autowired
	protected CityService cityService;
	@Autowired
	protected PackageService packService;
	@Autowired
	protected AcctPackageRelService acctPckService;
	@Autowired
	protected PayMentOrderService payMentOrderService;
	@Autowired
	protected RemindService remindService;
	@Autowired
	protected UserLogService userLogService;
	@Autowired
	protected SmsTemplateService smsTemplateService;
	@Autowired
	protected ParamsService paramsService;
	@Autowired
	protected ConsumeService consumeService;
	@Autowired
	protected SDDownloadCountService sDDownloadCountService ;
	@Autowired
	protected NewsService newsService;
	@Autowired
	protected AgreementService agreementService ;
	@Autowired
	protected SecurityRelieveApplyforService securityRelieveApplyforService;
	@Autowired
	protected SecurityBalanceService securityBalanceService;
	@Autowired
	protected AppShowNbrsService appShowNbrsService;
	@Autowired
	protected InvoiceService invoiceService;
	@Autowired
	protected InvoiceAddrService invoiceAddrService;
	@Autowired
	protected CouponService couponService;
	@Autowired
	protected RingService ringService;
	@Autowired
	protected ChannelService channelService;
	@Autowired
	protected AppBalanceService appBalanceService;
	@Autowired
	protected CloudNoticeService cloudNoticeService;
	@Autowired
	protected EventBalanceService eventBalanceService;
	@Autowired
	protected OtherService otherService;
	@Autowired
	protected TestWhilteListService testWhilteListService;
	@Autowired
	protected SmsNumService smsNumService;
}
