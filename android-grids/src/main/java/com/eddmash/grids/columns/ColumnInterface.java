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

import com.eddmash.grids.DataView;

import java.util.Map;

public interface ColumnInterface {
    View makeView(int index, Map datum);

    double getWeight();

    Context getContext();

    View getHeader();

    void setDisplayView(DataView dataView);

}
