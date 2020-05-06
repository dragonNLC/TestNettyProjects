package com.dragon.test.netty;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.dragon.test.netty.data.adapter.RCVChooseItemAdapter;
import com.dragon.test.netty.data.bean.CtlItemBean;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView rcv;

    private RCVChooseItemAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        rcv = findViewById(R.id.rcv_ctl_layout);
        rcv.setLayoutManager(new GridLayoutManager(this, 4, GridLayoutManager.VERTICAL, false));

        mAdapter = new RCVChooseItemAdapter();
        rcv.setAdapter(mAdapter);
        mAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                CtlItemBean cib = mAdapter.getData().get(position);
                startActivity(new Intent(MainActivity.this, cib.startActivity));
            }
        });

        List<CtlItemBean> data = new ArrayList<>();
        data.add(new CtlItemBean("查询所有人员的信息", UserActivity.class));
        data.add(new CtlItemBean("查询单个人的信息", UserActivity.class));
        data.add(new CtlItemBean("删除某个人的信息", UserActivity.class));
        data.add(new CtlItemBean("更新某个人的信息", UserActivity.class));
        data.add(new CtlItemBean("增加某个人的信息", UserActivity.class));
        mAdapter.replaceData(data);
    }

}
