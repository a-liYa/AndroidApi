package com.aliya.android.api28;

import android.app.Application;
import android.content.Context;
import android.content.res.Configuration;
import android.content.res.Resources;

/**
 * App
 *
 * @author a_liYa
 * @date 2019/3/20 11:11.
 */
public class App extends Application {

    public static Context sApp;

    @Override
    protected void attachBaseContext(Context base) {
//        WindowManager windowManager = (WindowManager)
//                getSystemService(Context.WINDOW_SERVICE);
//        Display display = windowManager.getDefaultDisplay();
        final Resources res = base.getResources();
        final Configuration config = res.getConfiguration();
        config.densityDpi = 480;
        super.attachBaseContext(base.createConfigurationContext(config));
        sApp = this;
    }

    @Override
    public void onCreate() {
        super.onCreate();
//        Log.e("TAG", "进程 name : " + getProcessName());
    }

}