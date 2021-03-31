package com.example.harjoitustyo2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import java.io.IOException;
import java.io.OutputStreamWriter;

public class WeightControl extends AppCompatActivity {

    EditText addWeight;
    private Context context;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weight_control);
        addWeight = findViewById(R.id.dailyWeightButton);
        context = WeightControl.this;
    }

    public void writeFile(View v){
        OutputStreamWriter osw;
        String s = "<credentials>\n" +
                "<user>testusr</user>\n" +
                "<password>testpwd</password>\n" +
                "<credentials>";
        try{
            osw = new OutputStreamWriter(context.openFileOutput("WeightData.txt", Context.MODE_PRIVATE));
            osw.write(s);
            osw.close();
        } catch (IOException e){
            e.printStackTrace();
        }

    }
}