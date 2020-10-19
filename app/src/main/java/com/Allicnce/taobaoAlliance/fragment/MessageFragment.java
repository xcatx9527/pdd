package com.Allicnce.taobaoAlliance.fragment;

import android.os.Bundle;
import android.view.View;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;
import butterknife.Bind;
import com.Allicnce.taobaoAlliance.R;
import com.Allicnce.taobaoAlliance.common.base.BaseFragment;
import com.Allicnce.taobaoAlliance.common.rcAdapter.BaseViewPagerAdapter;
import com.Allicnce.taobaoAlliance.common.view.tab.SlidingTabLayout;
import com.Allicnce.taobaoAlliance.common.view.tab.listener.CustomTabEntity;
import com.Allicnce.taobaoAlliance.common.view.tab.listener.OnTabSelectListener;

import java.util.ArrayList;

/**
 * @author: ChenYang
 * @date: 2017-10-24 17:41
 * @Email 1016224774@qq.com
 * @Description 消息
 */
public class MessageFragment extends BaseFragment {
    @Bind(R.id.tabbar)
    SlidingTabLayout mTabbar;
    @Bind(R.id.viewpager)
    ViewPager mViewPager;
    private String[] mTitles = {"任 务", "订 单"};

    private ArrayList<Fragment> mFragments = new ArrayList<>();
    private ArrayList<CustomTabEntity> mTabEntities = new ArrayList<>();

    @Override
    public int getLayoutId() {
        showToolBar("任务大厅", "", false);
        return R.layout.activity_message;
    }

    @Override
    public void initView() {
        mFragments.add(new MakeTaskFragment());
        mFragments.add(new MakeTaskFragment());
        mViewPager.setAdapter(new BaseViewPagerAdapter(getChildFragmentManager(), mFragments, mTitles));
        mTabbar.setViewPager(mViewPager, mTitles);
        initTabBar();
    }

    @Override
    public void onMessage(Bundle bundle) {

    }

    @Override
    public void onChildClick(View v) {
        switch (v.getId()) {

        }
    }


    @Override
    public void setListener() {

    }

    private void initTabBar() {

        mTabbar.setOnTabSelectListener(new OnTabSelectListener() {
            @Override
            public void onTabSelect(int position) {
                mViewPager.setCurrentItem(position);

            }

            @Override
            public void onTabReselect(int position) {

            }
        });

        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                mTabbar.setCurrentTab(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }

        });
        mTabbar.showDot(2);
        mViewPager.setCurrentItem(0);
    }

}
