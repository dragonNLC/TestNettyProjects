package com.dragon.test.netty.data.adapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.dragon.test.netty.R;
import com.dragon.test.netty.data.bean.CtlItemBean;

public class RCVChooseItemAdapter extends BaseQuickAdapter<CtlItemBean, BaseViewHolder> {

    public RCVChooseItemAdapter() {
        super(R.layout.rcv_choose_item_layout);
    }

    @Override
    protected void convert(BaseViewHolder helper, CtlItemBean item) {
        helper.setText(R.id.tv_name, item.itemName);
    }

}
