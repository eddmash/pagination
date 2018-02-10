Pagination Documentation
========================

This pagination library makes it easy to deal with paginating large set of data in a consistent way.

.. image:: pagination.png

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
        public void onLastPageDataLoaded() {
            // we dont need the load more button if we an on the last page of out data
            loadMoreBtn.setVisibility(View.GONE);
            adapter.notifyDataSetChanged();
        }

        @Override
        public void onFirstPageDataLoaded(boolean hasMorePages) {
            // update the load more button
            nextBtn(hasMorePages);
            adapter.notifyDataSetChanged();
        }

        @Override
        public void onNextPageDataLoaded() {
            adapter.notifyDataSetChanged();
        }

        void nextBtn(boolean hasMorePages) {
            String msg = getString(R.string.nextPageBtn) + " " + paginator.getCurrentPageString();
            loadMoreBtn.setText(msg);
            if (hasMorePages) {
                loadMoreBtn.setVisibility(View.VISIBLE);
            } else {
                loadMoreBtn.setVisibility(View.GONE);
            }
        }

        @Override
        public void dataUpdate(List<Map> records) {
            // update the adapter with more data as we get them.
            adapter.update(records);
        }

        @Override
        public void preDataLoad(boolean hasMorePages) {
            nextBtn(hasMorePages);
        }
    }
