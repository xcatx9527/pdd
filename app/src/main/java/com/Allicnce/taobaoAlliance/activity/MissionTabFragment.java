package com.Allicnce.taobaoAlliance.activity;


import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.widget.Toolbar;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;
import butterknife.Bind;
import com.Allicnce.taobaoAlliance.R;
import com.Allicnce.taobaoAlliance.common.base.BaseFragment;
import com.Allicnce.taobaoAlliance.common.rcAdapter.BaseViewPagerAdapter;
import com.Allicnce.taobaoAlliance.common.view.tab.SlidingTabLayout;
import com.Allicnce.taobaoAlliance.fragment.MissionFragment;
import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;


/**
 * A simple {@link androidx.fragment.app.Fragment} subclass.
 */
public class MissionTabFragment extends BaseFragment {

    @Bind(R.id.tabbar)
    SlidingTabLayout mTabbar;
    @Bind(R.id.viewpager)
    ViewPager mViewPager;
    @Bind(R.id.tv_title)
    TextView tvTitle;
    @Bind(R.id.sponsor)
    Button sponsor;
    @Bind(R.id.tool_bar)
    Toolbar toolBar;
    @Bind(R.id.app_bar_layout)
    AppBarLayout appBarLayout;
    @Bind(R.id.fab)
    FloatingActionButton fab;
    @Bind(R.id.main_content)
    CoordinatorLayout mainContent;
    private String[] mTitles = {"未接任务", "已接任务", "已完成任务"};
    private ArrayList<Fragment> mFragments = new ArrayList<>();


    @Override
    public int getLayoutId() {
        return R.layout.fragment_missiontab;
    }

    @Override
    public void initView() {
        mFragments.add(new MissionFragment("0"));
        mFragments.add(new MissionFragment("1"));
        mFragments.add(new MissionFragment("2"));
        mViewPager.setAdapter(new BaseViewPagerAdapter(getChildFragmentManager(), mFragments, mTitles));
        mTabbar.setViewPager(mViewPager);
        fab.setOnClickListener(this);
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
