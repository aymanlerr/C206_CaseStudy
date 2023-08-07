import java.util.ArrayList;

public class C206_CaseStudy {

	private static final int OPTION_RELOGIN = 8;
	private static final int OPTION_MAINTAIN_ATTENDANCE_OR_USER = 7;
	private static final int OPTION_DISPLAY_ATTENDANCE = 6;
	private static final int OPTION_MAINTAIN_APPLICATION = 5;
	private static final int OPTION_DISPLAY_APPLICATION = 4;
	private static final int OPTION_REGISTER_OR_MAINTAIN_CCA = 3;
	private static final int OPTION_DISPLAY_CCA = 2;
	private static final int OPTION_MENU = 1;
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

				if (option == OPTION_MENU) {
					// AUTO-MENU
					
				} else if (option == OPTION_DISPLAY_CCA) {
					displayCCA(ccaList);

				} else if (option == OPTION_REGISTER_OR_MAINTAIN_CCA) {
					if (role.equals("student")) {
						applyCCA(userList, ccaList, applicationList, user_ID);
						
					} else {
						maintainCCA(ccaList);
						
					}

				} else if (option == OPTION_DISPLAY_APPLICATION) {
					displayApplications(applicationList, user_ID, role);

				} else if (option == OPTION_MAINTAIN_APPLICATION) {

					if (role != "student") {
						maintainApplication(userList, ccaList, attendanceList, applicationList, user_ID, role);

					} else {
						Helper.line(100, "-");
						System.out.println("Invalid option");
						Helper.line(100, "-");
						
					}
				} else if (option == OPTION_DISPLAY_ATTENDANCE) {
					
					if (role == "teacher" || role == "advisor") {
						displayAttendance(attendanceList, ccaList);

					} else if (role == "admin") {
						displayUsers(userList);
						
					} else {
						Helper.line(100, "-");
						System.out.println("Invalid option");
						Helper.line(100, "-");
						
					}
				} else if (option == OPTION_MAINTAIN_ATTENDANCE_OR_USER) {
					if (role == "teacher" || role == "advisor") {
						maintainAttendance(attendanceList, ccaList, userList);
						
					} else if (role == "admin") {
						maintainUser(userList, ccaList, role);

					} else {
						Helper.line(100, "-");
						System.out.println("Invalid option");
						Helper.line(100, "-");
						
					}
				} else if (option == OPTION_RELOGIN) {
					Helper.line(100, "-");
					System.out.println(String.format("%50s", "Login"));
					Helper.line(100, "-");
					user_ID = 0;
					password = "";
					role = "";
					start = false;
					while (start == false) {
						user_ID = Helper.readInt("Enter your user ID > ");
						password = Helper.readString("Enter your password > ");
						if (login(userList, user_ID, password) == true) {
							start = true;
						}
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

	private static void maintainUser(ArrayList<User> userList, ArrayList<CCA> ccaList, String role) {
		// MAINTAIN USER
		displayUsers(userList);
		Helper.line(100, "-");
		System.out.println("1. Add User\n2. Delete User\n3. Edit User\n4. Back");
		Helper.line(100, "-");
		int choice = Helper.readInt("\nEnter your option > ");

		if (choice == 1) {

			int newUserId = Helper.readInt("Enter new User ID > ");
			String newUsername = Helper.readString("Enter new User name > ");
			String newPassword = Helper.readString("Enter new password > ");
			String newRole = Helper.readString("Enter new role [student/teacher/advisor/admin] > ");

			// CHECK IF NEW USER ID ALREADRY EXIST
			boolean checkExistingUserId = checkExistingUserID(userList, newUserId);

			boolean checkRole = false;
			if (role.equals("student") || role.equals("teacher") || role.equals("advisor")
					|| role.equals("admin")) {
				checkRole = true;
			}

			// ADD IF ALL CHECKS PASSED
			if (checkExistingUserId == true) {
				Helper.line(100, "-");
				System.out.println("User ID already exist. Please use another ID.");
				Helper.line(100, "-");
			} else if (checkRole == false) {
				Helper.line(100, "-");
				System.out.println("Incorrect role.");
				Helper.line(100, "-");
			} else {
				addUser(userList, newUserId, newUsername, newPassword, newRole);
			}

		} else if (choice == 2) {

			int userId = Helper.readInt("Enter User ID to delete > ");
			String username = Helper.readString("Enter username to confirm > ");

			// CHECK IF USER ID EXIST
			boolean checkExistingUserId = checkExistingUserID(userList, userId);

			// CHECK IF CCA NAME IS CORRECT
			boolean checkExistingUsername = false;
			for (int i = 0; i < userList.size(); i++) {
				if (username.equalsIgnoreCase(userList.get(i).getName())) {
					checkExistingUsername = true;
				}
			}

			// DELETE CCA IF ALL CHECKS PASSED
			if (checkExistingUserId == false) {
				Helper.line(100, "-");
				System.out.println("Invalid User ID");
				Helper.line(100, "-");
			} else if (checkExistingUsername == false) {
				Helper.line(100, "-");
				System.out.println("Invalid Username");
				Helper.line(100, "-");
			} else {
				deleteUser(userList, userId, username);
			}

		} else if (choice == 3) {

			int userId = Helper.readInt("Enter User ID to edit > ");
			// CHECK IF CCA ID EXIST
			boolean checkExistingUserId = checkExistingUserID(userList, userId);

			// GET CCA POSITION
			int position = 0;
			if (checkExistingUserId == false) {
				Helper.line(100, "-");
				System.out.println("Invalid User ID");
				Helper.line(100, "-");
			} else {
				for (int i = 0; i < userList.size(); i++) {
					if (userId == userList.get(i).getUserID()) {
						position = i;
					}
				}
				System.out.println("1. User ID\n2. Username\n3. password\n4. Role\n5. CCA ID\n6. Back");
				choice = Helper.readInt("\nEnter option to edit > ");
				editUser(userList, ccaList, userId, position, choice);
			}

		} else if (choice == 4) {

		} else {
			Helper.line(100, "-");
			System.out.println("Invalid option");
			Helper.line(100, "-");
		}
	}

	private static void maintainApplication(ArrayList<User> userList, ArrayList<CCA> ccaList,
			ArrayList<Attendance> attendanceList, ArrayList<Application> applicationList, int user_ID, String role) {
		if (displayApplications(applicationList, user_ID, role) == true) {
			Helper.line(100, "-");
			System.out.println("1. Delete Application\n2. Set Application Approval\n3. Back");
			Helper.line(100, "-");
			int choice = Helper.readInt("\nEnter your option > ");

			if (choice == 1) {
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
					deleteApplication(applicationList, ccaID, userID);
				}
			} else if (choice == 2) {
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
					editApplication(applicationList, ccaList, userList, attendanceList, approve, ccaID,
							userID);
				}

			} else if (choice == 3) {
				// BACK
			} else {
				Helper.line(100, "-");
				System.out.println("Invalid option");
				Helper.line(100, "-");
			}
		}
	}

