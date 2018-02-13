package com.eddmash.pagination;
/*
* This file is part of the androidcomponents package.
* 
* (c) Eddilbert Macharia (http://eddmash.com)<edd.cowan@gmail.com>
*
* For the full copyright and license information, please view the LICENSE
* file that was distributed with this source code.
*/

import java.util.List;
import java.util.Map;

/**
 * Class that implement this interface help you manage paginated data can data that’s split
 * across several pages.
 * <p>
 * {@link ListPaginator } this is used to resolve data that is not alot, i.e. data that can be
 * held inthe memory with little to no cost on the app, this is usually data held in a List.
 * <p>
 * With large data that is usally stored in the database the {@link SqlPaginator} comes in handy,
 * this paginator does not hold any data within it.
 * <p>
 * It only fetches the data and provides this data to you, its upto you to know how to store or
 * display this data in an efficient manner.
 */
public interface PaginatorInterface {
    /**
     * The number of records to display per page.
     *
     * @param pageSize
     */
    void setPageSize(int pageSize);

    /**
     * This does the actual loading of data. The loading should be done asynchronously.
     */
    void fetchNextPageData();


    /**
     * The total number of pages.
     *
     * @return
     */
    int getPageCount();

    /**
     * The total number of records.
     *
     * @return
     */
    int getTotalRecords();
}
