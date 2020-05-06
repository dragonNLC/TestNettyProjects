package com.dragon.test.netty.data.bean;

/**
 * http请求返回数据包封装
 * @param <T> 可以为任意实体类
 */
public class ResultBean<T> {

    public static final String REQUEST_SUCCESS_CODE = "00000000000000000";
    public static final String REQUEST_SUCCESS_BUT_EMPTY = "empty";

    private String repCode;
    private String errorMsg;
    private String dataPack;//具体内容数据包
    private T content;//具体内容实例类

    public ResultBean() {
    }

    public ResultBean(String repCode, String errorMsg, String dataPack, T content) {
        this.repCode = repCode;
        this.errorMsg = errorMsg;
        this.dataPack = dataPack;
        this.content = content;
    }

    public ResultBean(String repCode, String errorMsg, T content) {
        this.repCode = repCode;
        this.errorMsg = errorMsg;
        this.content = content;
    }

    public String getRepCode() {
        return repCode;
    }

    public void setRepCode(String repCode) {
        this.repCode = repCode;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }

    public T getContent() {
        return content;
    }

    public void setContent(T content) {
        this.content = content;
    }

    public String getDataPack() {
        return dataPack;
    }

    public void setDataPack(String dataPack) {
        this.dataPack = dataPack;
    }
}
