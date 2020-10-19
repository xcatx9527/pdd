package com.Allicnce.taobaoAlliance.common.base;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.annotation.LayoutRes;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.Allicnce.taobaoAlliance.R;
import com.Allicnce.taobaoAlliance.common.rcAdapter.BaseQuickAdapter;
import com.Allicnce.taobaoAlliance.common.rcAdapter.BaseViewHolder;
import com.Allicnce.taobaoAlliance.common.smartrefresh.header.defaultrefresh.*;

import java.util.List;

public abstract class BaseListFragment<T> extends BaseFragment implements View.OnClickListener, BaseParentCallBack<T> {
    private LinearLayout stateLayout;
    private ImageView stateImage;
    private TextView stateMessage;
    private int defaultPage = 1;
    private int currentPage = 1;
    private TextView stateRefresh;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = super.onCreateView(inflater, container, savedInstanceState);
        doRequest(defaultPage);
        return view;
    }

    public abstract void doRequest(int Page);

    public BaseQuickAdapter<List<T>, BaseViewHolder> initDates(RecyclerView rvList, PtrClassicFrameLayout pulltorefresh, @LayoutRes int resId, @Nullable List<T> datas, boolean customSet) {
        if (!customSet) {
            rvList.setLayoutManager(new LinearLayoutManager(getActivityContext()));
//        rvList.addItemDecoration(new DividerItemDecoration(getContext(), 1));
            if (pulltorefresh != null) {
                pulltorefresh.setLastUpdateTimeRelateObject(this);
                pulltorefresh.setDurationToCloseHeader(1000);
            }

        }
        if (pulltorefresh != null) {
            pulltorefresh.setPtrHandler(new PtrHandler2() {
                @Override
                public boolean checkCanDoRefresh(PtrFrameLayout frame, View content, View header) {
                    return PtrDefaultHandler.checkContentCanBePulledDown(frame, content, header);
                }

                @Override
                public boolean checkCanDoLoadMore(PtrFrameLayout frame, View content, View footer) {
                    return PtrDefaultHandler2.checkContentCanBePulledUp(frame, content, footer);
                }

                @Override
                public void onLoadMoreBegin(PtrFrameLayout frame) {
                    currentPage++;
                    doRequest(currentPage);
                    frame.postDelayed(() -> frame.refreshComplete(), 1000);
                }

                @Override
                public void onRefreshBegin(PtrFrameLayout frame) {
                    currentPage = defaultPage;
                    doRequest(defaultPage);
                    frame.postDelayed(() -> frame.refreshComplete(), 1000);

                }
            });
        }
        BaseQuickAdapter adapter = new BaseQuickAdapter<T, BaseViewHolder>(resId, datas) {
            @Override
            protected void convert(BaseViewHolder helper, T item) {
                onInitAdapterView(helper, item);
            }

        };
        rvList.setAdapter(adapter);
        adapter.bindToRecyclerView(rvList);
        adapter.setEmptyView(R.layout.empty_view);
        stateLayout = (LinearLayout) adapter.getEmptyView().findViewById(R.id.ll_state);
        stateImage = (ImageView) adapter.getEmptyView().findViewById(R.id.iv_state_img);
        stateMessage = (TextView) adapter.getEmptyView().findViewById(R.id.tv_state_message);
        stateRefresh = (TextView) adapter.getEmptyView().findViewById(R.id.btn_refresh);

        stateRefresh.setOnClickListener(v -> {
            if (!isFastClick()) {
                doRequest(1);
            }
        });
        return adapter;
    }

    @Override
    public void showEmptyView() {
        stateLayout.setVisibility(View.VISIBLE);
        stateImage.setImageResource(R.drawable.nodata);
        stateMessage.setText("暂无数据");
        stateRefresh.setVisibility(View.GONE);
    }

    @Override
    public void hideStateView() {
        stateLayout.setVisibility(View.GONE);
    }

    @Override
    public void showErrorView() {
        stateRefresh.setVisibility(View.VISIBLE);
        stateLayout.setVisibility(View.VISIBLE);
        stateImage.setImageResource(R.drawable.neterror);
        stateMessage.setText("网络异常,请检查您的网络");
    }
}
