ValidationListener
==================

.. java:package:: com.eddmash.validation
   :noindex:

.. java:type:: public interface ValidationListener

   Interface definition for callbacks to be invoked when the validation state has changed.

Methods
-------
onValidationFailed
^^^^^^^^^^^^^^^^^^

.. java:method::  void onValidationFailed(ValidatorInterface validatorInterface)
   :outertype: ValidationListener

   Invoked when validation failed

onValidationSuccess
^^^^^^^^^^^^^^^^^^^

.. java:method::  void onValidationSuccess(ValidatorInterface validatorInterface)
   :outertype: ValidationListener

   Invoked when the validation passed successfully.

