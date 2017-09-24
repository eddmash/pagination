package com.eddmash.pagination;
/*
* This file is part of the com.eddmash.pagination package.
* 
* (c) Eddilbert Macharia (http://eddmash.com)<edd.cowan@gmail.com>
*
* For the full copyright and license information, please view the LICENSE
* file that was distributed with this source code.
*/

import android.os.AsyncTask;
import android.util.Log;

import com.eddmash.querer.ActiveRecord;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

public abstract class SqlPaginator extends ListPaginator {

    private String _sql;
    private String[] _params;
    private ActiveRecord activeRecord;

    public SqlPaginator(ActiveRecord activeRecord){
        this.activeRecord = activeRecord;
    }

    public void query(String sql, String[] params) {
        this._sql = sql;
        Log.e("LERER", Arrays.toString(params));
        this._params = params;

        _totalRecords = activeRecord.getScalarInt("select count(*) from ("+sql+")", params);
        Log.e(getClass().getName(), "COUNT TOATL "+_totalRecords);
        if (_totalRecords > pageSize) {
           sql = sql+ " limit "+pageSize;
        }

        new LoadTask().execute(sql);
    }

    @Override
    protected List<Map> getNextPageRecords(int newPageStartPoint, int last) {
        Log.e("LMITI", newPageStartPoint+" to "+last);
        String sql = _sql+ " limit "+pageSize+" offset "+ newPageStartPoint;
        return activeRecord.find(sql, _params);
    }

    private class LoadTask extends AsyncTask<String, Void, Void>{

        @Override
        protected Void doInBackground(String... params) {
            Log.e("POLOPO", params[0]+" "+Arrays.toString(_params));
            _paginatedRecords = activeRecord.find(params[0], _params);
            Log.e(getClass().getName(), "DATA "+ _paginatedRecords);
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            boolean hasMorePages = false;
            if (_totalRecords > pageSize) {
                Double pageCount = Math.floor(_totalRecords / pageSize);
                _pageCount = pageCount.intValue();
                hasMorePages = true;
            }
            OnFirstPageLoad(hasMorePages);
        }
    }
}
