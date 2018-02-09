package com.eddmash.validation.checks;

import android.widget.EditText;

import java.util.regex.Pattern;

/**
 * Classes that impliment this interface can be used for validation checks.
 */
public interface CheckInterface {


    /**
     * This is where all the validation logic is placed.
     *
     * @return true if validation was a success else return false.
     */
    boolean run();


    /**
     * The error message to use for the failed validations.
     *
     * @return the error message
     */
    String getErrorMsg();

    /**
     * Set the error message on the View being validated.
     * <p>
     * This will invoked when the validation starts, To clear out any previous errors displayed
     * on the View. This is done by passing null as the error message
     * <p>
     * Its again invoked incase validation fails and error message need to be added to the View.
     *
     * @param error the error message that needs to be set on the View being validated
     */
    void setError(String error);

    /**
     * This method is invoked before validation is run, this is to allow clearing any
     * validation indicators on the View.
     */
    void clearError();
}