package com.example.beckhamlam.daysleft;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class itemPopup_delete extends AppCompatActivity {

    dataBaseHelper dataBaseHelper = new dataBaseHelper(this);
    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_popup_delete);
        getSupportActionBar().hide();

        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);
        int width = dm.widthPixels;
        int height = dm.heightPixels;
        getWindow().setLayout( (int) (width * 0.8), (int) (height * 0.55));

        Button buttonCancel = findViewById(R.id.button5);
        Button buttonDelete = findViewById(R.id.button6);
        TextView textView = findViewById(R.id.textView4);

        Intent intent = getIntent();

        final String event = intent.getStringExtra(itemPopup.message);

        textView.setText(event);

        buttonCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        buttonDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dataBaseHelper.deleteEvent(event);
                finish();
            }
        });
    }
}
