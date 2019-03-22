package com.aliya.android.api28;

import android.content.Context;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Build;
import android.util.DisplayMetrics;

/**
 * DensityDpi相关类
 *
 * @author a_liYa
 * @date 2017/3/31 16:30.
 */
public class Densities {

    public static Context forceDensityDpiByContext(Context base) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
            Resources resources = base.getResources();
            Configuration config = resources.getConfiguration();
            final int density = matchFitDensityDpi();
            if (config.densityDpi != density) {
                config.densityDpi = density;
                base = base.createConfigurationContext(config);
            }
        }
        return base;
    }

    public static Resources forceDensityDpiByResources(Resources resources) {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.JELLY_BEAN_MR1) {
            Configuration config = resources.getConfiguration();
            final int dpi = matchFitDensityDpi();
            /**
             *  0.8   0.9  1.0  1.1  1.2  1.35  1.5
             *   |    |     |    |    |    ｜　　｜
             * 极小   特小   小　  中   大   特大  超大
             */
            // 在这里设置字号 config.fontScale =

            if (config.densityDpi != dpi) {
                config.densityDpi = dpi;
                resources.updateConfiguration(config, resources.getDisplayMetrics());
            }
        }
        return resources;
    }

    private static int mFitDensity;

    /**
     * 匹配合适的 density dpi
     *
     * @return 返回匹配的 density dpi 理论值
     */
    public static int matchFitDensityDpi() {
        if (mFitDensity == 0) {
            DisplayMetrics metrics = Resources.getSystem().getDisplayMetrics();
            int width = Math.min(metrics.widthPixels, metrics.heightPixels);
            int height = Math.max(metrics.widthPixels, metrics.heightPixels);
            DpiEntity[] values = DpiEntity.values();

            double min = -1;
            int index = 0;

            for (int i = 0; i < values.length; i++) {
                DpiEntity value = values[i];
                double distance = getDistance(value.getWidth(), value.getHeight(), width, height);
                if (min < 0) {
                    min = distance;
                    index = i;
                } else if (distance < min) {
                    min = distance;
                    index = i;
                }
            }
            mFitDensity = values[index].getDpi();
        }
        return mFitDensity;
    }

    private static double getDistance(int x1, int y1, int x2, int y2) {
        double _x = Math.abs(x1 - x2);
        double _y = Math.abs(y1 - y2);
        return Math.sqrt(_x * _x + _y * _y);
    }

    private enum DpiEntity {
        L(240, 320, 120, 0.75f),
        M(320, 480, 160, 1f),
        H(480, 800, 240, 1.5f),
        XH(720, 1280, 320, 2f),
        XXH(1080, 1920, 480, 3f),
        XXXH(1440, 2560, 560, 3.5f);

        private int width;
        private int height;
        private int dpi;
        private float density;

        DpiEntity(int width, int height, int dpi, float density) {
            this.width = width;
            this.height = height;
            this.dpi = dpi;
            this.density = density;
        }

        public int getWidth() {
            return width;
        }

        public int getHeight() {
            return height;
        }

        public int getDpi() {
            return dpi;
        }

        public float getDensity() {
            return density;
        }
    }

}
