package com.Allicnce.taobaoAlliance.fragment;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.Bind;
import cn.bingoogolapple.bgabanner.BGABanner;
import com.Allicnce.taobaoAlliance.R;
import com.Allicnce.taobaoAlliance.activity.LinkmanActivity;
import com.Allicnce.taobaoAlliance.activity.ProjectUIActivity;
import com.Allicnce.taobaoAlliance.activity.StatisticsActivity;
import com.Allicnce.taobaoAlliance.activity.TestTabActivity;
import com.Allicnce.taobaoAlliance.common.base.BaseListFragment;
import com.Allicnce.taobaoAlliance.common.commonutils.SpannableStringUtils;
import com.Allicnce.taobaoAlliance.common.rcAdapter.BaseQuickAdapter;
import com.Allicnce.taobaoAlliance.common.rcAdapter.BaseViewHolder;
import com.Allicnce.taobaoAlliance.common.smartrefresh.SmartRefreshLayout;
import com.Allicnce.taobaoAlliance.common.tick.CountdownView;
import com.Allicnce.taobaoAlliance.model.TopListModel;
import com.Allicnce.taobaoAlliance.request.SalesRequest;
import com.Allicnce.taobaoAlliance.utils.Images;
import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;


/**
 * @author: ChenYang
 * @date: 2017-10-24 17:55
 * @Email 1016224774@qq.com
 * @Description 首页
 */
@SuppressLint("ValidFragment")
public class HomeFragment extends BaseListFragment<TopListModel.GameListEntity> implements BaseQuickAdapter.OnItemChildClickListener {


    @Bind(R.id.rv_list)
    RecyclerView rvList;
    BGABanner bgabanner;
    RecyclerView recyclerview1;
    @Bind(R.id.refreshLayout)
    SmartRefreshLayout refreshLayout;
    private BaseQuickAdapter orderAdapter;
    private List<TopListModel.GameListEntity> datas;
    private SalesRequest orderListRequest;
    private String[] titles = {"发布任务", "做任务", "任务统计", "任务帮助", "提现", "紧急客服"};
    private int[] mIconSelectIds = {R.drawable.ic_home_green_24dp, R.drawable.ic_bubble_chart_green_24dp, R.drawable.ic_add_circle_green_24dp,
            R.drawable.ic_explore_green_24dp, R.drawable.ic_person_outline_green_24dp, R.drawable.ic_person_outline_green_24dp};

    @Override
    public int getLayoutId() {
        return R.layout.home_fragment;
    }


    @Override
    public void initView() {
        datas = new ArrayList<>();
        for (int i = 0; i < 6; i++) {
            datas.add(new TopListModel().new GameListEntity());
        }
        orderAdapter = initDates(rvList, null, R.layout.item_message, datas, false);
        View view = getActivityContext().getLayoutInflater().inflate(R.layout.header_home, null);
        TextView iv_worn = (TextView) view.findViewById(R.id.iv_worn);
        iv_worn.setText(new SpannableStringUtils.Builder().append("公告\n").setFontSize(18, true)
                .append("请所有用户务必看完任务帮助后再进行发布任务或接任务操作!账号'XXX',因不按规定操作已经被系统永久封号!请大家珍惜自己的账号").create());
        recyclerview1 = (RecyclerView) view.findViewById(R.id.recyclerview1);
        CountdownView mCvCountdownView = (CountdownView) view.findViewById(R.id.cdv_ticktime);
        mCvCountdownView.start(30 * 60 * 1000 * 100);
        recyclerview1.setLayoutManager(new GridLayoutManager(getActivityContext(), 3));
        orderAdapter.setHeaderView(view);

        BaseQuickAdapter headerAdapter = new BaseQuickAdapter<TopListModel.GameListEntity, BaseViewHolder>(R.layout.item_home_model, datas) {

            @Override
            protected void convert(BaseViewHolder helper, TopListModel.GameListEntity item) {
                helper.setText(R.id.tv_text, titles[helper.getLayoutPosition()]);
                helper.setImageResource(R.id.iv_icon, mIconSelectIds[helper.getLayoutPosition()]);
            }
        };
        recyclerview1.setAdapter(headerAdapter);
        headerAdapter.setOnItemClickListener(this::onItemChildClick);
        bgabanner = (BGABanner) view.findViewById(R.id.bgabanner);
        ImageView imageView1 = new ImageView(getActivityContext());
        ImageView imageView2 = new ImageView(getActivityContext());
        ImageView imageView3 = new ImageView(getActivityContext());
        imageView1.setScaleType(ImageView.ScaleType.FIT_XY);
        imageView2.setScaleType(ImageView.ScaleType.FIT_XY);
        imageView3.setScaleType(ImageView.ScaleType.FIT_XY);
        List<View> imageViews = new ArrayList<>();
        imageViews.add(imageView1);
        imageViews.add(imageView2);
        imageViews.add(imageView3);
        Glide.with(getActivityContext()).load(Images.imageUrls[0]).into(imageView1);
        Glide.with(getActivityContext()).load(Images.imageUrls[3]).into(imageView2);
        Glide.with(getActivityContext()).load(Images.imageUrls[2]).into(imageView3);
        bgabanner.setData(imageViews);
    }

    @Override
    public void onChildClick(View v) {

    }

    @Override
    public void setListener() {

    }

    @Override
    public void onMessage(Bundle bundle) {
        if (bundle != null && bundle.getInt("currenttab") == 1) {
            orderListRequest = (SalesRequest) bundle.getSerializable("SalesRequest");
            doRequest(1);
        }
    }

    @Override
    public void doRequest(int Page) {
        if (orderListRequest == null) {
            orderListRequest = new SalesRequest();
            orderListRequest.pager.currentPage = 1;
            orderListRequest.pager.pageSize = 10;
        }

    }

    @Override
    public void onInitAdapterView(BaseViewHolder helper, TopListModel.GameListEntity item) {

    }

    @Override
    public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
        switch (position) {
            case 0:
                startActivity(TestTabActivity.class);
                break;
            case 1:
//                startActivity(LinkmanActivity.class);

                break;
            case 2:
                startActivity(StatisticsActivity.class);

                break;
            case 3:
                startActivity(LinkmanActivity.class);

                break;
            case 4:
                startActivity(ProjectUIActivity.class);

                break;
            case 5:

                break;
        }
    }
}