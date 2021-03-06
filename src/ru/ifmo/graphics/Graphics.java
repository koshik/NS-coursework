package ru.ifmo.graphics;

import com.xeiam.xchart.*;
import ru.ifmo.lang.Typke;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;


public class Graphics {

    private Chart chart;

    public Graphics(String XAsis, String YAsis) {
        chart = new ChartBuilder().width(800).height(600).build();
        chart.getStyleManager().setLegendPosition(StyleManager.LegendPosition.InsideNW);
        chart.setXAxisTitle(XAsis);
        chart.setYAxisTitle(YAsis);
    }


    public Graphics addGraphic(List<Typke> points, String graphicName) {

        List<Double> xData = new LinkedList<>();
        List<Double> yData = new LinkedList<>();
        for (Typke point : points) {
            xData.add(point.x);
            yData.add(point.y);
        }
        chart.addSeries(graphicName, xData, yData);

        return this;
    }

    public void show() {
        new SwingWrapper(chart).displayChart();
    }

    public void saveBitmap(String filename) {
        try {
            BitmapEncoder.saveBitmap(chart, filename, BitmapEncoder.BitmapFormat.PNG);
        } catch (IOException e) {
            System.err.println("Unable to save " + filename + ": " + e.getMessage());
        }
    }

    public void saveBitmap(String filename, BitmapEncoder.BitmapFormat format) {
        try {
            BitmapEncoder.saveBitmap(chart, filename, format);
        } catch (IOException e) {
            System.err.println("Unable to save " + filename + ": " + e.getMessage());
        }
    }
}
