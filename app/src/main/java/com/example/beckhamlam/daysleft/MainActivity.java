package com.example.beckhamlam.daysleft;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.Toast;
import java.util.Calendar;

public class MainActivity extends AppCompatActivity {

    public static final String sDate = "date";
    public static final String message = "message";
    int year;
    int month;
    int day;
    Calendar cal;
    String tDate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().hide();


        Button selectDate = findViewById(R.id.button2);

        selectDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cal = Calendar.getInstance();
                year = cal.get(Calendar.YEAR);
                month = cal.get(Calendar.MONTH);
                day = cal.get(Calendar.DAY_OF_MONTH);
                DatePickerDialog datePickerDialog = new DatePickerDialog(MainActivity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        tDate = dayOfMonth + "/" + (month + 1) + "/" + year;
                    }
                }, year, month , day);
                datePickerDialog.show();
            }
        });
    }

    public void sendMessage(View view) {
        Intent intent = new Intent(this, items.class);
        TextView editText = findViewById(R.id.editText);
        String messageTextView = editText.getText().toString();

        intent.putExtra(sDate, tDate);
        intent.putExtra(message, messageTextView);
        startActivity(intent);

        Toast toast = Toast.makeText(getApplicationContext(), "Event added!", Toast.LENGTH_SHORT);
        toast.show();
    }

//    public void addData(String event, String formattedDate) {
//        boolean insertDate = dataBaseHelper.addData(event, formattedDate);
//
//        if (insertDate) {
//            Toast.makeText(this, "Successfully added", Toast.LENGTH_SHORT).show();
//        }
//        else {
//            Toast.makeText(this, "failed added", Toast.LENGTH_SHORT).show();
//        }
//    }
}
