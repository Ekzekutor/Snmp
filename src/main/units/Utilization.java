package main.units;

import java.util.ArrayList;
import java.util.Date;

public class Utilization {
	private ArrayList<Integer> cpuUtiliztion = new ArrayList<Integer>();
	private ArrayList<Date> cpuUtiliztionDate = new ArrayList<Date>();
	
	Utilization(){
		addCpuUtiliztion(0);
	}
	
	public ArrayList<Integer> getCpuUtiliztion() {
		return cpuUtiliztion;
	}
	public void addCpuUtiliztion(int util){
		cpuUtiliztion.add(util);
		cpuUtiliztionDate.add(new Date(System.currentTimeMillis()));
	}
	public ArrayList<Date> getCpuUtiliztionDate() {
		return cpuUtiliztionDate;
	}
	//public void setCpuUtiliztionDate(ArrayList<Date> cpuUtiliztionDate) {
	//	this.cpuUtiliztionDate = cpuUtiliztionDate;
	//}
	
	public int getSize() {
		return cpuUtiliztionDate.size();
	}
}
