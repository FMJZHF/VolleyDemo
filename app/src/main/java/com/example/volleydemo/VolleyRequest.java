package com.example.volleydemo;

import android.content.Context;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.toolbox.StringRequest;

import java.util.Map;

/**
 * Created by 87501 on 2018/1/19.
 * 创建自己的请求管理
 */

public class VolleyRequest {

    public static StringRequest stringRequest;
    private Context mContext;

    public static void RequestGet(Context context, String url, String tag, VolleyInterface vif) {
        MyApplication.getHttpQueues().cancelAll(tag); // 请求之前先取消请求，防止重复请求
        stringRequest = new StringRequest(Request.Method.GET, url, vif.loadingListener(), vif.errorListener());
        stringRequest.setTag(tag);
        MyApplication.getHttpQueues().add(stringRequest);
        MyApplication.getHttpQueues().start();
    }

    public static void RequestPost(Context context, String url, String tag, final Map<String, String> params, VolleyInterface vif) {
        MyApplication.getHttpQueues().cancelAll(tag); // 请求之前先取消请求，防止重复请求
        stringRequest = new StringRequest(Request.Method.POST, url, vif.loadingListener(), vif.errorListener()) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                return params;
            }
        };
        stringRequest.setTag(tag);
        MyApplication.getHttpQueues().add(stringRequest);
        MyApplication.getHttpQueues().start();
    }
}
