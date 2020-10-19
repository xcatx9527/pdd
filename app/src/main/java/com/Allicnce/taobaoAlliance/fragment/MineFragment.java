package com.Allicnce.taobaoAlliance.fragment;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.appcompat.app.AlertDialog;
import butterknife.Bind;
import com.Allicnce.taobaoAlliance.AppConstant;
import com.Allicnce.taobaoAlliance.R;
import com.Allicnce.taobaoAlliance.activity.AboutActivity;
import com.Allicnce.taobaoAlliance.activity.TaskRecordActivity;
import com.Allicnce.taobaoAlliance.activity.login.LoginActivity;
import com.Allicnce.taobaoAlliance.common.base.BaseFragment;
import com.Allicnce.taobaoAlliance.common.commonutils.DisplayUtil;
import com.Allicnce.taobaoAlliance.common.commonutils.SPUtils;
import com.Allicnce.taobaoAlliance.common.commonutils.SpannableStringUtils;
import com.Allicnce.taobaoAlliance.common.view.obliqueview.shapes.StarPolygonShape;
import com.Allicnce.taobaoAlliance.common.view.obliqueview.view.PolygonImageView;
import com.Allicnce.taobaoAlliance.model.UserModel;
import com.Allicnce.taobaoAlliance.okgohttp.CustomRequest;
import com.Allicnce.taobaoAlliance.okgohttp.callback.ModelCallBack;
import com.Allicnce.taobaoAlliance.picPicker.GlideImageLoader;
import com.Allicnce.taobaoAlliance.utils.Object2SdCardUtils;
import com.Allicnce.taobaoAlliance.widget.PullZoomView.PullToZoomScrollViewEx;
import com.bumptech.glide.Glide;
import com.lzy.imagepicker.ImagePicker;
import com.lzy.imagepicker.bean.ImageItem;
import com.lzy.imagepicker.ui.ImageGridActivity;
import com.lzy.imagepicker.view.CropImageView;
import com.lzy.okgo.model.Response;
import com.lzy.okgo.request.base.Request;

import java.util.ArrayList;
import java.util.HashMap;

import static com.Allicnce.taobaoAlliance.picPicker.ChoosePicActivity.REQUEST_CODE_PREVIEW;
import static com.Allicnce.taobaoAlliance.picPicker.ChoosePicActivity.REQUEST_CODE_SELECT;


/**
 * @author: ChenYang
 * @date: 2017-10-24 17:55
 * @Email 1016224774@qq.com
 * @Description 我的
 */
public class MineFragment extends BaseFragment {
    PolygonImageView ivHeadpic;
    @Bind(R.id.zoomrecyclerview)
    PullToZoomScrollViewEx zoomrecyclerview;
    private ImagePicker imagePicker;
    private TextView tv_userId;
    private Dialog deletedialog;
    private TextView tv_unaward;
    private TextView tv_canaward;
    private TextView tv_award;
    private TextView tv_weixin;
    private LinearLayout ll_takemoney;

    @Override
    public int getLayoutId() {
        return R.layout.fragment_mine;
    }

    @Override
    public void initView() {
        initImagePicker();
        initRecycler();
        doRequest();
    }


