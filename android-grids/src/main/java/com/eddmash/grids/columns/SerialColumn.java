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
import android.widget.TextView;

import java.util.Map;

public class SerialColumn extends Column {
    public SerialColumn(Context context, String name) {
        super(context, name, name);
    }

    @Override
    public View getDataView(int index, Map datum) {
        TextView view = new TextView(getContext());
        view.setText(String.valueOf(index));
        return prepareDataView(view, .2f);
    }

    @Override
    public View getLabelView() {
        TextView view = new TextView(getContext());
        view.setText(String.valueOf(name));
        return prepareHeaderView(view, .2f);
    }

    @Override
    public View getSearchView() {
        return null;
    }
}
