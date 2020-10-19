package com.Allicnce.taobaoAlliance.activity;

import android.annotation.TargetApi;
import android.os.Build;
import android.transition.Explode;
import android.view.View;
import android.widget.ImageView;
import androidx.annotation.RequiresApi;
import androidx.appcompat.widget.Toolbar;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.Bind;
import com.Allicnce.taobaoAlliance.R;
import com.Allicnce.taobaoAlliance.common.base.BaseListActivity;
import com.Allicnce.taobaoAlliance.common.immersionbar.ImmersionBar;
import com.Allicnce.taobaoAlliance.common.rcAdapter.BaseQuickAdapter;
import com.Allicnce.taobaoAlliance.common.rcAdapter.BaseViewHolder;
import com.Allicnce.taobaoAlliance.common.smartrefresh.SmartRefreshLayout;
import com.Allicnce.taobaoAlliance.common.view.obliqueview.ObliqueView;
import com.Allicnce.taobaoAlliance.common.view.obliqueview.view.PolygonImageView;
import com.Allicnce.taobaoAlliance.model.SaleTjModel;
import com.bumptech.glide.Glide;
import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.appbar.CollapsingToolbarLayout;

import java.util.ArrayList;

/**
 * @author: ChenYang
 * @date: 2017-10-24 17:29
 * @Email 1016224774@qq.com
 * @Description 个人详情页demo
 */
public class MissionDetailsActivity extends BaseListActivity {

    @Bind(R.id.appBarLayout)
    AppBarLayout appBarLayout;
    @Bind(R.id.iv_book_bg)
    ImageView ivbookimg;
    @Bind(R.id.iv_top)
    PolygonImageView iv_top;
    @Bind(R.id.toolbar)
    Toolbar toolbar;
    @Bind(R.id.collapsingToolbarLayout)
    CollapsingToolbarLayout collapsingToolbarLayout;
    @Bind(R.id.topCoordinatorLayout)
    CoordinatorLayout topCoordinatorLayout;
    @Bind(R.id.obliqueView3)
    ObliqueView obliqueView3;
    @Bind(R.id.rv_list)
    RecyclerView rvList;
    @Bind(R.id.refreshLayout)
    SmartRefreshLayout refreshLayout;
    private ArrayList datas;
    private BaseQuickAdapter orderAdapter;


    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    private void setupWindowAnimations() {
        Explode explode = new Explode();
        explode.setDuration(200);
        getWindow().setExitTransition(explode);

    }

    @Override
    public void onChildClick(View v) {
        switch (v.getId()) {
            case R.id.iv_book_bg:
                break;
        }
    }

    @Override
    public int getLayoutId() {
        setupWindowAnimations();

        return R.layout.activity_book_detail;
    }


    @Override
    protected void onResume() {
        super.onResume();
    }


    @Override
    public void setListener() {

    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public void initView() {
        ImmersionBar.with(this).transparentStatusBar().init();
        Glide.with(MissionDetailsActivity.this).
                load(getIntent().getStringExtra("path"))
                .dontAnimate()
                .override(600, 500)//设置加载图片的尺寸
                .fitCenter()//相当于imageview的scype center属性
                .into(ivbookimg);
        ivbookimg.setTransitionName(getIntent().getStringExtra("path"));
        datas = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            datas.add(new SaleTjModel().new LotteryOrderStatListEntity());
        }
        orderAdapter = initDates(rvList, null, R.layout.item_timeline, datas, false);

    }

    @Override
    public void onInitAdapterView(BaseViewHolder helper, Object item) {
        switch (helper.getLayoutPosition()) {
            case 0:
                break;
            case 1:
                helper.setText(R.id.tvAcceptStation, "任务截图已提交,等待卖家审核");
                break;
            case 2:
                helper.setText(R.id.tvAcceptStation, "卖家审核完成,等待买家评价");

                break;
            case 3:

                break;

        }
    }

    @Override
    public void doRequest(int Page) {

    }

}
