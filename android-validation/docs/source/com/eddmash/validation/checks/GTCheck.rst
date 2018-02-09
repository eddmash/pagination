.. java:import:: android.util Log

.. java:import:: android.widget EditText

.. java:import:: android.widget Spinner

GTCheck
=======

.. java:package:: com.eddmash.validation.checks
   :noindex:

.. java:type:: public class GTCheck extends NotEmptyCheck

   Greater than

Constructors
------------
GTCheck
^^^^^^^

.. java:constructor:: public GTCheck(EditText editText, String errorMessage, int max)
   :outertype: GTCheck

GTCheck
^^^^^^^

.. java:constructor:: public GTCheck(Spinner editText, String errorMessage, int max)
   :outertype: GTCheck

GTCheck
^^^^^^^

.. java:constructor:: public GTCheck(EditText editText, String errorMessage, double max)
   :outertype: GTCheck

GTCheck
^^^^^^^

.. java:constructor:: public GTCheck(Spinner editText, String errorMessage, double max)
   :outertype: GTCheck

Methods
-------
run
^^^

.. java:method:: @Override public boolean run()
   :outertype: GTCheck

