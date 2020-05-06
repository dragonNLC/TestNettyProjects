package com.dragon.test.netty.data.adapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.dragon.test.netty.R;
import com.dragon.test.netty.data.bean.UserMealBean;

public class RCVMealAdapter extends BaseQuickAdapter<UserMealBean, BaseViewHolder> {

    public RCVMealAdapter() {
        super(R.layout.rcv_user_layout);
    }

    @Override
    protected void convert(BaseViewHolder helper, UserMealBean item) {
        helper.setText(R.id.tv_name, item.getEmpName());
    }

}
