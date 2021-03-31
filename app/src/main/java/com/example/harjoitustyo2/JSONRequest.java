package com.example.harjoitustyo2;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;


public class JSONRequest {

    public void readJSON(String diet, String beef, String fish, String pork, String dairy, String cheese, String rice, String egg, String salad){
        String json = getJSON(diet, beef, fish, pork, dairy, cheese, rice, egg, salad);

        if (json != null){
            try {
                JSONArray jsonArray = new JSONArray(json);
                for (int i = 0; i < jsonArray.length() ; i++){
                    JSONObject jsonObject = jsonArray.getJSONObject(i);
                    System.out.println("********" + (i+1) + "*********");
                    System.out.println(jsonObject.getString("Dairy"));
                    System.out.println(jsonObject.getString("Meat"));
                    System.out.println(jsonObject.getString("Plant"));
                    System.out.println(jsonObject.getString("Total"));
                }

            } catch (JSONException e) {
                e.printStackTrace();
            } finally {
                System.out.println("It works");
            }
        }

    }


    // Uses Climate Diet API
    public String getJSON(String diet, String bLevel, String fLevel, String pLevel, String dLevel, String cLevel, String rLevel, String eLevel, String sLevel){
        String response = null;
        try {
            URL url = new URL("https://ilmastodieetti.ymparisto.fi/ilmastodieetti/calculatorapi/v1/FoodCalculator?query.diet=" + diet + "&query.beefLevel=" + bLevel + "&query.fishLevel=" + fLevel + "&query.porkPoultryLevel=" + pLevel + "&query.dairyLevel=" + dLevel + "&query.cheeseLevel=" + cLevel + "&query.riceLevel=" + rLevel + "&query.eggLevel=" + eLevel + "&query.winterSaladLevel=+" + sLevel);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            InputStream in = new BufferedInputStream(conn.getInputStream());
            BufferedReader br = new BufferedReader(new InputStreamReader(in));
            StringBuilder sb = new StringBuilder();
            String line;

            while ((line = br.readLine()) != null) {
                sb.append(line).append("\n");
            }
            response = sb.toString();
            in.close();
            br.close();

        }catch (ProtocolException e){
            e.printStackTrace();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            System.out.println("***GETJSON TOIMII***");
        }

        System.out.println(response);
        return response;
    }
}