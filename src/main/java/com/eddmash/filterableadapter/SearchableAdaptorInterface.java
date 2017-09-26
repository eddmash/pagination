package com.eddmash.filterableadapter;
/*
* This file is part of the com.eddmash.filterableadapter package.
* 
* (c) Eddilbert Macharia (http://eddmash.com)<edd.cowan@gmail.com>
*
* For the full copyright and license information, please view the LICENSE
* file that was distributed with this source code.
*/

import java.util.List;
import java.util.Map;

interface SearchableAdaptorInterface {
    void clear();
    void filter(String searchTerm);
    void addAll(List<Map> map);
    void add(Map map);
    void update(List<Map> maps);
}
