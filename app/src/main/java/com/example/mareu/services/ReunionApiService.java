package com.example.mareu.services;

import com.example.mareu.models.TheDay;

import java.util.ArrayList;
import java.util.Map;

public interface ReunionApiService {

    /**
     * getByDate
     * @return a List filtered by date
     */
    ArrayList<ArrayList<String>> getByDate(String date);

    /**
     * getByRoom
     * @return a List filtered by room
     */
    ArrayList<ArrayList<String>> getByRoom(String room, String date);

    /**
     * getByHour
     * @return a List filtered by hour
     */
    ArrayList<ArrayList<String>> getByHour(String hour, String date);

    /**
     * addReunion
     * @param date
     * @param room
     * @param hour
     * @param reunionTime
     * @param nomReunion
     * @param participants
     * @return boolean
     */
    Boolean addReunion(String date, String room, String hour, String reunionTime, String nomReunion, String participants);

    /**
     * removeReunion
     * @param date
     * @param room
     * @param hour
     * @param name
     * @return boolean
     */
    Boolean removeReunion(String date, String room, String hour, String name);

    /**
     * use for tests
     * @return the model
     */
    Map<String,TheDay> getMDay();
}
