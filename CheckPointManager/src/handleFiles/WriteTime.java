package handleFiles;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class WriteTime {
	private String arrival, departure, entrant, node;
	private boolean excluded, timeCheckpoint;
	private int nodeNum;
	private File timesFile;
	private FileWriter fw;
	private BufferedWriter bw;

	public WriteTime(String fileNames) {
		try {
			timesFile = new File("src/handleFiles/times.txt");
			fw = new FileWriter(timesFile.getAbsoluteFile());
			bw = new BufferedWriter(fw);

		} catch (IOException e) {
			e.printStackTrace();
		}
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
			System.err.println("Error no entrant file");
		}
		checkData();
	}

	public void checkData() {
		if (departure.equals("")) {
			if ((arrival.matches("\\d\\d:\\d\\d"))) {
				System.out.println("Worked");
				parseNode();
				writeToFile();
			} else
				System.err.println("Error arrival time is not in correct format");
		} else {
			if ((arrival.matches("\\d\\d:\\d\\d"))
					&& (departure.matches("\\d\\d:\\d\\d"))) {
				System.out.println("Worked");
				writeToFile();
			} else
				System.err.println("Error arrival or departure time is not in correct format");
		}
	}

	public int parseNode() {
		nodeNum = Integer.parseInt(node);
		return nodeNum;
	}
	
	public void formatInToString() {
		if (timeCheckpoint == true) {
			
		}
	}

	public void writeToFile() {
		try {
			if (!timesFile.exists()) {
				timesFile.createNewFile();
				System.out.println("Hello");
			}

		} catch (IOException e) {
			e.printStackTrace();
		}

	}
}
