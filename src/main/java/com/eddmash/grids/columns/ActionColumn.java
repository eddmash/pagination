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

/**
 * ActionColumn displays action buttons such as update or delete for each row.
 * find more at {@link ColumnInterface}
 */
public abstract class ActionColumn extends Column {
    private Button view;
    private String name;

    public ActionColumn(Context context, String name) {
        super(context, name, name);
        this.name = name;
    }

    @Override
    public View getDataView(int position, final Map datum) {
        view = new Button(getContext());
        view.setText(name);

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                onItemClick(v, datum);
            }
        });

        return prepareDataView(view, .2f);
    }

    /**
     * Action that will be taken when a
     * {@link ColumnInterface#getDataView(int, Map)} is clicked.
     *
     * @param v
     * @param datum
     */
    protected abstract void onItemClick(View v, Map datum);

    @Override
    public View getSearchView() {
        return null;
    }
}
