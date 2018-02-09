package com.eddmash.androidcomponents;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;

import com.eddmash.androidcomponents.forms.BasicForm;
import com.eddmash.form.FormException;
import com.eddmash.form.FormInterface;
import com.eddmash.form.faker.DummyDataPopulator;
import com.eddmash.form.faker.provider.TelephoneProvider;
import com.eddmash.form.values.SimpleValue;
import com.eddmash.form.values.ValueInterface;
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
    private CheckBox termsCheckbox;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // get views
        List<ValueInterface> genders = new ArrayList<>();
        genders.add(new SimpleValue(" ", " "));
        genders.add(new SimpleValue("1", "Male"));
        genders.add(new SimpleValue("2", "Female"));
        genderSpinner = findViewById(R.id.gender);

        ArrayAdapter<ValueInterface> adapter = new ArrayAdapter<>(this, R.layout.spinneritem,
                genders);
        genderSpinner.setAdapter(adapter);
        genderSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                Object selected = adapterView.getSelectedItem();
                if (selected instanceof ValueInterface) {
                    ValueInterface val = (ValueInterface) selected;

                    val.getItem(); // get the data structure backing this value item
                    val.getLabel(); // the the label
                    val.getValue(); // get the actual value
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        phoneNumber = findViewById(R.id.phone_number);
        firstName = findViewById(R.id.first_name);
        termsCheckbox = findViewById(R.id.terms);
        errorspace = findViewById(R.id.errorspace);
        errorspace.setVisibility(View.GONE);

        // create form
        form = new BasicForm();
        form.addField("gender", genderSpinner);
        form.addField("phonenumber", phoneNumber);
        form.addField("firstname", firstName);
        form.addField("terms", termsCheckbox);

        // create validation checks
        form.addCheck(
                new NotEmptyCheck(genderSpinner,
                        getString(R.string.not_blank, "Gender")));

        form.addCheck(
                new NotEmptyCheck(termsCheckbox,
                        getString(R.string.not_blank, "Terms")));

        form.addCheck(new NotEmptyCheck(phoneNumber, getString(R.string.not_blank, "Phonenumber")));

        form.addCheck(new NotEmptyCheck(firstName,
                getString(R.string.not_blank, "Firstname")));

        // populate form with dummy data
        DummyDataPopulator dataPopulator = new DummyDataPopulator();
        try {
            dataPopulator.setFieldProvider("phonenumber",
                    new TelephoneProvider(dataPopulator, "(+###) ### ### ###"));
            dataPopulator.populate(form);
        } catch (FormException e) {
            e.printStackTrace();
        }

        // run validations on click
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
                } else {
                    showErrorsAtTheTop();
                }
            }
        });


        Button backBtn = (Button) findViewById(R.id.back_to_main);
        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, PaginationActivity.class));
            }
        });
        Button grid_example = (Button) findViewById(R.id.grid_example);
        grid_example.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, GridExampleActivity.class));
            }
        });
    }

    private void showErrorsAtTheTop() {
        errorspace.removeAllViews();
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
