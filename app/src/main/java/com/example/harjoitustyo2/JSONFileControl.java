package com.example.harjoitustyo2;

import android.content.Context;
import android.os.Message;
import android.util.JsonWriter;
import android.widget.EditText;

import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class JSONFileControl {


    FileWriter fileWriter;
    BufferedWriter bufferedWriter;
    BufferedReader bufferedReader;
    FileReader fileReader;
    String response, name;
    private JSONObject messageDetails;
    private Boolean isUserExisting;
    DatabaseHelper databaseHelper;

    public JSONFileControl(){

    }


    public void writeLog(String weight, Context context, String name, String Value) {

        String FILE_NAME = null;

        if (Value.equals("Weight")) {
            FILE_NAME = name + "Weight.json";
        } else {
            FILE_NAME = name + "Climate.json";
        }
        File file = new File(context.getFilesDir(), FILE_NAME);

        if (!file.exists()) {
            try {
                file.createNewFile();
                fileWriter = new FileWriter(file.getAbsoluteFile());
                bufferedWriter = new BufferedWriter(fileWriter);
                bufferedWriter.write("{}");
                bufferedWriter.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        try {
            StringBuffer output = new StringBuffer();

            bufferedReader = new BufferedReader(new FileReader(file.getAbsoluteFile()));
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                output.append(line).append("\n");
            }
            response = output.toString();
            bufferedReader.close();

            messageDetails = new JSONObject(response);
            isUserExisting = messageDetails.has(Value);
            if (!isUserExisting) {
                JSONArray newUserMessages = new JSONArray();
                newUserMessages.put(weight);
                messageDetails.put(Value, newUserMessages);
            } else {
                JSONArray userMessages = (JSONArray) messageDetails.get(Value);
                userMessages.put(weight);
            }

            fileWriter = new FileWriter(file.getAbsoluteFile());
            BufferedWriter bw = new BufferedWriter(fileWriter);
            bw.write(messageDetails.toString());
            bw.close();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }
    }



// Method for reading weight and climate log
    public String readLog(Context context, String name, String Value) throws Exception {

        StringBuffer output = new StringBuffer();
        String result;
        String FILE_NAME;
        if (Value.equals("Weight")) {
            FILE_NAME = name + "Weight.json";
        } else {
            FILE_NAME = name + "Climate.json";
        }

        File file = new File(context.getFilesDir(), FILE_NAME);
        fileReader = new FileReader(file.getAbsoluteFile());
        bufferedReader = new BufferedReader(fileReader);
        String line;

        while ((line = bufferedReader.readLine()) != null) {
            output.append(line).append("\n");

        }
        response = output.toString();
        bufferedReader.close();
        messageDetails = new JSONObject(response);
        isUserExisting = messageDetails.has(Value);
        JSONArray userMessages = (JSONArray) messageDetails.get(Value);
        result = userMessages.get(userMessages.length() - 1).toString();

        return result;
    }

    //Get values from Json file to graph
    public String getGraphData(Context context, String name, String Value, int i) throws Exception {
        StringBuffer output = new StringBuffer();
        String result;
        String FILE_NAME;
        if (Value.equals("Weight")) {
            FILE_NAME = name + "Weight.json";
        } else {
            FILE_NAME = name + "Climate.json";
        }

        File file = new File(context.getFilesDir(), FILE_NAME);
        fileReader = new FileReader(file.getAbsoluteFile());
        bufferedReader = new BufferedReader(fileReader);
        String line;

        while ((line = bufferedReader.readLine()) != null) {
            output.append(line).append("\n");

        }
        response = output.toString();
        bufferedReader.close();
        messageDetails = new JSONObject(response);
        isUserExisting = messageDetails.has(Value);
        JSONArray userMessages = (JSONArray) messageDetails.get(Value);
        result = userMessages.get(userMessages.length()-(userMessages.length()-i)).toString();
        System.out.println("RESULT JSONFILE: " + result);

        return result;
    }

    //Return quantity of values to create right size graph
    public int getQuantity(Context context, String name, String Value) throws Exception {
        StringBuffer output = new StringBuffer();
        String result = null;
        String FILE_NAME;
        if (Value.equals("Weight")) {
            FILE_NAME = name + "Weight.json";
        } else {
            FILE_NAME = name + "Climate.json";
        }

        File file = new File(context.getFilesDir(), FILE_NAME);
        fileReader = new FileReader(file.getAbsoluteFile());
        bufferedReader = new BufferedReader(fileReader);
        String line;

        while ((line = bufferedReader.readLine()) != null) {
            output.append(line).append("\n");

        }
        response = output.toString();
        bufferedReader.close();
        messageDetails = new JSONObject(response);
        isUserExisting = messageDetails.has(Value);
        JSONArray userMessages = (JSONArray) messageDetails.get(Value);

        return userMessages.length();
    }

    /////////////////////////// CLIMATE DIET  ///////////////////////////

/*
    public void writeLogClimate(Context context, String name, JSONObject jsonObject) {
        String total = null;
        String fileName = name + "Climate.json";


        try {
            total = modifyJSON(jsonObject.getString("Total"));
        } catch (JSONException e) {
            e.printStackTrace();
        }


        File file = new File(context.getFilesDir(), fileName);

        try {
            // FILE DOES NOT EXIST
            if (!file.exists()) {
                file.createNewFile();
                bufferedWriter = new BufferedWriter(new FileWriter(file.getAbsoluteFile()));
                bufferedWriter.write("{}");
                bufferedWriter.close();
            }
            StringBuffer output = new StringBuffer();


            bufferedReader = new BufferedReader(new FileReader(file.getAbsoluteFile()));
            String line;

            int i = 0;
            while ((line = bufferedReader.readLine()) != null) {
                output.append(line).append("\n");

            }
            response = output.toString();
            bufferedReader.close();

            messageDetails = new JSONObject(response);
            isUserExisting = messageDetails.has("Total");
            if (!isUserExisting) {
                JSONArray newUserMessages = new JSONArray();
                newUserMessages.put(total);
                messageDetails.put("Total", newUserMessages);
            } else {
                JSONArray userMessages = (JSONArray) messageDetails.get("Total");
                userMessages.put(total);
            }

            fileWriter = new FileWriter(file.getAbsoluteFile());
            BufferedWriter bw = new BufferedWriter(fileWriter);
            bw.write(messageDetails.toString());
            bw.close();
        } catch (IOException ioException) {
            ioException.printStackTrace();
        } catch (JSONException jsonException) {
            jsonException.printStackTrace();
        }

    }
    */

        public int round ( double d){
            double dAbs = Math.abs(d);
            int i = (int) dAbs;
            double result = dAbs - (double) i;
            if (result < 0.5) {
                return d < 0 ? -i : i;
            } else {
                return d < 0 ? -(i + 1) : i + 1;
            }
        }



        public String modifyJSON (String value){
            double d = Double.parseDouble(value);
            int v = round(d);

            return String.valueOf(v);
        }


}

