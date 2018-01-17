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
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.CheckBox;
import android.widget.CompoundButton;

import java.util.ArrayList;
import java.util.List;

public class MultiCheckboxView extends CollectionView<CheckBox> {
    List<CheckBox> checked = new ArrayList<>();
    private OnCheckedChangeListener mOnCheckedChangeListener;

    public MultiCheckboxView(Context context) {
        super(context);
    }

    public MultiCheckboxView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public MultiCheckboxView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onLayingOut(CheckBox child) {
        super.onLayingOut(child);
        final CheckBox checkBox = child;
        checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    checked.add(checkBox);
                } else {
                    if (checked.contains(checkBox)) {
                        checked.remove(checkBox);
                    }
                }
                if (mOnCheckedChangeListener != null) {
                    mOnCheckedChangeListener.onCheckedChanged(buttonView, isChecked);
                }
            }
        });
    }

    @Override
    public List<CheckBox> getValue() {
        return checked;
    }

    public void setValue(Object data) {
        List checkedData = (List) data;
        CheckBox checkBox;
        for (int i = 0; i < getChildCount(); i++) {
            checkBox = (CheckBox) getChildAt(i);

            if (checkedData.contains(checkBox.getText())) {
                checkBox.setChecked(true);
            }
        }
    }

    /**
     * Register a callback to be invoked when the checked state of this button
     * changes.
     *
     * @param listener the callback to call on checked state change
     */
    public void setOnCheckedChangeListener(OnCheckedChangeListener listener) {
        mOnCheckedChangeListener = listener;
    }

    protected void addEntries(CharSequence[] entries) {
        CheckBox view;
        for (CharSequence entry : entries) {
            view = new CheckBox(getContext());
            view.setText(entry);
            addView(view);
        }
    }

    public void addEntries(List data) {
        CheckBox view;
        for (Object datum : data) {

            view = new CheckBox(getContext());
            view.setText(String.valueOf(datum));
            view.setTag(datum);
            addView(view);
        }
    }

    public interface OnCheckedChangeListener {

        /**
         * Called when the checked state of a compound button has changed.
         *
         * @param buttonView The compound button view whose state has changed.
         * @param isChecked  The new checked state of buttonView.
         */
        void onCheckedChanged(CompoundButton buttonView, boolean isChecked);
    }
}
