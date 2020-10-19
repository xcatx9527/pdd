package com.Allicnce.taobaoAlliance.common.view.recyclerviewdecoration;

import android.graphics.Rect;
import android.view.View;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import static android.view.ViewGroup.LayoutParams;
import static android.view.ViewGroup.MarginLayoutParams;

/**
 * OrientationProvider for ReyclerViews who use a LinearLayoutManager
 */
public class LinearLayoutOrientationUtils {

    public int getOrientation(RecyclerView recyclerView) {
        RecyclerView.LayoutManager layoutManager = recyclerView.getLayoutManager();
        throwIfNotLinearLayoutManager(layoutManager);
        return ((LinearLayoutManager) layoutManager).getOrientation();
    }

    public boolean isReverseLayout(RecyclerView recyclerView) {
        RecyclerView.LayoutManager layoutManager = recyclerView.getLayoutManager();
        throwIfNotLinearLayoutManager(layoutManager);
        return ((LinearLayoutManager) layoutManager).getReverseLayout();
    }

    public void initMargins(Rect margins, View view) {
        LayoutParams layoutParams = view.getLayoutParams();

        if (layoutParams instanceof MarginLayoutParams) {
            MarginLayoutParams marginLayoutParams = (MarginLayoutParams) layoutParams;
            margins.set(
                    marginLayoutParams.leftMargin,
                    marginLayoutParams.topMargin,
                    marginLayoutParams.rightMargin,
                    marginLayoutParams.bottomMargin
            );
        } else {
            margins.set(0, 0, 0, 0);
        }
    }

    private void throwIfNotLinearLayoutManager(RecyclerView.LayoutManager layoutManager) {
        if (!(layoutManager instanceof LinearLayoutManager)) {
            throw new IllegalStateException("StickyListHeadersDecoration can only be used with a " +
                    "LinearLayoutManager.");
        }
    }
}
