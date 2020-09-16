package com.example.mareu.services;

import com.example.mareu.models.Reunion;
import com.example.mareu.models.TheDay;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

public abstract class ManageReunionGenerator {

    public static String today = new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault()).format(new Date());

    private static TheDay planning = planningGenerator();

    public static Map<String, TheDay> generatePlanning(){
        return new HashMap<>(getTodayPlanning());
    }

    private static Map<String, TheDay> getTodayPlanning(){
        Map<String, TheDay> mPlan = new HashMap<>();
        mPlan.put(today,planning);
        return mPlan;
    }

    private static TheDay planningGenerator(){
        TheDay mDay = new TheDay();
        mDay.getRoomList()
                .get("salle 1")
                .addReunion("09:00",new Reunion("reunion", "annie@lamzone.com, kevin@lamzone.com"));
        mDay.getRoomList()
                .get("salle 2")
                .addReunion("11:15",new Reunion("budget", "katia@lamzone.com, john@lamzone.com, michael@lamzone.com"));
        mDay.getRoomList()
                .get("salle 7")
                .addReunion("14:30",new Reunion("brainstorming", "jean@lamzone.com, kevin@lamzone.com"));
        mDay.getRoomList()
                .get("salle 5")
                .addReunion("18:45",new Reunion("new product", "jack@lamzone.com, murielle@lamzone.com, cecile@lamzone.com"));
        return mDay;
    }

}
