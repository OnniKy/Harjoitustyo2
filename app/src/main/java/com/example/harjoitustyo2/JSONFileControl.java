package com.example.harjoitustyo2;

import android.content.Context;
import android.widget.EditText;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;

public class JSONFileControl {
    JSONObject jsonObject;

    public JSONFileControl(){

    }

    public void writeJSONFile(Context context, String name, JSONObject jsonObject){
       try {
            Writer output = null;
            File file = new File(context.getFilesDir(), name + "Climate.json");
            output = new BufferedWriter(new FileWriter(file));
            output.write(jsonObject.toString() + "\n");
            output.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }




    public JSONObject readJSONFile(Context context, String name) throws JSONException {
        String result = null;

        try{
        File file = new File(context.getFilesDir(), name + "Climate.json");
        FileReader fileReader = new FileReader(file);
        BufferedReader bufferedReader = new BufferedReader(fileReader);
        StringBuilder stringBuilder = new StringBuilder();
        String line = bufferedReader.readLine();
        while (line != null){
            stringBuilder.append(line).append("\n");
            line = bufferedReader.readLine();
        }
        result = stringBuilder.toString();
        bufferedReader.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        jsonObject = new JSONObject(result);

        return jsonObject;
    }



    public void writeJSONWeightFile(JSONObject json,EditText addweight, Context context, String name){
        try {
            json = saveTOJSON(addweight, json);
            Writer output = null;
            File file = new File(context.getFilesDir(), name + "Weight.json");
            output = new BufferedWriter(new FileWriter(file));
            output.write(json.toString() + "\n");
            output.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }



    public JSONObject saveTOJSON(EditText addWeight,JSONObject json){

        String weight = addWeight.getText().toString();
        try {
            json.put("Weight", weight);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return json;
    }




    public int round(double d){
        double dAbs = Math.abs(d);
        int i = (int) dAbs;
        double result = dAbs - (double) i;
        if(result<0.5){
            return d<0 ? -i : i;
        }else{
            return d<0 ? -(i+1) : i+1;
        }
    }




    public String modifyJSON(String value) {
        double d = Double.parseDouble(value);
        int v = round(d);

        return String.valueOf(v);
    }
}
