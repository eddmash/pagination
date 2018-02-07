.. java:import:: android.util Log

.. java:import:: android.widget EditText

EqualCheck
==========

.. java:package:: com.eddmash.validation.checks
   :noindex:

.. java:type:: public class EqualCheck extends NotEmptyCheck

   Equal to

Constructors
------------
EqualCheck
^^^^^^^^^^

.. java:constructor:: public EqualCheck(EditText view, String errorMessage, int valToEquate)
   :outertype: EqualCheck

EqualCheck
^^^^^^^^^^

.. java:constructor:: public EqualCheck(EditText view, String errorMessage, double valToEquate)
   :outertype: EqualCheck

Methods
-------
run
^^^

.. java:method:: @Override public boolean run()
   :outertype: EqualCheck

