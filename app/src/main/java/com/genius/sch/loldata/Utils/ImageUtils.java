package com.genius.sch.loldata.Utils;

import android.widget.ImageView;

import com.genius.sch.loldata.R;

import org.xutils.image.ImageOptions;
import org.xutils.x;

public class ImageUtils {
    /**
     * 原画
     * @param iv
     * @param url
     */
    public static void bindChampionImage(ImageView iv, String url) {
        ImageOptions options = new ImageOptions.Builder()
//设置加载过程中的图片
                .setLoadingDrawableId(R.mipmap.app_logo)
//设置加载失败后的图片
                .setFailureDrawableId(R.mipmap.app_logo)
//设置使用缓存
                .setUseMemCache(true)
                .setSize(1080, 607)
                .build();
        x.image().bind(iv, url, options);
    }

    /**
     * 图标
     * @param iv
     * @param url
     */
    public static void bindChampionImageIcon(ImageView iv, String url) {
        ImageOptions options = new ImageOptions.Builder()
//设置加载过程中的图片
                .setLoadingDrawableId(R.mipmap.app_logo)
//设置加载失败后的图片
                .setFailureDrawableId(R.mipmap.app_logo)
//设置使用缓存
                .setUseMemCache(true)
                .setSize(192, 108)
                .build();
        x.image().bind(iv, url, options);
    }
    /**
     * 地区
     * @param iv
     * @param url
     */
    public static void bindFactionImage(ImageView iv, String url) {
        ImageOptions options = new ImageOptions.Builder()
//设置加载过程中的图片
                .setLoadingDrawableId(R.mipmap.app_logo)
//设置加载失败后的图片
                .setFailureDrawableId(R.mipmap.app_logo)
//设置使用缓存
                .setUseMemCache(true)
                .setSize(1080, 416)
                .build();
        x.image().bind(iv, url, options);
    }

}
