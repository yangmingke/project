package com.network.monitor.util.rest.vo;

/**
 * 封装提交给支付网关数据bean。
 * 参数默认值不能为null，防止拼url时提交到支付网关的参数为null字符串
 * 
 */
public class GatewayVo {
	private String commitUrl = "";
    private String merId = "";
    private String orderId = "";
    private String payType = "";
    private String payAmount = "";
    private String bankId = "";
    private String payTime = "";
    private String notifyUrl = "";
    private String merData = "";
    private String cardId = "";
    private String cardPassword = "";
    private String cardAmount = "";
    private String userId = "";
    private String productId = "";
    private String productDesc = "";
    private String receipt = "";
    private String goodsInfo = "";
    private String sign = "";
    private String mac="" ;

    public String getCommitUrl() {
        return commitUrl;
    }

    public void setCommitUrl(String commitUrl) {
        this.commitUrl = commitUrl;
    }

    public String getMerId() {
        return merId;
    }

    public void setMerId(String merId) {
        this.merId = merId;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getPayType() {
        return payType;
    }

    public void setPayType(String payType) {
        this.payType = payType;
    }

    public String getPayAmount() {
        return payAmount;
    }

    public void setPayAmount(String payAmount) {
        this.payAmount = payAmount;
    }

    public String getPayTime() {
        return payTime;
    }

    public void setPayTime(String payTime) {
        this.payTime = payTime;
    }

    public String getNotifyUrl() {
        return notifyUrl;
    }

    public void setNotifyUrl(String notifyUrl) {
        this.notifyUrl = notifyUrl;
    }

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }

    public String getMerData() {
        return merData;
    }

    public void setMerData(String merData) {
        this.merData = merData;
    }

    public String getCardId() {
        return cardId;
    }

    public void setCardId(String cardId) {
        this.cardId = cardId;
    }

    public String getCardPassword() {
        return cardPassword;
    }

    public void setCardPassword(String cardPassword) {
        this.cardPassword = cardPassword;
    }

    public String getCardAmount() {
        return cardAmount;
    }

    public void setCardAmount(String cardAmount) {
        this.cardAmount = cardAmount;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getProductDesc() {
        return productDesc;
    }

    public void setProductDesc(String productDesc) {
        this.productDesc = productDesc;
    }

    public String getBankId() {
        return bankId;
    }

    public void setBankId(String bankId) {
        this.bankId = bankId;
    }
    
    public String getReceipt() {
        return receipt;
    }

    public void setReceipt(String receipt) {
        this.receipt = receipt;
    }

    public String getGoodsInfo() {
        return goodsInfo;
    }

    public void setGoodsInfo(String goodsInfo) {
        this.goodsInfo = goodsInfo;
    }

    public String getMac() {
		return mac;
	}

	public void setMac(String mac) {
		this.mac = mac;
	}

	@Override
    public String toString() {
        return "GatewayDto [commitUrl=" + commitUrl + ", merId=" + merId
                + ", orderId=" + orderId + ", payType=" + payType
                + ", payAmount=" + payAmount + ", bankId=" + bankId
                + ", payTime=" + payTime + ", notifyUrl=" + notifyUrl
                + ", merData=" + merData + ", cardId=" + cardId
                + ", cardPassword=" + cardPassword + ", cardAmount="
                + cardAmount + ", userId=" + userId + ", productId="
                + productId + ", productDesc=" + productDesc + ", receipt="
                + receipt + ", goodsInfo=" + goodsInfo + ", sign=" + sign + "]";
    }

   

}
