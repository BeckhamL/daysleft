package com.example.beckhamlam.daysleft;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class itemPopup extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_popup);
        getSupportActionBar().hide();

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
        String message = intent.getStringExtra(eventAdapter.message);
        String daysLeft = intent.getStringExtra(eventAdapter.daysLeft);

        eventText.setText(message);
        daysLeftText.setText(daysLeft);

        buttonEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "edit", Toast.LENGTH_SHORT).show();
            }
        });

        buttonDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "delete", Toast.LENGTH_SHORT).show();
            }
        });

    }
}
