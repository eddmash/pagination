package com.eddmash.validation;
/*
* This file is part of the Tools package.
* 
* (c) Eddilbert Macharia (http://eddmash.com)<edd.cowan@gmail.com>
*
* For the full copyright and license information, please view the LICENSE
* file that was distributed with this source code.
*/

import android.app.Activity;
import android.util.Log;

import com.eddmash.validation.checks.CheckInterface;
import com.eddmash.validation.checks.CheckSingle;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

public class Validator implements ValidatorInterface {
    private String _tag = "GENERAL";
    private HashMap<String, List> errors;
    private List<ValidatorInterface> _validators;
    private List<CheckInterface> checkList;

    public Validator() {
        errors = new HashMap<>();
        checkList = new ArrayList<>();
        _validators = new ArrayList<>();
    }

    public Validator(String tag) {
        this();
        this._tag = tag;
    }

    // ============================== VALIDATIONS ========================

    @Override
    public boolean validate() {
        Log.e(getClass().getName(), _tag+" checklist =  " + checkList);
        clearErrors();
        Log.e(getClass().getName(), _tag+" VALIDATING =  " + this.hashCode());
        List viewErros = validateChecks();

        viewErros = new ArrayList(new HashSet(viewErros));
        Collections.sort(viewErros);
        errors.put(_tag, viewErros);

        boolean validators = validateValidators();

        Log.e(getClass().getName(), _tag+" checklist =  " + checkList);
        Log.e(getClass().getName(), _tag+" Errors =  " + errors);
        return validators && viewErros.size() == 0;
    }

    @Override
    public void validate(ValidationListener validationListener) {
        if (validate()) {
            validationListener.onValidationSuccess(this);
        } else {
            validationListener.onValidationFailed(this);
        }
    }

    private List<String> validateChecks() {

        String errMsg;
        List<String> serrors = new ArrayList<>();
        for (CheckInterface checkInterface : checkList) {

            checkInterface.clearError();

            if (!checkInterface.run()) {
                errMsg = checkInterface.getErrorMsg();
                checkInterface.setError(errMsg);
                serrors.add(errMsg);
            }
        }
        return serrors;
    }

    private boolean validateValidators() {
        boolean status = true;
        boolean validatorStatus;

        for (ValidatorInterface v : _validators) {
            v.clearErrors(); //clear errrors first
            validatorStatus = v.validate();
            if (!validatorStatus) {
                errors.putAll(v.getErrors());
                status = false;
            }
        }
        return status;
    }


    // ===================== CHECKS =================

    @Override
    public void addCheck(CheckInterface checkInterface) {
        checkList.add(checkInterface);
    }

    /**
     * {@link ValidatorInterface#addCheck(CheckInterface checkInterface) see }
     */
    @Override
    public void disableCheck(CheckInterface checkInterface) {
        removeCheck(checkInterface);
    }

    @Override
    public void addValidator(ValidatorInterface validator) {
        _validators.add(validator);
    }

    @Override
    public void disableValidator(ValidatorInterface validatorInterface) {
        if (_validators.contains(validatorInterface)) {
            _validators.remove(validatorInterface);
        }
    }

    private void removeCheck(CheckInterface checkInterface) {

        if (checkList.contains(checkInterface)) {
            checkList.remove(checkInterface);
        }
    }

    // ===================== ERRORS =================

    @Override
    public Map<String, List> getErrors() {
        return errors;
    }

    @Override
    public List getErrorsByTag(String tag) {
        if (getErrors().containsKey(tag)) {
            return getErrors().get(tag);
        }
        return new ArrayList();
    }


    @Override
    public void clearErrors() {
        errors.clear();
    }


    // ======================================== MISC =============================

    @Override
    public String toString() {
        return "{ " + _tag + " : " + errors + " }";
    }

}
