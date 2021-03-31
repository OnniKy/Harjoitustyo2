package com.example.harjoitustyo2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.EditText;

public class WeightControl extends AppCompatActivity {

    EditText addWeight;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weight_control);
        addWeight = findViewById(R.id.dailyWeightButton);
    }
}