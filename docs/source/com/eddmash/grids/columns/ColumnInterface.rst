.. java:import:: android.content Context

.. java:import:: android.view View

.. java:import:: com.eddmash.grids DataGridView

.. java:import:: java.util Map

ColumnInterface
===============

.. java:package:: com.eddmash.grids.columns
   :noindex:

.. java:type:: public interface ColumnInterface

   A column provides a view that will be displayed as a \ :java:ref:`ColumnInterface.getLabelView()`\  that will be rendered on the header row and \ :java:ref:`ColumnInterface.getDataView(int,Map)`\  that will be rendered on each of the data rows.

   A simple column definition refers to an key/value in the data map of the \ :java:ref:`com.eddmash.grids.DataGridView`\  data list.

Methods
-------
getContext
^^^^^^^^^^

.. java:method::  Context getContext()
   :outertype: ColumnInterface

   Context on which \ :java:ref:`com.eddmash.grids.DataGridView`\  is being rendered on .

getDataView
^^^^^^^^^^^

.. java:method::  View getDataView(int index, Map datum)
   :outertype: ColumnInterface

   This is the view that will be used to create to show the for this column on each of its data rows.

   :param index: the id of the row being populated.
   :param datum: data to be render of the row being populated.
   :return: view to render for column on the row being populated.

getLabelView
^^^^^^^^^^^^

.. java:method::  View getLabelView()
   :outertype: ColumnInterface

   Creates a view that will be used on the header row.

setDisplayView
^^^^^^^^^^^^^^

.. java:method::  void setDisplayView(DataGridView dataGridView)
   :outertype: ColumnInterface

   The \ :java:ref:`com.eddmash.grids.DataGridView`\  that will be used to render this column.

   :param dataGridView: \ :java:ref:`com.eddmash.grids.DataGridView`\

