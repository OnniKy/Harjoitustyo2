package com.example.harjoitustyo2;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.StrictMode;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.TextView;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.ArrayList;

public class ClimateControl extends AppCompatActivity {
    Button submit, cancel, graphButton;
    SeekBar beefBar, porkBar, fishBar, cheeseBar, dairyBar, riceBar, vegetableBar, eggBar;
    TextView beefView, porkView,fishView, cheeseView, dairyView, riceView, vegetableView, eggView;
    TextView dairyEmissionView, meatEmissionView, plantEmissionView, totalEmissionView;
    Spinner spinner;
    Context context;

    JSONRequest jsonRequest;
    JSONObject jsonObject;
    DatabaseHelper databaseHelper;

    String name, username;

    static int MIN = 0;
    int beefAVG = 40;
    int porkAVG = 100;
    int fishAVG = 60;
    int cheeseAVG = 30;
    int dairyAVG = 380;
    int riceAVG = 9;
    int vegetableAVG = 140;
    int eggAVG = 3;

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_climate_control);
        context = ClimateControl.this;

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        // Utilizing seekbars
        beefBar = (SeekBar) findViewById(R.id.seekBar1);
        porkBar = (SeekBar) findViewById(R.id.seekBar2);
        fishBar = (SeekBar) findViewById(R.id.seekBar3);
        cheeseBar = (SeekBar) findViewById(R.id.seekBar4);
        dairyBar = (SeekBar) findViewById(R.id.seekBar5);
        riceBar = (SeekBar) findViewById(R.id.seekBar6);
        vegetableBar = (SeekBar) findViewById(R.id.seekBar7);
        eggBar = (SeekBar) findViewById(R.id.seekBar8);

        // Utilizing textviews
        beefView = (TextView) findViewById(R.id.beefView);
        porkView = (TextView) findViewById(R.id.porkView);
        fishView = (TextView) findViewById(R.id.fishView);
        cheeseView = (TextView) findViewById(R.id.cheeseView);
        dairyView = (TextView) findViewById(R.id.dairyView);
        riceView = (TextView) findViewById(R.id.riceView);
        vegetableView = (TextView) findViewById(R.id.vegetableView);
        eggView = (TextView) findViewById(R.id.eggView);
        dairyEmissionView = (TextView) findViewById(R.id.dairyEmission);
        meatEmissionView = (TextView) findViewById(R.id.meatEmission);
        plantEmissionView = (TextView) findViewById(R.id.plantEmission);
        totalEmissionView = (TextView) findViewById(R.id.totalEmission);

        jsonRequest = new JSONRequest();
        submit = findViewById(R.id.submit);
        cancel = findViewById(R.id.cancel1);
        graphButton = findViewById(R.id.graphButton);
        spinner = findViewById(R.id.spinner1);

        this.seekbarUtilize(beefBar, beefAVG, beefView);
        this.seekbarUtilize(porkBar, porkAVG, porkView);
        this.seekbarUtilize(fishBar, fishAVG, fishView);
        this.seekbarUtilize(cheeseBar, cheeseAVG, cheeseView);
        this.seekbarUtilize(dairyBar, dairyAVG, dairyView);
        this.seekbarUtilize(riceBar, riceAVG, riceView);
        this.seekbarUtilize(vegetableBar, vegetableAVG, vegetableView);
        this.seekbarUtilize(eggBar, eggAVG, eggView);
        this.spinnerUtilize();

        username = getIntent().getStringExtra("Username");
        databaseHelper = new DatabaseHelper(this);


        try {
            name = databaseHelper.getName(username);
        } catch (Exception e) {
            e.printStackTrace();
        }

        submit.setOnClickListener(v -> {
            String diet = spinner.getSelectedItem().toString();
            String beef = changeToPercent(cutString(beefView.getText().toString()), beefAVG);
            String fish = changeToPercent(cutString(fishView.getText().toString()), fishAVG);
            String pork = changeToPercent(cutString(porkView.getText().toString()),porkAVG);
            String dairy = changeToPercent(cutString(dairyView.getText().toString()),dairyAVG);
            String cheese = changeToPercent(cutString(cheeseView.getText().toString()), cheeseAVG);
            String rice = changeToPercent(cutString(riceView.getText().toString()), riceAVG);
            String egg = changeToPercentEgg(cutString(eggView.getText().toString()), eggAVG);
            String salad = changeToPercent(cutString(vegetableView.getText().toString()), vegetableAVG);

            jsonObject = jsonRequest.readJSON(name ,diet, beef, fish, pork, dairy, cheese, rice, egg, salad, context);

            try {
                dairyEmissionView.setText("Dairy: " + modifyValue(jsonObject.getString("Dairy")) + "kg");
                meatEmissionView.setText("Meat: " + modifyValue(jsonObject.getString("Meat")) + "kg");
                plantEmissionView.setText("Plant: " + modifyValue(jsonObject.getString("Plant")) + "kg");
                totalEmissionView.setText("Total: " + modifyValue(jsonObject.getString("Total")) + "kg");
            } catch (JSONException e) {
                e.printStackTrace();
            }


        });


        cancel.setOnClickListener(v -> {
            Intent intent = new Intent(ClimateControl.this, MainPage.class);
            intent.putExtra("Username", username);
            startActivity(intent);

        });

        graphButton.setOnClickListener(v -> {
            Intent intent = new Intent(ClimateControl.this, ClimateGraph.class);
            intent.putExtra("Username", username);
            startActivity(intent);
        });
    }


    @RequiresApi(api = Build.VERSION_CODES.O)
    public void seekbarUtilize(SeekBar seekbar, int AVG, TextView view){
        if (eggBar.getId() == seekbar.getId()){
            seekbar.setMax(30);
            seekbar.setMin(MIN);
            seekbar.setProgress(AVG);
            view.setText(AVG + " pcs/week");
        } else {
            seekbar.setMax(AVG*2);
            seekbar.setMin(MIN);
            seekbar.setProgress(AVG);
            view.setText(AVG/100.0 + " kg/week");
        }


        seekbar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                if (eggBar.getId() == seekBar.getId()){
                    view.setText(progress + " pcs/week");
                }else {
                    float value = (float) ((float)progress / 100.00);
                    view.setText(value + " kg/week");
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) { }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) { }
        });

    }


    public void spinnerUtilize(){
        ArrayList<String> list = new ArrayList<>();
        list.add("omnivore");
        list.add("vegan");
        list.add("vegetarian");

        ArrayAdapter<String> adapter = new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_spinner_dropdown_item, list);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
    }

    // Cuts values from textviews
    private String cutString(String value){
        String [] strings = value.split(" ");
        System.out.println(strings[0]);
        return strings[0];
    }

    // Rounding double value to integer
    private int round(double d){
        double dAbs = Math.abs(d);
        int i = (int) dAbs;
        double result = dAbs - (double) i;
        if(result<0.5){
            return d<0 ? -i : i;
        }else{
            return d<0 ? -(i+1) : i+1;
        }
    }


    // Modifying decimal number to integer
    private String modifyValue(String value) {
        double d = Double.parseDouble(value);
        int v = round(d);
        return String.valueOf(v);
    }

    private String changeToPercent(String value, int AVG){
        String result;
        double d = Double.parseDouble(value)*100;
        double r = (d/AVG)*100;
        result = String.valueOf(round(r));
        return result;
    }

    private String changeToPercentEgg(String value, int AVG){
        double d = Double.parseDouble(value);
        double result = (d/AVG)*100;
        return String.valueOf(result);
    }
}