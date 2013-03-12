package gui;

import handleFiles.WriteTime;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JTextField;

public class Actions implements ActionListener {
	WriteTime times;
	GUIList list;
	JComboBox<String> checkPoints;
	JTextField arrive;
	JTextField depart;
	JComboBox<String> excluded;
	JButton submit;

	public Actions(WriteTime times, GUIList entrantList, JComboBox<String> checkPoints, JTextField arrive, JTextField depart, JComboBox<String> excluded) {
		this.times = times;
		this.list = entrantList; 
		this.checkPoints = checkPoints;
		this.arrive = arrive;
		this.depart =depart;
		this.excluded = excluded;
	}

	@Override
	public void actionPerformed(ActionEvent event) {
		if (event.getActionCommand().equals("Submit")) {
			if (excluded.getSelectedIndex()==0 && checkPoints.getSelectedIndex() == 0)
			times.setData(list.getSelectedValuesList(),arrive.getText(), depart.getText(), false, true);
			else if (excluded.getSelectedIndex()==1 && checkPoints.getSelectedIndex() == 0)
				times.setData(list.getSelectedValuesList(),arrive.getText(), depart.getText(), true, true);
			else if (excluded.getSelectedIndex()==1 && checkPoints.getSelectedIndex() == 1)
				times.setData(list.getSelectedValuesList(),arrive.getText(), depart.getText(), true,false);
			else if (excluded.getSelectedIndex()==0 && checkPoints.getSelectedIndex() == 1)
				times.setData(list.getSelectedValuesList(),arrive.getText(), depart.getText(), false, false);
			else System.err.println("Error");
		}

	}

}
