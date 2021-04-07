package com.example.harjoitustyo2;

import android.content.Context;
import android.widget.EditText;

import com.google.gson.Gson;

import org.json.JSONArray;
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
import java.util.HashMap;
import java.util.Map;

public class JSONFileControl {

    FileWriter fileWriter;
    BufferedWriter bufferedWriter;
    BufferedReader bufferedReader;
    FileReader fileReader;
    String response;
    private JSONObject messageDetails;
    private Boolean isUserExisting;
    JSONObject jsonObject;
    JSONObject jsn, jn, jsnObject;
    Map<String, Map<String, String>> map = new HashMap<>();


    public void writeLogWeight(String weight, Context context, String name){

        String FILE_NAME = name + ".json";
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

            bufferedReader = new BufferedReader(new FileReader(file.getAbsoluteFile()));
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


    public String readLogWeight(Context context, String name) throws Exception {
        StringBuffer output = new StringBuffer();
        String result = null;
        String FILE_NAME = name + ".json";

        File file = new File(context.getFilesDir(), FILE_NAME);
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
        JSONArray userMessages = (JSONArray) messageDetails.get("Weight");
        result = userMessages.get(userMessages.length()-1).toString();

        return result;
    }



    /////////////////////////// CLIMATE DIET  ///////////////////////////



    public void writeLogClimate(Context context, String name, JSONObject jsonObject){

        String dairy = null, meat = null, plant = null, total = null;

        String fileName = name + "Climate.json";


        try {
            dairy = modifyJSON(jsonObject.getString("Dairy"));
            meat = modifyJSON(jsonObject.getString("Meat"));
            plant = modifyJSON(jsonObject.getString("Plant"));
            total = modifyJSON(jsonObject.getString("Total"));
        } catch (JSONException e) {
            e.printStackTrace();
        }




        try {
            File file = new File(context.getFilesDir(), fileName);

            // FILE DOES NOT EXIST
            if (!file.exists()){
                file.createNewFile();
                bufferedWriter = new BufferedWriter(new FileWriter(file.getAbsoluteFile()));
                bufferedWriter.write("{}");
                bufferedWriter.close();

            }
            StringBuffer output = new StringBuffer();

            bufferedReader = new BufferedReader(new FileReader(file.getAbsoluteFile()));
            String line = "";
            System.out.println("MOROROR");

            int i = 0;
            while ((line = bufferedReader.readLine()) != null) {
                System.out.println("LINE: " + line);
                Map<String, String> map1 = new HashMap<>();
                i++;

                try {
                    jsn = new JSONObject(line);
                    System.out.println("JSN: " + jsn);
                    jn = new JSONObject(jsn.getString("map"));
                    jsnObject = new JSONObject(jn.getString("Data1"));

                    map1.put("Dairy", jsnObject.getString("Dairy"));
                    map1.put("Meat", jsnObject.getString("Meat"));
                    map1.put("Plant", jsnObject.getString("Plant"));
                    map1.put("Total", jsnObject.getString("Plant"));

                    System.out.println(map1);

                    map.put("De", map1);

                } catch (JSONException e) {
                    e.printStackTrace();
                }

                System.out.println(map1);

            }

            Map<String, String> clMap = new HashMap<>();

            clMap.put("Dairy",dairy);
            clMap.put("Meat",meat);
            clMap.put("Plant",plant);
            clMap.put("Total",total);

            System.out.println("clMAP: " + clMap);

            map.put("Data1", clMap );


            Data data = new Data(map);

            Gson gson = new Gson();
            String json = gson.toJson(data);


            BufferedWriter bw = new BufferedWriter(new FileWriter(file.getAbsoluteFile()));
            bw.write(json + "\n");
            bw.close();



        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public JSONObject readLogClimate(Context context, String name) throws JSONException {
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

