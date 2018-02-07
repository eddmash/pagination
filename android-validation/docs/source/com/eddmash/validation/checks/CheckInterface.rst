.. java:import:: android.widget EditText

.. java:import:: java.util.regex Pattern

CheckInterface
==============

.. java:package:: com.eddmash.validation.checks
   :noindex:

.. java:type:: public interface CheckInterface

Methods
-------
clearError
^^^^^^^^^^

.. java:method::  void clearError()
   :outertype: CheckInterface

   This method is invoked before validation is run, this is to allow clearing any validation indicators on the View.

getErrorMsg
^^^^^^^^^^^

.. java:method::  String getErrorMsg()
   :outertype: CheckInterface

   The error message to use for the failed validations.

   :return: the error message

run
^^^

.. java:method::  boolean run()
   :outertype: CheckInterface

   :return: true if validation was a success else return false.

setError
^^^^^^^^

.. java:method::  void setError(String error)
   :outertype: CheckInterface

   Set the error message on the View being validated.

   This will invoked when the validation starts, To clear out any previous errors displayed on the View. This is done by passing null as the error message

   Its again invoked incase validation fails and error message need to be added to the View.

   :param error: the error message that needs to be set on the View being validated

