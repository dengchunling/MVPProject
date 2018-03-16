package com.dcl.javacv.mvpproject.di.module;

import android.content.Context;

import com.bumptech.glide.Glide;
import com.bumptech.glide.GlideBuilder;
import com.bumptech.glide.Registry;
import com.bumptech.glide.annotation.GlideModule;
import com.bumptech.glide.load.DecodeFormat;
import com.bumptech.glide.load.engine.cache.ExternalPreferredCacheDiskCacheFactory;
import com.bumptech.glide.load.model.GlideUrl;
import com.bumptech.glide.module.AppGlideModule;
import com.dcl.javacv.mvpproject.utils.glide.OkHttpGlideUrlLoader;

import java.io.InputStream;

/**
 * Created by Administrator on 2018/3/16.
 */

@GlideModule
public class MyGlideModule extends AppGlideModule {

    public static final int DISK_CACHE_SIZE = 500 * 1024 * 1024;

    /**
     * 更改Glide和配置
     *
     * @param context
     * @param builder
     */
    @Override
    public void applyOptions(Context context, GlideBuilder builder) {
        /**
         * ExternalPreferredCacheDiskCacheFactory的默认缓存路径是在sdcard/Android/包名/cache/image_manager_disk_cache
         */
        builder.setDiskCache(new ExternalPreferredCacheDiskCacheFactory(context, DISK_CACHE_SIZE));
        //设置图片加载的格式
        builder.setDecodeFormat(DecodeFormat.PREFER_RGB_565);
    }

    /**
     * 替换Glide组件
     *
     * @param context
     * @param glide
     * @param registry
     */
    @Override
    public void registerComponents(Context context, Glide glide, Registry registry) {
        registry.append(GlideUrl.class, InputStream.class, new OkHttpGlideUrlLoader.Factory());
//        super.registerComponents(context, glide, registry);
    }

    @Override
    public boolean isManifestParsingEnabled() {
        return false;
    }
}
