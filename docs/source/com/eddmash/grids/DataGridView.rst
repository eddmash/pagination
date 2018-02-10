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

DataGridView
============

.. java:package:: com.eddmash.grids
   :noindex:

.. java:type:: public class DataGridView extends LinearLayout

   DataGridView can be used to display a list or table of data records providing features like pagination.

   Its takes a List of Maps that contains data and renders each row using a set of \ :java:ref:`ColumnInterface`\  presenting data in the form of a table.

   The minimal code needed to use DataGridView is as follows:

   .. parsed-literal::

      dataGridView = (DataGridView) findViewById(R.id.data_view);
      dataGridView.setPageSize(3);
      List data = "...;
      dataGridView.setData(data);

   Its also possible to override which columns are used in the grid and customize those columns as one wishes. Assuming in the data provided to the gridview looks like this [{"first_name":"jake", "age":"4"}, {"first_name":"joan", "age":"6"}, ] The ealier example will render both the firstname and age column on the grid we can tell the gridview to only render the firstname by \ :java:ref:`DataGridView.setColumns(Map)`\  as shown below:

   .. parsed-literal::

      Map cols = new HashMap();
      cols.put("first_name", new Column(this,"first_name","First Name"));
      dataGridView.setColumns(cols);

Fields
------
LEFT
^^^^

.. java:field:: public static final boolean LEFT
   :outertype: DataGridView

RIGHT
^^^^^

.. java:field:: public static final boolean RIGHT
   :outertype: DataGridView

data
^^^^

.. java:field:: protected List<Map> data
   :outertype: DataGridView

Constructors
------------
DataGridView
^^^^^^^^^^^^

.. java:constructor:: public DataGridView(Context context)
   :outertype: DataGridView

DataGridView
^^^^^^^^^^^^

.. java:constructor:: public DataGridView(Context context, AttributeSet attrs)
   :outertype: DataGridView

DataGridView
^^^^^^^^^^^^

.. java:constructor:: public DataGridView(Context context, AttributeSet attrs, int defStyleAttr)
   :outertype: DataGridView

Methods
-------
addColumn
^^^^^^^^^

.. java:method:: public void addColumn(BaseColumn col, boolean position)
   :outertype: DataGridView

   Add extra columns to the dataview

   :param col:
   :param position: true to add column at the beginning, false to add to the right

addToolbarView
^^^^^^^^^^^^^^

.. java:method:: public void addToolbarView(View view)
   :outertype: DataGridView

getColumns
^^^^^^^^^^

.. java:method:: public Map<String, ColumnInterface> getColumns()
   :outertype: DataGridView

   Returns of columns to use on this grid.

   :return: grid columns.

getContentLayout
^^^^^^^^^^^^^^^^

.. java:method:: public LinearLayout getContentLayout()
   :outertype: DataGridView

getCurrentPageString
^^^^^^^^^^^^^^^^^^^^

.. java:method:: protected String getCurrentPageString()
   :outertype: DataGridView

getDataListener
^^^^^^^^^^^^^^^

.. java:method:: protected DataListener getDataListener()
   :outertype: DataGridView

getFooterLayout
^^^^^^^^^^^^^^^

.. java:method:: protected LinearLayout getFooterLayout()
   :outertype: DataGridView

getNextPageBtn
^^^^^^^^^^^^^^

.. java:method:: protected Button getNextPageBtn()
   :outertype: DataGridView

isStripped
^^^^^^^^^^

.. java:method:: public void isStripped(boolean isStripped)
   :outertype: DataGridView

makeDataRows
^^^^^^^^^^^^

.. java:method:: protected void makeDataRows()
   :outertype: DataGridView

makeFooterRow
^^^^^^^^^^^^^

.. java:method:: protected void makeFooterRow()
   :outertype: DataGridView

makeHeaderRow
^^^^^^^^^^^^^

.. java:method:: protected void makeHeaderRow()
   :outertype: DataGridView

makeToolbarRow
^^^^^^^^^^^^^^

.. java:method:: protected void makeToolbarRow()
   :outertype: DataGridView

setColumns
^^^^^^^^^^

.. java:method:: public void setColumns(Map attributesLabel)
   :outertype: DataGridView

   Determine how and which columns of your data will be displayed by passing them here.

   This overrides the default implementation of using all the columns in you data.

   this method accepts a map in the following form {"gender}

   :param attributesLabel: a map

setData
^^^^^^^

.. java:method:: public void setData(List<Map> data)
   :outertype: DataGridView

setHeaderColor
^^^^^^^^^^^^^^

.. java:method:: public void setHeaderColor(int headerColor)
   :outertype: DataGridView

setPageSize
^^^^^^^^^^^

.. java:method:: public void setPageSize(int pageSize)
   :outertype: DataGridView

setPaginator
^^^^^^^^^^^^

.. java:method:: public void setPaginator(PaginatorInterface paginator, LazyResolver lazyResolver)
   :outertype: DataGridView

setQuery
^^^^^^^^

.. java:method:: public void setQuery(ActiveRecord activeRecord, String sql, String[] params)
   :outertype: DataGridView

setStripColor
^^^^^^^^^^^^^

.. java:method:: public void setStripColor(int stripColor)
   :outertype: DataGridView

