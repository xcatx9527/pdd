package com.Allicnce.taobaoAlliance.activity;

import android.content.Intent;
import android.view.View;
import android.widget.LinearLayout;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.Bind;
import com.Allicnce.taobaoAlliance.R;
import com.Allicnce.taobaoAlliance.common.base.BaseActivity;
import com.Allicnce.taobaoAlliance.common.rcAdapter.BaseQuickAdapter;
import com.Allicnce.taobaoAlliance.common.rcAdapter.BaseViewHolder;
import com.Allicnce.taobaoAlliance.picPicker.GlideImageLoader;

import java.util.ArrayList;

public class HelpActivity extends BaseActivity {


    @Bind(R.id.rv_list)
    RecyclerView rvList;
    @Bind(R.id.container)
    LinearLayout container;

    @Override
    public int getLayoutId() {
        showToolBar("任务帮助", "", true);
        return R.layout.activity_help;
    }


    @Override
    public void initView() {
        ArrayList<String> paths = (ArrayList<String>) getIntent().getSerializableExtra("pics");
        rvList.setLayoutManager(new GridLayoutManager(this, 3));
        rvList.setHasFixedSize(true);
        rvList.setAdapter(new BaseQuickAdapter<String, BaseViewHolder>(R.layout.item_image, paths) {

            @Override
            protected void convert(BaseViewHolder helper, String item) {
                GlideImageLoader.displayImage(HelpActivity.this, item, helper.getView(R.id.iv_img));
                helper.getView(R.id.iv_img).setOnClickListener(v -> {
                    Intent intent = new Intent(HelpActivity.this, ImagePreviewActivity2.class);
                    intent.putExtra("position", helper.getLayoutPosition());
                    intent.putExtra("picPaths", paths);
                    startActivity(intent);
                    overridePendingTransition(R.anim.act_fade_in_center, R.anim.fixed);
                });
            }

        });
    }

    @Override
    public void onChildClick(View v) {

    }

    @Override
    public void setListener() {

    }


}
