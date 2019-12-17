package com.jia.googleadm;

import android.annotation.SuppressLint;
import android.content.Context;
import android.net.Uri;
import android.widget.ImageView;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.jiage.glide.listener.ProgressListener;
import com.jiage.glide.util.ProgressInterceptor;


import java.io.File;

/**
 * 作者：李忻佳
 * 日期：2019/6/9/009.
 * 说明：Glide加载图片
 */

public class GlideUtil {
    public static void setImageView(Context context, String url, ImageView imageView){
        Glide.with(context).load(url).apply(getOptions()).into(imageView);
    }
    public static void setImageView(Context context, String url, ImageView imageView, ProgressListener listener){
        Glide.with(context).load(url).apply(getOptions()).into(imageView);
        listener(url, listener);
    }
    public static void setImageView(Context context, File file, ImageView imageView){
        Glide.with(context).load(file).apply(getOptions()).into(imageView);
    }
    public static void setImageView(Context context, int resource, ImageView imageView){
        Glide.with(context).load(resource).apply(getOptions()).into(imageView);
    }
    public static void setImageView(Context context, byte[] image, ImageView imageView){
        Glide.with(context).load(image).apply(getOptions()).into(imageView);
    }
    public static void setImageView(Context context, Uri imageUri, ImageView imageView){
        Glide.with(context).load(imageUri).apply(getOptions()).into(imageView);
    }

    private static void listener(String url,ProgressListener listener){
        ProgressInterceptor.addListener(url, listener);
    }
    @SuppressLint("CheckResult")
    private static RequestOptions getOptions(){
        RequestOptions options = new RequestOptions();
        options.diskCacheStrategy(DiskCacheStrategy.ALL);
        return options;
    }
}
