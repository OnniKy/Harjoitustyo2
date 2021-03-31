package com.example.harjoitustyo2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.os.StrictMode;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;

public class ClimateControl extends AppCompatActivity {
    JSONRequest jsonRequest;
    Button submit;
    Context context;
    SeekBar beefBar, porkBar, fishBar, cheeseBar, dairyBar, riceBar, vegetablesBar, eggBar;
    TextView beefView, porkView,fishView, cheeseView, dairyView, riceView, vegetableView, eggView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_climate_control);
        context = ClimateControl.this;

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        beefBar = (SeekBar) findViewById(R.id.seekBar1);
        porkBar = (SeekBar) findViewById(R.id.seekBar2);
        fishBar = (SeekBar) findViewById(R.id.seekBar3);
        cheeseBar = (SeekBar) findViewById(R.id.seekBar4);
        dairyBar = (SeekBar) findViewById(R.id.seekBar5);
        riceBar = (SeekBar) findViewById(R.id.seekBar6);
        vegetablesBar = (SeekBar) findViewById(R.id.seekBar7);
        eggBar = (SeekBar) findViewById(R.id.seekBar8);

        beefView = (TextView) findViewById(R.id.beefView);
        porkView = (TextView) findViewById(R.id.porkView);
        fishView = (TextView) findViewById(R.id.fishView);
        cheeseView = (TextView) findViewById(R.id.cheeseView);
        dairyView = (TextView) findViewById(R.id.dairyView);
        riceView = (TextView) findViewById(R.id.riceView);
        vegetableView = (TextView) findViewById(R.id.vegetableView);
        eggView = (TextView) findViewById(R.id.eggView);

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