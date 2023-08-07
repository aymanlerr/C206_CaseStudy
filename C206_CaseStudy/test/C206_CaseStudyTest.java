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
	private CCA testaddcca;
	
	private Application app1;
	
	private Attendance att1;

	
	private ArrayList<User> userList;
	private ArrayList<CCA> ccaList;
	private ArrayList<Application> applicationList;
	private ArrayList<Attendance> attendanceList;

	
	public C206_CaseStudyTest() {
		super();
	}
	
	@Before
	public void setUp() throws Exception {
		user1 = (new User(1, "Aiman", "1", "student"));
		user2 = (new User(2, "Aldusto", "2", "teacher"));
		user3 = (new User(3, "kedongdong", "3", "admin"));
		user4 = (new User(4, "Liam", "4", "student"));
		user5 = (new User(5, "Amran", "5", "student"));
		
		att1 = (new Attendance(1, "Soccer", "Monday: 4pm-9pm", 1, "Aiman","" ));
		
	
		cca1 = (new CCA(1, "Soccer", "Have fun playing soccer in this CCA!", "Wednesday: 4pm-6pm"));
		cca2 = (new CCA(2, "Badminton", "Have fun playing badminton in this CCA!", "Wednesday: 4pm-6pm"));
		cca3 = (new CCA(3, "Tennis", "Have fun playing tennis in this CCA!", "Friday: 4pm-6pm"));
		testaddcca = (new CCA(4, "Basketball", "Have fun playing basketball in this CCA!", "Friday: 4pm-6pm"));
		
		app1 = (new Application(1, "Soccer", "Wednesday: 4pm-6pm", 1, "gh", "gh"));
		
		att1 = (new Attendance(1, "Soccer", "Monday: 4pm-5pm", 1, "Aiman", ""));
		
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
		int userId = 1;
		String username = "Aiman";
		String password = "1";
		String role = "student";
		
		userList = new ArrayList<>();
		
		assertNotNull("Test if there is valid user arrayList to add to", userList);
		assertEquals("Test that the user arrayList is empty", 0, userList.size());
		// Test Add user
		C206_CaseStudy.addUser(userList, userId, username, password, role);
		assertEquals("Test that arrayList increases size to 1", 1, userList.size());
	}
	
	@Test
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
		attendanceList = new ArrayList<>();
		
		//test for empty arraylist
		assertNotNull("Test if there is any application arraylist to delete from", applicationList);
		assertEquals("Test that arraylist is empty", 0, applicationList.size());
		
		//add item in arraylist
		applicationList.add(app1);
		attendanceList.add(att1);
		assertEquals("Test that arraylist size is 1", 1, applicationList.size());
		assertSame("Test that application is added", app1, applicationList.get(0));
		
		//remove item in arraylist
		C206_CaseStudy.deleteApplication(applicationList, attendanceList, 1, 1);
		assertEquals("Test that arraylist size is 0", 0, applicationList.size());
		
	}

	@Test
	public void testDisplayCCA() {
		//view All CCA
		ccaList = new ArrayList<>();
		
		assertNotNull("Test that cca list exists", ccaList);
		
	}
	
	@Test
	public void testAddCCA() {
		//test Add CCA
		ccaList = new ArrayList<>();
		ccaList.add(cca1);
        ccaList.add(cca2);
        ccaList.add(cca3);
        
        assertEquals("Test that size of the cca list is 3", 3, ccaList.size());
        
		ccaList.add(testaddcca);
		
		assertEquals("Test that size of the cca list is 4", 4, ccaList.size());
	}
	
	@Test
	public void testDeleteCCA() {
		ccaList = new ArrayList<>();
		
		assertNotNull("Test if there is any cca list to delete from", ccaList);
		assertEquals("Test that arraylist is empty", 0, ccaList.size());
		
		ccaList.add(testaddcca);
		assertEquals("Test that arraylist size is 1", 1, ccaList.size());
		
		ccaList.remove(testaddcca);
		assertEquals("Test that arraylist size is 0", 0, ccaList.size());
			
	}
	
	@Test
	public void testEditCCA() {
		ccaList = new ArrayList<>();
		ccaList.add(cca3);
		
		C206_CaseStudy.editCCA(ccaList, 0, 0);;
	}
	
	@Test
	public void testAddApproval() {
		//new application arraylist
		applicationList = new ArrayList<>();
		
		//test for existing application arraylist
		//test for empty arraylist
		assertNotNull("Test if there is any application arraylist to delete from", applicationList);
		assertEquals("Test that arraylist is empty", 0, applicationList.size());
		
		//add application to maintain
		applicationList.add(app1);
		
		//test that applicationList is 1 (contains application)
		//test that changes made to application is reflected
		String newStatus = "Approval";
		applicationList.get(0).setStatus(newStatus);
		assertEquals("Test that changes that are made to an application’s status is reflected in the database accurately", applicationList.get(0).getStatus(), newStatus);
	}
	
	
	
	
@After
	public void tearDown() throws Exception {
	}


}
