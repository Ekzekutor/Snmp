package main.query;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import main.getters.getGetter;

public class getQuery {

	public static String getModelName(String ip, String port,String mibPutch){
		 return getGetter.get(ip,port,"1.3.6.1.2.1.1.1.0");
	}
	
	public static String getUpDateTime(String ip,String port,String mibPutch){
		 return getGetter.get(ip,port,"1.3.6.1.2.1.1.3.0");
	}
	
	public static Integer getCPUUtilization(String ip,String port,String mibPutch){
			return Integer.parseInt(getGetter.get(ip, port," 1.3.6.1.4.1.171.12.1.1.6.1.0"));
	   
	  }
}
