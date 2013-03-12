package handleFiles;

public class WriteTime {
	private String arrival, departure;
	private boolean excluded, timeCheckpoint;
	public WriteTime() {
		
	}
	
	public void setData(String arrival, String departure, boolean excluded, boolean timeCheckpoint) {
		this.arrival = arrival;
		this.departure = departure;
		this.excluded = excluded;
		this.timeCheckpoint = timeCheckpoint;
	}
}
