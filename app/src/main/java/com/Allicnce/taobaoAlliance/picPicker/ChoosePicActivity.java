package com.Allicnce.taobaoAlliance.picPicker;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.Bind;
import com.Allicnce.taobaoAlliance.AppConstant;
import com.Allicnce.taobaoAlliance.R;
import com.Allicnce.taobaoAlliance.activity.ImagePreviewActivity2;
import com.Allicnce.taobaoAlliance.common.base.BaseActivity;
import com.Allicnce.taobaoAlliance.common.rcAdapter.BaseQuickAdapter;
import com.Allicnce.taobaoAlliance.common.rcAdapter.BaseViewHolder;
import com.Allicnce.taobaoAlliance.model.ChangeOrderModel;
import com.Allicnce.taobaoAlliance.okgohttp.CustomRequest;
import com.Allicnce.taobaoAlliance.okgohttp.ModelCallBack;
import com.Allicnce.taobaoAlliance.utils.Object2SdCardUtils;
import com.lzy.imagepicker.ImagePicker;
import com.lzy.imagepicker.bean.ImageItem;
import com.lzy.imagepicker.ui.ImageGridActivity;
import com.lzy.imagepicker.ui.ImagePreviewDelActivity;
import com.lzy.imagepicker.view.CropImageView;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.Progress;
import com.lzy.okgo.model.Response;
import com.lzy.okgo.request.base.Request;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


/**
 * @author: ChenYang
 * @date: 2017-10-24 18:02
 * @Email 1016224774@qq.com
 * @Description
 */
public class ChoosePicActivity extends BaseActivity implements ImagePickerAdapter.OnRecyclerViewItemClickListener {

    public static final int IMAGE_ITEM_ADD = -1;
    public static final int REQUEST_CODE_SELECT = 100;
    public static final int REQUEST_CODE_PREVIEW = 101;
    @Bind(R.id.et_code)
    EditText etCode;
    @Bind(R.id.recyclerView)
    RecyclerView recyclerView;
    @Bind(R.id.rv_preview_list)
    RecyclerView rv_preview_list;
    @Bind(R.id.bt_commit)
    Button btCommit;
    @Bind(R.id.tv_shenhe)
    TextView tvShenhe;
    @Bind(R.id.tv_lingjiang)
    TextView tvLingjiang;

    private ImagePickerAdapter adapter;
    private ArrayList<ImageItem> selImageList; //当前选择的所有图片
    private int maxImgCount = 8;               //允许选择图片最大数

    @Override
    public int getLayoutId() {
        if (getIntent().hasExtra("state")) {
            showToolBar("修改资料", "", true);
        } else {
            showToolBar("提交资料", "", true);
        }
        return R.layout.activity_photo_picker;
    }

    @Override
    public void initView() {
        if (getIntent().hasExtra("state")) {
            doRequest();
            tvShenhe.setEnabled(false);
            tvShenhe.setTextColor(getResources().getColor(R.color.white));
        } else {

        }

        initImagePicker();
        initWidget();
    }

