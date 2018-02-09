.. java:import:: java.util List

.. java:import:: java.util Map

PaginatorInterface
==================

.. java:package:: com.eddmash.pagination
   :noindex:

.. java:type:: public interface PaginatorInterface

   Class that implement this interface help you manage paginated data can data that’s split across several pages.

   \ :java:ref:`ListPaginator`\  this is used to resolve data that is not alot, i.e. data that can be held inthe memory with little to no cost on the app, this is usually data held in a List.

   With large data that is usally stored in the database the \ :java:ref:`SqlPaginator`\  comes in handy, this paginator does not hold any data within it.

   It only fetches the data and provides this data to you, its upto you to know how to store or display this data in an efficient manner.

Methods
-------
fetchNextPageData
^^^^^^^^^^^^^^^^^

.. java:method::  void fetchNextPageData()
   :outertype: PaginatorInterface

   This does the actual loading of data. The loading should be done asynchronously.

getPageCount
^^^^^^^^^^^^

.. java:method::  int getPageCount()
   :outertype: PaginatorInterface

   The total number of pages.

getTotalRecords
^^^^^^^^^^^^^^^

.. java:method::  int getTotalRecords()
   :outertype: PaginatorInterface

   The total number of records.

setPageSize
^^^^^^^^^^^

.. java:method::  void setPageSize(int pageSize)
   :outertype: PaginatorInterface

   The number of records to display per page.

   :param pageSize:

