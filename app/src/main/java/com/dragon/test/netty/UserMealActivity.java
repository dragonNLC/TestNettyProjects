package com.dragon.test.netty;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.dragon.test.netty.data.adapter.RCVMealAdapter;
import com.dragon.test.netty.data.adapter.RCVUserAdapter;
import com.dragon.test.netty.data.bean.UseMealResultBean;
import com.dragon.test.netty.data.bean.UserMealBean;
import com.dragon.test.netty.data.bean.WhiteListUserBean;
import com.dragon.test.netty.data.constracts.XMLConstant;
import com.dragon.test.netty.data.contracts.NetServerContract;
import com.dragon.test.netty.data.contracts.presenter.NetServerPresenter;
import com.dragon.test.netty.data.eos.CipherUtil;
import com.dragon.test.netty.data.utils.XmlHelper;

import java.util.ArrayList;
import java.util.List;

public class UserMealActivity extends AppCompatActivity {

    private LinearLayout loadingLayout;
    private RecyclerView mRCVUsersLayout;

    private NetServerPresenter mPresenter;

    private RCVMealAdapter mAdapter;

    int idx = 0;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_layout);
        mRCVUsersLayout = findViewById(R.id.rcv_users_layout);
        loadingLayout = findViewById(R.id.linear_loading_layout);
        mRCVUsersLayout.setLayoutManager(new GridLayoutManager(this, 4, GridLayoutManager.VERTICAL, false));
        mAdapter = new RCVMealAdapter();
        mRCVUsersLayout.setAdapter(mAdapter);

        mPresenter = new NetServerPresenter();
        mPresenter.attach(simpleNetServerView);

        requestNewPage(++idx);
    }

    private NetServerContract.SimpleNetServerView simpleNetServerView = new NetServerContract.SimpleNetServerView() {

        @Override
        public void onConsumeListResponse(String type, boolean hasNext, List<UserMealBean> dataSet) {
            super.onConsumeListResponse(type, hasNext, dataSet);
            if (dataSet.size() > 0) {
                mAdapter.addData(dataSet);
            } else {
                if (mAdapter.getData().size() > 0) {
                    Toast.makeText(UserMealActivity.this, "已经没有更多订餐数据了！", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(UserMealActivity.this, "现在还没有人订餐哦！", Toast.LENGTH_SHORT).show();
                }
            }
            if (hasNext) {
                requestNewPage(++idx);
            }
        }

        @Override
        public void onConsumeListFail(String errorInfo) {
            super.onConsumeListFail(errorInfo);
            Toast.makeText(UserMealActivity.this, "获取用户数据失败，错误信息：" + errorInfo, Toast.LENGTH_SHORT).show();
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
            showTips(false, "没有订餐数据");
        }

    };

    public void requestNewPage(int pageIdx) {
        String sendData = CipherUtil.encryptData(XmlHelper.assembleUserMeals(String.valueOf(pageIdx)));
        if (!TextUtils.isEmpty(sendData)) {
            mPresenter.requestPersons(null, XMLConstant.ORDER_QUERY_TAG.toLowerCase(), XMLConstant.CROP_ID, sendData);
        }
    }

}
