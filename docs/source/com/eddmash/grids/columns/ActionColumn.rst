.. java:import:: android.content Context

.. java:import:: android.view View

.. java:import:: android.widget Button

.. java:import:: java.util Map

ActionColumn
============

.. java:package:: com.eddmash.grids.columns
   :noindex:

.. java:type:: public abstract class ActionColumn extends Column

   ActionColumn displays action buttons such as update or delete for each row. find more at \ :java:ref:`ColumnInterface`\

Constructors
------------
ActionColumn
^^^^^^^^^^^^

.. java:constructor:: public ActionColumn(Context context, String name)
   :outertype: ActionColumn

Methods
-------
getDataView
^^^^^^^^^^^

.. java:method:: @Override public View getDataView(int position, Map datum)
   :outertype: ActionColumn

getSearchView
^^^^^^^^^^^^^

.. java:method:: @Override public View getSearchView()
   :outertype: ActionColumn

onItemClick
^^^^^^^^^^^

.. java:method:: protected abstract void onItemClick(View v, Map datum)
   :outertype: ActionColumn

   Action that will be taken when a \ :java:ref:`ColumnInterface.getDataView(int,Map)`\  is clicked.

   :param v:
   :param datum:

