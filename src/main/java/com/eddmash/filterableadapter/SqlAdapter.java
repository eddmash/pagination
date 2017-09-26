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

import com.eddmash.pagination.PaginatorInterface;

public abstract class SqlAdapter<VH extends RecyclerView.ViewHolder>
        extends BaseFiltarableAdapter<VH>
        implements SqlPageableAdaptorInterface {

    public SqlAdapter(AppCompatActivity activity) {
        super(activity);
    }

    @Override
    public void addAll(PaginatorInterface paginator) {
        addAll(paginator.getData());
    }
}
