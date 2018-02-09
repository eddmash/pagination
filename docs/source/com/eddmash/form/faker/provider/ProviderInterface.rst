ProviderInterface
=================

.. java:package:: com.eddmash.form.faker.provider
   :noindex:

.. java:type:: public interface ProviderInterface

   This class is responsible for generating data.

   The default implementation \ :java:ref:`Provider`\  uses the \ :java:ref:`ProviderInterface.generate()`\  to generate the actual data and then use \ :java:ref:`ProviderInterface.getData()`\  to format the data.

   That is each method on the default providers is used a setter of the type of data to generate.

Methods
-------
generate
^^^^^^^^

.. java:method::  String generate()
   :outertype: ProviderInterface

   This should return the default values the populator will use.

getData
^^^^^^^

.. java:method::  String getData()
   :outertype: ProviderInterface

   Returns the generated value by the provider.

