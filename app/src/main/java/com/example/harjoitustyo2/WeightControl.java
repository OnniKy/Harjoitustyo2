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

import org.json.JSONObject;

public class WeightControl extends AppCompatActivity {

    EditText addWeight;
    Context context;
    String username, name;
    Button dailyWeightButton, back;
    DatabaseHelper databaseHelper;

    JSONFileControl jsonFileControl;
    LineGraphSeries<DataPoint> series;
    GraphView graph;
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
        x = 0.0;


        try {
            name = databaseHelper.getName(username);
        } catch (Exception e) {
            e.printStackTrace();
        }


        jsonFileControl = new JSONFileControl();
        graph = findViewById(R.id.graph);
        series = new LineGraphSeries<DataPoint>();



        dailyWeightButton.setOnClickListener(v -> {
            String addWeight1 = addWeight.getText().toString();
            jsonFileControl.writeLogWeight(addWeight1, context, name);
            weightGraph();
            Toast.makeText(this, "Weight updated!", Toast.LENGTH_SHORT).show();
        });


        back.setOnClickListener(v -> {
            Intent intent = new Intent(WeightControl.this, MainPage.class);
            intent.putExtra("Username", username);
            startActivity(intent);
        });

    }
    public int getQuantity(){
        int q = 0;
        try{
            q = jsonFileControl.getQuantity(context, name, "Weight");
        }catch (Exception e) {
            e.printStackTrace();
        }
        return q;
    }

    public void weightGraph(){
        int length=getQuantity();
        String p = "";
        for(int i = 0; i<length-1; i++){
            x = x+1;
            try{
                p = jsonFileControl.getGraphWeight(context, name, "Weight", i);
            }  catch (Exception e) {
                e.printStackTrace();
            }
            y = Integer.parseInt(p);

            series.appendData(new DataPoint(x, y), false, length-1);
        }
        graph.addSeries(series);
    }


}