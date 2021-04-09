package com.example.harjoitustyo2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.json.JSONObject;

public class WeightControl extends AppCompatActivity {

    EditText addWeight;
    Context context;
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

        jsonFileControl = new JSONFileControl();


        dailyWeightButton.setOnClickListener(v -> {
            String addWeight1 = addWeight.getText().toString();


            jsonFileControl.writeLogWeight(addWeight1, context, user);

            Toast.makeText(this, "Weight updated!", Toast.LENGTH_SHORT).show();
        });


        back.setOnClickListener(v -> {
            Intent intent = new Intent(WeightControl.this, MainPage.class);
            intent.putExtra("Username", user);
            startActivity(intent);
        });






    }

}