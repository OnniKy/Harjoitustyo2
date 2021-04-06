package com.example.harjoitustyo2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class WeightControl extends AppCompatActivity {

    EditText addWeight;
    Context context;
    JSONRequest jsonRequest;
    String user;
    Button dailyWeightButton, back;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weight_control);

        addWeight = findViewById(R.id.addWeight);
        dailyWeightButton = findViewById(R.id.dailyWeightButton);
        back = findViewById(R.id.Back);

        context = WeightControl.this;
        user = getIntent().getStringExtra("Username");

        jsonRequest = new JSONRequest();

        dailyWeightButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                jsonRequest.writeLog(addWeight, context, user);
            }
        });


        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(WeightControl.this, MainPage.class);
                startActivity(intent);
            }
        });






    }

}