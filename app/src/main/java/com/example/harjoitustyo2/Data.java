package com.example.harjoitustyo2;

import java.util.Map;

public class Data {

    private Map<String, String> map;


    public Data(){

    }

    public Data(Map<String, String> map){
        this.map = map;
    }

    public Map<String, String> getMap() {
        return map;
    }

    public void setMap(Map<String, String> map) {
        this.map = map;
    }
}
