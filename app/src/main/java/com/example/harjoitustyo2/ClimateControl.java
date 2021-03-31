package com.example.harjoitustyo2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.View;
import android.widget.Button;

public class ClimateControl extends AppCompatActivity {
    JSONRequest jsonRequest;
    Button submit;
    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_climate_control);
        context = ClimateControl.this;

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);


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