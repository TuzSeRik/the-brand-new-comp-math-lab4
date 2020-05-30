package io.github.tuzserik.utility;

import org.knowm.xchart.XYChart;
import org.knowm.xchart.XYChartBuilder;
import org.knowm.xchart.style.Styler;
import org.knowm.xchart.style.markers.SeriesMarkers;
import org.knowm.xchart.SwingWrapper;

import java.util.function.Function;
import java.util.ArrayList;

public class Charter {
    XYChart chart;
    double a;
    double b;

    Function<Double, Double> yFunction = (Double x) -> 0.0;

    public Charter(String title, double a, double b) {
        this.a = a;
        this.b = b;
        chart = new XYChartBuilder().width(640).height(480).theme(Styler.ChartTheme.Matlab)
                .title(title)
                .xAxisTitle("X").yAxisTitle("Y").build();

        generateSeries("Y = 0", yFunction);
    }

    public void generateSeries(String name, Function<Double, Double> function) {
        ArrayList<double[]> parameters = getPoints(function);
        chart.addSeries(name, parameters.get(0), parameters.get(1)).setMarker(SeriesMarkers.NONE);
    }

    public void addPoints(double[] xPoints, double[] yPoints) {
        chart.addSeries("Точки", xPoints, yPoints);
    }

    private ArrayList<double[]> getPoints(Function<Double, Double> function) {
        ArrayList<double[]> returningCoordinates = new ArrayList<>();
        double[] x = new double[100];
        double[] y = new double[100];
        double j = a;

        for (int i = 0; i < 100; i++) {
            x[i] = j;
            y[i] = function.apply(j);
            j+= Math.abs(b - a) / 100;
        }

        returningCoordinates.add(x);
        returningCoordinates.add(y);

        return returningCoordinates;
    }

    @SuppressWarnings({"rawtypes", "unchecked"})
    public void show() {
        new SwingWrapper(chart).displayChart();
    }
}
