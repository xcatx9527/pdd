package com.Allicnce.taobaoAlliance.model;

/**
 * @author: ChenYang
 * @date: 2017-10-24 17:58
 * @Email 1016224774@qq.com
 * @Description
 */
public class Pager {

    public int currentPage = 1;
    public int pageSize = 10;
    public int defaultPage = 1;

    public int getCurrentPage() {
        currentPage++;
        return currentPage;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getDefaultPage() {
        currentPage = 1;
        return defaultPage;
    }

    public void setDefaultPage(int defaultPage) {
        this.defaultPage = defaultPage;
    }
}
