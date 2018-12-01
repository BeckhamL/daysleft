package com.example.beckhamlam.daysleft;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class listActivity extends AppCompatActivity {

    dataBaseHelper dataBaseHelper = new dataBaseHelper(this);
    public static final String message1 = "message";
    public static final String daysLeft = "daysLeft";
    public static final String formattedDate = "formattedDate";

    ListView listView;
    ArrayList<event> events;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        listView = findViewById(R.id.listView);
        populateListView();

        Intent intent = getIntent();
        final String dateString = intent.getStringExtra(MainActivity.sDate);
        final String message = intent.getStringExtra(MainActivity.message);

        try {
            Date date = getDateFormat(dateString);
            Date currDate = getCurrDate();

            if (date.before(currDate)) {
                Toast.makeText(this, "error", Toast.LENGTH_SHORT).show();
            }

            else {

                String formattedDate = formatDate(date);

                long difference = (getDateDiff(currDate, date)) + 1;

                events = event.createEventList(new event(message, formattedDate, difference));
                dataBaseHelper.addData(message, formattedDate, difference);

            }

        } catch (ParseException e) {
            e.printStackTrace();
        }

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                event event = events.get(position);
                Intent intent = new Intent(getApplicationContext(), itemPopup.class);
                intent.putExtra(message1, event.getEvent());
                intent.putExtra(daysLeft, String.valueOf(event.getDaysLeft()));
                intent.putExtra(formattedDate, event.getFormattedDate());
                startActivity(intent);
            }
        });
    }

    private void populateListView() {
        Cursor data = dataBaseHelper.getData();
        ArrayList<String> list = new ArrayList<>();
        while (data.moveToNext()) {
            list.add(data.getString(0));
        }

        ListAdapter adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, list);
        listView.setAdapter(adapter);
    }

    public static long getDateDiff(Date date1, Date date2) {
        long diff = date2.getTime() - date1.getTime();
        return TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS);
    }

    public static Date getCurrDate() {
        return new Date();
    }

    public static Date getDateFormat(String s) throws ParseException {
        @SuppressLint("SimpleDateFormat") SimpleDateFormat parser = new SimpleDateFormat("dd/MM/yyyy");

        return parser.parse(s);
    }

    public static String formatDate(Date date) {
        @SuppressLint("SimpleDateFormat") SimpleDateFormat formatter = new SimpleDateFormat("EEEE, MMM dd, yyy");

        return formatter.format(date);
    }

}
