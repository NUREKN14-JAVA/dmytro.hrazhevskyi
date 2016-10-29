package kn.dmytro_grazhevskiy.usermanagement.db;

import static org.junit.Assert.*;

import java.util.Date;

import org.junit.Before;
import org.junit.Test;

import junit.framework.TestCase;
import kn.dmytro_grazhevskiy.usermanagement.User;

public class HsqldbUserDaoTest extends TestCase {

	 private HsqldbUserDao dao;
	 private ConnectionFactory connectionFactory;
	 
		@Before
		protected void setUp() throws Exception {
			super.setUp();
			connectionFactory=new ConnectionFactoryImpl();
			dao = new HsqldbUserDao(connectionFactory);
			
		}

		@Test
		public void testCreate() {
		 try {
			User user = new User();
			 user.setFirstName("John");
			 user.setLastName("Doe");
			 user.setDateOfBirth(new Date());
			 assertNull(user.getId());
			 user = dao.create(user);
			 assertNotNull(user);
			 assertNotNull(user.getId());
		} catch (DatabaseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			fail(e.toString());
		}
		 
		 
		}



}
