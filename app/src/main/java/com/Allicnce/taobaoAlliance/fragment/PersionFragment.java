package com.Allicnce.taobaoAlliance.fragment;


import android.view.View;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;
import butterknife.Bind;
import com.Allicnce.taobaoAlliance.R;
import com.Allicnce.taobaoAlliance.activity.AddMissionActivity;
import com.Allicnce.taobaoAlliance.common.base.BaseFragment;
import com.Allicnce.taobaoAlliance.common.rcAdapter.BaseViewPagerAdapter;
import com.Allicnce.taobaoAlliance.common.view.tab.SlidingTabLayout;
import com.google.android.material.appbar.AppBarLayout;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class PersionFragment extends BaseFragment {

    @Bind(R.id.tabbar)
    SlidingTabLayout mTabbar;
    @Bind(R.id.viewpager)
    ViewPager mViewPager;
    @Bind(R.id.app_bar_layout)
    AppBarLayout appBarLayout;
    @Bind(R.id.main_content)
    CoordinatorLayout mainContent;
    private String[] mTitles = {"图表", "任务"};
    private ArrayList<Fragment> mFragments = new ArrayList<>();


    @Override
    public int getLayoutId() {
        return R.layout.fragment_persion;
    }

    @Override
    public void initView() {
        mFragments.add(new MissionFragment("0"));
        mFragments.add(new MissionFragment("1"));
        mViewPager.setAdapter(new BaseViewPagerAdapter(getChildFragmentManager(), mFragments, mTitles));
        mTabbar.setViewPager(mViewPager);


    }

    @Override
    public void onChildClick(View v) {
        switch (v.getId()) {
            case R.id.fab:
                startActivity(AddMissionActivity.class);
                break;
        }
    }

    @Override
    public void setListener() {

    }


}
