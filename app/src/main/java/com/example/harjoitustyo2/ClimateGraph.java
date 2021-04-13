package com.example.harjoitustyo2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;

import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;

public class ClimateGraph extends AppCompatActivity {

    LineGraphSeries<DataPoint> series;
    GraphView climateGraph;
    JSONFileControl jsonFileControl;
    Context context;
    DatabaseHelper databaseHelper;
    String username, name;
    Graphs graphs;
    double x = 0.0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_climate_graph);
        climateGraph = findViewById(R.id.graph);
        series = new LineGraphSeries<DataPoint>();
        username = getIntent().getStringExtra("Username");
        databaseHelper = new DatabaseHelper(this);
        context = ClimateGraph.this;

        jsonFileControl = new JSONFileControl();
        graphs = new Graphs();

        try {
            name = databaseHelper.getName(username);
        } catch (Exception e) {
            e.printStackTrace();
        }
        series = graphs.createGraph(x, series, context, name, "Total");
        climateGraph.addSeries(series);
    }
}



