package main.query;

import java.util.Vector;

import org.snmp4j.smi.Counter64;
import org.snmp4j.smi.VariableBinding;


public class encoder {
	public static Vector<VariableBinding> encodeBulk( Vector<? extends VariableBinding> vector, String [] param){
		Vector<VariableBinding> vbr = null;
        int vCount= vector.size();
    	for(int i = 0; i < vCount; i++) {
    		for (int j=0; j<param.length;j++){
    		//StringBuilder b = new StringBuilder();
    		StringBuilder temp = new StringBuilder();
    		VariableBinding var = vector.get(i);
    		temp.append(var.getVariable().getSyntaxString());
    		if (temp.toString().contains(param[j])){
			/*b.append("\n\t\t\tType:").append(var.getVariable().getSyntaxString());
			b.append("\n\t\t\tVaue:").append(var.toValueString());
			System.out.println(b);*/}
    		else
    		vbr.addElement(var);
    	}}
		return vbr;
	}
}
