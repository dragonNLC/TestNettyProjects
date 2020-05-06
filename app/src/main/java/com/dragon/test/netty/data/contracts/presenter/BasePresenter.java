package com.dragon.test.netty.data.contracts.presenter;

/**
 * Created by lb on 2019/1/4.
 */

public class BasePresenter<V> {

    protected V mView;

    public void attach(V v) {
        this.mView = v;
    }

    public void detach() {
        this.mView = null;
    }

    protected boolean checkViewNoNull() {
        return mView != null;
    }

}
