package com.example.harjoitustyo2;


import android.content.Context;
import android.os.Environment;
import android.util.Log;

import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Serializable;
import java.net.PasswordAuthentication;
import java.util.ArrayList;
import java.util.Date;

public class UserManager implements Serializable {
    private static UserManager instance = null;
    private final String logName = "Accounts.csv";
    Context context;

    protected UserManager(Context context){
        try{
            OutputStreamWriter osw = new OutputStreamWriter(context.openFileOutput(logName, Context.MODE_PRIVATE));

        } catch (IOException e) {
            Log.e("IOException", "Virhe syötteessä");
        }  finally {
            System.out.println("Kirjoitettu");
        }

        System.out.println("KKK");


    }

    // Use singleton principal in creating UserManager
    public static UserManager getInstance(Context context){
        if (instance == null){
            instance = new UserManager(context);
        } else {
            System.out.println("Instance already exists.");
        }
        return instance;
    }

    public void createUser(String h, String w, String by, String municipality, String username, String name, String gender) {

        // Changing values to the right type
        int height = Integer.valueOf(h);
        double weight = Double.parseDouble(w);
        int birthyear = Integer.valueOf(by);

        //Creating new user
        User myUser = new User(height, weight, birthyear, municipality, username, gender);

        //userList.add(myUser);

    }

    public void checkUsername(String user){
/*
        for (int i = 0; i < userList.size() ; i++) {
            System.out.println(userList.get(i).getUsername());
            if (user.equals(userList.get(i).getUsername())){
                System.out.println("****** MATCH ******");
            } else {
                System.out.println("*NO MATCH*");
            }
        }

 */
    }



    public void checkPassword(){

    }

    public void logOut(){

    }
}
