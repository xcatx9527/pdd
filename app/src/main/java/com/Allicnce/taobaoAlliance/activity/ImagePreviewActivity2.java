package com.Allicnce.taobaoAlliance.activity;

import android.annotation.SuppressLint;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;
import butterknife.Bind;
import com.Allicnce.taobaoAlliance.R;
import com.Allicnce.taobaoAlliance.common.base.BaseActivity;
import com.Allicnce.taobaoAlliance.common.immersionbar.ImmersionBar;
import com.Allicnce.taobaoAlliance.picPicker.GlideImageLoader;
import com.lzy.imagepicker.view.ViewPagerFixed;
import uk.co.senab.photoview.PhotoView;

import java.util.ArrayList;


/**
 * @author: ChenYang
 * @date: 2017-10-24 17:13
 * @Email 1016224774@qq.com
 * @Description 大图预览
 */
public class ImagePreviewActivity2 extends BaseActivity {


    protected ArrayList<String> mImageItems;      //跳转进ImagePreviewFragment的图片文件夹
    protected int mCurrentPosition = 0;              //跳转进ImagePreviewFragment时的序号，第几个图片
    protected ImagePageAdapter mAdapter;
    @Bind(R.id.viewpager)
    ViewPagerFixed mViewPager;
    @Bind(R.id.tv_picnum)
    TextView mTitleCount;
    @Bind(R.id.content)
    RelativeLayout content;

    @Override
    public int getLayoutId() {
        return R.layout.activity_image_preview2;
    }

    @SuppressLint("StringFormatMatches")
    @Override
    public void initView() {
        ImmersionBar.with(this).transparentStatusBar().init();

        mCurrentPosition = getIntent().getIntExtra("position", 0);

        mImageItems = (ArrayList<String>) getIntent().getSerializableExtra("picPaths");
        mAdapter = new ImagePageAdapter();

        mViewPager.setAdapter(mAdapter);
        mViewPager.setCurrentItem(mCurrentPosition, false);

        //初始化当前页面的状态
        mTitleCount.setText(getString(R.string.preview_image_count, mCurrentPosition + 1, mImageItems.size()));


        //初始化当前页面的状态
        mTitleCount.setText(getString(R.string.preview_image_count, mCurrentPosition + 1, mImageItems.size()));
        //滑动ViewPager的时候，根据外界的数据改变当前的选中状态和当前的图片的位置描述文本
        mViewPager.addOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener() {
            @Override
            public void onPageSelected(int position) {
                mCurrentPosition = position;
                mTitleCount.setText(getString(R.string.preview_image_count, mCurrentPosition + 1, mImageItems.size()));
            }
        });
    }

    @Override
    public void onChildClick(View v) {
        switch (v.getId()) {

        }
    }

    @Override
    public void setListener() {
    }


    public class ImagePageAdapter extends PagerAdapter {

        public com.lzy.imagepicker.adapter.ImagePageAdapter.PhotoViewClickListener listener;

        public ImagePageAdapter() {

        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            PhotoView photoView = new PhotoView(ImagePreviewActivity2.this);
            GlideImageLoader.displayImage(ImagePreviewActivity2.this, mImageItems.get(position), photoView);
            photoView.setOnPhotoTapListener((view, x, y) -> {
                finish();
                overridePendingTransition(R.anim.fixed, R.anim.act_fade_out_center);
            });
            container.addView(photoView);
            return photoView;
        }

        @Override
        public int getCount() {
            return mImageItems.size();
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((View) object);
        }

        @Override
        public int getItemPosition(Object object) {
            return POSITION_NONE;
        }

    }

    @Override
    public void onBackPressed() {
        finish();
        overridePendingTransition(R.anim.fixed, R.anim.act_fade_out_center);
        super.onBackPressed();
    }
}
