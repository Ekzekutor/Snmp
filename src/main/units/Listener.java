package main.units;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Timer;

public class Listener {

	private Timer timer;
	private boolean enabled;
	Listener(Unit unit){
		enabled=true;
		timer = new Timer(5000, new ActionListener() {
		    public void actionPerformed(ActionEvent evt) {
		    	unit.updateData();
		        }
		});
	}	
}

