package com.Allicnce.taobaoAlliance.widget;

import android.content.Context;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.EditText;

import com.Allicnce.taobaoAlliance.R;

/**
 * @author: ChenYang
 * @date: 2017-10-24 18:04
 * @Email 1016224774@qq.com
 * @Description
 */
public class EditTextWithDel extends EditText {
	private Drawable imgAble;
	private Context mContext;

	public EditTextWithDel(Context context) {
		super(context);
		mContext = context;
		init();
	}

	public EditTextWithDel(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		mContext = context;
		init();
	}

	public EditTextWithDel(Context context, AttributeSet attrs) {
		super(context, attrs);
		mContext = context;
		init();
	}

	private void init() {
		imgAble = mContext.getResources().getDrawable(R.drawable.del_icon);
		addTextChangedListener(new TextWatcher() {
			@Override
			public void onTextChanged(CharSequence s, int start, int before,
                                      int count) {
				if(addSearchTextChangedListener != null){
					addSearchTextChangedListener.onTextChanged(s, start, before, count);
				}
			}

			@Override
			public void beforeTextChanged(CharSequence s, int start, int count,
                                          int after) {
			}

			@Override
			public void afterTextChanged(Editable s) {
				setDrawable();
				if(addSearchTextChangedListener != null){
					addSearchTextChangedListener.afterTextChanged();
				}
			}
		});
	}

	@Override
	protected void onFocusChanged(boolean focused, int direction,
			Rect previouslyFocusedRect) {
		super.onFocusChanged(focused, direction, previouslyFocusedRect);
		if (!focused) {// 如果失去焦点
			setCompoundDrawablePadding(0);
			setCompoundDrawablesWithIntrinsicBounds(null, null, null, null);
		} else if (focused && length() > 0) {
			setCompoundDrawablePadding(5);
			setCompoundDrawablesWithIntrinsicBounds(null, null, imgAble, null);
		}
	}

	// 设置删除图片
	private void setDrawable() {
		if (length() > 0) {
			setCompoundDrawablePadding(5);
			setCompoundDrawablesWithIntrinsicBounds(null, null, imgAble, null);
		} else {
			setCompoundDrawablePadding(0);
			setCompoundDrawablesWithIntrinsicBounds(null, null, null, null);
		}
	}

	// 处理删除事件
	@Override
	public boolean onTouchEvent(MotionEvent event) {
		if (imgAble != null && event.getAction() == MotionEvent.ACTION_UP) {
			int eventX = (int) event.getRawX();
			int eventY = (int) event.getRawY();
			Rect rect = new Rect();
			getGlobalVisibleRect(rect);
			rect.left = rect.right - 60;
			if (rect.contains(eventX, eventY))
				setText("");
		}
		return super.onTouchEvent(event);
	}

	@Override
	protected void finalize() throws Throwable {
		super.finalize();
	}
	
	private AddSearchTextChangedListener addSearchTextChangedListener;
	
	public void setAddSearchTextChangedListener(
			AddSearchTextChangedListener addSearchTextChangedListener) {
		this.addSearchTextChangedListener = addSearchTextChangedListener;
	}

	public interface AddSearchTextChangedListener{
		void afterTextChanged();
		void onTextChanged(CharSequence s, int start, int before,
                           int count);
	}
}
