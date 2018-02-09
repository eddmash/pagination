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
import android.widget.TextView;

import java.util.regex.Pattern;

/**
 * VAlidate a view against the provided rule or pattern
 */
public class RegexCheck extends CheckSingle {

    private EditText view;
    private final String errorMessage;
    private final Pattern pattern;
    private Spinner spinner;

    public RegexCheck(EditText view, String errorMessage, String rule) {
        this.view = view;
        this.errorMessage = errorMessage;
        pattern = Pattern.compile(rule);
    }

    public RegexCheck(EditText view, String errorMessage, Pattern pattern) {
        this.view = view;
        this.errorMessage = errorMessage;
        this.pattern = pattern;
    }

    public RegexCheck(Spinner spinner, String errorMessage, String rule) {
        this.spinner = spinner;
        this.errorMessage = errorMessage;
        pattern = Pattern.compile(rule);
    }

    public RegexCheck(Spinner spinner, String errorMessage, Pattern pattern) {
        this.spinner = spinner;
        this.errorMessage = errorMessage;
        this.pattern = pattern;
    }


    @Override
    public boolean run() {
        String value = getView().getText() + "";
        return pattern.matcher(value).matches();
    }

    @Override
    public String getErrorMsg() {
        return errorMessage;
    }

    @Override
    public TextView getView() {
        return view;
    }

    @Override
    public String getValue() {
        String value = "";
        if (spinner != null) {
            int selectedItemOfMySpinner = spinner.getSelectedItemPosition();
            value = (String) spinner.getItemAtPosition(selectedItemOfMySpinner);
        } else {
            value = view.getText() + "";
        }
        return value;
    }


}
