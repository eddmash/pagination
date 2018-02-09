.. java:import:: com.eddmash.form.faker PopulatorInterface

PersonProvider
==============

.. java:package:: com.eddmash.form.faker.provider
   :noindex:

.. java:type:: public class PersonProvider extends Provider

Fields
------
EMAIL
^^^^^

.. java:field:: public static final String EMAIL
   :outertype: PersonProvider

FEMALE
^^^^^^

.. java:field:: public static final String FEMALE
   :outertype: PersonProvider

FIRSTNAME
^^^^^^^^^

.. java:field:: public static final String FIRSTNAME
   :outertype: PersonProvider

LASTNAME
^^^^^^^^

.. java:field:: public static final String LASTNAME
   :outertype: PersonProvider

MALE
^^^^

.. java:field:: public static final String MALE
   :outertype: PersonProvider

NAME
^^^^

.. java:field:: public static final String NAME
   :outertype: PersonProvider

PROVIDER_NAME
^^^^^^^^^^^^^

.. java:field:: public static final String PROVIDER_NAME
   :outertype: PersonProvider

Constructors
------------
PersonProvider
^^^^^^^^^^^^^^

.. java:constructor:: public PersonProvider(PopulatorInterface populator)
   :outertype: PersonProvider

PersonProvider
^^^^^^^^^^^^^^

.. java:constructor:: public PersonProvider(PopulatorInterface populator, String format)
   :outertype: PersonProvider

Methods
-------
generate
^^^^^^^^

.. java:method:: @Override public String generate()
   :outertype: PersonProvider

getFirstName
^^^^^^^^^^^^

.. java:method:: public ProviderInterface getFirstName()
   :outertype: PersonProvider

getFirstName
^^^^^^^^^^^^

.. java:method:: public ProviderInterface getFirstName(String gender)
   :outertype: PersonProvider

getFullName
^^^^^^^^^^^

.. java:method:: public ProviderInterface getFullName()
   :outertype: PersonProvider

getFullName
^^^^^^^^^^^

.. java:method:: public ProviderInterface getFullName(String gender)
   :outertype: PersonProvider

getLastName
^^^^^^^^^^^

.. java:method:: public ProviderInterface getLastName()
   :outertype: PersonProvider

getLastName
^^^^^^^^^^^

.. java:method:: public ProviderInterface getLastName(String gender)
   :outertype: PersonProvider

setGender
^^^^^^^^^

.. java:method:: public PersonProvider setGender(String gender)
   :outertype: PersonProvider

setType
^^^^^^^

.. java:method:: public PersonProvider setType(String type)
   :outertype: PersonProvider

