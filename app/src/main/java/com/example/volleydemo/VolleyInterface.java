package com.example.volleydemo;

import android.content.Context;

import com.android.volley.Response.ErrorListener;
import com.android.volley.Response.Listener;
import com.android.volley.VolleyError;

/**
 * Created by 87501 on 2018/1/19.
 */

public abstract class VolleyInterface {
    private Context mContext;
    public static Listener<String> mListener;
    public static ErrorListener mErrorListener;

    public VolleyInterface(Context context, Listener<String> listener, ErrorListener errorListener) {
        this.mContext = context;
        this.mListener = listener;
        this.mErrorListener = errorListener;
    }

    public abstract  void onMySuccess(String result);
    public abstract  void onMyError( VolleyError error);


    //请求成功
    public Listener<String> loadingListener() {
        mListener = new Listener<String>() {
            @Override
            public void onResponse(String string) {
                //弹出加载中
                onMySuccess(string);
            }
        };
        return mListener;
    }

    //请求失败
    public ErrorListener errorListener() {
        mErrorListener = new ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                //提示请求失败
                onMyError(volleyError);
            }
        };
        return mErrorListener;
    }
}
