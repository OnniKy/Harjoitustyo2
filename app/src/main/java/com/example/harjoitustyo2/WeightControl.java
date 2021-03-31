package com.example.harjoitustyo2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
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
        String weight = addWeight.getText().toString();
        try{JSONObject jsonObject = new JSONObject();
            jsonObject.put("Weight", weight);
            String userString = jsonObject.toString();

            File file = new File(context.getFilesDir(),"WeightData.jsor");
            FileWriter fileWriter = new FileWriter(file);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            bufferedWriter.write(userString);
            bufferedWriter.close();
        } catch (IOException e){
            e.printStackTrace();
        } catch (JSONException e){
            throw new RuntimeException(e);
        }

    }
}