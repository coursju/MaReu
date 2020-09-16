package com.example.mareu.models;

import java.util.ArrayList;
import java.util.List;

public class Reunion {

    private String participants;
    private String nom;

    public Reunion(String nom, String participants) {
        this.nom = nom;
        this.participants = participants;
    }

    public String getParticipants() {
        return participants;
    }

    public void setParticipants(String participants) {
        this.participants = participants;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }
}
