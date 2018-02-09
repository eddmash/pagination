.. java:import:: android.os AsyncTask

.. java:import:: android.util Log

.. java:import:: java.util List

.. java:import:: java.util Map

Paginator
=========

.. java:package:: com.eddmash.pagination
   :noindex:

.. java:type:: public abstract class Paginator implements PaginatorInterface

Fields
------
_currentRecordsCounter
^^^^^^^^^^^^^^^^^^^^^^

.. java:field:: protected int _currentRecordsCounter
   :outertype: Paginator

_pageCount
^^^^^^^^^^

.. java:field:: protected int _pageCount
   :outertype: Paginator

_totalRecords
^^^^^^^^^^^^^

.. java:field:: protected int _totalRecords
   :outertype: Paginator

currentPage
^^^^^^^^^^^

.. java:field:: protected int currentPage
   :outertype: Paginator

dataListener
^^^^^^^^^^^^

.. java:field:: protected DataListener dataListener
   :outertype: Paginator

isLastPage
^^^^^^^^^^

.. java:field:: protected boolean isLastPage
   :outertype: Paginator

logTag
^^^^^^

.. java:field:: protected String logTag
   :outertype: Paginator

newPageStartPoint
^^^^^^^^^^^^^^^^^

.. java:field:: public int newPageStartPoint
   :outertype: Paginator

pageSize
^^^^^^^^

.. java:field:: protected int pageSize
   :outertype: Paginator

populating
^^^^^^^^^^

.. java:field:: protected boolean populating
   :outertype: Paginator

   Monitor if we are currently populating.

Constructors
------------
Paginator
^^^^^^^^^

.. java:constructor:: public Paginator(DataListener dataListener)
   :outertype: Paginator

Methods
-------
fetchNextPageData
^^^^^^^^^^^^^^^^^

.. java:method:: @Override public void fetchNextPageData()
   :outertype: Paginator

getCurrentPage
^^^^^^^^^^^^^^

.. java:method:: protected int getCurrentPage()
   :outertype: Paginator

getCurrentPageString
^^^^^^^^^^^^^^^^^^^^

.. java:method:: public String getCurrentPageString()
   :outertype: Paginator

getNextPageRecords
^^^^^^^^^^^^^^^^^^

.. java:method:: protected abstract List<Map> getNextPageRecords(int startPoint, int endPoint)
   :outertype: Paginator

getPageCount
^^^^^^^^^^^^

.. java:method:: @Override public int getPageCount()
   :outertype: Paginator

getTotalRecords
^^^^^^^^^^^^^^^

.. java:method:: public int getTotalRecords()
   :outertype: Paginator

setPageSize
^^^^^^^^^^^

.. java:method:: @Override public void setPageSize(int pageSize)
   :outertype: Paginator

