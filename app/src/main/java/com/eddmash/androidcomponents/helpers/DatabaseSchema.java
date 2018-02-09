/*
 * This file is part of the Fivmszm package.
 * <p>
 * (c) Eddilbert Macharia (http://eddmash.com)<edd.cowan@gmail.com>
 * <p>
 * For the full copyright and license information, please view the LICENSE
 * file that was distributed with this source code.
 */
package com.eddmash.androidcomponents.helpers;

import android.provider.BaseColumns;


public final class DatabaseSchema {

    public static abstract class Coffees implements BaseColumns {
        public static final String TABLE_NAME = "coffees";

    }
    public static abstract class SalePeople implements BaseColumns {
        public static final String TABLE_NAME = "salespeople";

    }


}
