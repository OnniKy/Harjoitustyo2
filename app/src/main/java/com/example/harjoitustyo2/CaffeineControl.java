package com.example.harjoitustyo2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class CaffeineControl extends AppCompatActivity {
    Button cancel, submit;
    String username;
    TextView totalConsumptionView;
    EditText coffeeConsumption, edConsumption, lemonadeConsumption;
    final int coffeeCaffeine = 150;
    final int edCaffeine = 320;
    final int lemonadeCaffeine = 65;
    double caffeineEmission;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_caffeine_control);

        coffeeConsumption = findViewById(R.id.coffeeConsumption);
        edConsumption = findViewById(R.id.edConsumption);
        lemonadeConsumption = findViewById(R.id.lemonadeConsumption);

        totalConsumptionView = findViewById(R.id.totalConsumption);

        submit = findViewById(R.id.submitC);
        cancel = findViewById(R.id.backC);
        username = getIntent().getStringExtra("Username");



        submit.setOnClickListener(v -> {
            String coffee = coffeeConsumption.getText().toString();
            String ed = edConsumption.getText().toString();
            String lemonade = lemonadeConsumption.getText().toString();

            if(coffee.isEmpty() || ed.isEmpty() || lemonade.isEmpty()){
                Toast.makeText(this, "Insert all values!", Toast.LENGTH_SHORT).show();
            } else {
                caffeineEmission = calculateCaffeine(coffee, ed, lemonade);
                totalConsumptionView.setText("Your today's caffeine consumption is " + caffeineEmission + " mg!");
            }




        });


        cancel.setOnClickListener(v -> {
            Intent intent = new Intent(CaffeineControl.this, MainPage.class);
            intent.putExtra("Username", username);
            startActivity(intent);
        });
    }

    public double calculateCaffeine(String coffee, String ed, String lemonade){
        double cCaffeine = Double.parseDouble(coffee)*coffeeCaffeine;
        double eCaffeine  = Double.parseDouble(ed)*edCaffeine;
        double eLemonade = Double.parseDouble(lemonade)*lemonadeCaffeine;
        double total = cCaffeine + eCaffeine + eLemonade;
        System.out.println(total);
        return total;
    }
}