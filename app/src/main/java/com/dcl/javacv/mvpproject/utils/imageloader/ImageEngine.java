package com.dcl.javacv.mvpproject.utils.imageloader;

import android.graphics.drawable.Drawable;
import android.widget.ImageView;


import io.reactivex.Observable;


/**
 * Created by Administrator on 2018/3/19.
 */

public interface ImageEngine {
    void clearMemoryCache();

    void clearDiskCache();

    void pauseRequest();

    void resumeRequest();

    void display(String path, ImageView imageView, ImageConfiguration configuration);

    Observable<Drawable> loadImage(String path, ImageView imageView, ImageConfiguration configuration);
}
