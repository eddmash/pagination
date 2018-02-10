.. java:import:: android.app Activity

.. java:import:: android.content Context

.. java:import:: android.util Log

.. java:import:: android.view View

.. java:import:: android.widget CheckBox

.. java:import:: android.widget CompoundButton

.. java:import:: com.eddmash.grids R

.. java:import:: java.util ArrayList

.. java:import:: java.util List

.. java:import:: java.util Map

CheckColumn
===========

.. java:package:: com.eddmash.grids.columns
   :noindex:

.. java:type:: public class CheckColumn extends BaseColumn

   This renders a column of checkable columns which can be used to performa a check all or check some required fields.

   learn more about \ :java:ref:`Columns <ColumnInterface>`\

Constructors
------------
CheckColumn
^^^^^^^^^^^

.. java:constructor:: public CheckColumn(Context context)
   :outertype: CheckColumn

CheckColumn
^^^^^^^^^^^

.. java:constructor:: public CheckColumn(Context context, String name)
   :outertype: CheckColumn

Methods
-------
getCheckedCheckboxes
^^^^^^^^^^^^^^^^^^^^

.. java:method:: public List<CheckBox> getCheckedCheckboxes()
   :outertype: CheckColumn

getCheckedData
^^^^^^^^^^^^^^

.. java:method:: public List<Map> getCheckedData()
   :outertype: CheckColumn

getDataView
^^^^^^^^^^^

.. java:method:: @Override public View getDataView(int index, Map datum)
   :outertype: CheckColumn

getLabelView
^^^^^^^^^^^^

.. java:method:: @Override public View getLabelView()
   :outertype: CheckColumn

