package com.example.harjoitustyo2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import java.text.DecimalFormat;

public class MainPage extends AppCompatActivity {

    JSONRequest jsonRequest;
    JSONFileControl jsonFileControl;
    TextView totalEmission, dailyWeight, bmiView, nameView, caffeineView, municipalityView;
    CardView climateButton, weightButton, caffeineButton;
    ImageButton logOut;
    String emission = null, caffeine = null;
    String weight;
    Context context;
    String username, name;
    DatabaseHelper databaseHelper;
    double BMI;
    User user;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_page);
        dailyWeight = findViewById(R.id.weightView);
        bmiView = findViewById(R.id.bmiView);
        climateButton = findViewById(R.id.climateCard);
        weightButton = findViewById(R.id.weightCard);
        caffeineButton = findViewById(R.id.caffeineCard);
        logOut = findViewById(R.id.logOutButton);
        nameView = findViewById(R.id.nameView);
        caffeineView = findViewById(R.id.caffeineView);
        municipalityView = findViewById(R.id.municipalityView);

        totalEmission = findViewById(R.id.coEmission);
        context = MainPage.this;

        jsonRequest = new JSONRequest();
        jsonFileControl = new JSONFileControl();

        username = getIntent().getStringExtra("Username");
        databaseHelper = new DatabaseHelper(this);

        user = new User(context, username);
        name = user.getName();


        nameView.setText(name);
        municipalityView.setText(user.getMunicipality());

        try {
            weight = jsonFileControl.readLog(context, name, "Weight"); //TODO
            dailyWeight.setText("Current weight: " + weight + "kg");
            emission = jsonFileControl.readLog(context, name, "Total");
            caffeine = jsonFileControl.readLog(context, name, "Caffeine");


        } catch (Exception e) {
            e.printStackTrace();
        }

        if (emission != null){
            totalEmission.setText("Total CO2 emission: " + modifyJSON(emission) + " kg per year");
        } else {
            totalEmission.setText("Calculate your emission on Climate \nControl page!");
        }

        if (caffeine != null){
            caffeineView.setText("Today's caffeine consumption: " + modifyJSON(caffeine) + " mg!");
        } else {
            caffeineView.setText("Calculate your caffeine consumption on caffeine control!");
        }

        setBMI();


        // Buttons OnClickListeners
        climateButton.setOnClickListener(v -> {
            Intent intent = new Intent(MainPage.this, ClimateControl.class);
            intent.putExtra("Username", username);
            startActivity(intent);
        });

        weightButton.setOnClickListener(v -> {
            Intent intent = new Intent(MainPage.this, WeightControl.class);
            intent.putExtra("Username", username);
            startActivity(intent);
        });

        caffeineButton.setOnClickListener(v -> {
            Intent intent = new Intent(MainPage.this, CaffeineControl.class);
            intent.putExtra("Username", username);
            startActivity(intent);
        });

        logOut.setOnClickListener(v -> {
            Intent intent = new Intent(MainPage.this, Login.class);
            startActivity(intent);
        });

    }

    public void setBMI(){
        bmiCalculator();
        DecimalFormat df = new DecimalFormat("0.00");

        if (BMI < 18.5){
            bmiView.setText("BMI: " + df.format(BMI) + " - Underweight");
        } else if (BMI >= 18.5 && BMI < 24.9){
            bmiView.setText("BMI: " + df.format(BMI) + " - Normal weight");
        } else if (BMI >= 24.9 && BMI < 29.9){
            bmiView.setText("BMI: " + df.format(BMI) + " - Overweight");
        } else if (BMI >= 29.9){
            bmiView.setText("BMI: " + df.format(BMI) + " - Obese");
        }
    }

    public void bmiCalculator(){
        String height = databaseHelper.getHeight(username);

        double heightDouble = Double.parseDouble(height)/100;
        try {
            double weightDouble = Double.parseDouble(weight);
            BMI = weightDouble / (heightDouble * heightDouble);
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    private int round(double d){
        double dAbs = Math.abs(d);
        int i = (int) dAbs;
        double result = dAbs - (double) i;
        if(result<0.5){
            return d<0 ? -i : i;
        }else{
            return d<0 ? -(i+1) : i+1;
        }
    }


    private String modifyJSON(String value) {
        double d = Double.parseDouble(value);
        int v = round(d);
        return String.valueOf(v);
    }


}