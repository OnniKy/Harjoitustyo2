package com.example.harjoitustyo2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class MainPage extends AppCompatActivity {

    EditText dailyWeight, dailyClimate;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_page);
        dailyWeight = findViewById(R.id.dailyWeight);
        dailyClimate = findViewById(R.id.dailyClimate);
        setTexts();

    }

    public void setTexts(){
        dailyWeight.setText("Päivän painosi on 1000 Kg");
        dailyClimate.setText("Nykyinen hiilikuormasi on 1 kuorma");

    }

    public void logOut(View v){
        Intent intent = new Intent(MainPage.this, MainActivity.class);
        startActivity(intent);
    }


}