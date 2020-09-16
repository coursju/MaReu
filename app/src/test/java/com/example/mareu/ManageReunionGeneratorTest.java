package com.example.mareu;

import com.example.mareu.DI.DI;
import com.example.mareu.models.Reunion;
import com.example.mareu.models.TheDay;

import org.junit.Before;
import org.junit.Test;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Map;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;

public class ManageReunionGeneratorTest {

    private Map<String, TheDay> dayTest;
    private String todayTest;

    @Before
    public void setup(){
        todayTest = new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault()).format(new Date());
        dayTest =  DI.service.getMDay();
    }

    @Test
    public void manageReunionGeneratorTest(){
        assertNotNull(dayTest);
    }

    @Test
    public void todayDateTest(){
        assertTrue(dayTest.containsKey(todayTest));
    }

    @Test
    public void contentValidation(){
        System.out.println(dayTest.get(todayTest).getRoomList().get("salle 1").getReunionList().get("09:00").getNom());
        assertTrue(dayTest.get(todayTest).getRoomList().containsKey("salle 1"));
        assertTrue(dayTest.get(todayTest).getRoomList().containsKey("salle 10"));
        assertTrue(dayTest.get(todayTest).getRoomList().get("salle 1").getReunionList().containsKey("09:00"));
        assertTrue(dayTest.get(todayTest).getRoomList().get("salle 1").getReunionList().containsKey("19:30"));

        assertTrue(dayTest.get(todayTest)
                .getRoomList()
                .get("salle 1")
                .addReunion("19:00",new Reunion(new String("reunion"), "annie@lamzone.com, kevin@lamzone.com")));
        assertFalse(dayTest.get(todayTest)
                .getRoomList()
                .get("salle 1")
                .addReunion("19:00",new Reunion(new String("reunion"), "annie@lamzone.com, kevin@lamzone.com")));
        assertTrue(dayTest.get(todayTest)
                .getRoomList()
                .get("salle 1")
                .removeReunion("19:00","reunion"));
        assertTrue(dayTest.get(todayTest).getRoomList().get("salle 1").getReunionList().get("09:00").getNom().contains("reunion"));


    }
}
