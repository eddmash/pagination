.. java:import:: android.content Context

.. java:import:: android.support.v4.content ContextCompat

.. java:import:: android.support.v7.app AppCompatActivity

.. java:import:: android.util Log

.. java:import:: android.view View

.. java:import:: android.widget EditText

.. java:import:: android.widget TextView

.. java:import:: java.util Map

Column
======

.. java:package:: com.eddmash.grids.columns
   :noindex:

.. java:type:: public class Column extends BaseColumn implements SearchableColumnInterface

   A column that displays data on the grid. this is an implementation of \ :java:ref:`ColumnInterface`\

Fields
------
header
^^^^^^

.. java:field:: protected final Object header
   :outertype: Column

name
^^^^

.. java:field:: protected final String name
   :outertype: Column

Constructors
------------
Column
^^^^^^

.. java:constructor:: public Column(Context context, String valueKey, Object labelText)
   :outertype: Column

Methods
-------
getDataView
^^^^^^^^^^^

.. java:method:: @Override public View getDataView(int index, Map datum)
   :outertype: Column

getLabelView
^^^^^^^^^^^^

.. java:method:: @Override public View getLabelView()
   :outertype: Column

getSearchView
^^^^^^^^^^^^^

.. java:method:: @Override public View getSearchView()
   :outertype: Column

