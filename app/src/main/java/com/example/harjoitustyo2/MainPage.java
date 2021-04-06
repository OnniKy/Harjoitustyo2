package com.example.harjoitustyo2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainPage extends AppCompatActivity {

    JSONRequest jsonRequest;
    EditText dailyWeight, dailyClimate, bmiTextbox, changeInWeight, changeInClimate;
    TextView totalEmission;
    Button button, button2;
    String emission = "____";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_page);
        dailyWeight = findViewById(R.id.dailyWeight);
        dailyClimate = findViewById(R.id.dailyClimate);
        bmiTextbox = findViewById(R.id.bmiTextbox);
        changeInWeight = findViewById(R.id.changeInWeight);
        changeInClimate = findViewById(R.id.changeInClimate);
        button = findViewById(R.id.toClimateControl);
        totalEmission = findViewById(R.id.textView14);

        jsonRequest = new JSONRequest();



        setTexts();

        button.setOnClickListener(v -> {
            Intent intent = new Intent(MainPage.this, ClimateControl.class);
            startActivity(intent);
        });

        //jsonRequest.sunmetodi(); //TODO



    }

    public void setTexts(){
        dailyWeight.setText("Your weight is 1000 Kg");
        dailyClimate.setText("You produce 2 coals");
        bmiTextbox.setText("Your bodymassindex is 2 ");
        changeInWeight.setText("+2kg");
        changeInClimate.setText("+5t");
        emission = getIntent().getStringExtra("Total");
        if (emission == null){
            emission = "___";
        }
        totalEmission.setText("Total CO2 emission: " + emission + " kg per year");


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