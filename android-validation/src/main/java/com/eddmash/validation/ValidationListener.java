package com.eddmash.validation;
/*
* This file is part of the Tools package.
* 
* (c) Eddilbert Macharia (http://eddmash.com)<edd.cowan@gmail.com>
*
* For the full copyright and license information, please view the LICENSE
* file that was distributed with this source code.
*/

/**
 * Interface definition of callbacks to be invoked when the validation state has changed.
 */
public interface ValidationListener {

    /**
     * Invoked when the validation passed successfully.
     */
    void onValidationSuccess(ValidatorInterface validatorInterface);

    /**
     * Invoked when validation failed
     */
    void onValidationFailed(ValidatorInterface validatorInterface);
}
