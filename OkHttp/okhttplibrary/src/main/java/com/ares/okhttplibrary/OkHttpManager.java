package com.ares.okhttplibrary;

import android.support.annotation.NonNull;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;

/**
 * OkHttp3管理类
 * Created by Yan Cui on 2017/2/27/027.
 */

public class OkHttpManager {

    private OkHttpClient client;

    /**
     * 私有构造方法，防止被实例化
     */
    private OkHttpManager(){
        client = new OkHttpClient();
    }

    /**
     * 获取单例OkHttpClient
     * @return {@link OkHttpClient}
     */
    public static OkHttpManager getInstance(){
        return OkHttpFactory.manager;
    }

    /**
     * 此处使用一个内部类来维护单例
     */
    private static class OkHttpFactory{
        private static OkHttpManager manager = new OkHttpManager();
    }

    public void getAsynHttp(@NonNull String url,@NonNull Callback callback){
        Request.Builder requestBuilder = new Request.Builder();
        requestBuilder.url(url);
        requestBuilder.method("GET",null);
        final Request request = requestBuilder.build();
        Call call = client.newCall(request);
        call.enqueue(callback);
    }



    /**
     * 如果该对象被用于序列化
     * 可以保证对象在序列化前后保持一致
     * @return {@link OkHttpClient}
     */
    public Object readResolve() {
        return getInstance();
    }
}
