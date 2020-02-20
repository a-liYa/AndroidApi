package com.aliya.android.api27.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

/**
 * HorizontalScrollView
 *
 * @author a_liYa
 * @date 2020-01-23 14:26.
 */
public class HorizontalScrollView extends android.widget.HorizontalScrollView {

    public HorizontalScrollView(Context context) {
        super(context);
    }

    public HorizontalScrollView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public HorizontalScrollView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onScrollChanged(int l, int t, int oldl, int oldt) {
        super.onScrollChanged(l, t, oldl, oldt);
        Log.e("TAG", "onScrollChanged: " + l);
    }

    @Override
    public void requestChildFocus(View child, View focused) {
        super.requestChildFocus(child, null);
    }
}
