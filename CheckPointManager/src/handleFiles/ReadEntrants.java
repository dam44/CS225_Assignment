package handleFiles;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Reads the entrants file to retrieve the entrants.
 * @author Dan
 *
 */
public class ReadEntrants {
	String[] entrants;

	/**
	 * ReadEntrants constructor.
	 * @param entrantsFile
	 */
	public ReadEntrants(String entrantsFile) {
		readIn(entrantsFile);
	}

	/**
	 * Reads the entrants file in. Adds the entrants to array list.
	 * @param entrantsFile
	 */
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
