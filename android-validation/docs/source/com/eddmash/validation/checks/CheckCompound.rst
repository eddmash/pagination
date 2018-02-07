.. java:import:: android.view ViewGroup

.. java:import:: com.eddmash.validation ValidatorInterface

.. java:import:: java.util ArrayList

.. java:import:: java.util List

CheckCompound
=============

.. java:package:: com.eddmash.validation.checks
   :noindex:

.. java:type:: public abstract class CheckCompound extends CheckSingle

Fields
------
checkList
^^^^^^^^^

.. java:field:: protected List<CheckInterface> checkList
   :outertype: CheckCompound

Constructors
------------
CheckCompound
^^^^^^^^^^^^^

.. java:constructor:: public CheckCompound(String errorMessage)
   :outertype: CheckCompound

Methods
-------
addCheck
^^^^^^^^

.. java:method:: public void addCheck(CheckInterface checkInterface)
   :outertype: CheckCompound

addChecks
^^^^^^^^^

.. java:method:: public void addChecks(List<CheckInterface> validationChecks)
   :outertype: CheckCompound

clearError
^^^^^^^^^^

.. java:method:: @Override public void clearError()
   :outertype: CheckCompound

disableCheck
^^^^^^^^^^^^

.. java:method:: public void disableCheck(CheckInterface checkInterface)
   :outertype: CheckCompound

disableChecks
^^^^^^^^^^^^^

.. java:method:: public void disableChecks(List<CheckInterface> validationChecks)
   :outertype: CheckCompound

getErrorMsg
^^^^^^^^^^^

.. java:method:: @Override public String getErrorMsg()
   :outertype: CheckCompound

setError
^^^^^^^^

.. java:method:: @Override public void setError(String error)
   :outertype: CheckCompound

