package com.example.mareu.DI;

import com.example.mareu.services.ManageReunionApiService;
import com.example.mareu.services.ReunionApiService;

public class DI {

    /**
     * get a ManageReunionApiService
     */
    public static ReunionApiService service = new ManageReunionApiService();
}
