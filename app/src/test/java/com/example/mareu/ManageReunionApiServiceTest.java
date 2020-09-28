package com.example.mareu;

import com.example.mareu.DI.DI;
import com.example.mareu.models.Reunion;
import com.example.mareu.models.TheDay;
import com.example.mareu.services.ReunionApiService;

import org.junit.Before;
import org.junit.Test;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Map;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

public class ManageReunionApiServiceTest {
    private ReunionApiService mService;
    private String todayTest;
    private Map<String, TheDay> dayTest;

    @Before
    public void setup(){
        mService = DI.service;
        todayTest = new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault()).format(new Date());
        dayTest =  DI.service.getMDay();
    }

    @Test
    public void getByDateTest(){
        assertNotNull(mService.getByDate(todayTest));
        assertTrue(mService.getByDate(todayTest).size() >= 4);
    }

    @Test
    public void getByRoomTest(){
        assertNotNull(mService.getByRoom("salle 1",todayTest));
        assertTrue(mService.getByRoom("salle 1",todayTest).size()>=1);
        assertTrue(mService.getByRoom("salle 1",todayTest).get(0).get(0).contains("reunion"));
    }

    @Test
    public void getByHourTest(){
        assertNotNull(mService.getByHour("14:30",todayTest));
        assertTrue(mService.getByHour("14:30",todayTest).size()>=1);
        assertTrue(mService.getByHour("14:30",todayTest).get(0).get(0).contains("brainstorming"));
    }

    @Test
    public void addReunionTest(){
        assertTrue(dayTest.get(todayTest)
                .getRoomList()
                .get("salle 1")
                .addReunion("19:00",new Reunion(new String("reunion"), "annie@lamzone.com, kevin@lamzone.com")));
    }

    @Test
    public void removeReunion(){
        assertTrue(dayTest.get(todayTest)
                .getRoomList()
                .get("salle 1")
                .addReunion("19:00",new Reunion(new String("reunion"), "annie@lamzone.com, kevin@lamzone.com")));
        assertTrue(dayTest.get(todayTest)
                .getRoomList()
                .get("salle 1")
                .removeReunion("19:00","reunion"));
    }
}
