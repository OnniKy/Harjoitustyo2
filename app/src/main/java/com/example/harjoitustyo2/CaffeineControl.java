package com.example.harjoitustyo2;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;

public class CaffeineControl extends AppCompatActivity {
    Button cancel, submit;
    String username, name;
    TextView totalConsumptionView;
    EditText coffeeConsumption, edConsumption, lemonadeConsumption;
    GraphView caffeineGraph;
    Graphs graphs;
    JSONFileControl jsonFileControl;
    DatabaseHelper databaseHelper;
    LineGraphSeries<DataPoint> series;
    final int coffeeCaffeine = 150;
    final int edCaffeine = 320;
    final int lemonadeCaffeine = 65;
    double caffeineEmission;
    Context context;
    Double d;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_caffeine_control);
        context = CaffeineControl.this;

        coffeeConsumption = findViewById(R.id.coffeeConsumption);
        edConsumption = findViewById(R.id.edConsumption);
        lemonadeConsumption = findViewById(R.id.lemonadeConsumption);
        totalConsumptionView = findViewById(R.id.totalConsumption);

        jsonFileControl = new JSONFileControl();
        graphs = new Graphs();
        databaseHelper = new DatabaseHelper(this);

        submit = findViewById(R.id.dailyWeightButton);
        cancel = findViewById(R.id.backC);
        username = getIntent().getStringExtra("Username");
        name = databaseHelper.getName(username);


        caffeineGraph = findViewById(R.id.graph);
        caffeineGraph.getGridLabelRenderer().setHorizontalLabelsVisible(false);
        caffeineGraph.getViewport().setXAxisBoundsManual(true);
        caffeineGraph.getViewport().setMinX(0);

        series = graphs.createGraph(context, name, "Caffeine");
        caffeineGraph.addSeries(series);
        d = series.getHighestValueX();
        series.setColor(getResources().getColor(R.color.green));
        caffeineGraph.getViewport().setMaxX(d.intValue());

        submit.setOnClickListener(v -> {
            String coffee = coffeeConsumption.getText().toString();
            String ed = edConsumption.getText().toString();
            String lemonade = lemonadeConsumption.getText().toString();

            if(coffee.isEmpty() || ed.isEmpty() || lemonade.isEmpty()){
                Toast.makeText(this, "Insert all values!", Toast.LENGTH_SHORT).show();
            } else {
                caffeineEmission = calculateCaffeine(coffee, ed, lemonade);
                jsonFileControl.writeLog(String.valueOf(caffeineEmission), context, name, "Caffeine");
                series = graphs.createGraph(context, name, "Caffeine");
                String text = "Total caffeine consumption: " + caffeineEmission + " mg/day!";
                totalConsumptionView.setText(text);
                try {
                    caffeineGraph.removeAllSeries();
                } catch (Exception e){
                    e.printStackTrace();
                }
                d = series.getHighestValueX();
                caffeineGraph.getViewport().setMaxX(d.intValue());
                caffeineGraph.addSeries(series);
                series.setColor(getResources().getColor(R.color.green));
                Toast.makeText(this, "Daily caffeine updated!", Toast.LENGTH_SHORT).show();
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