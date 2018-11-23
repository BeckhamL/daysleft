package com.example.beckhamlam.daysleft;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

public class eventAdapter extends RecyclerView.Adapter<eventAdapter.ViewHolder> {

    private ArrayList<event> events;
    private Context context;
    public static final String message = "message";
    public static final String daysLeft = "daysLeft";

    public eventAdapter(ArrayList<event> events, Context context) {
        this.events = events;
        this.context = context;
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        public TextView event_name;
        public TextView days_left;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            event_name = itemView.findViewById(R.id.event_name);
            days_left = itemView.findViewById(R.id.days_left);

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            int position = getAdapterPosition();
            event event = events.get(position);
            Intent intent = new Intent(context, itemPopup.class);
            intent.putExtra(message, event.getEvent());
            intent.putExtra(daysLeft, String.valueOf(event.getDaysLeft()));
            context.startActivity(intent);
        }
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        final Context context = viewGroup.getContext();
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
        event_name.setText("Event: " + event.getEvent() + "\n" + "Date: " + event.getFormattedDate());

        TextView days_left = viewHolder.days_left;
        days_left.setText(String.valueOf(event.getDaysLeft()) + " day(s)");

    }

    @Override
    public int getItemCount() {
        return events.size();
    }
}
