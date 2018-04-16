package com.dcl.javacv.baidu.utils.imageloader.glide;

import android.support.annotation.NonNull;

import com.bumptech.glide.Priority;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.data.DataFetcher;
import com.bumptech.glide.load.model.GlideUrl;
import com.bumptech.glide.util.ContentLengthInputStream;

import java.io.IOException;
import java.io.InputStream;
import java.util.Map;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;

/**
 * Created by Administrator on 2018/3/16.
 */

public class OkHttpFetcher implements DataFetcher<InputStream> {


    private final OkHttpClient client;
    private final GlideUrl url;
    private boolean isCancelled;
    private ResponseBody responseBody;
    private InputStream stream;

    public OkHttpFetcher(OkHttpClient client, GlideUrl url) {
        this.client = client;
        this.url = url;
    }

    @Override
    public void loadData(Priority priority, DataCallback<? super InputStream> callback) {
        Request.Builder requestBuilder = new Request.Builder()
                .url(url.toStringUrl());
        for (Map.Entry<String, String> headerEntry : url.getHeaders().entrySet()) {
            String key = headerEntry.getKey();
            requestBuilder.addHeader(key, headerEntry.getValue());
        }
        requestBuilder.addHeader("httplib", "OkHttp");
        Request request = requestBuilder.build();
        if (isCancelled) {
            return;
        }
        Response response = null;
        try {
            response = client.newCall(request).execute();
        } catch (IOException e) {
            e.printStackTrace();
        }
        responseBody = response.body();
        if (!response.isSuccessful() || responseBody == null) {
            try {
                throw new IOException("Request failed with code: " + response.code());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        stream = ContentLengthInputStream.obtain(responseBody.byteStream(),
                responseBody.contentLength());
        callback.onDataReady(stream);
    }

    @Override
    public void cleanup() {
        try {
            if (stream != null) {
                stream.close();
            }
            if (responseBody != null) {
                responseBody.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void cancel() {
        isCancelled = true;
    }

    @NonNull
    @Override
    public Class<InputStream> getDataClass() {
        return null;
    }

    @NonNull
    @Override
    public DataSource getDataSource() {
        return null;
    }
}
