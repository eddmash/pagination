package com.eddmash.validation;
/*
* This file is part of the Tools package.
* 
* (c) Eddilbert Macharia (http://eddmash.com)<edd.cowan@gmail.com>
*
* For the full copyright and license information, please view the LICENSE
* file that was distributed with this source code.
*/

import com.eddmash.validation.checks.CheckInterface;
import com.eddmash.validation.checks.CheckSingle;

import java.util.List;
import java.util.Map;

/**
 * Validators should implement this interface
 * <p>
 * {@link Validator}
 */
public interface ValidatorInterface {
    /**
     * Does the actual validation.
     *
     * @return boolean true of valid
     */
    boolean validate();

    /**
     * Does the actual validation.
     *
     * @param validationListener listener that is
     */
    void validate(ValidationListener validationListener);

    /**
     * Adds validation checks to be enforced by a validator
     *
     * @param checkInterface
     */
    void addCheck(CheckInterface checkInterface);

    /**
     * disable validation check
     *
     * @param checkInterface the validation check to disable.
     */
    void disableCheck(CheckInterface checkInterface);

    /**
     * Add another validator to validated at the time this one is being validated.
     *
     * @param validatorInterface the validatorInterface object
     */
    void addValidator(ValidatorInterface validatorInterface);

    /**
     * Disable the validator from being validated any more.
     *
     * @param validatorInterface validatorInterface object
     */
    void disableValidator(ValidatorInterface validatorInterface);

    /**
     * Returns all error that the validator found as a HashMap.
     * with the key being tags if your passed in any when creating the validator otherwise all
     * errors afre returned under the tag NON_SPECIFIC
     * <p>
     * the value of the HashMap consists an ArrayList of errors that relate to each tag
     *
     * @return Map
     */
    Map<String, List> getErrors();

    /**
     * Gets a list of errors for a specific tag.
     *
     * @param tag
     * @return
     */
    List getErrorsByTag(String tag);

    /**
     * Clear all the errors from the validator.
     * <p>
     * maybe use when you have already run the validation onces and want to
     * run the validation again using the same ValidatorInterface instance
     */
    void clearErrors();
}
