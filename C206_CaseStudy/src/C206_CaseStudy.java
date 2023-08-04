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

	}

}
