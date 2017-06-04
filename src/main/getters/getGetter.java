package main.getters;

import java.io.IOException;

import org.snmp4j.CommunityTarget;
import org.snmp4j.PDU;
import org.snmp4j.Snmp;
import org.snmp4j.TransportMapping;
import org.snmp4j.event.ResponseEvent;
import org.snmp4j.mp.SnmpConstants;
import org.snmp4j.smi.Integer32;
import org.snmp4j.smi.OID;
import org.snmp4j.smi.OctetString;
import org.snmp4j.smi.UdpAddress;
import org.snmp4j.smi.VariableBinding;
import org.snmp4j.transport.DefaultUdpTransportMapping;

public class getGetter {
	  private static int    snmpVersion  = SnmpConstants.version2c;
	  private static String  community  = "private";
	  public static String get(String ipAddress, String port ,String oidValue)
	  {
		  TransportMapping transport;
		try {
			transport = new DefaultUdpTransportMapping();
			transport.listen();
			CommunityTarget comtarget = new CommunityTarget();
		    comtarget.setCommunity(new OctetString(community));
		    comtarget.setVersion(snmpVersion);
		    comtarget.setAddress(new UdpAddress(ipAddress + "/" + port));
		    comtarget.setRetries(2);
		    comtarget.setTimeout(1000);

		    // Create the PDU object
		    PDU pdu = new PDU();
		    pdu.add(new VariableBinding(new OID(oidValue)));
		    pdu.setType(PDU.GET);
		    pdu.setRequestID(new Integer32(1));
		    Snmp snmp = new Snmp(transport);
		    ResponseEvent response;
		    response = snmp.get(pdu, comtarget);
			if (response != null)
		    {
		      PDU responsePDU = response.getResponse();

		      if (responsePDU != null)
		      {
		        int errorStatus = responsePDU.getErrorStatus();
		        int errorIndex = responsePDU.getErrorIndex();
		        String errorStatusText = responsePDU.getErrorStatusText();

		        if (errorStatus == PDU.noError)
		        {
		        	System.out.println( responsePDU.getVariableBindings().elementAt(0).toValueString());
		         return responsePDU.getVariableBindings().elementAt(0).toValueString();
		        }
		        else
		        {
		          System.out.println("Error: Request Failed");
		          System.out.println("Error Status = " + errorStatus);
		          System.out.println("Error Index = " + errorIndex);
		          System.out.println("Error Status Text = " + errorStatusText);
		        }
		      }
		      else
		      {
		        System.out.println("Error: Response PDU is null");
		      }
		    }
		    else
		    {
		      System.out.println("Error: Agent Timeout... ");
		    }
		    snmp.close();
		    return "";
		} catch (IOException e) {
			e.printStackTrace();
		} 
			return "";
	  }
}
