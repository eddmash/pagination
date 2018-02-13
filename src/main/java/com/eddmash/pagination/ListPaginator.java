package com.eddmash.pagination;
/*
* This file is part of the androidcomponents package.
* 
* (c) Eddilbert Macharia (http://eddmash.com)<edd.cowan@gmail.com>
*
* For the full copyright and license information, please view the LICENSE
* file that was distributed with this source code.
*/

import android.util.Log;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * An implimentation of {@link PaginatorInterface}
 */
public class ListPaginator extends Paginator {

    private List<Map> _records = new ArrayList<>();


    public ListPaginator(DataListener dataListener) {
        super(dataListener);
    }

    public void setData(List<Map> results) {
        Log.e(logTag, "SET DATA" + results.size());
        _records = results;
        _totalRecords = _records.size();
        _currentRecordsCounter = pageSize;

        new LoadDataTask(this).execute(0, pageSize);
    }


    protected List<Map> getNextPageRecords(int startPoint, int endPoint) {
        return _records.subList(startPoint, endPoint);
    }

}
