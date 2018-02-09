.. java:import:: com.eddmash.form.faker FakerException

.. java:import:: com.eddmash.form.faker PopulatorInterface

LocationsProvider
=================

.. java:package:: com.eddmash.form.faker.provider
   :noindex:

.. java:type:: public class LocationsProvider extends Provider

Fields
------
ADDRESS
^^^^^^^

.. java:field:: public static final String ADDRESS
   :outertype: LocationsProvider

CITY
^^^^

.. java:field:: public static final String CITY
   :outertype: LocationsProvider

COUNTRY
^^^^^^^

.. java:field:: public static final String COUNTRY
   :outertype: LocationsProvider

Constructors
------------
LocationsProvider
^^^^^^^^^^^^^^^^^

.. java:constructor:: public LocationsProvider(PopulatorInterface populator)
   :outertype: LocationsProvider

LocationsProvider
^^^^^^^^^^^^^^^^^

.. java:constructor:: public LocationsProvider(PopulatorInterface populator, String format)
   :outertype: LocationsProvider

Methods
-------
generate
^^^^^^^^

.. java:method:: @Override public String generate()
   :outertype: LocationsProvider

getCity
^^^^^^^

.. java:method:: public LocationsProvider getCity()
   :outertype: LocationsProvider

