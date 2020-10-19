package com.Allicnce.taobaoAlliance.fragment;

import android.annotation.SuppressLint;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.Bind;
import com.Allicnce.taobaoAlliance.R;
import com.Allicnce.taobaoAlliance.activity.TestDetailsActivity;
import com.Allicnce.taobaoAlliance.common.base.BaseListFragment;
import com.Allicnce.taobaoAlliance.common.rcAdapter.BaseQuickAdapter;
import com.Allicnce.taobaoAlliance.common.rcAdapter.BaseViewHolder;
import com.Allicnce.taobaoAlliance.common.smartrefresh.SmartRefreshLayout;
import com.Allicnce.taobaoAlliance.model.SaleTjModel;

import java.util.ArrayList;
import java.util.List;


/**
 * @author: ChenYang
 * @date: 2017-10-24 17:55
 * @Email 1016224774@qq.com
 * @Description 测试
 */
@SuppressLint("ValidFragment")
public class TestFragment extends BaseListFragment<SaleTjModel.LotteryOrderStatListEntity> {


    @Bind(R.id.rv_list)
    RecyclerView rvList;
    @Bind(R.id.refreshLayout)
    SmartRefreshLayout refreshLayout;
    /*   @Bind(R.id.pulltorefresh)
       PtrClassicFrameLayout pulltorefresh;*/
    private BaseQuickAdapter orderAdapter;
    private List<SaleTjModel.LotteryOrderStatListEntity> datas;

    @Override
    public int getLayoutId() {
        return R.layout.fragment_sales;
    }


    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public void initView() {
        datas = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            datas.add(new SaleTjModel().new LotteryOrderStatListEntity());
        }
        orderAdapter = initDates(rvList, null, R.layout.item_test, datas, false);
        orderAdapter.setOnItemClickListener((adapter, view, position) -> {
            startActivity(TestDetailsActivity.class);
        });
    }

    @Override
    public void onChildClick(View v) {

    }

    @Override
    public void setListener() {

    }

    @Override
    public void onMessage(Bundle bundle) {

    }


    @Override
    public void doRequest(int Page) {

    }

    @Override
    public void onInitAdapterView(BaseViewHolder helper, SaleTjModel.LotteryOrderStatListEntity item) {


    }


}
