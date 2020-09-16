package com.example.mareu.events;


public class DeleteReunionEvent {

    /**
     * Reunion to delete
     */
    public String date;
    public String room;
    public String hour;
    public String name;

    /**
     * constructor
     * @param date
     * @param room
     * @param hour
     * @param name
     */
    public DeleteReunionEvent(String date, String room, String hour, String name) {
        this.date = date;
        this.room = room;
        this.hour = hour;
        this.name = name;
    }
}
