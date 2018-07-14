package com.genius.sch.loldata.Utils;

import android.widget.ImageView;

import com.genius.sch.loldata.R;

import org.xutils.image.ImageOptions;
import org.xutils.x;

public class ImageUtils {
    static ImageOptions options = new ImageOptions.Builder()
//设置加载过程中的图片
            .setLoadingDrawableId(R.mipmap.ic_launcher)
//设置加载失败后的图片
            .setFailureDrawableId(R.mipmap.ic_launcher)
//设置使用缓存
            .setUseMemCache(true)
//设置显示圆形图片
            .setCircular(true)
//设置支持gif
            .setIgnoreGif(false)    //以及其他方法
            .build();

    public static void bindImageByUrl(ImageView iv, String url) {
        x.image().bind(iv, url, options);
    }

}
