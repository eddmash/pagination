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

import com.eddmash.grids.DataGridView;

import java.util.Map;

/**
 * A column provides a view that will be displayed as a
 * {@link ColumnInterface#getLabelView()} that will be rendered on the header
 * row and {@link ColumnInterface#getDataView(int, Map)} that will be rendered
 * on each of the data rows.
 * <p>
 * A simple column definition refers to an key/value in the data map of
 * the {@link com.eddmash.grids.DataGridView} data list.
 */
public interface ColumnInterface {

    /**
     * This is the view that will be used to create to show the for this column
     * on each of its data rows.
     *
     * @param index the id of the row being populated.
     * @param datum data to be render of the row being populated.
     * @return view to render for column on the row being populated.
     */
    View getDataView(int index, Map datum);

    /**
     * Creates a view that will be used on the header row.
     *
     * @return
     */
    View getLabelView();

    /**
     * Context on which {@link com.eddmash.grids.DataGridView} is being rendered
     * on .
     *
     * @return
     */
    Context getContext();

    /**
     * The {@link com.eddmash.grids.DataGridView} that will be used to render
     * this column.
     *
     * @param dataGridView {@link com.eddmash.grids.DataGridView}
     */
    void setDisplayView(DataGridView dataGridView);
}
