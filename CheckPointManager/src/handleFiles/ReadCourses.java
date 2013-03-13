package handleFiles;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class ReadCourses {

	String[] courses;
	public ReadCourses(String coursesFile) {
		readIn(coursesFile);
	}
	
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


