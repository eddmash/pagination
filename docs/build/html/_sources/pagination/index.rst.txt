Pagination Documentation
========================

This pagination library makes it easy to deal with paginating large set of data in a consistent way.

The paginators use :java:ref:`DataListener <DataListener>` to notify you of any changes that
happen e.g

If data for first page is ready for use the
:java:ref:`DataListener <DataListener.onFirstPageDataLoaded(boolean hasMorePages)>`

Paginate data from a List
-------------------------

To paginate a list of records we need to use :java:ref:`ListPaginator <ListPaginator>`.

.. code-block:: java

    List data = "..."
    MyDataListener datalisterner = new MyDataListener();

    ListPaginator pager = new ListPaginator(datalisterner);
    pager.setData(data);
    // set Page size
    pager.setPageSize(20);

    // invoke this method to trigger fetching of next page of data
    pager.fetchNextPageData();

Paginate data from database
---------------------------
To fetch data from the database we need to use :java:ref:`SqlPaginator <SqlPaginator>`.

.. code-block:: java

    // get and instance SQLiteDatabase
    SQLiteDatabase db = "...";

    MyDataListener datalisterner = new MyDataListener();

    SqlPaginator pager = new SqlPaginator(datalisterner, db);
    // set Page size
    pager.setPageSize(20);

    // pass in the sql the paginator will use to fetch data
    String sql = "select * from employee where paid_date = ?";
    String[] = new String[]{"1-12-2018"};
    pager.query(sql, params);

    // invoke this method to trigger fetching of next page of data
    pager.fetchNextPageData();

Create a DataListener
----------------------

A :java:ref:`Paginator <PaginatorInterface>` requires a :java:ref:`DataListener <DataListener>`

Create a DataListener this by implementing the :java:ref:`DataListener <DataListener>` interface.

.. code-block:: java

    public class MyDataListener implements DataListener {

        @Override
        public void onFirstPageDataLoaded(boolean hasMorePages) {

        }

        @Override
        public void onLastPageDataLoaded() {

        }

        @Override
        public void onNextPageDataLoaded() {

        }

        @Override
        public void preDataLoad(boolean hasMorePages) {

        }

        @Override
        public void dataUpdate(List<Map> records) {

        }
    }
