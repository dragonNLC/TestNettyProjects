package com.dragon.test.netty;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.dragon.test.netty.data.adapter.RCVUserAdapter;
import com.dragon.test.netty.data.bean.WhiteListUserBean;
import com.dragon.test.netty.data.constracts.XMLConstant;
import com.dragon.test.netty.data.contracts.NetServerContract;
import com.dragon.test.netty.data.contracts.presenter.NetServerPresenter;
import com.dragon.test.netty.data.eos.CipherUtil;
import com.dragon.test.netty.data.utils.XmlHelper;

import java.util.ArrayList;
import java.util.List;

public class UserActivity extends AppCompatActivity {

    private NetServerPresenter mPresenter;

    private RecyclerView mRCVUsersLayout;
    private LinearLayout loadingLayout;

    private RCVUserAdapter mAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_layout);
        mRCVUsersLayout = findViewById(R.id.rcv_users_layout);
        loadingLayout = findViewById(R.id.linear_loading_layout);

        mRCVUsersLayout.setLayoutManager(new GridLayoutManager(this, 4, GridLayoutManager.VERTICAL, false));

        mAdapter = new RCVUserAdapter();

        mRCVUsersLayout.setAdapter(mAdapter);

        mPresenter = new NetServerPresenter();
        mPresenter.attach(simpleNetServerView);

        requestNewPage(++idx);

        mAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                Intent it = new Intent(UserActivity.this, UserMealActivity.class);
                Bundle bundle = new Bundle();
                bundle.putParcelable("data", mAdapter.getData().get(position));
                it.putExtras(bundle);
                startActivity(it);
            }
        });
    }

    int idx = 0;

    private NetServerContract.SimpleNetServerView simpleNetServerView = new NetServerContract.SimpleNetServerView() {
        @Override
        public void onPullWhiteListSuccess(boolean hasNext, List<WhiteListUserBean> dataSet) {
            super.onPullWhiteListSuccess(hasNext, dataSet);
            mAdapter.addData(dataSet);
            if (hasNext)
                requestNewPage(++idx);
        }

        @Override
        public void onPullWhiteListFail(String errorInfo) {
            super.onPullWhiteListFail(errorInfo);
            Toast.makeText(UserActivity.this, "获取用户数据失败，错误信息：" + errorInfo, Toast.LENGTH_SHORT).show();
        }

        @Override
        public void hideLoading() {
            super.hideLoading();
            loadingLayout.setVisibility(View.GONE);
        }

        @Override
        public void showLoading() {
            super.showLoading();
            loadingLayout.setVisibility(View.VISIBLE);
        }

        @Override
        public void onEmpty() {
            super.onEmpty();
            mAdapter.replaceData(new ArrayList<>());
        }
    };

    public void requestNewPage(int pageIdx) {
        String sendData = CipherUtil.encryptData(XmlHelper.assembleWhiteList(String.valueOf(pageIdx), "10"));
        if (!TextUtils.isEmpty(sendData)) {
            mPresenter.requestPersons(null, XMLConstant.WHITE_LIST_TAG.toLowerCase(), XMLConstant.CROP_ID, sendData);
        }
    }

}
