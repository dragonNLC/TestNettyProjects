package com.dragon.test.netty.data.bean;

/**
 * 二维码核销结果
 */
public class QRConsumeResultBean {

    /**
     * code : SUCCESS
     * msg : 语音播报内容
     * msg_dis : 显示屏内容
     * msg_num : 2
     * msg_prn : 打印内容
     */

    private String code;
    private String msg;
    private String msg_dis;
    private String msg_num;
    private String msg_prn;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getMsg_dis() {
        return msg_dis;
    }

    public void setMsg_dis(String msg_dis) {
        this.msg_dis = msg_dis;
    }

    public String getMsg_num() {
        return msg_num;
    }

    public void setMsg_num(String msg_num) {
        this.msg_num = msg_num;
    }

    public String getMsg_prn() {
        return msg_prn;
    }

    public void setMsg_prn(String msg_prn) {
        this.msg_prn = msg_prn;
    }
}
