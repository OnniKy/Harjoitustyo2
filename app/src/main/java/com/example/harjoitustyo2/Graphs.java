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

    public LineGraphSeries createGraph(double x, LineGraphSeries series, Context context, String name, String value) {
        int length = getQuantity(context, name, value);
        System.out.println("LENGTH: " + length);
        String p = "";
        for (int i = 0; i < length - 1; i++) {
            int left = length - i;

            x = x + 1;
            try {
                p = jsonFileControl.getGraphData(context, name, value, left);
            } catch (Exception e) {
                e.printStackTrace();
            }
            y = Integer.parseInt(p);
            System.out.println("X2: " + x);
            series.appendData(new DataPoint(x, y), true, length - 1);
        }

        return series;
    }
}
