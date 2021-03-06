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
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class itemPopup_edit extends AppCompatActivity {

    String tDate;
    int day;
    int month;
    int year;
    Date formattedDate_date;
    dataBaseHelper dataBaseHelper = new dataBaseHelper(this);
    Date dateUpdate;
    String newFormattedDate = "";
    long difference = 0;

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
        final String event = intent.getStringExtra(itemPopup.message);
        final String formattedDate_string = intent.getStringExtra(itemPopup.message1);
        final int position = intent.getIntExtra(itemPopup.positionNum, 0);

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
        final EditText editEventName = findViewById(R.id.editText3);
        final TextView editDate = findViewById(R.id.editText2);

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

                String item = editEventName.getText().toString();
                editDate.setText(tDate);

                try {
                    dateUpdate = getDateFormat(tDate);
                    Date currDate = getCurrDate();

                    difference = (getDateDiff(currDate, dateUpdate)) + 1;
                    newFormattedDate = formatDate(dateUpdate);
                    dataBaseHelper.updateEvent(item, event, newFormattedDate, difference);
                    finish();

                } catch (ParseException e) {
                    e.printStackTrace();
                }

                ArrayList<event> list = listActivity.getList();
                event current = list.get(position);
                list.remove(position);

                current.setEvent(item);
                current.setFormattedDate(newFormattedDate);
                current.setDaysLeft(difference);

                list.add(current);

                sortList(list);

                listActivity.setList(list);
                finish();
                itemPopup.getInstance().finish();

            }
        });
    }

    public Date getDateFromString(String s) throws ParseException {
        @SuppressLint("SimpleDateFormat") DateFormat format = new SimpleDateFormat("EEEE, MMM dd, yyy");
        Date date = format.parse(s);
        return date;
    }

    public static Date getDateFormat(String s) throws ParseException {
        @SuppressLint("SimpleDateFormat") SimpleDateFormat parser = new SimpleDateFormat("dd/MM/yyyy");

        return parser.parse(s);
    }

    public static long getDateDiff(Date date1, Date date2) {
        long diff = date2.getTime() - date1.getTime();
        return TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS);
    }

    public static Date getCurrDate() {
        return new Date();
    }

    public static String formatDate(Date date) {
        @SuppressLint("SimpleDateFormat") SimpleDateFormat formatter = new SimpleDateFormat("EEEE, MMM dd, yyy");

        return formatter.format(date);
    }

    public static void sortList(ArrayList<event> list) {
        Collections.sort(list, new Comparator<event>() {
            @Override
            public int compare(event o1, event o2) {
                return (int) (o1.getDaysLeft() - o2.getDaysLeft());
            }
        });
    }

}
