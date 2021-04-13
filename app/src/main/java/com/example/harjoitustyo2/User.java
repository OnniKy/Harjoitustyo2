package com.example.harjoitustyo2;

import android.content.Context;
import android.media.Image;

import java.util.Date;

public class User {
    byte[] saltId;
    String name, username, password, municipality, gender, height;
    int weight, birthyear;
    DatabaseHelper dbHelper;

    public User(Context context, String user){
        dbHelper = new DatabaseHelper(context);

        this.saltId = dbHelper.getId(user);
        this.name = dbHelper.getName(user);
        this.username = user;
        this.password = dbHelper.getPassword(user);
        this.municipality = dbHelper.getMunicipality(user);
        this.gender = dbHelper.getGender(user);
        this.height = dbHelper.getHeight(user);
        this.weight = dbHelper.getWeight(user);
        this.birthyear = dbHelper.getBirthyear(user);

    }

    public User(byte[] saltId, String name, String username, String password, String municipality, String gender, String height, int weight, int birthyear){
        this.saltId = saltId;
        this.name = name;
        this.username = username;
        this.password = password;
        this.municipality = municipality;
        this.gender = gender;
        this.height = height;
        this.weight = weight;
        this.birthyear = birthyear;

    }

    public byte[] getSaltId() {
        return saltId;
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

    public String getHeight() {
        return height;
    }

    public int getWeight() {
        return weight;
    }

    public int getBirthyear() {
        return birthyear;
    }
}
