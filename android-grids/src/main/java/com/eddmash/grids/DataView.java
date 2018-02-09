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
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.eddmash.db.ActiveRecord;
import com.eddmash.grids.columns.ActionColumn;
import com.eddmash.grids.columns.Column;
import com.eddmash.grids.columns.ColumnInterface;
import com.eddmash.grids.columns.DataColumn;
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

    private DataViewListener dataListener;
    private LinearLayout contentLayout;
    private ProgressBar progressBar;
    private int totalRowsAdded = 0;

    private Object ACTIONSLABEL = "Actions";
    private Map headers;
    private List<Column> leftColumns;
    private List<Column> rightColumns;
    private LinearLayout topProgressBar;
    private LinearLayout headerLayout;
    private View infoBar;
    private List<View> toolbarViews = new ArrayList<>();

    public DataView(Context context) {
        this(context, null);
    }

    public DataView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public DataView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        data = new ArrayList<>();
        headers = new HashMap();
        rightColumns = new ArrayList<>();
        leftColumns = new ArrayList<>();
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

        topProgressBar = new LinearLayout(getContext());
        topProgressBar.setOrientation(LinearLayout.VERTICAL);
        topProgressBar.setGravity(Gravity.CENTER_HORIZONTAL);
        topProgressBar.setLayoutParams(params);

        ProgressBar progressBar = new ProgressBar(getContext());
        progressBar.setIndeterminate(true);
        progressBar.setLayoutParams(new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT));
        Drawable progressDrawable = progressBar.getIndeterminateDrawable().mutate();
        progressDrawable.setColorFilter(Color.YELLOW, android.graphics.PorterDuff.Mode.SRC_IN);
        progressBar.setIndeterminateDrawable(progressDrawable);
        TextView message = new TextView(getContext());
        message.setText("Loading initial page");
        message.setTextColor(Color.YELLOW);
        message.setLayoutParams(new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT));
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

    /**
     * Determine how and which columns of your data will be displayed by passing them here.
     * <p>
     * This overrides the default implementation of using all the columns in you data.
     *
     * @param attributesLabel
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
                    paginator.fetchNextPageData();

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
        return "Show More " + ((Paginator) paginator).getCurrentPageString();
    }

    protected DataListener getDataListener() {
        if (dataListener == null) {
            dataListener = new DataViewListener();
        }
        return dataListener;
    }


    // ========================== MAKE ROWS ===========================================

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

    public ArrayAdapter<String> getAdapter() {
        List<String> list = new ArrayList<String>();
        list.add("CSV");
        return new ArrayAdapter<String>(getContext(),
                android.R.layout.simple_spinner_item,
                list);
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
            headerRow.addView(prepareCellView(item.getHeader(), item.getWeight()));
        }

        if (hasActions) {
            headerRow.addView(makeHeaderView(ACTIONSLABEL + "", getWeight(ACTIONSLABEL)));
        }
        headerRow.setOrientation(LinearLayout.HORIZONTAL);
        headerRow.setBackgroundColor(headerColor);
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
        updateStatsBar();
    }

    private void updateStatsBar() {
        String text = String.format("Found %s of %s", data.size(), paginator.getTotalRecords());
        if (data.isEmpty()) {
            text = " No data to display";
        }

        if (infoBar == null) {
            infoBar = makeContentRow(prepareCellView(text, 1), false);

            footerLayout.addView(infoBar);
        }
        ((TextView) infoBar).setText(text);
    }

    protected void makeFooterRow() {
        footerLayout.removeAllViews();
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
        final SqlPaginator pager = new SqlPaginator(getDataListener(), activeRecord.getDb());

        setPaginator(pager, new LazyResolver() {
            public void resolve() {
                pager.query(sql, params);
            }
        });

    }

    public void setPaginator(PaginatorInterface paginator, LazyResolver lazyResolver) {
        data.clear();
        setup(paginator);
        lazyResolver.resolve();
    }

    private void setup(PaginatorInterface paginator) {

        paginator.setPageSize(pageSize);
        this.paginator = paginator;
        topProgressBar.setVisibility(View.VISIBLE);
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
