package handleFiles;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class ReadEntrants {
	String[] entrants;

	public ReadEntrants(String entrantsFile) {
		readIn(entrantsFile);
	}

	public void readIn(String entrantsFile) {
		String thisLine;
		ArrayList<String> entrantsList = new ArrayList<String>();
		try {
			BufferedReader br = new BufferedReader(new FileReader(
					"src/handleFiles/" + entrantsFile));
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
