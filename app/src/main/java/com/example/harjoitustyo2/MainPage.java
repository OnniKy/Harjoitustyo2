package com.example.harjoitustyo2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import java.text.DecimalFormat;

public class MainPage extends AppCompatActivity {

    JSONRequest jsonRequest;
    JSONFileControl jsonFileControl;
    EditText dailyWeight, bmiTextbox, changeInWeight, changeInClimate;
    TextView totalEmission;
    Button button, button2;
    ImageButton logOut;
    String emission = null;
    String weight;
    Context context;
    String username, name;
    DatabaseHelper databaseHelper;
    double BMI;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_page);
        dailyWeight = findViewById(R.id.dailyWeight);
        bmiTextbox = findViewById(R.id.bmiTextbox);
        /*changeInWeight = findViewById(R.id.changeInWeight);
        changeInClimate = findViewById(R.id.changeInClimate);*/
        button = findViewById(R.id.toClimateControl);
        button2 = findViewById(R.id.toWeightControl);
        logOut = findViewById(R.id.imageButton);

        totalEmission = findViewById(R.id.textView14);
        context = MainPage.this;

        jsonRequest = new JSONRequest();
        jsonFileControl = new JSONFileControl();

        username = getIntent().getStringExtra("Username");
        databaseHelper = new DatabaseHelper(this);
        System.out.println("USERNAME VALUE: " + username);
        try {
            name = databaseHelper.getName(username);
        } catch (Exception e) {
            e.printStackTrace();
        }


        try {
            weight = jsonFileControl.readLog(context, name, "Weight"); //TODO
            dailyWeight.setText("Your weight is: " + weight + "kg");
            emission = jsonFileControl.readLog(context, name, "Total");


        } catch (Exception e) {
            e.printStackTrace();
        }
        if (emission != null){
            totalEmission.setText("Total CO2 emission: " + emission + " kg per year");
        } else {
            totalEmission.setText("Calculate your emission on Climate \nControl page!");
        }

        setBMI();


        // Buttons OnClickListeners
        button.setOnClickListener(v -> {
            Intent intent = new Intent(MainPage.this, ClimateControl.class);
            intent.putExtra("Username", username);
            startActivity(intent);
        });

        button2.setOnClickListener(v -> {
            Intent intent = new Intent(MainPage.this, WeightControl.class);
            intent.putExtra("Username", username);
            startActivity(intent);
        });

        logOut.setOnClickListener(v -> {
            Intent intent = new Intent(MainPage.this, MainActivity.class);
            startActivity(intent);
        });

    }

    public void setBMI(){
        bmiCalculator();
        DecimalFormat df = new DecimalFormat("0.00");

        if (BMI < 18.5){
            bmiTextbox.setText("BMI: " + df.format(BMI) + " - Underweight");
        } else if (BMI >= 18.5 && BMI < 24.9){
            bmiTextbox.setText("BMI: " + df.format(BMI) + " - Normal weight");
        } else if (BMI >= 24.9 && BMI < 29.9){
            bmiTextbox.setText("BMI: " + df.format(BMI) + " - Overweight");
        } else if (BMI >= 29.9){
            bmiTextbox.setText("BMI: " + df.format(BMI) + " - Obese");
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


}