package com.dragon.test.netty.data.bean;

import java.util.List;

/**
 * 将白名单内容与下页标记打包
 */
public class WhiteListPackBean {

    private String mHasNext;
    private List<WhiteListUserBean> mDataSet;

    public WhiteListPackBean() {
    }

    public WhiteListPackBean(String mHasNext, List<WhiteListUserBean> mDataSet) {
        this.mHasNext = mHasNext;
        this.mDataSet = mDataSet;
    }

    public String getHasNext() {
        return mHasNext;
    }

    public boolean hasNext() {
        return "1".equals(mHasNext);
    }

    public void setHasNext(String hasNext) {
        this.mHasNext = hasNext;
    }

    public List<WhiteListUserBean> getDataSet() {
        return mDataSet;
    }

    public void setDataSet(List<WhiteListUserBean> dataSet) {
        this.mDataSet = dataSet;
    }
}
