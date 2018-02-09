package com.eddmash.validation.checks;
/*
* This file is part of the com.eddmash.validation package.
* 
* (c) Eddilbert Macharia (http://eddmash.com)<edd.cowan@gmail.com>
*
* For the full copyright and license information, please view the LICENSE
* file that was distributed with this source code.
*/

import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

/**
 * This is the base class most of the validation Checks that relate to a single view.
 * <p>
 * {@link CheckCompound } if you like to validate multiple checks as one unit.
 */
public abstract class CheckSingle implements CheckInterface {


    /**
     * Gets the view we are working on.This can be anything that is a child of TextView
     * e.g. EditText, CompoundButton like Checkboxes
     * <p>
     * Incase of a spinner you return the selected view like so.
     * <p>
     * <code>
     * (TextView) spinner.getSelectedView();
     * </code>
     *
     * @return the View from which to get value to isValid and also on which to set error by
     * invoking <strong>view.setError()</strong>
     */
    protected TextView getView() {
        return null;
    }

    /**
     * Gets the value to be validated.
     *
     * @return value of the view
     */
    protected String getValue() {
        if (getView() == null) {
            return null;
        }
        return (String) getView().getText();
    }


    @Override
    public void setError(String error) {
        if (getView() != null) {

            getView().setError(error);
        }
    }

    @Override
    public void clearError() {
        getView().setError(null);
    }
}
