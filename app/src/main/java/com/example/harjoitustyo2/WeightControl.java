package com.example.harjoitustyo2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import org.json.JSONObject;

public class WeightControl extends AppCompatActivity {

    EditText addWeight;
    Context context;
    JSONRequest jsonRequest;
    String user;
    Button dailyWeightButton, back;
    JSONFileControl jsonFileControl;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weight_control);

        addWeight = findViewById(R.id.addWeight);
        dailyWeightButton = findViewById(R.id.dailyWeightButton);
        back = findViewById(R.id.back);

        context = WeightControl.this;
        user = getIntent().getStringExtra("Username");

        jsonRequest = new JSONRequest();
        jsonFileControl = new JSONFileControl();
        JSONObject json = new JSONObject();


        dailyWeightButton.setOnClickListener(v -> {

            jsonRequest.writeLog(addWeight, context, user);
            //jsonFileControl.writeJSONWeightFile(json, addWeight, context, "Timo");
        });


        back.setOnClickListener(v -> {
            Intent intent = new Intent(WeightControl.this, MainPage.class);
            startActivity(intent);
        });






    }

}