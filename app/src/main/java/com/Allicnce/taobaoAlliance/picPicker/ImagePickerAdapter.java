package com.Allicnce.taobaoAlliance.picPicker;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import androidx.recyclerview.widget.RecyclerView;
import com.Allicnce.taobaoAlliance.R;
import com.lzy.imagepicker.ImagePicker;
import com.lzy.imagepicker.bean.ImageItem;
import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;

/**
 * @author: ChenYang
 * @date: 2017-10-24 18:02
 * @Email 1016224774@qq.com
 * @Description
 */
public class ImagePickerAdapter extends RecyclerView.Adapter<ImagePickerAdapter.SelectedPicViewHolder> {
    private int maxImgCount;
    private Context mContext;
    private List<ImageItem> mData;
    private LayoutInflater mInflater;
    private OnRecyclerViewItemClickListener listener;
    private boolean isAdded;   //是否额外添加了最后一个图片

    public interface OnRecyclerViewItemClickListener {
        void onItemClick(View view, int position);
    }

    public void setOnItemClickListener(OnRecyclerViewItemClickListener listener) {
        this.listener = listener;
    }

    public void setImages(List<ImageItem> data) {
        mData = new ArrayList<>(data);
        if (getItemCount() < maxImgCount) {
            mData.add(new ImageItem());
            isAdded = true;
        } else {
            isAdded = false;
        }
        notifyDataSetChanged();
    }

    public List<ImageItem> getImages() {
        //由于图片未选满时，最后一张显示添加图片，因此这个方法返回真正的已选图片
        if (isAdded) {
            return new ArrayList<>(mData.subList(0, mData.size() - 1));
        } else {
            return mData;
        }
    }

    public ImagePickerAdapter(Context mContext, List<ImageItem> data, int maxImgCount) {
        this.mContext = mContext;
        this.maxImgCount = maxImgCount;
        this.mInflater = LayoutInflater.from(mContext);
        setImages(data);
    }

    @Override
    public SelectedPicViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new SelectedPicViewHolder(mInflater.inflate(R.layout.picker_list_item_image, parent, false));
    }

    @Override
    public void onBindViewHolder(SelectedPicViewHolder holder, int position) {
        holder.bind(position);

    }


    @Override
    public int getItemCount() {
        return mData.size();
    }

    public class SelectedPicViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private ImageView iv_delate;
        private ImageView iv_img;
        private int clickPosition;

        public SelectedPicViewHolder(View itemView) {
            super(itemView);
            iv_img = (ImageView) itemView.findViewById(R.id.iv_img);
            iv_delate = (ImageView) itemView.findViewById(R.id.iv_delate);
        }

        @SuppressLint("ResourceAsColor")
        public void bind(int position) {
            //设置条目的点击事件
            itemView.setOnClickListener(this);
            //根据条目位置设置图片
            ImageItem item = mData.get(position);
            if (isAdded && position == getItemCount() - 1) {
                iv_img.setImageResource(R.drawable.picker_selector_image_add);
                clickPosition = ChoosePicActivity.IMAGE_ITEM_ADD;
                iv_delate.setVisibility(View.GONE);
            } else {
                iv_delate.setOnClickListener(v -> {
                    Bundle bundle = new Bundle();
                    bundle.putInt("removePic", position);
                    EventBus.getDefault().post(bundle);
                });
                iv_delate.setVisibility(View.VISIBLE);
                ImagePicker.getInstance().getImageLoader().displayImage((Activity) mContext, item.path, iv_img, 0, 0);
                clickPosition = position;
            }
        }

        @Override
        public void onClick(View v) {
            if (listener != null) listener.onItemClick(v, clickPosition);
        }
    }
}