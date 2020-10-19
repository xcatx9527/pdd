package com.Allicnce.taobaoAlliance.common.base;

import com.Allicnce.taobaoAlliance.common.rcAdapter.BaseViewHolder;

/**
 * author NullPointer
 * create time 2017/5/24$ 10:16$
 * descrition:
 */


public interface BaseParentCallBack<T> {
    public void onInitAdapterView(BaseViewHolder helper, T item);
}

