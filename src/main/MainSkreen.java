package main;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

import javax.swing.*;

public class MainSkreen  extends JFrame {

	private JButton button = new JButton("Press");
	private JTextField input = new JTextField("", 5);
	private JLabel label = new JLabel("Input:");
	private JRadioButton radio1 = new JRadioButton("Select this");
	private JRadioButton radio2 = new JRadioButton("Select that");
	private JCheckBox check = new JCheckBox("Check", false);
	JList list = new JList();
	
	public MainSkreen() {
		super("Пробное окно");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		JPanel panel = new JPanel();
		panel.setLayout(new GridLayout(2,3,5,10));
		panel.add(new JButton("Кнопка"));
		panel.add(new JButton("+"));
		panel.add(new JButton("-"));
		panel.add(new JButton("Кнопка с длинной надписью"));
		panel.add(new JButton("еще кнопка"));
		setContentPane(panel);
		setSize(250, 100);
	}
	
	class ButtonEventListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			String message = "";
			message += "Button was pressed\n";
			message += "Text is " + input.getText() + "\n";
			message += (radio1.isSelected()?"Radio #1":"Radio #2") 
                                + " is selected\n"; 
			message += "CheckBox is " + ((check.isSelected())
                                ?"checked":"unchecked"); 
			JOptionPane.showMessageDialog(null,
		    		message,
		    		"Output",
		    	    JOptionPane.PLAIN_MESSAGE);
		}
	}

	public static void main(String[] args) {
		JFrame myWindow = new MainSkreen();
		myWindow.setVisible(true);
	}
	}

