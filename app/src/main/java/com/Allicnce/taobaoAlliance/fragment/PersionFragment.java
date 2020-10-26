package com.Allicnce.taobaoAlliance.fragment;


import android.view.View;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;
import butterknife.Bind;
import com.Allicnce.taobaoAlliance.R;
import com.Allicnce.taobaoAlliance.animator.SampleHeaderBehavior;
import com.Allicnce.taobaoAlliance.common.base.BaseFragment;
import com.Allicnce.taobaoAlliance.common.rcAdapter.BaseViewPagerAdapter;
import com.Allicnce.taobaoAlliance.common.view.tab.SlidingTabLayout;
import com.Allicnce.taobaoAlliance.utils.ReflexUtil;
import com.chenyang.lloglib.LLog;
import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.Map;


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
    @Bind(R.id.fab)
    FloatingActionButton floatingActionButton;
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
        floatingActionButton.setOnClickListener(this::onChildClick);
    }

    @Override
    public void onResume() {
        super.onResume();

    }

    @Override
    public void onChildClick(View v) {
        switch (v.getId()) {
            case R.id.fab:
                ThreadLocal<Map<String, Constructor<CoordinatorLayout.Behavior>>> local = (ThreadLocal<Map<String, Constructor<CoordinatorLayout.Behavior>>>) ReflexUtil.getField(mainContent.getClass(), "sConstructors");
                Map<String, Constructor<CoordinatorLayout.Behavior>> map = local.get();
                SampleHeaderBehavior sampleHeaderBehavior = new SampleHeaderBehavior();
                for (int i = 0; i < sampleHeaderBehavior.getClass().getConstructors().length; i++) {
                    LLog.e(sampleHeaderBehavior.getClass().getConstructors()[i].getName() + "+++" + sampleHeaderBehavior.getClass().getConstructors()[i].toString());
                }
                Constructor<CoordinatorLayout.Behavior> constructor = (Constructor<CoordinatorLayout.Behavior>) SampleHeaderBehavior.class.getConstructors()[1];
                map.put(SampleHeaderBehavior.class.getConstructors()[1].toString(), constructor);
                for (Map.Entry<String, Constructor<CoordinatorLayout.Behavior>> entry : map.entrySet()) {
                    LLog.e(entry.getValue() + "---" + entry.getKey());
                }
//                startActivity(AddMissionActivity.class);
                break;
        }
    }

    @Override
    public void setListener() {

    }


}
