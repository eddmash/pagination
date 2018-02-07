.. java:import:: android.util Log

.. java:import:: android.widget EditText

.. java:import:: android.widget Spinner

.. java:import:: android.widget TextView

NotEmptyCheck
=============

.. java:package:: com.eddmash.validation.checks
   :noindex:

.. java:type:: public class NotEmptyCheck extends CheckSingle

   Not empty

Fields
------
errorMessage
^^^^^^^^^^^^

.. java:field:: protected String errorMessage
   :outertype: NotEmptyCheck

Constructors
------------
NotEmptyCheck
^^^^^^^^^^^^^

.. java:constructor:: public NotEmptyCheck(EditText view, String errorMessage)
   :outertype: NotEmptyCheck

NotEmptyCheck
^^^^^^^^^^^^^

.. java:constructor:: public NotEmptyCheck(Spinner spinner, String errorMessage)
   :outertype: NotEmptyCheck

Methods
-------
getErrorMsg
^^^^^^^^^^^

.. java:method:: @Override public String getErrorMsg()
   :outertype: NotEmptyCheck

getValue
^^^^^^^^

.. java:method:: @Override public String getValue()
   :outertype: NotEmptyCheck

getView
^^^^^^^

.. java:method:: @Override protected TextView getView()
   :outertype: NotEmptyCheck

   Gets the view we are working on.

run
^^^

.. java:method:: @Override public boolean run()
   :outertype: NotEmptyCheck

