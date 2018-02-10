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

/**
 * This renders a column of checkable columns which can be used to performa a
 * check all or check some required fields.
 * <p>
 * learn more about {@link ColumnInterface Columns}
 */
public class CheckColumn extends BaseColumn {
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
    public View getDataView(int index, Map datum) {
        view = new CheckBox(getContext());
        view.setTag(datum);
        view.setId(R.id.checkboxid);
        view.setOnCheckedChangeListener(
                new CompoundButton.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(CompoundButton buttonView,
                                                 boolean isChecked) {
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
    public View getLabelView() {
        CheckBox header = new CheckBox(getContext());

        if (name != null) {
            header.setText(name);
        }
        header.setOnCheckedChangeListener(
                new CompoundButton.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(CompoundButton buttonView,
                                                 boolean isChecked) {
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
                if (dataGridView != null) {
                    for (int i = 0;
                         i < dataGridView.getContentLayout().getChildCount();
                         i++) {
                        View view = dataGridView.getContentLayout()
                                                .getChildAt(i);
                        CheckBox checkbox = (CheckBox) view
                                .findViewById(R.id.checkboxid);
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
