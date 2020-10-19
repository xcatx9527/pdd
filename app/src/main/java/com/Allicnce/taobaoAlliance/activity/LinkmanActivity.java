package com.Allicnce.taobaoAlliance.activity;

import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.widget.Toolbar;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.Bind;
import com.Allicnce.taobaoAlliance.R;
import com.Allicnce.taobaoAlliance.common.base.BaseListActivity;
import com.Allicnce.taobaoAlliance.common.commonutils.DisplayUtil;
import com.Allicnce.taobaoAlliance.common.rcAdapter.BaseQuickAdapter;
import com.Allicnce.taobaoAlliance.common.rcAdapter.BaseViewHolder;
import com.Allicnce.taobaoAlliance.common.smartrefresh.SmartRefreshLayout;
import com.Allicnce.taobaoAlliance.model.SaleTjModel;
import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

/**
 * @author: ChenYang
 * @date: 2017-10-24 17:45
 * @Email 1016224774@qq.com
 * @Description
 */
public class LinkmanActivity extends BaseListActivity<SaleTjModel.LotteryOrderStatListEntity> {
    @Bind(R.id.tv_title)
    TextView tvTitle;
    @Bind(R.id.sponsor)
    Button sponsor;
    @Bind(R.id.tool_bar)
    Toolbar toolBar;
    @Bind(R.id.app_bar_layout)
    AppBarLayout appBarLayout;
    @Bind(R.id.fab)
    FloatingActionButton fab;
    @Bind(R.id.main_content)
    CoordinatorLayout mainContent;
    @Bind(R.id.rv_list)
    RecyclerView rvList;
    @Bind(R.id.refreshLayout)
    SmartRefreshLayout refreshLayout;
    private ArrayList datas;
    private BaseQuickAdapter orderAdapter;
    private int fabtranstiony;
    private float defaulty;

    @Override
    public int getLayoutId() {
        return R.layout.activity_linkman;
    }

    @Override
    public void initView() {
        datas = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            datas.add(new SaleTjModel().new LotteryOrderStatListEntity());
        }

        defaulty = DisplayUtil.getScreenHeight() - DisplayUtil.dp2px(80);
        orderAdapter = initDates(rvList, null, R.layout.item_linkman, datas, false);
        rvList.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                if (fab.getY() <= DisplayUtil.getScreenHeight() && dy > 0) {
                    fabtranstiony += dy;
                    fab.setTranslationY(fabtranstiony);
                }
                if (fab.getY() > defaulty && dy < 0) {
                    fabtranstiony += dy;
                    fab.setTranslationY(fabtranstiony);
                }
                Log.e("--^_^-->", "gety" + fab.getY() + "fabtranstiony" + fabtranstiony + "dy" + dy + "default" + defaulty);
            }
        });
    }


    @Override
    public void onChildClick(View v) {

    }

    @Override
    public void setListener() {
        fab.setOnClickListener(v -> startActivity(AddTestActivity.class));
    }


    @Override
    public void doRequest(int Page) {

    }

    @Override
    public void onInitAdapterView(BaseViewHolder helper, SaleTjModel.LotteryOrderStatListEntity item) {

    }
}
