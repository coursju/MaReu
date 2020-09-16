package com.example.mareu.models;

import java.util.HashMap;
import java.util.Map;

public class Room {

    private int mColor;
    private Map<String,Reunion> reunionList;
    private String[] mHour = new String[]{"09","10","11","12","13","14","15","16","17","18","19"};
    private String[] mMinute = new String[]{"00","15","30","45"};

    public Room(int color) {
        this.mColor = color;
        initReunionList();
    }

    public int getColor() {
        return mColor;
    }

    public Map<String, Reunion> getReunionList() {
        return reunionList;
    }

    public Boolean addReunion(String hour, Reunion reunion){
        if (this.reunionList.get(hour) == null) {
            this.reunionList.put(hour, reunion);
            return true;
        }
        return false;
    }

    public Boolean removeReunion(String hour, String reunionName){
       if(this.reunionList.get(hour).getNom().contains(reunionName)){
            this.reunionList.put(hour,null);
            return true;
        }
        return false;
    }

    private void initReunionList(){
        this.reunionList = new HashMap<String, Reunion>();
        for(int i = 0; i< 11; i++){
            for(int j =0; j< 4; j++){
                String str = mHour[i]+":"+mMinute[j];
                reunionList.put(str,null);
            }
        }
    }
}
