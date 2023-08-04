import java.util.ArrayList;

public class C206_CaseStudy {
	
	private static final int OPTION_QUIT = 99;

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		// setup ArrayList of users
		ArrayList<User> userList = new ArrayList<>();
		userList.add(new User(1, "Aiman", "1", "student"));
		userList.add(new User(2, "Aldusto", "2", "teacher"));
		userList.add(new User(3, "kedongdong", "3", "admin"));

		userList.add(new User(4, "Liam", "4", "student"));
		userList.add(new User(5, "Amran", "5", "student"));
		;

		// setup ArrayList of CCAs
		ArrayList<CCA> ccaList = new ArrayList<>();
		ccaList.add(new CCA(1, "Soccer", "Have fun playing soccer in this CCA!", "Wednesday: 4pm-6pm"));
		ccaList.add(new CCA(2, "Badminton", "Have fun playing badminton in this CCA!", "Wednesday: 4pm-6pm"));
		ccaList.add(new CCA(3, "Tennis", "Have fun playing tennis in this CCA!", "Friday: 4pm-6pm"));

		// setup ArrayList of attendance
		ArrayList<Attendance> attendanceList = new ArrayList<>();

		// setup ArrayList of CCA application
		ArrayList<Application> applicationList = new ArrayList<>();

		// ask user for login details
		Boolean start = false;
		int user_ID = 0;
		String password = "";
		String role = "";

		Helper.line(100, "-");
		System.out.println(String.format("%50s", "Login"));
		Helper.line(100, "-");

		while (start == false) {
			user_ID = Helper.readInt("Enter your user ID > ");
			password = Helper.readString("Enter your password > ");
			if (login(userList, user_ID, password) == true) {
				start = true;
			}
		}
		
		if (start == true) {
			int option = 0;
			while (option != OPTION_QUIT) {
				role = getRole(userList, user_ID, password);
				menu(role);
				option = Helper.readInt("\nEnter option > ");
				if (option == 1) {
					// AUTO-MENU
				}
			}
		}
	}
	
	// LOGIN
	private static boolean login(ArrayList<User> userList, int user_ID, String password) {
		// Check if user_id and password in list
		for (int i = 0; i < userList.size(); i++) {
			if (userList.get(i).getUserID() == user_ID) {
				if (userList.get(i).getPassword().equals(password)) {
					return true;
				}
			}
		}
		Helper.line(100, "-");
		System.out.println("Invalid User ID or Password");
		Helper.line(100, "-");
		return false;
	}
	
	// MENU
	private static void menu(String role) {
		Helper.line(100, "-");
		System.out.println(String.format("%50s", "MENU"));
		Helper.line(100, "-");
		String output = "";
		if (role.equalsIgnoreCase("student")) {
			output += "1. Menu";
			output += "\n2. View CCA";
			output += "\n3. Register CCA";
			output += "\n4. View application(s)";
			output += "\n8. Sign out";
			output += "\n99. Quit program";
		} else if (role.equalsIgnoreCase("teacher") || role.equalsIgnoreCase("advisor")) {
			output += "1. Menu";
			output += "\n2. View CCA";
			output += "\n3. Maintain CCA";
			output += "\n4. View applications";
			output += "\n5. Maintain application(s)";
			output += "\n6. View CCA attendance";
			output += "\n7. Maintain CCA attendance";
			output += "\n8. Sign out";
			output += "\n99. Quit program";
		} else if (role.equalsIgnoreCase("admin")) {
			output += "1. Menu";
			output += "\n2. View CCA";
			output += "\n3. Maintain CCA";
			output += "\n4. View applications";
			output += "\n5. Maintain application(s)";
			output += "\n6. View all user accounts";
			output += "\n7. Maintain user accounts";
			output += "\n8. Sign out";
			output += "\n99. Quit program";
		} else {
			Helper.line(100, "-");
			System.out.println("Error: Invalid role");
			Helper.line(100, "-");
		}
		System.out.println(output);
	}
	
	// GET USER ROLE
	private static String getRole(ArrayList<User> userList, int user_ID, String password) {
		String role = "";
		for (int i = 0; i < userList.size(); i++) {
			if (userList.get(i).getUserID() == user_ID && userList.get(i).getPassword().equals(password)) {
				role = userList.get(i).getRole();
			}
		}
		return role;
	}

}
