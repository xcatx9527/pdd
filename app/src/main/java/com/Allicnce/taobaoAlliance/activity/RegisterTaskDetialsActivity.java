package com.Allicnce.taobaoAlliance.activity;


import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.Bind;
import com.Allicnce.taobaoAlliance.AppConstant;
import com.Allicnce.taobaoAlliance.R;
import com.Allicnce.taobaoAlliance.common.base.BaseListActivity;
import com.Allicnce.taobaoAlliance.common.commonutils.AppUtils;
import com.Allicnce.taobaoAlliance.common.rcAdapter.BaseQuickAdapter;
import com.Allicnce.taobaoAlliance.common.rcAdapter.BaseViewHolder;
import com.Allicnce.taobaoAlliance.model.DownApkModel;
import com.Allicnce.taobaoAlliance.model.TaskDetailsModel;
import com.Allicnce.taobaoAlliance.okgohttp.CustomRequest;
import com.Allicnce.taobaoAlliance.okgohttp.callback.ModelCallBack;
import com.Allicnce.taobaoAlliance.picPicker.ChoosePicActivity;
import com.Allicnce.taobaoAlliance.picPicker.GlideImageLoader;
import com.Allicnce.taobaoAlliance.utils.Object2SdCardUtils;
import com.lzy.okgo.model.Response;

import java.util.HashMap;

/**
 * @author: ChenYang
 * @date: 2017-10-24 17:41
 * @Email 1016224774@qq.com
 * @Description 消息详情
 */
public class RegisterTaskDetialsActivity extends BaseListActivity<TaskDetailsModel.DataEntity.StatusListEntity> {


    @Bind(R.id.tv_name)
    TextView tvTitle;
    @Bind(R.id.tv_content)
    TextView tvContent;
    @Bind(R.id.bt_commit)
    Button btCommit;
    @Bind(R.id.tv_award)
    TextView tv_award;
    @Bind(R.id.container)
    LinearLayout container;
    @Bind(R.id.bt_download)
    Button btDownload;

    @Bind(R.id.ll_choose_price)
    LinearLayout llChoosePrice;
    @Bind(R.id.iv_head)
    ImageView ivHead;
    @Bind(R.id.tv_price)
    TextView tvPrice;
    @Bind(R.id.rv_list)
    RecyclerView rvList;
    @Bind(R.id.tv_howdo)
    TextView tvHowdo;
    private String taskid;
    private Uri uri;
    private String mOrderId;
    private String gamePackage;
    private BaseQuickAdapter mAdapter;
    private TaskDetailsModel.DataEntity datas;

    @Override
    public int getLayoutId() {
        showToolBar("任务详情", "", true);
        return R.layout.activity_longtask_details;
    }

    @Override
    public void initView() {
        gamePackage = getIntent().getStringExtra("gamePackage");
        if (!TextUtils.isEmpty(gamePackage) && AppUtils.isInstallAppForPackage(gamePackage)) {
            btDownload.setText("启动");
        }
        datas = new TaskDetailsModel().new DataEntity();
        mAdapter = initDates(rvList, R.layout.item_longtask, datas.statusList, false);
    }


    public void getTask() {
        HashMap<String, String> pargams = new HashMap<>();
        pargams.put("os", "Android");
        pargams.put("devInfo", AppConstant.APPID);
        pargams.put("session", Object2SdCardUtils.getObjectFormSdCard().session);
        pargams.put("userId", Object2SdCardUtils.getObjectFormSdCard().userId);
        pargams.put("taskId", taskid);
        pargams.put("amount", tvPrice.getText() + "");
        CustomRequest.PostNoSecret(pargams, AppConstant.GETTASK_URL, new ModelCallBack<DownApkModel>(this, ModelCallBack.SHOWLOADING) {
            @Override
            public void onSuccess(Response<DownApkModel> response) {
                super.onSuccess(response);
                if (response.body() != null && response.body().code == 0) {
                    mOrderId = response.body().orderId;
                    Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                    startActivity(intent);
                }
            }

        });
    }

    @Override
    public void onMessage(Bundle bundle) {
        if (bundle != null) {
            if (!TextUtils.isEmpty(bundle.getString("taskid"))) {
                taskid = bundle.getString("taskid");
            }
        }

    }


