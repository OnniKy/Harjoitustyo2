package com.example.harjoitustyo2;

public class EntryManager {
    private static EntryManager instance = null;
    Entry entry;

    protected EntryManager(){

    }

    // Use singleton principal in creating EntryManager
    public static EntryManager getInstance(){
        if (instance == null){
            instance = new EntryManager();
        } else {
            System.out.println("Instance already exists.");
        }
        return instance;
    }

    public void SaveEntries(Entry e){

    }

    public void getEntry(int s) {

    }

    public void readEntries(){

    }
}
