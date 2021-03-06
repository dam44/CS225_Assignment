package handleFiles;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.nio.channels.FileLock;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Write time class constructs times strings and writes them to file.
 * 
 * @author Dan
 * 
 */
public class WriteTime {
	private String arrival, departure, entrant, node;
	private boolean excluded, timeCheckpoint;
	private int nodeNum;
	private File timesFile;
	private FileWriter fw;
	private BufferedWriter bw;
	private FileLock fl;
	private FileOutputStream fos;
	private OutputStreamWriter writer;
	private String writeThis;
	private String[] courses;
	private String entrantCourse;
	private String times;
	private String[] myCourse;

	/**
	 * Constructor.
	 * 
	 * @param times
	 * @param strings
	 */
	public WriteTime(String times, String[] strings) {
		courses = strings;
		this.times = times;
	}

	/**
	 * Retrieves an formats data.
	 * 
	 * @param entrant
	 * @param node
	 * @param arrival
	 * @param departure
	 * @param exclude
	 * @param checkpoints
	 */
	public void setData(List<String> entrant, String node, String arrival,
			String departure, int exclude, int checkpoints) {
		this.node = node;
		this.arrival = arrival;
		this.departure = departure;
		if (exclude == 0)
			this.excluded = false;
		else
			this.excluded = true;
		if (checkpoints == 0)
			this.timeCheckpoint = true;
		else
			this.timeCheckpoint = false;
		try {
			this.entrant = entrant.get(0);
			checkData();
		} catch (IndexOutOfBoundsException e) {
			System.err.println("Error no entrant selected");
		}
	}

	/**
	 * Uses regex to check data is legal.
	 */
	private void checkData() {
		if (departure.equals("")) {
			if ((arrival.matches("\\d\\d:\\d\\d"))) {
				if (parseNode() != -1) {
					writeToFile();
				}
			} else
				System.err
						.println("Error arrival time is not in correct format");
		} else {
			if ((arrival.matches("\\d\\d:\\d\\d"))
					&& (departure.matches("\\d\\d:\\d\\d"))) {
				if (parseNode() != -1)
					writeToFile();
			} else
				System.err
						.println("Error arrival or departure time is not in correct format");
		}
	}

	/**
	 * Turns node into an integer.
	 * @return
	 */
	private int parseNode() {
		try {
			nodeNum = Integer.parseInt(node);
			return nodeNum;
		} catch (NumberFormatException e) {
			System.err.println("Error, node is not a number");
			return -1;
		}
	}
	/**
	 * Turns entrant number into an integer.
	 * @return
	 */
	private int parseEntrantNum() {
		Matcher matcher = Pattern.compile("\\d+").matcher(entrant);
		matcher.find();
		return Integer.valueOf(matcher.group());
	}

	/**
	 * Formats data into a string ready to be written to a file.
	 */
	private void formatInToString() {
		boolean found = false;
		Matcher matcher = Pattern.compile("[A-Z]+").matcher(entrant);
		matcher.find();
		entrantCourse = matcher.group();
		for (int i = 0; i < courses.length; i++) {
			for (int j = 0; j < courses[i].length(); j++) {
				if (String.valueOf(courses[i].charAt(j)).equals(entrantCourse)) {
					myCourse = courses[i].split("\\s+");
				}
			}
		}
		for (int i = 2; i < myCourse.length; i++) {
			if (Integer.parseInt(node) == Integer.parseInt(myCourse[i])) {
				found = true;
				break;
			}
		}
		if (found == false) {
			writeThis = "I " + nodeNum + " " + parseEntrantNum() + " "
					+ arrival;
			;
		} else if ((found == true) && (timeCheckpoint == true)) {
			writeThis = "T " + nodeNum + " " + parseEntrantNum() + " "
					+ arrival;
		} else if ((found == true) && (timeCheckpoint == false)) {
			if (excluded == true) {
				writeThis = "E " + nodeNum + " " + parseEntrantNum() + " "
						+ arrival;
			} else {
				writeThis = "A " + nodeNum + " " + parseEntrantNum() + " "
						+ arrival + System.getProperty("line.separator") + "D "
						+ nodeNum + " " + parseEntrantNum() + " " + departure;
			}
		}
	}

	/**
	 * Writes string to a file. Uses file locking to lock the file whilst data is being written to.
	 */
	private void writeToFile() {
		formatInToString();
		try {
			timesFile = new File("src/handleFiles/" + times);
			if (!timesFile.exists()) {
				timesFile.createNewFile();
			}
			fos = new FileOutputStream(timesFile, true);
			writer = new OutputStreamWriter(fos, "UTF-8");
			fl = fos.getChannel().tryLock();
			if (fl != null) {
				bw = new BufferedWriter(writer);
				bw.write(writeThis);
				bw.newLine();
				fl.release();
				bw.close();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
}
