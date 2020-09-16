package com.example.mareu.services;

import com.example.mareu.models.Reunion;
import com.example.mareu.models.TheDay;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public interface ReunionApiService {

    /**
     * getByDate
     * @return
     */
    ArrayList<ArrayList<String>> getByDate(String date);

    /**
     * getByRoom
     * @return
     */
    ArrayList<ArrayList<String>> getByRoom(String room, String date);

    /**
     * getByHour
     * @return
     */
    ArrayList<ArrayList<String>> getByHour(String hour, String date);

    /**
     * addReunion
     * @param date
     * @param room
     * @param hour
     * @param nomReunion
     * @param participants
     */
    String addReunion(String date, String room, String hour, String reunionTime, String nomReunion, String participants);

    /**
     * removeReunion
     * @param date
     * @param room
     * @param hour
     */
    Boolean removeReunion(String date, String room, String hour, String name);

    /**
     * use for tests
     * @return
     */
    Map<String,TheDay> getMDay();
}
