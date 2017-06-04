package main;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Vector;

import org.snmp4j.smi.VariableBinding;
import org.knowm.xchart.*;
import org.knowm.xchart.style.Styler.ChartTheme;

import main.getters.*;
import main.query.*;
public class tempMain{
	
	  private List<Double> bubbleData;
	 
	  public static final String SERIES_NAME = "series1";
	public static void main(String[] args) throws Exception {
		 ArrayList<Double> yData=new ArrayList<Double>();
		 ArrayList<Double> xData=new ArrayList<Double>();
		 List<Double> newErrorBarData=null;
	    System.out.println("Start");
	 //  getGetter.get("192.168.1.5","1.3.6.1.2.1.1.3.0");
	   //getNextGetter.getNext("192.168.1.5", "1.3.6.1.2.1.4.22.1");
	   String [] param={"IpAddress"};
	   ArrayList<QueryResult> array=  SnmpGetter.getBulk("192.168.1.5", "1.3.6.1.2.1.4.22.1.3");
	   for(int i=0; i<array.size();i++){
		   System.out.println(array.get(i).getType());
		   System.out.println(array.get(i).getValue());
	   }
	   xData.add(0.0);
	   yData.add(0.0);
	   //double[] xData = new double[] {0.0};
	   //double[] yData = new double[] {0.0 };
	    // Create Chart
	    XYChart chart= new XYChart(600, 400, ChartTheme.XChart);
	    chart.addSeries("y(x)", xData, yData);
	    		//QuickChart.getChart("Sample Chart", "X", "Y", "y(x)", xData, yData);
	    // Show it
	    new SwingWrapper<XYChart>(chart).displayChart();
	    while(1!=0){
	    	
	    	//yData.add(Double.parseDouble(getGetter.get("192.168.1.5"," 1.3.6.1.4.1.171.12.1.1.6.1.0")));
	    	Long time=System.currentTimeMillis();
	    	xData.add(time.doubleValue()/10000);
	    	chart.updateXYSeries("y(x)", xData, yData,newErrorBarData);
	    	Thread.sleep(10000);
	    }
	 
}
}
	

