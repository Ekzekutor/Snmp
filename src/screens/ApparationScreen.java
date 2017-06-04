package screens;


import javax.swing.JFrame;
import javax.swing.JTextField;
import main.units.Unit;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.JCheckBox;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ApparationScreen extends JFrame{

	//private static final long serialVersionUID = -8928781018627372872L;
	private JFrame frmAdd;
	private JTextField nameField;
	private JTextField ipField;
	private JTextField portField;
	MainScreen mainScreen;
	public ApparationScreen(MainScreen mainScreen) {
		 initialize();
		 this.frmAdd.setVisible(true);
		 this.mainScreen=mainScreen;
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmAdd = new JFrame();
		frmAdd.setTitle("Устройства");
		frmAdd.setBounds(100, 100, 267, 268);
		frmAdd.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		ipField = new JTextField();
		ipField.setBounds(22, 77, 110, 20);
		ipField.setColumns(10);
		
		nameField = new JTextField();
		nameField.setBounds(22, 31, 203, 20);
		nameField.setColumns(10);
		
		JLabel label = new JLabel("Имя устройства");
		label.setBounds(22, 11, 202, 14);
		
		JLabel lblIp = new JLabel("Ip адрес");
		lblIp.setBounds(22, 57, 96, 14);
		
		portField = new JTextField();
		portField.setBounds(138, 77, 86, 20);
		portField.setColumns(10);
		
		JLabel label_1 = new JLabel("Порт");
		label_1.setBounds(128, 57, 96, 14);
		
		JComboBox versionBox = new JComboBox();
		versionBox.setBounds(22, 123, 130, 20);
		versionBox.addItem("SNMP 1");
		versionBox.addItem("SNMP 2c");
		versionBox.addItem("SNMP 3");
		JLabel lblSnmp = new JLabel("Версия SNMP");
		lblSnmp.setBounds(22, 103, 202, 14);
		
		JCheckBox chckbxTrap = new JCheckBox("Получить Trap уведомления");
		chckbxTrap.setBounds(22, 150, 231, 30);
		
		JButton btnSave = new JButton("Save");
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Unit unit = new Unit(nameField.getText(),ipField.getText(),portField.getText() ,"", chckbxTrap.isSelected());
				mainScreen.newUnit(unit);
				frmAdd.setVisible(false);
				frmAdd.dispose();
			}
		});
		btnSave.setBounds(22, 198, 96, 23);
		
		JButton btnCancel = new JButton("Cancel");
		btnCancel.setBounds(128, 198, 96, 23);
		frmAdd.getContentPane().setLayout(null);
		frmAdd.getContentPane().add(btnSave);
		frmAdd.getContentPane().add(btnCancel);
		frmAdd.getContentPane().add(label);
		frmAdd.getContentPane().add(lblIp);
		frmAdd.getContentPane().add(label_1);
		frmAdd.getContentPane().add(ipField);
		frmAdd.getContentPane().add(portField);
		frmAdd.getContentPane().add(lblSnmp);
		frmAdd.getContentPane().add(versionBox);
		frmAdd.getContentPane().add(chckbxTrap);
		frmAdd.getContentPane().add(nameField);
	}
}
