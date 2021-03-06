package com.ares.okhttplibrary;

import android.support.annotation.NonNull;
import android.util.Log;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Headers;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * OkHttp3管理类
 * Created by Yan Cui on 2017/2/27/027.
 */

public class OkHttpManager {

    private OkHttpClient client;
    static final MediaType MEDIA_TYPE_MARKDOWN = MediaType.parse("text/x-markdown; charset=utf-8");
    static final MediaType MEDIA_TYPE_PNG = MediaType.parse("image/png");

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

    public Object getSyncHttp(@NonNull String url){
        Request.Builder requestBuilder = new Request.Builder();
        requestBuilder.url(url);
        requestBuilder.method("GET",null);
        Request request = requestBuilder.build();
        return "";
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



    /******************************************* 练 习 *****************************************************/
    /**
     * 同步get请求
     */
    public void getSyncHttp(){
        Request.Builder requestBuilder = new Request.Builder();
        requestBuilder.url("http://publicobject.com/helloworld.txt");
        requestBuilder.method("GET",null);
        Request request = requestBuilder.build();

        try {
            Response response = client.newCall(request).execute();
            if (!response.isSuccessful()) throw new IOException("Unexpected code "+response);
            Headers resposeHeaders = response.headers();
            for (int i = 0; i < resposeHeaders.size(); i++) {
                Log.e("*******",resposeHeaders.name(i)+" : "+resposeHeaders.value(i));
            }
            Log.e("*******",response.body().string());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
