package com.Allicnce.taobaoAlliance.common.view.recyclerviewdecoration;

import android.graphics.Canvas;
import android.graphics.Rect;
import android.util.SparseArray;
import android.view.View;
import android.widget.LinearLayout;
import androidx.collection.LongSparseArray;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class StickyRecyclerHeadersDecoration extends RecyclerView.ItemDecoration {

    private final StickyRecyclerHeadersAdapter mAdapter;
    private final ItemVisibilityAdapter mVisibilityAdapter;
    private final SparseArray<Rect> mHeaderRects = new SparseArray<>();
    private LinearLayoutOrientationUtils mOrientationProvider = new LinearLayoutOrientationUtils();
    private final HeaderPositionCalculator mHeaderPositionCalculator;
    private final LongSparseArray<View> mHeaderViews = new LongSparseArray<>();

    /**
     * The following field is used as a buffer for internal calculations. Its sole purpose is to avoid
     * allocating new Rect every time we need one.
     */
    private final Rect mTempRect = new Rect();

    public StickyRecyclerHeadersDecoration(StickyRecyclerHeadersAdapter adapter) {
        this(adapter, null);
    }


    public StickyRecyclerHeadersDecoration(StickyRecyclerHeadersAdapter adapter, ItemVisibilityAdapter visibilityAdapter) {
        mAdapter = adapter;
        mHeaderPositionCalculator = new HeaderPositionCalculator(adapter);
        mVisibilityAdapter = visibilityAdapter;
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        super.getItemOffsets(outRect, view, parent, state);
        int itemPosition = parent.getChildAdapterPosition(view);
        if (itemPosition == RecyclerView.NO_POSITION) {
            return;
        }
        if (mHeaderPositionCalculator.hasNewHeader(itemPosition, mOrientationProvider.isReverseLayout(parent))) {
            View header = getHeaderView(parent, itemPosition);
            setItemOffsetsForHeader(outRect, header, mOrientationProvider.getOrientation(parent));
        }
    }

    /**
     * Sets the offsets for the first item in a section to make room for the header view
     *
     * @param itemOffsets rectangle to define offsets for the item
     * @param header      view used to calculate offset for the item
     * @param orientation used to calculate offset for the item
     */
    private void setItemOffsetsForHeader(Rect itemOffsets, View header, int orientation) {
        mOrientationProvider.initMargins(mTempRect, header);
        if (orientation == LinearLayoutManager.VERTICAL) {
            itemOffsets.top = header.getHeight() + mTempRect.top + mTempRect.bottom;
        } else {
            itemOffsets.left = header.getWidth() + mTempRect.left + mTempRect.right;
        }
    }

    //画顶部header
    @Override
    public void onDrawOver(Canvas canvas, RecyclerView parent, RecyclerView.State state) {
        super.onDrawOver(canvas, parent, state);

        final int childCount = parent.getChildCount();
        if (childCount <= 0 || mAdapter.getItemCount() <= 0) {
            return;
        }

        for (int i = 0; i < childCount; i++) {
            View itemView = parent.getChildAt(i);
            int position = parent.getChildAdapterPosition(itemView);
            if (position == RecyclerView.NO_POSITION) {
                continue;
            }

            boolean hasStickyHeader = mHeaderPositionCalculator.hasStickyHeader(itemView, mOrientationProvider.getOrientation(parent), position);
            if (hasStickyHeader || mHeaderPositionCalculator.hasNewHeader(position, mOrientationProvider.isReverseLayout(parent))) {
                View header = mHeaderPositionCalculator.getHeader(parent, position);
                //re-use existing Rect, if any.
                Rect headerOffset = mHeaderRects.get(position);
                if (headerOffset == null) {
                    headerOffset = new Rect();
                    mHeaderRects.put(position, headerOffset);
                }
                mHeaderPositionCalculator.initHeaderBounds(headerOffset, parent, header, itemView, hasStickyHeader);
                drawHeader(parent, canvas, header, headerOffset);
            }
        }
    }

    /**
     * Gets the position of the header under the specified (x, y) coordinates.
     *
     * @param x x-coordinate
     * @param y y-coordinate
     * @return position of header, or -1 if not found
     */
    public int findHeaderPositionUnder(int x, int y) {
        for (int i = 0; i < mHeaderRects.size(); i++) {
            Rect rect = mHeaderRects.get(mHeaderRects.keyAt(i));
            if (rect.contains(x, y)) {
                int position = mHeaderRects.keyAt(i);
                if (mVisibilityAdapter == null || mVisibilityAdapter.isPositionVisible(position)) {
                    return position;
                }
            }
        }
        return -1;
    }

    /**
     * Gets the header view for the associated position.  If it doesn't exist yet, it will be
     * created, measured, and laid out.
     *
     * @param parent   the recyclerview
     * @param position the position to get the header view for
     * @return Header view
     */
    public View getHeaderView(RecyclerView parent, int position) {
        return mHeaderPositionCalculator.getHeader(parent, position);
    }

    /**
     * Invalidates cached headers.  This does not invalidate the recyclerview, you should do that manually after
     * calling this method.
     */
    public void invalidateHeaders() {
        mHeaderPositionCalculator.invalidate();
        mHeaderRects.clear();
    }

    public void drawHeader(RecyclerView recyclerView, Canvas canvas, View header, Rect offset) {
        canvas.save();

        if (recyclerView.getLayoutManager().getClipToPadding()) {
            // Clip drawing of headers to the padding of the RecyclerView. Avoids drawing in the padding
            initClipRectForHeader(mTempRect, recyclerView, header);
            canvas.clipRect(mTempRect);
        }

        canvas.translate(offset.left, offset.top);

        header.draw(canvas);
        canvas.restore();
    }


    private void initClipRectForHeader(Rect clipRect, RecyclerView recyclerView, View header) {
        mOrientationProvider.initMargins(clipRect, header);
        if (mOrientationProvider.getOrientation(recyclerView) == LinearLayout.VERTICAL) {
            clipRect.set(
                    recyclerView.getPaddingLeft(),
                    recyclerView.getPaddingTop(),
                    recyclerView.getWidth() - recyclerView.getPaddingRight() - clipRect.right,
                    recyclerView.getHeight() - recyclerView.getPaddingBottom());
        } else {
            clipRect.set(
                    recyclerView.getPaddingLeft(),
                    recyclerView.getPaddingTop(),
                    recyclerView.getWidth() - recyclerView.getPaddingRight(),
                    recyclerView.getHeight() - recyclerView.getPaddingBottom() - clipRect.bottom);
        }
    }

    public interface ItemVisibilityAdapter {
        boolean isPositionVisible(final int position);
    }

}
