package com.aliya.android.api28;

import android.content.Context;
import android.content.pm.ActivityInfo;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.webkit.WebView;

import com.aliya.android.api28.compat.ActivityOrientationCompat;

public class MainActivity extends AppCompatActivity {

    WebView mWebView;

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(newBase);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        ActivityOrientationCompat.setRequestedOrientation(this, ActivityInfo.SCREEN_ORIENTATION_PORTRAIT, true);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mWebView = findViewById(R.id.web_view);
        mWebView.loadUrl("http://t.pae.baidu.com/s?s=bai-xtzvbd");
    }

    @Override
    public Resources getResources() {
        Resources res = super.getResources();
        return res;
    }
}
