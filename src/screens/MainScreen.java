package screens;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JList;
import java.awt.BorderLayout;
import javax.swing.JTabbedPane;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JPanel;
import java.awt.Canvas;
import javax.swing.JToggleButton;
import javax.swing.Timer;

import org.knowm.xchart.XChartPanel;
import org.knowm.xchart.XYChart;
import org.knowm.xchart.XYChartBuilder;
import org.knowm.xchart.XYSeries;
import org.knowm.xchart.XYSeries.XYSeriesRenderStyle;
import org.knowm.xchart.style.Styler.ChartTheme;
import org.knowm.xchart.style.colors.XChartSeriesColors;

import main.getters.getGetter;
import main.units.Unit;

import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.TimerTask;
import java.util.concurrent.CopyOnWriteArrayList;
import java.awt.event.ActionEvent;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.ListSelectionEvent;

public class MainScreen extends JFrame{

	private JFrame frame;
	private List<Integer> yData=new ArrayList<Integer>();
	private List<Date> xData;
	JPanel panel;
	XYChart chart;
	JLabel lblNewLabel;
	JButton btnNewApp;
	JButton btnDelApp;
	JButton btnStartButton;
	JMenuBar menuBar;
	JMenu menu;
	
	JLabel lblAppName;
	JLabel lblModellApp;
	JLabel lblUpTime;
	protected static ArrayList<Unit> units = new ArrayList<Unit>();
	private JTable table;
	static MainScreen mainScreen;
	Timer timer;
	DefaultListModel<String> listModel = new DefaultListModel<String>();
	JList<String> list = new JList<String>(listModel);
	//public ArrayList<Unit> units = new ArrayList<Unit>();
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		mainScreen=new MainScreen();
		mainScreen.go();
		mainScreen.frame.setVisible(true);
		
    	  
	}

	
	/**
	 * Create the application.
	 */
	public MainScreen() {
		initialize();
	}

	private void go(){
		EventQueue.invokeLater(new Runnable() {
		          @Override
		          public void run() {
		        	 
		          }
		        });
	}
	
	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		
		frame = new JFrame();
		frame.setBounds(100, 100, 1589, 869);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		list.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent arg0) {
				updateInformationByNumber(list.getSelectedIndex());
				selectChart();
			}
		});
		initChart();
		
		//JScrollPane listPane = new JScrollPane(table, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		list.setBounds(10, 28, 126, 736);
		frame.getContentPane().add(list);
		
		btnNewApp = new JButton("+");
		btnNewApp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//newUnit(new Unit ("asdas","asd","ads","ads",false));
				JFrame myWindow =new ApparationScreen(mainScreen);		
			}
		});
		btnNewApp.setBounds(10, 775, 46, 23);
		frame.getContentPane().add(btnNewApp);
		
		btnDelApp = new JButton("-");
		btnDelApp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (list.getSelectedIndex()>=0){
					units.remove(list.getSelectedIndex());
				listModel.remove(list.getSelectedIndex());
			}}
		});
		btnDelApp.setBounds(90, 775, 46, 23);
		frame.getContentPane().add(btnDelApp);
		
		lblNewLabel = new JLabel("Устройства");
		lblNewLabel.setBounds(10, 11, 115, 14);
		frame.getContentPane().add(lblNewLabel);
		
		
		lblAppName = new JLabel("Имя устройства:");
		lblAppName.setBounds(146, 29, 388, 14);
		frame.getContentPane().add(lblAppName);
		
		lblModellApp = new JLabel("Модель устройства:");
		lblModellApp.setBounds(146, 54, 388, 14);
		frame.getContentPane().add(lblModellApp);
		
		lblUpTime = new JLabel("Время работы:");
		lblUpTime.setBounds(146, 79, 388, 14);
		frame.getContentPane().add(lblUpTime);
		
		timer = new Timer(5000, new ActionListener() {
		    public void actionPerformed(ActionEvent evt) {
		    	updateData();
		        }
		     
		});
		btnStartButton = new JButton("Начать мониторинг");
		btnStartButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (btnStartButton.getText().equals("Начать мониторинг")){
					
					timer.start();
				btnStartButton.setText("Остановить мониторинг");}
				else
					{btnStartButton.setText("Начать мониторинг");
					timer.stop();
					}
			}
		});
		btnStartButton.setBounds(643, 775, 176, 23);
		frame.getContentPane().add(btnStartButton);
		
		
		
		
	
		
		table = new JTable();
		//JScrollPane tablePane= new JScrollPane(table, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		table.setBounds(1319, 11, 244, 787);
		frame.getContentPane().add(table);
		
		menuBar = new JMenuBar();
		frame.setJMenuBar(menuBar);
		
		menu = new JMenu("Файл");
		menuBar.add(menu);
		
		
	}
	
	 public void updateData() {
		    for(int i=0;i<units.size();i++){
		    	units.get(i).updateData();
		    }
		    
		   // List<Double> newData = getRandomData(1);
		   // SimpleDateFormat df2 = new SimpleDateFormat("HH:mm:ss");
		   // yData.addAll(units.get(list.getSelectedIndex()).);
		    // Limit the total number of points
		   

		    chart.updateXYSeries("Загрузка процессра", units.get(list.getSelectedIndex()).getCpuUtilizationDate(), units.get(list.getSelectedIndex()).getCpuUtilization(), null);
		    panel.repaint();
		  }
	 
	 public void newUnit(Unit unit){
		 units.add(unit);
		 updateInformationByNumber(units.size()-1);
		 listModel.addElement(units.get(units.size()-1).getName());
		 
	 }
	 
	 public void selectChart(){

		    // Limit the total number of points
		    chart.updateXYSeries("Загрузка процессра",units.get(list.getSelectedIndex()).getCpuUtilizationDate(), units.get(list.getSelectedIndex()).getCpuUtilization(), null);
		    panel.repaint();
	 }
	 
	 public void initChart(){
		 	yData.add(0);
			xData=new ArrayList<Date>();
			xData.add(new Date(System.currentTimeMillis()));
			chart = new XYChartBuilder().theme(ChartTheme.XChart).title("График загрузки CPU").width(176).height(23).build();
			XYSeries series = chart.addSeries("Загрузка процессра", xData, yData);
			chart.getStyler().setYAxisMax(100.0);
		    chart.getStyler().setYAxisMin(0.0);
		    //chart.getStyler().setXAxisMax(10000000.0);
		   // chart.getStyler().setXAxisMin(0.0);
		    chart.getStyler().setMarkerSize(0);
		    chart.getStyler().setDatePattern("HH:mm:ss");
		    series.setLineColor(XChartSeriesColors.GREEN);
		    series.setFillColor(XChartSeriesColors.GREEN);
		    series.setMarkerColor(XChartSeriesColors.GREEN);
		    chart.getStyler().setDefaultSeriesRenderStyle(XYSeriesRenderStyle.Area);
			panel =new XChartPanel(chart);
			panel.setBounds(142, 97, 1167, 663);
			frame.getContentPane().add(panel);
	 }
	public void initGraph(){
		//yData.add(0);
		//xData=new ArrayList<Date>();
		//SimpleDateFormat df2 = new SimpleDateFormat("HH:mm:ss");
		//Date curr=new Date(System.currentTimeMillis());
		//System.out.println(curr.toString());
		//System.out.println(df2.format(curr));
		//xData.add(curr);
		//chart = new XYChartBuilder().theme(ChartTheme.XChart).title("График загрузки CPU").width(176).height(23).build();
		
	    
	}
	 
	 public void updateInformationByNumber(int i){
		 units.get(i).getInformation();
		 lblAppName.setText("Имя устройства: "+units.get(i).getName());
		 lblModellApp.setText("Модель устройства: "+units.get(i).getModelName());
		 lblUpTime.setText("Время работы: "+units.get(i).getUpTime());
	 }
	 
}
