package com.Allicnce.taobaoAlliance.common.richpath;

import android.graphics.*;
import android.graphics.drawable.Drawable;
import androidx.annotation.IntRange;
import androidx.annotation.Nullable;
import com.Allicnce.taobaoAlliance.common.richpath.listener.OnRichPathUpdatedListener;
import com.Allicnce.taobaoAlliance.common.richpath.model.Vector;
import com.Allicnce.taobaoAlliance.common.richpath.pathparser.PathParser;


/**
 * Created by tarek on 6/29/17.
 */

class RichPathDrawable extends Drawable {

    private Vector vector;
    private int width;
    private int height;

    public RichPathDrawable(Vector vector) {
        this.vector = vector;
        listenToPathsUpdates();
    }

    @Override
    protected void onBoundsChange(Rect bounds) {
        super.onBoundsChange(bounds);
        if (bounds.width() > 0 && bounds.height() > 0) {
            width = bounds.width();
            height = bounds.height();
            mapPaths();

        }
    }

    void mapPaths() {
        if (vector == null) return;

        float centerX = width / 2;
        float centerY = height / 2;

        Matrix matrix = new Matrix();

        matrix.postTranslate(centerX - vector.getViewportWidth() / 2,
                centerY - vector.getViewportHeight() / 2);

        float widthRatio = width / vector.getViewportWidth();
        float heightRatio = height / vector.getViewportHeight();

        float ratio = Math.min(widthRatio, heightRatio);

        matrix.postScale(ratio, ratio, centerX, centerY);

        for (RichPath path : vector.paths) {
            path.mapToMatrix(matrix);
            path.scaleStrokeWidth(ratio);
        }

    }

    @Nullable
    public RichPath findRichPathByName(String name) {
        if (vector == null) return null;

        for (RichPath path : vector.paths) {
            if (name.equals(path.getName())) {
                return path;
            }
        }
        return null;
    }

    public void listenToPathsUpdates() {

        if (vector == null) return;

        for (RichPath path : vector.paths) {

            path.setOnRichPathUpdatedListener(new OnRichPathUpdatedListener() {
                @Override
                public void onPathUpdated() {
                    invalidateSelf();
                }
            });
        }

    }

    public void addPath(String path) {
        addPath(PathParser.createPathFromPathData(path));
    }

    public void addPath(Path path) {
        if (path instanceof RichPath) {
            addPath((RichPath) path);
        } else {
            addPath(new RichPath(path));
        }
    }

    private void addPath(RichPath path) {

        if (vector == null) return;

        vector.paths.add(path);
        path.setOnRichPathUpdatedListener(new OnRichPathUpdatedListener() {
            @Override
            public void onPathUpdated() {
                invalidateSelf();
            }
        });
        invalidateSelf();
    }

    @Override
    public void draw(Canvas canvas) {

        if (vector == null || vector.paths.size() < 0) return;

        for (RichPath path : vector.paths) {
            path.draw(canvas);
        }
    }

    @Override
    public void setAlpha(@IntRange(from = 0, to = 255) int alpha) {

    }

    @Override
    public void setColorFilter(@Nullable ColorFilter colorFilter) {

    }

    @Override
    public int getOpacity() {
        return PixelFormat.TRANSLUCENT;
    }

}
