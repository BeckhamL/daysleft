package com.example.beckhamlam.daysleft;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class show extends AppCompatActivity {

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show);
        getSupportActionBar().hide();

        Intent intent = getIntent();
        String date = intent.getStringExtra(MainActivity.sDate);
        String message = intent.getStringExtra(MainActivity.message);

        TextView textView = findViewById(R.id.textView2);
        textView.setText("Days left until your event " + message + " on " + date);
    }
}
