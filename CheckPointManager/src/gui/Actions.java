package gui;

import handleFiles.WriteTime;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JTextField;
/**
 * contains action listener methods for the gui.
 * @author Dan
 *
 */
public class Actions implements ActionListener {
	WriteTime times;
	GUIList list;
	JComboBox<String> checkPoints;
	JTextField arrive;
	JTextField depart;
	JComboBox<String> excluded;
	JButton submit;
	JTextField node;
	/**
	 * Actions class constructor, gui components are passed to class.
	 * 
	 */
	public Actions(WriteTime times, JTextField node, GUIList entrantList,
			JComboBox<String> checkPoints, JTextField arrive,
			JTextField depart, JComboBox<String> excluded) {
		this.times = times;
		this.list = entrantList;
		this.checkPoints = checkPoints;
		this.arrive = arrive;
		this.depart = depart;
		this.excluded = excluded;
		this.node = node;
	}

	/**
	 * Deals with component actions and passes them to the WriteTime class object.
	 */
	@Override
	public void actionPerformed(ActionEvent event) {
		//Handles the submit button actions.
		if (event.getActionCommand().equals("Submit")) {
			times.setData(list.getSelectedValuesList(), node.getText(),
					arrive.getText(), depart.getText(),
					excluded.getSelectedIndex(), checkPoints.getSelectedIndex());
		}

		else if (event.getSource() == checkPoints) {
			if (checkPoints.getSelectedIndex() == 1) {
				depart.setEditable(true);
				depart.setText("00:00");
			} else if (checkPoints.getSelectedIndex() == 0) {
				depart.setText("");
				depart.setEditable(false);
			}
		}

	}

}
