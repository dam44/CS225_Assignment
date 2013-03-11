package handleFiles;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class ReadEntrants {
	String[] entrants;

	public ReadEntrants(String[] fileNames) {
		readIn(fileNames);
	}

	public void readIn(String[] fileNames) {
		String thisLine;
		ArrayList<String> entrantsList = new ArrayList<String>();
		try {
			BufferedReader br = new BufferedReader(new FileReader(
					"src/handleFiles/" + fileNames[0]));
			while ((thisLine = br.readLine()) != null) {
				entrantsList.add(thisLine);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		entrants = entrantsList.toArray(new String[entrantsList.size()]);

	}

	public String[] getEntrants() {
		return entrants;
	}
}
