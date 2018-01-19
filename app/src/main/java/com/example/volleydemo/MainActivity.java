package com.example.volleydemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request.Method;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ((Button) findViewById(R.id.button)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                volley_Get_StringRequest();
                volley_Get_JsonObjectRequest();
            }
        });
        ((Button) findViewById(R.id.button_post)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                volley_Post_StringRequest();
                volley_Post_JsonObjectRequest();
            }
        });

        //二次封装
        ((Button) findViewById(R.id.button_get2)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                volley_Get_JsonObjectRequest2();
            }
        });
        ((Button) findViewById(R.id.button_post2)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                volley_Post_JsonObjectRequest2();
            }
        });

    }

    //========================二次封装==START===================================
    private void volley_Get_JsonObjectRequest2() {
        String url = "http://apis.juhe.cn/mobile/get?phone=13429667914&key=335adcc4e891ba4e4be6d7534fd54c5d";

        VolleyRequest.RequestGet(this, url, "abcGet", new VolleyInterface(this, VolleyInterface.mListener, VolleyInterface.mErrorListener) {
            @Override
            public void onMySuccess(String result) {
                toast("请求成功：" + result);
            }

            @Override
            public void onMyError(VolleyError error) {
                toast("请求失败：" + error.toString());
            }
        });
    }

    private void volley_Post_JsonObjectRequest2() {
        String url = "http://apis.juhe.cn/mobile/get?";

        Map<String, String> hashMap = new HashMap<String, String>();
        hashMap.put("phone", "13429667914");
        hashMap.put("key", "335adcc4e891ba4e4be6d7534fd54c5d");

        VolleyRequest.RequestPost(this, url, "abcPost", hashMap, new VolleyInterface(this, VolleyInterface.mListener, VolleyInterface.mErrorListener) {
            @Override
            public void onMySuccess(String result) {
                toast("请求成功：" + result);
            }

            @Override
            public void onMyError(VolleyError error) {
                toast("请求失败：" + error.toString());
            }
        });


//        JSONObject jsonObject = new JSONObject(hashMap);  // 将map转为jsonObject对象
//        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Method.POST, url, jsonObject, new Response.Listener<JSONObject>() {
//            @Override
//            public void onResponse(JSONObject jsonObject) {
//                toast("请求成功：" + jsonObject.toString());
//            }
//        }, new Response.ErrorListener() {
//            @Override
//            public void onErrorResponse(VolleyError volleyError) {
//                toast("请求失败：" + volleyError.toString());
//            }
//        });
//        jsonObjectRequest.setTag("abcPost"); //设置标签
//        MyApplication.getHttpQueues().add(jsonObjectRequest);//添加到全局队列中
    }


    //========================二次封装==END===================================

    private void volley_Post_StringRequest() {
        String url = "http://apis.juhe.cn/mobile/get?";
        StringRequest request = new StringRequest(Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String s) { // 请求成功回调
                toast("请求成功：" + s);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) { // 请求失败回调

                toast("请求失败：" + volleyError.toString());
            }
        }) { // post请求参数
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> hashMap = new HashMap<String, String>();
                hashMap.put("phone", "13429667914");
                hashMap.put("key", "335adcc4e891ba4e4be6d7534fd54c5d");
                return hashMap;
            }
        };
        request.setTag("abcPost"); //设置标签
        MyApplication.getHttpQueues().add(request);//添加到全局队列中
    }

    private void volley_Post_JsonObjectRequest() {
        String url = "http://apis.juhe.cn/mobile/get?";

        Map<String, String> hashMap = new HashMap<String, String>();
        hashMap.put("phone", "13429667914");
        hashMap.put("key", "335adcc4e891ba4e4be6d7534fd54c5d");
        JSONObject jsonObject = new JSONObject(hashMap);  // 将map转为jsonObject对象
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Method.POST, url, jsonObject, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject jsonObject) {
                toast("请求成功：" + jsonObject.toString());
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                toast("请求失败：" + volleyError.toString());
            }
        });
        jsonObjectRequest.setTag("abcPost"); //设置标签
        MyApplication.getHttpQueues().add(jsonObjectRequest);//添加到全局队列中
    }

    private void volley_Get_StringRequest() {
        //验证手机号归属地
        String url = "http://apis.juhe.cn/mobile/get?phone=13429667914&key=335adcc4e891ba4e4be6d7534fd54c5d";
        StringRequest request = new StringRequest(Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String s) { // 请求成功回调
                toast("请求成功：" + s);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) { // 请求失败回调

                toast("请求失败：" + volleyError.toString());
            }
        });
        request.setTag("abcGet"); //设置标签
        MyApplication.getHttpQueues().add(request);//添加到全局队列中
    }

    private void volley_Get_JsonObjectRequest() {
        //验证手机号归属地
        String url = "http://apis.juhe.cn/mobile/get?phone=13429667914&key=335adcc4e891ba4e4be6d7534fd54c5d";
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject jsonObject) {
                toast("请求成功：" + jsonObject.toString());
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                toast("请求失败：" + volleyError.toString());
            }
        });
        jsonObjectRequest.setTag("abcGet"); //设置标签
        MyApplication.getHttpQueues().add(jsonObjectRequest);//添加到全局队列中
    }
    //===========================================================

    private void toast(String string) {
        Toast.makeText(MainActivity.this, string, Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onStop() {
        super.onStop();
        MyApplication.getHttpQueues().cancelAll("abcPost");//通过指定的tag值，将指定的队列全部关闭
    }
}
