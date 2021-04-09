package com.example.harjoitustyo2;

import android.media.Image;

import java.util.Date;

public class User {
    String name, username, password, municipality, gender;
    int height, weight, birthyear;

    public User(String name, String username, String password, String municipality, String gender, int height, int weight, int birthyear){
        this.name = name;
        this.username = username;
        this.password = password;
        this.municipality = municipality;
        this.gender = gender;
        this.height = height;
        this.weight = weight;
        this.birthyear = birthyear;

    }

    public String getName() {
        return name;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getMunicipality() {
        return municipality;
    }

    public String getGender() {
        return gender;
    }

    public int getHeight() {
        return height;
    }

    public int getWeight() {
        return weight;
    }

    public int getBirthyear() {
        return birthyear;
    }
}
