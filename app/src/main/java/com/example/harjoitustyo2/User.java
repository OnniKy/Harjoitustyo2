package com.example.harjoitustyo2;

import android.media.Image;

import java.util.Date;

public class User {
    int height;
    double weight;
    int birthYear;
    String municipality;
    double bmi;
    String username;
    String gender;

    public User(int height, double weight, int birthYear, String municipality, String username, String gender){
        this.height = height;
        this.weight = weight;
        this.birthYear = birthYear;
        this.municipality = municipality;
        this.calulateBmi(this.height, this.weight);
        this.username = username;
        this.gender = gender;


    }

    private void calulateBmi(int height, double weight){

        this.bmi = bmi;
    }

    public void updateWeight(){

    }
    public String getUsername(){
        return username;
    }

    public int getBirthYear() { return birthYear; }

    public double getWeight() { return weight; }

    public double getBmi() { return weight; }

}
