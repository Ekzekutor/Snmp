package main;

import java.awt.Color;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 * Copyright 2015-2017 Knowm Inc. (http://knowm.org) and contributors.
 * Copyright 2011-2015 Xeiam LLC (http://xeiam.com) and contributors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.CopyOnWriteArrayList;

import org.knowm.xchart.SwingWrapper;
import org.knowm.xchart.XYChart;
import org.knowm.xchart.XYChartBuilder;
import org.knowm.xchart.XYSeries;
import org.knowm.xchart.XYSeries.XYSeriesRenderStyle;
import org.knowm.xchart.style.Styler.ChartTheme;
import org.knowm.xchart.style.colors.XChartSeriesColors;

import main.getters.*;
import main.getters.getGetter;

/**
 * Real-time XY Chart
 * <p>
 * Demonstrates the following:
 * <ul>
 * <li>real-time chart updates with SwingWrapper
 * <li>Matlab Theme
 */
public class Main{

  private XYChart xyChart;

  private List<Double> yData;
  private List<Date> xData;
  public static final String SERIES_NAME = "series1";
  

  public static void main(String[] args) {

    // Setup the panel
    final Main realtimeChart01 = new Main();
    realtimeChart01.go();
  }

  private void go() {

    final SwingWrapper<XYChart> swingWrapper = new SwingWrapper<XYChart>(getChart());
    swingWrapper.displayChart();

    // Simulate a data feed
    TimerTask chartUpdaterTask = new TimerTask() {
    
      @Override
      public void run() {

        updateData();

        javax.swing.SwingUtilities.invokeLater(new Runnable() {

          @Override
          public void run() {

            swingWrapper.repaintChart();
          }
        });
      }
    };

    Timer timer = new Timer();
    timer.scheduleAtFixedRate(chartUpdaterTask,0, 5000);
  }

  public XYChart getChart() {

    yData = getRandomData(1);
    xData=new ArrayList<Date>();
    SimpleDateFormat df2 = new SimpleDateFormat("HH:mm:ss");
    Date curr=new Date(System.currentTimeMillis());
    System.out.println(curr.toString());
    System.out.println(df2.format(curr));
    xData.add(curr);
    // Create Chart
    
    xyChart = new XYChartBuilder().width(800).height(400).theme(ChartTheme.Matlab).title("Real-time XY Chart").build();
    XYSeries series = xyChart.addSeries(SERIES_NAME, xData, yData);
    xyChart.getStyler().setYAxisMax(100.0);
    xyChart.getStyler().setYAxisMin(0.0);
    xyChart.getStyler().setDatePattern("HH:mm:ss");
    series.setLineColor(XChartSeriesColors.GREEN);
    series.setFillColor(XChartSeriesColors.GREEN);
    series.setMarkerColor(XChartSeriesColors.GREEN);
    xyChart.getStyler().setDefaultSeriesRenderStyle(XYSeriesRenderStyle.Area);
    return xyChart;
  }

  public void updateData() {

    // Get some new data
    List<Double> newData = getRandomData(1);
    SimpleDateFormat df2 = new SimpleDateFormat("HH:mm:ss");
    yData.addAll(newData);
    Date curr=new Date(System.currentTimeMillis());
    System.out.println(curr.toString());
    System.out.println(df2.format(curr));
    xData.add(curr);
    // Limit the total number of points
    while (yData.size() > 20) {
      yData.remove(0);
    }
    if (xData.size()>20){
    	xData.remove(0);
    }
    xyChart.updateXYSeries(SERIES_NAME, xData, yData, null);
  }

  private List<Double> getRandomData(int numPoints) {

    List<Double> data = new CopyOnWriteArrayList<Double>();
    for (int i = 0; i < numPoints; i++) {
      try {
	//	data.add(Double.parseDouble(getGetter.get("192.168.1.5"," 1.3.6.1.4.1.171.12.1.1.6.1.0")));
    	  //data.add(Double.parseDouble(getGetter.get("192.168.1.5","1.3.6.1.4.1.171.11.113.1.3.2.2.1.1.5")));
	} catch (NumberFormatException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
    }
    return data;
  }
}