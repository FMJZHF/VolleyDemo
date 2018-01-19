package com.example.volleydemo.Image;

import android.graphics.Bitmap;
import android.util.LruCache;

import com.android.volley.toolbox.ImageLoader;

/**
 * Created by 87501 on 2018/1/19.
 * 图片缓存
 */

public class BitmapCashe implements ImageLoader.ImageCache {

    private LruCache<String, Bitmap> cache;
    private int max = 10 * 1024 * 1024; // 最大缓存值 10M， 如果超过10M，自动回收

    public BitmapCashe() {
        cache = new LruCache<String, Bitmap>(max) {
            @Override
            protected int sizeOf(String key, Bitmap value) {
                return value.getRowBytes() * value.getHeight();
            }
        };
    }

    @Override
    public Bitmap getBitmap(String s) {
        return cache.get(s);
    }

    @Override
    public void putBitmap(String s, Bitmap bitmap) {
        cache.put(s, bitmap);
    }
}
