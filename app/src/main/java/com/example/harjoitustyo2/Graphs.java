package com.example.harjoitustyo2;

import android.content.Context;

import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;

public class Graphs {

    JSONFileControl jsonFileControl = new JSONFileControl();

    LineGraphSeries<DataPoint> series;
    double y = 0.0;

    //Method gets size of Json file and returns it to graph creating method
    public int getQuantity(Context context, String name, String value) {
        int q = 0;
        try {
            q = jsonFileControl.getQuantity(context, name, value);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return q;
    }

    //Method gets data from Json file to graph series and returns graph data series
    public LineGraphSeries<DataPoint> createGraph(Context context, String name, String value) {
        double x = 0.0;
        series = new LineGraphSeries<>();
        int length = getQuantity(context, name, value);
        String p = "";
        for (int i = 0; i < length; i++) {

            try {
                p = jsonFileControl.getGraphData(context, name, value, i);
            } catch (Exception e) {
                e.printStackTrace();
            }
            y = Double.parseDouble(p);
            series.appendData(new DataPoint(x, y), true, length);
            x = x + 1;
        }

        return series;
    }
}
