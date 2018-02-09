package com.eddmash.validation.checks;
/*
* This file is part of the com.eddmash.validation package.
* 
* (c) Eddilbert Macharia (http://eddmash.com)<edd.cowan@gmail.com>
*
* For the full copyright and license information, please editText the LICENSE
* file that was distributed with this source code.
*/

import android.util.Log;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Objects;

/**
 * Ensure the view if not blank.
 */
public class NotEmptyCheck extends CheckSingle {

    private EditText editText;
    private Spinner spinner;
    private CompoundButton compoundButton;

    protected String errorMessage;

    public NotEmptyCheck(EditText editText, String errorMessage) {
        this.editText = editText;
        this.errorMessage = errorMessage;
    }

    public NotEmptyCheck(Spinner spinner, String errorMessage) {
        this.spinner = spinner;
        this.errorMessage = errorMessage;
    }

    public NotEmptyCheck(CompoundButton compoundButton, String errorMessage) {
        this.compoundButton = compoundButton;
        this.errorMessage = errorMessage;
    }

    @Override
    public boolean run() {
        Log.e(getClass().getSimpleName(), errorMessage + " :: " + getValue().trim().isEmpty());
        return !getValue().trim().isEmpty();
    }

    /**
     * Gets the editText we are working on.
     *
     * @return
     */
    @Override
    protected TextView getView() {
        TextView v = editText;
        if (spinner != null) {
            v = (TextView) spinner.getSelectedView();
        }

        if (compoundButton != null) {
            v = compoundButton;
        }
        return v;
    }

    @Override
    public String getValue() {
        String value = "";

        if (spinner != null) {
            int selectedItemOfMySpinner = spinner.getSelectedItemPosition();
            if (spinner.getItemAtPosition(selectedItemOfMySpinner) == null) {
                value = "";
            } else {
                value = String.valueOf(spinner.getItemAtPosition(selectedItemOfMySpinner));
            }
        } else if (compoundButton != null) {
            if (compoundButton.isChecked()) {
                return "true";
            }
            return "";
        } else {
            value = String.valueOf(editText.getText());
        }
        return value;
    }

    @Override
    public String getErrorMsg() {
        return errorMessage;
    }

}
