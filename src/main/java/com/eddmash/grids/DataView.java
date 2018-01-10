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
import android.support.annotation.ColorInt;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;

import com.eddmash.db.ActiveRecord;
import com.eddmash.grids.columns.ActionColumn;
import com.eddmash.grids.columns.Column;
import com.eddmash.grids.columns.ColumnInterface;
import com.eddmash.grids.columns.DataColumn;
import com.eddmash.grids.listener.DataListenerInterface;
import com.eddmash.pagination.ListPaginator;
import com.eddmash.pagination.PaginatorInterface;
import com.eddmash.pagination.SqlPaginator;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class DataView extends LinearLayout {

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

    private DataListenerInterface dataListener;
    private LinearLayout contentLayout;
    private ProgressBar progressBar;
    private int totalRowsAdded = 0;

    private Object ACTIONSLABEL = "Actions";
    private Map headers = new HashMap();
    private List<Column> leftColumns = new ArrayList();
    private List<Column> rightColumns = new ArrayList();
    private ProgressBar topProgressBar;
    private LinearLayout headerLayout;
    private TextView infoCell;

    public DataView(Context context) {
        this(context, null);
    }

    public DataView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public DataView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        data = new ArrayList<>();
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT);

        contentLayout = new LinearLayout(context);
        contentLayout.setOrientation(LinearLayout.VERTICAL);
        contentLayout.setLayoutParams(params);
        footerLayout = new LinearLayout(context);
        footerLayout.setLayoutParams(params);
        footerLayout.setOrientation(LinearLayout.VERTICAL);
        headerLayout = new LinearLayout(context);
        headerLayout.setLayoutParams(params);
        headerLayout.setOrientation(LinearLayout.VERTICAL);
        styles(context, attrs);
        addView(headerLayout);
        addView(contentLayout);
        addView(footerLayout);
    }


    // =============== COLUMNS =================================

    /**
     * @param col
     * @param position true to add column at the beginning, false to add to the right
     */
    public void addColumn(Column col, boolean position) {
        col.setDisplayView(this);
        if (position) {
            leftColumns.add(col);
        } else {
            rightColumns.add(col);
        }

    }

    public void setColumns(Map attributesLabel) {
        this.displayItems = attributesLabel;
        getColumns(); // this prepare the columns early enough
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
        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.DataView);
        try {
            setHeaderColor(a.getColor(R.styleable.DataView_headerColor, Color.GRAY));
            setStripColor(a.getColor(R.styleable.DataView_stripColor, Color.LTGRAY));
            isStripped(a.getBoolean(R.styleable.DataView_isStrip, true));
            setPageSize(a.getInteger(R.styleable.DataView_pageSize, 10));

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
                headers.put(key,
                        capitalizeFirstLetter(key.toString().replace("_", " ")));
            }
        } else {
            for (String key : getColumns().keySet()) {

                headers.put(key, getColumns().get(key).getHeader());
            }
        }
        return headers;
    }

    private View makeHeaderView(String value, double weight) {
        if (getHeaders().containsKey(value)) {

            value = (String) getHeaders().get(value);
        }
        TextView view = (TextView) prepareCellView(value + "", weight);
        view.setTextColor(Color.WHITE);
        return view;
    }

    protected Button getNextPageBtn() {
        if (nextPageBtn == null) {
            nextPageBtn = new Button(getContext());
            nextPageBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    paginator.nextPage();

                }
            });
        }
        return nextPageBtn;
    }

    private View makeContentRow(View dataRow, boolean isHeaderRow) {

        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT);
        dataRow.setLayoutParams(params);
        int pos = contentLayout.getChildCount();

        if ((pos % 2) != 0 && isStripped && !isHeaderRow) {
            dataRow.setBackgroundColor(stripColor);
        }

        return dataRow;
    }

    private View prepareCellView(View view, double weight) {
        float w = Double.valueOf(weight).floatValue();
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(0,
                ViewGroup.LayoutParams.WRAP_CONTENT, w);
        view.setLayoutParams(params);
        view.setPadding(2, 2, 2, 2);
        return view;
    }

    private View prepareCellView(String value, double weight) {
        TextView view = new TextView(getContext());
        view.setText(value + "".trim());
        return prepareCellView(view, weight);
    }

    protected String getCurrentPageString() {
        return "Show More " + ((ListPaginator) paginator).getCurrentPageString();
    }

    protected DataListenerInterface getDataListener() {
        if (dataListener == null) {
            dataListener = new DataListener();
        }
        return dataListener;
    }


    // ========================== MAKE ROWS ===========================================

    protected void makeToolbarRow() {

        LinearLayout toolbarRow = new LinearLayout(getContext());
        toolbarRow.setGravity(Gravity.RIGHT);
        toolbarRow.addView(makeInfoCell());
        toolbarRow.addView(makeSearchCell());
        toolbarRow.addView(makeExportCell());
        headerLayout.addView(toolbarRow);
    }

    private View makeInfoCell() {
        infoCell = new TextView(getContext());
        return prepareCellView(infoCell, 1);
    }

    private View makeExportCell() {
        Spinner spinner = new Spinner(getContext());
        spinner.setAdapter(getAdapter());

        return prepareCellView(spinner, .5);
    }

    private View makeSearchCell() {
        EditText search = new EditText(getContext());
        search.setHint("Search");
        return prepareCellView(search, .5);
    }

    public ArrayAdapter<String> getAdapter() {
        List<String> list = new ArrayList<String>();
        list.add("CSV");
        return new ArrayAdapter<String>(getContext(),
                android.R.layout.simple_spinner_item,
                list);
    }

    protected void makeHeaderRow() {

        LinearLayout headerRow = new LinearLayout(getContext());
        boolean hasActions = false;

        LinkedList<ColumnInterface> cols = new LinkedList<>();
        cols.addAll(leftColumns);
        cols.addAll(getColumns().values());
        cols.addAll(rightColumns);
        topProgressBar = new ProgressBar(getContext());
        topProgressBar.setIndeterminate(false);

        for (ColumnInterface item : cols) {
            if (item instanceof ActionColumn) {
                hasActions = true;
                continue;
            }
            headerRow.addView(prepareCellView(item.getHeader(), item.getWeight()));
        }

        if (hasActions) {
            headerRow.addView(makeHeaderView(ACTIONSLABEL + "", getWeight(ACTIONSLABEL)));
        }
        headerRow.setOrientation(LinearLayout.HORIZONTAL);
        headerRow.setBackgroundColor(headerColor);
        makeToolbarRow();
        headerLayout.addView(makeContentRow(headerRow, true));
    }

    private double getWeight(Object head) {

        return 1;
    }

    protected void makeDataRows() {
        makeDataRows(data);
    }

    private void makeDataRows(List<Map> dataItems) {
        LinearLayout actionLayout;
        for (Map datum : dataItems) {
            LinearLayout dataRow = new LinearLayout(getContext());

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
                dataRow.addView(
                        prepareCellView(
                                item.makeView(totalRowsAdded + 1, datum),
                                item.getWeight()));
            }

            if (!actionColumns.isEmpty()) {
                actionLayout = new LinearLayout(getContext());
                actionLayout.setOrientation(HORIZONTAL);
                for (ActionColumn actionColumn : actionColumns) {

                    actionLayout.addView(prepareCellView(
                            actionColumn.makeView(totalRowsAdded + 1, datum),
                            actionColumn.getWeight()));
                }
                dataRow.addView(prepareCellView(actionLayout, getWeight(ACTIONSLABEL)));
            }
            dataRow.setOrientation(LinearLayout.HORIZONTAL);
            contentLayout.addView(makeContentRow(dataRow, false));
            totalRowsAdded++;

        }
    }

    protected void makeFooterRow() {

        ViewGroup.LayoutParams params = new ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT);
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
            ((ViewGroup) getNextPageBtn().getParent()).removeView(getNextPageBtn());
        }

        footerRow.addView(progressBar);
        footerRow.addView(getNextPageBtn());

        addView(footerRow);
    }


    // ================== SET DATA ==================================

    public void setData(final List<Map> data) {
        final ListPaginator paginator = new ListPaginator() {
            @Override
            public void onLastPageLoad() {
                getDataListener().onLastPageLoaded();
            }

            @Override
            public void OnFirstPageLoad(boolean hasMorePages) {
                getDataListener().onFirstPageLoaded(hasMorePages);
            }

            @Override
            public void OnNextPageLoad(boolean hasMorePages) {
                getDataListener().preNextPageLoading(hasMorePages);
            }

            @Override
            public void updateAdapter(List<Map> records) {
                getDataListener().dataUpdate(records);
            }

            @Override
            public void onDoneAddingRecords() {
                getDataListener().onDoneAddingRecords();
            }
        };
        setPaginator(paginator);
        paginator.setData(data);

    }

    public void setQuery(ActiveRecord activeRecord,
                         final String sql,
                         final String[] params) {
        final SqlPaginator paginator = new SqlPaginator(activeRecord) {
            @Override
            public void onLastPageLoad() {
                getDataListener().onLastPageLoaded();
            }

            @Override
            public void OnFirstPageLoad(boolean hasMorePages) {
                getDataListener().onFirstPageLoaded(hasMorePages);
            }

            @Override
            public void OnNextPageLoad(boolean hasMorePages) {
                getDataListener().preNextPageLoading(hasMorePages);
            }

            @Override
            public void updateAdapter(List<Map> records) {
                getDataListener().dataUpdate(records);
            }

            @Override
            public void onDoneAddingRecords() {
                getDataListener().onDoneAddingRecords();
            }
        };
        setPaginator(paginator);

        paginator.query(sql, params);
    }

    public void setPaginator(PaginatorInterface paginator) {
        paginator.setPageSize(pageSize);
        this.paginator = paginator;
    }

    public Map<String, ColumnInterface> getColumns() {
        Map<String, ColumnInterface> preparedDisplayItems = new LinkedHashMap<>();
        ColumnInterface item;
        for (Object key : getDisplayItems().keySet()) {
            Object val = getDisplayItems().get(key);
            if (val instanceof ColumnInterface) {
                item = (ColumnInterface) val;
            } else {
                item = new DataColumn(getContext(), key + "", val);
            }
            item.setDisplayView(this);
            preparedDisplayItems.put(key + "", item);
        }
        return preparedDisplayItems;
    }

    public Map getDisplayItems() {
        if (displayItems == null) {
            displayItems = new HashMap();
            Map dataItem = data.get(0);
            for (Object key : dataItem.keySet()) {
                displayItems.put(key, new DataColumn(getContext(),
                        key + "",
                        capitalizeFirstLetter(key + "")));
            }
        }
        return displayItems;
    }

    // ======================  Data Listener =================================

    public class DataListener implements DataListenerInterface {
        private List<Map> newData;

        @Override
        public void onLastPageLoaded() {

            getNextPageBtn().setVisibility(View.GONE);
        }

        @Override
        public void onFirstPageLoaded(boolean hasMorePages) {
            log(" FIRST PAGE DATA LOADED " + paginator.getData());
            data = paginator.getData();
            reset();
            makeHeaderRow();
            makeDataRows();
            makeFooterRow();
            nextBtn(hasMorePages);
            info();
        }

        private void nextBtn(boolean hasMorePages) {
            if (hasMorePages) {
                getNextPageBtn().setText(getCurrentPageString());
                getNextPageBtn().setVisibility(View.VISIBLE);
            } else {
                getNextPageBtn().setVisibility(View.GONE);
                progressBar.setVisibility(View.GONE);
            }
        }

        @Override
        public void preNextPageLoading(boolean hasMorePages) {
            nextPageBtn.setText("..loading");
            progressBar.setVisibility(View.VISIBLE);
        }

        @Override
        public void dataUpdate(List<Map> records) {
            log(" DATA TO UPDATE count " + records.size() + " :: " + records);
            newData = records;
            data.addAll(newData);
        }

        @Override
        public void onDoneAddingRecords() {
            makeDataRows(newData);
            progressBar.setVisibility(View.GONE);
            getNextPageBtn().setText(getCurrentPageString());
            info();

        }

        protected void info() {
            infoCell.setText(String.format("Showing %s of %s records",
                    paginator.getData().size(),
                    ((ListPaginator) paginator).getTotalRecords()));
        }
    }

    private void reset() {
        getContentLayout().removeAllViews();
        getFooterLayout().removeAllViews();
    }

}
