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
 * Checks if the value is a float value
 */
public class IsFloatCheck extends NotEmptyCheck {


    public IsFloatCheck(EditText view, String errorMessage) {
        super(view, errorMessage);
    }

    public IsFloatCheck(Spinner spinner, String errorMessage) {
        super(spinner, errorMessage);
    }

    @Override
    public boolean run() {
        boolean valid = super.run();
        if (valid) {
            try {
                Float.parseFloat(getValue());
            } catch (Exception e) {
                valid = false;
            }
        }
        return valid;
    }
}
