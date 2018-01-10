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

public interface DataListener {

    /**
     * Invoked when a new list of records has been added to the current records.
     * this is called after fetchNextPageData();
     * <p>
     * This method should be run on the main ui thread. on AsyncTask this should be invoked on
     * onPostExecute()
     *
     * @param isLastPage true only if the data that has been loaded is the last page
     */
    void onDataLoaded(boolean isLastPage);

    /**
     * Invoked before the next page is loaded.
     *
     * @param hasMorePages
     */
    void preNextPageDataLoad(boolean hasMorePages);


    /**
     * Use this method to update whichever data structure you using to hold the data.
     * <p>
     * This is invoked when new data is received. it asynchronously  on the doBackground method of
     * an AsyncTask.
     *
     * @param records
     */
    void dataUpdate(List<Map> records);
}
