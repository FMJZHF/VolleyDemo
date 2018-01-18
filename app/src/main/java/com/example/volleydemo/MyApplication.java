package com.example.volleydemo;

import android.app.Application;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

/**
 * Created by 87501 on 2018/1/16.
 * 注册到AndroidMainfest.xml文件中并添加网络请求权限
 */

public class MyApplication extends Application {

    public static RequestQueue queues;


    @Override
    public void onCreate() {
        super.onCreate();
        queues = Volley.newRequestQueue(getApplicationContext());
    }
    //建立请求队列
    public static RequestQueue getHttpQueues(){
        return queues;
    }
}
