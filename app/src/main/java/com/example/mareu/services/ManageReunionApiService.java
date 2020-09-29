package com.example.mareu.services;

import com.example.mareu.models.Reunion;
import com.example.mareu.models.Room;
import com.example.mareu.models.TheDay;

import java.util.ArrayList;
import java.util.Map;
import java.util.Set;

public class ManageReunionApiService implements ReunionApiService {
    /**
     * the model contening the reunions
     */
    private Map<String,TheDay> mDay = ManageReunionGenerator.generatePlanning();

    @Override
    public ArrayList<ArrayList<String>> getByDate(String date) {
        ArrayList<ArrayList<String>> filteredByDate = new ArrayList<ArrayList<String>>();
        if(!mDay.containsKey(date))return filteredByDate;
        Map<String, Room> roomList = mDay.get(date).getRoomList();
        Set<String> byRoom = roomList.keySet();
        for(String room : byRoom){
            Map<String,Reunion> reunionList = roomList.get(room).getReunionList();
            Set<String> byReunionHour = reunionList.keySet();
            for (String hour : byReunionHour){
                Reunion reu = reunionList.get(hour);
                ArrayList<String> reunionByDate = new ArrayList<String>();
                if (reu != null){
                    reunionByDate.add(reu.getNom());//0
                    reunionByDate.add(hour);        //1
                    reunionByDate.add(room);        //2
                    reunionByDate.add(reu.getParticipants());//3
                    reunionByDate.add(date);//4
                    filteredByDate.add(reunionByDate);
                }
            }
        }
        return filteredByDate;
    }

    @Override
    public ArrayList<ArrayList<String>> getByRoom(String room, String date) {
        ArrayList<ArrayList<String>> filteredByDate = new ArrayList<ArrayList<String>>();
        if(!mDay.containsKey(date))return filteredByDate;
            Map<String,Reunion> reunionList = mDay.get(date).getRoomList().get(room).getReunionList();
            Set<String> byReunionHour = reunionList.keySet();
            for (String hour : byReunionHour){
                Reunion reu = reunionList.get(hour);
                ArrayList<String> reunionByDate = new ArrayList<String>();
                if (reu != null){
                    reunionByDate.add(reu.getNom());//0
                    reunionByDate.add(hour);        //1
                    reunionByDate.add(room);        //2
                    reunionByDate.add(reu.getParticipants());//3
                    reunionByDate.add(date);//4
                    filteredByDate.add(reunionByDate);
                }
            }
        return filteredByDate;
    }

    @Override
    public ArrayList<ArrayList<String>> getByHour(String hour, String date) {
        ArrayList<ArrayList<String>> filteredByDate = new ArrayList<ArrayList<String>>();
        if(!mDay.containsKey(date))return filteredByDate;
        Map<String, Room> roomList = mDay.get(date).getRoomList();
        Set<String> byRoom = roomList.keySet();
        for(String room : byRoom){
            Map<String,Reunion> reunionList = roomList.get(room).getReunionList();
                Reunion reu = reunionList.get(hour);
                ArrayList<String> reunionByDate = new ArrayList<String>();
                if (reu != null){
                    reunionByDate.add(reu.getNom());//0
                    reunionByDate.add(hour);        //1
                    reunionByDate.add(room);        //2
                    reunionByDate.add(reu.getParticipants());//3
                    reunionByDate.add(date);//4
                    filteredByDate.add(reunionByDate);
                }
        }
        return filteredByDate;
    }

    @Override
    public Boolean addReunion(String date, String room, String hour, String reunionTime, String nomReunion, String participants) {
        if (!mDay.containsKey(date))mDay.put(date,new TheDay());
        return mDay.get(date).getRoomList().get(room).addReunion(hour, new Reunion(nomReunion, participants));
    }

    @Override
    public Boolean removeReunion(String date, String room, String hour, String name) {
        //TODO boucle pour enlever toute la durée de la reunion
        return mDay.get(date).getRoomList().get(room).removeReunion(hour, name);
    }

    /**
     * Use for Unit Tests
     * @return the model "mDay"
     */
    public Map<String,TheDay> getMDay(){
        return this.mDay;
    }
}
