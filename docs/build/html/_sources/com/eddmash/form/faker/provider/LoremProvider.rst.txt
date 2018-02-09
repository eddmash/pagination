.. java:import:: com.eddmash.form.faker FakerException

.. java:import:: com.eddmash.form.faker PopulatorInterface

.. java:import:: org.apache.commons.lang3 StringUtils

LoremProvider
=============

.. java:package:: com.eddmash.form.faker.provider
   :noindex:

.. java:type:: public class LoremProvider extends Provider

Constructors
------------
LoremProvider
^^^^^^^^^^^^^

.. java:constructor:: public LoremProvider(PopulatorInterface populator)
   :outertype: LoremProvider

LoremProvider
^^^^^^^^^^^^^

.. java:constructor:: public LoremProvider(PopulatorInterface populator, String format)
   :outertype: LoremProvider

Methods
-------
generate
^^^^^^^^

.. java:method:: @Override public String generate()
   :outertype: LoremProvider

getWord
^^^^^^^

.. java:method:: public ProviderInterface getWord()
   :outertype: LoremProvider

getWords
^^^^^^^^

.. java:method:: public LoremProvider getWords()
   :outertype: LoremProvider

getWords
^^^^^^^^

.. java:method:: public LoremProvider getWords(int noOfWords)
   :outertype: LoremProvider

