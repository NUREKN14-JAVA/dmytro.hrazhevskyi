package kn.dmytro_grazhevskiy.usermanagement.gui;

import java.awt.Component;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Properties;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.mockobjects.dynamic.Mock;

import junit.extensions.jfcunit.JFCTestCase;
import junit.extensions.jfcunit.JFCTestHelper;
import junit.extensions.jfcunit.eventdata.JTableMouseEventData;
import junit.extensions.jfcunit.eventdata.MouseEventData;
import junit.extensions.jfcunit.eventdata.StringEventData;
import junit.extensions.jfcunit.finder.DialogFinder;
import junit.extensions.jfcunit.finder.NamedComponentFinder;
import kn.dmytro_grazhevskiy.usermanagement.User;
import kn.dmytro_grazhevskiy.usermanagement.db.DaoFactory;
import kn.dmytro_grazhevskiy.usermanagement.db.DaoFactoryImpl;
import kn.dmytro_grazhevskiy.usermanagement.db.MockDaoFactory;
import kn.dmytro_grazhevskiy.usermanagement.db.MockUserDao;
import kn.dmytro_grazhevskiy.usermanagement.util.Messages;

public class MainFrameTest extends JFCTestCase {

	private MainFrame mainFrame;
	private Mock mockUserDao;
	private List users;

