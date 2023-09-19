package com.jacobwoolbright;

import com.jacobwoolbright.db.DatabaseManager;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.data.time.Second;
import org.jfree.data.time.TimeSeries;
import org.jfree.chart.ChartUtils;
import org.jfree.data.time.TimeSeriesCollection;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.Map;

public class GraphMaker {

    public static void GenerateLineGraphWashers(){
        TimeSeries series = new TimeSeries("Available Washers in Couch Center in the Last 24h");

        Map<Date, Integer> points = DatabaseManager.getInstance().getWasherAvailabilityBy5Minutes();

        for(Date time:points.keySet()){
            series.add(new Second(time), points.get(time));
        }

        TimeSeriesCollection dataset = new TimeSeriesCollection(series);
        JFreeChart chart = ChartFactory.createTimeSeriesChart(
                "Available Washers in Couch Center in the Last 24h",
                "Time",
                "# of available machines",
                dataset,
                false,
                true,
                false
        );

        XYPlot plot = chart.getXYPlot();
        XYLineAndShapeRenderer renderer = new XYLineAndShapeRenderer();
        plot.setRenderer(renderer);

        try {
            ChartUtils.saveChartAsPNG(new File("time_line_graph.png"), chart, 800, 600);
            System.out.println("Chart saved as time_line_graph.png");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void GenerateLineGraphDryers(){
        TimeSeries series = new TimeSeries("Available Dryers in Couch Center in the Last 24h");

        Map<Date, Integer> points = DatabaseManager.getInstance().getDryerAvailabilityBy5Minutes();

        for(Date time:points.keySet()){
            series.add(new Second(time), points.get(time));
        }

        TimeSeriesCollection dataset = new TimeSeriesCollection(series);
        JFreeChart chart = ChartFactory.createTimeSeriesChart(
                "Available Dryers in Couch Center in the Last 24h",
                "Time",
                "# of available machines",
                dataset,
                false,
                true,
                false
        );

        XYPlot plot = chart.getXYPlot();
        XYLineAndShapeRenderer renderer = new XYLineAndShapeRenderer();
        plot.setRenderer(renderer);

        try {
            ChartUtils.saveChartAsPNG(new File("time_line_graph.png"), chart, 800, 600);
            System.out.println("Chart saved as time_line_graph.png");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void GenerateLineGraphDryers(String timeRange){
        TimeSeries series = new TimeSeries("Dryers");

        Map<Date, Integer> points = DatabaseManager.getInstance().getDryerAvailabilityByTime(timeRange);

        for(Date time:points.keySet()){
            series.add(new Second(time), points.get(time));
        }

        TimeSeriesCollection dataset = new TimeSeriesCollection(series);
        JFreeChart chart = ChartFactory.createTimeSeriesChart(
                "Dryers",
                "Time",
                "# of available machines",
                dataset,
                false,
                true,
                false
        );

        XYPlot plot = chart.getXYPlot();
        XYLineAndShapeRenderer renderer = new XYLineAndShapeRenderer();
        plot.setRenderer(renderer);

        try {
            ChartUtils.saveChartAsPNG(new File("time_line_graph.png"), chart, 800, 600);
            System.out.println("Chart saved as time_line_graph.png");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
