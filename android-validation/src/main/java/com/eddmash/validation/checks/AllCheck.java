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
 * Ensures all are validation checks are valid.
 * <p>
 * If the no checks are provided i.e. checkList is empty, validation will always pass for this
 * check.
 */
public class AllCheck extends CheckCompound {

    public AllCheck(String errorMessage) {
        super(errorMessage);
    }

    @Override
    public boolean run() {
        boolean status = true;

        for (CheckInterface check : checkList) {

            if (!check.run()) {
                status = false;
                break;
            }
        }
        return status;
    }
}
