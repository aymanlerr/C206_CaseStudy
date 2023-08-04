import java.util.ArrayList;

public class C206_CaseStudy {

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

}
