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
 * Check if the value on the view is less than the provided value.
 */
public class LTCheck extends NotEmptyCheck {
    private double min;

    public LTCheck(EditText view, String errorMessage, int min) {
        super(view, errorMessage);
        this.min = (double) min;
    }

    public LTCheck(Spinner view, String errorMessage, int min) {
        super(view, errorMessage);
        this.min = (double) min;
    }

    public LTCheck(EditText view, String errorMessage, double min) {
        super(view, errorMessage);
        this.min =  min;
    }

    public LTCheck(Spinner view, String errorMessage, double min) {
        super(view, errorMessage);
        this.min = min;
    }

    @Override
    public boolean run() {
        boolean status = super.run();
        if (status) {
            try {
                Double value = Double.valueOf(getValue());

                if (value > min) {
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
