.. java:import:: java.util List

.. java:import:: java.util Map

DataListener
============

.. java:package:: com.eddmash.pagination
   :noindex:

.. java:type:: public interface DataListener

   Implementation of this class will be notified each time there is a change in the \ :java:ref:`SqlPaginator`\ .

Methods
-------
dataUpdate
^^^^^^^^^^

.. java:method::  void dataUpdate(List<Map> records)
   :outertype: DataListener

   Use this method to update whichever data structure you using to hold the data.

   This is invoked when new data is received. it asynchronously on the doBackground method of an AsyncTask.

   Please note this method is never run on the main UI thread.

   :param records: list of data for the current page.

onFirstPageDataLoaded
^^^^^^^^^^^^^^^^^^^^^

.. java:method::  void onFirstPageDataLoaded(boolean hasMorePages)
   :outertype: DataListener

   Triggered when data for the first page has been loaded and is ready for use.

   The data can be accessed on \ :java:ref:`DataListener.dataUpdate(List)`\  method.

   :param hasMorePages: true if there are more pages to load.

onLastPageDataLoaded
^^^^^^^^^^^^^^^^^^^^

.. java:method::  void onLastPageDataLoaded()
   :outertype: DataListener

   Triggered when data for the last page has been loaded and is ready for use.

   The data can be accessed on \ :java:ref:`DataListener.dataUpdate(List)`\  method.

onNextPageDataLoaded
^^^^^^^^^^^^^^^^^^^^

.. java:method::  void onNextPageDataLoaded()
   :outertype: DataListener

   Invoked when a new list of records has been added to the current records. this is called after fetchNextPageData();

   This method should be run on the main ui thread. on AsyncTask this should be invoked on onPostExecute()

preDataLoad
^^^^^^^^^^^

.. java:method::  void preDataLoad(boolean hasMorePages)
   :outertype: DataListener

   Invoked before the next page is loaded.

   :param hasMorePages: true if there are more pages to load.

