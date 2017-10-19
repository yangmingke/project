package com.flypaas.daocenter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;

import com.flypaas.dao.AcctBalanceDao;
import com.flypaas.dao.AcctPackageRelDao;
import com.flypaas.dao.AgreementDao;
import com.flypaas.dao.AppBalanceDao;
import com.flypaas.dao.AppDao;
import com.flypaas.dao.AppShowNbrsDao;
import com.flypaas.dao.CallBackDao;
import com.flypaas.dao.ChannelDao;
import com.flypaas.dao.CityDao;
import com.flypaas.dao.ClientDao;
import com.flypaas.dao.CloudNoticeDao;
import com.flypaas.dao.ConsumeDao;
import com.flypaas.dao.CountryMobilePrefixDao;
import com.flypaas.dao.CouponDao;
import com.flypaas.dao.EventBalanceDao;
import com.flypaas.dao.FeeItemDao;
import com.flypaas.dao.FeeItemRelDao;
import com.flypaas.dao.InvoiceAddrDao;
import com.flypaas.dao.InvoiceDao;
import com.flypaas.dao.MailDao;
import com.flypaas.dao.NewConsumeDao;
import com.flypaas.dao.NewsDao;
import com.flypaas.dao.OtherDao;
import com.flypaas.dao.PackageDao;
import com.flypaas.dao.ParamsDao;
import com.flypaas.dao.PayMentOrderDao;
import com.flypaas.dao.ProvinceDao;
import com.flypaas.dao.RemindDao;
import com.flypaas.dao.RingDao;
import com.flypaas.dao.SDDownloadCountDao;
import com.flypaas.dao.SecurityBalanceDao;
import com.flypaas.dao.SecurityDeductionLogDao;
import com.flypaas.dao.SecurityRelieveApplyforDao;
import com.flypaas.dao.SmsTemplateDao;
import com.flypaas.dao.TestWhilteListDao;
import com.flypaas.dao.UserDao;
import com.flypaas.dao.UserLogDao;
import com.flypaas.dao.UserMsgDao;
import com.flypaas.dao.UserPicDao;
import com.flypaas.dao.WhiteListDao;
import com.flypaas.dao.impl.SessionPacketLossDao;

public abstract class DaoCenter {
	@Autowired
	protected AcctBalanceDao acctBalanceDao;
	@Autowired
	protected AcctPackageRelDao acctPackageRelDao ;
	@Autowired
	protected AppDao appDao;
	@Autowired
	protected CallBackDao callBackDao ;
	@Autowired
	protected CityDao cityDao ;
	@Autowired
	protected ClientDao clientDao;
	@Autowired
	protected CountryMobilePrefixDao countryMobilePrefixDao ;
	@Autowired
	protected FeeItemRelDao feeItemRelDao;
	@Autowired
	protected FeeItemDao feeItemDao;
	@Autowired
	protected MailDao mailDao;
	@Autowired
	protected JavaMailSender mailSender;
	@Autowired
	protected PackageDao packageDao ;
	@Autowired
	protected ParamsDao paramsDao ;
	@Autowired
	protected PayMentOrderDao payMentOrderDao;
	@Autowired
	protected ProvinceDao provinceDao;
	@Autowired
	protected RemindDao remindDao;
	@Autowired
	protected UserLogDao userLogDao;
	@Autowired
	protected UserMsgDao userMsgDao;
	@Autowired
	protected UserPicDao userPicDao;
	@Autowired
	protected UserDao userDao;
	@Autowired
	protected WhiteListDao whiteListDao ;
	@Autowired
	protected SmsTemplateDao smsTemplateDao;
	@Autowired
	protected ConsumeDao consumeDao;
	@Autowired
	protected NewConsumeDao newConsumeDao;
	@Autowired
	protected SDDownloadCountDao sDDownloadCountDao;
	@Autowired
	protected NewsDao newsDao;
	@Autowired
	protected AgreementDao agreementDao;
	@Autowired
	protected SecurityDeductionLogDao securityDeductionLogDao;
	@Autowired
	protected SecurityRelieveApplyforDao securityRelieveApplyforDao;
	@Autowired
	protected SecurityBalanceDao securityBalanceDao;
	@Autowired
	protected AppShowNbrsDao appShowNbrsDao;
	@Autowired
	protected InvoiceDao invoiceDao;
	@Autowired
	protected InvoiceAddrDao invoiceAddrDao;
	@Autowired
	protected CouponDao couponDao;
	@Autowired
	protected RingDao ringDao;
	@Autowired
	protected ChannelDao channelDao;
	@Autowired
	protected AppBalanceDao appBalanceDao;
	@Autowired
	protected CloudNoticeDao cloudNoticeDao;
	@Autowired
	protected EventBalanceDao eventBalanceDao;
	@Autowired
	protected OtherDao otherDao;
	@Autowired
	protected TestWhilteListDao testWhilteListDao;
	@Autowired
	protected SessionPacketLossDao sessionPacketLossDao;
	
	/**
	 * add by zenglb
	 */
	@Autowired
	protected MyBatisDao myBatisDao;
	
	/**
	 * add by zenglb
	 */
	@Autowired
	protected StatisticsMyBatisDao statisticsMyBatisDao;
}
