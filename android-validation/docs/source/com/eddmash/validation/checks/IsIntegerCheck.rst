.. java:import:: android.widget EditText

.. java:import:: android.widget Spinner

IsIntegerCheck
==============

.. java:package:: com.eddmash.validation.checks
   :noindex:

.. java:type:: public class IsIntegerCheck extends NotEmptyCheck

   Check if value is an integer.

Constructors
------------
IsIntegerCheck
^^^^^^^^^^^^^^

.. java:constructor:: public IsIntegerCheck(EditText editText, String errorMessage)
   :outertype: IsIntegerCheck

IsIntegerCheck
^^^^^^^^^^^^^^

.. java:constructor:: public IsIntegerCheck(Spinner spinner, String errorMessage)
   :outertype: IsIntegerCheck

Methods
-------
getErrorMsg
^^^^^^^^^^^

.. java:method:: @Override public String getErrorMsg()
   :outertype: IsIntegerCheck

run
^^^

.. java:method:: @Override public boolean run()
   :outertype: IsIntegerCheck

