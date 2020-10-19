package com.Allicnce.taobaoAlliance.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;
import butterknife.Bind;
import cn.bingoogolapple.bgabanner.BGABanner;
import com.Allicnce.taobaoAlliance.R;
import com.Allicnce.taobaoAlliance.common.base.BaseActivity;
import com.Allicnce.taobaoAlliance.utils.Images;
import com.bumptech.glide.Glide;

import java.util.Arrays;

/**
 * @author: ChenYang
 * @date: 2017-10-24 17:40
 * @Email 1016224774@qq.com
 * @Description 引导页
 */
public class GuideActivity extends BaseActivity implements BGABanner.Delegate<ImageView, String>, BGABanner.Adapter<ImageView, String> {
    @Bind(R.id.banner_main_default)
    BGABanner bannerMainDefault;
    @Bind(R.id.banner_guide_background)
    BGABanner bannerGuideBackground;
    @Bind(R.id.banner_guide_foreground)
    BGABanner bannerGuideForeground;


    @Override
    public void onChildClick(View v) {

    }

    @Override
    public boolean dispatchKeyEvent(KeyEvent event) {
        if (event.getAction() == KeyEvent.ACTION_DOWN && event.getKeyCode() == KeyEvent.KEYCODE_BACK) {
            return true;
        }
        return super.dispatchKeyEvent(event);
    }

    @Override
    public void onMessage(Bundle bundle) {

    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_guide;
    }

    @Override
    public void initView() {
        bannerMainDefault.setAdapter(this);
        bannerMainDefault.setData(Arrays.asList(Images.imageUrls), null);
        bannerMainDefault.setDelegate(this);
        /**
         * 必须设置在数据之前
         * 设置进入按钮和跳过按钮控件资源 id 及其点击事件
         * 如果进入按钮和跳过按钮有一个不存在的话就传 0
         * 在 BGABanner 里已经帮开发者处理了防止重复点击事件
         * 在 BGABanner 里已经帮开发者处理了「跳过按钮」和「进入按钮」的显示与隐藏
         */
        bannerGuideForeground.setEnterSkipViewIdAndDelegate(R.id.btn_guide_enter, R.id.tv_guide_skip, () -> {
            startActivity(new Intent(GuideActivity.this, MainActivity.class));
            finish();
        });
        // 设置数据源
        bannerGuideBackground.setData(R.drawable.uoko_guide_background_1, R.drawable.uoko_guide_background_2, R.drawable.uoko_guide_background_3);

        bannerGuideForeground.setData(R.drawable.uoko_guide_foreground_1, R.drawable.uoko_guide_foreground_2, R.drawable.uoko_guide_foreground_3);

    }


    @Override
    public void setListener() {
    }

    // 如果开发者的引导页主题是透明的，需要在界面可见时给背景 Banner 设置一个白色背景，避免滑动过程中两个 Banner 都设置透明度后能看到 Launcher
    // bannerGuideBackground.setBackgroundResource(android.R.color.white);
    @Override
    public void onBannerItemClick(BGABanner banner, ImageView itemView, String model, int position) {
        Toast.makeText(banner.getContext(), "点击了第" + (position + 1) + "页", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void fillBannerItem(BGABanner banner, ImageView itemView, String model, int position) {
        Glide.with(itemView.getContext())
                .load(model)
                .placeholder(R.mipmap.default_image)
                .error(R.mipmap.default_image)
                .dontAnimate()
                .centerCrop()
                .into(itemView);
    }
}