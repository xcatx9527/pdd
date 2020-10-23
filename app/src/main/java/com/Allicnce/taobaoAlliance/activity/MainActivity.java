package com.Allicnce.taobaoAlliance.activity;

import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;
import butterknife.Bind;
import com.Allicnce.taobaoAlliance.AppApplication;
import com.Allicnce.taobaoAlliance.R;
import com.Allicnce.taobaoAlliance.common.base.BaseActivity;
import com.Allicnce.taobaoAlliance.common.commonwidget.NoScorllViewPager;
import com.Allicnce.taobaoAlliance.common.immersionbar.ImmersionBar;
import com.Allicnce.taobaoAlliance.common.rcAdapter.BaseViewPagerAdapter;
import com.Allicnce.taobaoAlliance.common.view.tab.CommonTabLayout;
import com.Allicnce.taobaoAlliance.common.view.tab.listener.CustomTabEntity;
import com.Allicnce.taobaoAlliance.common.view.tab.listener.OnTabSelectListener;
import com.Allicnce.taobaoAlliance.fragment.HomeFragment;
import com.Allicnce.taobaoAlliance.fragment.MineFragment;
import com.Allicnce.taobaoAlliance.fragment.OptionsFragment;
import com.Allicnce.taobaoAlliance.model.TabEntity;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

/**
 * @author: ChenYang
 * @date: 2017-10-24 17:41
 * @Email 1016224774@qq.com
 * @Description 主页面
 */
public class MainActivity extends BaseActivity {


    @Bind(R.id.vp_2)
    NoScorllViewPager vp2;
    @Bind(R.id.tl_2)
    CommonTabLayout tl2;
    @Bind(R.id.drawer_layout)
    DrawerLayout drawerLayout;
    private ArrayList<Fragment> mFragments = new ArrayList<>();

    private String[] mTitles = {"首页", "任务", "发现", "个人中心"};
    private int[] mIconUnselectIds = {R.drawable.ic_home_write_24dp, R.drawable.ic_bubble_chart_write_24dp,
            R.drawable.ic_explore_write_24dp, R.drawable.ic_person_outline_write_24dp};
    private int[] mIconSelectIds = {R.drawable.ic_home_green_24dp, R.drawable.ic_bubble_chart_green_24dp,
            R.drawable.ic_explore_green_24dp, R.drawable.ic_person_outline_green_24dp};
    private ArrayList<CustomTabEntity> mTabEntities = new ArrayList<>();
    private boolean isExits;


    @Override
    public int getLayoutId() {
        return R.layout.main_drawer;
    }

    @Override
    public void onMessage(Bundle bundle) {

    }


    public void checkVersion() {

    }


    @Override
    public boolean dispatchKeyEvent(KeyEvent event) {
        if (event.getAction() == KeyEvent.ACTION_DOWN && event.getKeyCode() == KeyEvent.KEYCODE_BACK) {
            isExits();
            return true;
        }
        return super.dispatchKeyEvent(event);
    }

    /**
     * 双击退出程序
     */
    private void isExits() {
        if (!isExits) {
            isExits = true;
            showShortToast("再次点击，程序退出");
            new Timer().schedule(new TimerTask() {

                @Override
                public void run() {
                    isExits = false;
                }
            }, 2000);
        } else {
            AppApplication.instance.onTerminate();
            finish();
            System.exit(0);
        }
    }

    @Override
    public void onChildClick(View v) {
        switch (v.getId()) {

        }
    }


    @Override
    public void initView() {
        checkVersion();

        ImmersionBar.with(this).transparentStatusBar().init();
        mFragments.add(new HomeFragment());
        mFragments.add(new MissionTabFragment());
        mFragments.add(new OptionsFragment());
        mFragments.add(new MineFragment());
        for (int i = 0; i < mTitles.length; i++) {
            mTabEntities.add(new TabEntity( mIconSelectIds[i], mIconUnselectIds[i]));
        }

        tl_2();
        vp2.setAdapter(new BaseViewPagerAdapter(getSupportFragmentManager(), mFragments, mTitles));
        vp2.setOffscreenPageLimit(4);

    }


    @Override
    public void setListener() {
        drawerLayout.addDrawerListener(new DrawerLayout.SimpleDrawerListener() {
            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
            }
        });
    }

    private void tl_2() {

        tl2.setTabData(mTabEntities);
        tl2.setOnTabSelectListener(new OnTabSelectListener() {
            @Override
            public void onTabSelect(int position) {
                vp2.setCurrentItem(position);
                if (position == 2) {
                    Bundle bundle = new Bundle();
                    bundle.putBoolean("refreshUser", true);
                    sendMessage(bundle);//更新我页面的用户信息
                }
            }

            @Override
            public void onTabReselect(int position) {
                if (position == 0) {//添加红点
//                    mTabLayout_2.showMsg(0, 10);
                }
            }
        });

        vp2.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                tl2.setCurrentTab(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        vp2.setCurrentItem(1);
    }


}
