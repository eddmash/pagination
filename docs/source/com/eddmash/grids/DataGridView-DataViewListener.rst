.. java:import:: android.content Context

.. java:import:: android.content.res TypedArray

.. java:import:: android.graphics Color

.. java:import:: android.graphics.drawable Drawable

.. java:import:: android.support.annotation ColorInt

.. java:import:: android.support.annotation Nullable

.. java:import:: android.util AttributeSet

.. java:import:: android.util Log

.. java:import:: android.view Gravity

.. java:import:: android.view View

.. java:import:: android.view ViewGroup

.. java:import:: android.widget Button

.. java:import:: android.widget LinearLayout

.. java:import:: android.widget ProgressBar

.. java:import:: android.widget TextView

.. java:import:: com.eddmash.db ActiveRecord

.. java:import:: com.eddmash.grids.columns ActionColumn

.. java:import:: com.eddmash.grids.columns BaseColumn

.. java:import:: com.eddmash.grids.columns ColumnInterface

.. java:import:: com.eddmash.grids.columns Column

.. java:import:: com.eddmash.pagination DataListener

.. java:import:: com.eddmash.pagination ListPaginator

.. java:import:: com.eddmash.pagination Paginator

.. java:import:: com.eddmash.pagination PaginatorInterface

.. java:import:: com.eddmash.pagination SqlPaginator

.. java:import:: java.util ArrayList

.. java:import:: java.util HashMap

.. java:import:: java.util LinkedHashMap

.. java:import:: java.util LinkedList

.. java:import:: java.util List

.. java:import:: java.util Map

DataGridView.DataViewListener
=============================

.. java:package:: com.eddmash.grids
   :noindex:

.. java:type:: public class DataViewListener implements DataListener
   :outertype: DataGridView

Methods
-------
dataUpdate
^^^^^^^^^^

.. java:method:: @Override public void dataUpdate(List<Map> records)
   :outertype: DataGridView.DataViewListener

onFirstPageDataLoaded
^^^^^^^^^^^^^^^^^^^^^

.. java:method:: @Override public void onFirstPageDataLoaded(boolean hasMorePages)
   :outertype: DataGridView.DataViewListener

onLastPageDataLoaded
^^^^^^^^^^^^^^^^^^^^

.. java:method:: @Override public void onLastPageDataLoaded()
   :outertype: DataGridView.DataViewListener

onNextPageDataLoaded
^^^^^^^^^^^^^^^^^^^^

.. java:method:: @Override public void onNextPageDataLoaded()
   :outertype: DataGridView.DataViewListener

preDataLoad
^^^^^^^^^^^

.. java:method:: @Override public void preDataLoad(boolean hasMorePages)
   :outertype: DataGridView.DataViewListener

