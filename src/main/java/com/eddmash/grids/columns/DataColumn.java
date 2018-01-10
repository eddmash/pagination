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
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.util.Map;

public class DataColumn extends Column implements SearchableColumnInterface {

    protected final String name;
    protected final Object header;

    public DataColumn(Context context, String name, Object header) {
        super(context);
        this.name = name;
        this.header = header;
    }

    @Override
    public View makeView(int index, Map datum) {
        TextView view = new TextView(getContext());
        view.setText(datum.get(name) + "");
        return view;
    }

    @Override
    public double getWeight() {
        return 1;
    }

    @Override
    public View getHeader() {
        TextView view = new TextView(getContext());
        view.setText(header + "");
        return view;
    }

    @Override
    public View getSearchView() {
        EditText searchView = new EditText(getContext());
        return searchView;
    }

}