	private static void maintainCCA(ArrayList<CCA> ccaList) {
		displayCCA(ccaList);
		Helper.line(100, "-");
		System.out.println("1. Add CCA\n2. Delete CCA\n3. Edit CCA\n4. Back");
		Helper.line(100, "-");
		int choice = Helper.readInt("\nEnter your option > ");

		if (choice == 1) {

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
			String timeslot = Helper.readString(
					"Enter new timeslot    Format=> day[spelled out]: [#][pm/am]-[#][pm/am] > ");

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
				addCCA(ccaList, cca_ID, cca_name, desc, timeslot);
			}
		} else if (choice == 2) {
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
				deleteCCA(ccaList, cca_ID, cca_name);
			}
		} else if (choice == 3) {
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
			int edit = Helper.readInt("\nEnter option to edit > ");
			editCCA(ccaList, edit, position);
			
		} else if (choice == 4) {
			// BACK
		} else {
			Helper.line(100, "-");
			System.out.println("Invalid option");
			Helper.line(100, "-");
		}
	}

	private static void applyCCA(ArrayList<User> userList, ArrayList<CCA> ccaList,
			ArrayList<Application> applicationList, int user_ID) {
		displayCCA(ccaList);
		int ccaId = Helper.readInt("Enter CCA ID that you want to join > ");

		// CHECK CCA ID IS CORRECT
		boolean checkCCAID = checkExistingCcaID(ccaList, ccaId);

		// CHECK EXISTING APPLICATION
		boolean checkExistingApplication = checkExistingApplication(applicationList, user_ID, ccaId);

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
			registerCCA(ccaList, userList, applicationList, user_ID, ccaId);
		}
	}

	public static void addCCA(ArrayList<CCA> ccaList, int cca_ID, String cca_name, String desc, String timeslot) {
		ccaList.add(new CCA(cca_ID, cca_name, desc, timeslot));
		Helper.line(100, "-");
		System.out.println("CCA successfully added");
		Helper.line(100, "-");
	}

	// LOGIN
	public static boolean login(ArrayList<User> userList, int user_ID, String password) {
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
	public static void menu(String role) {
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

	public static void editUser(ArrayList<User> userList, ArrayList<CCA> ccaList, int user_Id, int position,
			int choice) {
		// CCA NAME
		if (choice == 1) {
			int newUserId = Helper.readInt("Enter new User ID > ");

			// CHECK USER ID
			boolean checkId = checkExistingUserID(userList, user_Id);

			// UPDATE IF CHECKS PASSED
			if (checkId == true) {
				Helper.line(100, "-");
				System.out.println("User ID already exist");
				Helper.line(100, "-");
			} else {
				userList.get(position).setUserID(newUserId);
				Helper.line(100, "-");
				System.out.println("User ID successfully updated");
				Helper.line(100, "-");
			}

			// USERNAME
		} else if (choice == 2) {
			String newUsername = Helper.readString("Enter new Username > ");
			userList.get(position).setName(newUsername);

			// PASSWORD
		} else if (choice == 3) {
			String newPassword = Helper.readString("Enter new Password > ");
			userList.get(position).setPassword(newPassword);

			// ROLE
		} else if (choice == 4) {
			String newRole = "";
			int roleInput = Helper.readInt("Enter new Role [0: Student, 1: Teacher, 2: Advisor, 3: Admin] > ");
			boolean correctRole = true;
			if (roleInput == 0) {
				newRole = "student";
			} else if (roleInput == 1) {
				newRole = "teacher";
			} else if (roleInput == 2) {
				newRole = "advisor";
			} else if (roleInput == 3) {
				newRole = "admin";
			} else {
				correctRole = false;
			}

			if (correctRole == false) {
				Helper.line(100, "-");
				System.out.println("Invalid role");
				Helper.line(100, "-");
			} else {
				userList.get(position).setRole(newRole);
				Helper.line(100, "-");
				System.out.println("Role successfully updated");
				Helper.line(100, "-");
			}

		} // CCA ID
		else if (choice == 5) {
			displayCCA(ccaList);
			int newCcaId = Helper.readInt("Enter new CCA ID > ");

			// CHECK
			boolean checkNewCcaId = checkExistingCcaID(ccaList, newCcaId);

			// UPDATE IF PASSED
			if (checkNewCcaId == false) {
				Helper.line(100, "-");
				System.out.println("Invalid CCA ID");
				Helper.line(100, "-");
			} else {
				userList.get(position).setCca_ID(newCcaId);
				Helper.line(100, "-");
				System.out.println("CCA ID successfully updated");
				Helper.line(100, "-");
			}

		} else if (choice == 6) {
			// BACK
		} else {
			Helper.line(100, "-");
			System.out.println("Invalid option");
			Helper.line(100, "-");
		}
	}

	public static void deleteUser(ArrayList<User> userList, int user_Id, String username) {
		for (int i = 0; i < userList.size(); i++) {
			if (user_Id == userList.get(i).getUserID() && username.equals(userList.get(i).getName())) {
				userList.remove(i);
				Helper.line(100, "-");
				System.out.println("User successfully removed");
				Helper.line(100, "-");
			}
		}
	}

	public static void addUser(ArrayList<User> userList, int user_Id, String username, String password, String role) {
		userList.add(new User(user_Id, username, password, role));
		Helper.line(100, "-");
		System.out.println("User successfully added");
		Helper.line(100, "-");
	}

	public static void maintainAttendance(ArrayList<Attendance> attendanceList, ArrayList<CCA> ccaList,
			ArrayList<User> userList) {
		if (displayAttendance(attendanceList, ccaList) == true) {
			Helper.line(100, "-");
			System.out.println("1. Delete Attendance\n2. Set Attendance\n3. Back");
			Helper.line(100, "-");
			int option = Helper.readInt("\nEnter your option > ");
			int ccaID;
			int userID;
			// DELETE
			if (option == 1) {
				// DELETE APPLICATION

				ccaID = Helper.readInt("Enter application CCA ID > ");
				userID = Helper.readInt("Enter application user ID > ");

				// CHECK INPUT FIELDS
				boolean checkCcaId = false;
				boolean checkUserId = false;

				for (int i = 0; i < attendanceList.size(); i++) {
					if (ccaID == attendanceList.get(i).getCca_ID()) {
						checkCcaId = true;
					}
					if (userID == attendanceList.get(i).getUser_ID()) {
						checkUserId = true;
					}
				}

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
					deleteAttendance(attendanceList, ccaID, userID);
				}
				// SET ATTENDANCE
			} else if (option == 2) {
				// APPROVE OR REJECT APPLICATION
				ccaID = Helper.readInt("Enter attendance CCA ID > ");
				userID = Helper.readInt("Enter attendance user ID > ");
				int approve = Helper.readInt("Enter 0: Present, 1: Absent, 2: Late > ");

				// CHECK INPUT FIELDS
				boolean checkCcaId = false;
				boolean checkUserId = false;

				for (int i = 0; i < attendanceList.size(); i++) {
					if (ccaID == attendanceList.get(i).getCca_ID()) {
						checkCcaId = true;
					}
					if (userID == attendanceList.get(i).getUser_ID()) {
						checkUserId = true;
					}
				}

				String status = "Pending";
				boolean checkApprove = true;
				if (approve == 0) {
					status = "Present";
				} else if (approve == 1) {
					status = "Absent";
				} else if (approve == 2) {
					status = "Late";
				} else {
					checkApprove = false;
				}

				// UPDATE APPLICATION STATUS IF CHECKING FIELDS PASS
				if (checkCcaId == false) {
					Helper.line(100, "-");
					System.out.println("Invalid CCA ID");
					Helper.line(100, "-");
				} else if (checkUserId == false) {
					Helper.line(100, "-");
					System.out.println("Invalid User ID");
					Helper.line(100, "-");
				} else if (checkApprove == false) {
					Helper.line(100, "-");
					System.out.println("Invalid option");
					Helper.line(100, "-");
				} else {
					editAttendance(attendanceList, ccaID, userID, status);
				}
			} else if (option == 3) {

			}
		}

	}

	private static void editAttendance(ArrayList<Attendance> attendanceList, int ccaID, int userID, String status) {
		for (int i = 0; i < attendanceList.size(); i++) {
			if (ccaID == attendanceList.get(i).getCca_ID()
					&& userID == attendanceList.get(i).getUser_ID()) {
				attendanceList.get(i).setStatus(status);
				Helper.line(100, "-");
				System.out.println("Attendance succesfully updated");
				Helper.line(100, "-");
			}
		}
	}

	private static void deleteAttendance(ArrayList<Attendance> attendanceList, int ccaID, int userID) {
		for (int i = 0; i < attendanceList.size(); i++) {
			if (ccaID == attendanceList.get(i).getCca_ID()
					&& userID == attendanceList.get(i).getUser_ID()) {
				attendanceList.remove(i);
				Helper.line(100, "-");
				System.out.println("Application succesfully deleted");
				Helper.line(100, "-");
			}
		}
	}

	// DISPLAY ATTENDANCE
	public static boolean displayAttendance(ArrayList<Attendance> attendanceList, ArrayList<CCA> ccaList) {
		// CCA ATTENDANCE AVAILABLE
		Helper.line(100, "-");
		System.out.println(String.format("%50s", "CCA ATTENDANCE LIST"));
		Helper.line(100, "-");
		String output = "";

		ArrayList<Integer> ccaIdList = new ArrayList<>();
		for (int i = 0; i < ccaList.size(); i++) {
			ccaIdList.add(ccaList.get(i).getCcaID());
		}

		ArrayList<Integer> currentCca = new ArrayList<>();
		for (int i = 0; i < attendanceList.size(); i++) {
			if (ccaIdList.contains(attendanceList.get(i).getCca_ID())) {
				output += String.format("%d. %s\n", attendanceList.get(i).getCca_ID(),
						attendanceList.get(i).getCca_name());
				currentCca.add(attendanceList.get(i).getCca_ID());
			}
		}

		if (currentCca.size() == 0) {
			System.out.println("There is no CCA attendance to view");
			return false;
		} else {
			System.out.println(output);
			Helper.line(100, "-");
			int ccaChoice = Helper.readInt("Enter CCA attendance to view > ");

			if (!currentCca.contains(ccaChoice)) {
				Helper.line(100, "-");
				System.out.println("Invalid CCA ID");
				Helper.line(100, "-");
				return false;
			} else {
				String ccaName = "";
				for (int i = 0; i < ccaList.size(); i++) {
					if (ccaChoice == ccaList.get(i).getCcaID()) {
						ccaName = ccaList.get(i).getCca_name();
					}
				}

				Helper.line(100, "-");
				System.out.println(String.format("%50s", ccaName + " Attendance"));
				Helper.line(100, "-");
				System.out.println(String.format("%-10s %-10s %-25s %-10s %-10s %-10s", "CCA ID", "CCA NAME",
						"TIMESLOT", "USER ID", "USERNAME", "STATUS"));
				Helper.line(100, "-");
				output = "";
				for (int i = 0; i < attendanceList.size(); i++) {
					if (ccaChoice == attendanceList.get(i).getCca_ID()) {
						output += attendanceList.get(i).display();
					}
				}
				System.out.println(output);
				return true;
			}
		}

	}

	public static void displayUsers(ArrayList<User> userList) {
		Helper.line(100, "-");
		System.out.println(String.format("%50s", "Users"));
		Helper.line(100, "-");
		System.out.println(
				String.format("%-10s %-10s %-10s %-10s %-10s", "USER ID", "USERNAME", "PASSWORD", "ROLE", "CCA ID"));
		Helper.line(100, "-");
		String output = "";
		for (int i = 0; i < userList.size(); i++) {
			output += userList.get(i).display();
		}
		System.out.println(output);
	}

	// GET USER ROLE
	public static String getRole(ArrayList<User> userList, int user_ID, String password) {
		String role = "";
		for (int i = 0; i < userList.size(); i++) {
			if (userList.get(i).getUserID() == user_ID && userList.get(i).getPassword().equals(password)) {
				role = userList.get(i).getRole();
			}
		}
		return role;
	}

	// DISPLAY CCA DETAILS
	public static void displayCCA(ArrayList<CCA> ccaList) {
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
	public static boolean checkExistingCcaID(ArrayList<CCA> ccaList, int cca_ID) {
		for (int i = 0; i < ccaList.size(); i++) {
			if (cca_ID == ccaList.get(i).getCcaID()) {
				return true;
			}
		}
		return false;
	}

	// CHECK FOR EXISTING APPLICATION
	public static boolean checkExistingApplication(ArrayList<Application> applicationList, int user_ID, int cca_ID) {
		for (int i = 0; i < applicationList.size(); i++) {
			if (user_ID == applicationList.get(i).getUser_ID() && cca_ID == applicationList.get(i).getCca_ID()) {
				return true;
			}
		}
		return false;
	}

	// GET NUMBER OF APPLIED CCAs
	public static int getAppliedCCA(ArrayList<Application> applicationList, int user_ID) {
		int appliedCCAs = 0;
		for (int i = 0; i < applicationList.size(); i++) {
			if (user_ID == applicationList.get(i).getUser_ID()) {
				appliedCCAs++;
			}
		}
		return appliedCCAs;
	}

	// REGISTER FOR CCA
	public static void registerCCA(ArrayList<CCA> ccaList, ArrayList<User> userList,
			ArrayList<Application> applicationList, int user_ID, int cca_ID) {
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

	// CHECK IF TIMESLOT EXIST WITH SAME CCA NAME
	public static boolean checkExistingTimeslotAndName(ArrayList<CCA> ccaList, String timeslot, String cca_name) {
		for (int i = 0; i < ccaList.size(); i++) {
			if (timeslot.equalsIgnoreCase(ccaList.get(i).getTimeslot())
					&& cca_name.equalsIgnoreCase(ccaList.get(i).getCca_name())) {
				return true;
			}
		}
		return false;
	}

	// CHECK IF CCA NAME IS CORRECT
	public static boolean checkExistingCcaName(ArrayList<CCA> ccaList, String cca_name) {
		for (int i = 0; i < ccaList.size(); i++) {
			if (cca_name.equals(ccaList.get(i).getCca_name())) {
				return true;
			}
		}
		return false;
	}

	public static void editCCA(ArrayList<CCA> ccaList, int choice, int position) {
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
	}

	public static void deleteCCA(ArrayList<CCA> ccaList, int cca_ID, String cca_name) {
		for (int i = 0; i < ccaList.size(); i++) {
			if (cca_ID == ccaList.get(i).getCcaID() && cca_name.equals(ccaList.get(i).getCca_name())) {
				ccaList.remove(i);
				Helper.line(100, "-");
				System.out.println("CCA successfully removed");
				Helper.line(100, "-");
			}
		}
	}

	// DISPLAY APPLICATION APPROVAL STATUS
	public static boolean displayApplications(ArrayList<Application> applicationList, int user_ID, String role) {
		Helper.line(100, "-");
		System.out.println(String.format("%60s", "VIEW CCA APPROVAL STATUS"));
		Helper.line(100, "-");
		System.out.println(String.format("%-10s %-10s %-20s %-10s %-10s %-10s", "CCA ID", "CCA NAME", "TIMESLOT",
				"USER_ID", "USERNAME", "STATUS"));
		Helper.line(100, "-");
		String output = "";
		if (applicationList.size() != 0) {
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
			return true;
		} else {
			System.out.println("There are no applications");
			return false;
		}
	}

	// CHECK USER ID
	public static boolean checkExistingUserID(ArrayList<User> userList, int user_ID) {
		for (int i = 0; i < userList.size(); i++) {
			if (user_ID == userList.get(i).getUserID()) {
				return true;
			}
		}
		return false;
	}

	public static void editApplication(ArrayList<Application> applicationList, ArrayList<CCA> ccaList,
			ArrayList<User> userList, ArrayList<Attendance> attendanceList, int approve, int ccaID, int userID) {
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
				if (ccaID == applicationList.get(i).getCca_ID() && userID == applicationList.get(i).getUser_ID()) {
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
				if (ccaID == applicationList.get(i).getCca_ID() && userID == applicationList.get(i).getUser_ID()) {
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

	public static void deleteApplication(ArrayList<Application> applicationList, int ccaID, int userID) {
		for (int i = 0; i < applicationList.size(); i++) {
			if (ccaID == applicationList.get(i).getCca_ID() && userID == applicationList.get(i).getUser_ID()) {
				applicationList.remove(i);
				Helper.line(100, "-");
				System.out.println("Application succesfully deleted");
				Helper.line(100, "-");
			}
		}
	}

}
