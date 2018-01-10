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

    /**
     * This does the actual loading of data. The loading should be done asynchronously.
     */
    void fetchNextPageData();


    int getPageCount();
}
