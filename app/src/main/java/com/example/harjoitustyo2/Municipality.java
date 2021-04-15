package com.example.harjoitustyo2;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

public class Municipality {
    ArrayList<String> mlist;

    public Municipality(){
        mlist = new ArrayList<>();
    }


    //Gets list of municipalities from Sotkanet API. Returns arraylist
    public ArrayList<String> getMunicipality(){

        try{
            DocumentBuilder builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
            String urlString = "https://sotkanet.fi/rest/1.1/rdf/regions"; //TODO
            Document doc = builder.parse(urlString);
            doc.getDocumentElement().normalize();
            System.out.println("Root element: " + doc.getDocumentElement().getNodeName());
            NodeList nList = doc.getDocumentElement().getElementsByTagName("sotkanet:Kunta");

            mlist.add("Municipality");

            for (int i = 0; i < nList.getLength() ; i++){
                Node node = nList.item(i);

                if (node.getNodeType() == Node.ELEMENT_NODE){
                    Element element = (Element) node;
                    String city = element.getElementsByTagName("dc:title").item(0).getTextContent();
                    mlist.add(city);
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
        Collections.sort(mlist);

        return mlist;
    }
}
