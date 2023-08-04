public class CCA{
	private int cca_ID;
	private String cca_name;
	private String desc;
	private String timeslot;
	
	public CCA(int cca_ID, String cca_name, String desc, String timeslot) {
		this.cca_ID = cca_ID;
		this.cca_name = cca_name;
		this.desc = desc;
		this.timeslot = timeslot;
	}

	public int getCcaID() {
		return cca_ID;
	}

	public void setCcaID(int cca_ID) {
		this.cca_ID = cca_ID;
	}

	public String getCca_name() {
		return cca_name;
	}

	public void setCca_name(String cca_name) {
		this.cca_name = cca_name;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public String getTimeslot() {
		return timeslot;
	}

	public void setTimeslot(String timeslot) {
		this.timeslot = timeslot;
	}
	
	public String display() {
		String output = "";
		output += String.format("%-10d %-15s %-50s %-10s\n", cca_ID, cca_name, desc, timeslot);
		return output;
	}
}