    public void initRecycler() {
        View headView = LayoutInflater.from(getActivity()).inflate(R.layout.pullzoom_head_view, null, false);
        ivHeadpic = (PolygonImageView) headView.findViewById(R.id.iv_headpic);
        ivHeadpic.setOnClickListener(this);
        tv_unaward = (TextView) headView.findViewById(R.id.tv_unaward);
        tv_award = (TextView) headView.findViewById(R.id.tv_award);
        tv_canaward = (TextView) headView.findViewById(R.id.tv_canaward);
        tv_userId = (TextView) headView.findViewById(R.id.tv_userid);
        ll_takemoney = (LinearLayout) headView.findViewById(R.id.ll_takemoney);


        Glide.with(getActivityContext()).load(new SPUtils().getString("headpic")).error(R.drawable.default_headpic).into(ivHeadpic);
        View zoomView = LayoutInflater.from(getActivity()).inflate(R.layout.pull_zoom_bg, null, false);
        View contentView = LayoutInflater.from(getActivity()).inflate(R.layout.pullzoom_content_view, null, false);
        RelativeLayout rl_bag = (RelativeLayout) contentView.findViewById(R.id.rl_bag);
        RelativeLayout rl_message = (RelativeLayout) contentView.findViewById(R.id.rl_message);
        RelativeLayout rl_hemai = (RelativeLayout) contentView.findViewById(R.id.rl_hemai);
        RelativeLayout rl_examine = (RelativeLayout) contentView.findViewById(R.id.rl_examine);
        RelativeLayout rl_setting = (RelativeLayout) contentView.findViewById(R.id.rl_setting);
        RelativeLayout rl_loginout = (RelativeLayout) contentView.findViewById(R.id.rl_loginout);
        RelativeLayout rl_sales = (RelativeLayout) contentView.findViewById(R.id.rl_sales);
        RelativeLayout rl_about = (RelativeLayout) contentView.findViewById(R.id.rl_about);
        tv_weixin = (TextView) contentView.findViewById(R.id.tv_weixin);
        rl_bag.setOnClickListener(this);
        rl_message.setOnClickListener(this);
        rl_hemai.setOnClickListener(this);
        rl_examine.setOnClickListener(this);
        rl_setting.setOnClickListener(this);
        rl_loginout.setOnClickListener(this);
        rl_sales.setOnClickListener(this);
        rl_about.setOnClickListener(this);

        zoomrecyclerview.setHeaderView(headView);
        zoomrecyclerview.setZoomView(zoomView);
        zoomrecyclerview.setScrollContentView(contentView);
        zoomrecyclerview.setParallax(true);
        zoomrecyclerview.setZoomEnabled(true);
        zoomrecyclerview.setParallax(true);
        int mScreenWidth = DisplayUtil.getScreenWidth();
        LinearLayout.LayoutParams localObject = new LinearLayout.LayoutParams(mScreenWidth, DisplayUtil.dp2px(260));
        zoomrecyclerview.setHeaderLayoutParams(localObject);

        //如果不想设置边框但又想设置阴影,可以将边框设为阴影类似颜色,如不设置边框,阴影不明显
        ivHeadpic.addShadow(6f, 0f, 0f, Color.GRAY);//设置阴影 大小,x偏移,y偏移,颜色
        ivHeadpic.addBorder(1, getResources().getColor(R.color.md_grey300));//设置边框 宽度,颜色
        ivHeadpic.setCornerRadius(10);//设置圆角
        ivHeadpic.setVertices(0);//设置顶点数,没有顶点为圆形
        ivHeadpic.setPolygonShape(new StarPolygonShape(0.8f, false));
    }


    public void doRequest() {

    }

    public void takeMoney() {
        HashMap<String, String> params = new HashMap<>();
        params.put("os", "Android");
        params.put("devInfo", AppConstant.APPID);
        params.put("session", Object2SdCardUtils.getObjectFormSdCard().session);
        params.put("userId", Object2SdCardUtils.getObjectFormSdCard().userId);
        CustomRequest.PostNoSecret(params, AppConstant.TAKEMONEY_URL, new ModelCallBack<UserModel>() {
            @Override
            public void onStart(Request<UserModel, ? extends Request> request) {
                super.onStart(request);

            }

            @Override
            public void onSuccess(com.lzy.okgo.model.Response<UserModel> response) {
                super.onSuccess(response);
                if (response.body().code == 0) {

                }
            }

            @Override
            public void onError(com.lzy.okgo.model.Response<UserModel> response) {
                super.onError(response);
            }
        });
    }

