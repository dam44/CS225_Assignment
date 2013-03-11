package gui;

import java.awt.event.ActionEvent;

import javax.swing.JComboBox;

public class GUICheckPointsBox extends JComboBox {
	public GUICheckPointsBox(String[] values) {
		super(values);
		this.setSelectedIndex(0);
		this.addActionListener(this);
	}
	public void actionPerformed(ActionEvent e){
		JComboBox cb = (JComboBox)e.getSource();
		//updateLabel((String)cb.getSelectedItem());
	}
	
}
