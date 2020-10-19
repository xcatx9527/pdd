package com.Allicnce.taobaoAlliance.fragment;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.Bind;
import com.Allicnce.taobaoAlliance.AppConstant;
import com.Allicnce.taobaoAlliance.R;
import com.Allicnce.taobaoAlliance.activity.MoneyTaskDetialsActivity;
import com.Allicnce.taobaoAlliance.activity.RegisterTaskDetialsActivity;
import com.Allicnce.taobaoAlliance.activity.TaskRecordActivity;
import com.Allicnce.taobaoAlliance.common.base.BaseListFragment;
import com.Allicnce.taobaoAlliance.common.rcAdapter.BaseQuickAdapter;
import com.Allicnce.taobaoAlliance.common.rcAdapter.BaseViewHolder;
import com.Allicnce.taobaoAlliance.common.smartrefresh.header.defaultrefresh.PtrClassicFrameLayout;
import com.Allicnce.taobaoAlliance.model.LoginModel;
import com.Allicnce.taobaoAlliance.model.TaskModel;
import com.Allicnce.taobaoAlliance.okgohttp.CustomRequest;
import com.Allicnce.taobaoAlliance.okgohttp.NumberProgressBar;
import com.Allicnce.taobaoAlliance.okgohttp.callback.ModelCallBack;
import com.Allicnce.taobaoAlliance.picPicker.GlideImageLoader;
import com.Allicnce.taobaoAlliance.utils.Object2SdCardUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


/**
 * @author: ChenYang
 * @date: 2017-10-24 17:55
 * @Email 1016224774@qq.com
 * @Description 任务大厅
 */
@SuppressLint("ValidFragment")
public class MakeTaskFragment extends BaseListFragment<TaskModel.TaskListEntity> {


    @Bind(R.id.rv_list)
    RecyclerView rvList;
    @Bind(R.id.pulltorefresh)
    PtrClassicFrameLayout pulltorefresh;
    private BaseQuickAdapter orderAdapter;
    private List<TaskModel.TaskListEntity> datas = new ArrayList<>();

    @Override
    public int getLayoutId() {
        showToolBar("任务大厅", "任务记录", v -> {
            startActivity(TaskRecordActivity.class);
        });
        return R.layout.fragment_maketask;
    }


    @Override
    public void initView() {
        datas = new ArrayList<>();
        orderAdapter = initDates(rvList, pulltorefresh, R.layout.item_sales, datas, false);

    }

    @Override
    public void onChildClick(View v) {

    }

    @Override
    public void onResume() {
        super.onResume();
        doRequest(0);
    }

    @Override
    public void setListener() {
        orderAdapter.setOnItemClickListener((adapter, view, position) -> {
            Bundle bundle = new Bundle();
            bundle.putString("taskid", datas.get(position).taskId);
            bundle.putString("gamePackage", datas.get(position).gamePackage);
            if (datas.get(position).type == 0) {
                startActivity(RegisterTaskDetialsActivity.class, bundle);
            } else {
                startActivity(MoneyTaskDetialsActivity.class, bundle);
            }
        });
        orderAdapter.setOnItemChildClickListener((adapter, view, position) -> {
            switch (view.getId()) {
            }

        });
    }

    @Override
    public void onMessage(Bundle bundle) {

    }


    @Override
    public void doRequest(int Page) {
        LoginModel loginModel = Object2SdCardUtils.getObjectFormSdCard();
        if (loginModel == null) {
            return;
        }
        HashMap<String, String> pargams = new HashMap<>();
        pargams.put("os", "Android");
        pargams.put("session", loginModel.session);
        pargams.put("userId", loginModel.userId);
        pargams.put("devInfo", AppConstant.APPID);
        CustomRequest.PostNoSecret(pargams, AppConstant.TASK_LIST, new ModelCallBack<TaskModel>(this, ModelCallBack.SHOWSTATEVIEW) {
            @Override
            public void onSuccess(com.lzy.okgo.model.Response<TaskModel> response) {
                super.onSuccess(response);
                datas = response.body().taskList;
                orderAdapter.setNewData(datas);
            }


        });

    }


    @Override
    public void onInitAdapterView(BaseViewHolder helper, TaskModel.TaskListEntity item) {
        helper.setText(R.id.tv_name, item.gameName);
        helper.setText(R.id.tv_award, "奖励:¥" + item.rewardAmount);

        ImageView imageView = helper.getView(R.id.iv_head);
        GlideImageLoader.displayImage(getActivity(), item.gameLogo, imageView);
        NumberProgressBar progressBar = helper.getView(R.id.pbProgress);
        progressBar.setMax(100);
        progressBar.setProgress(0);
        progressBar.setVisibility(View.GONE);
        progressBar.setTag(helper.getLayoutPosition());
    }

}
