package com.example.beckhamlam.daysleft;
import java.util.ArrayList;

public class event {
    private String event;
    private long daysLeft;
    private String formattedDate;

    public event(String event, String formattedDate, long daysLeft) {
        this.event = event;
        this.formattedDate = formattedDate;
        this.daysLeft = daysLeft;
    }

    public long getDaysLeft() {
        return daysLeft;
    }

    public String getEvent() {
        return event;
    }

    public String getFormattedDate() {
        return formattedDate;
    }

    public static ArrayList<event> createEventList(event event) {

        ArrayList<event> list = new ArrayList<>();

        list.add(event);
        return list;
    }

    public void setDaysLeft(long daysLeft) {
        this.daysLeft = daysLeft;
    }

    public void setEvent(String event) {
        this.event = event;
    }

    public void setFormattedDate(String formattedDate) {
        this.formattedDate = formattedDate;
    }
}
