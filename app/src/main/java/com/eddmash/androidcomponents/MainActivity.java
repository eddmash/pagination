package com.eddmash.androidcomponents;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;

import com.eddmash.androidcomponents.forms.BasicForm;
import com.eddmash.form.FormException;
import com.eddmash.form.FormInterface;
import com.eddmash.form.values.Value;
import com.eddmash.validation.checks.NotEmptyCheck;
import com.eddmash.validation.renderer.ErrorRenderer;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private Spinner genderSpinner;
    private Button saveBtn;
    private FormInterface form;
    private EditText phoneNumber;
    private EditText firstName;
    private LinearLayout errorspace;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        List genders = new ArrayList<>();
        genders.add(new Value(" ", " "));
        genders.add(new Value("1", "Male"));
        genders.add(new Value("2", "Female"));
        genderSpinner = findViewById(R.id.gender);
        ArrayAdapter<Value> adapter = new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_item, genders);
        genderSpinner.setAdapter(adapter);

        phoneNumber = findViewById(R.id.phone_number);
        firstName = findViewById(R.id.first_name);
        errorspace = findViewById(R.id.errorspace);
        errorspace.setVisibility(View.GONE);

        form = new BasicForm();
        form.addField("gender", genderSpinner);
        form.addField("phonenumber", phoneNumber);
        form.addField("firstname", firstName);

        form.addCheck(new NotEmptyCheck(genderSpinner,
                getString(R.string.not_blank, "Gender")));

        form.addCheck(new NotEmptyCheck(phoneNumber,
                getString(R.string.not_blank, "Phonenumber")));

        form.addCheck(new NotEmptyCheck(firstName,
                getString(R.string.not_blank, "Firstname")));

        saveBtn = findViewById(R.id.savebtn);
        saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                errorspace.setVisibility(View.GONE);
                if (form.isValid()) {
                    try {
                        form.save();
                    } catch (FormException e) {
                        e.printStackTrace();
                    }
                }else {
                    showErrorsAtTheTop();
                }
            }
        });
    }

    private void showErrorsAtTheTop() {
        errorspace.setVisibility(View.VISIBLE);
        ErrorRenderer errorRenderer = new ErrorRenderer(this, form.getValidator());
        errorRenderer.render(errorspace);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
