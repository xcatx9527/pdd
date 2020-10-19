package com.Allicnce.taobaoAlliance.fragment;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.Bind;
import com.Allicnce.taobaoAlliance.AppConstant;
import com.Allicnce.taobaoAlliance.R;
import com.Allicnce.taobaoAlliance.common.base.BaseListFragment;
import com.Allicnce.taobaoAlliance.common.commonutils.SpannableStringUtils;
import com.Allicnce.taobaoAlliance.common.rcAdapter.BaseQuickAdapter;
import com.Allicnce.taobaoAlliance.common.rcAdapter.BaseViewHolder;
import com.Allicnce.taobaoAlliance.common.smartrefresh.header.defaultrefresh.PtrClassicFrameLayout;
import com.Allicnce.taobaoAlliance.common.tick.CountdownView;
import com.Allicnce.taobaoAlliance.model.TaskRecordModel;
import com.Allicnce.taobaoAlliance.okgohttp.CustomRequest;
import com.Allicnce.taobaoAlliance.okgohttp.callback.ModelCallBack;
import com.Allicnce.taobaoAlliance.picPicker.ChoosePicActivity;
import com.Allicnce.taobaoAlliance.picPicker.GlideImageLoader;
import com.Allicnce.taobaoAlliance.utils.Object2SdCardUtils;
import com.lzy.okgo.model.Response;
import com.lzy.okgo.request.base.Request;

import java.util.ArrayList;
import java.util.HashMap;


/**
 * @author: ChenYang
 * @date: 2017-10-24 17:55
 * @Email 1016224774@qq.com
 * @Description 任务记录
 */
@SuppressLint("ValidFragment")
public class TaskRecordFragment extends BaseListFragment<TaskRecordModel.DataEntity> {


    @Bind(R.id.rv_list)
    RecyclerView rvList;
    @Bind(R.id.pulltorefresh)
    PtrClassicFrameLayout pulltorefresh;
    private BaseQuickAdapter orderAdapter;
    private ArrayList<TaskRecordModel.DataEntity> datas = new ArrayList<>();
    private String state;

    @Override
    public int getLayoutId() {
        return R.layout.fragment_maketask;
    }

    public TaskRecordFragment(String state) {
        this.state = state;
    }

    @Override
    public void initView() {
        datas = new ArrayList<>();
        orderAdapter = initDates(rvList, pulltorefresh, R.layout.item_taskrecord, datas, false);
        View view = getActivity().getLayoutInflater().inflate(R.layout.rv_empty_view, null);
        Button button = (Button) view.findViewById(R.id.bu_show);
        button.setOnClickListener(v -> getActivity().finish());
        orderAdapter.setEmptyView(view);

    }

    @Override
    public void onChildClick(View v) {

    }

    @Override
    public void setListener() {
        orderAdapter.setOnItemClickListener((adapter, view, position) -> {
            Bundle bundle = new Bundle();
            switch (datas.get(position).status) {
                case 0:
                    bundle.putString("orderid", datas.get(position).orderId);
                    bundle.putString("state", state);
                    startActivity(ChoosePicActivity.class, bundle);
                    break;
                case 1:
                    bundle.putString("orderid", datas.get(position).orderId);
                    bundle.putString("state", state);
                    startActivity(ChoosePicActivity.class, bundle);
                    break;
                case 4:
                    bundle.putString("orderid", datas.get(position).orderId);
                    bundle.putString("state", state);
                    startActivity(ChoosePicActivity.class, bundle);
                    break;

            }

        });

    }

    @Override
    public void onMessage(Bundle bundle) {

    }


    @Override
    public void doRequest(int Page) {
        HashMap<String, String> params = new HashMap<>();
        params.put("os", "Android");
        params.put("devInfo", AppConstant.APPID);
        params.put("session", Object2SdCardUtils.getObjectFormSdCard().session);
        params.put("userId", Object2SdCardUtils.getObjectFormSdCard().userId);
        if (!state.equals("-1")) {
            params.put("status", state);
        }
        CustomRequest.PostNoSecret(params, AppConstant.GETORDERLIST_URL, new ModelCallBack<TaskRecordModel>() {
            @Override
            public void onStart(Request<TaskRecordModel, ? extends Request> request) {
                super.onStart(request);

            }

            @Override
            public void onSuccess(com.lzy.okgo.model.Response<TaskRecordModel> response) {
                super.onSuccess(response);
                if (response.body().code == 0) {
                    datas = response.body().data;
                    orderAdapter.setNewData(datas);
                }
            }

            @Override
            public void onError(Response<TaskRecordModel> response) {
                super.onError(response);
            }
        });
    }

    String[] statelables = {"待传图", "审核中", "已完成", "未通过", "长期任务", "超时"};

    @Override
    public void onInitAdapterView(BaseViewHolder helper, TaskRecordModel.DataEntity item) {
        CountdownView cdv_ticktime = helper.getView(R.id.cdv_ticktime);
        if (item.time != 0 && item.status == 0) {
            long lasttiem = 3 * 60 * 60 * 1000 - (System.currentTimeMillis() - item.time);
            cdv_ticktime.start(lasttiem);
        } else {
            cdv_ticktime.setVisibility(View.GONE);
        }
        SpannableStringUtils.Builder priceall = new SpannableStringUtils.Builder();
        if (item.type == 0) {
            priceall.append("奖励:¥").append(item.rewardAmount + "\n", R.color.md_red400)
                    .append("总金额:¥").append(item.totalAmount + "\n", R.color.md_red400);
        } else {
            priceall.append("奖励:¥").append(item.rewardAmount + "\n", R.color.md_red400).append("充值金额:¥")
                    .append(item.rewardAmount + "\n", R.color.md_red400).append("总金额:¥")
                    .append(item.totalAmount + "\n", R.color.md_red400);
        }
        if (item.status == 0) {
            helper.setVisible(R.id.bt_download, true);
        } else {
            helper.setVisible(R.id.bt_download, false);
        }
        helper.setText(R.id.tv_state, statelables[item.status]);
        helper.setText(R.id.tv_name, item.gameName);
        helper.setText(R.id.tv_priceall, priceall.create());
        GlideImageLoader.displayImage(getActivity(), item.gameLogo, helper.getView(R.id.iv_head));

    }


}
