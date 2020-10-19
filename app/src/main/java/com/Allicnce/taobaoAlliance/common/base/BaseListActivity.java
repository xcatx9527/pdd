package com.Allicnce.taobaoAlliance.common.base;

import android.os.Bundle;
import android.view.View;
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


/**
 * author NullPointer
 * create time 2017/5/23$ 18:56$
 * descrition:
 */

public abstract class BaseListActivity<T> extends BaseActivity implements View.OnClickListener, BaseParentCallBack<T> {
    private LinearLayout stateLayout;
    private ImageView stateImage;
    private TextView stateMessage;
    private PtrClassicFrameLayout mpulltorefresh;
    private int defaultPage = 1;
    private int currentPage = 1;
    private TextView stateRefresh;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        doRequest(defaultPage);
    }

    public abstract void doRequest(int Page);

    @Override
    public void showEmptyView() {
        stateLayout.setVisibility(View.VISIBLE);
        stateImage.setImageResource(R.drawable.nodata);
        stateMessage.setText("暂无数据");
        stateRefresh.setVisibility(View.GONE);
    }

    @Override
    public void showErrorView() {
        stateRefresh.setVisibility(View.VISIBLE);
        stateLayout.setVisibility(View.VISIBLE);
        stateImage.setImageResource(R.drawable.neterror);
        stateMessage.setText("网络异常,请检查您的网络");
        stateLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mpulltorefresh.autoRefresh();
            }
        });
    }

    @Override
    public void hideStateView() {
        super.hideStateView();
        if (stateLayout != null) {
            stateLayout.setVisibility(View.GONE);
        }
    }

    public BaseQuickAdapter<List<T>, BaseViewHolder> initDates(RecyclerView rvList, PtrClassicFrameLayout pulltorefresh, @LayoutRes int resId, @Nullable List<T> datas, boolean customSet) {
        if (!customSet) {
            rvList.setLayoutManager(new LinearLayoutManager(this));
            //rvList.addItemDecoration(new DividerItemDecoration(this, 1));
            if (pulltorefresh != null) {
                mpulltorefresh = pulltorefresh;
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

    /**
     * @param rvList
     * @param resId
     * @param datas
     * @param customSet
     * @return 无下拉刷新使用
     */
    public BaseQuickAdapter<List<T>, BaseViewHolder> initDates(RecyclerView rvList, @LayoutRes int resId, @Nullable List<T> datas, boolean customSet) {
        if (!customSet) {
            rvList.setLayoutManager(new LinearLayoutManager(this));
        }

        BaseQuickAdapter adapter = new BaseQuickAdapter<T, BaseViewHolder>(resId, datas) {
            @Override
            protected void convert(BaseViewHolder helper, T item) {
                onInitAdapterView(helper, item);
            }

        };
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

}
