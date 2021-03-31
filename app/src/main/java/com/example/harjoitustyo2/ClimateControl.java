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

import java.util.ArrayList;

public class ClimateControl extends AppCompatActivity {
    JSONRequest jsonRequest;
    Button submit, cancel;
    Context context;
    SeekBar beefBar, porkBar, fishBar, cheeseBar, dairyBar, riceBar, vegetableBar, eggBar;
    TextView beefView, porkView,fishView, cheeseView, dairyView, riceView, vegetableView, eggView;
    Spinner spinner;

    static int MAX = 15;
    static int MIN = 0;
    int beefAVG = 4;
    int porkAVG = 1;
    int fishAVG = 6;
    int cheeseAVG = 3;
    int dairyAVG = 3;
    int riceAVG = 1;
    int vegetableAVG = 1;
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

            //System.out.println(diet + beef + fish + pork +  dairy + cheese + rice + egg + salad);

            jsonRequest.readJSON(diet, beef, fish, pork, dairy, cheese, rice, egg, salad);
        });


        cancel.setOnClickListener(v -> {
            Intent intent = new Intent(ClimateControl.this, MainPage.class);
            startActivity(intent);

        });             


    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public void seekbarUtilize(SeekBar seekbar, int AVG, TextView view){
        seekbar.setMax(MAX);
        seekbar.setMin(MIN);
        seekbar.setProgress(AVG);
        System.out.println("BEFORE: " + seekbar.getId());
        view.setText("" + AVG + " kg/week");


        seekbar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                System.out.println(seekbar);
                view.setText("" + progress + " kg/week");

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

    public String cutString(String value){
        String [] strings = value.split(" ");
        return strings[0];
    }








}