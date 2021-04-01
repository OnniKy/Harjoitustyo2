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
    JSONRequest jsonRequest;
    Button submit, cancel;
    Context context;
    SeekBar beefBar, porkBar, fishBar, cheeseBar, dairyBar, riceBar, vegetableBar, eggBar;
    TextView beefView, porkView,fishView, cheeseView, dairyView, riceView, vegetableView, eggView;
    TextView dairyEmissionView, meatEmissionView, plantEmissionView, totalEmissionView;
    Spinner spinner;

    static int MAX = 500;
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
        spinner = (Spinner) findViewById(R.id.spinner1);

        this.seekbarUtilize(beefBar, beefAVG, beefView);
        this.seekbarUtilize(porkBar, porkAVG, porkView);
        this.seekbarUtilize(fishBar, fishAVG, fishView);
        this.seekbarUtilize(cheeseBar, cheeseAVG, cheeseView);
        this.seekbarUtilize(dairyBar, dairyAVG, dairyView);
        this.seekbarUtilize(riceBar, riceAVG, riceView);
        this.seekbarUtilize(vegetableBar, vegetableAVG, vegetableView);
        this.seekbarUtilize(eggBar, eggAVG, eggView);

        this.spinnerUtilize();

        submit.setOnClickListener(v -> {
            String diet = spinner.getSelectedItem().toString();
            String beef = cutString(beefView.getText().toString());
            String fish = cutString(fishView.getText().toString());
            String pork = cutString(porkView.getText().toString());
            String dairy = cutString(dairyView.getText().toString());
            String cheese = cutString(cheeseView.getText().toString());
            String rice = cutString(riceView.getText().toString());
            String egg = cutString(eggView.getText().toString());
            String salad = cutString(vegetableView.getText().toString());

            //TODO TEE NÄISTÄ KOKONAISLUKUJA

            JSONObject jsonObject = jsonRequest.readJSON(diet, beef, fish, pork, dairy, cheese, rice, egg, salad);

            try {
                dairyEmissionView.setText("Dairy: " + modifyJSON(jsonObject.getString("Dairy")) + "kg CO2/year");
                meatEmissionView.setText("Meat: " + modifyJSON(jsonObject.getString("Meat")) + "kg CO2/year");
                plantEmissionView.setText("Plant: " + modifyJSON(jsonObject.getString("Plant")) + "kg CO2/year");
                totalEmissionView.setText("Total: " + modifyJSON(jsonObject.getString("Total")) + "kg CO2/year");
            } catch (JSONException e) {
                e.printStackTrace();
            }
        });


        cancel.setOnClickListener(v -> {
            Intent intent = new Intent(ClimateControl.this, MainPage.class);
            startActivity(intent);

        });             


    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public void seekbarUtilize(SeekBar seekbar, int AVG, TextView view){
        if (eggBar.getId() == seekbar.getId()){
            seekbar.setMax(10);
            seekbar.setMin(0);
            seekbar.setProgress(AVG);
            view.setText( AVG + " pcs/week");
        } else {
            seekbar.setMax(MAX);
            seekbar.setMin(MIN);
            seekbar.setProgress(AVG);
            view.setText("" + AVG/100.0 + " kg/week");
        }

        seekbar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                if (eggBar.getId() == seekBar.getId()){
                    view.setText("" + progress + " pcs/week");
                }else {
                    float value = (float) ((float)progress / 100.00);
                    view.setText("" + value + " kg/week");
                }

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

    }

    public void spinnerUtilize(){
        ArrayList<String> list = new ArrayList<String>();
        list.add("omnivore");
        list.add("vegan");
        list.add("vegetarian");

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_spinner_dropdown_item, list);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

    }

    private String cutString(String value){
        String [] strings = value.split(" ");
        Double d = Double.parseDouble(strings[0]);
        int v = round(d);

        return String.valueOf(v);


    }

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

    private String modifyJSON(String value){
        Double d = Double.parseDouble(value);
        int v = round(d);

        return String.valueOf(v);
    }








}