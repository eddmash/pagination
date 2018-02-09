.. java:import:: com.eddmash.form.faker Callback

.. java:import:: com.eddmash.form.faker PopulatorInterface

CoordinatesProvider
===================

.. java:package:: com.eddmash.form.faker.provider
   :noindex:

.. java:type:: public class CoordinatesProvider extends Provider

Fields
------
LATITUDE
^^^^^^^^

.. java:field::  String LATITUDE
   :outertype: CoordinatesProvider

LONGITUDE
^^^^^^^^^

.. java:field::  String LONGITUDE
   :outertype: CoordinatesProvider

Constructors
------------
CoordinatesProvider
^^^^^^^^^^^^^^^^^^^

.. java:constructor:: public CoordinatesProvider(PopulatorInterface populator)
   :outertype: CoordinatesProvider

CoordinatesProvider
^^^^^^^^^^^^^^^^^^^

.. java:constructor:: public CoordinatesProvider(PopulatorInterface populator, String format)
   :outertype: CoordinatesProvider

Methods
-------
generate
^^^^^^^^

.. java:method:: @Override public String generate()
   :outertype: CoordinatesProvider

getLatitude
^^^^^^^^^^^

.. java:method:: public ProviderInterface getLatitude()
   :outertype: CoordinatesProvider

getLongitude
^^^^^^^^^^^^

.. java:method:: public ProviderInterface getLongitude()
   :outertype: CoordinatesProvider

