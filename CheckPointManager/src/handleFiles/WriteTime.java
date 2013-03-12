package handleFiles;

import java.util.List;

public class WriteTime {
	private String arrival, departure, entrant;
	private boolean excluded, timeCheckpoint;
	public WriteTime(String fileNames) {
		
	}
	
	public void setData(List<String> entrant, String arrival, String departure, boolean excluded, boolean timeCheckpoint) {
		this.arrival = arrival;
		this.departure = departure;
		this.excluded = excluded;
		this.timeCheckpoint = timeCheckpoint;
		this.entrant = entrant.get(0);
	}
	public void checkData() {
		
	}
	
	public void writeToFile(){
		
	}
}
