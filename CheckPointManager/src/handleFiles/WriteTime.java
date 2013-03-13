package handleFiles;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class WriteTime {
	private String arrival, departure, entrant, node;
	private boolean excluded, timeCheckpoint;
	private int nodeNum;
	private File timesFile;
	private FileWriter fw;
	private BufferedWriter bw;
	private String writeThis;
	private String[] courses;
	private String entrantCourse;
	private String times;
	private String[] myCourse;

	public WriteTime(String times, String[] strings) {
		courses = strings;
		this.times = times;
	}

	public void setData(List<String> entrant, String node,String arrival, String departure,
			 int exclude, int checkpoints) {
		this.node = node;
		this.arrival = arrival;
		this.departure = departure;
		if (exclude == 0) this.excluded = false;
		else this.excluded = true;
		if (checkpoints == 0) this.timeCheckpoint = true;
		else this.timeCheckpoint = false;
		try {
			this.entrant = entrant.get(0);
			checkData();
		} catch (IndexOutOfBoundsException e) {
			System.err.println("Error no entrant selected");
		}
	}

	private void checkData() {
		if (departure.equals("")) {
			if ((arrival.matches("\\d\\d:\\d\\d"))) {
				if (parseNode() != -1){
				writeToFile();
				}
			} else
				System.err.println("Error arrival time is not in correct format");
		} else {
			if ((arrival.matches("\\d\\d:\\d\\d"))
					&& (departure.matches("\\d\\d:\\d\\d"))) {
				if (parseNode() != -1)
				writeToFile();
			} else
				System.err.println("Error arrival or departure time is not in correct format");
		}
	}

	private int parseNode() {
		try {
		nodeNum = Integer.parseInt(node);
		return nodeNum;
		}
		catch (NumberFormatException e) {
			System.err.println("Error, node is not a number");
			return -1;
		}
	}
	
	private int parseEntrantNum() {
		Matcher matcher = Pattern.compile("\\d+").matcher(entrant);
		matcher.find();
		return Integer.valueOf(matcher.group());
	}
	
	private void formatInToString() {
		boolean found = false;
			Matcher matcher = Pattern.compile("[A-Z]+").matcher(entrant);
			matcher.find();
			entrantCourse = matcher.group();
			for (int i = 0; i<courses.length; i++) {
				for (int j =0; j<courses[i].length(); j++) {	
					if (String.valueOf(courses[i].charAt(j)).equals(entrantCourse)) {
						myCourse =courses[i].split("\\s+");
					}
				}
			}
			for (int i = 2; i<myCourse.length; i++) {
				if (Integer.parseInt(node) == Integer.parseInt(myCourse[i])) {
					found = true;
					break;
				}
			}
			if (found == false) {
				writeThis = "I "+nodeNum+" "+parseEntrantNum()+" "+arrival;;
			}
			else if ((found == true) && (timeCheckpoint == true)) {
				writeThis = "T "+nodeNum+" "+parseEntrantNum()+" "+arrival;
			}
			else if ((found == true) && (timeCheckpoint == false)) {
				if (excluded = true) {
					writeThis = "E "+nodeNum+" "+parseEntrantNum()+" "+arrival;
				}
				else {
					writeThis = "A "+nodeNum+" "+parseEntrantNum()+" "+arrival+
							"\n"+"D "+nodeNum+" "+parseEntrantNum()+" "+departure;
				}
			}
	}

	private void writeToFile() {
		formatInToString();
		try {
		timesFile = new File("src/handleFiles/"+times);
		fw = new FileWriter(timesFile.getAbsoluteFile(), true);
		bw = new BufferedWriter(fw);
			if (!timesFile.exists()) {
				timesFile.createNewFile();
			}
			
			bw.write(writeThis);
			bw.newLine();
			bw.close();

		} catch (IOException e) {
			e.printStackTrace();
		}

	}
}
