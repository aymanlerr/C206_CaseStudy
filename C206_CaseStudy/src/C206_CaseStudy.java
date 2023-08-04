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
			role = getRole(userList, user_ID, password);
			menu(role);
			while (option != OPTION_QUIT) {
				option = Helper.readInt("\nEnter option > ");
				if (option == 1) {
					// AUTO-MENU
					menu(role);
				} else if (option == 2) {
					displayCCA(ccaList);
				} else if (option == 3) {
					if (role.equals("student")) {
						registerCCA(ccaList, userList, applicationList, user_ID);
					} else {
						maintainCCA(ccaList);
					}
				} else if (option == 4) {
					displayApplications(applicationList, user_ID, role);
				} else if (option == 5) {
					if (role != "student") {
						maintainApplication(applicationList, ccaList, userList, attendanceList, user_ID, role);
					} else {
						Helper.line(100, "-");
						System.out.println("Invalid option");
						Helper.line(100, "-");
					}
				} else if (option == OPTION_QUIT) {
					System.out.println("See you again!");
				} else {
					Helper.line(100, "-");
					System.out.println("Invalid option");
					Helper.line(100, "-");
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

	// DISPLAY CCA DETAILS
	private static void displayCCA(ArrayList<CCA> ccaList) {
		Helper.line(100, "-");
		System.out.println(String.format("%50s", "CCA"));
		Helper.line(100, "-");
		System.out.println(String.format("%-10s %-15s %-50s %-10s", "CCA ID", "CCA NAME", "DESCRIPTION", "TIMESLOT"));
		Helper.line(100, "-");
		String output = "";
		for (int i = 0; i < ccaList.size(); i++) {
			output += ccaList.get(i).display();
		}
		System.out.println(output);
	}
	
	// CHECK IF CCA ID IS CORRECT / EXIST
	private static boolean checkExistingCcaID(ArrayList<CCA> ccaList, int cca_ID) {
		for (int i = 0; i < ccaList.size(); i++) {
			if (cca_ID == ccaList.get(i).getCcaID()) {
				return true;
			}
		}
		return false;
	}
	
	// CHECK FOR EXISTING APPLICATION
	private static boolean checkExistingApplication(ArrayList<Application> applicationList, int user_ID, int cca_ID) {
		for (int i = 0; i < applicationList.size(); i++) {
			if (user_ID == applicationList.get(i).getUser_ID() && cca_ID == applicationList.get(i).getCca_ID()) {
				return true;
			}
		}
		return false;
	}
	
	// GET NUMBER OF APPLIED CCAs
	private static int getAppliedCCA(ArrayList<Application> applicationList, int user_ID) {
		int appliedCCAs = 0;
		for (int i = 0; i < applicationList.size(); i++) {
			if (user_ID == applicationList.get(i).getUser_ID()) {
				appliedCCAs++;
			}
		}
		return appliedCCAs;
	}
	
	// REGISTER FOR CCA
	private static void registerCCA(ArrayList<CCA> ccaList, ArrayList<User> userList,
			ArrayList<Application> applicationList, int user_ID) {
		displayCCA(ccaList);
		int cca_ID = Helper.readInt("Enter CCA ID that you want to join > ");

		// CHECK CCA ID IS CORRECT
		boolean checkCCAID = checkExistingCcaID(ccaList, cca_ID);

		// CHECK EXISTING APPLICATION
		boolean checkExistingApplication = checkExistingApplication(applicationList, user_ID, cca_ID);

		// CHECK NUMBER OF APPLICATION
		int appliedCCAs = getAppliedCCA(applicationList, user_ID);

		// ADD APPLICATION IF ALL CHECKING STAGES PASSED
		if (checkCCAID == false) {
			Helper.line(100, "-");
			System.out.println("Incorrect CCA ID");
			Helper.line(100, "-");
		} else if (checkExistingApplication == true) {
			Helper.line(100, "-");
			System.out.println("You have already applied for this CCA");
			Helper.line(100, "-");
		} else if (appliedCCAs == 2) {
			Helper.line(100, "-");
			System.out.println("You have applied for 2 CCas. Maximum application is 2.");
			Helper.line(100, "-");
		} else {
			// GET CCA NAME AND TIMESLOT
			String cca_name = "";
			String timeslot = "";
			for (int i = 0; i < ccaList.size(); i++) {
				if (cca_ID == ccaList.get(i).getCcaID()) {
					cca_name = ccaList.get(i).getCca_name();
					timeslot = ccaList.get(i).getTimeslot();
				}
			}

			// ADD INTO APPLICATION LIST
			for (int i = 0; i < userList.size(); i++) {
				if (user_ID == userList.get(i).getUserID()) {
					applicationList.add(new Application(cca_ID, cca_name, timeslot, userList.get(i).getUserID(),
							userList.get(i).getName(), ""));
					Helper.line(100, "-");
					System.out.println("You have succesfully registered for this cca");
					Helper.line(100, "-");
				}
			}
		}
	}
	
	// CHECK IF TIMESLOT EXIST WITH SAME CCA NAME
	private static boolean checkExistingTimeslotAndName(ArrayList<CCA> ccaList, String timeslot, String cca_name) {
		for (int i = 0; i < ccaList.size(); i++) {
			if (timeslot.equalsIgnoreCase(ccaList.get(i).getTimeslot())
					&& cca_name.equalsIgnoreCase(ccaList.get(i).getCca_name())) {
				return true;
			}
		}
		return false;
	}
	
	// CHECK IF CCA NAME IS CORRECT
	private static boolean checkExistingCcaName(ArrayList<CCA> ccaList, String cca_name) {
		for (int i = 0; i < ccaList.size(); i++) {
			if (cca_name.equals(ccaList.get(i).getCca_name())) {
				return true;
			}
		}
		return false;
	}

	
	// MAINTAIN CCA
	private static void maintainCCA(ArrayList<CCA> ccaList) {
		displayCCA(ccaList);
		Helper.line(100, "-");
		System.out.println("1. Add CCA\n2. Delete CCA\n3. Edit CCA\n4. Back");
		Helper.line(100, "-");
		int option = Helper.readInt("\nEnter your option > ");

		if (option == 1) {

			int cca_ID = 1;
			boolean getId = false;
			ArrayList<Integer> ccaIds = new ArrayList<>();
			for (int i = 0; i < ccaList.size(); i++) {
				ccaIds.add(ccaList.get(i).getCcaID());
			}

			while (getId == false) {
				if (ccaIds.contains(cca_ID)) {
					cca_ID++;
				} else {
					getId = true;
				}
			}

			String cca_name = Helper.readString("Enter new CCA name > ");
			String desc = Helper.readString("Enter new CCA description > ");
			String timeslot = Helper
					.readString("Enter new timeslot    Format=> day[spelled out]: [#][pm/am]-[#][pm/am] > ");

			// CHECK IF NEW CCA ID IS VALID
			boolean checkExistingCcaID = checkExistingCcaID(ccaList, cca_ID);

			// CHECK IF NEW TIMESLOT IS VALID
			boolean checkExistingTimeslot = checkExistingTimeslotAndName(ccaList, timeslot, cca_name);

			// ADD IF ALL CHECKS PASSED
			if (checkExistingCcaID == true) {
				Helper.line(100, "-");
				System.out.println("CCA ID already exist. Please use another ID.");
				Helper.line(100, "-");
			} else if (checkExistingTimeslot == true) {
				Helper.line(100, "-");
				System.out.println("Timeslot for the CCA already exist. Please enter another timeslot");
				Helper.line(100, "-");
			} else {
				ccaList.add(new CCA(cca_ID, cca_name, desc, timeslot));
				Helper.line(100, "-");
				System.out.println("CCA successfully added");
				Helper.line(100, "-");
			}
		} else if (option == 2) {
			int cca_ID = Helper.readInt("Enter CCA ID to delete > ");
			String cca_name = Helper.readString("Enter CCA name to confirm > ");

			// CHECK IF CCA ID EXIST
			boolean checkExistingCcaID = checkExistingCcaID(ccaList, cca_ID);

			// CHECK IF CCA NAME IS CORRECT
			boolean checkExistingCcaName = checkExistingCcaName(ccaList, cca_name);

			// DELETE CCA IF ALL CHECKS PASSED
			if (checkExistingCcaID == false) {
				Helper.line(100, "-");
				System.out.println("Invalid CCA ID");
				Helper.line(100, "-");
			} else if (checkExistingCcaName == false) {
				Helper.line(100, "-");
				System.out.println("Invalid CCA name");
				Helper.line(100, "-");
			} else {
				for (int i = 0; i < ccaList.size(); i++) {
					if (cca_ID == ccaList.get(i).getCcaID() && cca_name.equals(ccaList.get(i).getCca_name())) {
						ccaList.remove(i);
						Helper.line(100, "-");
						System.out.println("CCA successfully removed");
						Helper.line(100, "-");
					}
				}
			}

		} else if (option == 3) {
			int cca_ID = Helper.readInt("Enter CCA ID to edit > ");

			// CHECK IF CCA ID EXIST
			boolean checkExistingCcaID = checkExistingCcaID(ccaList, cca_ID);

			// GET CCA POSITION
			int position = 0;
			if (checkExistingCcaID == false) {
				Helper.line(100, "-");
				System.out.println("Invalid CCA ID");
				Helper.line(100, "-");
			} else {
				for (int i = 0; i < ccaList.size(); i++) {
					if (cca_ID == ccaList.get(i).getCcaID()) {
						position = i;
					}
				}
			}

			// EDIT
			System.out.println("1. CCA Name\n2. CCA Description\n3. Timeslot\n4. Back");
			int choice = Helper.readInt("\nEnter option to edit > ");
			// CCA NAME
			if (choice == 1) {
				String newCcaName = Helper.readString("Enter new CCA Name > ");

				// CHECK TIMESLOT
				boolean checkExistingTimeslot = checkExistingTimeslotAndName(ccaList,
						ccaList.get(position).getTimeslot(), newCcaName);

				// UPDATE IF CHECKS PASSED
				if (checkExistingTimeslot == true) {
					Helper.line(100, "-");
					System.out.println("CCA name with same timeslot already exist");
					Helper.line(100, "-");
				} else {
					ccaList.get(position).setCca_name(newCcaName);
					Helper.line(100, "-");
					System.out.println("CCA name successfully updated");
					Helper.line(100, "-");
				}

				// CCA DESC
			} else if (choice == 2) {
				String newDesc = Helper.readString("Enter new CCA description > ");
				ccaList.get(position).setDesc(newDesc);

				// CCA TIMESLOT
			} else if (choice == 3) {
				String newCcaTimeslot = Helper
						.readString("Enter new timeslot    Format=> day[spelled out]: [#][pm/am]-[#][pm/am] > ");

				// CHECK TIMESLOT
				boolean checkExistingTimeslot = checkExistingTimeslotAndName(ccaList, newCcaTimeslot,
						ccaList.get(position).getCca_name());

				// UPDATE IF CHECKS PASSED
				if (checkExistingTimeslot == true) {
					Helper.line(100, "-");
					System.out.println("CCA name with same timeslot already exist");
					Helper.line(100, "-");
				} else {
					ccaList.get(position).setTimeslot(newCcaTimeslot);
					Helper.line(100, "-");
					System.out.println("CCA name successfully updated");
					Helper.line(100, "-");
				}

				// BACK
			} else if (choice == 4) {

			}

		} else if (option == 4) {

		} else {
			Helper.line(100, "-");
			System.out.println("Invalid option");
			Helper.line(100, "-");
		}
	}
	
	// DISPLAY APPLICATION APPROVAL STATUS
	private static void displayApplications(ArrayList<Application> applicationList, int user_ID, String role) {
		Helper.line(100, "-");
		System.out.println(String.format("%60s", "VIEW CCA APPROVAL STATUS"));
		Helper.line(100, "-");
		System.out.println(String.format("%-10s %-10s %-20s %-10s %-10s %-10s", "CCA ID", "CCA NAME", "TIMESLOT",
				"USER_ID", "USERNAME", "STATUS"));
		Helper.line(100, "-");
		String output = "";
		if (role == "student") {
			for (int i = 0; i < applicationList.size(); i++) {
				if (user_ID == applicationList.get(i).getUser_ID()) {
					output += applicationList.get(i).display();
				}
			}
		} else {
			for (int i = 0; i < applicationList.size(); i++) {
				output += applicationList.get(i).display();
			}
		}
		System.out.println(output);
	}
	
	// CHECK USER ID
	private static boolean checkExistingUserID(ArrayList<User> userList, int user_ID) {
		for (int i = 0; i < userList.size(); i++) {
			if (user_ID == userList.get(i).getUserID()) {
				return true;
			}
		}
		return false;
	}
	
	// MAINTAIN APPLICATION
	private static void maintainApplication(ArrayList<Application> applicationList, ArrayList<CCA> ccaList,
			ArrayList<User> userList, ArrayList<Attendance> attendanceList, int user_ID, String role) {
		displayApplications(applicationList, user_ID, role);
		Helper.line(100, "-");
		System.out.println("1. Delete Application\n2. Set Application Approval\n3. Back");
		Helper.line(100, "-");
		int option = Helper.readInt("\nEnter your option > ");

		if (option == 1) {
			// DELETE APPLICATION
			int ccaID = Helper.readInt("Enter application CCA ID > ");
			int userID = Helper.readInt("Enter application user ID > ");

			// CHECK INPUT FIELDS
			boolean checkCcaId = checkExistingCcaID(ccaList, ccaID);
			boolean checkUserId = checkExistingUserID(userList, userID);

			// DELETE IF ALL CHECK PASSED
			if (checkCcaId == false) {
				Helper.line(100, "-");
				System.out.println("Invalid CCA ID");
				Helper.line(100, "-");
			} else if (checkUserId == false) {
				Helper.line(100, "-");
				System.out.println("Invalid User ID");
				Helper.line(100, "-");
			} else {
				for (int i = 0; i < applicationList.size(); i++) {
					if (ccaID == applicationList.get(i).getCca_ID() && userID == applicationList.get(i).getUser_ID()) {
						applicationList.remove(i);
						Helper.line(100, "-");
						System.out.println("Application succesfully deleted");
						Helper.line(100, "-");
					}
				}
			}

		} else if (option == 2) {
			// APPROVE OR REJECT APPLICATION
			int ccaID = Helper.readInt("Enter application CCA ID > ");
			int userID = Helper.readInt("Enter application user ID > ");
			int approve = Helper.readInt("Enter 0 to approve and 1 to reject > ");

			// CHECK INPUT FIELDS
			boolean checkCcaId = checkExistingCcaID(ccaList, ccaID);
			boolean checkUserId = checkExistingUserID(userList, userID);

			// UPDATE APPLICATION STATUS IF CHECKING FIELDS PASS
			if (checkCcaId == false) {
				Helper.line(100, "-");
				System.out.println("Invalid CCA ID");
				Helper.line(100, "-");
			} else if (checkUserId == false) {
				Helper.line(100, "-");
				System.out.println("Invalid User ID");
				Helper.line(100, "-");
			} else {
				if (approve == 0) {
					String cca_name = "";
					String timeslot = "";
					String username = "";
					int pos = 0;

					for (int i = 0; i < ccaList.size(); i++) {
						if (ccaID == ccaList.get(i).getCcaID()) {
							cca_name = ccaList.get(i).getCca_name();
							timeslot = ccaList.get(i).getTimeslot();
						}
					}

					for (int i = 0; i < userList.size(); i++) {
						if (userID == userList.get(i).getUserID()) {
							username = userList.get(i).getName();
							pos = i;
						}
					}

					for (int i = 0; i < applicationList.size(); i++) {
						if (ccaID == applicationList.get(i).getCca_ID()
								&& userID == applicationList.get(i).getUser_ID()) {
							applicationList.get(i).setStatus("Approved");
							attendanceList.add(new Attendance(ccaID, cca_name, timeslot, userID, username, ""));
							userList.get(pos).setCca_ID(ccaID);
							Helper.line(100, "-");
							System.out.println("Application succesfully approved");
							Helper.line(100, "-");
						}
					}
				} else if (approve == 1) {
					for (int i = 0; i < applicationList.size(); i++) {
						if (ccaID == applicationList.get(i).getCca_ID()
								&& userID == applicationList.get(i).getUser_ID()) {
							applicationList.get(i).setStatus("Rejected");
							Helper.line(100, "-");
							System.out.println("Application succesfully rejected");
							Helper.line(100, "-");
						}
					}
				} else {
					Helper.line(100, "-");
					System.out.println("Invalid option");
					Helper.line(100, "-");
				}
			}

		} else if (option == 3) {
			// BACK
		}
	}

}
