package com.example.harjoitustyo2;

import java.io.Serializable;
import java.net.PasswordAuthentication;
import java.util.ArrayList;
import java.util.Date;

public class UserManager implements Serializable {
    private static UserManager instance = null;
    ArrayList<User> userList;

    protected UserManager(){
        userList = new ArrayList<User>();
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

    public void createUser(String h, String w, String by, String municipality, String username,String name, String gender) {

        int height = Integer.valueOf(h);
        double weight = Double.parseDouble(w);
        int birthyear = Integer.valueOf(by);


        //System.out.println(name +  + weight + birthyear + municipality + username + gender);
        User myUser = new User(height, weight, birthyear, municipality, username, gender);
        userList.add(myUser);

        for (int i = 0; i < userList.size() ; i++) {
            System.out.println(userList.get(i).getUsername() + " Weight: " + userList.get(i).getWeight());
        }
        System.out.println(userList.size());
    }

    public void checkUsername(String user){

        System.out.println("TSEKKAILLAA" + userList.size());
        for (int i = 0; i < userList.size() ; i++) {
            System.out.println(userList.get(i).getUsername());
            if (user.equals(userList.get(i).getUsername())){
                System.out.println("****** MATCH ******");
            } else {
                System.out.println("*NO MATCH*");
            }
        }
    }

    public void checkPassword(){

    }

    public void logOut(){

    }
}
