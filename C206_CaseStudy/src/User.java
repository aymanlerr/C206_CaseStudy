public class User{
	private int user_ID;
	private String username;
	private String password;
	private String role;
	private int cca_ID;
	
	public User(int user_ID, String username, String password, String role) {
		this.user_ID = user_ID;
		this.username = username;
		this.password = password;
		this.role = role;
		this.cca_ID = -1;
	}
	
	public User(int user_ID, String username, String password, String role, int cca_ID) {
		this.user_ID = user_ID;
		this.username = username;
		this.password = password;
		this.role = role;
		this.cca_ID = cca_ID;
	}

	public int getUserID() {
		return user_ID;
	}

	public void setUserID(int user_ID) {
		this.user_ID = user_ID;
	}

	public String getName() {
		return username;
	}

	public void setName(String name) {
		this.username = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}
	
	public int getCca_ID() {
		return cca_ID;
	}

	public void setCca_ID(int cca_ID) {
		this.cca_ID = cca_ID;
	}

	public String display() {
		String output = "";
		if (cca_ID == -1) {
			output += String.format("%-10d %-10s %-10s %-10s\n", user_ID, username, password, role);	
		} else {
			output += String.format("%-10d %-10s %-10s %-10s %-10d\n", user_ID, username, password, role, cca_ID);
		}
		return output;
	}
}
