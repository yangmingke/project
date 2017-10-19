package com.flypaas.manager;
import java.io.UnsupportedEncodingException;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import com.flypaas.constant.SysConstant;
import com.flypaas.customExceptions.OrderBusinessException;
import com.flypaas.entity.PaymentOrder;
import com.flypaas.entity.vo.GatewayVo;
import com.flypaas.utils.DateUtil;
import com.flypaas.utils.EncryptUtil;
import com.flypaas.utils.StrUtil;
import com.flypaas.utils.SysConfig;

@Service("orderManager")
public class OrderManager{

    private static Logger log = LoggerFactory.getLogger(OrderManager.class);
    private static HttpServletRequest request = null ;
    
    public static void instance(HttpServletRequest req){
    	request = req;
    }
    /**
     * 检查参数
     * 
     * @return
     * @throws Exception 
     */
    public static void check(GatewayVo vo)  {
        if (vo.getMerId()==null || vo.getOrderId() == null || vo.getPayType()==null 
        		|| vo.getPayAmount()==null || vo.getPayTime()==null || vo.getNotifyUrl()==null
        		|| vo.getCommitUrl()==null || vo.getSign()==null) {
            log.warn("lose argument, " + vo);
            throw new OrderBusinessException(SysConstant.RESULT_PARAMETER_ERROR);
        }
        String sign = null;
		try {
			sign = EncryptUtil.md5Digest(vo.getMerId()+vo.getOrderId() + vo.getPayType()
			        + vo.getPayAmount() + vo.getPayTime()
			        +  vo.getNotifyUrl() + SysConstant.PAY_GATEWAY_KEY);
		} catch (Exception e) {
			log.error("--------------------------check sign error-----------------------------" + vo);
	        throw new OrderBusinessException(SysConstant.RESULT_PARAMETER_ERROR);
		}
        if (!sign.equals(vo.getSign())) {
            log.warn("------------------------------check sign error-------------------------" + vo);
            throw new OrderBusinessException(SysConstant.RESULT_PARAMETER_ERROR);
        }
    }

    /**
     * 构建请求支付网关接口
     * @param order
     * @return
     * @throws Exception 
     */
    public static GatewayVo buildGatewayDto(PaymentOrder order)  {
    	GatewayVo gatewayDto = new GatewayVo();
        try {
        	gatewayDto.setMerId(SysConstant.MERID);
        	gatewayDto.setOrderId(order.getPayId());
        	gatewayDto.setPayType(SysConfig.getInstance().getPayTypeMap().get(Integer.parseInt(order.getChargeType()))+"");
//        	gatewayDto.setPayAmount(String.valueOf(order.getCharge()*SysConstant.payRate));
        	gatewayDto.setPayAmount(String.valueOf(order.getCharge()));
        	gatewayDto.setProductDesc(SysConstant.PRODUCT_DESC);
        	gatewayDto.setPayTime(DateUtil.dateToStr(DateUtil.getCurrentDate(), DateUtil.DATE_TIME_NO_SLASH));
        	gatewayDto.setNotifyUrl(SysConstant.PAY_CALLBACK_URL);
        	gatewayDto.setCommitUrl(SysConstant.PAY_GATEWAY_URL);
//        	gatewayDto.setMerData(Des3Utils.encodeDes3(order.getSid()+SysConstant.SYS_DIVISION+order.getOrderId())); //长度不固定，当超过一定长度，支付宝不支持
        	gatewayDto.setMerData(order.getSid()+SysConstant.SYS_DIVISION+order.getOrderId());
        	System.out.println("------------------------组装MerData:"+order.getSid()+":"+SysConstant.SYS_DIVISION+":"+order.getOrderId()+"------------------------");
			gatewayDto.setSign(genSign(gatewayDto));
		} catch (Exception e) {
			e.printStackTrace();
			log.error("----------------------------------组装请求支付的参数失败！----------------------------");
	        throw new OrderBusinessException(SysConstant.RESULT_PARAMETER_ERROR);
		}
        /*
         * 易宝支付需要传参数：merData=05-16-DC-59-C2-34|0:0:0:0:0:0:0:1|Mozilla/5.0
         * (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko)
         * Chrome/33.0.1750.154 Safari/537.36
         * mac地址由客户端传过来，其他支付方式可以不传，如果传了就按以下方式设置merData
         */
        if(!StrUtil.isEmpty(order.getChargeType()) && SysConfig.getInstance().getPayTypeMap().get(Integer.parseInt(order.getChargeType())).equals(SysConstant.CHARGETYPE_2)){
        	gatewayDto.setMac(SysConstant.MAC);
        }
        if (!StrUtil.isEmpty(gatewayDto.getMac())) {
            String merData = gatewayDto.getMac() + "|" + request.getRemoteAddr()
                    + "|" + request.getHeader("User-Agent");
            gatewayDto.setMerData(merData);
        }
        return gatewayDto;
    }

    /**
     * 生成签名
     * @param gatewayDto
     * @return
     * @throws Exception 
     */
   /* private static String genSign(GatewayVo gatewayDto) throws Exception {
        StringBuilder src = new StringBuilder();
        	 src.append(gatewayDto.getMerId())
        		.append(gatewayDto.getOrderId())
                .append(gatewayDto.getPayType())
                .append(gatewayDto.getPayAmount())
//                .append(gatewayDto.getPayTime())
//                .append(gatewayDto.getNotifyUrl())
//                .append(gatewayDto.getUserId())
                .append(SysConstant.PAY_GATEWAY_KEY);
        return DigestUtils.md5DigestAsHex(src.toString().getBytes("GBK"));
    }*/
    
    private static String genSign(GatewayVo gatewayDto) throws Exception{
        StringBuilder src = new StringBuilder();
        src.append(gatewayDto.getMerId()).append(gatewayDto.getOrderId())
                .append(gatewayDto.getPayType()).append(gatewayDto.getBankId())
                .append(gatewayDto.getPayAmount())
                .append(gatewayDto.getPayTime())
                .append(gatewayDto.getNotifyUrl())
                .append(gatewayDto.getMerData()).append(gatewayDto.getCardId())
                .append(gatewayDto.getCardPassword())
                .append(gatewayDto.getCardAmount())
                .append(gatewayDto.getUserId())
                .append(gatewayDto.getProductId())
                .append(gatewayDto.getProductDesc())
                .append(SysConstant.PAY_GATEWAY_KEY);

        return DigestUtils.md5DigestAsHex(src.toString().getBytes());
    }
    
    public static void main(String[] args) {
    	String src="ucp1000000047alipay12fdf902h3nfay&Miyt1svcakJ8y234nnu" ;
    	try {
			System.out.println(DigestUtils.md5DigestAsHex(src.toString().getBytes("GBK")));
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
	}

}
