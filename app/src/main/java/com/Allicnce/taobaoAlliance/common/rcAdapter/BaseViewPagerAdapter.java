package com.Allicnce.taobaoAlliance.common.rcAdapter;


import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import java.util.List;


/**
 * @author: ChenYang
 * @date: 2017-10-24 17:48
 * @Email 1016224774@qq.com
 * @Description viewpager适配器
 */
public class BaseViewPagerAdapter extends FragmentPagerAdapter {
    private final List<Fragment> mFragments;
    private CharSequence[] mTitles;


    public BaseViewPagerAdapter(FragmentManager fm, List<Fragment> fragments, String[] titles) {
        super(fm);
        this.mFragments = fragments;
        this.mTitles = titles;
    }

    @Override
    public int getCount() {
        return mFragments.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return mTitles[position];
    }

    @Override
    public Fragment getItem(int position) {
        return mFragments.get(position);
    }
}

