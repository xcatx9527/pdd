package com.Allicnce.taobaoAlliance.animator.interpolator;

import android.animation.TypeEvaluator;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.Allicnce.taobaoAlliance.R;

import java.util.ArrayList;
import java.util.List;


/**
 * @author: ChenYang
 * @date: 2017-10-24 17:50
 * @Email 1016224774@qq.com
 * @Description TypeEvaluator
 */
public class EvaluatorAdapter extends BaseAdapter {

    private LayoutInflater mInflater;

    private List<String> mNames;
    private EasingFunction[] mInterpolators;

    public EvaluatorAdapter(Context context) {
        mInflater = LayoutInflater.from(context);

        mInterpolators = EasingFunction.values();
        mNames = new ArrayList<String>();
        for (EasingFunction function : EasingFunction.values()) {
            mNames.add(function.name());
        }
    }

    @Override
    public View getView(int position, View convertView, ViewGroup viewGroup) {
        ViewHolder mHolder = null;
        if (convertView == null) {
            mHolder = new ViewHolder();
            convertView = mInflater.inflate(R.layout.item_animator, null);
            mHolder.textView = (TextView) convertView.findViewById(R.id.list_item_text);
            convertView.setTag(mHolder);
        } else {
            mHolder = (ViewHolder) convertView.getTag();
        }

        TypeEvaluator evaluator = mInterpolators[position];
        mHolder.textView.setText(String.format("%s Evaluator", mNames.get(position).replace("_", " ")));
        //convertView.setTag(o);//
        return convertView;
    }

    private class ViewHolder {
        public TextView textView;
    }

    @Override
    public int getCount() {
        return mInterpolators == null ? 0 : mInterpolators.length;
    }

    @Override
    public Object getItem(int position) {
        return mInterpolators == null ? null : mInterpolators[position];
    }

    @Override
    public long getItemId(int position) {
        return position;
    }
}
