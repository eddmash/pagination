package com.eddmash.validation.checks;
/*
* This file is part of the Ziamismalawi package.
* 
* (c) Eddilbert Macharia (http://eddmash.com)<edd.cowan@gmail.com>
*
* For the full copyright and license information, please view the LICENSE
* file that was distributed with this source code.
*/

import android.widget.CompoundButton;
import android.widget.TextView;

/**
 * Checks if the provided compound button is checked.
 * <p>
 * Compound buttons include checkbox, radios etc
 */
public class IsCheckedCheck implements CheckInterface {

    private CompoundButton compoundButton;
    private String errorMessage;

    public IsCheckedCheck(CompoundButton compoundButton, String errorMessage) {
        this.compoundButton = compoundButton;
        this.errorMessage = errorMessage;
    }

    public IsCheckedCheck(CompoundButton compoundButton) {
        this(compoundButton, null);
    }

    @Override
    public boolean run() {
        return compoundButton.isChecked();
    }

    @Override
    public String getErrorMsg() {
        return errorMessage;
    }

    @Override
    public void setError(String error) {
        compoundButton.setError(error);
    }

    @Override
    public void clearError() {
        compoundButton.setError(null);
    }
}
