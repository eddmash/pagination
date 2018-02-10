.. java:import:: android.content Context

.. java:import:: android.graphics Typeface

.. java:import:: android.support.v4.content ContextCompat

.. java:import:: android.view ViewGroup

.. java:import:: android.widget LinearLayout

.. java:import:: android.widget TextView

.. java:import:: com.eddmash.grids DataGridView

BaseColumn
==========

.. java:package:: com.eddmash.grids.columns
   :noindex:

.. java:type:: public abstract class BaseColumn implements ColumnInterface

   The base class for all column defination.

   Learn more about \ :java:ref:`Columns <ColumnInterface>`\

Fields
------
dataGridView
^^^^^^^^^^^^

.. java:field:: protected DataGridView dataGridView
   :outertype: BaseColumn

Constructors
------------
BaseColumn
^^^^^^^^^^

.. java:constructor:: public BaseColumn(Context context)
   :outertype: BaseColumn

Methods
-------
getContext
^^^^^^^^^^

.. java:method:: @Override public Context getContext()
   :outertype: BaseColumn

prepareDataView
^^^^^^^^^^^^^^^

.. java:method:: protected TextView prepareDataView(TextView view, float weight)
   :outertype: BaseColumn

prepareHeaderView
^^^^^^^^^^^^^^^^^

.. java:method:: protected TextView prepareHeaderView(TextView view, float weight)
   :outertype: BaseColumn

setDisplayView
^^^^^^^^^^^^^^

.. java:method:: @Override public void setDisplayView(DataGridView dataGridView)
   :outertype: BaseColumn

