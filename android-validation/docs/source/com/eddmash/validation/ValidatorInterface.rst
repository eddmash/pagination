.. java:import:: com.eddmash.validation.checks CheckInterface

.. java:import:: com.eddmash.validation.checks CheckSingle

.. java:import:: java.util List

.. java:import:: java.util Map

ValidatorInterface
==================

.. java:package:: com.eddmash.validation
   :noindex:

.. java:type:: public interface ValidatorInterface

Methods
-------
addCheck
^^^^^^^^

.. java:method::  void addCheck(CheckInterface checkInterface)
   :outertype: ValidatorInterface

   Adds validation checks to be enforced by a validator

   :param checkInterface:

addValidator
^^^^^^^^^^^^

.. java:method::  void addValidator(ValidatorInterface validatorInterface)
   :outertype: ValidatorInterface

   Add another validator to validated at the time this one is being validated.

   :param validatorInterface: the validatorInterface object

clearErrors
^^^^^^^^^^^

.. java:method::  void clearErrors()
   :outertype: ValidatorInterface

   Clear all the errors from the validator. maybe use when you have already run the validation onces and want to run the validation again using the same ValidatorInterface instance

disableCheck
^^^^^^^^^^^^

.. java:method::  void disableCheck(CheckInterface checkInterface)
   :outertype: ValidatorInterface

   disable validation check

   :param checkInterface: the validation check to disable.

disableValidator
^^^^^^^^^^^^^^^^

.. java:method::  void disableValidator(ValidatorInterface validatorInterface)
   :outertype: ValidatorInterface

   Disable the validator from being validated any more.

   :param validatorInterface: validatorInterface object

getErrors
^^^^^^^^^

.. java:method::  Map<String, List> getErrors()
   :outertype: ValidatorInterface

   Returns all error that the validator found as a HashMap. with the key being tags if your passed in any when creating the validator otherwise all errors afre returned under the tag NON_SPECIFIC

   the value of the HashMap consists an ArrayList of errors that relate to each tag

   :return: Map

getErrorsByTag
^^^^^^^^^^^^^^

.. java:method::  List getErrorsByTag(String tag)
   :outertype: ValidatorInterface

   Gets a list of errors for a specific tag.

   :param tag:

validate
^^^^^^^^

.. java:method::  boolean validate()
   :outertype: ValidatorInterface

   Does the actual validation.

   :return: boolean true of valid

validate
^^^^^^^^

.. java:method::  void validate(ValidationListener validationListener)
   :outertype: ValidatorInterface

   Does the actual validation.

   :param validationListener: listener that is

