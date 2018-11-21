//TODO: validate whether date user inputted is after currDate

package com.example.beckhamlam.daysleft;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class show extends AppCompatActivity {

    ArrayList<event> events;

    @RequiresApi(api = Build.VERSION_CODES.O)
    @SuppressLint({"SetTextI18n", "SimpleDateFormat", "DefaultLocale", "ShowToast"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show);
        getSupportActionBar().hide();

        RecyclerView rvEvents = findViewById(R.id.rvEvents);

        events = event.createEventList(20);

        eventAdapter eventAdapter = new eventAdapter(events);
        rvEvents.setAdapter(eventAdapter);
        rvEvents.setLayoutManager(new LinearLayoutManager(this));

        Intent intent = getIntent();
        String dateString = intent.getStringExtra(MainActivity.sDate);
        String message = intent.getStringExtra(MainActivity.message);

        try {
            SimpleDateFormat parser = new SimpleDateFormat("dd/MM/yyyy");
            Date date = parser.parse(dateString);
            Date currDate = getLocalDate();

            long difference = (getDateDiff(date, currDate) * - 1) + 1;
            //Intent intent1 = new Intent(this, event.class);
            //intent1.putExtra("difference", difference);

            SimpleDateFormat formatter = new SimpleDateFormat("EEEE, MMM dd, yyy");
            String formattedDate = formatter.format(date);

            TextView textView = findViewById(R.id.textView2);
            TextView textDaysLeft = findViewById(R.id.textView3);

            textView.setText("Days left until your event " + message + " on " + formattedDate);
            textDaysLeft.setText(String.format("%d", difference));

            events.add(new event(message, difference));

        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    public static long getDateDiff(Date date1, Date date2) {
        long diff = date2.getTime() - date1.getTime();
        long value = TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS);
        return value;
    }

    public static Date getLocalDate() {
        Date date = new Date();
        return date;
    }
}
