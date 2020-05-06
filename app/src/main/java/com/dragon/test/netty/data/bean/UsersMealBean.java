package com.dragon.test.netty.data.bean;

import java.util.List;

/**
 * 用户订餐记录查询，这里保存该查询语句查询下来的数据
 */
public class UsersMealBean {

    private String retCode;
    private String retMsg;
    private String hasNext;
    private List<UserMealBean> contents;

    public UsersMealBean() {
    }

    public UsersMealBean(String retCode, String retMsg, String hasNext, List<UserMealBean> contents) {
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

    public List<UserMealBean> getContents() {
        return contents;
    }

    public void setContents(List<UserMealBean> contents) {
        this.contents = contents;
    }

    public boolean hasNextPage() {
        return hasNext.equals("1");
    }

}
