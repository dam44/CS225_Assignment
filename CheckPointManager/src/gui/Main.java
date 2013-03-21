package gui;

public class Main {

	/**
	 * Main method, creates the GUIFrame.
	 * @param fileNames
	 */
	public static void main(String[] fileNames) {
		if (fileNames.length != 3) System.err.println("No files added, please add files.");
		else new GUIFrame(fileNames);

	}

}