    @Override
    public void onChildClick(View v) {
        switch (v.getId()) {
            case R.id.bt_commit:
                if (TextUtils.isEmpty(etCode.getText())) {
                    Toast.makeText(ChoosePicActivity.this, "请填写游戏账号", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (selImageList == null || selImageList.size() < 3) {
                    Toast.makeText(ChoosePicActivity.this, "请上传3张以上图片", Toast.LENGTH_SHORT).show();
                    return;
                }

                formUpload();
                break;

        }
    }

    @Override
    public void setListener() {
        btCommit.setOnClickListener(this);
    }

    @Override
    public void onMessage(Bundle bundle) {
        super.onMessage(bundle);
        if (bundle.getInt("removePic", -1) != -1) {
            selImageList.remove(bundle.getInt("removePic", 0));
            adapter.setImages(selImageList);
        }
    }

    private void initImagePicker() {
        ImagePicker imagePicker = ImagePicker.getInstance();
        imagePicker.setStyle(CropImageView.Style.RECTANGLE);    //设置矩形裁剪
        imagePicker.setMultiMode(true);                         //设置单选多选
        imagePicker.setImageLoader(GlideImageLoader.instance);   //设置图片加载器
        imagePicker.setShowCamera(true);                      //显示拍照按钮
        imagePicker.setCrop(true);                           //允许裁剪（单选才有效）
        imagePicker.setSaveRectangle(true);                   //是否按矩形区域保存
        imagePicker.setSelectLimit(maxImgCount);              //选中数量限制
        imagePicker.setStyle(CropImageView.Style.RECTANGLE);  //裁剪框的形状
        imagePicker.setFocusWidth(800);                       //裁剪框的宽度。单位像素（圆形自动取宽高最小值）
        imagePicker.setFocusHeight(800);                      //裁剪框的高度。单位像素（圆形自动取宽高最小值）
        imagePicker.setOutPutX(1000);                         //保存文件的宽度。单位像素
        imagePicker.setOutPutY(1000);                         //保存文件的高度。单位像素
    }

    private void initWidget() {
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        selImageList = new ArrayList<>();
        adapter = new ImagePickerAdapter(this, selImageList, maxImgCount);
        adapter.setOnItemClickListener(this);

        recyclerView.setLayoutManager(new GridLayoutManager(this, 3));
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(adapter);
    }

    private SelectDialog showDialog(SelectDialog.SelectDialogListener listener, List<String> names) {
        SelectDialog dialog = new SelectDialog(this, R.style.transparentFrameWindowStyle, listener, names);
        if (!this.isFinishing()) {
            dialog.show();
        }
        return dialog;
    }

    @Override
    public void onItemClick(View view, int position) {
        switch (position) {
            case IMAGE_ITEM_ADD:
                //打开选择,本次允许选择的数量
                ImagePicker.getInstance().setSelectLimit(maxImgCount - selImageList.size());
                Intent intent1 = new Intent(ChoosePicActivity.this, ImageGridActivity.class);
                /* 如果需要进入选择的时候显示已经选中的图片，
                 * 详情请查看ImagePickerActivity
                 * */
//                                intent1.putExtra(ImageGridActivity.EXTRAS_IMAGES,images);
                startActivityForResult(intent1, REQUEST_CODE_SELECT);
               /* List<String> names = new ArrayList<>();
                names.add("拍照");
                names.add("相册");
                showDialog((parent, view1, position1, id) -> {
                    switch (position1) {
                        case 0: // 直接调起相机
                            *//**
             * 0.4.7 目前直接调起相机不支持裁剪，如果开启裁剪后不会返回图片，请注意，后续版本会解决
             *
             * 但是当前直接依赖的版本已经解决，考虑到版本改动很少，所以这次没有上传到远程仓库
             *
             * 如果实在有所需要，请直接下载源码引用。
             *//*
                            //打开选择,本次允许选择的数量
                            ImagePicker.getInstance().setSelectLimit(maxImgCount - selImageList.size());
                            Intent intent = new Intent(ChoosePicActivity.this, ImageGridActivity.class);
                            intent.putExtra(ImageGridActivity.EXTRAS_TAKE_PICKERS, true); // 是否是直接打开相机
                            startActivityForResult(intent, REQUEST_CODE_SELECT);
                            break;
                        case 1:
                            //打开选择,本次允许选择的数量
                            ImagePicker.getInstance().setSelectLimit(maxImgCount - selImageList.size());
                            Intent intent1 = new Intent(ChoosePicActivity.this, ImageGridActivity.class);
                            *//* 如果需要进入选择的时候显示已经选中的图片，
             * 详情请查看ImagePickerActivity
             * *//*
//                                intent1.putExtra(ImageGridActivity.EXTRAS_IMAGES,images);
                            startActivityForResult(intent1, REQUEST_CODE_SELECT);
                            break;
                        default:
                            break;
                    }

                }, names);*/

                break;
            default:
                //打开预览
                Intent intentPreview = new Intent(this, ImagePreviewDelActivity.class);
                intentPreview.putExtra(ImagePicker.EXTRA_IMAGE_ITEMS, (ArrayList<ImageItem>) adapter.getImages());
                intentPreview.putExtra(ImagePicker.EXTRA_SELECTED_IMAGE_POSITION, position);
                intentPreview.putExtra(ImagePicker.EXTRA_FROM_ITEMS, true);
                startActivityForResult(intentPreview, REQUEST_CODE_PREVIEW);
                break;
        }
    }

    ArrayList<ImageItem> images = null;

    public void formUpload() {
        ArrayList<File> files = new ArrayList<>();
        if (selImageList != null && selImageList.size() > 0) {
            for (int i = 0; i < selImageList.size(); i++) {
                files.add(new File(selImageList.get(i).path));
            }
        }
        HashMap<String, String> params = new HashMap<>();
        params.put("os", "Android");
        params.put("devInfo", AppConstant.APPID);
        params.put("session", Object2SdCardUtils.getObjectFormSdCard().session);
        params.put("userId", Object2SdCardUtils.getObjectFormSdCard().userId);
        params.put("orderId", getIntent().getStringExtra("orderid"));//
        params.put("gameAccount", etCode.getText() + "");//
        Log.e("--^_^-->", params.toString());
        //拼接参数
        OkGo.post(AppConstant.UPDATEPIC_URL)//
                .tag(this)//
                .params(params)
                .addFileParams("file", files)           // 这种方式为同一个key，上传多个文件
                .execute(new ModelCallBack<Object>() {
                    @Override
                    public void onStart(Request<Object, ? extends Request> request) {
                        super.onStart(request);
                        showLoading();
                    }

                    @Override
                    public void onSuccess(Response<Object> response) {
                        super.onSuccess(response);
                        Log.e("--^_^-->", "onSuccess");
                        hideLoading();
                        showShortToast("提交成功");
                    }

                    @Override
                    public void uploadProgress(Progress progress) {
                        super.uploadProgress(progress);
                        Log.e("--^_^-->", "uploadProgress");
                    }

                    @Override
                    public void onError(Response<Object> response) {
                        super.onError(response);
                        hideLoading();
                    }

                    @Override
                    public void onFinish() {
                        super.onFinish();
                        Log.e("--^_^-->", "onFinish");
                        finish();
                    }
                });
    }

    public void doRequest() {
        HashMap<String, String> params = new HashMap<>();
        params.put("os", "Android");
        params.put("devInfo", AppConstant.APPID);
        params.put("session", Object2SdCardUtils.getObjectFormSdCard().session);
        params.put("userId", Object2SdCardUtils.getObjectFormSdCard().userId);
        params.put("orderId", getIntent().getStringExtra("orderid"));
        CustomRequest.PostNoSecret(params, AppConstant.GETORDER_URL, new ModelCallBack<ChangeOrderModel>() {
            @Override
            public void onStart(Request<ChangeOrderModel, ? extends Request> request) {
                super.onStart(request);

            }

            @Override
            public void onSuccess(Response<ChangeOrderModel> response) {
                super.onSuccess(response);
                if (response.body().code == 0) {
                    if (etCode != null) {
                        etCode.setText(response.body().data.gameAccount);
                    }
                    if (response.body().data.gameImgList != null) {
                        rv_preview_list.setVisibility(View.VISIBLE);
                        rv_preview_list.setLayoutManager(new GridLayoutManager(ChoosePicActivity.this, 3));
                        rv_preview_list.setHasFixedSize(true);
                        rv_preview_list.setAdapter(new BaseQuickAdapter<String, BaseViewHolder>(R.layout.item_image, response.body().data.gameImgList) {

                            @Override
                            protected void convert(BaseViewHolder helper, String item) {
                                GlideImageLoader.displayImage(ChoosePicActivity.this, item, helper.getView(R.id.iv_img));
                                helper.getView(R.id.iv_img).setOnClickListener(v -> {
                                    Intent intent = new Intent(ChoosePicActivity.this, ImagePreviewActivity2.class);
                                    intent.putExtra("position", helper.getLayoutPosition());
                                    intent.putExtra("picPaths", response.body().data.gameImgList);
                                    startActivity(intent);
                                    overridePendingTransition(R.anim.act_fade_in_center, R.anim.fixed);
                                });
                            }

                        });
                    }
                }
            }

            @Override
            public void onError(Response<ChangeOrderModel> response) {
                super.onError(response);
            }
        });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == ImagePicker.RESULT_CODE_ITEMS) {
            //添加图片返回
            if (data != null && requestCode == REQUEST_CODE_SELECT) {
                images = (ArrayList<ImageItem>) data.getSerializableExtra(ImagePicker.EXTRA_RESULT_ITEMS);
                if (images != null) {
                    selImageList.addAll(images);
                    adapter.setImages(selImageList);
                }
            }
        } else if (resultCode == ImagePicker.RESULT_CODE_BACK) {
            //预览图片返回
            if (data != null && requestCode == REQUEST_CODE_PREVIEW) {
                images = (ArrayList<ImageItem>) data.getSerializableExtra(ImagePicker.EXTRA_IMAGE_ITEMS);
                if (images != null) {
                    selImageList.clear();
                    selImageList.addAll(images);
                    adapter.setImages(selImageList);
                }
            }
        }
    }
}
