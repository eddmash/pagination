AnyCheck
========

.. java:package:: com.eddmash.validation.checks
   :noindex:

.. java:type:: public class AnyCheck extends CheckCompound

   This is an implementation of \ :java:ref:`CheckCompound`\ .

   Checks if at least one of the checks passed validation.

   If the no checks are provided i.e. checkList is empty, validation will always fail.

Constructors
------------
AnyCheck
^^^^^^^^

.. java:constructor:: public AnyCheck(String errorMessage)
   :outertype: AnyCheck

Methods
-------
run
^^^

.. java:method:: @Override public boolean run()
   :outertype: AnyCheck

