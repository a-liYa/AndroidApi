package com.aliya.android.api28;

import android.content.Context;
import android.content.res.Resources;
import android.os.Build;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.webkit.WebResourceRequest;
import android.webkit.WebResourceResponse;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;

import java.io.IOException;
import java.io.PipedInputStream;
import java.io.PipedOutputStream;
import java.util.HashSet;
import java.util.Set;

/**
 * XWebView
 *
 * @author a_liYa
 * @date 2019/3/20 21:46.
 */
public class XWebView extends WebView {

    static Set<String> sImgUrlSet = new HashSet<>();
    static  {
        sImgUrlSet.add("https://f12.baidu.com/it/u=1167958349,296813570&fm=173&app=49&f=JPEG?w=640&h=800&s=DAA021C0F8523EC638A3A11E030060D3&access=215967316");
        sImgUrlSet.add("https://f11.baidu.com/it/u=2710057645,3015615465&fm=173&app=49&f=JPEG?w=640&h=640&s=56A9B944AB2B2686093D9C990100C0C1&access=215967316");
        sImgUrlSet.add("https://f11.baidu.com/it/u=558144961,3955229065&fm=173&app=49&f=JPEG?w=640&h=639&s=CD7831C6966B26A69BADC51903001091&access=215967316");
        sImgUrlSet.add("https://f10.baidu.com/it/u=1897743342,2827216105&fm=173&app=49&f=JPEG?w=640&h=640&s=06727984F2621AA668B9E5190100C093&access=215967316");
        sImgUrlSet.add("https://f12.baidu.com/it/u=1547661338,2132381560&fm=173&app=49&f=JPEG?w=640&h=640&s=BAB06184C64B3AE66B91A51901007091&access=215967316");
        sImgUrlSet.add("https://f12.baidu.com/it/u=3312391535,2566689854&fm=173&app=49&f=JPEG?w=640&h=640&s=97A8F804C6231EAE46183C930100E0C0&access=215967316");
    }

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
        // WebView在安卓5.0之前默认允许其加载混合网络协议内容
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            settings.setMixedContentMode(WebSettings.MIXED_CONTENT_ALWAYS_ALLOW);
        }
        setWebViewClient(new WebViewClient() {

            @Nullable
            @Override
            public WebResourceResponse shouldInterceptRequest(WebView view,
                                                              WebResourceRequest request) {
                return super.shouldInterceptRequest(view, request);
            }

            @Nullable
            @Override
            public WebResourceResponse shouldInterceptRequest(WebView view, final String url) {
                WebResourceResponse response = null;
                if (sImgUrlSet.contains(url)) {
                    try {
                        final PipedOutputStream out = new PipedOutputStream();
                        PipedInputStream in = new PipedInputStream(out);
                        response = new WebResourceResponse("image/jpeg", "UTF-8", in);
                        Glide.with(getContext()).load(url).asBitmap().toBytes().listener(new RequestListener<String, byte[]>() {
                            @Override
                            public boolean onException(Exception e, String model, Target<byte[]> target, boolean isFirstResource) {
                                return false;
                            }

                            @Override
                            public boolean onResourceReady(final byte[] resource, String model,
                                                           Target<byte[]> target,
                                                           boolean isFromMemoryCache,
                                                           boolean isFirstResource) {
                                new Thread(new Runnable() {
                                    @Override
                                    public void run() {
                                        try {
                                            out.write(resource);
                                            out.close();
                                        } catch (IOException e) {

                                        }
                                    }
                                }).start();

                                return true;
                            }
                        }).into(5000, 5000);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                return response;
            }
        });
    }
}
