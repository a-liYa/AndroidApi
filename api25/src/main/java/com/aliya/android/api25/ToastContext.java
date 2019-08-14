package com.aliya.android.api25;

import android.content.Context;
import android.content.ContextWrapper;
import android.support.annotation.NonNull;
import android.util.AttributeSet;
import android.util.Log;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;

import java.util.zip.Inflater;

/**
 * ToastContext TODO
 *
 * @author a_liYa
 * @date 2019-06-06 17:07.
 */
public class ToastContext extends ContextWrapper {

    public ToastContext(Context base) {
        super(base);
    }

    @Override
    public Context getBaseContext() {
        Log.e("TAG", "getBaseContext: ");
        return super.getBaseContext();
    }

    @Override
    public Context getApplicationContext() {
        Log.e("TAG", "getApplicationContext: ");
        return new ApplicationContextWrapper(super.getApplicationContext());
    }

    @Override
    public Object getSystemService(String name) {
        Object service = super.getSystemService(name);
        if (Context.LAYOUT_INFLATER_SERVICE.equals(name)) {
//            ((LayoutInflater)service).setFactory(new LayoutInflater.Factory() {
//                @Override
//                public View onCreateView(String name, Context context, AttributeSet attrs) {
//                }
//            });
//            Log.e("TAG", "getSystemService: " + se);\

        }
        return service;
    }

    private static class ApplicationContextWrapper extends ContextWrapper {

        public ApplicationContextWrapper(Context base) {
            super(base);
        }

        @Override
        public Object getSystemService(String name) {
            if (Context.WINDOW_SERVICE.equals(name)) {
                Log.e("TAG", "getSystemService: ");
                return new WindowManagerWrapper((WindowManager) super.getSystemService(name));
            } else if (Context.WINDOW_SERVICE.equals(name)){

            }
            return super.getSystemService(name);
        }
    }

    private static class WindowManagerWrapper implements WindowManager {

        private final WindowManager mBase;


        private WindowManagerWrapper(@NonNull WindowManager base) {
            this.mBase = base;
        }

        @Override
        public Display getDefaultDisplay() {
            return mBase.getDefaultDisplay();
        }

        @Override
        public void removeViewImmediate(View view) {
            mBase.removeViewImmediate(view);
        }

        @Override
        public void addView(View view, ViewGroup.LayoutParams params) {
            try {
                mBase.addView(view, params);
            } catch (BadTokenException e) {
                /* ignore */
            }
        }

        @Override
        public void updateViewLayout(View view, ViewGroup.LayoutParams params) {
            mBase.updateViewLayout(view, params);
        }

        @Override
        public void removeView(View view) {
            mBase.removeView(view);
        }

    }

}
