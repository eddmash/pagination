package com.eddmash.pagination;
/*
* This file is part of the Tools package.
* 
* (c) Eddilbert Macharia (http://eddmash.com)<edd.cowan@gmail.com>
*
* For the full copyright and license information, please view the LICENSE
* file that was distributed with this source code.
*/

import android.os.AsyncTask;
import android.util.Log;

import java.util.List;
import java.util.Map;

public abstract class Paginator implements PaginatorInterface {

    protected int pageSize;
    /**
     * Monitor if we are currently populating.
     */
    private boolean populating = false;
    protected int currentPage;
    public int newPageStartPoint;
    protected int _totalRecords;
    protected int _currentRecordsCounter;
    protected boolean isLastPage = false;
    protected int _pageCount = 1;
    protected String logTag = getClass().getName();
    protected DataListener dataListener;

    public Paginator(DataListener dataListener) {

        this.dataListener = dataListener;
        pageSize = 20;
        currentPage = 1;
        _totalRecords = 0;
    }

    @Override
    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    @Override
    public void fetchNextPageData() {
        currentPage++;
        newPageStartPoint = (currentPage - 1) * pageSize;
        int last = pageSize + newPageStartPoint;
        Log.e(logTag, "GETTING NEXT PAGE " + currentPage);
        int size = _totalRecords;
        if (size <= last) {
            last = _totalRecords;
            isLastPage = true;
        }

        //don't create another sync when we are currently populating.
        if (size >= newPageStartPoint && !populating) {
            dataListener.prePageDataLoad(last <= _totalRecords);
            new LoadDataTask().execute(newPageStartPoint, last);
        }
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

    protected abstract List<Map> getNextPageRecords(int startPoint, int endPoint);

    public int getTotalRecords() {
        return _totalRecords;
    }

    protected class LoadDataTask extends AsyncTask<Integer, Void, Void> {

        @Override
        protected Void doInBackground(Integer... params) {
            populating = true;
            int startPoint = params[0];
            int endPoint = params[1];
            List<Map> data = getNextPageRecords(startPoint, endPoint);
            _currentRecordsCounter = _currentRecordsCounter + data.size();
            dataListener.dataUpdate(data);
            return null;
        }

        @Override
        protected void onPostExecute(Void done) {
            populating = false;
            dataListener.onDataLoaded(currentPage == 1, isLastPage);
        }
    }

}
