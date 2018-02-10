.. java:import:: android.content Context

.. java:import:: android.view View

.. java:import:: android.widget TextView

.. java:import:: java.util Map

SerialColumn
============

.. java:package:: com.eddmash.grids.columns
   :noindex:

.. java:type:: public class SerialColumn extends Column

Constructors
------------
SerialColumn
^^^^^^^^^^^^

.. java:constructor:: public SerialColumn(Context context, String name)
   :outertype: SerialColumn

Methods
-------
getDataView
^^^^^^^^^^^

.. java:method:: @Override public View getDataView(int index, Map datum)
   :outertype: SerialColumn

getLabelView
^^^^^^^^^^^^

.. java:method:: @Override public View getLabelView()
   :outertype: SerialColumn

getSearchView
^^^^^^^^^^^^^

.. java:method:: @Override public View getSearchView()
   :outertype: SerialColumn

