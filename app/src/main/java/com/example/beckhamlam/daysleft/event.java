package com.example.beckhamlam.daysleft;
import java.util.ArrayList;

public class event {
    private String event;
    private long daysLeft;

    public event(String event, long daysLeft) {
        this.event = event;
        this.daysLeft = daysLeft;
    }

    public long getDaysLeft() {
        return daysLeft;
    }

    public String getEvent() {
        return event;
    }

    private static int lastEventId = 0;


    public static ArrayList<event> createEventList(int numEvents) {

        ArrayList<event> list = new ArrayList<>();

        for (int i = 1; i < numEvents; i++) {
            list.add(new event("Event" + ++lastEventId, 0));
        }
        return list;
    }

}
