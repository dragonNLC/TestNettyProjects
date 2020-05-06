package com.dragon.test.netty.data.bean;

/**
 * 订餐核销结果
 */
public class UseMealResultBean {

    private String retCode;
    private String retMsg;
    private String errorTranId;

    public UseMealResultBean() {
    }

    public UseMealResultBean(String retCode, String retMsg, String errorTranId) {
        this.retCode = retCode;
        this.retMsg = retMsg;
        this.errorTranId = errorTranId;
    }

    public String getRetCode() {
        return retCode;
    }

    public void setRetCode(String retCode) {
        this.retCode = retCode;
    }

    public String getRetMsg() {
        return retMsg;
    }

    public void setRetMsg(String retMsg) {
        this.retMsg = retMsg;
    }

    public String getErrorTranId() {
        return errorTranId;
    }

    public void setErrorTranId(String errorTranId) {
        this.errorTranId = errorTranId;
    }

}
