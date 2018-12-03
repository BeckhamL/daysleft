package com.example.beckhamlam.daysleft;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class listAdapter extends ArrayAdapter<event> {

    private Context context;
    private int resource;

    public listAdapter(Context context, int resource, ArrayList<event> list) {
        super(context, resource, list);
        this.context = context;
        this.resource = resource;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        String event = getItem(position).getEvent();
        String formattedDate = getItem(position).getFormattedDate();
        long difference = getItem(position).getDaysLeft();

        LayoutInflater layoutInflater = LayoutInflater.from(getContext());

        if (convertView == null) {
            convertView = layoutInflater.inflate(resource, parent, false);
        }

        TextView eventText = convertView.findViewById(R.id.textview5);
        TextView date = convertView.findViewById(R.id.textdate);
        TextView differenceText = convertView.findViewById(R.id.textView6);

        eventText.setText(event);
        date.setText(formattedDate);
        differenceText.setText(String.valueOf(difference));

        return convertView;
    }
}
