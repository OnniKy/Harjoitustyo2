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
    double x,y;
    JSONFileControl jsonFileControl;
    Context context;
    DatabaseHelper databaseHelper;
    String username, name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_climate_graph);
        climateGraph = findViewById(R.id.weightGraph);
        series = new LineGraphSeries<DataPoint>();
        username = getIntent().getStringExtra("Username");
        databaseHelper = new DatabaseHelper(this);
        x = 0.0;
        jsonFileControl = new JSONFileControl();
        try {
            name = databaseHelper.getName(username);
        } catch (Exception e) {
            e.printStackTrace();
        }
        ClimateGraph();
    }
    public int getQuantity(){
        int q = 0;
        try{
            q = jsonFileControl.getQuantity(context, name, "Total");
        }catch (Exception e) {
            e.printStackTrace();
        }
        return q;
    }

    public void ClimateGraph(){
        int length=getQuantity();
        String p = "";
        for(int i = 0; i<length-1; i++){
            int left = length-i;

            x = x+1;
            try{
                p = jsonFileControl.climateGraphData(context, name, left);
            }  catch (Exception e) {
                e.printStackTrace();
            }
            y = Integer.parseInt(p);

            series.appendData(new DataPoint(x, y), true, length-1);
        }
        climateGraph.addSeries(series);

    }
}