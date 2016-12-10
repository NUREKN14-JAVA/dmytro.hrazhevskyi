package kn.dmytro_grazhevskiy.usermanagement.web;

import java.util.Properties;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.mockobjects.dynamic.Mock;
import com.mockrunner.servlet.BasicServletTestCaseAdapter;

import kn.dmytro_grazhevskiy.usermanagement.db.DaoFactory;
import kn.dmytro_grazhevskiy.usermanagement.db.MockDaoFactory;

public abstract class MockServletTestCase extends BasicServletTestCaseAdapter {

	 private Mock mockUserDao;
	    
	    /*
	     * @see BasicServletTestCaseAdapter#setUp()
	     */
	    protected void setUp() throws Exception {
	        super.setUp();
	        Properties properties = new Properties();
	        properties.setProperty("dao.factory", MockDaoFactory.class.getName());
	        DaoFactory.init(properties);
	        setMockUserDao(((MockDaoFactory) DaoFactory.getInstance()).getMockUserDao());
	    }

	    /*
	     * @see BasicServletTestCaseAdapter#tearDown()
	     */
	    protected void tearDown() throws Exception {
	        getMockUserDao().verify();
	        super.tearDown();
	    }
	    
	    

	 
	    public Mock getMockUserDao() {
	        return mockUserDao;
	    }
	  
	    public void setMockUserDao(Mock mockUserDao) {
	        this.mockUserDao = mockUserDao;
	    }

}
