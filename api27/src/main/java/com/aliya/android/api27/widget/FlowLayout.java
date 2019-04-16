package com.aliya.android.api27.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

/**
 * 自定义流式布局
 * 
 * @author a_liYa
 * @date 2016-4-4 下午3:29:32
 */
public class FlowLayout extends ViewGroup {

	public FlowLayout(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
	}

	public FlowLayout(Context context, AttributeSet attrs) {
		this(context, attrs, 0);
	}

	public FlowLayout(Context context) {
		this(context, null);
	}

	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		int widthSize = MeasureSpec.getSize(widthMeasureSpec);
		int widthMode = MeasureSpec.getMode(widthMeasureSpec);

		int heightSize = MeasureSpec.getSize(heightMeasureSpec);
		int heightMode = MeasureSpec.getMode(heightMeasureSpec);

		// wrap_content
		int width = 0;
		int height = 0;

		// 记录每一行的宽度与高度
		int lineWidth = 0;
		int lineHeight = 0;

		// 得到内部元素的个数
		int cCount = getChildCount();

		for (int i = 0; i < cCount; i++) {
			View child = getChildAt(i);
			// 测量子View的宽和高
			measureChild(child, widthMeasureSpec, heightMeasureSpec);
			// 得到子View的LayoutParams
			MarginLayoutParams clp = (MarginLayoutParams) child
					.getLayoutParams();

			// 子View占据的宽度
			int childWidth = child.getMeasuredWidth() + clp.leftMargin
					+ clp.rightMargin;
			// 子View占据的高度
			int childHeight = child.getMeasuredHeight() + clp.topMargin
					+ clp.bottomMargin;

			// 换行
			if (lineWidth + childWidth > widthSize - getPaddingLeft()
					- getPaddingRight()) {
				// 记录Flow的宽高
				width = Math.max(width, lineWidth);
				height += lineHeight;

				// 重置行的宽高
				lineWidth = childWidth;
				lineHeight = childHeight;
			} else { // 未换行
				// 叠加行宽
				lineWidth += childWidth;
				// 得到当前行最大的高度
				lineHeight = Math.max(lineHeight, childHeight);
			}
			// 最后一个控件，记录最后一行的宽高度
			if (i == cCount - 1) {
				width = Math.max(lineWidth, width);
				height += lineHeight;
			}
		}

		setMeasuredDimension(
				//
				widthMode == MeasureSpec.EXACTLY ? widthSize : width
						+ getPaddingLeft() + getPaddingRight(),
				heightMode == MeasureSpec.EXACTLY ? heightSize : height
						+ getPaddingTop() + getPaddingBottom()//
		);

	}

	/**
	 * 存储所有的View
	 */
	private List<List<View>> mAllViews = new ArrayList<List<View>>();
	/**
	 * 每一行的高度
	 */
	private List<Integer> mLineHeight = new ArrayList<Integer>();

	@Override
	protected void onLayout(boolean changed, int l, int t, int r, int b) {
		mAllViews.clear();
		mLineHeight.clear();

		// 当前ViewGroup的宽度
		int width = getWidth();

		int lineWidth = 0;
		int lineHeight = 0;

		// 每一行的子View
		List<View> lineViews = new ArrayList<View>();

		for (int i = 0; i < getChildCount(); i++) {
			View child = getChildAt(i);
			MarginLayoutParams clp = (MarginLayoutParams) child
					.getLayoutParams();

			int childWidth = child.getMeasuredWidth();
			int childHeight = child.getMeasuredHeight();

			// 如果需要换行
			if (childWidth + lineWidth + clp.leftMargin + clp.rightMargin > width
					- getPaddingLeft() - getPaddingRight()) {
				// 记录LineHeight
				mLineHeight.add(lineHeight);
				mAllViews.add(lineViews); // 记录当前行的Views

				// 重置我们的行宽和行高
				lineWidth = 0;
				lineHeight = childHeight + clp.topMargin + clp.bottomMargin;
				// 重置我们的View集合
				lineViews = new ArrayList<View>();
			}
			lineWidth += childWidth + clp.leftMargin + clp.rightMargin;
			lineHeight = Math.max(lineHeight, childHeight + clp.topMargin
					+ clp.bottomMargin);
			lineViews.add(child);

		}// for end
			// 处理最后一行
		mLineHeight.add(lineHeight);
		mAllViews.add(lineViews);

		// 设置子View的位置
		int left = getPaddingLeft();
		int top = getPaddingTop();

		for (int i = 0; i < mAllViews.size(); i++) {
			// 当前行的所有的View
			lineViews = mAllViews.get(i);
			lineHeight = mLineHeight.get(i);

			for (int j = 0; j < lineViews.size(); j++) {
				View child = lineViews.get(j);
				// 判断child的状态
				if (child.getVisibility() == View.GONE) {
					continue;
				}

				MarginLayoutParams lp = (MarginLayoutParams) child
						.getLayoutParams();

				int lc = left + lp.leftMargin;
				int tc = top + lp.topMargin;
				int rc = lc + child.getMeasuredWidth();
				int bc = tc + child.getMeasuredHeight();

				// 为子View进行布局
				child.layout(lc, tc, rc, bc);

				left += child.getMeasuredWidth() + lp.leftMargin
						+ lp.rightMargin;
			}
			left = getPaddingLeft();
			top += lineHeight;
		}

	}

	/**
	 * 与当前ViewGroup对应的LayoutParams
	 */
	@Override
	public LayoutParams generateLayoutParams(AttributeSet attrs) {
		return new MarginLayoutParams(getContext(), attrs);
	}

}