    private void initImagePicker() {
        imagePicker = ImagePicker.getInstance();
        imagePicker.setStyle(CropImageView.Style.RECTANGLE);    //设置矩形裁剪
        imagePicker.setMultiMode(false);                         //设置单选多选
        imagePicker.setImageLoader(GlideImageLoader.instance);   //设置图片加载器
        imagePicker.setShowCamera(true);                      //显示拍照按钮
        imagePicker.setCrop(true);                           //允许裁剪（单选才有效）
        imagePicker.setSaveRectangle(true);                   //是否按矩形区域保存
        imagePicker.setStyle(CropImageView.Style.RECTANGLE);  //裁剪框的形状
        imagePicker.setFocusWidth(800);                       //裁剪框的宽度。单位像素（圆形自动取宽高最小值）
        imagePicker.setFocusHeight(800);                      //裁剪框的高度。单位像素（圆形自动取宽高最小值）
        imagePicker.setOutPutX(1000);                         //保存文件的宽度。单位像素
        imagePicker.setOutPutY(1000);                         //保存文件的高度。单位像素
    }

    @Override
    public void onChildClick(View v) {
        switch (v.getId()) {
            case R.id.iv_headpic:
                ImageItem imageItem = new ImageItem();
                ArrayList<ImageItem> imageItems = new ArrayList<>();
                imageItems.add(imageItem);
                imageItem.path = new SPUtils().getString("headpic");
                Intent intent1 = new Intent(getActivity(), ImageGridActivity.class);
                startActivityForResult(intent1, REQUEST_CODE_SELECT);
                getActivity().overridePendingTransition(R.anim.act_fade_in_center, R.anim.fixed);
              /*  if (TextUtils.isEmpty(imageItem.path)) {
                    Intent intent1 = new Intent(getActivity(), ImageGridActivity.class);
                    startActivityForResult(intent1, REQUEST_CODE_SELECT);
                    getActivity().overridePendingTransition(R.anim.act_fade_in_center, R.anim.fixed);
                } else {
                    Intent intent = new Intent(getActivity(), ImagePreviewActivity2.class);
                    intent.putExtra(ImagePicker.EXTRA_SELECTED_IMAGE_POSITION, 0);
                    intent.putExtra(ImagePicker.EXTRA_IMAGE_ITEMS, imageItems);
                    startActivity(intent);
                    getActivity().overridePendingTransition(R.anim.act_fade_in_center, R.anim.fixed);
                }*/

                break;
            case R.id.rl_bag:

                break;
            case R.id.rl_sales:
                startActivity(TaskRecordActivity.class);
                break;
            case R.id.rl_message:
                break;
            case R.id.rl_about:
                startActivity(AboutActivity.class);
                break;
            case R.id.ll_takemoney:
                takeMoney();
                break;

            case R.id.rl_loginout:
                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                builder.setCancelable(true).setTitle("确定退出?").setNegativeButton("取消", (dialog, which) -> dialog.dismiss()).setPositiveButton("确定", (dialog, which) -> {
                    Object2SdCardUtils.DelateObjectToSdCard();
                    startActivity(LoginActivity.class);
                    getActivity().finish();
                }).create().show();

                break;

        }
    }

    @Override
    public void setListener() {
        ll_takemoney.setOnClickListener(this);
    }

    @Override
    public void onMessage(Bundle bundle) {
        super.onMessage(bundle);
        if (bundle != null && bundle.containsKey("refreshUser")) {
            Log.e("--^_^-->", "onMessage");
        }

    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (isVisibleToUser) {
            doRequest();
        } else {
        }
    }

    ArrayList<ImageItem> images = null;

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == ImagePicker.RESULT_CODE_ITEMS) {
            //添加图片返回
            if (data != null && requestCode == REQUEST_CODE_SELECT) {
                images = (ArrayList<ImageItem>) data.getSerializableExtra(ImagePicker.EXTRA_RESULT_ITEMS);
                if (images != null) {
                    new SPUtils().put("headpic", images.get(0).path);
                    Glide.with(getActivity()).load(images.get(0).path).into(ivHeadpic);
                }
            }
        } else if (resultCode == ImagePicker.RESULT_CODE_BACK) {
            //预览图片返回
            if (data != null && requestCode == REQUEST_CODE_PREVIEW) {
                images = (ArrayList<ImageItem>) data.getSerializableExtra(ImagePicker.EXTRA_IMAGE_ITEMS);
                if (images != null) {

                }
            }
        }
    }

}
