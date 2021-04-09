package com.example.harjoitustyo2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import org.json.JSONObject;

public class MainPage extends AppCompatActivity {

    JSONRequest jsonRequest;
    JSONFileControl jsonFileControl;
    EditText dailyWeight, dailyClimate, bmiTextbox, changeInWeight, changeInClimate;
    TextView totalEmission;
    Button button, button2;
    ImageButton logOut;
    String emission = null;
    String weight;
    Context context;
    String username;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_page);
        dailyWeight = findViewById(R.id.dailyWeight);
        dailyClimate = findViewById(R.id.dailyClimate);
        bmiTextbox = findViewById(R.id.bmiTextbox);
        /*changeInWeight = findViewById(R.id.changeInWeight);
        changeInClimate = findViewById(R.id.changeInClimate);*/
        button = findViewById(R.id.toClimateControl);
        button2 = findViewById(R.id.button);
        logOut = findViewById(R.id.imageButton);

        totalEmission = findViewById(R.id.textView14);
        context = MainPage.this;

        jsonRequest = new JSONRequest();
        jsonFileControl = new JSONFileControl();

        username = getIntent().getStringExtra("Username");


        try {
            weight = jsonFileControl.readLog(context, username, "Weight"); //TODO
            System.out.println(weight);
            dailyWeight.setText("Your weight is: " + weight + " at the moment");
            emission = jsonFileControl.readLog(context, username, "Total");
            System.out.println("EMISSION: " + emission);


        } catch (Exception e) {
            e.printStackTrace();
        }
        if (emission != null){
            totalEmission.setText("Total CO2 emission: " + emission + " kg per year");
        } else {
            totalEmission.setText("Calculate your emission on Climate \nControl page!");
        }

        setTexts();

        // Buttons OnClickListeners
        button.setOnClickListener(v -> {
            Intent intent = new Intent(MainPage.this, ClimateControl.class);
            intent.putExtra("Username",getIntent().getStringExtra("Username"));
            startActivity(intent);
        });

        button2.setOnClickListener(v -> {
            Intent intent = new Intent(MainPage.this, WeightControl.class);
            intent.putExtra("Username", getIntent().getStringExtra("Username"));
            startActivity(intent);
        });

        logOut.setOnClickListener(v -> {
            Intent intent = new Intent(MainPage.this, MainActivity.class);
            startActivity(intent);
        });


    }

    public void setTexts(){
        dailyClimate.setText("You produce 2 coals");
        bmiTextbox.setText("Your bodymassindex is 2 ");
        //changeInWeight.setText("+2kg");
        //changeInClimate.setText("+5t");




    }


}