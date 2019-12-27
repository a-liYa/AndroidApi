package com.aliya.android.api28;

import android.content.Context;
import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.webkit.WebView;

import com.aliya.android.api28.compat.ActivityOrientationCompat;
import com.aliya.android.api28.compat.DensityHelper;

public class MainActivity extends AppCompatActivity {

    WebView mWebView;

    @Override
    protected void attachBaseContext(Context newBase) {
        final Resources res = newBase.getResources();
        final Configuration config = res.getConfiguration();
        config.densityDpi = DensityHelper.matchTheoryDpi(App.sApp);
        newBase = newBase.createConfigurationContext(config);
        super.attachBaseContext(newBase);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        ActivityOrientationCompat.setRequestedOrientation(this, ActivityInfo.SCREEN_ORIENTATION_PORTRAIT, true);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mWebView = findViewById(R.id.web_view);

        mWebView.loadUrl("https://mbd.baidu.com/newspage/data/landingsuper?context=%7B%22nid%22%3A%22news_9728797053672044395%22%7D&n_type=0&p_from=1");

    }

    @Override
    protected void onResume() {
        super.onResume();
    }
}
