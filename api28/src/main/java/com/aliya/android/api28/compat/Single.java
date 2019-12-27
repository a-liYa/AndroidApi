package com.aliya.android.api28.compat;

import android.util.Log;

/**
 * Single
 *
 * @author a_liYa
 * @date 2019-12-02 08:28.
 */
public final class Single {

    public static Single sSingle = new Single("mode_1");


    public static final Single get() {
        return new Single("mode_2");
    }

    private Single(String mode) {
        Log.e("TAG", "Single: " + mode);
    }
}
