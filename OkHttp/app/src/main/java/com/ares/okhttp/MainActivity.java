package com.ares.okhttp;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ImageView;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity implements Callback{

    private ImageView imageView;

    private Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what){
                case 1:
                    Bitmap bitmap = BitmapFactory.decodeByteArray((byte[]) msg.obj,0,((byte[]) msg.obj).length);
                    imageView.setImageBitmap(bitmap);
                    break;
            }
            super.handleMessage(msg);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        imageView = (ImageView) findViewById(R.id.iv_http);

//        OkHttpManager.getInstance().getAsynHttp("http://192.168.16.224:8080/AresServer/images/20160227.png",this);
//        OkHttpManager.getInstance().getAsynHttp("http://192.168.16.224:8080/AresServer/file/hello.txt",this);

    }

    @Override
    public void onFailure(Call call, IOException e) {
        Log.e("Request Failure",e.toString());
    }

    @Override
    public void onResponse(Call call, Response response) throws IOException {
        if (response.isSuccessful()) {
            if (response.body() != null) {
//                Log.e("Request Success",response.body().byteStream()+"");
                Log.e("Request Success",response.body().string());

//                byte[] bytes = response.body().bytes();
//                Message message = new Message();
//                message.what = 1;
//                message.obj = bytes;
//                handler.sendMessage(message);
            }
        }
    }
}
