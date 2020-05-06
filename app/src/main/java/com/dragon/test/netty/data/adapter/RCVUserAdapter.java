package com.dragon.test.netty.data.adapter;

import androidx.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.dragon.test.netty.R;
import com.dragon.test.netty.data.bean.WhiteListUserBean;


public class RCVUserAdapter extends BaseQuickAdapter<WhiteListUserBean, BaseViewHolder> {

    public RCVUserAdapter() {
        super(R.layout.rcv_user_layout);
    }

    @Override
    protected void convert(BaseViewHolder helper, WhiteListUserBean item) {
        helper.setText(R.id.tv_name, item.getEmpName());
        helper.addOnClickListener(R.id.btn_query_meals);
    }

}
