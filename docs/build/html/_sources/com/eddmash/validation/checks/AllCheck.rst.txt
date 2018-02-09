AllCheck
========

.. java:package:: com.eddmash.validation.checks
   :noindex:

.. java:type:: public class AllCheck extends CheckCompound

   This is an implementation of \ :java:ref:`CheckCompound`\ .

   Ensures all are validation checks are valid.

   If the no checks are provided i.e. checkList is empty, validation will always pass for this check.

Constructors
------------
AllCheck
^^^^^^^^

.. java:constructor:: public AllCheck(String errorMessage)
   :outertype: AllCheck

Methods
-------
run
^^^

.. java:method:: @Override public boolean run()
   :outertype: AllCheck

