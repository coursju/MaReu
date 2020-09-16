package com.example.mareu.models;

import android.graphics.Color;

import java.util.HashMap;
import java.util.Map;

public class TheDay {

    private Map<String,Room> roomList;
    private int[] color = new int[]{
            Color.BLACK,
            Color.BLUE,
            Color.CYAN,
            Color.GRAY,
            Color.GREEN,
            Color.MAGENTA,
            Color.RED,
            Color.WHITE,
            Color.YELLOW,
            Color.LTGRAY};

    public TheDay() {
        this.initRoomList();
    }

    public Map<String, Room> getRoomList() {
        return roomList;
    }

    private void initRoomList(){
        this.roomList = new HashMap<String, Room>();
        for(int i = 0; i<10; i++){
            String str = "salle "+(i+1);
            //System.out.println("valeur str="+str);
            roomList.put(str,new Room(color[i]));
        }
    }
}
