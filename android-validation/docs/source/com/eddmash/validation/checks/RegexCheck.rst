.. java:import:: android.widget EditText

.. java:import:: android.widget Spinner

.. java:import:: android.widget TextView

.. java:import:: java.util.regex Pattern

RegexCheck
==========

.. java:package:: com.eddmash.validation.checks
   :noindex:

.. java:type:: public class RegexCheck extends CheckSingle

   VAlidate againsta a regex pattern

Constructors
------------
RegexCheck
^^^^^^^^^^

.. java:constructor:: public RegexCheck(EditText view, String errorMessage, String rule)
   :outertype: RegexCheck

RegexCheck
^^^^^^^^^^

.. java:constructor:: public RegexCheck(EditText view, String errorMessage, Pattern pattern)
   :outertype: RegexCheck

RegexCheck
^^^^^^^^^^

.. java:constructor:: public RegexCheck(Spinner spinner, String errorMessage, String rule)
   :outertype: RegexCheck

RegexCheck
^^^^^^^^^^

.. java:constructor:: public RegexCheck(Spinner spinner, String errorMessage, Pattern pattern)
   :outertype: RegexCheck

Methods
-------
getErrorMsg
^^^^^^^^^^^

.. java:method:: @Override public String getErrorMsg()
   :outertype: RegexCheck

getValue
^^^^^^^^

.. java:method:: @Override public String getValue()
   :outertype: RegexCheck

getView
^^^^^^^

.. java:method:: @Override public TextView getView()
   :outertype: RegexCheck

run
^^^

.. java:method:: @Override public boolean run()
   :outertype: RegexCheck

