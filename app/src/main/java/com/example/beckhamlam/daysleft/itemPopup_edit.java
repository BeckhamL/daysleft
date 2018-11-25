package com.example.beckhamlam.daysleft;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class itemPopup_edit extends AppCompatActivity {

    String tDate;
    int day;
    int month;
    int year;
    Date formattedDate_date;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_popup_edit);
        getSupportActionBar().hide();

        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);
        int width = dm.widthPixels;
        int height = dm.heightPixels;
        getWindow().setLayout( (int) (width * 0.8), (int) (height * 0.55));

        Intent intent = getIntent();
        String event = intent.getStringExtra(itemPopup.message);
        String formattedDate_string = intent.getStringExtra(itemPopup.message1);

        try {
            formattedDate_date = getDateFromString(formattedDate_string);

            Calendar cal = Calendar.getInstance();
            cal.setTime(formattedDate_date);
            day = cal.get(Calendar.DAY_OF_MONTH);
            month = cal.get(Calendar.MONTH);
            year = cal.get(Calendar.YEAR);

        } catch (ParseException e) {
            e.printStackTrace();
        }

        Button buttonCancel = findViewById(R.id.button7);
        Button buttonUpdate = findViewById(R.id.button8);
        EditText editEventName = findViewById(R.id.editText3);
        TextView editDate = findViewById(R.id.editText2);

        editEventName.setText(event, TextView.BufferType.EDITABLE);
        editDate.setText(formattedDate_string, TextView.BufferType.EDITABLE);

        editDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(itemPopup_edit.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        tDate = dayOfMonth + "/" + (month + 1) + "/" + year;
                    }
                }, year, month , day);
                datePickerDialog.show();
            }
        });

        buttonCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        buttonUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

    public Date getDateFromString(String s) throws ParseException {
        @SuppressLint("SimpleDateFormat") DateFormat format = new SimpleDateFormat("EEEE, MMM dd, yyy");
        Date date = format.parse(s);
        return date;
    }
}
