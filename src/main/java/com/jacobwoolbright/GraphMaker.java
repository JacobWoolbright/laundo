package com.jacobwoolbright;

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

    public static void generateLineGraphWashers(){
        TimeSeries series = new TimeSeries("Available Washers in Couch Center in the Last 24h");

        Map<Date, Integer> points = FilterGraph.getGraphPointsWasher();

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

    public static void generateLineGraphWashers(String timeSpan){
        TimeSeries series = new TimeSeries("Available Washers in Couch Center in the Last " + timeSpan);

        Map<Date, Integer> points = FilterGraph.getGraphPointsWasher(timeSpan);

        for(Date time:points.keySet()){
            series.add(new Second(time), points.get(time));
        }

        TimeSeriesCollection dataset = new TimeSeriesCollection(series);
        JFreeChart chart = ChartFactory.createTimeSeriesChart(
                "Available Washers in Couch Center in the Last " + timeSpan,
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

    public static void generateLineGraphWashers(String timeSpan, String groupTime){
        TimeSeries series = new TimeSeries("Available Washers in Couch Center in the Last " + timeSpan);

        Map<Date, Integer> points = FilterGraph.getGraphPointsWasher(timeSpan, groupTime);

        for(Date time:points.keySet()){
            series.add(new Second(time), points.get(time));
        }

        TimeSeriesCollection dataset = new TimeSeriesCollection(series);
        JFreeChart chart = ChartFactory.createTimeSeriesChart(
                "Available Washers in Couch Center in the Last " + timeSpan + "Grouped by: " + groupTime,
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

    public static void generateLineGraphDryers(){
        TimeSeries series = new TimeSeries("Available Dryers in Couch Center in the Last 24h");

        Map<Date, Integer> points = FilterGraph.getGraphPointsDryer();


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

    public static void generateLineGraphDryers(String timespan){
        TimeSeries series = new TimeSeries("Available Dryers in Couch Center in the Last " + timespan);

        Map<Date, Integer> points = FilterGraph.getGraphPointsDryer(timespan);

        for(Date time:points.keySet()){
            series.add(new Second(time), points.get(time));
        }

        TimeSeriesCollection dataset = new TimeSeriesCollection(series);
        JFreeChart chart = ChartFactory.createTimeSeriesChart(
                "Available Dryers in Couch Center in the Last " + timespan,
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

    public static void generateLineGraphDryers(String timeSpan, String groupTime){
        TimeSeries series = new TimeSeries("Available Dryers in Couch Center in the Last " + timeSpan);

        Map<Date, Integer> points = FilterGraph.getGraphPointsDryer(timeSpan, groupTime);

        for(Date time:points.keySet()){
            series.add(new Second(time), points.get(time));
        }

        TimeSeriesCollection dataset = new TimeSeriesCollection(series);
        JFreeChart chart = ChartFactory.createTimeSeriesChart(
                "Available Dryers in Couch Center in the Last " + timeSpan + "Grouped by: " + timeSpan,
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
