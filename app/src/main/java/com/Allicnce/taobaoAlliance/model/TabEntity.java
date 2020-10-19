package com.Allicnce.taobaoAlliance.model;


import com.Allicnce.taobaoAlliance.common.view.tab.listener.CustomTabEntity;

/**
 * @author: ChenYang
 * @date: 2017-10-24 17:38
 * @Email 1016224774@qq.com
 * @Description
 */
public class TabEntity implements CustomTabEntity {
    public String title;
    public int selectedIcon;
    public int unSelectedIcon;

    public TabEntity(String title, int selectedIcon, int unSelectedIcon) {
        this.title = title;
        this.selectedIcon = selectedIcon;
        this.unSelectedIcon = unSelectedIcon;
    }

    @Override
    public String getTabTitle() {
        return title;
    }

    @Override
    public int getTabSelectedIcon() {
        return selectedIcon;
    }

    @Override
    public int getTabUnselectedIcon() {
        return unSelectedIcon;
    }
}
