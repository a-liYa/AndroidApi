package com.aliya.android.api28;

import android.content.Context;
import android.util.AttributeSet;
import android.webkit.WebSettings;
import android.webkit.WebView;

/**
 * XWebView
 *
 * @author a_liYa
 * @date 2019/3/20 21:46.
 */
public class XWebView extends WebView {

    public XWebView(Context context) {
        this(context, null);
    }

    public XWebView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public XWebView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        WebSettings settings = getSettings();
        settings.setJavaScriptEnabled(true); // 启用支持javaScript
        settings.setDefaultTextEncodingName("UTF-8");
        settings.setLoadWithOverviewMode(true);
        settings.setUseWideViewPort(true); // 视频全屏点击支持回调
        settings.setAllowFileAccess(true); // 允许访问文件

    }

}
