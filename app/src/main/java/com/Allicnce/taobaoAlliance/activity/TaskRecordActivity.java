package com.Allicnce.taobaoAlliance.activity;

import android.view.View;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;
import butterknife.Bind;
import com.Allicnce.taobaoAlliance.R;
import com.Allicnce.taobaoAlliance.common.base.BaseActivity;
import com.Allicnce.taobaoAlliance.common.rcAdapter.BaseViewPagerAdapter;
import com.Allicnce.taobaoAlliance.common.view.tab.SlidingTabLayout;
import com.Allicnce.taobaoAlliance.fragment.TaskRecordFragment;

import java.util.ArrayList;

/**
 * @author: ChenYang
 * @date: 2017-10-24 17:45
 * @Email 1016224774@qq.com
 * @Description 销量统计和排行榜tab
 */
public class TaskRecordActivity extends BaseActivity {
    @Bind(R.id.tabbar)
    SlidingTabLayout mTabbar;
    @Bind(R.id.viewpager)
    ViewPager mViewPager;
    @Bind(R.id.tool_bar)
    Toolbar toolBar;
    private String[] mTitles = {"全部", "进行中", "审核中", "已结束"};
    //    0：待传图，1：审核中，2审核通过，3：未通过，4：续充（待传图），5：超时
    private ArrayList<Fragment> mFragments = new ArrayList<>();

    @Override
    public int getLayoutId() {
        return R.layout.activity_taskrecord;
    }

    @Override
    public void initView() {
        mFragments.add(new TaskRecordFragment("-1"));
        mFragments.add(new TaskRecordFragment("0,4"));
        mFragments.add(new TaskRecordFragment("1"));
        mFragments.add(new TaskRecordFragment("3,5,2"));
        mViewPager.setAdapter(new BaseViewPagerAdapter(getSupportFragmentManager(), mFragments, mTitles));
        mViewPager.setOffscreenPageLimit(3);
        mTabbar.setViewPager(mViewPager);
        if (getIntent() != null) {
            int currenttab = getIntent().getIntExtra("tab", 0);
            mTabbar.setCurrentTab(currenttab);
        }
    }


    @Override
    public void onChildClick(View v) {

    }

    @Override
    public void setListener() {
        toolBar.setNavigationOnClickListener(v -> {
            finish();
            overridePendingTransition(R.anim.fixed, R.anim.slide_left_exit);

        });
    }


}
