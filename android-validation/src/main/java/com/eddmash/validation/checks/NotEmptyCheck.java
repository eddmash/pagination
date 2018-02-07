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
import android.widget.TextView;

/**
 * Not empty
 */
public class NotEmptyCheck extends CheckSingle {

    private EditText view;
    private Spinner spinner;

    protected String errorMessage;

    public NotEmptyCheck(EditText view, String errorMessage) {
        this.view = view;
        this.errorMessage = errorMessage;
    }

    public NotEmptyCheck(Spinner spinner, String errorMessage) {
        this.spinner = spinner;
        this.errorMessage = errorMessage;
    }

    @Override
    public boolean run() {
        return !getValue().isEmpty();
    }

    /**
     * Gets the view we are working on.
     * @return
     */
    @Override
    protected TextView getView() {
        TextView v = view;
        if (spinner != null) {
            v = (TextView) spinner.getSelectedView();
        }
        return v;
    }

    @Override
    public String getValue() {
        String value = "";
        if (spinner != null) {
            int selectedItemOfMySpinner = spinner.getSelectedItemPosition();
            Object val = spinner.getItemAtPosition(selectedItemOfMySpinner);
            if (val!=null){
                value = val.toString();
            }
        } else {
            value = view.getText() + "";
        }
        return value;
    }

    @Override
    public String getErrorMsg() {
        return errorMessage;
    }

}
