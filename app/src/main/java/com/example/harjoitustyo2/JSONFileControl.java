package com.example.harjoitustyo2;

import android.content.Context;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class JSONFileControl {


    FileWriter fileWriter;
    BufferedWriter bufferedWriter;
    BufferedReader bufferedReader;
    FileReader fileReader;
    String response;
    private JSONObject messageDetails;
    private Boolean isUserExisting;
    public JSONFileControl(){

    }

    //Method for writing weight, caffeine and climate data to Json file
    public void writeLog(String weight, Context context, String name, String Value) {
        String FILE_NAME = null;

        switch (Value) {
            case "Weight":
                FILE_NAME = name + "Weight.json";
                break;
            case "Caffeine":
                FILE_NAME = name + "Caffeine.json";
                break;
            case "Total":
                FILE_NAME = name + "Climate.json";
                break;
        }

        if (FILE_NAME == null) throw new AssertionError();
        File file = new File(context.getFilesDir(), FILE_NAME);

        if (!file.exists()) {
            try {
                //file.createNewFile();
                fileWriter = new FileWriter(file.getAbsoluteFile());
                bufferedWriter = new BufferedWriter(fileWriter);
                bufferedWriter.write("{}");
                bufferedWriter.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        try {
            StringBuilder output = new StringBuilder();

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



// Method for reading weight, caffeine and climate log. Method returns latest value
    public String readLog(Context context, String name, String Value) throws Exception {
        StringBuilder output = new StringBuilder();
        String result;
        String FILE_NAME = null;
        switch (Value) {
            case "Weight":
                FILE_NAME = name + "Weight.json";
                break;
            case "Caffeine":
                FILE_NAME = name + "Caffeine.json";
                break;
            case "Total":
                FILE_NAME = name + "Climate.json";
                break;
        }

        if (FILE_NAME == null) throw new AssertionError();
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

    //Method gets values from Json file to graph. It returns all values one by one
    public String getGraphData(Context context, String name, String Value, int i) throws Exception {
        StringBuilder output = new StringBuilder();
        String result;
        String FILE_NAME = null;
        switch (Value) {
            case "Weight":
                FILE_NAME = name + "Weight.json";
                break;
            case "Caffeine":
                FILE_NAME = name + "Caffeine.json";
                break;
            case "Total":
                FILE_NAME = name + "Climate.json";
                break;
        }

        if (FILE_NAME == null) throw new AssertionError();
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

        return result;
    }

    //Method gets quantity of values in Json file to create right size graph. It returns quantity of values
    public int getQuantity(Context context, String name, String Value) throws Exception {
        StringBuilder output = new StringBuilder();
        String FILE_NAME = null;
        switch (Value) {
            case "Weight":
                FILE_NAME = name + "Weight.json";
                break;
            case "Caffeine":
                FILE_NAME = name + "Caffeine.json";
                break;
            case "Total":
                FILE_NAME = name + "Climate.json";
                break;
        }

        if (FILE_NAME == null) throw new AssertionError();
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
}

