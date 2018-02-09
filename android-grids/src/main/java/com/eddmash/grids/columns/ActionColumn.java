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
import android.widget.Button;

import java.util.Map;

public abstract class ActionColumn extends DataColumn {
    private Button view;
    private String name;

    public ActionColumn(Context context, String name) {
        super(context, name, name);
        this.name = name;
    }

    public View makeView(int position, final Map datum) {
        view = new Button(getContext());
        view.setText(name);

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                onItemClick(v, datum);
            }
        });

        return view;
    }

    protected abstract void onItemClick(View v, Map datum);

    @Override
    public double getWeight() {
        return .5;
    }

    @Override
    public View getSearchView() {
        return null;
    }
}
