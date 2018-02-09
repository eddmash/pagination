package com.eddmash.views;

import android.app.DatePickerDialog;
import android.content.Context;
import android.support.v7.widget.AppCompatEditText;
import android.util.AttributeSet;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

import static android.text.InputType.TYPE_DATETIME_VARIATION_DATE;

/*
* This file is part of the Tools package.
* 
* (c) Eddilbert Macharia (http://eddmash.com)<edd.cowan@gmail.com>
*
* For the full copyright and license information, please view the LICENSE
* file that was distributed with this source code.
*/

public class DatePickerView extends AppCompatEditText {
    public static String DATEFORMAT = "yyyy-MM-dd";


    public DatePickerView(Context context) {
        super(context);
        init();
    }

    public DatePickerView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public DatePickerView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init(){
        setFocusable(false);
        setInputType(TYPE_DATETIME_VARIATION_DATE);
        setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {

                SelectDatePicker.show(getContext(), Calendar.getInstance(), v);
            }
        });
    }

    @Override
    public boolean callOnClick() {
        return super.callOnClick();
    }

    private static class SelectDatePicker implements DatePickerDialog.OnDateSetListener {
        private Calendar calendar;
        private EditText view;

        SelectDatePicker(Calendar calendar, View view) {
            this.calendar = calendar;
            this.view = (EditText) view;
        }

        @Override
        public void onDateSet(DatePicker view, int year, int monthOfYear,
                              int dayOfMonth) {

            calendar.set(Calendar.YEAR, year);
            calendar.set(Calendar.MONTH, monthOfYear);
            calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);

            SimpleDateFormat sdf = new SimpleDateFormat(DATEFORMAT, Locale.US);
            this.view.setText(sdf.format(calendar.getTime()));
            this.view.setError(null);// clear any errors that we there
        }

        /**
         * Displays the actual datepicker using the DatePickerDialog.
         *
         * @param context
         * @param calendar
         * @param v
         */
        public static void show(Context context, Calendar calendar, View v) {
            DatePickerDialog datePicker = new DatePickerDialog(context,
                    new SelectDatePicker(calendar, v),
                    calendar.get(Calendar.YEAR),
                    calendar.get(Calendar.MONTH),
                    calendar.get(Calendar.DAY_OF_MONTH));
            datePicker.show();
        }

        public static void show(Context context, View v) {
            Calendar calendar = Calendar.getInstance();
            show(context, calendar, v);
        }

        public static void show(View v) {
            Calendar calendar = Calendar.getInstance();
            show(v.getContext(), calendar, v);
        }
    }
}
