package com.eddmash.pagination;
/*
* This file is part of the Tools package.
* 
* (c) Eddilbert Macharia (http://eddmash.com)<edd.cowan@gmail.com>
*
* For the full copyright and license information, please view the LICENSE
* file that was distributed with this source code.
*/

import java.util.List;
import java.util.Map;

/**
 * Implementation of this class will be notified each time there is a change in the
 * {@link SqlPaginator}.
 */
public interface DataListener {

    /**
     * Invoked when a new list of records has been added to the current records.
     * this is called after fetchNextPageData();
     * <p>
     * This method should be run on the main ui thread. on AsyncTask this should be invoked on
     * onPostExecute()
     */
    void onNextPageDataLoaded();

    /**
     * Invoked before the next page is loaded.
     *
     * @param hasMorePages true if there are more pages to load.
     */
    void preDataLoad(boolean hasMorePages);


    /**
     * Use this method to update whichever data structure you using to hold the data.
     * <p>
     * This is invoked when new data is received. it asynchronously  on the doBackground method of
     * an AsyncTask.
     * <p>
     * Please note this method is never run on the main UI thread.
     *
     * @param records list of data for the current page.
     */
    void dataUpdate(List<Map> records);

    /**
     * Triggered when data for the last page has been loaded and is ready for use.
     * <p>
     * The data can be accessed on {@link DataListener#dataUpdate(List)} method.
     */
    void onLastPageDataLoaded();

    /**
     * Triggered when data for the first page has been loaded and is ready for use.
     * <p>
     * The data can be accessed on {@link DataListener#dataUpdate(List)} method.
     *
     * @param hasMorePages true if there are more pages to load.
     */
    void onFirstPageDataLoaded(boolean hasMorePages);
}
