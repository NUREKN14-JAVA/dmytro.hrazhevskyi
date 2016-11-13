package kn.dmytro_grazhevskiy.usermanagement.db;

import java.util.Calendar;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import kn.dmytro_grazhevskiy.usermanagement.User;

public class MockUserDao implements UserDao {
    private long id = 0;
    private Map<Long, User> users = new HashMap<Long, User>();
    
    public MockUserDao(){
    	User user = new User();
    	user.setId(id);
    	user.setFirstName("Patrick");
    	user.setLastName("Rotfuss");
Calendar calendar = Calendar.getInstance();
calendar.set(1973, Calendar.JUNE, 6 );
user.setDateOfBirth(calendar.getTime());
users.put(id++, user);

user.setId(id);
user.setFirstName("Max");
user.setLastName("Weber");
calendar.set(1854, Calendar.APRIL, 21 );
user.setDateOfBirth(calendar.getTime());
users.put(id++, user);

}

	public User create(User user) throws DatabaseException {
		Long currentId = new Long(++id);
        user.setId(currentId);
        users.put(currentId, user);
        return user;
	}


	public void update(User user) throws DatabaseException {
		 Long currentId = user.getId();
	        users.remove(currentId);
	        users.put(currentId, user);

	}

	public void delete(User user) throws DatabaseException {
		   Long currentId = user.getId();
	        users.remove(currentId);
	}

	public User find(Long id) throws DatabaseException {
		 return (User) users.get(id);
	}

	public Collection findAll() throws DatabaseException {
		  return users.values();
	}

	@Override
	public Collection find(String firstName, String lastName) throws DatabaseException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setConnectionFactory(ConnectionFactory connectionFactory) {
		// TODO Auto-generated method stub

	}

}
