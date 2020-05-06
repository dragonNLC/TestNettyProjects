package com.dragon.test.netty.data.bean;

import android.app.Activity;

public class CtlItemBean {

    public String itemName;
    public Class<?> startActivity;

    public CtlItemBean() {
    }

    public CtlItemBean(String itemName, Class<?> startActivity) {
        this.itemName = itemName;
        this.startActivity = startActivity;
    }
}
