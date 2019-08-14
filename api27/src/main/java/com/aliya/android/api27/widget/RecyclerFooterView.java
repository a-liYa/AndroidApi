package com.aliya.android.api27.widget;

import android.content.Context;
import android.os.Build;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.widget.LinearLayout;

import java.lang.reflect.Field;

/**
 * RecyclerFooterView
 *
 * @author a_liYa
 * @date 2019-06-05 14:55.
 */
public class RecyclerFooterView extends LinearLayout {

    public RecyclerFooterView(Context context) {
        super(context);
    }

    public RecyclerFooterView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public RecyclerFooterView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public RecyclerFooterView(Context context, AttributeSet attrs, int defStyleAttr,
                              int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int spareHeight = getParentSpareHeight();
        if (spareHeight > getMinimumHeight()) {
            heightMeasureSpec = MeasureSpec.makeMeasureSpec(spareHeight, MeasureSpec.EXACTLY);
        } else {
            heightMeasureSpec = MeasureSpec.makeMeasureSpec(spareHeight, MeasureSpec.UNSPECIFIED);
        }
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

    }

    private int getParentSpareHeight() {
        if (getParent() instanceof RecyclerView) {
            RecyclerView parent = (RecyclerView) getParent();
            if (parent.getLayoutManager() instanceof LinearLayoutManager) {
                LinearLayoutManager layoutManager = (LinearLayoutManager) parent.getLayoutManager();
                try {
                    Field field = LinearLayoutManager.class.getDeclaredField("mLayoutState");
                    field.setAccessible(true);
                    Object layoutState = field.get(layoutManager);
                    if (layoutState != null) {
                        Field offsetField = layoutState.getClass().getDeclaredField("mOffset");
                        offsetField.setAccessible(true);
                        Integer offset = (Integer) offsetField.get(layoutState);
                        if (offset != null) {
                            return parent.getHeight() - offset.intValue();
                        }
                    }
                } catch (Exception e) {
                    // no-op
                }
            }
        }
        return -1;
    }
}
