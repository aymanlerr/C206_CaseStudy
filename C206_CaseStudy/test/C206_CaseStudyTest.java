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
		
	
		cca1 = (new CCA(1, "Soccer", "Have fun playing soccer in this CCA!", "Wednesday: 4pm-6pm"));
		cca2 = (new CCA(2, "Badminton", "Have fun playing badminton in this CCA!", "Wednesday: 4pm-6pm"));
		cca3 = (new CCA(3, "Tennis", "Have fun playing tennis in this CCA!", "Friday: 4pm-6pm"));
		
		app1 = (new Application(1, "Soccer", "Wednesday: 4pm-6pm", 1, "gh", "gh"));
		
		att1 = (new Attendance(1, "Soccer", "Monday: 4pm-5pm", 1, "Aiman", ""));
		
	}

	
	@Test
	public void c206_test() {
		//fail("Not yet implemented"); 
		assertTrue("C206_CaseStudy_SampleTest ",true);
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
	public void testDeleteApplication() {
		//new application arraylist
		applicationList = new ArrayList<>();
		
		//test for existing application arraylist
		//test for empty arraylist
		assertNotNull("Test if there is any application arraylist to delete from", applicationList);
		assertEquals("Test that arraylist is empty", 0, applicationList.size());
		
		//add item in arraylist
		//test that applicationList is 1
		//test that item is added in arraylist
		applicationList.add(app1);
		assertEquals("Test that arraylist size is 1", 1, applicationList.size());
		assertSame("Test that application is added", app1, applicationList.get(0));
		
		//remove item in arraylist
		//test that applicationList is 0
		//test that item is removed from arraylist
		applicationList.remove(app1);
		assertEquals("Test that arraylist size is 0", 0, applicationList.size());
		
		C206_CaseStudy.deleteApplication(applicationList, 1, 1);
	}

	@Test
	public void displayCCA() {
		//view All CCA
		ccaList.add(cca1);
		ccaList.add(cca2);
		ccaList.add(cca3);
		
		
		C206_CaseStudy.displayCCA(ccaList);
		
		assertEquals("Test that the displayed CCAs contain the text 'view all activities'", ccaList);

        // Test that the student can view all CCAs when clicking on the "view all activities" button.
        assertTrue("Test that the displayed CCAs contain CCA 1 - Soccer", ccaList.get(1));
        assertTrue("Test that the displayed CCAs contain CCA 2 - Badminton", ccaList.get(2));
        assertTrue("Test that the displayed CCAs contain CCA 3 - Tennis", ccaList.get(3));
	}

	
	@Test
	public void testDeleteAttendance() {
		// declare new attendance list
		attendanceList = new ArrayList<>();
		
		// check that attendance list exist
		assertNotNull("Test that the attendance list exist", attendanceList);
		
		// check that there is no attendance in the attendance list
		assertEquals("Test that the attendance list is empty", 0, attendanceList.size());
		
		// add attendance
		attendanceList.add(att1);
		
		// check that there is 1 attendance in the attendance list
		assertEquals("Thest that the size of attendance list is 1", 1, attendanceList.size());
		
		// delete attendance
		C206_CaseStudy.deleteAttendance(attendanceList, 1, 1);
		
		// check that there is no attendance in the attendance list
		assertEquals("Test that the size of attendance list is 0", 0, attendanceList.size());
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
