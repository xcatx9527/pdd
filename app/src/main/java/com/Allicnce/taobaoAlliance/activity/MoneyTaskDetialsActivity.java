package com.Allicnce.taobaoAlliance.activity;


import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.*;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.Bind;
import com.Allicnce.taobaoAlliance.AppConstant;
import com.Allicnce.taobaoAlliance.R;
import com.Allicnce.taobaoAlliance.common.base.BaseActivity;
import com.Allicnce.taobaoAlliance.common.commonutils.AppUtils;
import com.Allicnce.taobaoAlliance.common.rcAdapter.BaseQuickAdapter;
import com.Allicnce.taobaoAlliance.common.rcAdapter.BaseViewHolder;
import com.Allicnce.taobaoAlliance.fragment.ChoosePriceDialogFragment;
import com.Allicnce.taobaoAlliance.model.DownApkModel;
import com.Allicnce.taobaoAlliance.model.TaskDetailsModel;
import com.Allicnce.taobaoAlliance.okgohttp.CustomRequest;
import com.Allicnce.taobaoAlliance.okgohttp.NumberProgressBar;
import com.Allicnce.taobaoAlliance.okgohttp.callback.ModelCallBack;
import com.Allicnce.taobaoAlliance.picPicker.ChoosePicActivity;
import com.Allicnce.taobaoAlliance.picPicker.GlideImageLoader;
import com.Allicnce.taobaoAlliance.utils.Object2SdCardUtils;

import java.util.HashMap;

/**
 * @author: ChenYang
 * @date: 2017-10-24 17:41
 * @Email 1016224774@qq.com
 * @Description 消息详情
 */
public class MoneyTaskDetialsActivity extends BaseActivity {


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
    @Bind(R.id.pbProgress)
    NumberProgressBar pbProgress;
    @Bind(R.id.ll_container)
    RelativeLayout llContainer;
    @Bind(R.id.tv_price)
    TextView tvPrice;
    @Bind(R.id.rv_list)
    RecyclerView rvList;
    private int currentPrice;
    private String taskid;
    private TaskDetailsModel taskDetailsModel;
    private Uri uri;
    private String mOrderId;
    private String gamePackage;

    @Override
    public int getLayoutId() {
        showToolBar("任务详情", "", true);
        return R.layout.activity_message_details;
    }

    @Override
    public void initView() {
        gamePackage = getIntent().getStringExtra("gamePackage");
        if (!TextUtils.isEmpty(gamePackage) && AppUtils.isInstallAppForPackage(gamePackage)) {
            btDownload.setText("启动");
        }
        rvList.setLayoutManager(new GridLayoutManager(this, 3));
        rvList.setHasFixedSize(true);
        doRequest();
    }

    public void doRequest() {
        HashMap<String, String> pargams = new HashMap<>();
        pargams.put("os", "Android");
        pargams.put("devInfo", AppConstant.APPID);
        pargams.put("session", Object2SdCardUtils.getObjectFormSdCard().session);
        pargams.put("userId", Object2SdCardUtils.getObjectFormSdCard().userId);
        pargams.put("taskId", taskid);
        CustomRequest.PostNoSecret(pargams, AppConstant.TASK_DETAILS, new ModelCallBack<TaskDetailsModel>(this, ModelCallBack.SHOWSTATEVIEW) {
            @Override
            public void onSuccess(com.lzy.okgo.model.Response<TaskDetailsModel> response) {
                super.onSuccess(response);
                if (response.body().code == 0) {
                    taskDetailsModel = response.body();
                    tvTitle.setText(response.body().data.gameName);
                    tv_award.setText("奖励:¥" + response.body().data.rewardAmount);
                    llChoosePrice.setVisibility(response.body().data.type == 1 ? View.VISIBLE : View.GONE);
                    uri = Uri.parse(response.body().data.gameLink);
                    mOrderId = response.body().data.orderId;
                    GlideImageLoader.displayImage(MoneyTaskDetialsActivity.this, response.body().data.gameLogo, ivHead);


                    rvList.setAdapter(new BaseQuickAdapter<String, BaseViewHolder>(R.layout.item_image, taskDetailsModel.data.demoImgList) {

                        @Override
                        protected void convert(BaseViewHolder helper, String item) {
                            GlideImageLoader.displayImage(MoneyTaskDetialsActivity.this, item, helper.getView(R.id.iv_img));
                            helper.getView(R.id.iv_img).setOnClickListener(v -> {
                                Intent intent = new Intent(MoneyTaskDetialsActivity.this, ImagePreviewActivity2.class);
                                intent.putExtra("position", helper.getLayoutPosition());
                                intent.putExtra("picPaths", taskDetailsModel.data.demoImgList);
                                startActivity(intent);
                                overridePendingTransition(R.anim.act_fade_in_center, R.anim.fixed);
                            });
                        }

                    });
                }
            }


        });
    }

    public void getTask() {
        HashMap<String, String> pargams = new HashMap<>();
        pargams.put("os", "Android");
        pargams.put("devInfo", AppConstant.APPID);
        pargams.put("session", Object2SdCardUtils.getObjectFormSdCard().session);
        pargams.put("userId", Object2SdCardUtils.getObjectFormSdCard().userId);
        pargams.put("taskId", taskid);
        pargams.put("amount", tvPrice.getText().toString().replace("¥", ""));
        CustomRequest.PostNoSecret(pargams, AppConstant.GETTASK_URL, new ModelCallBack<DownApkModel>(this, ModelCallBack.SHOWLOADING) {
            @Override
            public void onSuccess(com.lzy.okgo.model.Response<DownApkModel> response) {
                super.onSuccess(response);
                if (response.body() != null && response.body().code == 0) {
                    mOrderId = response.body().orderId;
                    if (!TextUtils.isEmpty(uri.getHost())) {
                        Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                        startActivity(intent);
                    } else {
                        showShortToast("任务异常,请联系客服..");
                    }
                }
            }

        });
    }

    @Override
    public void onMessage(Bundle bundle) {
        if (bundle != null) {
            if (bundle.getInt(ChoosePriceDialogFragment.class.getName() + "price", -1) != -1) {
                currentPrice = bundle.getInt(ChoosePriceDialogFragment.class.getName() + "price", -1);
                tvPrice.setText(currentPrice + "¥");
            }
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
                if (!TextUtils.isEmpty(mOrderId)) {
                    Bundle bundle = new Bundle();
                    bundle.putString("orderid", mOrderId);
                    startActivity(ChoosePicActivity.class);
                } else {
                    showShortToast("请先下载安装任务app");
                }
                break;
            case R.id.ll_choose_price:
                Bundle bundle = new Bundle();
                bundle.putSerializable("amountList", taskDetailsModel.data.amountList);
                ChoosePriceDialogFragment priceDialogFragment = new ChoosePriceDialogFragment();

                priceDialogFragment.setArguments(bundle);
                priceDialogFragment.show(getSupportFragmentManager(), "充值金额");
                break;
            case R.id.bt_download:
                if (btDownload.getText().equals("启动")) {
                    AppUtils.launchApp(gamePackage);
                } else {
                    if (currentPrice > 0) {
                        getTask();
                    } else {
                        Toast.makeText(MoneyTaskDetialsActivity.this, "请先选择充值金额。", Toast.LENGTH_SHORT).show();
                        llChoosePrice.performClick();
                    }
                }
                break;

        }
    }

    @Override
    public void setListener() {
        btDownload.setOnClickListener(this);
        llChoosePrice.setOnClickListener(this);
    }
}