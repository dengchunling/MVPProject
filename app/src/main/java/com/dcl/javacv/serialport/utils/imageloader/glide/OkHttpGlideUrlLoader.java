package com.dcl.javacv.serialport.utils.imageloader.glide;

import android.support.annotation.Nullable;

import com.bumptech.glide.load.Options;
import com.bumptech.glide.load.model.GlideUrl;
import com.bumptech.glide.load.model.ModelLoader;
import com.bumptech.glide.load.model.ModelLoaderFactory;
import com.bumptech.glide.load.model.MultiModelLoaderFactory;

import java.io.InputStream;

import okhttp3.OkHttpClient;

/**
 * Created by Administrator on 2018/3/16.
 */

public class OkHttpGlideUrlLoader implements ModelLoader<GlideUrl, InputStream> {

    private final OkHttpClient okHttpClient;

    public static class Factory implements ModelLoaderFactory<GlideUrl, InputStream> {

        private OkHttpClient client;

        public Factory() {
        }

        public Factory(OkHttpClient client) {
            this.client = client;
        }

        private synchronized OkHttpClient getOkHttpClient() {
            if (client == null) {
                client = new OkHttpClient();
            }
            return client;
        }

        @Override
        public ModelLoader<GlideUrl, InputStream> build(MultiModelLoaderFactory multiFactory) {
            return new OkHttpGlideUrlLoader(getOkHttpClient());
        }

        @Override
        public void teardown() {

        }
    }

    public OkHttpGlideUrlLoader(OkHttpClient client) {
        this.okHttpClient = client;
    }

    @Nullable
    @Override
    public LoadData<InputStream> buildLoadData(GlideUrl glideUrl, int width, int height, Options options) {
        return new LoadData<>(glideUrl, new OkHttpFetcher(okHttpClient, glideUrl));
    }

    @Override
    public boolean handles(GlideUrl glideUrl) {
        return false;
    }
}
