// TODO: validate whether date user inputted is after currDate
// TODO: keyboard click outside to close
// TODO: when editing item, if date is not given app will crash

package com.example.beckhamlam.daysleft;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class items extends AppCompatActivity {

    ArrayList<event> events;
    dataBaseHelper dataBaseHelper = new dataBaseHelper(this);
    event currEvent = new event("","",0);

    @RequiresApi(api = Build.VERSION_CODES.O)
    @SuppressLint({"SetTextI18n", "SimpleDateFormat", "DefaultLocale", "ShowToast"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show);
        getSupportActionBar().hide();

        Intent intent = getIntent();
        String dateString = intent.getStringExtra(MainActivity.sDate);
        String message = intent.getStringExtra(MainActivity.message);

        Button button = findViewById(R.id.addNew);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent newIntent = new Intent(items.this, MainActivity.class);
                startActivity(newIntent);
            }
        });

        try {
            Date date = getDateFormat(dateString);
            Date currDate = getCurrDate();

            if (date.before(currDate)) {
                Toast.makeText(this, "error", Toast.LENGTH_SHORT).show();
            }

            else {

                String formattedDate = formatDate(date);

                long difference = (getDateDiff(currDate, date)) + 1;

                currEvent.setDaysLeft(difference);
                currEvent.setEvent(message);
                currEvent.setFormattedDate(formattedDate);

                events = event.createEventList(currEvent);

                //events = event.createEventList(new event(message, formattedDate, difference));
                dataBaseHelper.addData(message, formattedDate, difference);

                RecyclerView rvEvents = findViewById(R.id.rvEvents);

                eventAdapter eventAdapter = new eventAdapter(events, this);
                eventAdapter.notifyDataSetChanged();
                rvEvents.setAdapter(eventAdapter);
                rvEvents.setLayoutManager(new LinearLayoutManager(this));

            }

        } catch (ParseException e) {
            e.printStackTrace();
        }
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

    @Override
    public void onStop() {
        super.onStop();
    }

    @Override
    public void onPause() {
        super.onPause();
    }

}
