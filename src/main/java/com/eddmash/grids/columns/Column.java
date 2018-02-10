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
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.util.Map;

/**
 * A column that displays data on the grid. this is an implementation of
 * {@link ColumnInterface}
 */
public class Column extends BaseColumn implements SearchableColumnInterface {

    protected final String name;
    protected final Object header;

    public Column(Context context, String valueKey, Object labelText) {
        super(context);
        this.name = valueKey;
        this.header = labelText;
    }

    @Override
    public View getDataView(int index, Map datum) {
        TextView view = new TextView(getContext());
        view.setText(String.valueOf(datum.get(name)));

        return prepareDataView(view, 1);
    }

    @Override
    public View getLabelView() {
        TextView view = new TextView(getContext());

        Log.e(getClass().getSimpleName(), " HEADER " + header);
        view.setText(String.valueOf(header));
        return prepareHeaderView(view, 1);
    }


    @Override
    public View getSearchView() {
        return new EditText(getContext());
    }

}
