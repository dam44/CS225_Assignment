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
import javax.swing.ListSelectionModel;

public class GUIPanel extends JPanel  {
	ReadEntrants readEntrants;
	ReadCourses readCourses;
	GUIList list;
	JComboBox<String> checkPoints;
	JComboBox<String> exclude;
	JTextField node;
	JTextField arrive;
	JTextField depart;
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
		eastNorthPanel.setLayout(new GridLayout(6,2));
		readEntrants = new ReadEntrants(fileNames[0]);
		readCourses = new ReadCourses(fileNames[2]);
		list = new GUIList(readEntrants.getEntrants());
	    list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		String[] checkPointTypes = { "Time", "Medical" };
		String[] excludeStrings = { "Not Excluded", "Excluded" };
		checkPoints = new JComboBox<String>(checkPointTypes);
		//checkPoints.addActionListener(actions);
		JLabel nodeLabel = new JLabel ("Select CheckPoint node: ");
		JLabel checkLabel = new JLabel("Select CheckPoint Type: ");
		JLabel arriveLabel = new JLabel("Input Arrival Time: ");
		JLabel departLabel = new JLabel("Input Departing Time: ");
		JLabel excludeLabel = new JLabel("Excluded?: ");
		JLabel submitLabel = new JLabel("Press ToSubmit: ");
		node = new JTextField("0");
		arrive = new JTextField("00:00");
		depart = new JTextField();
		depart.setText("");
		depart.setEditable(false);
		exclude = new JComboBox<String>(excludeStrings);
		submit = new JButton("Submit");
		
		this.add(eastPanel, BorderLayout.CENTER);
		eastPanel.add(eastNorthPanel);
		eastPanel.add(eastSouthPanel);
		this.add(list, BorderLayout.WEST);
		eastNorthPanel.add(nodeLabel);
		eastNorthPanel.add(node);
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
		
		times = new WriteTime(fileNames[1],readCourses.getCourses());
		actions = new Actions(getTimes(),getNode(), getList(), getCheckPoints(), getArrive(), getDepart(), getExclude());
		submit.addActionListener(actions);
		checkPoints.addActionListener(actions);
	}

	public JTextField getNode() {
		return node;
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
