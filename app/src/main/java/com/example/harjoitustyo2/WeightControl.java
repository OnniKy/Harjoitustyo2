package com.example.harjoitustyo2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;

public class WeightControl extends AppCompatActivity {

    EditText addWeight;
    private Context context;
    private static final String FILE_NAME = "WeightDailyData.jsor";

    FileReader fileReader = null;
    FileWriter fileWriter = null;
    BufferedReader bufferedReader = null;
    BufferedWriter bufferedWriter = null;
    String response = null;
    private JSONObject messageDetails;
    private Boolean isUserExisting;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weight_control);
        addWeight = findViewById(R.id.dailyWeightButton);
        context = WeightControl.this;

    }


    public void writeFile(View v){
        String weight = addWeight.getText().toString();
        File file = new File(context.getFilesDir(), FILE_NAME);

        if(!file.exists()){
            try{
                file.createNewFile();
                fileWriter = new FileWriter(file.getAbsoluteFile());
                bufferedWriter = new BufferedWriter(fileWriter);
                bufferedWriter.write("{}");
                bufferedWriter.close();


            }catch (IOException e){
                e.printStackTrace();

            }
        }
        try {
            StringBuffer output = new StringBuffer();

            fileReader = new FileReader(file.getAbsoluteFile());
            bufferedReader = new BufferedReader(fileReader);
            String line = "";
            while ((line = bufferedReader.readLine()) != null) {
                output.append(line + "\n");
            }
            response = output.toString();
            bufferedReader.close();


            messageDetails = new JSONObject(response);
            isUserExisting = messageDetails.has("Weight");
            if (!isUserExisting) {
                JSONArray newUserMessages = new JSONArray();
                newUserMessages.put(weight);
                messageDetails.put("Weight", newUserMessages);
            } else {
                JSONArray userMessages = (JSONArray) messageDetails.get("Weight");
                userMessages.put(weight);
            }

            fileWriter = new FileWriter(file.getAbsoluteFile());
            BufferedWriter bw = new BufferedWriter(fileWriter);
            bw.write(messageDetails.toString());
            bw.close();
        } catch (IOException e){
            e.printStackTrace();
        }catch (JSONException e) {
            throw new RuntimeException(e);
        }








    }
}