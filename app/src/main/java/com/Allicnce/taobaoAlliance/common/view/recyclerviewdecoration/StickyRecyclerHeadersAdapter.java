package com.Allicnce.taobaoAlliance.common.view.recyclerviewdecoration;

import android.graphics.Color;
import androidx.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import com.Allicnce.taobaoAlliance.R;
import com.Allicnce.taobaoAlliance.common.rcAdapter.BaseQuickAdapter;
import com.Allicnce.taobaoAlliance.common.rcAdapter.BaseViewHolder;

import java.security.SecureRandom;
import java.util.List;

public class StickyRecyclerHeadersAdapter extends BaseQuickAdapter<String, BaseViewHolder> {
    public StickyRecyclerHeadersAdapter(int layoutResId, @Nullable List<String> data) {
        super(layoutResId, data);
    }


    public long getHeaderId(int position) {
        if (position == 0) {
            return -1;
        } else {
            return getItem(position).charAt(0);
        }
    }

    public RecyclerView.ViewHolder onCreateHeaderViewHolder(ViewGroup parent) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.stickyrecycler_header, parent, false);
        return new RecyclerView.ViewHolder(view) {
        };
    }

    public void onBindHeaderViewHolder(RecyclerView.ViewHolder holder, int position) {
        TextView textView = (TextView) holder.itemView;
        textView.setText(String.valueOf(getItem(position).charAt(0)));
        holder.itemView.setBackgroundColor(getRandomColor());
    }

    private int getRandomColor() {
        SecureRandom rgen = new SecureRandom();
        return Color.HSVToColor(150, new float[]{
                rgen.nextInt(359), 1, 1
        });
    }

    @Override
    protected void convert(BaseViewHolder helper, String item) {
        helper.setText(R.id.text, item);
    }
}
