package com.Allicnce.taobaoAlliance.fragment;


import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;
import butterknife.Bind;
import com.Allicnce.taobaoAlliance.R;
import com.Allicnce.taobaoAlliance.common.rcAdapter.BaseQuickAdapter;
import com.Allicnce.taobaoAlliance.common.rcAdapter.BaseViewHolder;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class ChoosePriceDialogFragment extends BaseDialogFragment {


    @Bind(R.id.iv_close)
    ImageView ivClose;
    @Bind(R.id.rv_list)
    RecyclerView rvList;
    ArrayList<Integer> datas;


    @Override
    protected int getDialogResource() {
        return R.layout.fragment_choose_price_dialog;
    }


    @Override
    protected void initView() {
        datas = (ArrayList<Integer>) getArguments().getSerializable("amountList");
        rvList.setLayoutManager(new StaggeredGridLayoutManager(4, StaggeredGridLayoutManager.VERTICAL));
        rvList.setAdapter(new BaseQuickAdapter<Integer, BaseViewHolder>(R.layout.item_text, datas) {

            @Override
            protected void convert(BaseViewHolder helper, Integer item) {
                TextView textView = helper.getView(R.id.tv_text);
                textView.setText(item + "");
                textView.setOnClickListener(v -> {
                    Bundle bundle = new Bundle();
                    bundle.putInt(ChoosePriceDialogFragment.class.getName() + "price", item);
                    sendMessage(bundle);
                    dismiss();
                });
            }
        });
    }

    @Override
    public void onChildClick(View v) {
        switch (v.getId()) {
            case R.id.iv_close:
                dismiss();
                break;
        }
    }

    @Override
    public void setListener() {
        ivClose.setOnClickListener(this);
    }
}
