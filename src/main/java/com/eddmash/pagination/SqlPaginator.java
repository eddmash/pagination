package com.eddmash.pagination;
/*
* This file is part of the com.eddmash.pagination package.
* 
* (c) Eddilbert Macharia (http://eddmash.com)<edd.cowan@gmail.com>
*
* For the full copyright and license information, please view the LICENSE
* file that was distributed with this source code.
*/

import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.eddmash.db.ActiveRecord;

import java.util.List;
import java.util.Map;

/**
 * An implimentation of {@link PaginatorInterface}
 */
public class SqlPaginator extends Paginator {

    private String _sql;
    private String[] _params;
    private ActiveRecord activeRecord;

    public SqlPaginator(DataListener dataListener, SQLiteDatabase database) {
        super(dataListener);

        this.activeRecord = ActiveRecord.getInstance(database);
    }

    public void query(String sql, String[] params) {
        _sql = sql;
        _params = params;
        _currentRecordsCounter = pageSize;
        _totalRecords = activeRecord.getScalarInt("select count(*) from (" + sql + ")", params);
        Log.e(getClass().getName(), "COUNT TOTAL " + _totalRecords);

        new LoadDataTask(this).execute(0, pageSize);
    }

    @Override
    protected List<Map> getNextPageRecords(int startPoint, int endPoint) {
        String sql = _sql + " limit " + pageSize + " offset " + startPoint;
        Log.e(getClass().getSimpleName(), startPoint + " to " + endPoint
                + " (" + sql + ")");
        return activeRecord.find(sql, _params);
    }
}
