package com.example.harjoitustyo2;

import android.media.Image;

import java.util.Date;

public class User {
    int height;
    double weight;
    Date birthYear;
    String municipality;
    double bmi;
    String username;
    String gender;

    public User(int height, double weight, Date birthYear, String municipality, double bmi, String username, String gender){
        this.height = height;
        this.weight = weight;
        this.birthYear = birthYear;
        this.municipality = municipality;
        this.bmi = bmi;
        this.username = username;
        this.gender = gender;

    }

    private void calulateBmi(int height, double weight){

    }

    public void updateWeight(){

    }
    public String getUsername(){
        return username;
    }

}
