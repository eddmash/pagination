package com.eddmash.androidcomponents;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.eddmash.androidcomponents.helpers.DatabaseSchema;
import com.eddmash.androidcomponents.helpers.SqlHelper;
import com.eddmash.db.ActiveRecord;
import com.eddmash.grids.DataGridView;
import com.eddmash.grids.columns.ActionColumn;
import com.eddmash.grids.columns.Column;
import com.eddmash.grids.columns.SerialColumn;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GridExampleActivity extends AppCompatActivity {

    private DataGridView dataGridView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grid_example);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        ActiveRecord activeRecord = ActiveRecord.getInstance(
                SqlHelper.getInstance(this).getReadableDatabase());
        dataGridView = (DataGridView) findViewById(R.id.content_dsp);
        dataGridView.setPageSize(3);

        List<Map> data = activeRecord.all(DatabaseSchema.SalePeople.TABLE_NAME);
        Map cols = new HashMap();
        cols.put("first_name", new Column(
                this,
                "first_name",
                "FirstName"
        ));
//        dataGridView.setColumns(cols);
        dataGridView.addColumn(
                new SerialColumn(this, "#"),
                DataGridView.LEFT
        );
        dataGridView.addColumn(
                new EditActionColumn(this, "Edit"),
                DataGridView.RIGHT
        );
        dataGridView.addColumn(
                new EditActionColumn(this, "Delete"),
                DataGridView.RIGHT
        );
        dataGridView.setData(data);
    }

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
}
