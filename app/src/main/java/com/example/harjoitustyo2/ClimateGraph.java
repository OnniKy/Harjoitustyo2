package com.example.harjoitustyo2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;

public class ClimateGraph extends AppCompatActivity {
    GraphView climateGraph;
    Button back;
    Context context;

    JSONFileControl jsonFileControl;
    DatabaseHelper databaseHelper;
    Graphs graphs;

    LineGraphSeries<DataPoint> series;
    String username, name;
    Double d;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_climate_graph);
        climateGraph = findViewById(R.id.graph);
        username = getIntent().getStringExtra("Username");
        databaseHelper = new DatabaseHelper(this);
        context = ClimateGraph.this;
        back = findViewById(R.id.backButton);

        climateGraph.getGridLabelRenderer().setHorizontalLabelsVisible(false);
        climateGraph.getViewport().setXAxisBoundsManual(true);
        climateGraph.getViewport().setMinX(0);

        jsonFileControl = new JSONFileControl();
        graphs = new Graphs();

        try {
            name = databaseHelper.getName(username);
        } catch (Exception e) {
            e.printStackTrace();
        }

        series = graphs.createGraph(context, name, "Total");
        series.setColor(getResources().getColor(R.color.green));
        climateGraph.addSeries(series);
        d = series.getHighestValueX();
        climateGraph.getViewport().setMaxX(d.intValue());
        series.setColor(getResources().getColor(R.color.green));



        back.setOnClickListener(v -> {
            Intent intent = new Intent(ClimateGraph.this, ClimateControl.class);
            intent.putExtra("Username", username);
            startActivity(intent);
        });
    }
}



