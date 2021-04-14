package com.example.harjoitustyo2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;

public class WeightControl extends AppCompatActivity {

    EditText addWeight;
    Context context;
    String username, name;
    Button dailyWeightButton, back;
    DatabaseHelper databaseHelper;

    JSONFileControl jsonFileControl;
    Graphs graphs;
    LineGraphSeries<DataPoint> series;
    GraphView weightGraph;
    double x,y;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weight_control);

        addWeight = findViewById(R.id.addWeight);
        dailyWeightButton = findViewById(R.id.dailyWeightButton);
        back = findViewById(R.id.back);

        context = WeightControl.this;
        username = getIntent().getStringExtra("Username");
        databaseHelper = new DatabaseHelper(this);
        graphs = new Graphs();
        x = 0.0;



        jsonFileControl = new JSONFileControl();
        weightGraph = findViewById(R.id.graph);
        series = new LineGraphSeries<DataPoint>();
        weightGraph.getGridLabelRenderer().setHorizontalLabelsVisible(false);


        try {
            name = databaseHelper.getName(username);
        } catch (Exception e) {
            e.printStackTrace();
        }

        series = graphs.createGraph(x, series, context, name, "Weight");
        weightGraph.addSeries(series);





        dailyWeightButton.setOnClickListener(v -> {
            String addWeight1 = addWeight.getText().toString();
            jsonFileControl.writeLogWeight(addWeight1, context, name);
            series = graphs.createGraph(x,series, context, name, "Weight");
            weightGraph.addSeries(series);
            Toast.makeText(this, "Weight updated!", Toast.LENGTH_SHORT).show();
        });


        back.setOnClickListener(v -> {
            Intent intent = new Intent(WeightControl.this, MainPage.class);
            intent.putExtra("Username", username);
            startActivity(intent);
        });

    }

}