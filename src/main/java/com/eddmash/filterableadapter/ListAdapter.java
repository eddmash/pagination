package com.eddmash.filterableadapter;
/*
* This file is part of the Tools package.
* 
* (c) Eddilbert Macharia (http://eddmash.com)<edd.cowan@gmail.com>
*
* For the full copyright and license information, please view the LICENSE
* file that was distributed with this source code.
*/

import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public abstract class ListAdapter<VH extends RecyclerView.ViewHolder>
        extends BaseFiltarableAdapter<VH> implements SearchableAdaptorInterface {

    public ListAdapter(AppCompatActivity activity) {
        super(activity);
    }

}
