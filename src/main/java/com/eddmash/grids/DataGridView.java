package com.eddmash.grids;
/*
* This file is part of the Tools package.
* 
* (c) Eddilbert Macharia (http://eddmash.com)<edd.cowan@gmail.com>
*
* For the full copyright and license information, please view the LICENSE
* file that was distributed with this source code.
*/

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.support.annotation.ColorInt;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.eddmash.db.ActiveRecord;
import com.eddmash.grids.columns.ActionColumn;
import com.eddmash.grids.columns.BaseColumn;
import com.eddmash.grids.columns.ColumnInterface;
import com.eddmash.grids.columns.Column;
import com.eddmash.pagination.DataListener;
import com.eddmash.pagination.ListPaginator;
import com.eddmash.pagination.Paginator;
import com.eddmash.pagination.PaginatorInterface;
import com.eddmash.pagination.SqlPaginator;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * DataGridView can be used to display a list or table of data records providing
 * features like pagination.
 * <p>
 * Its takes a List of Maps that contains data and renders each row using a set
 * of {@link ColumnInterface} presenting data in the form of a table.
 * <p>
 * The minimal code needed to use DataGridView is as follows:
 * <p>
 * <pre>
 *      dataGridView = (DataGridView) findViewById(R.id.data_view);
 *      dataGridView.setPageSize(3);
 *      List<Map> data = "...;
 *      dataGridView.setData(data);
 * </pre>
 * <p>
 * Its also possible to override which columns are used in the grid and
 * customize those columns as one wishes.
 *
 * Assuming in the data provided to the gridview looks like this
 *
 * [{"first_name":"jake", "age":"4"}, {"first_name":"joan", "age":"6"}, ]
 *
 * The ealier example will render both the firstname and age column on the grid
 * we can tell the gridview to only render the firstname by
 * {@link DataGridView#setColumns(Map)} as shown below:
 *
 * <pre>
 *     Map cols = new HashMap();
 *     cols.put("first_name", new Column(this,"first_name","First Name"));
 *     dataGridView.setColumns(cols);
 * </pre>
 *
 */
public class DataGridView extends LinearLayout {

    public static final boolean LEFT = true;
    public static final boolean RIGHT = false;
    private LinearLayout footerLayout;
    protected List<Map> data;
    private Map displayItems;
    private PaginatorInterface paginator;
    private Button nextPageBtn;
    private int pageSize;
    private boolean isStripped;
    private int headerColor;
    private int stripColor;

    private DataViewListener dataListener;
    private LinearLayout contentLayout;
    private ProgressBar progressBar;
    private int totalRowsAdded = 0;

    private String ACTIONSLABEL = "Actions";
    private Map headers;
    private List<BaseColumn> leftColumns;
    private List<BaseColumn> rightColumns;
    private LinearLayout topProgressBar;
    private LinearLayout headerLayout;
    private View infoBar;
    private List<View> toolbarViews = new ArrayList<>();

    public DataGridView(Context context) {
        this(context, null);
    }

    public DataGridView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public DataGridView(Context context, @Nullable AttributeSet attrs, int
            defStyleAttr) {
        super(context, attrs, defStyleAttr);
        data = new ArrayList<>();
        headers = new HashMap();
        rightColumns = new ArrayList<>();
        leftColumns = new ArrayList<>();
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
        );

        contentLayout = new LinearLayout(context);
        contentLayout.setOrientation(LinearLayout.VERTICAL);
        contentLayout.setLayoutParams(params);
        footerLayout = new LinearLayout(context);
        footerLayout.setLayoutParams(params);
        footerLayout.setOrientation(LinearLayout.VERTICAL);
        headerLayout = new LinearLayout(context);
        headerLayout.setLayoutParams(params);
        headerLayout.setOrientation(LinearLayout.VERTICAL);

        topProgressBar = new LinearLayout(getContext());
        topProgressBar.setOrientation(LinearLayout.VERTICAL);
        topProgressBar.setGravity(Gravity.CENTER_HORIZONTAL);
        topProgressBar.setLayoutParams(params);

        ProgressBar progressBar = new ProgressBar(getContext());
        progressBar.setIndeterminate(true);
        progressBar.setLayoutParams(new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
        ));
        Drawable progressDrawable = progressBar.getIndeterminateDrawable()
                                               .mutate();
        progressDrawable.setColorFilter(
                Color.YELLOW,
                android.graphics.PorterDuff.Mode.SRC_IN
        );
        progressBar.setIndeterminateDrawable(progressDrawable);
        TextView message = new TextView(getContext());
        message.setText("Loading initial page");
        message.setTextColor(Color.YELLOW);
        message.setLayoutParams(new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
        ));
        message.setGravity(Gravity.CENTER_HORIZONTAL);
        topProgressBar.addView(progressBar);
        topProgressBar.addView(message);

        styles(context, attrs);
        addView(topProgressBar);
        addView(headerLayout);
        addView(contentLayout);
        addView(footerLayout);
    }


    // =============== COLUMNS =================================

    /**
     * Add extra columns to the dataview
     *
     * @param col
     * @param position true to add column at the beginning, false to add to the
     *                 right
     */
    public void addColumn(BaseColumn col, boolean position) {
        col.setDisplayView(this);
        if (position) {
            leftColumns.add(col);
        } else {
            rightColumns.add(col);
        }

    }

    /**
     * Determine how and which columns of your data will be displayed by passing
     * them here.
     * <p>
     * This overrides the default implementation of using all the columns in you
     * data.
     * <p>
     * this method accepts a map in the following form
     * {"gender}
     *
     * @param attributesLabel a map
     */
    public void setColumns(Map attributesLabel) {

        data = new ArrayList<>();
        headers = new HashMap();
        rightColumns = new ArrayList<>();
        leftColumns = new ArrayList<>();
        this.displayItems = attributesLabel;
        getColumns(); // this prepare the columns early enough
    }

    public void addToolbarView(View view) {
        toolbarViews.add(view);
    }

    // =============== CONFIGURABLES =================================

    public void setStripColor(@ColorInt int stripColor) {
        this.stripColor = stripColor;
    }

    public void setHeaderColor(@ColorInt int headerColor) {
        this.headerColor = headerColor;
    }

    public void isStripped(boolean isStripped) {

        this.isStripped = isStripped;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    protected LinearLayout getFooterLayout() {
        return footerLayout;
    }

    public LinearLayout getContentLayout() {
        return contentLayout;
    }


    // ========================= HELPERS ===================================

    private void styles(Context context, AttributeSet attrs) {
        TypedArray a = context
                .obtainStyledAttributes(attrs, R.styleable.DataGridView);
        try {
            setHeaderColor(a.getColor(
                    R.styleable.DataGridView_headerColor,
                    Color.GRAY
            ));
            setStripColor(a.getColor(
                    R.styleable.DataGridView_stripColor,
                    Color.LTGRAY
            ));
            isStripped(a.getBoolean(R.styleable.DataGridView_isStrip, true));
            setPageSize(a.getInteger(R.styleable.DataGridView_pageSize, 10));

        } finally {
            a.recycle();
        }
    }

    private void log(String s) {
        Log.e(getClass().getSimpleName(), "<::DISPLAY::>" + s);
    }

    private String capitalizeFirstLetter(String original) {
        if (original == null || original.length() == 0) {
            return original;
        }
        return original.substring(0, 1).toUpperCase() + original.substring(1);
    }

    private Map getHeaders() {
        if (headers == null) {
            Map datum = data.get(0);
            for (Object key : datum.keySet()) {
                headers.put(
                        key,
                        capitalizeFirstLetter(key.toString().replace("_", " "))
                );
            }
        } else {
            for (String key : getColumns().keySet()) {

                headers.put(key, (getColumns().get(key)).getLabelView());
            }
        }
        return headers;
    }

    /**
     * Make the header cell for the action column.
     *
     * @param value
     * @return
     */
    private View getActionView(String value) {
        if (getHeaders().containsKey(value)) {

            value = (String) getHeaders().get(value);
        }

        return new Column(getContext(), value, value).getLabelView();
    }

    protected Button getNextPageBtn() {
        if (nextPageBtn == null) {
            nextPageBtn = new Button(getContext());
            nextPageBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    paginator.fetchNextPageData();

                }
            });
        }
        return nextPageBtn;
    }

    private View makeContentRow(View dataRow, boolean isHeaderRow) {

        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
        );
        dataRow.setLayoutParams(params);
        int pos = contentLayout.getChildCount();

        if ((pos % 2) != 0 && isStripped && !isHeaderRow) {
            dataRow.setBackgroundColor(stripColor);
        }

        return dataRow;
    }

    protected String getCurrentPageString() {
        return "Show More " + ((Paginator) paginator).getCurrentPageString();
    }

    protected DataListener getDataListener() {
        if (dataListener == null) {
            dataListener = new DataViewListener();
        }
        return dataListener;
    }


    // ========================== MAKE ROWS ================================

    protected void makeToolbarRow() {
        // hide the progress bar
        topProgressBar.setVisibility(View.GONE);

        if (toolbarViews.size() > 0) {

            LinearLayout toolbarRow = new LinearLayout(getContext());
            toolbarRow.setGravity(Gravity.RIGHT);
            for (View view : toolbarViews) {
                toolbarRow.addView(view);
            }
            headerLayout.addView(toolbarRow);
        }
    }

    protected void makeHeaderRow() {
        headerLayout.removeAllViews();
        LinearLayout headerRow = new LinearLayout(getContext());
        boolean hasActions = false;

        LinkedList<ColumnInterface> cols = new LinkedList<>();
        cols.addAll(leftColumns);
        cols.addAll(getColumns().values());
        cols.addAll(rightColumns);

        for (ColumnInterface item : cols) {
            if (item instanceof ActionColumn) {
                hasActions = true;
                continue;
            }
            headerRow.addView(item.getLabelView());
        }

        if (hasActions) {
            headerRow.addView(getActionView(ACTIONSLABEL));
        }
        headerRow.setOrientation(LinearLayout.HORIZONTAL);
        headerRow.setBackgroundColor(headerColor);
        headerLayout.addView(makeContentRow(headerRow, true));
    }

    protected void makeDataRows() {

        makeDataRows(data);
    }

    private void makeDataRows(List<Map> dataItems) {
        LinearLayout actionLayout;
        LinearLayout dataRow;

        for (Map datum : dataItems) {
            dataRow = new LinearLayout(getContext());

            List<ActionColumn> actionColumns = new ArrayList<>();

            LinkedList<ColumnInterface> cols = new LinkedList<>();
            cols.addAll(leftColumns);
            cols.addAll(getColumns().values());
            cols.addAll(rightColumns);
            for (ColumnInterface item : cols) {
                if (item instanceof ActionColumn) {
                    actionColumns.add((ActionColumn) item);
                    continue;
                }
                dataRow.addView(item.getDataView(totalRowsAdded + 1, datum));
            }

            if (!actionColumns.isEmpty()) {
                actionLayout = new LinearLayout(getContext());
                actionLayout.setOrientation(HORIZONTAL);
                for (ActionColumn actionColumn : actionColumns) {

                    actionLayout.addView(
                            actionColumn
                                    .getDataView(totalRowsAdded + 1, datum));
                }
                dataRow.addView(actionLayout);
            }
            dataRow.setOrientation(LinearLayout.HORIZONTAL);

            contentLayout.addView(makeContentRow(dataRow, false));
            totalRowsAdded++;

        }
        updateStatsBar();
    }

    private void updateStatsBar() {
        String text = String.format("Found %s of %s", data.size(),
                                    paginator.getTotalRecords()
        );
        if (data.isEmpty()) {
            text = " No data to display";
        }

        if (infoBar == null) {
            infoBar = new Column(getContext(), text, text).getLabelView();

            footerLayout.addView(infoBar);
        }
        ((TextView) infoBar).setText(text);
    }

    protected void makeFooterRow() {
        footerLayout.removeAllViews();
        ViewGroup.LayoutParams params = new ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
        );
        progressBar = new ProgressBar(getContext());
        progressBar.setLayoutParams(params);
        progressBar.setIndeterminate(true);
        progressBar.setVisibility(View.GONE);
        getNextPageBtn().setLayoutParams(params);

        LinearLayout footerRow = new LinearLayout(getContext());
        if (progressBar.getParent() != null) {
            ((ViewGroup) progressBar.getParent()).removeView(progressBar);
        }
        if (getNextPageBtn().getParent() != null) {
            ((ViewGroup) getNextPageBtn().getParent())
                    .removeView(getNextPageBtn());
        }

        footerRow.addView(progressBar);
        footerRow.addView(getNextPageBtn());

        footerLayout.addView(footerRow);
    }


    // ================== SET DATA ==================================

    public void setData(final List<Map> data) {
        final ListPaginator pager = new ListPaginator(getDataListener());

        setPaginator(pager, new LazyResolver() {
            public void resolve() {
                pager.setData(data);
            }
        });

    }

    public void setQuery(ActiveRecord activeRecord,
                         final String sql,
                         final String[] params) {
        final SqlPaginator pager = new SqlPaginator(
                getDataListener(), activeRecord.getDb());

        setPaginator(pager, new LazyResolver() {
            public void resolve() {
                pager.query(sql, params);
            }
        });

    }

    public void setPaginator(PaginatorInterface paginator, LazyResolver
            lazyResolver) {
        data.clear();
        setup(paginator);
        lazyResolver.resolve();
    }

    private void setup(PaginatorInterface paginator) {

        paginator.setPageSize(pageSize);
        this.paginator = paginator;
        topProgressBar.setVisibility(View.VISIBLE);
    }

    /**
     * Returns of columns to use on this grid.
     *
     * @return grid columns.
     */
    public Map<String, ColumnInterface> getColumns() {
        Map<String, ColumnInterface> preparedDisplayItems = new
                LinkedHashMap<>();
        ColumnInterface item;
        for (Object key : getDisplayItems().keySet()) {
            Object val = getDisplayItems().get(key);
            if (val instanceof ColumnInterface) {
                item = (ColumnInterface) val;
            } else {
                item = new Column(getContext(), key + "", val);
            }
            item.setDisplayView(this);
            preparedDisplayItems.put(key + "", item);
        }
        return preparedDisplayItems;
    }

    /**
     * Get column objects to use when rendering the grid view.
     * <p>
     * This method get all the map keys and transforms them into a
     * {@link ColumnInterface} if not display items where provided by the user.
     *
     * @return columns to use for rendering.
     */
    private Map getDisplayItems() {
        if (displayItems == null) {
            displayItems = new HashMap();
            Map dataItem = data.get(0);
            for (Object key : dataItem.keySet()) {
                displayItems.put(key, new Column(
                        getContext(),
                        key + "",
                        capitalizeFirstLetter(key + "")
                ));
            }
        }
        return displayItems;
    }


    // ======================  Data Listener =================================

    public class DataViewListener implements DataListener {
        private List<Map> newData;

        @Override
        public void onFirstPageDataLoaded(boolean hasMorePages) {
            Log.e(getClass().getSimpleName(), "IS FIRST PAGE");
            reset();
            nextBtn(hasMorePages);
            makeToolbarRow();
            makeHeaderRow();
            makeDataRows();
            makeFooterRow();
        }

        @Override
        public void onLastPageDataLoaded() {
            Log.e(getClass().getSimpleName(), "IS LAST PAGE");
            updateDataRows();
            nextBtn(false);
        }

        @Override
        public void onNextPageDataLoaded() {
            nextBtn(true);
            updateDataRows();
        }

        private void updateDataRows() {
            makeDataRows(newData);
        }

        @Override
        public void preDataLoad(boolean hasMorePages) {

            progressBar.setVisibility(View.VISIBLE);
            getNextPageBtn().setVisibility(View.VISIBLE);
            getNextPageBtn().setText("...loading");
        }

        @Override
        public void dataUpdate(List<Map> records) {
            log(" DATA TO UPDATE count " + records.size() + " :: " + records);
            newData = records;
            data.addAll(newData);
        }

        private void nextBtn(boolean hasMorePages) {
            if (progressBar != null) {
                progressBar.setVisibility(View.GONE);
            }
            if (hasMorePages) {
                getNextPageBtn().setText(getCurrentPageString());
            } else {
                getNextPageBtn().setVisibility(View.GONE);
            }
        }
    }

    private void reset() {
        getContentLayout().removeAllViews();
        getFooterLayout().removeAllViews();
    }

    private abstract class LazyResolver {
        public abstract void resolve();
    }
}
