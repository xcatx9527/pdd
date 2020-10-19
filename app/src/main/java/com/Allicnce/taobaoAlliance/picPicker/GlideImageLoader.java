package com.Allicnce.taobaoAlliance.picPicker;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import androidx.core.graphics.drawable.RoundedBitmapDrawable;
import androidx.core.graphics.drawable.RoundedBitmapDrawableFactory;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.Priority;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.request.target.BitmapImageViewTarget;
import com.bumptech.glide.request.target.SimpleTarget;
import com.Allicnce.taobaoAlliance.AppApplication;
import com.Allicnce.taobaoAlliance.R;
import com.lzy.imagepicker.loader.ImageLoader;

import io.reactivex.Observable;
import io.reactivex.schedulers.Schedulers;

/**
 * @author: ChenYang
 * @date: 2017-10-24 18:02
 * @Email 1016224774@qq.com
 * @Description
 */
public class GlideImageLoader implements ImageLoader {
    public static GlideImageLoader instance = new GlideImageLoader();

    private GlideImageLoader() {
    }

    @Override
    public void displayImage(Activity activity, String path, ImageView imageView, int width, int height) {

        Glide.with(activity)
                .load(path)
                .error(R.mipmap.default_image)
                .override(width, height)
                .placeholder(R.mipmap.default_image)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(imageView);
    }

    @Override
    public void clearMemoryCache() {

    }

    public static void displayImage(Activity activity, String path, ImageView imageView) {
        Glide.with(activity).load(path)
                .placeholder(R.mipmap.default_image)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .dontAnimate()
                .centerCrop()
                .priority(Priority.NORMAL)
                .into(imageView);
    }

    public static void displayBlurImage(Activity activity, String path, ImageView imageView) {
        Glide.with(activity)
                .load(path)
                .error(R.mipmap.default_image)
                .placeholder(R.mipmap.default_image)
                .transform(new BlurTransformation(activity, 10))
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(imageView);
    }


    /**
     * 设置要加载的内容
     *
     */
    public static void displayTarget(Context mContext, String path, SimpleTarget<GlideDrawable> simpleTarget) {
        Glide.with(mContext).load(path).centerCrop().into(simpleTarget);
    }


    /**
     * 恢复请求，一般在停止滚动的时候
     *
     */
    public void resumeRequests(Context context) {
        Glide.with(context).resumeRequests();
    }

    /**
     * 暂停请求 正在滚动的时候
     *
     */
    public void pauseRequests(Context context) {
        Glide.with(context).pauseRequests();
    }

    /**
     * 清理磁盘缓存
     */
    public static void GuideClearDisk() {
        //理磁盘缓存 需要在子线程中执行
        Observable.empty().observeOn(Schedulers.io()).subscribe(o -> Glide.get(AppApplication.instance).clearDiskCache());
    }

    /**
     * 清理内存缓存
     */
    public static void GuideClearMemory() {
        Glide.get(AppApplication.instance).clearMemory();
    }


    /**
     * 加载圆角图片
     *
     */
    public static void displayRoundImage(final Context context, String url, final int radius, final ImageView imageView) {
        Glide.with(context)
                .load(url)
                .asBitmap()
                .placeholder(R.mipmap.default_image)
                .error(R.mipmap.default_image)
                .diskCacheStrategy(DiskCacheStrategy.ALL) //设置缓存
                .into(new BitmapImageViewTarget(imageView) {
                    @Override
                    protected void setResource(Bitmap resource) {
                        super.setResource(resource);
                        RoundedBitmapDrawable circularBitmapDrawable =
                                RoundedBitmapDrawableFactory.create(context.getResources(), resource);
                        circularBitmapDrawable.setCornerRadius(radius); //设置圆角弧度
                        imageView.setImageDrawable(circularBitmapDrawable);
                    }
                });
    }

    /**
     * 加载圆形图片
     *
     */
    public static void displayCirclePic(final Context context, String url, final ImageView imageView) {
        Glide.with(context)
                .load(url)
                .asBitmap()
                .placeholder(R.mipmap.default_image)
                .error(R.mipmap.default_image)
                .diskCacheStrategy(DiskCacheStrategy.ALL) //设置缓存
                .into(new BitmapImageViewTarget(imageView) {
                    @Override
                    protected void setResource(Bitmap resource) {
                        RoundedBitmapDrawable circularBitmapDrawable =
                                RoundedBitmapDrawableFactory.create(context.getResources(), resource);
                        circularBitmapDrawable.setCircular(true);
                        imageView.setImageDrawable(circularBitmapDrawable);
                    }
                });
    }
}
