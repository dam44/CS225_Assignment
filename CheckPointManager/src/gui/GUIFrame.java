package gui;

import javax.swing.JFrame;
/**
 * Extends JFrame, creates the gui panel.
 * @author Dan
 *
 */
public class GUIFrame extends JFrame {

	public GUIFrame(String[] fileNames) {
		this.setVisible(true);
		GUIPanel panel = new GUIPanel(fileNames);
		this.add(panel);
		this.setSize(450, 350);
	}

}
