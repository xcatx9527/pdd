package com.Allicnce.taobaoAlliance.activity;

import android.view.View;
import android.widget.TextView;
import androidx.appcompat.widget.Toolbar;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;
import butterknife.Bind;
import com.Allicnce.taobaoAlliance.R;
import com.Allicnce.taobaoAlliance.common.base.BaseActivity;
import com.Allicnce.taobaoAlliance.common.rcAdapter.BaseViewPagerAdapter;
import com.Allicnce.taobaoAlliance.common.view.tab.SlidingTabLayout;
import com.Allicnce.taobaoAlliance.fragment.TestFragment;
import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

/**
 * @author: ChenYang
 * @date: 2017-10-24 17:45
 * @Email 1016224774@qq.com
 * @Description
 */
public class TestTabActivity extends BaseActivity {
    @Bind(R.id.tabbar)
    SlidingTabLayout mTabbar;
    @Bind(R.id.viewpager)
    ViewPager mViewPager;
    @Bind(R.id.tv_title)
    TextView tvTitle;
    @Bind(R.id.tool_bar)
    Toolbar toolBar;
    @Bind(R.id.app_bar_layout)
    AppBarLayout appBarLayout;
    @Bind(R.id.fab)
    FloatingActionButton fab;
    @Bind(R.id.main_content)
    CoordinatorLayout mainContent;
    private String[] mTitles = {"发布中", "已完成", "全部"};
    private ArrayList<Fragment> mFragments = new ArrayList<>();

    @Override
    public int getLayoutId() {
        return R.layout.activity_salestab;
    }

    @Override
    public void initView() {
        mFragments.add(new TestFragment());
        mFragments.add(new TestFragment());
        mFragments.add(new TestFragment());
        mViewPager.setAdapter(new BaseViewPagerAdapter(getSupportFragmentManager(), mFragments, mTitles));
        mViewPager.setOffscreenPageLimit(3);
        mTabbar.setViewPager(mViewPager, mTitles);
        if (getIntent() != null) {
            int currenttab = getIntent().getIntExtra("tab", 0);
            mTabbar.setCurrentTab(currenttab);
        }
        toolBar.setNavigationOnClickListener(v -> {
            finish();
        });

    }


    @Override
    public void onChildClick(View v) {

    }

    @Override
    public void setListener() {
        fab.setOnClickListener(v -> startActivity(AddTestActivity.class));
    }


}
