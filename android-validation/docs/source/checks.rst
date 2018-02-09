Checks
######

.. contents::
    :depth: 2

Introduction
************

A Check is a class that implements the :java:ref:`CheckInterface <CheckInterface>`. The interface
defines a few methods.

- :java:ref:`run() <CheckInterface.run()>` This is where all the logic of validation is placed.
  This method should return ``true`` if validation succeeds or ``false`` on failure.
- :java:ref:`getErrorMsg() <CheckInterface.getErrorMsg()>` this should the error message to be used
  incase validation fails.
- :java:ref:`setError() <CheckInterface.setError()>` This method is invoked to with the error
  message above as an argument. This method should be used to set the error message on the editText.
- :java:ref:`clearError() <CheckInterface.clearError()>` This method is invoked just be validation
  is run. it should be used to clear any previous error displayed on the validator.

Creating custom validations
***************************

You can create a custom checks by implementing :java:ref:`CheckInterface <CheckInterface>`,
or by extending one of the :ref:`built-in checks<build_in_checks>`.

As an example we create a simple check that checks if a string contains only
alphanumeric characters.

.. code-block:: java

    class ContainsAlphaNumericCheck implements CheckInterface {
        private EditText editText;

        public ContainsAlphaNumericCheck(EditText editText) {
            this.editText = editText;
        }

        @Override
        public boolean run() {
            String value = editText.getText().toString();
            return Pattern.compile("^[a-zA-Z0-9]+$").matcher(value).matches();
        }

        @Override
        public String getErrorMsg() {
            return "String contains an illegal character: it can only contain letters or numbers.";
        }

        @Override
        public void setError(String error) {
            editText.setError(error);
        }

        @Override
        public void clearError() {
            editText.setError(null);
        }
    }

.. _build_in_checks:

Built-in Checks
****************

.. toctree::
    :maxdepth: 2
    :titlesonly:

    com/eddmash/validation/checks/package-index