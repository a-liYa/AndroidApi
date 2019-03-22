package com.aliya.android.api28;

import android.content.Context;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Bundle;
import android.util.Log;
import android.webkit.WebView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    WebView mWebView;

    @Override
    protected void attachBaseContext(Context newBase) {
        Resources resources = newBase.getResources();
        Configuration config = resources.getConfiguration();
        config.densityDpi = 160;
        resources.updateConfiguration(config, resources.getDisplayMetrics());
        Log.e("TAG", "attachBaseContext: " + newBase);
        super.attachBaseContext(newBase);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mWebView = findViewById(R.id.web_view);
        mWebView.loadUrl("https://mp.weixin.qq.com/s/d9QCoBP6kV9VSWvVldVVwA");

    }

    int count;

    @Override
    public Resources getResources() {
        Resources res = super.getResources();
        Log.e("TAG", " dpi: " + res.getConfiguration().densityDpi);
//        Configuration config = res.getConfiguration();
////        config.setToDefaults();
//        /**
//         *  0.8   0.9  1.0  1.1  1.2  1.35  1.5
//         *   |    |     |    |    |    ｜　　｜
//         * 极小   特小   小　  中   大   特大  超大
//         */
//        // 在这里设置字号
//        // config.fontScale =
//
//        App app = (App) getApplicationContext();
//
//        //适配大屏
//        config.densityDpi = 320;
//        res.updateConfiguration(config, res.getDisplayMetrics());
        return res;
    }
}
