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

public class SerialColumn extends DataColumn {
    public SerialColumn(Context context, String name) {
        super(context, name, name);
    }

    @Override
    public View makeView(int index, Map datum) {
        TextView view = (TextView) super.makeView(index, datum);
        view.setText(index + "");
        return view;
    }

    @Override
    public double getWeight() {
        return .4;
    }

    @Override
    public View getSearchView() {
        return null;
    }
}
