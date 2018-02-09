.. java:import:: android.database.sqlite SQLiteDatabase

.. java:import:: android.util Log

.. java:import:: com.eddmash.db ActiveRecord

.. java:import:: java.util List

.. java:import:: java.util Map

SqlPaginator
============

.. java:package:: com.eddmash.pagination
   :noindex:

.. java:type:: public class SqlPaginator extends Paginator

   An implimentation of \ :java:ref:`PaginatorInterface`\

Constructors
------------
SqlPaginator
^^^^^^^^^^^^

.. java:constructor:: public SqlPaginator(DataListener dataListener, SQLiteDatabase database)
   :outertype: SqlPaginator

Methods
-------
getNextPageRecords
^^^^^^^^^^^^^^^^^^

.. java:method:: @Override protected List<Map> getNextPageRecords(int startPoint, int endPoint)
   :outertype: SqlPaginator

query
^^^^^

.. java:method:: public void query(String sql, String[] params)
   :outertype: SqlPaginator

