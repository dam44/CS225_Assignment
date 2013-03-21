package gui;

import java.awt.Color;

import javax.swing.JList;
/**
 * Extends JList, sets the list for the GUI up.
 * @author Dan
 *
 */
public class GUIList extends JList<String> {
	public GUIList(String[] values) {
		super(values);
		this.setVisible(true);
		this.setSize(250, 500);
		this.setBackground(Color.GRAY);
	}
}
