.. java:import:: android.util Log

.. java:import:: android.widget EditText

.. java:import:: android.widget Spinner

GTECheck
========

.. java:package:: com.eddmash.validation.checks
   :noindex:

.. java:type:: public class GTECheck extends NotEmptyCheck

   Greater than or equal

Constructors
------------
GTECheck
^^^^^^^^

.. java:constructor:: public GTECheck(EditText editText, String errorMessage, int max)
   :outertype: GTECheck

GTECheck
^^^^^^^^

.. java:constructor:: public GTECheck(Spinner editText, String errorMessage, int max)
   :outertype: GTECheck

GTECheck
^^^^^^^^

.. java:constructor:: public GTECheck(EditText editText, String errorMessage, double max)
   :outertype: GTECheck

GTECheck
^^^^^^^^

.. java:constructor:: public GTECheck(Spinner editText, String errorMessage, double max)
   :outertype: GTECheck

Methods
-------
run
^^^

.. java:method:: @Override public boolean run()
   :outertype: GTECheck

