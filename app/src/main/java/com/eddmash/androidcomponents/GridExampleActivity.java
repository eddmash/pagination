package com.eddmash.androidcomponents;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;

import com.eddmash.androidcomponents.helpers.DatabaseSchema;
import com.eddmash.androidcomponents.helpers.SqlHelper;
import com.eddmash.db.ActiveRecord;
import com.eddmash.grids.DataView;
import com.eddmash.grids.columns.ActionColumn;

import java.util.List;
import java.util.Map;

public class GridExampleActivity extends AppCompatActivity {

    private DataView dataView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grid_example);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        ActiveRecord activeRecord = ActiveRecord.getInstance(
                SqlHelper.getInstance(this).getReadableDatabase());
        dataView = (DataView) findViewById(R.id.content_dsp);
        dataView.setPageSize(3);

        List<Map> data = activeRecord.all(DatabaseSchema.SalePeople.TABLE_NAME);
        dataView.addColumn(new EditActionColumn(this, "edit"), DataView.RIGHT);
        dataView.setData(data);
    }

    public class EditActionColumn extends ActionColumn {
        public EditActionColumn(Context context, String name) {
            super(context, name);
        }

        @Override
        protected void onItemClick(View v, Map datum) {
            // launch edtitactivity here
        }
    }
}
