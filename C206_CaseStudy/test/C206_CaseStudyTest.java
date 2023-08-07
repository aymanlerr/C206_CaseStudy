import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class C206_CaseStudyTest {
	private User user1;
	private User user2;
	private User user3;
	private User user4;
	private User user5;
	
	private CCA cca1;
	private CCA cca2;
	private CCA cca3;
	
	private Application app1;
	
	private Attendance att1;
	
	private ArrayList<User> userList = new ArrayList<>();
	private ArrayList<CCA> ccaList = new ArrayList<>();
	private ArrayList<Application> applicationList = new ArrayList<>();
	private ArrayList<Attendance> attendanceList = new ArrayList<>();

	
	public C206_CaseStudyTest() {
		super();
	}
	
	@Before
	public void setUp() throws Exception {
		user1 = new User(1, "Aiman", "1", "student");
		user2 = new User(2, "Aldusto", "2", "teacher");
		user3 = new User(3, "kedongdong", "3", "admin");
		user4 = new User(4, "Liam", "4", "student");
		user5 = new User(5, "Amran", "5", "student");
		
		att1 = (new Attendance(1, "Soccer", "Monday: 4pm-9pm", 1, "Aiman","" ));
		
	
		cca1 = (new CCA(1, "Soccer", "Have fun playing soccer in this CCA!", "Wednesday: 4pm-6pm"));
		cca2 = (new CCA(2, "Badminton", "Have fun playing badminton in this CCA!", "Wednesday: 4pm-6pm"));
		cca3 = (new CCA(3, "Tennis", "Have fun playing tennis in this CCA!", "Friday: 4pm-6pm"));
		
		app1 = (new Application(1, "Soccer", "Wednesday: 4pm-6pm", 1, "Aiman", "approved"));
		
	}

	
	@Test
	public void c206_test() {
		//fail("Not yet implemented"); 
		assertTrue("C206_CaseStudy_SampleTest ",true);
	}
	
	@Test
	public void testViewApplications() {
		//Create new application and user arraylist
		applicationList = new ArrayList<>();
		userList = new ArrayList<>();
		
		//Test that arraylists exist and are empty
		//Add application and user into their new respective arraylist
		//Test that application and user has now been added into their arraylists
		assertNotNull("Test if there is a valid application arraylist to display", applicationList);
		assertNotNull("Test if there is a valid application arraylist to display", userList);
		assertEquals("Test that the application arraylist is empty", 0, applicationList.size());
		assertEquals("Test that the application arraylist is empty", 0, userList.size());
		applicationList.add(app1);
		//adding teacher user and admin user 
		userList.add(user2); //teacher
		userList.add(user3); //admin
		assertEquals("Test that arrayList has increased due to new application", 1, applicationList.size());
		assertEquals("Test that arrayList has increased due to new users", 2, userList.size());
		
		//Test that teacher user can view all applications
		boolean output = C206_CaseStudy.displayApplications(applicationList, userList.get(0).getUserID(), userList.get(0).getRole());
		assertTrue("Test that the output shows all applications", output);

	}
	
	@Test
	public void testDeleteAttendance() {
		attendanceList = new ArrayList<>();
		assertNotNull("Test arraylist is created", attendanceList);
		assertEquals("Test that arraylist size is 0", 0, attendanceList.size());
		attendanceList.add(att1);
		assertEquals("Test that arraylist size is 1", 1, attendanceList.size());
		C206_CaseStudy.deleteAttendance(attendanceList, 1, 1);
		assertEquals("Test that arraylist size is 0", 0, attendanceList.size());
		
	}
	
	@Test
	public void testAddUsers() {
		//define user details
		int userId = 1;
		String username = "Aiman";
		String password = "1";
		String role = "student";
		
		assertNotNull("Test if there is valid user arrayList to add to", userList);
		assertEquals("Test that the user arrayList is empty", 0, userList.size());
		// Test Add user
		C206_CaseStudy.addUser(userList, userId, username, password, role);
		assertEquals("Test that arrayList increases size to 1", 1, userList.size());
	}
	
	@Test
	public void testDeleteUser() {
		int userId = 1;
		String username = "Aiman";
		String password = "1";
		String role = "student";
		
		assertNotNull("Test that there is a valid arryaList to add to", userList);
		
		//add user
		C206_CaseStudy.addUser(userList, userId, username, password, role);
		assertEquals("Test that user arrayList increases size to 1", 1, userList.size());
		//delete user
		C206_CaseStudy.deleteUser(userList, userId, username);
		assertEquals("Test that user arrayList decreases size to 0", 0, userList.size());
	}
	
	@Test
	public void testSetUserCcaId() {
		int userId = 1;
		String username = "Aiman";
		String password = "1";
		String role = "student";
		
		assertNotNull("Test that there is a valid arryaList to add to", userList);
		
		//add user
		C206_CaseStudy.addUser(userList, userId, username, password, role);
		assertEquals("Test that user arrayList increases size to 1", 1, userList.size());
		//check that ccaId is -1
		assertEquals("Test that user CCA id is -1", -1, userList.get(0).getCca_ID());
		//set User CCA Id
		C206_CaseStudy.setUserCcaId(userList, 0, 1);
		assertEquals("Test that user that set CCA id set correctly", 1, userList.get(0).getCca_ID());
	}
	
	public void testAddApplication() {
		//Create new application, cca, and user arraylist
		applicationList = new ArrayList<>();
		userList = new ArrayList<>();
		ccaList = new ArrayList<>();
		
		//Adding a user and cca to their respective arrayLists, and declaring user_ID and cca_ID variables
		userList.add(user1);
		ccaList.add(cca1);
		
		
		//Test that there is existing application arraylist to add to
		//Test that the application arraylist is empty
		assertNotNull("Test if there is valid application arrayList to add to", applicationList);
		assertEquals("Test that the user arrayList is empty", 0, applicationList.size());
		
		//Test adding of application to arraylist
		//Test that new application has been added to application arraylist
		C206_CaseStudy.registerCCA(ccaList, userList, applicationList, userList.get(0).getUserID(), ccaList.get(0).getCcaID());
		assertEquals("Test that arrayList has increased due to new application", 1, applicationList.size());
	}
	
	
	@Test
	public void testDeleteApplication() {
		//new application arraylist
		applicationList = new ArrayList<>();
		
		//test for empty arraylist
		assertNotNull("Test if there is any approval arraylist to delete from", applicationList);
		assertEquals("Test that arraylist is empty", 0, applicationList.size());
		
		//add item in arraylist
		applicationList.add(app1);
		assertEquals("Test that arraylist size is 1", 1, applicationList.size());
		assertSame("Test that application is added", app1, applicationList.get(0));
		
		//remove item in arraylist
		C206_CaseStudy.deleteApplication(applicationList, 1, 1);
		assertEquals("Test that arraylist size is 0", 0, applicationList.size());
		
	}

@After
	public void tearDown() throws Exception {
	}

}
