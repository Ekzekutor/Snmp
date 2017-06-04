/*package main.getters;

import java.io.IOException;
import java.util.Vector;

import org.snmp4j.CommunityTarget;
import org.snmp4j.PDU;
import org.snmp4j.Snmp;
import org.snmp4j.event.ResponseEvent;
import org.snmp4j.mp.SnmpConstants;
import org.snmp4j.smi.OID;
import org.snmp4j.smi.OctetString;
import org.snmp4j.smi.UdpAddress;
import org.snmp4j.smi.Variable;
import org.snmp4j.smi.VariableBinding;
import org.snmp4j.transport.DefaultUdpTransportMapping;

public class bulkGetter {
	private static String  port    = "161";
	public static Vector<? extends VariableBinding> getBulk(String ipAddress, String oid) throws IOException{
		Snmp snmp = new Snmp(new DefaultUdpTransportMapping());
	    snmp.listen();

	    CommunityTarget target = new CommunityTarget();
	    target.setCommunity(new OctetString("private"));
	    target.setVersion(SnmpConstants.version2c);
	    target.setAddress(new UdpAddress(ipAddress + "/" + port));
	    target.setTimeout(3000);    //3s
	    target.setRetries(1);

	    PDU pdu = new PDU();
	    pdu.setType(PDU.GETBULK);
	    pdu.setMaxRepetitions(200); 
	    pdu.setNonRepeaters(0);
	    pdu.add(new VariableBinding(new OID(oid))); 

	    ResponseEvent responseEvent = snmp.send(pdu, target);
	    PDU response = responseEvent.getResponse();

	    if (response == null) {
	        System.out.println("TimeOut...");
	    } 
	    else 
	    {
	        if (response.getErrorStatus() == PDU.noError) 
	        {
	        	StringBuilder b = new StringBuilder();
	            Vector<? extends VariableBinding> vbs = response.getVariableBindings();
	            
	              //System.out.println(vb.toValueString() + " ," + vb.getVariable().getSyntaxString());
	            }
	        	VariableBinding vbs = pdu.get(i);
	            int vCount= vbs.size();
	        	for(int i = 0; i < vCount; i++) {
	    			b.append("\n\t\t#").append(i).append(":");
	    			//b.append("\n\t\t\tOID:").append(vbs.get(i).getOid().toString());
	        		VariableBinding var = vbs.get(i);
	    			b.append("\n\t\t\tType:").append(var.getVariable().getSyntaxString());
	    			b.append("\n\t\t\tVaue:").append(var.toValueString());
	    			System.out.println(b);  
	    			
	        	return vbs; 
	              
	        } 
	        else 
	        {
	            System.out.println("Error:" + response.getErrorStatusText());
	            return null;
	        }
	    }
		return null;
		}
	}*/
