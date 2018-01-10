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

import com.eddmash.grids.DataView;

public abstract class Column implements ColumnInterface {

    private Context context;
    protected DataView dataView;

    public Column(Context context) {
        this.context = context;
    }

    @Override
    public Context getContext() {
        return context;
    }

    @Override
    public void setDisplayView(DataView dataView) {

        this.dataView = dataView;
    }
}
