package com.example.beckhamlam.daysleft;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class listActivity extends AppCompatActivity {

    dataBaseHelper dataBaseHelper = new dataBaseHelper(this);
    public static final String message1 = "message";
    public static final String daysLeft = "daysLeft";
    public static final String formattedDate = "formattedDate";
    public static final String positionNum = "positionNum";

    ListView listView;
    ArrayList<event> events;
    event currEvent = new event("","",0);
    listAdapter listAdapter;
    static ArrayList<event> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        listView = findViewById(R.id.listView);

        FloatingActionButton floatingActionButton = findViewById(R.id.fab);
        final SwipeRefreshLayout swipeRefreshLayout = findViewById(R.id.swiperefresh);

        final Intent intent = getIntent();
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

                currEvent.setDaysLeft(difference);
                currEvent.setEvent(message);
                currEvent.setFormattedDate(formattedDate);

                events = event.createEventList(currEvent);
                dataBaseHelper.addData(message, formattedDate, difference);

            }

        } catch (ParseException e) {
            e.printStackTrace();
        }

        populateListView();

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                event newEvents = listAdapter.getItem(position);

                Intent intent = new Intent(getApplicationContext(), itemPopup.class);
                intent.putExtra(message1, newEvents.getEvent());
                intent.putExtra(daysLeft, String.valueOf(newEvents.getDaysLeft()));
                intent.putExtra(formattedDate, newEvents.getFormattedDate());
                intent.putExtra(positionNum, position);
                listAdapter.notifyDataSetChanged();
                startActivity(intent);
            }
        });

        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent1);
            }
        });

        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                listAdapter.notifyDataSetChanged();
                swipeRefreshLayout.setRefreshing(false);
            }
        });
    }

    private void populateListView() {

        list = dataBaseHelper.getEvents();

        sortList(list);

        listAdapter = new listAdapter(this, R.layout.listview_items, list);
        listView.setAdapter(listAdapter);
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
    public void onResume(){
        super.onResume();
        listAdapter.notifyDataSetChanged();

    }

    public static void setList(ArrayList<event> list) {
        listActivity.list = list;
    }

    public static ArrayList<event> getList() {
        return list;
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
