package com.aliya.android.api28;

import android.app.Application;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.res.Configuration;
import android.os.Build;
import android.provider.Settings;
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

//        int dpi = Densities.forceMatchDensity(mScreenWidthPx, mScreenHeightPx);

//        Log.e("TAG", "onCreate: " + dpi);

//        Resources system = Resources.getSystem();
//        Configuration config = system.getConfiguration();
//        config.densityDpi = 320;
//        system.updateConfiguration(config, system.getDisplayMetrics());

        if (Build.VERSION.SDK_INT >= 24) {

            String[] s = getApplicationInfo().sharedLibraryFiles;
            int arrayLength = (s != null ? s.length : 0) + 1;
            final String[] sharedLibFiles = new String[arrayLength];
            if (s != null) {
                System.arraycopy(s, 0, sharedLibFiles, 0, s.length);
            }
            String webviewImplPackageName = Settings.Global.getString(getContentResolver(),"webview_provider");
            PackageInfo packageInfo = null;
            try {
                packageInfo = getPackageManager().getPackageInfo(webviewImplPackageName,
                        PackageManager.GET_META_DATA);
            } catch (PackageManager.NameNotFoundException e) {
                e.printStackTrace();
            }
            sharedLibFiles[arrayLength - 1] = packageInfo.applicationInfo.sourceDir;
            getApplicationInfo().sharedLibraryFiles = sharedLibFiles;
        }
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        Log.e("TAG", "onConfigurationChanged: " + newConfig.densityDpi);
        super.onConfigurationChanged(newConfig);

    }
}