.. java:import:: android.util Log

.. java:import:: java.util ArrayList

.. java:import:: java.util List

.. java:import:: java.util Map

ListPaginator
=============

.. java:package:: com.eddmash.pagination
   :noindex:

.. java:type:: public class ListPaginator extends Paginator

   An implimentation of \ :java:ref:`PaginatorInterface`\

Constructors
------------
ListPaginator
^^^^^^^^^^^^^

.. java:constructor:: public ListPaginator(DataListener dataListener)
   :outertype: ListPaginator

Methods
-------
getNextPageRecords
^^^^^^^^^^^^^^^^^^

.. java:method:: protected List<Map> getNextPageRecords(int startPoint, int endPoint)
   :outertype: ListPaginator

setData
^^^^^^^

.. java:method:: public void setData(List<Map> results)
   :outertype: ListPaginator