    @Override
    protected void onResume() {
        super.onResume();
        if (!TextUtils.isEmpty(gamePackage) && AppUtils.isInstallAppForPackage(gamePackage)) {
            btDownload.setText("启动");
        } else {
            btDownload.setText("下载");
        }
    }

    @Override
    public void onChildClick(View v) {
        switch (v.getId()) {
            case R.id.bt_commit:
                if (!AppUtils.isInstallAppForPackage(gamePackage)) {
                    showShortToast("请先下载安装任务app");
                    return;
                }
                if (!TextUtils.isEmpty(mOrderId)) {
                    Bundle bundle = new Bundle();
                    bundle.putString("orderid", mOrderId);
                    startActivity(ChoosePicActivity.class, bundle);
                } else {
                    showShortToast("您已安装过此app,无法接任务,请卸载后重试");
                }
                break;
            case R.id.bt_download:
                if (btDownload.getText().equals("启动")) {
                    AppUtils.launchApp(gamePackage);
                } else {
                    getTask();
                }
                break;
            case R.id.tv_howdo:
                Bundle bundle1 = new Bundle();
                bundle1.putSerializable("pics", datas.demoImgList);
                startActivity(HelpActivity.class, bundle1);
                break;

        }
    }


    @Override
    public void setListener() {
        btDownload.setOnClickListener(this);
        llChoosePrice.setOnClickListener(this);
        tvHowdo.setOnClickListener(this);
    }


    @Override
    public void doRequest(int Page) {
        HashMap<String, String> pargams = new HashMap<>();
        pargams.put("os", "Android");
        pargams.put("devInfo", AppConstant.APPID);
        pargams.put("session", Object2SdCardUtils.getObjectFormSdCard().session);
        pargams.put("userId", Object2SdCardUtils.getObjectFormSdCard().userId);
        pargams.put("taskId", taskid);
        CustomRequest.PostNoSecret(pargams, AppConstant.TASK_DETAILS, new ModelCallBack<TaskDetailsModel>(this, ModelCallBack.SHOWSTATEVIEW) {
            @Override
            public void onSuccess(Response<TaskDetailsModel> response) {
                super.onSuccess(response);
                if (response.body().code == 0) {
                    tvTitle.setText(response.body().data.gameName);
                    tv_award.setText("奖励:¥" + response.body().data.rewardAmount);
                    llChoosePrice.setVisibility(response.body().data.type == 1 ? View.VISIBLE : View.GONE);
                    uri = Uri.parse(response.body().data.gameLink);
                    mOrderId = response.body().data.orderId;
                    Log.e("--^_^-->", mOrderId + "orderid");
                    datas = response.body().data;
                    mAdapter.setNewData(response.body().data.statusList);
                    GlideImageLoader.displayImage(RegisterTaskDetialsActivity.this, response.body().data.gameLogo, ivHead);

                }
            }


        });
    }

    @Override
    public void onInitAdapterView(BaseViewHolder helper, TaskDetailsModel.DataEntity.StatusListEntity item) {

        if (helper.getLayoutPosition() == 0) {
            helper.setText(R.id.tv_name, "第" + (helper.getLayoutPosition() + 1 + "天注册"));
        } else {
            helper.setText(R.id.tv_name, "第" + (helper.getLayoutPosition() + 1 + "天留存"));
        }
        helper.setText(R.id.tv_content, datas.gameName);
        Button bt_lable = helper.getView(R.id.bt_lable);
        switch (item.status) {
            case 0:
                bt_lable.setText(item.desc);
                bt_lable.setBackground(getResources().getDrawable(R.drawable.blue_conner_5bg));
                bt_lable.setTextColor(Color.WHITE);
                break;
            case 1:
                bt_lable.setText("未完成");
                bt_lable.setBackground(getResources().getDrawable(R.drawable.gray_conner_5bg));

                bt_lable.setTextColor(Color.WHITE);
                break;
            case 2:
                bt_lable.setText("已完成");
                bt_lable.setTextColor(getResources().getColor(R.color.colorPrimary));
                bt_lable.setBackground(getResources().getDrawable(R.drawable.blue_bord_bg));
                break;

        }

    }

}