package main.units;

import java.util.ArrayList;
import java.util.Date;

import main.query.getQuery;

public class Unit {
	private String name;
	private String modelName;
	private String ipAddress;
	private String port;
	private String mibPatch;
	private String upTime;
	private boolean enabled;
	private Listener listener;
	Utilization utilization=new Utilization();
	public Unit(String name, String ipAddress, String port, String mibPatch, boolean enabled){
		this.name=name;
		this.ipAddress=ipAddress;
		this.port=port;
		this.mibPatch=mibPatch;
		this.enabled=false;
		if (this.enabled){
			listener = new Listener(this);
			
		}
	}
	
	public String getName() {
		return name;
	}
	public void setEnabled(boolean enabled){
		this.enabled=enabled;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getIpAddress() {
		return ipAddress;
	}
	public void setIpAddress(String ipAddress) {
		this.ipAddress = ipAddress;
	}
	public String getPort() {
		return port;
	}
	public void setPort(String port) {
		this.port = port;
	}
	public String getMibPatch() {
		return mibPatch;
	}
	public void setMibPatch(String mibPatch) {
		this.mibPatch = mibPatch;
	}
	
	public void getInformation(){
		this.modelName=getQuery.getModelName(ipAddress, port,"");
		this.upTime=getQuery.getUpDateTime(ipAddress,port ,"");
	}
	
	public String getUpTime() {
		return upTime;
	}

	public String getModelName() {
		return modelName;
	}

	public ArrayList<Integer> getCpuUtilization(){
		return utilization.getCpuUtiliztion();
	}
	public ArrayList<Date> getCpuUtilizationDate(){
		return utilization.getCpuUtiliztionDate();
	}
	
	public void updateData(){
		upTime=getQuery.getUpDateTime(ipAddress,port ,"");
		utilization.addCpuUtiliztion(getQuery.getCPUUtilization(ipAddress, port, ""));
	}

	
	
	
	
}