	protected void setUp() throws Exception {
		super.setUp();
		
		
		 try {
	            Properties properties = new Properties();
	            properties.setProperty("dao.factory", MockDaoFactory.class.getName());
	            DaoFactory.init(properties );
	            mockUserDao = ((MockDaoFactory) DaoFactory.getInstance()).getMockUserDao();
	            mockUserDao.expectAndReturn("findAll", new ArrayList());
	           	setHelper(new JFCTestHelper());
	            mainFrame = new MainFrame();
	            
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	        mainFrame.setVisible(true);
		
	}

	@After
	protected void tearDown() throws Exception {
		try {
            mockUserDao.verify();
            mainFrame.setVisible(false);
            getHelper().cleanUp(this);
            super.tearDown();
            
        } catch (Exception e) {
            e.printStackTrace();
        }
	}
	
	private Component find(Class componentClass, String name){
		NamedComponentFinder finder;
		finder = new NamedComponentFinder(componentClass, name);
		finder.setWait(0);
		Component component = finder.find(mainFrame,0);
		assertNotNull("Could not find component '"+ name + "'", component);
		return component;
	}
	
	public void testBrowserControls(){
		find(JPanel.class, "browsePanel");
		JTable table = (JTable) find(JTable.class, "userTable");
        assertEquals(3, table.getColumnCount());
        assertEquals(Messages.getString("UserTableModel.id"), table.getColumnName(0));
        assertEquals(Messages.getString("UserTableModel.first_name"), table.getColumnName(1));
        assertEquals(Messages.getString("UserTableModel.last_name"), table.getColumnName(2));
		find(JTable.class, "userTable");
		find(JButton.class, "addButton");
		find(JButton.class, "editButton");
		find(JButton.class, "deleteButton");
		find(JButton.class, "detailButton");

	}

	public void testAddUser(){
		
		try{
		String firstName = "John";
		String lastName = "Doe";
		Date now = new Date();
		
		User user = new User(firstName, lastName, now);
		User expectedUser = new User(new Long(3), firstName, lastName, now);
		mockUserDao.expectAndReturn("create", user, expectedUser);
		
		ArrayList users = new ArrayList();
		users.add(expectedUser);
		mockUserDao.expectAndReturn("findAll", users);
		JTable table = (JTable) find(JTable.class, "userTable");
assertEquals(0,table.getRowCount());
		 JButton addButton = (JButton) find(JButton.class, "addButton");
		 getHelper().enterClickAndLeave(new MouseEventData(this, addButton));
		 find(JPanel.class, "addPanel");
		JTextField firstNameField = (JTextField) find(JTextField.class, "firstNameField");
		JTextField lastNameField = (JTextField) find(JTextField.class, "lastNameField");
		JTextField dateOfBirthField = (JTextField)find(JTextField.class, "dateOfBirthField");
		JButton okButton = (JButton) find(JButton.class, "okButton");
	    find(JButton.class, "cancelButton");
		
		getHelper().sendString(new StringEventData(this,firstNameField, firstName));
		getHelper().sendString(new StringEventData(this,lastNameField, lastName));
		
		DateFormat formatter = DateFormat.getDateInstance();
		String date = formatter.format(now);;
		getHelper().sendString(new StringEventData(this,dateOfBirthField, date));
		getHelper().enterClickAndLeave(new MouseEventData(this, okButton));
		
		find (JPanel.class, "browsePanel");
		 table = (JTable) find(JTable.class, "userTable");
		 assertEquals(1,table.getRowCount());

		}
		catch(Exception e){
			fail(e.toString());
		}
	
	}
	
	public void testCancelAddUser() {
        try {
            String firstName = "John";
            String lastName = "Doe";
            Date now = new Date();
            ArrayList users = new ArrayList(this.users);
            mockUserDao.expectAndReturn("findAll", users);
            JTable table = (JTable) find(JTable.class, "userTable");
            assertEquals(1, table.getRowCount());
            JButton addButton = (JButton) find(JButton.class, "addButton");
            getHelper().enterClickAndLeave(new MouseEventData(this, addButton));
            find(JPanel.class, "addPanel");
            fillField(firstName, lastName, now);
            JButton cancelButton = (JButton) find(JButton.class, "cancelButton");
            getHelper().enterClickAndLeave(new MouseEventData(this, cancelButton));
            find(JPanel.class, "browsePanel");
            table = (JTable) find(JTable.class, "userTable");
            assertEquals(1, table.getRowCount());            
            mockUserDao.verify();
        } catch (Exception e) {
            fail(e.toString());
        }
    }

    public void testCancelEditUser() {
        try {
            String firstName = "John";
            String lastName = "Doe";
            Date now = new Date();
            User expectedUser = new User(new Long(1), firstName, lastName, now);
            List users = new ArrayList(this.users);
            users.add(expectedUser);           
            mockUserDao.expectAndReturn("findAll", users);          
            JTable table = (JTable) find(JTable.class, "userTable");
            assertEquals(1, table.getRowCount());
            JButton editButton = (JButton) find(JButton.class, "editButton");
            getHelper().enterClickAndLeave(new MouseEventData(this, editButton));
            String title = "Edit user";
          findDialog(title);           
            getHelper().enterClickAndLeave(new JTableMouseEventData(this, table, 0, 0, 1));
            getHelper().enterClickAndLeave(new MouseEventData(this, editButton));          
            find(JPanel.class, "editPanel");          
            JTextField firstNameField = (JTextField) find(JTextField.class,
                    "firstNameField");
            JTextField lastNameField = (JTextField) find(JTextField.class,
                    "lastNameField");
            JTextField dateOfBirthField = (JTextField) find(JTextField.class,
                    "dateOfBirthField");         
            assertEquals("George", firstNameField.getText());
            assertEquals("Bush", lastNameField.getText());          
            getHelper().sendString(
                    new StringEventData(this, firstNameField, firstName));
            getHelper().sendString(
                    new StringEventData(this, lastNameField, lastName));
            DateFormat formatter = DateFormat.getDateInstance();
            String date = formatter.format(now);
            getHelper().sendString(
                    new StringEventData(this, dateOfBirthField, date));
            JButton cancelButton = (JButton) find(JButton.class, "cancelButton");
            getHelper().enterClickAndLeave(new MouseEventData(this, cancelButton));
            find(JPanel.class, "browsePanel");
            table = (JTable) find(JTable.class, "userTable");
            assertEquals(2, table.getRowCount());
            mockUserDao.verify();      
        } catch (Exception e) {
            fail(e.toString());
        }
    }

    public void testEditUser() {
        try {           
            User expectedUser = new User(new Long(1000), "George", "Bush", new Date());
            System.out.println(expectedUser);
            mockUserDao.expect("update", expectedUser);
            List users = new ArrayList(this.users);
            mockUserDao.expectAndReturn("findAll", users);     
            JTable table = (JTable) find(JTable.class, "userTable");
            assertEquals(1, table.getRowCount());
            JButton editButton = (JButton) find(JButton.class, "editButton");
            getHelper().enterClickAndLeave(new JTableMouseEventData(this, table, 0, 0, 1));
            getHelper().enterClickAndLeave(new MouseEventData(this, editButton));         
            find(JPanel.class, "editPanel");
            JTextField firstNameField = (JTextField) find(JTextField.class,
                    "firstNameField");
            JTextField lastNameField = (JTextField) find(JTextField.class,
                    "lastNameField");
            JTextField dateOfBirthField = (JTextField) find(JTextField.class,
                    "dateOfBirthField");           
            getHelper().sendString(
                    new StringEventData(this, firstNameField, "1"));
            getHelper().sendString(
                    new StringEventData(this, lastNameField, "1"));
            JButton okButton = (JButton) find(JButton.class, "okButton");
            getHelper().enterClickAndLeave(new MouseEventData(this, okButton));
            find(JPanel.class, "browsePanel");
            table = (JTable) find(JTable.class, "userTable");
            assertEquals(1, table.getRowCount());
            mockUserDao.verify();
            
        } catch (Exception e) {
            fail(e.toString());
        }
    }

    private void findDialog(String title) {
        JDialog dialog;
        DialogFinder dFinder = new DialogFinder(title);
        dialog = (JDialog) dFinder.find();
        assertNotNull("Could not find dialog '" + title + "'", dialog);
        getHelper().disposeWindow( dialog, this );
    }

    private void fillField(String firstName, String lastName, Date now) {
        JTextField firstNameField = (JTextField) find(JTextField.class,
                "firstNameField");
        JTextField lastNameField = (JTextField) find(JTextField.class,
                "lastNameField");
        JTextField dateOfBirthField = (JTextField) find(JTextField.class,
                "dateOfBirthField");
        getHelper().sendString(
                new StringEventData(this, firstNameField, firstName));
        getHelper().sendString(
                new StringEventData(this, lastNameField, lastName));
        DateFormat formatter = DateFormat.getDateInstance();
        String date = formatter.format(now);
        getHelper().sendString(
                new StringEventData(this, dateOfBirthField, date));
    }

    public void testDeleteUser() {
        try {
            User expectedUser = new User(new Long(1000), "George", "Bush", new Date());
            mockUserDao.expect("delete", expectedUser);
            List users = new ArrayList();
            mockUserDao.expectAndReturn("findAll", users);      
            JTable table = (JTable) find(JTable.class, "userTable");
            assertEquals(1, table.getRowCount());
            JButton deleteButton = (JButton) find(JButton.class, "deleteButton");
            getHelper().enterClickAndLeave(new JTableMouseEventData(this, table, 0, 0, 1));
            getHelper().enterClickAndLeave(new MouseEventData(this, deleteButton));            
            find(JPanel.class, "browsePanel");
            table = (JTable) find(JTable.class, "userTable");
            assertEquals(0, table.getRowCount());
            mockUserDao.verify();
            
        } catch (Exception e) {
            fail(e.toString());
        }
    }
    
    public void testDetails() {

    	try {
			mockUserDao.expectAndReturn("findAll", users);
			JTable table = (JTable) find(JTable.class, "userTable");
			assertEquals(1, table.getRowCount());
			JButton detailsButton = (JButton) find(JButton.class, "detailsButton");
			getHelper().enterClickAndLeave(new JTableMouseEventData(this, table, 0, 0, 1));
			getHelper().enterClickAndLeave(new MouseEventData(this, detailsButton));
			find(JPanel.class, "detailsPanel");
			JLabel firstName = (JLabel) find(JLabel.class, "firstName");
			JLabel lastName = (JLabel) find(JLabel.class, "lastName");
			JButton okButton = (JButton) find(JButton.class, "okButton");
			getHelper().enterClickAndLeave(new MouseEventData(this, okButton));
			table = (JTable) find(JTable.class, "userTable");
			find(JPanel.class, "browsePanel");
			mockUserDao.verify();
		} catch (Exception e) {
			fail(e.toString());
		}    	
    }
}
