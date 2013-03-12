package gui;

import handleFiles.*;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class GUIPanel extends JPanel  {
	ReadEntrants readEntrants;
	GUIList list;
	JComboBox<String> checkPoints;
	JTextField arrive;
	JTextField depart;
	JComboBox<String> exclude;
	JButton submit;
	WriteTime times;
	Actions actions;
	public GUIPanel(String[] fileNames) {
		init(fileNames);
	}
	
	public void init(String[] fileNames) {
		this.setSize(500, 500);
		this.setVisible(true);
		this.setBackground(Color.WHITE);
		this.setLayout(new BorderLayout());
		JPanel eastPanel = new JPanel();
		JPanel eastSouthPanel = new JPanel();
		JPanel eastNorthPanel = new JPanel();
		eastPanel.setLayout(new GridLayout(2,1));
		eastNorthPanel.setBackground(Color.WHITE);
		eastSouthPanel.setBackground(Color.WHITE);
		eastNorthPanel.setLayout(new GridLayout(5,2));
		readEntrants = new ReadEntrants(fileNames);
		list = new GUIList(readEntrants.getEntrants());
		String[] checkPointTypes = { "Time", "Medical" };
		String[] excludeStrings = { "Not Excluded", "Excluded" };
		checkPoints = new JComboBox<String>(checkPointTypes);
		//checkPoints.addActionListener(actions);
		JLabel checkLabel = new JLabel("Select CheckPoint Type: ");
		JLabel arriveLabel = new JLabel("Input Arrival Time: ");
		JLabel departLabel = new JLabel("Input Departing Time: ");
		JLabel excludeLabel = new JLabel("Excluded?: ");
		JLabel submitLabel = new JLabel("Press ToSubmit: ");
		arrive = new JTextField("00:00");
		depart = new JTextField("00:00");
		exclude = new JComboBox<String>(excludeStrings);
		submit = new JButton("Submit");
		
		this.add(eastPanel, BorderLayout.CENTER);
		eastPanel.add(eastNorthPanel);
		eastPanel.add(eastSouthPanel);
		this.add(list, BorderLayout.WEST);
		eastNorthPanel.add(checkLabel);
		eastNorthPanel.add(checkPoints);
		eastNorthPanel.add(excludeLabel);
		eastNorthPanel.add(exclude);
		eastNorthPanel.add(arriveLabel);
		eastNorthPanel.add(arrive);
		eastNorthPanel.add(departLabel);
		eastNorthPanel.add(depart);
		eastNorthPanel.add(submitLabel);
		eastNorthPanel.add(submit);
		
		times = new WriteTime();
		actions = new Actions(getTimes(), getList(), getCheckPoints(), getArrive(), getDepart(), getExclude());
//		checkPoints.addActionListener(this);
//		exclude.addActionListener(this);
		submit.addActionListener(actions);
	}

	public ReadEntrants getReadEntrants() {
		return readEntrants;
	}

	public GUIList getList() {
		return list;
	}

	public JComboBox<String> getCheckPoints() {
		return checkPoints;
	}

	public JTextField getArrive() {
		return arrive;
	}

	public JTextField getDepart() {
		return depart;
	}

	public JComboBox<String> getExclude() {
		return exclude;
	}

	public JButton getSubmit() {
		return submit;
	}

	public WriteTime getTimes() {
		return times;
	}

	public Actions getActions() {
		return actions;
	}
	
	
}
