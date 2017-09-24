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

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public abstract class ListPaginator implements PaginatorInterface {

    protected int currentPage;
    protected int pageSize;
    private List _records = new ArrayList();
    protected List _paginatedRecords = new ArrayList();
    protected int _pageCount;
    protected String logTag = getClass().getName();
    public int newPageStartPoint;
    /**
     * Monitor if we are currently populating.
     */
    private boolean populating = false;
    protected int _totalRecords;

    ListPaginator() {
        pageSize = 20;
        currentPage = 1;
        _totalRecords = 0;
    }

    @Override
    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public void setStartPage(int startPage) {
        this.currentPage = startPage;
    }

    @Override
    public void nextPage() {
        currentPage++;
        newPageStartPoint = (currentPage - 1) * pageSize;
        int last = pageSize + newPageStartPoint;
        Log.e(logTag, "GETTIMG NEXT PAGE " + currentPage);
        int size = _totalRecords;
        if (size <= last) {
            last = _totalRecords;
            onLastPageLoad();
        }

        //don't createFile another sync when we are currently populating.
        if (size >= newPageStartPoint && !populating) {
            OnNextPageLoad(last <= _records.size());
            new AddMoreTask().execute(newPageStartPoint, last);
        }
    }


    @Override
    public List<Map> getData() {
        return _paginatedRecords;
    }

    public void setData(List<Map> results) {
        Log.e(logTag, "SET DATA" + results.size());
        boolean hasMorePages = false;
        _records = results;
        _totalRecords = _records.size();
        if (_totalRecords < pageSize) {
            _paginatedRecords = _records;
        } else {
            _paginatedRecords = _records.subList(0, pageSize);
        }
        if (_totalRecords > pageSize) {

            Double pageCount = Math.floor(_totalRecords / pageSize);
            _pageCount = pageCount.intValue();
            hasMorePages = true;
        }
        OnFirstPageLoad(hasMorePages);
    }

    @Override
    public int getPageCount() {
        return _pageCount;
    }

    protected int getCurrentPage() {
        return currentPage;
    }

    public String getCurrentPageString() {
        return "( page " + getCurrentPage() + " of " + getPageCount() + ")";
    }

    protected List<Map> getNextPageRecords(int newPageStartPoint, int last) {
        return _records.subList(this.newPageStartPoint, last);
    }

    public int getTotalRecords() {
        return _totalRecords;
    }

    private class AddMoreTask extends AsyncTask<Integer, Void, Void> {

        @Override
        protected Void doInBackground(Integer... params) {
            populating = true;
            int newPageStartPoint = params[0];
            int last = params[1];
            updateAdapter(getNextPageRecords(newPageStartPoint, last));
            return null;
        }

        @Override
        protected void onPostExecute(Void done) {
            populating = false;
            onDoneAddingRecords();
        }
    }
}
