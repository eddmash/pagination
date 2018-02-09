package com.eddmash.views;
/*
* This file is part of the Tools package.
* 
* (c) Eddilbert Macharia (http://eddmash.com)<edd.cowan@gmail.com>
*
* For the full copyright and license information, please view the LICENSE
* file that was distributed with this source code.
*/

import android.content.Context;
import android.content.res.TypedArray;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

abstract public class CollectionView<T> extends ViewGroup {

    private int columns;
    private int rowPadding = 10;
    ;

    private void init(Context context) {
    }

    public CollectionView(Context context) {
        this(context, null, 0);
    }

    public CollectionView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CollectionView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
        styles(context, attrs);
    }

    private void styles(Context context, AttributeSet attrs) {
        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.CollectionView);
        try {
            columns = a.getInteger(R.styleable.CollectionView_columns, 3);

            final CharSequence[] entries = a.getTextArray(R.styleable.CollectionView_entries);

            if (entries != null) {
                this.addEntries(entries);
            }

        } finally {
            a.recycle();
        }
    }

    protected abstract void addEntries(CharSequence[] entries);

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        final int count = getChildCount();
        int curWidth, curHeight, curLeft, curTop, maxHeight;
        //get the available size of child view
        final int childLeft = this.getPaddingLeft();
        curHeight = this.getPaddingTop();
        final int childTop = this.getPaddingTop();
        final int childRight = this.getMeasuredWidth() - this.getPaddingRight();
        final int childBottom = this.getMeasuredHeight() - this.getPaddingBottom();
        final int fullWidth = getMeasuredWidth();
        final int childWidth = fullWidth / columns;
        final int childHeight = childBottom - childTop;
        maxHeight = 0;
        curLeft = childLeft;
        curTop = childTop;
        int curW;
        int bPadding;
        for (int i = 0; i < count; i++) {
            View child = getChildAt(i);
            if (child.getVisibility() == GONE) {
                continue;
            }


            //Get the maximum size of the child
            curW = childWidth;
            bPadding = getPaddingBottom() + rowPadding;

            child.setPadding(
                    child.getPaddingLeft(),
                    child.getPaddingTop(),
                    child.getPaddingRight(),
                    bPadding
            );
            child.measure(MeasureSpec.makeMeasureSpec(curW, MeasureSpec.EXACTLY),
                    MeasureSpec.makeMeasureSpec(childHeight, MeasureSpec.AT_MOST));

            curWidth = child.getMeasuredWidth();

            curHeight = Math.max(curHeight, child.getMeasuredHeight());
            //wrap is reach to the end
            if (i % columns == 0) {
                curLeft = childLeft;
                curTop += maxHeight;
                maxHeight = 0;
            }
            //do the layout
            child.layout(curLeft, curTop, curLeft + curWidth, curTop + curHeight);
            //store the max height
            if (maxHeight < curHeight)
                maxHeight = curHeight;
            curLeft += curWidth;

            try {
                onLayingOut((T) child);
            } catch (ClassCastException e) {
                // just pass
            }

        }
    }

    protected void onLayingOut(T child) {
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int width = Math.max(getMeasuredWidth(), getSuggestedMinimumWidth());
        if (width > 0) {
            int count = getChildCount();
            // Measurement will ultimately be computing these values.
            int largPad = 0;
            int maxHeight = 0;
            int prevWidth = 0;
            int curWidth = 0;
            int childState = 0;
            int mLeftWidth = 0;
            int rowCount = 1;
            int childWidth = width / columns;
            int height = getPaddingBottom() + getPaddingTop();

            // Iterate through all children, measuring them and computing our dimensions
            // from their size.
            for (int i = 0; i < count; i++) {
                final View child = getChildAt(i);
                if (child.getVisibility() == GONE) {
                    continue;
                }
                // Measure the child.
                measureChild(child,
                        MeasureSpec.makeMeasureSpec(childWidth, MeasureSpec.EXACTLY),
                        heightMeasureSpec
                );

                maxHeight = Math.max(maxHeight, child.getMeasuredHeight());

                if (i % columns == 0) {
                    height += maxHeight + rowPadding;
                    maxHeight = 0;
                }
            }
            // Check against our minimum height and width
            height = Math.max(height, getSuggestedMinimumHeight());

            // Report our final dimensions.
            setMeasuredDimension(width, height);
        }
    }

    public String getLogTag() {
        return getClass().getName();
    }

    public abstract List<T> getValue();

    public abstract void setValue(Object data);
}
