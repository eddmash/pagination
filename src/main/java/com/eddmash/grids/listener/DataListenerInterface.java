package com.eddmash.grids.listener;
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

public interface DataListenerInterface {

    void onLastPageLoaded();

    void onFirstPageLoaded(boolean hasMorePages);

    void preNextPageLoading(boolean hasMorePages);

    void dataUpdate(List<Map> records);

    void onDoneAddingRecords();
}
