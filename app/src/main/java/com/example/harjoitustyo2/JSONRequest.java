package com.example.harjoitustyo2;

import android.content.Context;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

public class JSONRequest {
    JSONArray jsonArray;
    JSONFileControl jsonFileControl;
    ArrayList<String> municipalitylist;



    public JSONRequest(){
        jsonArray = new JSONArray();

    }

    //Writes climate data to Json file. Returns json object
    public JSONObject readJSON(String name, String diet, String beef, String fish, String pork, String dairy, String cheese, String rice, String egg, String salad, Context context){
        String json = getJSON(diet, beef, fish, pork, dairy, cheese, rice, egg, salad);
        JSONObject jsonObject = null;
        jsonFileControl = new JSONFileControl();

        if (json != null) {
            try {
                jsonObject = new JSONObject(json);
                String total = jsonObject.getString("Total");
                jsonFileControl.writeLog(total, context, name, "Total");

            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return jsonObject;

    }




    // Uses Climate Diet API. Returns values to json
    public String getJSON(String diet, String bLevel, String fLevel, String pLevel, String dLevel, String cLevel, String rLevel, String eLevel, String sLevel){
        String response = null;
        try {
            URL url = new URL("https://ilmastodieetti.ymparisto.fi/ilmastodieetti/calculatorapi/v1/FoodCalculator?query.diet=" + diet + "&query.beefLevel=" + bLevel + "&query.fishLevel=" + fLevel + "&query.porkPoultryLevel=" + pLevel + "&query.dairyLevel=" + dLevel + "&query.cheeseLevel=" + cLevel + "&query.riceLevel=" + rLevel + "&query.eggLevel=" + eLevel + "&query.winterSaladLevel=" + sLevel);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            InputStream in = new BufferedInputStream(conn.getInputStream());
            BufferedReader br = new BufferedReader(new InputStreamReader(in));
            StringBuilder sb = new StringBuilder();
            String line;

            while ((line = br.readLine()) != null) {
                sb.append(line);
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
        }

        return response;
    }

    public ArrayList<String> getMunicipality(){
        municipalitylist = new ArrayList<>();

        try{
            DocumentBuilder builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
            String urlString = "https://sotkanet.fi/rest/1.1/rdf/regions"; //TODO
            Document doc = builder.parse(urlString);
            doc.getDocumentElement().normalize();
            System.out.println("Root element: " + doc.getDocumentElement().getNodeName());
            NodeList nList = doc.getDocumentElement().getElementsByTagName("sotkanet:Kunta");


            for (int i = 0; i < nList.getLength() ; i++){
                Node node = nList.item(i);

                if (node.getNodeType() == Node.ELEMENT_NODE){
                    Element element = (Element) node;
                    String city = element.getElementsByTagName("dc:title").item(0).getTextContent();
                    municipalitylist.add(city);
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } finally {
            System.out.println("##########DONE##########");
        }
        Collections.sort(municipalitylist);

        return municipalitylist;
    }

}
