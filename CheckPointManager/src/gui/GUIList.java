package gui;

import java.awt.Color;

import javax.swing.JList;

public class GUIList extends JList {


	public GUIList(String[] values) {
		super(values);
		this.setVisible(true);
		this.setSize(250, 500);
		this.setBackground(Color.GRAY);
	}
}
