package com.example.harjoitustyo2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class MainPage extends AppCompatActivity {

    EditText dailyWeight, dailyClimate, bmiTextbox, changeInWeight, changeInClimate;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_page);
        dailyWeight = findViewById(R.id.dailyWeight);
        dailyClimate = findViewById(R.id.dailyClimate);
        bmiTextbox = findViewById(R.id.bmiTextbox);
        changeInWeight = findViewById(R.id.changeInWeight);
        changeInClimate = findViewById(R.id.changeInClimate);
        setTexts();

    }

    public void setTexts(){
        dailyWeight.setText("Your weight is 1000 Kg");
        dailyClimate.setText("You produce 2 coals");
        bmiTextbox.setText("Your bodymassindex is 2 ");
        changeInWeight.setText("+2kg");
        changeInClimate.setText("+5t");


    }

    public void logOut(View v){
        Intent intent = new Intent(MainPage.this, MainActivity.class);
        startActivity(intent);
    }
    public void weigthControl(View v){
        Intent intent = new Intent(MainPage.this, WeightControl.class);
        startActivity(intent);
    }


}