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

	public WriteTime(String fileNames) {
	}

	public void setData(List<String> entrant, String node,String arrival, String departure,
			 boolean excluded, boolean timeCheckpoint) {
		this.node = node;
		this.arrival = arrival;
		this.departure = departure;
		this.excluded = excluded;
		this.timeCheckpoint = timeCheckpoint;
		try {
			this.entrant = entrant.get(0);
		} catch (IndexOutOfBoundsException e) {
			System.err.println("Error no entrant selected");
		}
		checkData();
	}

	public void checkData() {
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
				if (parseNode() != 0)
				writeToFile();
			} else
				System.err.println("Error arrival or departure time is not in correct format");
		}
	}

	public int parseNode() {
		try {
		nodeNum = Integer.parseInt(node);
		return nodeNum;
		}
		catch (NumberFormatException e) {
			System.err.println("Error, node is not a number");
			return -1;
		}
	}
	
	public int parseEntrantNum() {
		Matcher matcher = Pattern.compile("\\d+").matcher(entrant);
		matcher.find();
		System.out.println(Integer.valueOf(matcher.group()));
		return Integer.valueOf(matcher.group());
	}
	
	public void formatInToString() {
		if (timeCheckpoint == true) {
			writeThis = "T "+nodeNum+" "+parseEntrantNum()+" "+arrival;
			System.out.println(writeThis);
		}
	}

	public void writeToFile() {
		formatInToString();
		try {
		timesFile = new File("src/handleFiles/times.txt");
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
