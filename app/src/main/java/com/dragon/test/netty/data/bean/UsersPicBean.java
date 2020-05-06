package com.dragon.test.netty.data.bean;

import java.util.List;

/**
 * 查询用户头像信息，这里保存该查询语句获得的数据
 */
public class UsersPicBean {

    private String retCode;
    private String retMsg;
    private String hasNext;
    private List<UserPicBean> contents;

    public UsersPicBean() {
    }

    public UsersPicBean(String retCode, String retMsg, String hasNext, List<UserPicBean> contents) {
        this.retCode = retCode;
        this.retMsg = retMsg;
        this.hasNext = hasNext;
        this.contents = contents;
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

    public String getHasNext() {
        return hasNext;
    }

    public void setHasNext(String hasNext) {
        this.hasNext = hasNext;
    }

    public List<UserPicBean> getContents() {
        return contents;
    }

    public void setContents(List<UserPicBean> contents) {
        this.contents = contents;
    }
}
