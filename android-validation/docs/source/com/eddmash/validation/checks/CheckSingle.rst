.. java:import:: android.widget CompoundButton

.. java:import:: android.widget EditText

.. java:import:: android.widget Spinner

.. java:import:: android.widget TextView

CheckSingle
===========

.. java:package:: com.eddmash.validation.checks
   :noindex:

.. java:type:: public abstract class CheckSingle implements CheckInterface

   Use this when you want to perform lazy execution of validations.

Methods
-------
clearError
^^^^^^^^^^

.. java:method:: @Override public void clearError()
   :outertype: CheckSingle

getValue
^^^^^^^^

.. java:method:: protected String getValue()
   :outertype: CheckSingle

   Gets the value to be validated.

   :return: value of the view

getView
^^^^^^^

.. java:method:: protected TextView getView()
   :outertype: CheckSingle

   Gets the view we are working on.This can be anything that is a child of TextView e.g. EditText, CompoundButton like Checkboxes

   Incase of a spinner you return the selected view like so.

   (TextView) spinner.getSelectedView();

   :return: the View from which to get value to isValid and also on which to set error by invoking \ **view.setError()**\

setError
^^^^^^^^

.. java:method:: @Override public void setError(String error)
   :outertype: CheckSingle

