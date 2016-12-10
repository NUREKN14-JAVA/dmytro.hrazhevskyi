package kn.dmytro_grazhevskiy.usermanagement;

import static org.junit.Assert.*;

import java.util.Calendar;
import java.util.Date;

import org.junit.Before;
import org.junit.Test;

import junit.framework.TestCase;
public class UserTest extends TestCase {
private User user;
private Date dateOfBirth;

	public void setUp() throws Exception {
		super.setUp();
		user=new User();
		
		Calendar calendar = Calendar.getInstance();
		calendar.set(1984, Calendar.MAY, 26);
		dateOfBirth=calendar.getTime();
		
	}
public void testGetFullName(){
	user.setFirstName("John");
	user.setLastName("Doe");
	assertEquals("Doe, John", user.getFullName());
}
	public void testGetAge(){
		user.setDateOfBirth(dateOfBirth);
		assertEquals(2016-1984, user.getAge());
	}

}
