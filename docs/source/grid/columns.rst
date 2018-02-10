Grid Columns
============

A column is a class that implements the :java:ref:`Columns <ColumnInterface>`

The library comes with :java:ref:`Columns <ColumnInterface>` but you can
create custom columns to suit your needs.

In this example we create and EditActionColumn which basically displays a editbutton which if a user
clicks the edit activity is launched.

This examples use the the :java:ref:`ActionColumn <ActionColumn>`.

.. code-block:: java

    public class EditActionColumn extends ActionColumn {
        public EditActionColumn(Context context, String name) {
            super(context, name);
        }

        @Override
        protected void onItemClick(View v, Map datum) {
            Intent intent = new Intent(getContext(), MainActivity.class);
            intent.putExtra("record_id", String.valueOf(datum.get("id")));
            startActivity(intent);
        }
    }


.. toctree::
    :titlesonly:
    :maxdepth: 1

    ../com/eddmash/grids/columns/package-index