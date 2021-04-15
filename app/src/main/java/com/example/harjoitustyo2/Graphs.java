package com.example.harjoitustyo2;

import android.content.Context;

import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;

public class Graphs {

    LineGraphSeries<DataPoint> series;
    double y = 0.0;
    JSONFileControl jsonFileControl = new JSONFileControl();

    public int getQuantity(Context context, String name, String value) {
        int q = 0;
        try {
            q = jsonFileControl.getQuantity(context, name, value);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return q;
    }

    public LineGraphSeries<DataPoint> createGraph(Context context, String name, String value) {
        double x = 0.0;
        series = new LineGraphSeries<>();
        int length = getQuantity(context, name, value);
        System.out.println("LENGTH: " + length);
        String p = "";
        for (int i = 0; i < length; i++) {

            try {
                p = jsonFileControl.getGraphData(context, name, value, i);
            } catch (Exception e) {
                e.printStackTrace();
            }
            System.out.println("P: " + p);
            y = Double.parseDouble(p);
            System.out.println("X2: " + x);
            series.appendData(new DataPoint(x, y), true, length);
            x = x + 1;
        }

        return series;
    }
}
