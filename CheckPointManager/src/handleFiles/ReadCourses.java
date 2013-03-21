package handleFiles;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
/**
 * Reads the courses file.
 * @author Dan
 *
 */
public class ReadCourses {

	String[] courses;
	/**
	 * ReadCourses file constructor.
	 * @param coursesFile
	 */
	public ReadCourses(String coursesFile) {
		readIn(coursesFile);
	}
	
	/**
	 * Reads in the courses file and adds them to the courses array list.
	 * @param coursesFile
	 */
	public void readIn(String coursesFile) {
		String thisLine;
		ArrayList<String> coursesList = new ArrayList<String>();
		try {
			BufferedReader br = new BufferedReader(new FileReader(
					"src/handleFiles/" + coursesFile));
			while ((thisLine = br.readLine()) != null) {
				coursesList.add(thisLine);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		courses = coursesList.toArray(new String[coursesList.size()]);

	}

	public String[] getCourses() {
		return courses;
	}
}


