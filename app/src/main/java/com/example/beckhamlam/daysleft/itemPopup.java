package com.example.beckhamlam.daysleft;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class itemPopup extends AppCompatActivity {

    public static final String message = "message";
    public static final String message1 = "formattedDate";
    public static final String positionNum = "positionNum";
    static itemPopup itemPopup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_popup);
        getSupportActionBar().hide();

        itemPopup = this;

        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);
        int width = dm.widthPixels;
        int height = dm.heightPixels;
        getWindow().setLayout( (int) (width * 0.8), (int) (height * 0.55));

        Button buttonEdit = findViewById(R.id.button3);
        Button buttonDelete = findViewById(R.id.button4);
        TextView eventText = findViewById(R.id.textView2);
        TextView daysLeftText = findViewById(R.id.textView3);

        Intent intent = getIntent();
        final String event = intent.getStringExtra(eventAdapter.message);
        String daysLeft = intent.getStringExtra(eventAdapter.daysLeft);
        final String formattedDate = intent.getStringExtra(eventAdapter.formattedDate);
        final int position = intent.getIntExtra(listActivity.positionNum, 0);

        eventText.setText(event);
        daysLeftText.setText(daysLeft);

        buttonEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent2 = new Intent(getApplicationContext(), itemPopup_edit.class);
                intent2.putExtra(message, event);
                intent2.putExtra(message1, formattedDate);
                intent2.putExtra(positionNum, position);
                startActivity(intent2);
            }
        });

        buttonDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent1 = new Intent(getApplicationContext(), itemPopup_delete.class);
                intent1.putExtra(message, event);
                intent1.putExtra(positionNum, position);
                startActivity(intent1);
            }
        });

    }

    public static itemPopup getInstance() {
        return itemPopup;
    }
}
