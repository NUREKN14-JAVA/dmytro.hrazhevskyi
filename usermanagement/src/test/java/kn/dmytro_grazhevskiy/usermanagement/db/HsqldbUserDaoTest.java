package kn.dmytro_grazhevskiy.usermanagement.db;

import static org.junit.Assert.*;

import java.util.Collection;
import java.util.Date;

import org.dbunit.DatabaseTestCase;
import org.dbunit.database.DatabaseConnection;
import org.dbunit.database.IDatabaseConnection;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.xml.XmlDataSet;
import org.junit.Before;
import org.junit.Test;

import junit.framework.TestCase;
import kn.dmytro_grazhevskiy.usermanagement.User;

public class HsqldbUserDaoTest extends DatabaseTestCase {

	 private HsqldbUserDao dao;
	 private ConnectionFactory connectionFactory;
	 
		@Before
		protected void setUp() throws Exception {
			super.setUp();
			
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

		@Override
		protected IDatabaseConnection getConnection() throws Exception {
			connectionFactory=new ConnectionFactoryImpl( "org.hsqldb.jdbcDriver", "jdbc:hsqldb:file:db/usermanagement", "sa", "");
			return new DatabaseConnection(connectionFactory.createConnection()) ;
		}
public void testFindAll(){
	try {
		Collection collection = dao.findAll();
		  assertNotNull("Collection is null", collection);
          assertEquals("Collection size.", 2, collection.size());
	} catch (DatabaseException e) {
		e.printStackTrace();
		fail(e.toString());
	}
}
		@Override
		protected IDataSet getDataSet() throws Exception {
		IDataSet dataSet = new XmlDataSet(getClass().getClassLoader().getResourceAsStream("usersDataSet.xml"));
			return dataSet;
		}



}
