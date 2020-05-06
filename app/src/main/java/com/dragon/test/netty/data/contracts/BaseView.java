package com.dragon.test.netty.data.contracts;

public interface BaseView {

    void showLoading();
    void hideLoading();

    void showTips(boolean success, String tips);
    void hideTips(String tips);

    void onEmpty();

}
