package gui;

public class Main {

	/**
	 * 
	 * @param fileNames
	 */
	public static void main(String[] fileNames) {
		if (fileNames.length != 2) System.err.println("No files added, please add files.");
		else new GUIFrame(fileNames);

	}

}
