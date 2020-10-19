package com.Allicnce.taobaoAlliance.activity;


import android.content.Intent;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;
import butterknife.Bind;
import com.Allicnce.taobaoAlliance.R;
import com.Allicnce.taobaoAlliance.common.base.BaseActivity;
import com.Allicnce.taobaoAlliance.common.rcAdapter.BaseQuickAdapter;
import com.Allicnce.taobaoAlliance.common.rcAdapter.BaseViewHolder;
import com.Allicnce.taobaoAlliance.picPicker.GlideImageLoader;
import com.lzy.imagepicker.ImagePicker;
import com.lzy.imagepicker.bean.ImageItem;
import com.lzy.imagepicker.ui.ImageGridActivity;
import com.lzy.imagepicker.view.CropImageView;

import java.util.ArrayList;


public class AddTestActivity extends BaseActivity {

    public static final int IMAGE_ITEM_ADD = -1;
    public static final int REQUEST_CODE_SELECT = 100;
    public static final int REQUEST_CODE_PREVIEW = 101;
    @Bind(R.id.et_broken_content)
    EditText etBrokenContent;
    @Bind(R.id.rv_choosepic)
    RecyclerView rvChoosepic;
    private BaseQuickAdapter baseQuickAdapter;
    private ImagePicker imagePicker;


    @Override
    public int getLayoutId() {
        showToolBar("添加任务", "完成", true);
        return R.layout.fragment_addtest;
    }

    @Override
    public void initView() {
        initImagePicker();
        images = new ArrayList<>();
        images.add(new ImageItem());
        baseQuickAdapter = new BaseQuickAdapter<ImageItem, BaseViewHolder>(R.layout.item_choosepic, images) {
            @Override
            protected void convert(BaseViewHolder helper, ImageItem item) {
                helper.addOnClickListener(R.id.iv_choosepic);
                ImageView imageView = helper.getView(R.id.iv_choosepic);
                if (helper.getLayoutPosition() < images.size() - 1 || helper.getLayoutPosition() == 6) {
                    GlideImageLoader.displayImage(AddTestActivity.this, images.get(helper.getLayoutPosition()).path, imageView);
                }
            }

        };
        baseQuickAdapter.setOnItemChildClickListener((adapter, view, position) -> {
            if (position == images.size() - 1 && position != 6) {
                ImagePicker.getInstance().setSelectLimit(1);
                Intent intent = new Intent(AddTestActivity.this, ImageGridActivity.class);
                intent.putExtra(ImageGridActivity.EXTRAS_TAKE_PICKERS, true); // 是否是直接打开相机
                startActivityForResult(intent, REQUEST_CODE_SELECT);
                //打开选择,本次允许选择的数量
            } else {
               /* Intent intent = new Intent(AddMissionActivity.this, ImagePreviewActivity2.class);
                intent.putExtra(ImagePicker.EXTRA_SELECTED_IMAGE_POSITION, 0);
                intent.putExtra(ImagePicker.EXTRA_IMAGE_ITEMS, images);
                startActivity(intent);*/
            }

        });
        StaggeredGridLayoutManager layoutManager = new StaggeredGridLayoutManager(6, StaggeredGridLayoutManager.VERTICAL);
        rvChoosepic.setLayoutManager(layoutManager);
        rvChoosepic.setAdapter(baseQuickAdapter);
    }


    @Override
    public void onChildClick(View v) {
        switch (v.getId()) {
            case R.id.ll_container:
                break;

        }
    }

    private void initImagePicker() {
        imagePicker = ImagePicker.getInstance();
        imagePicker.setStyle(CropImageView.Style.RECTANGLE);    //设置矩形裁剪
        imagePicker.setMultiMode(false);                         //设置单选多选
        imagePicker.setImageLoader(GlideImageLoader.instance);   //设置图片加载器
        imagePicker.setShowCamera(true);                      //显示拍照按钮
        imagePicker.setCrop(false);                           //允许裁剪（单选才有效）
    }

    @Override
    public void setListener() {

    }

    ArrayList<ImageItem> images = null;

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == ImagePicker.RESULT_CODE_ITEMS) {
            //添加图片返回
            if (data != null && requestCode == REQUEST_CODE_SELECT) {
                if (images != null) {
                    images.remove(images.size() - 1);
                    images.addAll((ArrayList<ImageItem>) data.getSerializableExtra(ImagePicker.EXTRA_RESULT_ITEMS));
                } else {
                    images = (ArrayList<ImageItem>) data.getSerializableExtra(ImagePicker.EXTRA_RESULT_ITEMS);
                }
                if (images.size() < 6) {

                    images.add(new ImageItem());
                }
                baseQuickAdapter.setNewData(images);
            }
        } else if (resultCode == ImagePicker.RESULT_CODE_BACK) {
            //预览图片返回
            if (data != null && requestCode == REQUEST_CODE_PREVIEW) {
                images = (ArrayList<ImageItem>) data.getSerializableExtra(ImagePicker.EXTRA_IMAGE_ITEMS);
                if (images != null) {

                }
            }
        }

    }
}
