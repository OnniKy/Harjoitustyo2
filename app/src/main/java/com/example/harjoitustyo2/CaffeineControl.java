package com.example.harjoitustyo2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.jjoe64.graphview.GraphView;

public class CaffeineControl extends AppCompatActivity {
    Button cancel, submit;
    String username;
    TextView totalConsumptionView;
    EditText coffeeConsumption, edConsumption, lemonadeConsumption;
    GraphView caffeineGraph;
    Graphs graphs;
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

        graphs = new Graphs();

        submit = findViewById(R.id.submitC);
        cancel = findViewById(R.id.backC);
        username = getIntent().getStringExtra("Username");

        caffeineGraph = findViewById(R.id.caffeineGraph);
        caffeineGraph.getGridLabelRenderer().setHorizontalLabelsVisible(false);
        caffeineGraph.getViewport().setXAxisBoundsManual(true);
        caffeineGraph.getViewport().setMinX(0);

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