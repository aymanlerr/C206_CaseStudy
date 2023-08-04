public class Application {
	private int cca_ID;
	private String cca_name;
	private String timeslot;
	private int user_ID;
	private String username;
	private String status;
	
	public Application(int cca_ID, String cca_name, String timeslot, int user_ID, String username, String status) {
		super();
		this.cca_ID = cca_ID;
		this.cca_name = cca_name;
		this.timeslot = timeslot;
		this.user_ID = user_ID;
		this.username = username;
		this.status = "Pending";
	}

	public int getCca_ID() {
		return cca_ID;
	}

	public void setCca_ID(int cca_ID) {
		this.cca_ID = cca_ID;
	}

	public String getCca_name() {
		return cca_name;
	}

	public void setCca_name(String cca_name) {
		this.cca_name = cca_name;
	}

	public String getTimeslot() {
		return timeslot;
	}

	public void setTimeslot(String timeslot) {
		this.timeslot = timeslot;
	}

	public int getUser_ID() {
		return user_ID;
	}

	public void setUser_ID(int user_ID) {
		this.user_ID = user_ID;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
	public String display() {
		String output = "";
		output = String.format("%-10d %-10s %-20s %-10d %-10s %-10s\n", cca_ID, cca_name, timeslot, user_ID, username, status);
		return output;
	}
}
