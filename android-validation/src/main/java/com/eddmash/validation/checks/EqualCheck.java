package com.eddmash.validation.checks;
/*
* This file is part of the com.eddmash.validation package.
* 
* (c) Eddilbert Macharia (http://eddmash.com)<edd.cowan@gmail.com>
*
* For the full copyright and license information, please view the LICENSE
* file that was distributed with this source code.
*/

import android.util.Log;
import android.widget.EditText;

/**
 * Equal to
 */
public class EqualCheck extends NotEmptyCheck {
    private double valToEquate;

    public EqualCheck(EditText view, String errorMessage, int valToEquate) {
        super(view, errorMessage);
        this.valToEquate = (double) valToEquate;
    }

    public EqualCheck(EditText view, String errorMessage, double valToEquate) {
        super(view, errorMessage);
        this.valToEquate = valToEquate;
    }

    @Override
    public boolean run() {
        boolean status = super.run();
        if (status) {
            try {
                Double value = Double.valueOf(getValue());

                if (value == valToEquate) {
                    return false;
                }
            } catch (Exception e) {
                Log.e(getClass().getName(), "ERROR :: " + e.getMessage());
                e.printStackTrace();
                return false;
            }
        }
        return status;
    }
}
