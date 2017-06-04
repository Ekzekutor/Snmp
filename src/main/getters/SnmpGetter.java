package main.getters;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Vector;

import org.snmp4j.CommunityTarget;
import org.snmp4j.PDU;
import org.snmp4j.Snmp;
import org.snmp4j.event.ResponseEvent;
import org.snmp4j.mp.SnmpConstants;
import org.snmp4j.smi.Address;
import org.snmp4j.smi.GenericAddress;
import org.snmp4j.smi.OID;
import org.snmp4j.smi.OctetString;
import org.snmp4j.smi.VariableBinding;
import org.snmp4j.transport.DefaultUdpTransportMapping;

public class SnmpGetter {


public static ArrayList<QueryResult> getBulk(String ipAddress,String oid) throws IOException, InterruptedException {
		    Snmp snmp = new Snmp(new DefaultUdpTransportMapping());
		    snmp.listen();
		  
		   
		    Address targetAddress = GenericAddress.parse(ipAddress+"/161");
		    CommunityTarget target = new CommunityTarget();
		    target.setCommunity(new OctetString("private"));
		    target.setVersion(SnmpConstants.version2c);
		    target.setAddress(targetAddress);
		    target.setTimeout(3000);    //3s
		    target.setRetries(1);

		    PDU pdu = new PDU();
		    pdu.setType(PDU.GETBULK);
		    pdu.setMaxRepetitions(200);
		    pdu.setNonRepeaters(0);
		    pdu.add(new VariableBinding(new OID(oid)));

		    ResponseEvent responseEvent = snmp.send(pdu, target);
		    PDU response = responseEvent.getResponse();
		    
		    ArrayList<QueryResult>responsePieces =new ArrayList<QueryResult>();
		    if (response == null) {
		        System.out.println("TimeOut...");
		    }
		    else
		    {
		        if (response.getErrorStatus() == PDU.noError)
		        {
		            Vector<? extends VariableBinding> vbs = response.getVariableBindings();
		            for (VariableBinding vb : vbs) {
		            	QueryResult result=new QueryResult();
		            	result.setType(vb.getVariable().getSyntaxString());
		            	//System.out.println(vb.getVariable().getSyntaxString());
		            	//System.out.println(vb.toString());
		            	result.setValue(vb.toValueString());
		                responsePieces.add(result);
		            }
		        }
		        else
		        {
		            System.out.println("Error:" + response.getErrorStatusText());
		        }
		    }
		    return responsePieces;
		}
	}

	

