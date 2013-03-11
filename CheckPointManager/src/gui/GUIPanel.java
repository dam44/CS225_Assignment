package gui;

import handleFiles.*;

import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.JPanel;

public class GUIPanel extends JPanel {
	ReadEntrants readEntrants;
	GUIList list;
	public GUIPanel(String[] fileNames) {
		init(fileNames);
	}
	
	public void init(String[] fileNames) {
		this.setSize(500, 500);
		this.setVisible(true);
		this.setBackground(Color.WHITE);
		this.setLayout(new BorderLayout());
		readEntrants = new ReadEntrants(fileNames);
		list = new GUIList(readEntrants.getEntrants());
		this.add(list, BorderLayout.WEST);
		String[] checkPointTypes = { "Time", "Medical" };
		GUICheckPointsBox checkPoints = new GUICheckPointsBox(checkPointTypes);
		this.add(checkPoints, BorderLayout.NORTH);
	}
	
	
}
