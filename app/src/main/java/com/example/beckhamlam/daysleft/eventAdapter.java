package com.example.beckhamlam.daysleft;
import android.annotation.SuppressLint;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

public class eventAdapter extends RecyclerView.Adapter<eventAdapter.ViewHolder> {

    private ArrayList<event> events;

    public eventAdapter(ArrayList<event> events) {
        this.events = events;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public TextView event_name;
        public TextView days_left;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            event_name = itemView.findViewById(R.id.event_name);
            days_left = itemView.findViewById(R.id.days_left);
        }
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        Context context = viewGroup.getContext();
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View eventView = layoutInflater.inflate(R.layout.item_event, viewGroup, false);

        ViewHolder viewHolder = new ViewHolder(eventView);
        return viewHolder;
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {

        event event = events.get(i);

        TextView event_name = viewHolder.event_name;
        event_name.setText("Days left until your event: "+ event.getEvent() + " on " + event.getFormattedDate());

        TextView days_left = viewHolder.days_left;
        days_left.setText(String.valueOf(event.getDaysLeft()) + " days");

    }

    @Override
    public int getItemCount() {
        return events.size();
    }
}
