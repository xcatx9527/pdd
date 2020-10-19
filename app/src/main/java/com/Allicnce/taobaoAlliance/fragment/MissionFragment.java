package com.Allicnce.taobaoAlliance.fragment;

import android.annotation.SuppressLint;
import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.Bind;
import com.Allicnce.taobaoAlliance.R;
import com.Allicnce.taobaoAlliance.activity.MissionDetailsActivity;
import com.Allicnce.taobaoAlliance.activity.TestDetailsActivity;
import com.Allicnce.taobaoAlliance.common.base.BaseListFragment;
import com.Allicnce.taobaoAlliance.common.rcAdapter.BaseQuickAdapter;
import com.Allicnce.taobaoAlliance.common.rcAdapter.BaseViewHolder;
import com.Allicnce.taobaoAlliance.common.smartrefresh.SmartRefreshLayout;
import com.Allicnce.taobaoAlliance.model.SaleTjModel;
import com.Allicnce.taobaoAlliance.utils.Images;
import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;


/**
 * @author: ChenYang
 * @date: 2017-10-24 17:55
 * @Email 1016224774@qq.com
 * @Description 销量统计
 */
@SuppressLint("ValidFragment")
public class MissionFragment extends BaseListFragment<SaleTjModel.LotteryOrderStatListEntity> {


    @Bind(R.id.rv_list)
    RecyclerView rvList;
    @Bind(R.id.refreshLayout)
    SmartRefreshLayout refreshLayout;
    private BaseQuickAdapter orderAdapter;
    private List<SaleTjModel.LotteryOrderStatListEntity> datas;
    String type;

    public MissionFragment(String type) {
        this.type = type;
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_sales;
    }


    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public void initView() {
        datas = new ArrayList<>();
        for (int i = 0; i < Images.imageUrls.length; i++) {
            datas.add(new SaleTjModel().new LotteryOrderStatListEntity());
        }
        orderAdapter = initDates(rvList, null, R.layout.item_sales, datas, false);
        orderAdapter.setOnItemClickListener((adapter, view, position) -> {
            ImageView iv_pic = (ImageView) view.findViewById(R.id.iv_pic);
            iv_pic.setTransitionName(Images.imageUrls[position]);//布局文件中也可以添加该属性
            Intent i = null;
            if (type.equals("0")) {

                i = new Intent(getActivityContext(), TestDetailsActivity.class);
            } else {
                i = new Intent(getActivityContext(), MissionDetailsActivity.class);
            }
            i.putExtra("path", Images.imageUrls[position]);
            ActivityOptions transitionActivityOptions = ActivityOptions.makeSceneTransitionAnimation(getActivityContext(), iv_pic, iv_pic.getTransitionName());
            startActivity(i, transitionActivityOptions.toBundle());
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
        ImageView imageView = helper.getView(R.id.iv_pic);
        Glide.with(getActivityContext()).load(Images.imageUrls[helper.getLayoutPosition()]).placeholder(R.color.colorPrimary).into(imageView);

    }


}
