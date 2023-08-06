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
		
	}

	
	@Test
	public void c206_test() {
		//fail("Not yet implemented"); 
		assertTrue("C206_CaseStudy_SampleTest ",true);
	}
	
	@Test
	public void testMaintainUsers() {
		C206_CaseStudy.maintainUsers(userList,ccaList);
		// Add user
		userList.add(user1);
		assertEquals("Test that infomation are submitted correctly", user1, userList.get(0));
		// Edit user
		String newName = "John";
		userList.get(0).setName(newName);
		assertEquals("Test that editing of user info will be shown correctly", userList.get(0).getName(), newName);
		// Delete user
		userList.remove(user1);
		assertEquals("Test that deletion user will be shown correctly", 0, userList.size());
		
	}
@After
	public void tearDown() throws Exception {
	}

}
