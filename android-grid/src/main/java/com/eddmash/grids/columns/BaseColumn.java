package com.eddmash.grids.columns;
/*
* This file is part of the Tools package.
* 
* (c) Eddilbert Macharia (http://eddmash.com)<edd.cowan@gmail.com>
*
* For the full copyright and license information, please view the LICENSE
* file that was distributed with this source code.
*/

import android.content.Context;
import android.graphics.Typeface;
import android.support.v4.content.ContextCompat;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.eddmash.grids.DataGridView;

/**
 * The base class for all column defination.
 * <p>
 * Learn more about {@link ColumnInterface Columns}
 */
public abstract class BaseColumn implements ColumnInterface {

    private Context context;
    protected DataGridView dataGridView;

    public BaseColumn(Context context) {
        this.context = context;
    }

    @Override
    public Context getContext() {
        return context;
    }

    @Override
    public void setDisplayView(DataGridView dataGridView) {

        this.dataGridView = dataGridView;
    }


    protected TextView prepareDataView(TextView view, float weight) {
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                0,
                ViewGroup.LayoutParams.WRAP_CONTENT,
                weight
        );

        params.setMargins(4, 4, 4, 4);
        view.setLayoutParams(params);

        return view;
    }

    protected TextView prepareHeaderView(TextView view, float weight) {
        view = prepareDataView(view, weight);
        view.setTypeface(view.getTypeface(), Typeface.BOLD);
        view.setTextSize(16);

        view.setTextColor(ContextCompat.getColor(
                getContext(),
                android.R.color.white
        ));
        return view;
    }
}
