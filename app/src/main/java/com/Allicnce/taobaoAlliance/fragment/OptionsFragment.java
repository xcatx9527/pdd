package com.Allicnce.taobaoAlliance.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.Bind;
import com.Allicnce.taobaoAlliance.R;
import com.Allicnce.taobaoAlliance.activity.GuideActivity;
import com.Allicnce.taobaoAlliance.activity.WebActivity;
import com.Allicnce.taobaoAlliance.animator.interpolator.EvaluatorActivity;
import com.Allicnce.taobaoAlliance.animator.interpolator.InterpolatorActivity;
import com.Allicnce.taobaoAlliance.common.base.BaseFragment;
import com.Allicnce.taobaoAlliance.common.rcAdapter.BaseQuickAdapter;
import com.Allicnce.taobaoAlliance.common.rcAdapter.BaseViewHolder;
import com.Allicnce.taobaoAlliance.picPicker.ImagePickerDemoActivity;

import java.util.Arrays;
import java.util.List;


/**
 * @author: ChenYang
 * @date: 2017-10-24 17:55
 * @Email 1016224774@qq.com
 * @Description 例子
 */
public class OptionsFragment extends BaseFragment {

    @Bind(R.id.recyclerview)
    RecyclerView recyclerview;

    @Override
    public int getLayoutId() {
        return R.layout.fragment_suqare;
    }

    @Override
    public void initView() {
        String[] titles = new String[]{"Interpolator", "Evaluator", "Rxpermission", "刷新", "二维码", "progressbar",
                "选择图片", "persion", "okgo", "recycleview刷新", "guide", "recycler", "webView", "iosLoading", "共享元素动画", "path动画", "6.0权限申请", "StickyRecyclerHeaders"};

        List<String> vos = Arrays.asList(titles);
        BaseQuickAdapter adapter = new BaseQuickAdapter<String, BaseViewHolder>(R.layout.gridview_item) {

            @Override
            protected void convert(BaseViewHolder helper, String item) {
                TextView textView = helper.getView(R.id.title);
                textView.setText(item);
                textView.setOnClickListener(v -> onclickView(helper.getLayoutPosition()));
            }
        };
        adapter.setNewData(vos);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getActivity(), 3);
        recyclerview.setLayoutManager(gridLayoutManager);
        recyclerview.setAdapter(adapter);
    }

    @Override
    public void onChildClick(View v) {

    }

    @Override
    public void setListener() {

    }

    @Override
    public void onMessage(Bundle bundle) {

    }

    public void onclickView(int position) {
        switch (position) {
            case 0:
                startActivity(new Intent(getContext(), InterpolatorActivity.class));
                break;
            case 1:
                startActivity(new Intent(getContext(), EvaluatorActivity.class));
                break;
            case 2:
                break;
            case 3:
                break;
            case 4:
                break;
            case 5:
                break;
            case 6:
                startActivity(new Intent(getContext(), ImagePickerDemoActivity.class));
                break;
            case 7:
                break;
            case 8:
                startActivity(new Intent(getContext(), WebActivity.class));
                break;
            case 9:
                break;
            case 10:
                startActivity(new Intent(getContext(), GuideActivity.class));
                break;


        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }
}
