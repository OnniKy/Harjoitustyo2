package com.example.harjoitustyo2;

import java.io.Serializable;
import java.net.PasswordAuthentication;
import java.util.ArrayList;

public class UserManager implements Serializable {
    private static UserManager instance = null;
    ArrayList<User> userList;

    protected UserManager(){

    }

    // Use singleton principal in creating UserManager
    public static UserManager getInstance(){
        if (instance == null){
            instance = new UserManager();
        } else {
            System.out.println("Instance already exists.");
        }
        return instance;
    }

    public void createUser(){


        User myUser = new User(); //TODO
    }

    public void checkUsername(){

    }

    public void checkPassword(){

    }

    public void logOut(){

    }
}
