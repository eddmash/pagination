.. java:import:: java.util ArrayList

.. java:import:: java.util HashMap

.. java:import:: java.util List

.. java:import:: java.util Map

MapValue
========

.. java:package:: com.eddmash.form.values
   :noindex:

.. java:type:: public class MapValue implements ValueInterface<Map>

   \ :java:ref:`ValueInterface`\

   Use this if you need to be able to access the map that the value and label where pulled from latter.

Constructors
------------
MapValue
^^^^^^^^

.. java:constructor:: public MapValue(Map item, String labelCol, String valueCol)
   :outertype: MapValue

Methods
-------
fromCollection
^^^^^^^^^^^^^^

.. java:method:: public static List<ValueInterface> fromCollection(List<Map> data, String colKey, String valueKey)
   :outertype: MapValue

   Take list of maps and prepares them for use as values on a spinner.

   :param data:

getItem
^^^^^^^

.. java:method:: @Override public Map getItem()
   :outertype: MapValue

getLabel
^^^^^^^^

.. java:method:: @Override public String getLabel()
   :outertype: MapValue

getValue
^^^^^^^^

.. java:method:: @Override public String getValue()
   :outertype: MapValue

toString
^^^^^^^^

.. java:method:: @Override public String toString()
   :outertype: MapValue

