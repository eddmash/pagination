package com.eddmash.validation.checks;
/*
* This file is part of the Ziamismalawi package.
* 
* (c) Eddilbert Macharia (http://eddmash.com)<edd.cowan@gmail.com>
*
* For the full copyright and license information, please view the LICENSE
* file that was distributed with this source code.
*/

/**
 * This is an implementation of {@link CheckCompound}.
 * <p>
 * Checks if at least one of the checks passed validation.
 * <p>
 * If the no checks are provided i.e. checkList is empty, validation will always fail.
 */
public class AnyCheck extends CheckCompound {

    public AnyCheck(String errorMessage) {
        super(errorMessage);
    }

    @Override
    public boolean run() {
        boolean status = false;

        for (CheckInterface check : checkList) {

            if (check.run()) {
                status = true;
                break;
            }
        }
        return status;
    }
}
