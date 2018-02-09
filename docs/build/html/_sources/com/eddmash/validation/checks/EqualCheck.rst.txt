.. java:import:: android.util Log

.. java:import:: android.widget EditText

.. java:import:: java.util Objects

EqualCheck
==========

.. java:package:: com.eddmash.validation.checks
   :noindex:

.. java:type:: public class EqualCheck extends NotEmptyCheck

   Checks if the provided values is matches to what view has.

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

EqualCheck
^^^^^^^^^^

.. java:constructor:: public EqualCheck(EditText view, String errorMessage, String valToEquate)
   :outertype: EqualCheck

Methods
-------
run
^^^

.. java:method:: @Override public boolean run()
   :outertype: EqualCheck

