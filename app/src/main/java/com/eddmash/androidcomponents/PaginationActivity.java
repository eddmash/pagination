package com.eddmash.androidcomponents;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.eddmash.androidcomponents.helpers.DatabaseSchema;
import com.eddmash.androidcomponents.helpers.SqlHelper;
import com.eddmash.db.ActiveRecord;
import com.eddmash.filterableadapter.BaseFiltarableAdapter;
import com.eddmash.pagination.DataListener;
import com.eddmash.pagination.ListPaginator;

import java.util.List;
import java.util.Map;

public class PaginationActivity extends AppCompatActivity {

    private ListPaginator paginator;
    private SalePeopleAdapter salePeopleAdapter;
    private Button loadMoreBtn;
    private RecyclerView salePeopleView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pagination);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        loadMoreBtn = (Button) findViewById(R.id.load_more);
        salePeopleView = (RecyclerView) findViewById(R.id.salepeople);

        ActiveRecord activeRecord = ActiveRecord.getInstance(
                SqlHelper.getInstance(this).getReadableDatabase());

        // create the paginationListener instance
        List<Map> data = activeRecord.all(DatabaseSchema.SalePeople.TABLE_NAME);
        PaginationListener paginationListener = new PaginationListener();


        //get the adapter we will use to disaply data on the recycle view
        salePeopleAdapter = new SalePeopleAdapter(this);
        salePeopleView.setLayoutManager(new LinearLayoutManager(this));
        salePeopleView.setAdapter(salePeopleAdapter);


        // create paginator instance
        paginator = new ListPaginator(paginationListener);
        paginator.setPageSize(3);
        paginator.setData(data);

        // when load button is clicked load next page
        loadMoreBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                paginator.fetchNextPageData();

            }
        });
    }


    private class PaginationListener implements DataListener {

        @Override
        public void onLastPageDataLoaded() {
            // we dont need the load more button if we an on the last page of out data
            loadMoreBtn.setVisibility(View.GONE);
            salePeopleAdapter.notifyDataSetChanged();
        }

        @Override
        public void onFirstPageDataLoaded(boolean hasMorePages) {
            // update the load more button
            nextBtn(hasMorePages);
            salePeopleAdapter.notifyDataSetChanged();
        }

        @Override
        public void onNextPageDataLoaded() {
            salePeopleAdapter.notifyDataSetChanged();
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
            salePeopleAdapter.update(records);
        }

        @Override
        public void preDataLoad(boolean hasMorePages) {
            nextBtn(hasMorePages);
        }
    }


    private class SalePeopleAdapter extends BaseFiltarableAdapter<SalePeopleAdapter.ViewHolder> {


        public SalePeopleAdapter(AppCompatActivity activity) {
            super(activity);
        }

        @Override
        protected List<Map> doFilter(String searchTerm, List<Map> hayStack) {
            return hayStack;
        }

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            return new ViewHolder(LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.salepeople_layout, parent, false));
        }

        @Override
        public void onBindViewHolder(ViewHolder holder, int position) {
            try {
                Map data = getData().get(position);
                holder.fname.setText(data.get("first_name") + "");
                holder.lname.setText(data.get("last_name") + "");
                holder.crate.setText(data.get("commission_rate") + "");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        class ViewHolder extends RecyclerView.ViewHolder {
            TextView fname;
            TextView lname;
            TextView crate;

            public ViewHolder(View itemView) {
                super(itemView);
                fname = (TextView) itemView.findViewById(R.id.first_name);
                lname = (TextView) itemView.findViewById(R.id.last_name);
                crate = (TextView) itemView.findViewById(R.id.commission_rate);
            }
        }
    }

}
