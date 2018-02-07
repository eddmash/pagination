package com.eddmash.validation.checks;
/*
* This file is part of the com.eddmash.validation package.
* 
* (c) Eddilbert Macharia (http://eddmash.com)<edd.cowan@gmail.com>
*
* For the full copyright and license information, please view the LICENSE
* file that was distributed with this source code.
*/

import android.widget.EditText;
import android.widget.Spinner;

/**
 * Check if value is an integer.
 */
public class IsIntegerCheck extends NotEmptyCheck {

    public IsIntegerCheck(EditText view, String errorMessage) {
        super(view, errorMessage);
    }

    public IsIntegerCheck(Spinner spinner, String errorMessage) {
        super(spinner, errorMessage);
    }

    @Override
    public boolean run() {
        boolean valid = super.run();
        if (valid) {
            try {
                Integer.parseInt(getValue());
            } catch (Exception e) {
                setError(errorMessage);
                valid = false;
            }
        }
        return valid;
    }


    @Override
    public String getErrorMsg() {
        return errorMessage;
    }
}
