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
import android.widget.Spinner;

/**
 * Check if the value on the view is greater than or equal the provided value.
 */
public class GTECheck extends NotEmptyCheck {
    private double max;

    public GTECheck(EditText view, String errorMessage, int max) {
        super(view, errorMessage);
        this.max = (double) max;
    }

    public GTECheck(Spinner view, String errorMessage, int max) {
        super(view, errorMessage);
        this.max = (double) max;
    }

    public GTECheck(EditText view, String errorMessage, double max) {
        super(view, errorMessage);
        this.max = max;
    }

    public GTECheck(Spinner view, String errorMessage, double max) {
        super(view, errorMessage);
        this.max = max;
    }

    @Override
    public boolean run() {
        boolean status = super.run();
        if (status) {
            try {
                Double value = Double.valueOf(getValue());

                if (value <= max) {
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
