.. java:import:: android.widget CompoundButton

.. java:import:: android.widget TextView

IsCheckedCheck
==============

.. java:package:: com.eddmash.validation.checks
   :noindex:

.. java:type:: public class IsCheckedCheck implements CheckInterface

Constructors
------------
IsCheckedCheck
^^^^^^^^^^^^^^

.. java:constructor:: public IsCheckedCheck(CompoundButton compoundButton, String errorMessage)
   :outertype: IsCheckedCheck

IsCheckedCheck
^^^^^^^^^^^^^^

.. java:constructor:: public IsCheckedCheck(CompoundButton compoundButton)
   :outertype: IsCheckedCheck

Methods
-------
clearError
^^^^^^^^^^

.. java:method:: @Override public void clearError()
   :outertype: IsCheckedCheck

getErrorMsg
^^^^^^^^^^^

.. java:method:: @Override public String getErrorMsg()
   :outertype: IsCheckedCheck

run
^^^

.. java:method:: @Override public boolean run()
   :outertype: IsCheckedCheck

setError
^^^^^^^^

.. java:method:: @Override public void setError(String error)
   :outertype: IsCheckedCheck

