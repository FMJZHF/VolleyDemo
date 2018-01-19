package com.example.volleydemo;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.ImageRequest;
import com.android.volley.toolbox.NetworkImageView;
import com.example.volleydemo.Image.BitmapCashe;

/**
 * Created by 87501 on 2018/1/19.
 */

public class ImageActivity extends AppCompatActivity {

    private ImageView imageView, imageView2;
    private Button button, button2, button3;
    private NetworkImageView networkImageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image);
        initView();
    }

    private void initView() {
        imageView = findViewById(R.id.image);
        button = findViewById(R.id.button);
        imageView2 = findViewById(R.id.image2);
        button2 = findViewById(R.id.button2);
        button3 = findViewById(R.id.button3);
        networkImageView = findViewById(R.id.net_work_image);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url = "https://ss0.bdstatic.com/5aV1bjqh_Q23odCf/static/superman/img/logo_top_ca79a146.png";
                ImageRequest imageRequest = new ImageRequest(url, new Response.Listener<Bitmap>() {
                    @Override
                    public void onResponse(Bitmap bitmap) {
                        imageView.setImageBitmap(bitmap);
                    }
                }, 0, 0, Bitmap.Config.RGB_565, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError volleyError) {
                        imageView.setImageResource(R.mipmap.ic_launcher);
                    }
                });
                MyApplication.getHttpQueues().add(imageRequest);
            }
        });
        //利用缓存获取图片
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url = "https://ss0.bdstatic.com/5aV1bjqh_Q23odCf/static/superman/img/logo_top_ca79a146.png";
                ImageLoader imageLoader = new ImageLoader(MyApplication.getHttpQueues(), new BitmapCashe());
                ImageLoader.ImageListener imageListener = ImageLoader.getImageListener(imageView2, R.mipmap.ic_launcher, R.mipmap.ic_launcher);
                imageLoader.get(url, imageListener);
            }
        });


        //利用NetworkImageView获取图片
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url = "https://ss0.bdstatic.com/5aV1bjqh_Q23odCf/static/superman/img/logo_top_ca79a146.png";
                ImageLoader imageLoader = new ImageLoader(MyApplication.getHttpQueues(), new BitmapCashe());
                networkImageView.setDefaultImageResId(R.mipmap.ic_launcher); // 加载中默认图片;
                networkImageView.setErrorImageResId(R.mipmap.ic_launcher); // 加载失败显示的图片;
                networkImageView.setImageUrl(url, imageLoader);
            }
        });
    }
}
