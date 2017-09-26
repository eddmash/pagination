package com.eddmash.pagination;
/*
* This file is part of the com.eddmash.pagination package.
* 
* (c) Eddilbert Macharia (http://eddmash.com)<edd.cowan@gmail.com>
*
* For the full copyright and license information, please view the LICENSE
* file that was distributed with this source code.
*/

import java.util.List;
import java.util.Map;

public interface PaginatorInterface {
    void setPageSize(int pageSize);


    List<Map> getData();
    /**
     * Invoked before last page is loaded.
     */
    void onLastPageLoad();

    /**
     * Invoked before first page is loaded.
     * @param hasMorePages indicate if there more pages to be shown.
     */
    void OnFirstPageLoad(boolean hasMorePages);

    /**
     * Invoked before the next page is loaded.
     * @param hasMorePages
     */
    void OnNextPageLoad(boolean hasMorePages);

    void updateAdapter(List<Map> records);

    /**
     * start the process of loading the next page for us
     */
    void nextPage();

    /**
     * Invoked when a new list of records has been added to the current records.
     * this is called after nextPage()
     */
    void onDoneAddingRecords();

    int getPageCount();
}
