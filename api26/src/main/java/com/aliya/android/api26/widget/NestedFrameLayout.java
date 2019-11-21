package com.aliya.android.api26.widget;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.view.NestedScrollingParent;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.FrameLayout;

/**
 * NestedFrameLayout
 *
 * @author a_liYa
 * @date 2019-10-22 10:14.
 */
public class NestedFrameLayout extends FrameLayout implements NestedScrollingParent {

    private String TAG = "TAG";

    public NestedFrameLayout(Context context) {
        super(context);
    }

    public NestedFrameLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        Log.e(TAG, "NestedFrameLayout: ");
    }

    public NestedFrameLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    private RecyclerView.OnScrollListener mOnScrollListener = new RecyclerView.OnScrollListener() {
        @Override
        public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
            Log.e(TAG, "onScrollStateChanged: " + newState);
        }
    };

    // 是否接受此次的内部嵌套滚动
    // target是想要内部滚动的view，child是包含target的parent的直接子vie
    @Override
    public boolean onStartNestedScroll(View child, View target, int nestedScrollAxes) {
        Log.e(TAG, "onStartNestedScroll: " + target + " - " + nestedScrollAxes);
        if (target instanceof RecyclerView) {
            ((RecyclerView) target).removeOnScrollListener(mOnScrollListener);
            ((RecyclerView) target).addOnScrollListener(mOnScrollListener);
        }
        return true;
    }

    // 停止了内部滚动
    @Override
    public void onStopNestedScroll(View target) {
        super.onStopNestedScroll(target);
        Log.e(TAG, "onStopNestedScroll: " + target);
    }

}
