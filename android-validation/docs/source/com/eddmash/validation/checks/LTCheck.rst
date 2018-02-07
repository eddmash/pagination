.. java:import:: android.util Log

.. java:import:: android.widget EditText

.. java:import:: android.widget Spinner

LTCheck
=======

.. java:package:: com.eddmash.validation.checks
   :noindex:

.. java:type:: public class LTCheck extends NotEmptyCheck

   Less than

Constructors
------------
LTCheck
^^^^^^^

.. java:constructor:: public LTCheck(EditText view, String errorMessage, int min)
   :outertype: LTCheck

LTCheck
^^^^^^^

.. java:constructor:: public LTCheck(Spinner view, String errorMessage, int min)
   :outertype: LTCheck

LTCheck
^^^^^^^

.. java:constructor:: public LTCheck(EditText view, String errorMessage, double min)
   :outertype: LTCheck

LTCheck
^^^^^^^

.. java:constructor:: public LTCheck(Spinner view, String errorMessage, double min)
   :outertype: LTCheck

Methods
-------
run
^^^

.. java:method:: @Override public boolean run()
   :outertype: LTCheck

