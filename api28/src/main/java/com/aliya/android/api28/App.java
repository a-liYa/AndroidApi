package com.aliya.android.api28;

import android.app.Application;
import android.util.Log;

/**
 * App
 *
 * @author a_liYa
 * @date 2019/3/20 11:11.
 */
public class App extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        Log.e("TAG", "进程 name : " + getProcessName());
    }

}