package com.eddmash.grids.columns;
/*
* This file is part of the Tools package.
* 
* (c) Eddilbert Macharia (http://eddmash.com)<edd.cowan@gmail.com>
*
* For the full copyright and license information, please view the LICENSE
* file that was distributed with this source code.
*/

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;

import com.eddmash.grids.R;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class CheckColumn extends Column {
    private final String name;
    private CheckBox view;
    private List<CheckBox> checked;

    public CheckColumn(Context context) {
        this(context, null);
    }

    public CheckColumn(Context context, String name) {
        super(context);
        this.name = name;
        checked = new ArrayList<>();
    }

    @Override
    public View makeView(int index, Map datum) {
        view = new CheckBox(getContext());
        view.setTag(datum);
        view.setId(R.id.checkboxid);
        view.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    checked.add(view);
                } else {
                    checked.remove(view);
                }
                Log.e("SINGLECHECKED", checked.size() + "");
            }
        });
        return view;
    }

    @Override
    public double getWeight() {
        return 0.5;
    }

    @Override
    public View getHeader() {
        CheckBox header = new CheckBox(getContext());

        if (name != null) {
            header.setText(name);
        }
        header.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                Log.e("CHECKED", "checekd");
                selectDeselectAll(isChecked);
            }
        });
        return header;
    }

    public List<CheckBox> getCheckedCheckboxes() {
        return checked;
    }

    public List<Map> getCheckedData() {
        List<Map> data = new ArrayList<>();
        for (CheckBox checkbox : checked) {
            data.add((Map) checkbox.getTag());
        }
        return data;
    }

    private void selectDeselectAll(final boolean isChecked) {
        if (!isChecked) {
            checked.clear();
        }

        // we need to un in the main ui thread
        ((Activity) getContext()).runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if (dataView != null) {
                    for (int i = 0; i < dataView.getContentLayout().getChildCount(); i++) {
                        View view = dataView.getContentLayout().getChildAt(i);
                        CheckBox checkbox = (CheckBox) view.findViewById(R.id.checkboxid);
                        if (checkbox != null) {
                            checkbox.setChecked(isChecked);
                        }
                    }
                }
                Log.e("ALL CHECKED", checked.size() + "");
            }
        });
    }


}
