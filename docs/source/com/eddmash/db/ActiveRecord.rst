.. java:import:: android.database Cursor

.. java:import:: android.database.sqlite SQLiteDatabase

.. java:import:: android.util Log

.. java:import:: java.util ArrayList

.. java:import:: java.util Arrays

.. java:import:: java.util HashMap

.. java:import:: java.util List

.. java:import:: java.util Map

ActiveRecord
============

.. java:package:: com.eddmash.db
   :noindex:

.. java:type:: public class ActiveRecord

   A simple set of helper methods to query for data on android sqlite database.

   To get the instance of theis Active record use the getInstance method, this method takes just one parameter. an instance of SQLiteDatabase.

   this class is implemented as a singleton meaning only one instance of ActiveRecord ever exists in your application life time.

   NB:: the instance of SQLiteDatabase passed in getInstance method is destroyed once the garbage collector destroys the instance of the ActiveRecord.

Methods
-------
all
^^^

.. java:method:: public List<Map> all(String tableName, String[] queryCols)
   :outertype: ActiveRecord

   Returns an list of maps, where the map represents each record in the database.

   with keys of the map as the column name and values of the map as the values of the respective columns.

   something like this:

   [{id:1, username:ken, age:50}, {id:2, username:matt, age:70}]

   :param tableName:
   :param queryCols:

all
^^^

.. java:method:: public List<Map> all(String tableName)
   :outertype: ActiveRecord

   Returns an list of maps, where the map represents each record in the database.

   with keys of the map as the column name and values of the map as the values of the respective columns.

   something like this:

   [{id:1, username:ken, age:50}, {id:2, username:matt, age:70}]

   :param tableName:

exists
^^^^^^

.. java:method:: public boolean exists(String tableName, String field, String value)
   :outertype: ActiveRecord

   Returns value of the single column requested.

exists
^^^^^^

.. java:method:: public boolean exists(String sql, String[] params)
   :outertype: ActiveRecord

   Returns value of the single column requested.

finalize
^^^^^^^^

.. java:method:: @Override protected void finalize() throws Throwable
   :outertype: ActiveRecord

find
^^^^

.. java:method:: public List<Map> find(String sql, String[] args)
   :outertype: ActiveRecord

   Returns an list of maps, where the map represents each record in the database.

   with keys of the map as the column name and values of the map as the values of the respective columns.

   something like this:

   [{id:1, username:ken, age:50}, {id:2, username:matt, age:70}]

   :param sql:
   :param args:

get
^^^

.. java:method:: public HashMap get(String sql, String[] params)
   :outertype: ActiveRecord

   Returns a Map representing a single record based on the query.

   :param sql:
   :param params: parameters to bind to the query

getDb
^^^^^

.. java:method:: public SQLiteDatabase getDb()
   :outertype: ActiveRecord

   REturn instance of SQLiteDatabase that the activerecord instance is using.

getInstance
^^^^^^^^^^^

.. java:method:: public static ActiveRecord getInstance(SQLiteDatabase database)
   :outertype: ActiveRecord

   Returns an instance of the the activerecord class

   :param database:

getScalarInt
^^^^^^^^^^^^

.. java:method:: public int getScalarInt(String sql, String[] params)
   :outertype: ActiveRecord

