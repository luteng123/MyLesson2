package com.luteng.lesson2.application;

import android.app.Application;
import android.graphics.Bitmap;
import com.squareup.picasso.LruCache;
import com.squareup.picasso.OkHttpDownloader;
import com.squareup.picasso.Picasso;

/**
 * Created by John on 2015/12/29.
 */
public class CustomApplication extends Application{
    @Override
    public void onCreate() {
        super.onCreate();
        //10M内存村存图片
        Picasso.Builder downloader = new Picasso.Builder(this).memoryCache(new LruCache(10 << 20))
                .downloader(new OkHttpDownloader(getCacheDir(), 30 << 20));
        Picasso picasso = downloader
                .defaultBitmapConfig(Bitmap.Config.RGB_565)
                .build();
        Picasso.setSingletonInstance(picasso);//下次就是这个设置好的picasso了；
        //清楚缓存按钮,指定才能做删除
    }
}
