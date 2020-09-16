package com.example.mareu;

import com.example.mareu.DI.DI;
import com.example.mareu.services.ReunionApiService;

import org.junit.Before;
import org.junit.Test;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

public class ManageReunionApiServiceTest {
    private ReunionApiService mService;
    private String todayTest;

    @Before
    public void setup(){
        mService = DI.service;
        todayTest = new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault()).format(new Date());
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
}
