package com.example.harjoitustyo2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.os.StrictMode;
import android.widget.Button;
import android.widget.SeekBar;

public class ClimateControl extends AppCompatActivity {
    JSONRequest jsonRequest;
    Button submit;
    Context context;
    SeekBar beefBar, porkBar, fishBar, cheeseBar, dairyBar, riceBar, vegetablesBar, eggBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_climate_control);
        context = ClimateControl.this;

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        beefBar = findViewById(R.id.seekBar1);
        porkBar = findViewById(R.id.seekBar2);
        fishBar = findViewById(R.id.seekBar3);
        cheeseBar = findViewById(R.id.seekBar4);
        dairyBar = findViewById(R.id.seekBar5);
        riceBar = findViewById(R.id.seekBar6);
        vegetablesBar = findViewById(R.id.seekBar7);
        eggBar = findViewById(R.id.seekBar8);

        jsonRequest = new JSONRequest();
        submit = findViewById(R.id.button2);

        submit.setOnClickListener(v -> {
            String diet = "omnivore";
            String beef = "2";
            String fish = "1";
            String pork = "5";
            String dairy = "3";
            String cheese = "5";
            String rice = "2";
            String egg = "0";
            String salad = "4";

            jsonRequest.readJSON(diet, beef, fish, pork, dairy, cheese, rice, egg, salad);
        });

    }
}